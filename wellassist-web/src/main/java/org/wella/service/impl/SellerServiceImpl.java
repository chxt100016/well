package org.wella.service.impl;

import org.wella.entity.Order;
import org.wella.entity.Prod;
import org.wella.service.SellerService;

import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/10.
 */
public class SellerServiceImpl implements SellerService {
    @Override
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
