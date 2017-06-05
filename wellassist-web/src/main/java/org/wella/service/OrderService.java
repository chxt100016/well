package org.wella.service;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/5.
 * 用于一些订单细节的常用逻辑
 */
public interface OrderService {
    /**
     * 得到订单实时价格和数量
     * @param orderId
     * @return
     */
    Map<String,Object> findNewestOrderLog(Long orderId);

    /**
     * 得到订单卖家已发货的数量和对应成交额
     */
    Map<String,Object> findDeliverProdCount(Long orderId);

    /**
     * 得到订单买家已收货的数量和对应成交额
     * @param orderId
     * @return
     */
    Map<String,Object> findReceiveProdCount(long orderId);

}
