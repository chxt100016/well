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
    public List<LogisticsInfo> findLogisticsInfos(Map param){
        return logisticsInfoDao.findLogisticsInfos(param);
    }
    /**
     * 抢单大厅获取物流订单总数（分页）
     * @param param
     * @return
     */
    @Override
    public int findLogisticsInfosCount(Map param) {
        return logisticsInfoDao.findLogisticsInfosCount(param);
    }

    /**
     * 通过id获取物流订单
     * @param param 包含参数（String）logisticsInfoId
     * @return
     */
    @Override
    public LogisticsInfo findLogisticsInfo(Map param) {
        param.put("logisticsId",Long.parseLong((String)param.get("logisticsId")));
        return logisticsInfoDao.findLogisticsInfo(param);
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
        LogisticsInfo logisticsInfo=logisticsInfoDao.findLogisticsInfo(logiticsInfoParam);
        vehicleGrab.setOrderId(logisticsInfo.getOrderId());
        vehicleGrab.setLogisticsId(logisticsInfo.getLogisticsId());
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
                    vehicleGrabInfo.setGrabId(vehicleGrab.getGrabId());
                    vehicleGrabInfo.setSjLxr((String) vehivleMap.get("sjmc"));
                    vehicleGrabInfo.setSjLxrPhone((String)vehivleMap.get("sjdh"));
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
}
