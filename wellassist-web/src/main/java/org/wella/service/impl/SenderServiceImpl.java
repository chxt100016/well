package org.wella.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.*;
import org.wella.entity.*;
import org.wella.service.MessageService;
import org.wella.service.SenderService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by liuwen on 2017/5/10.
 */
@Service("senderServiceImpl")
public class SenderServiceImpl implements SenderService {
    @Autowired
    private LogisticsInfoDao logisticsInfoDao;
    @Autowired
    private VehicleGrabDao vehicleGrabDao;
    @Autowired
    private VehicleGrabInfoDao vehicleGrabInfoDao;
    @Autowired
    private MessageService messageServicesk;
    @Autowired
    private BillDao billDao;
    @Autowired
    private OrderDao orderDao;
    /**
     * 抢单大厅获取物流订单
     * @param param
     * @return
     */
    public List<Map<String,Object>> grabHallInfos(Map param){
        param.put("state",0);
        List<Map<String,Object>> res=logisticsInfoDao.undoLogisticsInfosAttachProdByConditions(param);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        return res;
    }

    @Override
    public int grabHallInfosCount(Map param) {
        param.put("state",0);
        return logisticsInfoDao.CountLogitticsInfoByConditions(param);
    }

    @Override
    public Map<String, Object> grabLogisticsPageInfo(long logisticsId) {
        Map<String, Object> res=logisticsInfoDao.singleLogisticsInfoByPrimaryKey(logisticsId);
        Map<String,Object> userInfo=logisticsInfoDao.selectUserInfo(logisticsId);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        if(userInfo!=null){
            res.putAll(userInfo);
        }
        return res;
    }

    /**
     * 传入参数logisticsId物流订单id，wlUserId物流用户id，grabMoney报价，sjLxr车队联系人,sjLxPhone联系人电话，orderData车队信息，
     * @param param
     */
    /*@Override
    public void grabLogisticsOrder(Map param) {
        VehicleGrab vehicleGrab=new VehicleGrab();
        Map<String,Object> logisticsInfo=logisticsInfoDao.singleLogisticsInfoByPrimaryKey(Long.parseLong((String)param.get("logisticsId")));
        ConvertUtil.convertDataBaseMapToJavaMap(logisticsInfo);
        vehicleGrab.setOrderId((long)logisticsInfo.get("orderId"));
        vehicleGrab.setLogisticsId((long)logisticsInfo.get("logisticsId"));
        vehicleGrab.setWlUserId(Long.parseLong((String)param.get("wlUserId")));
        vehicleGrab.setGrabMoney(new BigDecimal((String)param.get("grabMoney")));
        vehicleGrab.setSjLxr((String)param.get("sjLxr"));
        vehicleGrab.setSjLxPhone((String)param.get("sjLxPhone"));
        vehicleGrab.setGrabDate(new Date());
        vehicleGrab.setGrabState((byte)0);
        vehicleGrabDao.createVehicleGrab(vehicleGrab);
        if(!org.wella.utils.CommonUtil.isEmpty((String)param.get("orderData"))){
            ArrayList<Map<String,Object>> vehicleList = ConvertUtil.converJSONtoArrayListMap((String)param.get("orderData"));
            if (vehicleList.size()>0){
                for (Map vehivleMap:vehicleList) {
                    VehicleGrabInfo vehicleGrabInfo=new VehicleGrabInfo();
                    vehicleGrabInfo.setVehicleGrabId(vehicleGrab.getGrabId());
                    vehicleGrabInfo.setDriverName((String) vehivleMap.get("sjmc"));
                    vehicleGrabInfo.setDriverPhone((String)vehivleMap.get("sjdh"));
                    vehicleGrabInfo.setVehicleNo((String)vehivleMap.get("cph"));
                    vehicleGrabInfoDao.createVehicleGrabInfo(vehicleGrabInfo);
                }
            }
        }
    }*/

    /**
     * 物流方查看自己的抢单记录
     * @param param
     * @return
     */
    @Override
    public List<Map<String, Object>> grabLogisticsList(Map param) {
        return vehicleGrabDao.getGrabLogisticsList(param);
    }

    @Override
    public int grabLogisticsListCount(Map param) {
        return vehicleGrabDao.grabLogisticsListCount(param);
    }

    @Override
    public List<Map<String,Object>> listLogisticsInfoByConditions(Map queryLogistics) {
        return logisticsInfoDao.listLogisticsInfoByConditions(queryLogistics);
    }

    @Override
    public List<Map<String, Object>> homePageLogisicsInfos(Map queryLogistics) {
        return logisticsInfoDao.undoLogisticsInfosByConditions(queryLogistics);
    }

