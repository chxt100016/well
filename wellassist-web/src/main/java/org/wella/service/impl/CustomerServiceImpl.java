package org.wella.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.*;
import org.wella.entity.*;
import org.wella.service.CustomerService;
import org.wella.service.WaOrderService;
import org.wella.utils.CommonUtil;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by liuwen on 2017/5/10.
 */
@Service("customerServiceImpl")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserinfoDao userinfoDao;
    @Autowired
    private ProdDao prodDao;
    @Autowired
    private OrderInfoDao orderInfoDao;
    @Autowired
    private RegionDao regionDao;
    @Autowired
    private VehicleInfoDao vehicleInfoDao;
    @Autowired
    private OrderLogisticsInfoDao orderLogisticsInfoDao;
    @Autowired
    private ProdUserDao prodUserDao;
    @Autowired
    private WaOrderService waOrderServiceImpl;
    @Autowired
    private ZorderDao zorderDao;


    /**
     * 需要进行事务控制
     * @param map 表单中提交的内容(String):prodId，saleNum，danjia，saleMoney，isSelfCar，contacts，conTel，toRegionId
     *                toRegionAddr，orderData，deliverDate，receiveDate，customerExceptCarriage
     */
    @Override
    @Transactional
    public void order(Map map) {
        //获取与用户和产品相关联的信息写入订单表中
        long userId=(long)map.get("userId");
        Userinfo userinfo=userinfoDao.getOrderUserinfoByUserid(userId);
        long prodId=Long.parseLong((String)map.get("prodId"));
        Map<String,Object> prod=prodDao.singleProdByPrimaryKey(prodId);
        ConvertUtil.convertDataBaseMapToJavaMap(prod);

        Order order=new Order();
        order.setOrderNo(CommonUtil.genKey(4));
        order.setProdId(prodId);
        order.setProdName((String) prod.get("prodName"));
        order.setFromRegionId((Long) prod.get("prodRegionId"));
        order.setFromRegionAddr((String) prod.get("prodRegionAddr"));
        order.setSupplierId((Long) prod.get("userId"));

        order.setToRegionId(Long.parseLong((String) map.get("toRegionId")));
        order.setToRegionAddr((String) map.get("toRegionAddr"));

        order.setUserId(userId);
        order.setCompanyLxr(userinfo.getCompanyLxr());
        order.setCompanyLxrPhone(userinfo.getCompanyLxrPhone());

        order.setSaleNum(new BigDecimal((String)map.get("saleNum")));
        order.setSaleMoney(new BigDecimal((String)map.get("saleMoney")));
        order.setOrderDate(new Date());
        order.setDjModifyDate(new Date());
        order.setOrderIp((String)map.get("orderIp"));
        order.setIsSelfCar(Byte.parseByte((String)map.get("isSelfCar")));

        order.setOrderType((byte)0);
        order.setOrderState((byte)0);

        orderDao.createOrder(order);
        //生成一条wa_order_info记录
        OrderInfo orderInfo=new OrderInfo();
        orderInfo.setOrderId(order.getOrderId());
        orderInfo.setInfoType(order.getOrderType());
        orderInfo.setMgrIp(order.getOrderIp());
        orderInfo.setMgrDate(order.getOrderDate());
        orderInfoDao.createOrderInfo(orderInfo);

        OrderLogisticsInfo orderLogisticsInfo=new OrderLogisticsInfo();
        Date ccDate=str2Date((String)map.get("deliverDate"),"yyyy-MM-dd HH:mm:ss");
        Date ddDate=str2Date((String)map.get("receiveDate"),"yyyy-MM-dd HH:mm:ss");

        orderLogisticsInfo.setOrderId(order.getOrderId());
        orderLogisticsInfo.setDeliverDate(ccDate);
        orderLogisticsInfo.setReceiveDate(ddDate);
        orderLogisticsInfo.setContacts((String)map.get("contacts"));
        orderLogisticsInfo.setConTel((String)map.get("conTel"));
        HashMap paramMap=new HashMap();
        paramMap.put("regionId",(String)map.get("toRegionId"));
        String addr=regionDao.getRegionDetailName(paramMap)+" "+(String)map.get("toRegionAddr");
        orderLogisticsInfo.setAddress(addr);
        orderLogisticsInfoDao.createOrderLogisticsInfoDao(orderLogisticsInfo);

        if (order.getIsSelfCar()==0){
            //自提则将司机信息写入wa_vehicle_info表中，将出发时间和到达时间写入wa_order_logistics_info表中
            ArrayList<Map<String,Object>> vehicleList = ConvertUtil.converJSONtoArrayListMap((String)map.get("orderData"));
            for (Map vehivleMap:vehicleList) {
                VehicleInfo vInfo=new VehicleInfo();
                vInfo.setOrderId(order.getOrderId());
                vInfo.setName((String) vehivleMap.get("driverName"));
                vInfo.setPhone((String)vehivleMap.get("driverPhone"));
                vInfo.setVehicleNo((String)vehivleMap.get("carCode"));
                vehicleInfoDao.createVehicleInfo(vInfo);
            }


        }
    }


    /**
     * 字符串转Date
     * @param dateStr
     * @param format
     * @return
     */
    public Date str2Date(String dateStr,String format){
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        Date date=null;
        try {
            date=sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
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
    public List<Prod> findProdList(Map map) {
        return prodUserDao.getUserProdList(map);
    }



    /**
     * 获取产品信息，并将其中的prod_region_id(编码)转换成fromRegionName（字符串）
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> findProdById(Map param) {
        Map<String,Object> prodMap=prodDao.findProdById(param);
        ConvertUtil.convertDataBaseMapToJavaMap(prodMap);
        HashMap<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("regionId",(long)prodMap.get("prodRegionId"));
        String fromRegionName=regionDao.getRegionDetailName(paramMap);
        prodMap.put("fromRegionName",fromRegionName);
        return prodMap;
    }

    @Override
    public List<Map<String, Object>> getRegionList(Map param) {
        List resList=regionDao.getRegionList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(resList);
        return resList;
    }

    @Override
    public String findZcAddress(Userinfo userinfo) {
        HashMap<String,Object> paramMap=new HashMap<>();
        paramMap.put("regionId",userinfo.getZcRegionId());
        return regionDao.getRegionDetailName(paramMap)+" "+userinfo.getZcXxAddress();
    }

    @Override
    public Map<String, Object> getOrderDetailInfo(long orderId) {
        //得到订单基本信息
        Map<String,Object> res=orderDao.singleOrderAttachProdAttachOrderLogisticsInfo(orderId);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        int orderState=(int)res.get("orderState");
        if(orderState==0){
            return res;
        }
        //从wa_order_log表中得到订单价格和数量
        Map<String,Object> orderlog=waOrderServiceImpl.findNewestOrderLog(orderId);
        res.putAll(orderlog);
        if(orderState==1||orderState==2){
            return res;
        }
        //从wa_zorder表中得到收货量和成交额
        Map<String,Object> zorderDeliverCount=waOrderServiceImpl.findReceiveProdCount(orderId);
        res.putAll(zorderDeliverCount);
        List<Map<String,Object>> zorders=waOrderServiceImpl.findZorders(orderId);
        res.put("zorders",zorders);
        return res;
    }

    @Override
    public int zorderConfirmReceive(long zorderId) {
        Zorder zorder=new Zorder();
        zorder.setZorderId(zorderId);
        zorder.setZorderState((byte)2);
        return zorderDao.updateByPrimaryKey(zorder);
    }
}
