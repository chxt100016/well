package org.wella.service;

import org.wella.entity.Bill;

import java.util.List;
import java.util.Map;

/**
 * Created by ailing on 2017/7/25.
 */

public interface BillService {

    /**
     * 开具发票的商品订单信息
     * @param billId wa_bill主键
     * @return 开具发票的商品订单信息
     */
    List<Map<String,Object>> billOrders(long billId);

    /**
     * 开具发票物流订单信息
     * @param billId wa_bill主键
     * @return 开具发票物流订单信息
     */
    List<Map<String,Object>> billLogisticss(long billId);

    Bill bill(long billId);
}
