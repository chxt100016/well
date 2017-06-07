package org.wella.dao;

import org.wella.entity.OrderVehicle;
import org.wella.entity.Zorder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/2.
 */
public interface OrderVehicleDao {

    void createOrderVehicle(OrderVehicle orderVehicle);

    List<Map<String,Object>> listOrderVehiclesByConditions(HashMap queryOv);
}
