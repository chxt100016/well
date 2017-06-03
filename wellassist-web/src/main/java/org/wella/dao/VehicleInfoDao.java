package org.wella.dao;

import org.wella.entity.VehicleInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/11.
 */
public interface VehicleInfoDao {
    void createVehicleInfo(VehicleInfo vehicleInfo);

    List<Map<String,Object>> listVehicleInfoByConditions(Map vehicleInfoQuery);
}
