package org.wella.dao;

import org.wella.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by 丁建文 on 2017/5/10.
 */
public interface OrderDao {

    void createOrder(Order order);

    /**
     * 根据订单的id，对订单进行更新操作，orderId被封装在Map对象中
     * @param map
     */
    void updateOrderByID(Map map);

    List<Order> findOrder(Map map);


}
