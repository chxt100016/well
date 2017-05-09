package org.wella.front.customer.service;

/**
 * Created by liuwen on 2017/5/9.
 */
public interface OrderService {
    /**
     * 买家下订单进行的相关逻辑操作
     * 物流选择自提时的业务逻辑处理
     * 1.在wa_order表中生成一条记录
     * 2.
     */
    void order();
}
