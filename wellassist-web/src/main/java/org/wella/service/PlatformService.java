package org.wella.service;

import org.wella.entity.Order;
import org.wella.entity.Prod;

import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/10.
 */
public interface PlatformService {

    List<Order> findOrderList();

    List<Order> findOrderList(Map map);

    List<Prod> findProdList();

    List<Prod> findProdList(Map map);

    void updateOrder(Order order);

    void updateProduct(Prod prod);

    void deleteOrder(Order order);

    void deleteProduct(Prod prod);

    void confirmDeal(int orderId);

    void preprocessLoanApply(int orderId);

    void processloanApply(int orderId);


    boolean insertCustomer(Map map);



}
