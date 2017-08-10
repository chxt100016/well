package org.wella.service.impl;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.*;
import org.wella.entity.*;
import org.wella.service.RegionService;
import org.wella.service.SellerService;
import org.wella.service.WaOrderService;
import org.wella.utils.DateUtils;

import java.math.BigDecimal;
import java.security.PrivateKey;
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

    @Autowired
    private VehicleInfoDao vehicleInfoDao;

    @Autowired
    private VehicleGrabInfoDao vehicleGrabInfoDao;

    @Autowired
    private RegionDao regionDao;

    @Autowired
    private ZorderDao zorderDao;

    @Autowired
    private OrderVehicleDao orderVehicleDao;

    @Autowired
    private WaOrderService waOrderServiceImpl;

    @Autowired
    private RegionService regionServiceImpl;


    /**
     * 买家确认时的业务逻辑
     *
     * @param orderId 订单id
     *                业务逻辑处理：
     *                1.先根据订单id从表wa_order中获取该条记录并判断该其配送方式
     */
    @Transactional
    public void processOrder(long orderId, Map map) {
        HashMap queryMap = new HashMap();
        queryMap.put("orderId", orderId);
        List<Order> orderList = orderDao.findOrder(queryMap);
        Order order = new Order();
        if (orderList == null || orderList.size() != 1) {
            return;
        }
        order = orderList.get(0);
        double confirmPrice = Double.parseDouble(map.get("confirmPrice").toString());
        double confirmNumber = Double.parseDouble(map.get("confirmNumber").toString());

        order.setConfirmNumber(BigDecimal.valueOf(confirmNumber));
        order.setConfirmPrice(BigDecimal.valueOf(confirmPrice));

        //更新order表中的确认价格，确认数量，单价修改时间，状态
        HashMap orderUpdateMap = new HashMap();
        orderUpdateMap.put("confirmPrice", confirmPrice);
        orderUpdateMap.put("confirmNumber", confirmNumber);
        orderUpdateMap.put("orderState", 1);
        orderUpdateMap.put("orderId", orderId);
        orderUpdateMap.put("djModifyDate", new Date());
        orderDao.updateOrderByID(orderUpdateMap);
        //判断配送方式，如果为配送则生成物流订单供物流抢单使用
        if (order.getIsSelfCar() == 1) {
            createLogisticsOrder(orderId, order);
        }
        map.put("orderPrice", map.get("confirmPrice"));
        map.put("orderNumber", map.get("confirmNumber"));
        createOrderLog(order.getOrderId(), map);
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
        map.put("prodState", 2);
        map.put("orderBy", " prod_id DESC");
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
     *
     * @param param
     * @return
     */
    @Override
    public List<Map<String, Object>> getOrderList(Map param) {
        List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> orderMaps = orderDao.listOrderAttachProd(param);
        long tmpCutomerId = 0;
        int index = -1;
        for (Map<String, Object> orderMap : orderMaps) {
            ConvertUtil.convertDataBaseMapToJavaMap(orderMap);
            long customerId = (long) orderMap.get("userId");
            if (customerId != tmpCutomerId) {
                index++;
                tmpCutomerId = customerId;
                res.add(new HashMap<String, Object>());
                if (res.get(index).get("orders") == null) {
                    res.get(index).put("orders", new ArrayList<Map<String, Object>>());
                }
                Map<String, Object> customer = userinfoDao.singleUserinfoByPrimaryKey(customerId);
                res.get(index).put("customerId", (long) customer.get("user_id"));
                res.get(index).put("customerName", (String) customer.get("company_name"));
                res.get(index).put("customerImg", (String) customer.get("company_img"));
                ((ArrayList<Map<String, Object>>) res.get(index).get("orders")).add(orderMap);
            } else {
                ((ArrayList<Map<String, Object>>) res.get(index).get("orders")).add(orderMap);
            }
        }
        return res;
    }

    /**
     * 获取卖家订单列表总数
     *
     * @param param
     * @return
     */
    @Override
    public int getOrderListCount(Map param) {
        return orderDao.listOrderCount(param);
    }

    @Override
    public Map<String, Object> getInfoForConfirmOrderPage(Long orderId) {
        Map<String, Object> res = orderDao.singleOrderAttachUserAttachOrderLogisticsInfo(orderId);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        return res;
    }

    @Override
    public Map<String, Object> getOrderDetail(Long orderId) {
        Map<String, Object> res = orderDao.singleOrderAttachOrderLogAttachProdAttachZorderAttachUserAttachOrderLogisticsInfo(orderId);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        return res;
    }

    /**
     * 根据订单产生物流订单的逻辑代码
     *
     * @param orderId
     * @param order
     */
    private void createLogisticsOrder(long orderId, Order order) {

        LogisticsInfo logisticsInfo = new LogisticsInfo();
        logisticsInfo.setOrderId((long) orderId);
        logisticsInfo.setFromAddress(regionServiceImpl.getDetailAddress(order.getFromRegionId(), order.getFromRegionAddr()));
        logisticsInfo.setToAddress(regionServiceImpl.getDetailAddress(order.getToRegionId(), order.getToRegionAddr()));
        //根据user_id获取customer的相关信息
        HashMap customerQueryMap = new HashMap();
        customerQueryMap.put("userId", order.getUserId());
        List<User> customerList = waUserDao.findUser(customerQueryMap);
        logisticsInfo.setCustomerUserId(order.getUserId());
        if (customerList != null && customerList.size() != 0) {
            User customer = customerList.get(0);
            logisticsInfo.setCustomerUserName(customer.getUserName());
        }
        //根据id获取seller的相关信息，主要时获取seller的name属性
        HashMap sellerQueryMap = new HashMap();
        sellerQueryMap.put("userId", order.getSupplierId());
        List<User> sellerList = waUserDao.findUser(sellerQueryMap);
        if (sellerList != null && sellerList.size() != 0) {
            User seller = sellerList.get(0);
            logisticsInfo.setSellerUserName(seller.getUserName());
        }
        logisticsInfo.setSellerUserId(order.getSupplierId());

        logisticsInfo.setProdId(order.getProdId());
        logisticsInfo.setProdName(order.getProdName());
        logisticsInfo.setNum(order.getConfirmNumber().doubleValue());
        logisticsInfo.setState((byte) 0);
        //double confirmNumber =Double.parseDouble(order.getConfirmNumber().toString());
        //BigDecimal prePayment =order.getConfirmNumber().multiply(order.getConfirmPrice());
        //logisticsInfo.setPrePayment(prePayment.doubleValue());

        logisticsInfo.setOrderDate(order.getOrderDate());
        //创建物流订单
        logisticsInfoDao.createLogisticsInfo(logisticsInfo);
    }

    /**
     * 生成对订单操作的日志文件
     *
     * @param
     * @param map 其中confirmPrice是指修改后的文件
     */
    @Override
    public void createOrderLog(Long orderId, Map map) {
        OrderLog orderLog = new OrderLog();
        orderLog.setOperationTime(new Date());
        orderLog.setOrderId(orderId);
        orderLog.setOrderPrice(Double.parseDouble(map.get("orderPrice").toString()));
        orderLog.setOrderNumber(Double.parseDouble(map.get("orderNumber").toString()));
        orderLog.setUserId(Long.parseLong(map.get("userId").toString()));
        orderLog.setOperationIp(map.get("operationIp").toString());
        orderLogDao.createOrderLog(orderLog);
    }

    @Override
    public Map<String, Object> getOrderLogisticsInfo(long orderId) {
        Map<String, Object> order = orderDao.singleOrderByPrimaryKey(orderId);
        ConvertUtil.convertDataBaseMapToJavaMap(order);
        if ((int) order.get("isSelfCar") == 0) {
            Map<String, Object> basicinfo = orderDao.singleOrderAttachUserAttachOrderLogisticsInfo(orderId);
            Map vehicleInfoQuery = new HashMap();
            vehicleInfoQuery.put("orderId", orderId);
            List<Map<String, Object>> vehiclesinfo = vehicleInfoDao.listVehicleInfoByConditions(vehicleInfoQuery);
            ConvertUtil.convertDataBaseMapToJavaMap(basicinfo);
            ConvertUtil.convertDataBaseMapToJavaMap(vehiclesinfo);
            basicinfo.put("vehicles", vehiclesinfo);
            basicinfo.put("fromAddress", regionDao.getRegionDetailNameByRegionId((long) basicinfo.get("fromRegionId")) + " " + basicinfo.get("fromRegionAddr"));
            basicinfo.put("toAddress", regionDao.getRegionDetailNameByRegionId((long) basicinfo.get("toRegionId")) + " " + basicinfo.get("toRegionAddr"));
            return basicinfo;
        } else if ((int) order.get("isSelfCar") == 1) {
            Map<String, Object> basicinfo = orderDao.singleOrderAttachLogisticsInfoAttachVehicleGrab(orderId);
            ConvertUtil.convertDataBaseMapToJavaMap(basicinfo);
            long vehicleGrabId = (long) basicinfo.get("vehicleGrabId");
            Map query = new HashMap();
            query.put("vehicleGrabId", vehicleGrabId);
            List<Map<String, Object>> vehiclesinfo = vehicleGrabInfoDao.listVehicleGrabInfoByConditions(query);
            basicinfo.put("vehicles", vehiclesinfo);

            basicinfo.put("fromAddress", regionDao.getRegionDetailNameByRegionId((long) basicinfo.get("fromRegionId")) + " " + basicinfo.get("fromRegionAddr"));
            basicinfo.put("toAddress", regionDao.getRegionDetailNameByRegionId((long) basicinfo.get("toRegionId")) + " " + basicinfo.get("toRegionAddr"));
            return basicinfo;
        }
        return null;
    }

    @Override
    public Map<String, Object> getSendProdPageInfo(long orderId) {
        Map<String, Object> res = orderDao.singleOrderAttachOrderLogAttachZorderCountAttachProdRestNum(orderId);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        Map<String, Object> orderLogisticsInfo = getOrderLogisticsInfo(orderId);
        res.put("toAddress", orderLogisticsInfo.get("toAddress"));
        return res;
    }

    @Override
    @Transactional
    public void sendProd(Map params) {
        long userId = (long) params.get("userId");
        String orderId = (String) params.get("orderId");
        String zorderPrice = (String) params.get("zorderPrice");
        String zorderNum = (String) params.get("zorderNum");
        String sendComment = (String) params.get("sendComment");
        String zorderBill = (String) params.get("zorderBill");
        String zorderDate = (String) params.get("zorderDate");
        Date dZorderDate = DateUtils.parse(zorderDate, DateUtils.DATE_TIME_PATTERN);
        List<Map<String, Object>> orderVehicles = ConvertUtil.converJSONtoArrayListMap((String) params.get("orderVehicles"));

        //减库存
        Map<String, Object> map = prodDao.selectSalenum(Long.parseLong(orderId));
        if (map.containsKey("saleNum")) {
            Integer number = (Integer) map.get("saleNum") + Integer.parseInt(zorderNum);
            map.put("saleNum", number);
        } else {
            map.put("saleNum", Integer.parseInt(zorderNum));
        }
        //prodDao.updateKucun(map);
        prodDao.updateProdByPrimaryKey(map);


        //如果是第一次发货，修改订单状态
        Map<String, Object> order = orderDao.singleOrderByPrimaryKey(Long.parseLong(orderId));
        if ((int) order.get("order_state") == 2) {
            //将wa_order表的order_state置为3
            Map updateOrderMap = new HashedMap();
            updateOrderMap.put("orderId", Long.parseLong(orderId));
            updateOrderMap.put("orderState", 3);
            orderDao.updateOrderByID(updateOrderMap);
            //如果是第三方物流，将wa_logitics_info表的state置为3
            if ((int) order.get("is_self_car") == 1) {
                Map updateLogisticsInfoMap = new HashMap();
                updateLogisticsInfoMap.put("orderId", Long.parseLong(orderId));
                updateLogisticsInfoMap.put("state", 4);
                logisticsInfoDao.updateByConditions(updateLogisticsInfoMap);
            }
        }
        //insert wa_zorder表新纪录
        Zorder zorder = new Zorder();
        zorder.setOrderId(Long.parseLong(orderId));
        zorder.setZorderPrice(new BigDecimal(zorderPrice));
        zorder.setZorderNum(new BigDecimal(zorderNum));
        zorder.setZorderMoney(zorder.getZorderNum().multiply(zorder.getZorderPrice()));
        zorder.setZorderDate(dZorderDate);
        zorder.setZorderState((byte) 1);
        zorder.setModifyDate(new Date());
        zorder.setUserId(userId);
        zorder.setSendComment(sendComment);
        zorder.setZorderBill(zorderBill);
        zorderDao.createZorder(zorder);
        //insert wa_order_vehicle表新记录
        for (Map<String, Object> ov : orderVehicles) {
            OrderVehicle orderVehicle = new OrderVehicle();
            orderVehicle.setOrderId(Long.parseLong(orderId));
            orderVehicle.setZorderId(zorder.getZorderId());
            orderVehicle.setVehicleNo((String) ov.get("vehicleNo"));
            orderVehicle.setVehicleHangingNo((String) ov.get("vehicleHangingNo"));
            orderVehicle.setVehicleActualSize(new BigDecimal(ov.get("vehicleActualSize").toString()));
            orderVehicle.setVehicleSize(new BigDecimal(ov.get("vehicleSize").toString()));
            orderVehicle.setDriverName((String) ov.get("driverName"));
            orderVehicle.setDriverPhone((String) ov.get("driverPhone"));
            orderVehicle.setDeliverActualDate(dZorderDate);
            orderVehicleDao.createOrderVehicle(orderVehicle);
        }

    }

    @Override
    public void sendProdStop(long orderId) {
        Map<String, Object> updateOrderMap = new HashedMap();
        updateOrderMap.put("orderId", orderId);
        updateOrderMap.put("orderState", 4);
        orderDao.updateOrderByID(updateOrderMap);
    }

    @Override
    public Map<String, Object> getOrderDetailInfo(long orderId) {
        //得到订单基本信息
        Map<String, Object> res = orderDao.singleOrderAttachProdAttachOrderLogisticsInfo(orderId);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        int orderState = (int) res.get("orderState");
        if (orderState == 0) {
            return res;
        }
        //从wa_order_log表中得到订单价格和数量
        Map<String, Object> orderlog = waOrderServiceImpl.findNewestOrderLog(orderId);
        res.putAll(orderlog);
        if (orderState == 1 || orderState == 2) {
            return res;
        }
        //从wa_zorder表中得到发货量和成交额
        Map<String, Object> zorderDeliverCount = waOrderServiceImpl.findDeliverProdCount(orderId);
        res.putAll(zorderDeliverCount);

        List<Map<String, Object>> zorders = waOrderServiceImpl.findZorders(orderId);
        res.put("zorders", zorders);
        return res;
    }


}
