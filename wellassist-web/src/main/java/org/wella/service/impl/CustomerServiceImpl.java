package org.wella.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wella.dao.OrderDao;
import org.wella.dao.OrderInfoDao;
import org.wella.dao.ProdDao;
import org.wella.dao.UserinfoDao;
import org.wella.entity.Order;
import org.wella.entity.OrderInfo;
import org.wella.entity.Prod;
import org.wella.entity.Userinfo;
import org.wella.service.CustomerService;
import org.wella.utils.CommonUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/10.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserinfoDao userinfoDao;
    @Autowired
    private ProdDao prodDao;
    @Autowired
    private OrderInfoDao orderInfoDao;

    /**
     * 需要进行事务控制
     * @param map 表单中提交的内容:userId,prodId,toRegionId,toRegionAddr,saleNum,saleMoney,orderIp,isSelfCar
     */
    @Override
    public void order(Map map) {
        long userId=(long)map.get("userId");
        Userinfo userinfo=userinfoDao.getOrderUserinfoByUserid(userId);
        long prodId=(long)map.get("prodId");
        Prod prod=prodDao.getOrderProdByProdid(prodId);

        Order order=new Order();
        order.setOrderNo(CommonUtil.genKey(4));
        order.setProdId(prodId);
        order.setProdName(prod.getProdName());
        order.setFromRegionId(prod.getProdRegionId());
        order.setFromRegionAddr(prod.getProdRegionAddr());
        order.setSupplierId(prod.getUserId());

        order.setToRegionId((String) map.get("toRegionId"));
        order.setToRegionAddr((String) map.get("toRegionAddr"));

        order.setUserId(userId);
        order.setUserLxr(userinfo.getCompanyLxr());
        order.setUserLxrPhone(userinfo.getCompanyLxrPhone());

        order.setSaleNum((BigDecimal)map.get("saleNum"));
        order.setSaleMoney((BigDecimal)map.get("saleMoney"));
        order.setOrderDate(new Date());
        order.setOrderIp((String)map.get("orderIp"));
        order.setIsSelfCar((byte)map.get("isSelfCar"));

        order.setOrderType((byte)0);

        orderDao.createOrder(order);

        OrderInfo orderInfo=new OrderInfo();
        orderInfo.setOrderId(order.getOrderId());
        orderInfo.setInfoType(order.getOrderType());
        orderInfo.setMgrIp(order.getOrderIp());
        orderInfo.setMgrDate(order.getOrderDate());
        orderInfoDao.createOrderInfo(orderInfo);
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
