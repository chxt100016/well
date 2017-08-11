package org.wella.platform.service.impl;

import io.wellassist.utils.IPUtils;
import io.wellassist.utils.Query;
import io.wellassist.utils.ShiroUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.*;
import org.wella.entity.OrderLog;
import org.wella.platform.service.TradeService;
import org.wella.service.WaOrderService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/29.
 */
@Service("tradeServiceImpl")
public class TradeServiceImpl implements TradeService{

    @Autowired
    private OrderTransDao orderTransDao;

    @Autowired
    private TradeDAO tradeDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderLogDao orderLogDao;

    @Autowired
    private ZorderDao zorderDao;

    @Autowired
    private OrderVehicleDao orderVehicleDao;


    @Autowired
    private WaOrderService waOrderServiceImpl;

    @Autowired
    private UserMoneyDao userMoneyDao;

    @Override
    public Map<String, Object> findOfflinePayInfo(long orderTransId) {
        Map<String,Object> res=orderTransDao.singleOrderTransAttachOrderinfoviewByPrimaryKey(orderTransId);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        return res;
    }

    @Override
    public int offlinePayCheckSubmit(Map<String, Object> param) {
        /*return tradeDao.prodOfflinePayCheckProcess(param);*/
        Long orderTransId=Long.parseLong((String) param.get("orderTransId"));
        Long orderId=Long.parseLong((String)param.get("orderId"));
        int passCheck=Integer.parseInt((String)param.get("passCheck"));
        String checkComment=(String)param.get("checkComment");
        Map<String,Object> orderTrans=orderTransDao.singlePoByPrimaryKey(orderTransId);
        long moneyId=(long)orderTrans.get("money_id");
        Map<String,Object> userMoney=userMoneyDao.singlePoByPrimaryKey(moneyId);
        Map<String,Object> update=new HashMap<>();
        if (passCheck==1){
            if ((int)userMoney.get("jy_type")==1){
                update.put("orderId",orderId);
                update.put("prodPayState",5);
                orderDao.updateOrderByID(update);
                update.clear();
            }else if ((int)userMoney.get("jy_type")==3){
                update.put("orderId",orderId);
                update.put("logisticsPayState",5);
                orderDao.updateOrderByID(update);
                update.clear();
            }
            update.put("moneyId",moneyId);
            update.put("jyState",1);
            userMoneyDao.update(update);
            update.clear();
            update.put("orderTransId",orderTransId);
            update.put("transState",1);
            update.put("checkComment",checkComment);
            orderTransDao.update(update);
            waOrderServiceImpl.checkOrderRepayOff(orderId);
        }else if (passCheck==0){
            if ((int)userMoney.get("jy_type")==1){
                update.put("orderId",orderId);
                update.put("prodPayState",-2);
                orderDao.updateOrderByID(update);
                update.clear();
            }else if ((int)userMoney.get("jy_type")==3){
                update.put("orderId",orderId);
                update.put("logisticsPayState",-2);
                orderDao.updateOrderByID(update);
                update.clear();
            }
            update.put("moneyId",moneyId);
            update.put("jyState",-1);
            userMoneyDao.update(update);
            update.clear();
            update.put("orderTransId",orderTransId);
            update.put("transState",-2);
            update.put("checkComment",checkComment);
            orderTransDao.update(update);
        }
        return 1;
    }

    @Override
    public Map<String, Object> getOrderDetailPageInfo(long orderId) {
        Map<String,Object> res=orderDao.singleOrderinfoByPrimaryKey(orderId);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        int orderState=(int)res.get("orderState");
        int orderStateFirstNumber=0;
        if(orderState>0){
            orderStateFirstNumber=Integer.parseInt(new Integer(orderState).toString().substring(0,1));
        }
        if(orderState==0){
            return res;
        }
        //从wa_order_log表中得到订单价格和数量
        Map<String,Object> orderlog=waOrderServiceImpl.findNewestOrderLog(orderId);
        res.putAll(orderlog);
        if(orderStateFirstNumber<=2){
            return res;
        }
        //从wa_zorder表中得到发货量和成交额
        Map<String,Object> zorderDeliverCount=waOrderServiceImpl.findDeliverProdCount(orderId);
        res.putAll(zorderDeliverCount);

        List<Map<String,Object>> zorders=waOrderServiceImpl.findZorders(orderId);
        res.put("zorders",zorders);

        return res;
    }

    @Override
    @Transactional
    public void updateOrder(Map<String, Object> params) {
        //更新订单单价和数量
        OrderLog ol=new OrderLog();
        long orderId=Long.parseLong((String) params.get("orderId"));
        ol.setOrderId(orderId);
        ol.setOrderPrice(Double.parseDouble((String)params.get("orderPrice")));
        ol.setOrderNumber(Double.parseDouble((String)params.get("orderNumber")));
        ol.setOperationTime(new Date());
        ol.setUserId(ShiroUtils.getUserId());
        ol.setOperationIp((String)params.get("ip"));
        orderLogDao.createOrderLog(ol);

        String [] zorderIds= (String[]) params.get("zorderIds");
        String [] zorderStates=(String [])params.get("zorderStates");
        String [] zorderPrices=(String [])params.get("zorderPrices");
        String [] zorderNums=(String [])params.get("zorderNums");
        String [] orderVehicleIds=(String [])params.get("orderVehicleIds");
        String [] vehicleActualSizes=(String [])params.get("vehicleActualSizes");

        //更新子订单
        if ( null != zorderIds && zorderIds.length > 0 ){
            for (int i=0;i<zorderIds.length;i++){
                int zorderState=Integer.parseInt(zorderStates[i]);
                Map<String,Object> updateMap=new HashMap<String,Object>();
                updateMap.put("zorderId",Long.parseLong(zorderIds[i]));
                updateMap.put("zorderNum",new BigDecimal(zorderNums[i]));
                updateMap.put("zorderPrice",new BigDecimal(zorderPrices[i]));
                //如果子订单状态未为已收货，存疑，则修改为确认
                if (zorderState==11){
                    updateMap.put("zorderState",2);
                }
                zorderDao.updateByPrimaryKey(updateMap);
            }
        }

        //更新发货车辆信息
        if (null != orderVehicleIds && orderVehicleIds.length > 0){
            for (int i=0;i<orderVehicleIds.length;i++){
                Map<String,Object> updateMap=new HashMap<String,Object>();
                updateMap.put("orderVehicleId",Long.parseLong(orderVehicleIds[i]));
                updateMap.put("vehicleActualSize",new BigDecimal(vehicleActualSizes[i]));
                orderVehicleDao.updateByPrimaryKey(updateMap);
            }
        }

        waOrderServiceImpl.checkZordersConfirmed(orderId);

        Map<String,Object> updateorder=new HashMap<>();
        updateorder.put("orderId",orderId);
        updateorder.put("hasQuestion",0);
        orderDao.updateOrderByID(updateorder);

    }

    @Override
    public List orderList(Query query) {
        List<Map<String,Object>> res=tradeDao.orderList(query);
        if (res == null ||res.size()==0){
            return res;
        }
        for (Map<String,Object> order:res){
            if (waOrderServiceImpl.idZordersQuestion((long)order.get("orderId"))){
                order.put("isZordersQuestion",1);
            }else{
                order.put("isZordersQuestion",0);
            }
        }
        return res;
    }

    @Override
    public int orderListCount(Query query) {
        return tradeDao.orderListCount(query);
    }
}
