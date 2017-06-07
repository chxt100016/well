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
     * @param orderId
     * @return
     */
    List<Map<String,Object>> findZorders(long orderId);
    /**
     * 得到订单物流详情
     * @param orderId
     * @return
     */
    Map<String,Object> findOrderLogisticsInfo(long orderId);
}