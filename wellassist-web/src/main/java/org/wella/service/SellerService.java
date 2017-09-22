package org.wella.service;

import io.wellassist.utils.Query;
import org.wella.entity.Order;
import org.wella.entity.Prod;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by ailing on 2017/5/10.
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

    Map<String,Object> getOrderDetail(Long orderId);

    Long createOrderLog(Long orderId, Map map);

    /**
     * 获取订单物流信息
     * @param orderId
     * @return 1.如果是买家自己物流：
     * 2.第三方物流：
     */
    Map<String,Object> getOrderLogisticsInfo(long orderId);

    Map<String,Object> getSendProdPageInfo(long orderId);

    /**
     * 卖家发货
     * @param params
     */
    void sendProd(Map params);

    /**
     * 买家结束发货：update wa_order表 order_state 为4
     * @param orderId
     */
    void sendProdStop(long orderId);

    Map<String,Object> getOrderDetailInfo(long orderId);



    List<BigDecimal> salesVolume(Map<String,Object> map);

    List<BigDecimal> profit(Map<String,Object> map);


    /**
     * 卖家收到申请发票列表
     * @param query 分页参数
     * @return 发票列表
     */
    List requestBillsList(Query query);

    /**
     * 卖家收到申请发票列表总记录数
     * @param query 分页参数
     * @return 总记录数
     */
    int requestBillsListCount(Query query);

    int sendBill(long billId, int kpType, String eBill, String kdNo, String kdName);
}
