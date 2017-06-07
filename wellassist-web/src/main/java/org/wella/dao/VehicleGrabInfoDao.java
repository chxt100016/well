package org.wella.dao;

import org.wella.entity.VehicleGrabInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/12.
 */
public interface VehicleGrabInfoDao {

    void createVehicleGrabInfo(VehicleGrabInfo vehicleGrabInfo);

    List<Map<String,Object>> listVehicleGrabInfoByConditions(Map query);
}
