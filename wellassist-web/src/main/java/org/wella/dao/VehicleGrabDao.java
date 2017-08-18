package org.wella.dao;

import org.apache.ibatis.annotations.Param;
import org.wella.entity.VehicleGrab;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/12.
 */
public interface VehicleGrabDao {

    int createVehicleGrab(VehicleGrab vehicleGrab);

    List<Map<String,Object>> getGrabLogisticsList(Map param);

    int grabLogisticsListCount(Map param);

    List<Map<String,Object>> listVehicleGrabAttachUserinfoByConditions(Map query);

    List<Map<String,Object>> listVehicleGrabByConditions(Map queryVG);

    int updateByPrimaryKey(Map<String, Object> vehicleGrab);

    Map<String,Object> singleVehicleGrabByPrimaryKey(@Param("vehicleGrabId") long vehicleGrabId);

    Map<String,Object> selectVgdId(Map map);

    Map<String,Object> selectLogisticsDateByLogisticsInfoId(@Param("logisticsId") Long logisticsId);
}
