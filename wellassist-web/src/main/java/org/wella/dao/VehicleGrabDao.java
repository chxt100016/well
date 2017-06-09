package org.wella.dao;

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
}
