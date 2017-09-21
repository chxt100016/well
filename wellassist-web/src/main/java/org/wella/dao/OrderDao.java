package org.wella.dao;

import org.apache.ibatis.annotations.Param;
import org.wella.entity.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    Map<String,Object> singleOrderinfoByPrimaryKey(@Param("orderId") long orderId);

    List<Map<Object,Object>>  financeDate(Map map);

    String selectOrderNoByOrderId(@Param("orderId") long orderId);


    List<Map<Integer,Object>> tradingVolume(Map map);

    List<Map<Integer,Object>>  salesVolume(Map<String,Object> map);

    List<Map<Integer,Object>> profit(Map<String,Object> map);

    ArrayList<Map<String, Object>> getWaOrderList(Map<String, Object> paramMap);

    int getWaOrderListCount(Map<String, Object> paramMap);

    /**
     * 查询orderinfoview 视图
     * @param params 查询参数
     * @return list<map>
     */
    List<Map<String,Object>> listOrderinfoviewByConditions(Map params);

    /**
     * 通过orderId 拼接 orderNo字符串
     * @param inOrderIds example:(orderId1,orderId2,...)
     * @return 拼接 orderNo字符串
     */
    String concatOrderNos(@Param("inOrderIds") String inOrderIds);
}
