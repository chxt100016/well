package org.wella.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.LogisticsInfoDao;
import org.wella.dao.VehicleGrabDao;
import org.wella.dao.VehicleGrabInfoDao;
import org.wella.entity.LogisticsInfo;
import org.wella.entity.VehicleGrab;
import org.wella.entity.VehicleGrabInfo;
import org.wella.entity.VehicleInfo;
import org.wella.service.SenderService;

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
        ConvertUtil.convertDataBaseMapToJavaMap(res);
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
        return res;
    }
}
