package org.wella.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/5.
 * 用于一些订单细节的常用逻辑
 */
public interface WaOrderService {
    /**
     * 得到订单实时价格和数量
     * @param orderId
     * @return
     */
    Map<String,Object> findNewestOrderLog(long orderId);

    /**
     * 得到订单卖家已发货的数量和对应成交额
     */
    Map<String,Object> findDeliverProdCount(long orderId);

    /**
     * 得到订单买家已收货的数量和对应成交额
     * @param orderId
     * @return
     */
    Map<String,Object> findReceiveProdCount(long orderId);

    /**
     * 得到分批发货的详细信息
     * @param orderId wa_order表主键
     * @return
     */
    List<Map<String,Object>> findZorders(long orderId);

    /**
     * 得到订单物流详情
     * @param orderId wa_order表主键
     * @return 自提或第三方物流信息和车辆信息
     */
    Map<String,Object> findOrderLogisticsInfo(long orderId);

    /**
     * 得到订单的提货车辆信息
     */
    List<Map<String,Object>> findOrderVehicles(long orderId);

    /**
     * 确认子订单是否全部确认收货
     * @param orderId
     */
    boolean checkZordersConfirmed(long orderId);

    /**
     * 该订单是否存疑
     * @param orderId
     * @return
     */
    boolean idZordersQuestion(long orderId);

    /**
     * 检查商品订单和物流订单是否都已预付款付款完成，是则将order_state 置为2
     * @param orderId wa_order表主键
     * @return true：已付款完成，false：未付款完成
     */
    boolean checkOrderRepayOff(long orderId);

    Map<String,Object> orderinfo(long orderId);

    boolean checkOrder2ndpayOff(long orderId);

}
