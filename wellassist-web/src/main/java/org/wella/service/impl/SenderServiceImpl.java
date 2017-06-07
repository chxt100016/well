package org.wella.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        return logisticsInfoDao.undoLogisticsInfosAttachProdByConditions(param);
    }

    @Override
    public int grabHallInfosCount(Map param) {
        param.put("state",0);
        return logisticsInfoDao.CountLogitticsInfoByConditions(param);
    }


    /**
     * 通过id获取物流订单
     * @param param 包含参数（String）logisticsInfoId
     * @return
     */
    @Override
    public Map<String, Object> findLogisticsInfo(Map param) {
        param.put("logisticsId",Long.parseLong((String)param.get("logisticsId")));
        Map<String, Object> res=logisticsInfoDao.findLogisticsInfo(param);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        return res;
    }

    /**
     * 传入参数logisticsId物流订单id，wlUserId物流用户id，grabMoney报价，sjLxr车队联系人,sjLxPhone联系人电话，orderData车队信息，
     * @param param
     */
    @Override
    public void grabLogisticsOrder(Map param) {
        VehicleGrab vehicleGrab=new VehicleGrab();
        Map logiticsInfoParam=new HashMap();
        logiticsInfoParam.put("logisticsId",Long.parseLong((String)param.get("logisticsId")));
        Map<String,Object> logisticsInfo=logisticsInfoDao.findLogisticsInfo(logiticsInfoParam);
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
    }

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
}
