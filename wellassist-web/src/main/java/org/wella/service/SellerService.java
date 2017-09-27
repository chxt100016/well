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

    /**
     * 卖家确认订单页面vo
     * @param orderId orderId
     * @return 卖家确认订单页面vo
     */
    Map<String,Object> getInfoForConfirmOrderPage(Long orderId);

    /**
     * 订单详细信息
     * @param orderId orderId
     * @return 订单详细信息
     */
    Map<String,Object> getOrderDetail(Long orderId);

    Long createOrderLog(Long orderId, Map map);

    /**
     * 获取订单物流信息
     * @param orderId
     * @return 1.如果是买家自己物流：从wa_order_logistics_info表中读取车辆信息
     * 2.第三方物流：从wa_vehicle_grab_info表中读取车辆信息
     */
    Map<String,Object> getOrderLogisticsInfo(long orderId);

    /**
     *
     * @param orderId
     * @return
     */
    Map<String,Object> getSendProdPageInfo(long orderId);

    /**
     * 处理卖家发货
     * @param params 发货表单提交参数
     */
    void sendProd(Map params);

    /**
     * 买家结束发货：update wa_order表 order_state 为4
     * @param orderId orderId
     */
    void sendProdStop(long orderId);

    /**
     * 卖家订单详情页面信息
     * @param orderId
     * @return 订单详情map
     */
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

    /**
     * 卖家发送发票
     * @param billId 发票id
     * @param kpType 开票类型
     * @param eBill 电子发票url
     * @param kdNo 快递单号
     * @param kdName 快递名
     * @return 数据库更新行数
     */
    int sendBill(long billId,String billNo, int kpType, String eBill, String kdNo, String kdName);

    /**
     * 卖家已发送发票列表
     * @param query
     * @return
     */
    List handledBillsList(Query query);

    /**
     * 卖家已发送发票列表总记录数
     * @param query 分页参数
     * @return 总记录数
     */
    int handledBillsListCount(Query query);
}
