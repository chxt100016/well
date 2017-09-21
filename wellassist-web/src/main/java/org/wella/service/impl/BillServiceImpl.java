package org.wella.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.*;
import org.wella.entity.*;
import org.wella.service.BillService;
import org.wella.service.WaOrderService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xuqinghuo on 2017/7/25.
 */
@Service("billServiceImpl")
public class BillServiceImpl implements BillService{

    @Autowired
    private BillDao billDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private WaOrderService waOrderServiceImpl;

    @Autowired
    private LogisticsInfoDao logisticsInfoDao;

    @Override
    public List<Map<String, Object>> billOrders(long billId) {
        Bill bill=billDao.query(billId);
        String orderIds=bill.getOrderIds();
        Map<String,Object> query=new HashMap<>();
        StringBuilder sb=new StringBuilder();
        sb.append("(").append(orderIds).append(")");
        query.put("orderIds",sb.toString());
        List<Map<String,Object>> orders=orderDao.listOrderinfoviewByConditions(query);
        for (Map<String,Object> order:orders){
            long orderId=(long)order.get("order_id");
            Date completeDate=waOrderServiceImpl.getOrderCompleteDate(orderId);
            order.put("complete_date",completeDate);
        }
        ConvertUtil.convertDataBaseMapToJavaMap(orders);
        return orders;
    }

    @Override
    public List<Map<String, Object>> billLogisticss(long billId) {
        Bill bill=billDao.query(billId);
        String logisticsInfoIds=bill.getLogisticsInfoIds();
        Map<String,Object> query=new HashMap<>();
        StringBuilder sb=new StringBuilder();
        sb.append("(").append(logisticsInfoIds).append(")");
        query.put("logisticsIds",sb.toString());
        List<Map<String,Object>> logisticss=logisticsInfoDao.listLogisticsOrderInfoViewByConditions(query);
        for (Map<String,Object> logistics:logisticss){
            long orderId=(long)logistics.get("order_id");
            Date completeDate=waOrderServiceImpl.getOrderCompleteDate(orderId);
            logistics.put("complete_date",completeDate);
        }
        ConvertUtil.convertDataBaseMapToJavaMap(logisticss);
        return logisticss;
    }

    @Override
    public Bill bill(long billId) {
        Bill bill=billDao.query(billId);
        return bill;
    }
}
