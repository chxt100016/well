package org.wella.service.impl;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.*;
import org.wella.entity.Order;
import org.wella.entity.OrderLogisticsInfo;
import org.wella.entity.OrderVehicle;
import org.wella.entity.VehicleInfo;
import org.wella.service.MessageService;
import org.wella.service.RegionService;
import org.wella.service.WaOrderService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/5.
 */
@Service("waOrderServiceImpl")
public class WaOrderServiceImpl implements WaOrderService {

    @Autowired
    private OrderLogDao orderLogDao;

    @Autowired
    private ZorderDao zorderDao;

    @Autowired
    private OrderVehicleDao orderVehicleDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private RegionService regionServiceImpl;

    @Autowired
    private VehicleInfoDao vehicleInfoDao;

    @Autowired
    private OrderLogisticsInfoDao orderLogisticsInfoDao;

    @Autowired
    private LogisticsInfoDao logisticsInfoDao;

    @Autowired
    private VehicleGrabInfoDao vehicleGrabInfoDao;

    @Autowired
    private WaUserDao waUserDao;

    @Autowired
    private MessageService messageServicesk;

    @Override
    public Map<String, Object> findNewestOrderLog(long orderId) {
        Map<String, Object> res=orderLogDao.findNewestOrderLog(orderId);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        return res;
    }

    @Override
    public Map<String, Object> findDeliverProdCount(long orderId) {
        Map query=new HashMap();
        query.put("orderId",orderId);
        query.put("zorderState",1);
        Map<String,Object> res=zorderDao.findProdCountByConditions(query);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        return res;
    }

    @Override
    public Map<String, Object> findReceiveProdCount(long orderId) {
        Map query=new HashMap();
        query.put("orderId",orderId);
        query.put("zorderState",2);
        Map<String,Object> res=zorderDao.findProdCountByConditions(query);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        return res;
    }

    @Override
    public List<Map<String, Object>> findZorders(long orderId) {
        HashMap query=new HashMap();
        query.put("orderId",orderId);
        query.put("orderBy","zorder_date asc");
        List<Map<String,Object>> zorders=zorderDao.listZordersByConditions(query);
        ConvertUtil.convertDataBaseMapToJavaMap(zorders);
        for (Map<String,Object> zorder:zorders){
            HashMap queryOv=new HashMap();
            queryOv.put("zorderId",(long)zorder.get("zorderId"));
            List<Map<String,Object>> orderVehicles=orderVehicleDao.listOrderVehiclesByConditions(queryOv);
            ConvertUtil.convertDataBaseMapToJavaMap(orderVehicles);
            zorder.put("orderVehicles",orderVehicles);
        }
        return zorders;
    }

    @Override
    public Map<String, Object> findOrderLogisticsInfo(long orderId) {
        Map<String,Object> res=orderDao.findOrderLogisticsBasicInfo(orderId);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        res.put("fromAddress",regionServiceImpl.getDetailAddress((long)res.get("fromRegionId"),(String) res.get("fromRegionAddr")));
        res.put("toAddress",regionServiceImpl.getDetailAddress((long)res.get("toRegionId"),(String) res.get("toRegionAddr")));
        Map queryOLI=new HashMap();
        queryOLI.put("orderId",orderId);
        Map<String,Object> oli=orderLogisticsInfoDao.singleOLIByConditions(queryOLI);
        res.put("customerContacts",oli.get("contacts"));
        res.put("customerConTel",oli.get("con_tel"));
        if((int)res.get("isSelfCar")==0){
            Map queryVIO=new HashMap();
            queryVIO.put("orderId",orderId);
            List<Map<String,Object>> vehicles=vehicleInfoDao.listVehicleInfoByConditions(queryVIO);
            ConvertUtil.convertDataBaseMapToJavaMap(vehicles);
            res.put("vehicles",vehicles);
            res.put("deliverDate",oli.get("deliver_date"));
            res.put("receiveDate",oli.get("receive_date"));
        }else if((int)res.get("isSelfCar")==1){
            Map queryLI=new HashMap();
            queryLI.put("orderId",orderId);
            Map<String,Object> logisticsInfo=logisticsInfoDao.singleLIattachVGByConditions(queryLI);
            res.put("deliverDate",logisticsInfo.get("deliver_date"));
            res.put("receiveDate",logisticsInfo.get("receive_date"));
            Map queryVGI=new HashMap();
            queryVGI.put("vehicleGrabId",logisticsInfo.get("vehicle_grab_id"));
            List<Map<String,Object>> vehicles=vehicleGrabInfoDao.listVehicleGrabInfoByConditions(queryVGI);
            ConvertUtil.convertDataBaseMapToJavaMap(vehicles);
            res.put("vehicles",vehicles);
            Map<String,Object> sender=waUserDao.singleUserByPrimaryKey((long)logisticsInfo.get("sender_user_id"));
            res.put("senderName",sender.get("user_name"));
        }
        return res;
    }

