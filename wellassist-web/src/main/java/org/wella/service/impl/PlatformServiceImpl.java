package org.wella.service.impl;

import org.wella.entity.Order;
import org.wella.entity.Prod;
import org.wella.service.PlatformService;

import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/10.
 */
public class PlatformServiceImpl implements PlatformService {
    @Override
    public List<Order> findOrderList() {
        return null;
    }

    @Override
    public List<Order> findOrderList(Map map) {
        return null;
    }

    @Override
    public List<Prod> findProdList() {
        return null;
    }

    @Override
    public List<Prod> findProdList(Map map) {
        return null;
    }

    @Override
    public void updateOrder(Order order) {

    }

    @Override
    public void updateProduct(Prod prod) {

    }

    @Override
    public void deleteOrder(Order order) {

    }

    @Override
    public void deleteProduct(Prod prod) {

    }

    @Override
    public void confirmDeal(int orderId) {

    }

    @Override
    public void preprocessLoanApply(int orderId) {

    }

    @Override
    public void processloanApply(int orderId) {

    }
}