    @Override
    @Transactional
    public int grabLogistics(Map param) {
        long senderUserId=Long.parseLong((String)param.get("senderUserId"));
        long logisticsInfoId=Long.parseLong((String)param.get("logisticsInfoId"));
        BigDecimal grabMoney=new BigDecimal((String)param.get("grabMoney"));
        List<Map<String,Object>> vehicles=ConvertUtil.converJSONtoArrayListMap((String)param.get("grabVehicles"));
        int res=0;
        //insert wa_vehicle_grab 表
        Map<String,Object> logisticsInfo=logisticsInfoDao.singleLogisticsInfoByPrimaryKey(logisticsInfoId);
        VehicleGrab vehicleGrab=new VehicleGrab();
        vehicleGrab.setLogisticsInfoId(logisticsInfoId);
        vehicleGrab.setOrderId((long)logisticsInfo.get("order_id"));
        vehicleGrab.setSenderUserId(senderUserId);
        vehicleGrab.setGrabMoney(grabMoney);
        vehicleGrab.setGrabDate(new Date());
        vehicleGrab.setGrabState((byte)0);
        res+=vehicleGrabDao.createVehicleGrab(vehicleGrab);

        //insert wa_vehicle_grab_info 表
        for(Map<String,Object> vehicle:vehicles){
            VehicleGrabInfo vehicleGrabInfo=new VehicleGrabInfo();
            vehicleGrabInfo.setVehicleGrabId(vehicleGrab.getVehicleGrabId());
            vehicleGrabInfo.setDriverName((String) vehicle.get("driverName"));
            vehicleGrabInfo.setDriverPhone((String)vehicle.get("driverPhone"));
            vehicleGrabInfo.setVehicleNo((String)vehicle.get("vehicleNo"));
            vehicleGrabInfo.setVehicleHangingNo((String)vehicle.get("vehicleHangingNo"));
            vehicleGrabInfo.setVehicleSize(Double.parseDouble((String)vehicle.get("vehicleSize")));
            res+=vehicleGrabInfoDao.createVehicleGrabInfo(vehicleGrabInfo);
        }
        messageServicesk.handleOrderGrabedMessage((long)logisticsInfo.get("order_id"));
        return res;
    }

    @Override
    public List<Map<String, Object>> logisticsOrderListInfo(Map param) {
        List<Map<String, Object>> res=logisticsInfoDao.senderLogisticsOrderListInfo(param);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        return res;
    }

    @Override
    public int logisticsOrderListInfoCount(Map param) {
        return logisticsInfoDao.senderLogisticsOrderListInfoCount(param);
    }

    @Override
    public boolean calcelGrab(HttpServletRequest request, long vehicleGrabId) {
        User user=(User)request.getSession().getAttribute("user");
        Map<String,Object> vehicleGrab=vehicleGrabDao.singleVehicleGrabByPrimaryKey(vehicleGrabId);
        if (vehicleGrab==null||vehicleGrab.size()==0){
            return false;
        }
        if ((long)vehicleGrab.get("sender_user_id")!=user.getUserId()){
            return false;
        }
        if ((int)vehicleGrab.get("grab_state")!=0){
            return false;
        }
        HashMap<String,Object> updatemap=new HashMap<String,Object>();
        updatemap.put("vehicleGrabId",vehicleGrabId);
        updatemap.put("grabState",-1);
        int res=vehicleGrabDao.updateByPrimaryKey(updatemap);
        if(res>0){
            return true;
        }
        return false;
    }

    /**
     * 物流方再抢单
     * @param logisticsId
     * @return 0:订单可以再抢单
     *          -1：订单已确认（被选择）
     *          -5:其他错误
     */
    @Override
    public int reGrabLogistics(long logisticsId) {
        Map<String,Object> logisticsInfo=logisticsInfoDao.singleLogisticsInfoByPrimaryKey(logisticsId);
        if(logisticsInfo==null||logisticsInfo.size()==0){
            return -5;
        }
        int state=(int)logisticsInfo.get("state");
        if(state!=0){
            return -1;
        }
        return 0;
    }


    @Override
    public List<Map<String, Object>> selectDriver(Long logisticsId) {
        List<Map<String,Object>> list=vehicleGrabInfoDao.selectDriver(logisticsId);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        return list;
    }


    @Override
    public List<BigDecimal> profit(Map<String, Object> map) {
        List<Map<Integer,Object>> list=logisticsInfoDao.profit(map);
        PlatformServiceImpl.Transformation(list);
        return PlatformServiceImpl.Transformation(list);
    }

    @Override
    public List requestBillsList(Map query) {
        query.put("inBillState","(-1,0)");
        List<Map<String,Object>> list=billDao.listBilllistviewByConditions(query);
        for(Map<String,Object> bill:list){
            String logisticsInfoIds=bill.get("logistics_info_ids").toString();
            StringBuilder inLogisticsInfoIds=new StringBuilder();
            inLogisticsInfoIds.append("(").append(logisticsInfoIds.trim()).append(")");
            String orderIds=logisticsInfoDao.concatOrderIds(inLogisticsInfoIds.toString());
            StringBuilder inOrderIds=new StringBuilder();
            inOrderIds.append("(").append(orderIds.trim()).append(")");
            String order_nos=orderDao.concatOrderNos(inOrderIds.toString());
            bill.put("order_nos",order_nos);
        }
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        return list;
    }

    @Override
    public int requestBillsListCount(Map query) {
        query.put("inBillState","(-1,0)");
        int res=billDao.listBilllistviewByConditionsCount(query);
        return res;
    }

    @Override
    public int sendBill(long billId, String billNo, int kpType, String eBill, String kdNo, String kdName) {
        int res=0;
        Date now=new Date();
        Bill bill=new Bill();
        bill.setBillId(billId);
        bill.setBillState((byte)1);
        bill.setSendDate(now);
        bill.setBillNo(billNo);
        bill.setKpType((byte)kpType);
        if (kpType==1){
            bill.seteBill(eBill);
            bill.setKdNo("");
            bill.setKdName("");
        }else if (kpType==2){
            bill.seteBill("");
            bill.setKdNo(kdNo);
            bill.setKdName(kdName);
        }
        res+=billDao.update(bill);
        Bill bill1=billDao.query(billId);
        String logisticsInfoIds=bill1.getLogisticsInfoIds();
        StringBuilder sb=new StringBuilder();
        sb.append("(").append(logisticsInfoIds.trim()).append(")");
        Map<String,Object> update=new HashMap<>();
        update.put("inLogisticsIds",sb.toString());
        update.put("kpState",2);
        res +=logisticsInfoDao.updateByConditions(update);
        return res;
    }
}