    @Override
    public List<Map<String, Object>> findOrderVehicles(long orderId) {
        Map<String,Object> order=orderDao.singleOrderByPrimaryKey(orderId);
        if((int)order.get("is_self_car")==0){
            Map queryVIO=new HashMap();
            queryVIO.put("orderId",orderId);
            List<Map<String,Object>> vehicles=vehicleInfoDao.listVehicleInfoByConditions(queryVIO);
            ConvertUtil.convertDataBaseMapToJavaMap(vehicles);
            return vehicles;
        }else if((int)order.get("is_self_car")==1){
            Map queryLI=new HashMap();
            queryLI.put("orderId",orderId);
            Map<String,Object> logisticsInfo=logisticsInfoDao.singleLIattachVGByConditions(queryLI);
            Map queryVGI=new HashMap();
            queryVGI.put("vehicleGrabId",logisticsInfo.get("vehicle_grab_id"));
            List<Map<String,Object>> vehicles=vehicleGrabInfoDao.listVehicleGrabInfoByConditions(queryVGI);
            ConvertUtil.convertDataBaseMapToJavaMap(vehicles);
            return vehicles;
        }
        return null;
    }

    @Override
    public boolean checkZordersConfirmed(long orderId) {
        Map<String,Object> order=orderDao.singleOrderByPrimaryKey(orderId);
        if((int)order.get("order_state")!=4){
            return false;
        }
        Map queryzorder=new HashMap();
        queryzorder.put("orderId",orderId);
        List<Map<String,Object>> zorders=zorderDao.listZordersByConditions(queryzorder);
        for(Map<String,Object> zor:zorders){
            if ((int)zor.get("zorder_state")!=2){
                return false;
            }
        }
        Map updateOrder=new HashMap();
        updateOrder.put("orderState",(byte)5);
        updateOrder.put("orderId",orderId);
        BigDecimal saleSjNum=new BigDecimal(0);
        BigDecimal saleSjMoney=new BigDecimal(0);
        for(Map<String,Object> zor:zorders){
            saleSjNum=saleSjNum.add((BigDecimal)zor.get("zorder_num"));
            saleSjMoney=saleSjMoney.add((BigDecimal)zor.get("zorder_money"));
        }
        updateOrder.put("saleSjNum",saleSjNum);
        updateOrder.put("saleSjMoney",saleSjMoney);
        orderDao.updateOrderByID(updateOrder);
        messageServicesk.handleReceiveProdOverMessage(orderId);
        if ((int)order.get("is_self_car")!=1){
            return true;
        }
        Map updateLogisticsInfoMap=new HashMap();
        updateLogisticsInfoMap.put("orderId",orderId);
        updateLogisticsInfoMap.put("state",5);
        logisticsInfoDao.updateByConditions(updateLogisticsInfoMap);
        return true;
    }

    @Override
    public boolean idZordersQuestion(long orderId) {
        Map query=new HashMap();
        query.put("orderId",orderId);
        query.put("zorderState",11);
        List<Map<String,Object>> zorders=zorderDao.listZordersByConditions(query);
        if (zorders != null && zorders.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkOrderRepayOff(long orderId) {
        Map<String,Object> updateOrder=new HashMap<>();
        updateOrder.put("orderId",orderId);
        Map<String,Object> order=orderDao.singleOrderByPrimaryKey(orderId);
        int isSelfCar=(int)order.get("is_self_car");
        if (order.get("prod_pay_state")==null){
            order.put("prod_pay_state",0);
        }
        if (order.get("logistics_pay_state")==null){
            order.put("logistics_pay_state",0);
        }
        int prodPayState=(int)order.get("prod_pay_state");
        int logisticsPayState=(int)order.get("logistics_pay_state");
        if (isSelfCar==0){
            if (prodPayState==5){
                updateOrder.put("orderState",(byte)2);
                orderDao.updateOrderByID(updateOrder);
                return true;
            }
        }else if (isSelfCar==1){
            if (prodPayState==5 && logisticsPayState==5){
                updateOrder.put("orderState",(byte)2);
                orderDao.updateOrderByID(updateOrder);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkOrder2ndpayOff(long orderId) {
        Map<String,Object> query=new HashMap<>();
        query.put("orderId",orderId);
        Map<String,Object> order=orderDao.singleOrderByPrimaryKey(orderId);
        int isSelfCar=(int)order.get("is_self_car");
        if (order.get("prod_2ndpay_state")==null){
            order.put("prod_2ndpay_state",0);
        }
        if (order.get("logistics_2ndpay_state")==null){
            order.put("logistics_2ndpay_state",0);
        }
        int prod2ndpayState=(int)order.get("prod_2ndpay_state");
        int logistics2ndpayState=(int)order.get("logistics_2ndpay_state");
        if (isSelfCar==0){
            if (prod2ndpayState==7){
                query.put("orderState",(byte)6);
                orderDao.updateOrderByID(query);
                return true;
            }
        }else if (isSelfCar==1){
            if (prod2ndpayState==7 && logistics2ndpayState==7){
                query.put("orderState",(byte)6);
                orderDao.updateOrderByID(query);
                return true;
            }
        }
        return false;
    }

    @Override
    public Map<String, Object> orderinfo(long orderId) {
        Map<String,Object> orderinfo=orderDao.singleOrderinfoByPrimaryKey(orderId);
        return orderinfo;
    }
}
