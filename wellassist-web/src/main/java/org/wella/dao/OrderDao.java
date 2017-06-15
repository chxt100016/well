package org.wella.dao;

import org.apache.ibatis.annotations.Param;
import org.wella.entity.Order;

import java.util.ArrayList;
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
    int updateOrderByID(Map map);

    List<Order> findOrder(Map map);

    ArrayList findCustomerOrderList(Map map);

    int findCustomerOrderListCount(Map map);

    List<Map<String,Object>> listOrderAttachProd(Map param);

    int listOrderCount(Map param);

    Map<String,Object> singleOrderAttachUserAttachOrderLogisticsInfo(@Param("orderId") Long orderId);

    Map<String,Object> singleOrderAttachOrderLogAttachProdAttachZorderAttachUserAttachOrderLogisticsInfo(@Param("orderId")Long orderId);

    Map<String,Object> singleOrderByPrimaryKey(@Param("orderId")long orderId);

    Map<String,Object> singleOrderAttachLogisticsInfoAttachVehicleGrab(@Param("orderId")long orderId);

    Map<String,Object> singleOrderAttachOrderLogAttachZorderCountAttachProdRestNum(long orderId);

    Map<String,Object> singleOrderAttachProdAttachOrderLogisticsInfo(@Param("orderId") long orderId);

    Map<String,Object> findOrderLogisticsBasicInfo(@Param("orderId")long orderId);

}
