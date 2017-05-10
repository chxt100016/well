package org.wella.service.impl;

import org.wella.entity.Order;
import org.wella.entity.Prod;
import org.wella.service.CustomerService;

import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/10.
 */
public class CustomerServiceImpl implements CustomerService {
    @Override
    public void order(Map map) {

    }

    @Override
    public void orderPay(int orderId) {

    }

    @Override
    public void cancelPay(int orderId) {

    }

    @Override
    public void processDeliveryOrder(int deliveryOrderId) {

    }

    @Override
    public void deliveryOrderPay(int deliveryOrderId) {

    }

    @Override
    public void confirmDelivery(int deliveryOrderId) {

    }

    @Override
    public void evaluate(int orderId) {

    }

    @Override
    public void loanApply(int customerId) {

    }

    @Override
    public void loanConfirm() {

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
    public void createOrder() {

    }

    @Override
    public void updateOrder(Order order) {

    }

    @Override
    public void deleteOrder(int orderId) {

    }

    @Override
    public List<Prod> findProdList() {
        return null;
    }

    @Override
    public List<Prod> findProdList(Map map) {
        return null;
    }
}
