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
import org.wella.service.RegionService;
import org.wella.service.WaOrderService;

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

}