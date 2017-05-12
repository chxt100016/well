package org.wella.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.wella.dao.LogisticsInfoDao;
import org.wella.dao.OrderDao;
import org.wella.dao.OrderLogDao;
import org.wella.entity.Order;
import org.wella.entity.OrderInfo;
import org.wella.entity.Prod;
import org.wella.service.SellerService;

import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/10.
 */
public class SellerServiceImpl implements SellerService {
    @Autowired
    private LogisticsInfoDao logisticsInfoDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderLogDao orderLogDao;

    @Autowired
    private OrderInfo orderInfo;

    /**
     * 买家确认时的业务逻辑
     * @param orderId 订单id
     * 业务逻辑处理：
     * 1.先根据订单id从表wa_order中获取该条记录并判断该其配送方式
     */
    public void processOrder(int orderId) {


    }

    @Override
    public void deliverGoods(int orderId) {

    }

    @Override
    public void createProduct() {

    }

    @Override
    public void deleteProduct(int productId) {

    }

    @Override
    public void updateProduct(Prod prod) {

    }

    @Override
    public List<Prod> findProductList() {
        return null;
    }

    @Override
    public List<Prod> findProductList(Map map) {
        return null;
    }

    @Override
    public List<Order> findOrderList() {
        return null;
    }

    @Override
    public List<Order> findOrderList(Map map) {
        return null;
    }

    @Override
    public void evaluate(int orderId) {

    }
}
