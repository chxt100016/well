package org.wella.service;

import org.wella.entity.Order;
import org.wella.entity.Prod;

import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/10.
 */
public interface SellerService {

    /**
     * 处理订单
     * @param orderId 订单id
     * 业务逻辑处理：
     * 1.确认订单后，修改订单表wa_order表中的信息，并在wa_order_info中生成一条记录，对当前的订单状态进行标记
     * 2.如果订单通过卖家确认，若干自提，则向运输表
     */
    void processOrder(long orderId,Map map);


    /**
     *
     * @param orderId
     */
    void deliverGoods(int orderId);

    /**
     *
     */
    void createProduct();

     /**
     *
     * @param productId
     */
    void deleteProduct(int productId);

    /**
     *
     * @param prod
     */
    void updateProduct(Prod prod);

    /**
     *
     * @return
     */
    List<Prod> findProductList();

    /**
     *
     * @param map
     * @return
     */
    List<Prod> findProductList(Map map);

    /**
     *
     * @return
     */
    List<Order> findOrderList();

    /**
     *
     * @param map
     * @return
     */
    List<Order> findOrderList(Map map);

    /**
     *
     * @param orderId
     */
    void evaluate(int orderId);

    List<Map<String,Object>> getOrderList(Map param);

    int getOrderListCount(Map param);

    Map<String,Object> getInfoForConfirmOrderPage(Long orderId);
}
