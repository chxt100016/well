package org.wella.service.impl;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.*;
import org.wella.entity.*;
import org.wella.service.SellerService;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by liuwen on 2017/5/10.
 */
@Service("sellerServiceImpl")
public class SellerServiceImpl implements SellerService {
    @Autowired
    private LogisticsInfoDao logisticsInfoDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderLogDao orderLogDao;

    @Autowired
    private OrderInfoDao orderInfoDao;

    @Autowired
    private WaUserDao waUserDao;

    @Autowired
    private OrderLogisticsInfoDao orderLogisticsInfoDao;

    @Autowired
    private ProdDao prodDao;

    @Autowired
    private UserinfoDao userinfoDao;

    /**
     * 买家确认时的业务逻辑
     * @param orderId 订单id
     * 业务逻辑处理：
     * 1.先根据订单id从表wa_order中获取该条记录并判断该其配送方式
     */
    @Transactional
    public void processOrder(long orderId,Map map) {
        HashMap queryMap = new HashMap();
        queryMap.put("orderId",orderId);
        List<Order> orderList = orderDao.findOrder(queryMap);
        Order order = new Order();
        if(orderList==null||orderList.size()!=1){
            return;
        }
        order = orderList.get(0);
        double confirmPrice = Double.parseDouble(map.get("confirmPrice").toString());
        double confirmNumber =Double.parseDouble(map.get("confirmNumber").toString());

        order.setConfirmNumber(BigDecimal.valueOf(confirmNumber));
        order.setConfirmPrice(BigDecimal.valueOf(confirmPrice));

        //更新order表中的确认价格，确认数量，单价修改时间，状态
        HashMap orderUpdateMap = new HashMap();
        orderUpdateMap.put("confirmPrice",confirmPrice);
        orderUpdateMap.put("confirmNumber",confirmNumber);
        orderUpdateMap.put("orderState",1);
        orderUpdateMap.put("orderId",orderId);
        orderUpdateMap.put("djModifyDate",new Date());
        orderDao.updateOrderByID(orderUpdateMap);
        //判断配送方式，如果为配送则生成物流订单供物流抢单使用
        if(order.getIsSelfCar() ==1){
            createLogisticsOrder(orderId,order);
        }
        createOrderLog(order,map);
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
        map.put("prodState",2);
        map.put("orderBy"," prod_id DESC");
        return prodDao.findProdByUserId(map);
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

    /**
     * 获取卖家订单列表
     * @param param
     * @return
     */
    @Override
    public List<Map<String, Object>> getOrderList(Map param) {
        List<Map<String,Object>> res=new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> orderMaps=orderDao.listOrderAttachProd(param);
        long tmpCutomerId=0;
        int index=-1;
        for (Map<String,Object> orderMap : orderMaps){
            ConvertUtil.convertDataBaseMapToJavaMap(orderMap);
            long customerId=(long)orderMap.get("userId");
            if (customerId!=tmpCutomerId){
                index++;
                tmpCutomerId=customerId;
                res.add(new HashMap<String,Object>());
                if (res.get(index).get("orders")==null){
                    res.get(index).put("orders",new ArrayList<Map<String,Object>>());
                }
                Map<String,Object> customer=userinfoDao.singleUserinfoByPrimaryKey(customerId);
                res.get(index).put("customerId",(long)customer.get("user_id"));
                res.get(index).put("customerName",(String)customer.get("company_name"));
                res.get(index).put("customerImg",(String)customer.get("company_img"));
                ((ArrayList<Map<String,Object>>)res.get(index).get("orders")).add(orderMap);
            } else {
                ((ArrayList<Map<String,Object>>)res.get(index).get("orders")).add(orderMap);
            }
        }
        return res;
    }

    /**
     * 获取卖家订单列表总数
     * @param param
     * @return
     */
    @Override
    public int getOrderListCount(Map param) {
        return orderDao.listOrderCount(param);
    }

    @Override
    public Map<String, Object> getInfoForConfirmOrderPage(Long orderId) {
        Map<String, Object> res=orderDao.singleOrderAttachUserAttachOrderLogisticsInfo(orderId);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        return res;
    }

    /**
     * 根据订单产生物流订单的逻辑代码
     * @param orderId
     * @param order
     */
    private void  createLogisticsOrder(long orderId,Order order){

        LogisticsInfo logisticsInfo = new LogisticsInfo();
        logisticsInfo.setOrderId((long)orderId);
        logisticsInfo.setFromAddress(order.getFromRegionAddr());
        logisticsInfo.setToAddress(order.getToRegionAddr());
        //根据user_id获取customer的相关信息
        HashMap customerQueryMap = new HashMap();
        customerQueryMap.put("userId",order.getUserId());
        List<User> customerList = waUserDao.findUser(customerQueryMap);
        logisticsInfo.setCustomerUserId(order.getUserId());
        if(customerList!=null&& customerList.size()!=0){
            User customer = customerList.get(0);
            logisticsInfo.setCustomerUserName(customer.getUserName());
        }
        //根据id获取seller的相关信息，主要时获取seller的name属性
        HashMap sellerQueryMap = new HashMap();
        sellerQueryMap.put("userId",order.getSupplierId());
        List<User> sellerList = waUserDao.findUser(sellerQueryMap);
        if(sellerList!=null&& sellerList.size()!=0){
            User seller = sellerList.get(0);
            logisticsInfo.setSellerUserName(seller.getUserName());
        }
        logisticsInfo.setSellerUserId(order.getSupplierId());

        logisticsInfo.setProdId(order.getProdId());
        logisticsInfo.setProdName(order.getProdName());
        logisticsInfo.setNum(order.getConfirmNumber().doubleValue());
        logisticsInfo.setState((byte) 0);
        double confirmNumber =Double.parseDouble(order.getConfirmNumber().toString());
        BigDecimal prePayment =order.getConfirmNumber().multiply(order.getConfirmPrice());
        logisticsInfo.setPrePayment(prePayment.doubleValue());

        logisticsInfo.setOrderDate(order.getOrderDate());
        //查询物流信息表
        logisticsInfo.setFromAddress(order.getFromRegionAddr());
        //创建物流订单
        logisticsInfoDao.createLogisticsInfo(logisticsInfo);
    }

    /**
     * 生成对订单操作的日志文件
     * @param order
     * @param map
     * 其中confirmPrice是指修改后的文件
     * 其中confirmNumber是指修改后的数量
     */
    private void createOrderLog(Order order,Map map){
        OrderLog orderLog = new OrderLog();
        orderLog.setOperationTime(new Date());
        orderLog.setOrderId(order.getOrderId());
        orderLog.setOrderPrice(Double.parseDouble(map.get("confirmPrice").toString()));
        orderLog.setOrderNumber(Double.parseDouble(map.get("confirmNumber").toString()));
        orderLog.setUserId(Long.parseLong(map.get("userId").toString()));
        orderLog.setOperationIp(map.get("operationIp").toString());
        orderLogDao.createOrderLog(orderLog);
    }

}
