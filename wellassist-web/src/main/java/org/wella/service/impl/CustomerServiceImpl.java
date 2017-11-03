package org.wella.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.javafx.collections.MappingChange;
import com.wellapay.cncb.model.ForceTransferBasicInfo;
import io.wellassist.utils.Query;
import io.wellassist.utils.R;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.*;
import org.wella.entity.*;
import org.wella.service.*;
import org.wella.utils.CommonUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

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
    @Autowired
    private LogisticsInfoDao logisticsInfoDao;
    @Autowired
    private VehicleGrabDao vehicleGrabDao;
    @Autowired
    private WaUserDao waUserDao;
    @Autowired
    private OrderHistoryTailDao orderHistoryTailDao;
    @Autowired
    private CreditDao creditDao;
    @Autowired
    private LoanDao loanDao;
    @Autowired
    private RepayDao repayDao;
    @Autowired
    private RegionService regionServiceImpl;
    @Autowired
    private MessageServiceImpl messageServicesk;
    @Autowired
    private UserSubAccountDao userSubAccountDao;
    @Autowired
    private CncbTransDao cncbTransDao;
    @Autowired
    private OrderTransDao orderTransDao;
    @Autowired
    private FinanceService financeServiceImpl;
    @Autowired
    private UserMoneyDao userMoneyDao;
    @Autowired
    private LogisticsTransDao logisticsTransDao;
    @Autowired
    private UserinfoService userinfoServiceImpl;
    @Autowired
    private BillDao billDao;
    @Autowired
    private BillAddressDao billAddressDao;
    @Autowired
    private AdminSubAccountService adminSubAccountServiceImpl;




    /**
     * 需要进行事务控制
     *
     * @param map 表单中提交的内容(String):prodId，saleNum，danjia，saleMoney，isSelfCar，contacts，conTel，toRegionId
     *            toRegionAddr，orderData，deliverDate，receiveDate，customerExceptCarriage
     */
    @Override
    @Transactional
    public long order(Map map) {
        //获取与用户和产品相关联的信息写入订单表中
        long userId = (long) map.get("userId");
        Userinfo userinfo = userinfoDao.getOrderUserinfoByUserid(userId);
        long prodId = Long.parseLong((String) map.get("prodId"));
        Map<String, Object> prod = prodDao.singleProdByPrimaryKey(prodId);
        ConvertUtil.convertDataBaseMapToJavaMap(prod);

        Order order = new Order();
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

        order.setSaleNum(new BigDecimal((String) map.get("saleNum")));
        order.setSaleMoney(new BigDecimal((String) map.get("saleMoney")));
        order.setOrderDate(new Date());
        order.setDjModifyDate(new Date());
        order.setOrderIp((String) map.get("orderIp"));
        byte isSelfCar=Byte.parseByte((String) map.get("isSelfCar"));
        order.setIsSelfCar(isSelfCar);
        if ( isSelfCar==1 && null!=map.get("customerExceptCarriage") ){
            order.setCustomerExceptCarriage(new BigDecimal((String)map.get("customerExceptCarriage")));
        }
        order.setOrderType((byte) 0);
        order.setOrderState((byte) 0);
        orderDao.createOrder(order);
        //生成一条wa_order_info记录
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(order.getOrderId());
        orderInfo.setInfoType(order.getOrderType());
        orderInfo.setMgrIp(order.getOrderIp());
        orderInfo.setMgrDate(order.getOrderDate());
        orderInfoDao.createOrderInfo(orderInfo);

        OrderLogisticsInfo orderLogisticsInfo = new OrderLogisticsInfo();
        Date ccDate = str2Date((String) map.get("deliverDate"), "yyyy-MM-dd HH:mm:ss");
        Date ddDate = str2Date((String) map.get("receiveDate"), "yyyy-MM-dd HH:mm:ss");

        orderLogisticsInfo.setOrderId(order.getOrderId());
        orderLogisticsInfo.setDeliverDate(ccDate);
        orderLogisticsInfo.setReceiveDate(ddDate);
        orderLogisticsInfo.setContacts((String) map.get("contacts"));
        orderLogisticsInfo.setConTel((String) map.get("conTel"));
        HashMap paramMap = new HashMap();
        paramMap.put("regionId", (String) map.get("toRegionId"));
        String addr = regionDao.getRegionDetailName(paramMap) + " " + (String) map.get("toRegionAddr");
        orderLogisticsInfo.setAddress(addr);
        orderLogisticsInfoDao.createOrderLogisticsInfoDao(orderLogisticsInfo);

        if (order.getIsSelfCar() == 0) {
            //自提则将司机信息写入wa_vehicle_info表中，将出发时间和到达时间写入wa_order_logistics_info表中
            ArrayList<Map<String, Object>> vehicleList = ConvertUtil.converJSONtoArrayListMap((String) map.get("orderData"));
            for (Map vehivleMap : vehicleList) {
                VehicleInfo vInfo = new VehicleInfo();
                vInfo.setOrderId(order.getOrderId());
                vInfo.setDriverName((String) vehivleMap.get("driverName"));
                vInfo.setDriverPhone((String) vehivleMap.get("driverPhone"));
                vInfo.setVehicleNo((String) vehivleMap.get("carCode"));
                vInfo.setVehicleHangingNo((String) vehivleMap.get("vehicleHangingNo"));
                vInfo.setVehicleSize(Double.parseDouble(vehivleMap.get("vehicleSize").toString()));
                vehicleInfoDao.createVehicleInfo(vInfo);
            }
        }
        //生成订单追踪信息
        /*OrderHistoryTail orderHistoryTail=new OrderHistoryTail();
        orderHistoryTail.setOrderId(order.getOrderId());
        orderHistoryTail.setOrderNo(order.getOrderNo());
        orderHistoryTail.setHistoryDate(order.getOrderDate());
        orderHistoryTail.setContent("买家下单。");
        orderHistoryTail.setTailDate(new Date());
        orderHistoryTailDao.createOrderHistoryTail(orderHistoryTail);*/

        return order.getOrderId();
    }


    /**
     * 字符串转Date
     *
     * @param dateStr
     * @param format
     * @return
     */
    public Date str2Date(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    @Override
    public List<Prod> findProdList(Map map) {
        return prodUserDao.getUserProdList(map);
    }


    /**
     * 获取产品信息，并将其中的prod_region_id(编码)转换成fromRegionName（字符串）
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> findProdById(Map param) {
        Map<String, Object> prodMap = prodDao.findProdById(param);
        ConvertUtil.convertDataBaseMapToJavaMap(prodMap);
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("regionId", (long) prodMap.get("prodRegionId"));
        String fromRegionName = regionDao.getRegionDetailName(paramMap);
        prodMap.put("fromRegionName", fromRegionName);
        return prodMap;
    }

    @Override
    public List<Map<String, Object>> getRegionList(Map param) {
        List resList = regionDao.getRegionList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(resList);
        return resList;
    }

    @Override
    public String findZcAddress(Userinfo userinfo) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("regionId", userinfo.getZcRegionId());
        return regionDao.getRegionDetailName(paramMap) + " " + userinfo.getZcXxAddress();
    }


    @Override
    public Map<String, Object> getOrderDetailInfo(long orderId) {
        //得到订单基本信息
        Map<String, Object> res = orderDao.singleOrderAttachProdAttachOrderLogisticsInfo(orderId);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        //add info 订单联系人，联系人电话
        Map queryOLI=new HashMap();
        queryOLI.put("orderId",orderId);
        Map<String,Object> oli=orderLogisticsInfoDao.singleOLIByConditions(queryOLI);
        res.put("customerContacts",oli.get("contacts"));
        res.put("customerConTel",oli.get("con_tel"));
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
        //从wa_zorder表中得到收货量和成交额
        Map<String, Object> zorderDeliverCount = waOrderServiceImpl.findReceiveProdCount(orderId);
        res.putAll(zorderDeliverCount);
        List<Map<String, Object>> zorders = waOrderServiceImpl.findZorders(orderId);
        res.put("zorders", zorders);
        return res;
    }

    /**
     * 判断有没有发货完成order_state:4？在判断其他子订单有没有确认收货zorder_state:2?都通过则order_state->5
     * @param zorderId wa_zorder表主键
     * @param receiveComment 收货意见
     * @return 数据库内update行数
     */
    @Override
    public int zorderConfirmReceive(long zorderId, String receiveComment) {
        int res = 0;
        /*Zorder zorder=new Zorder();
        zorder.setZorderId(zorderId);
        zorder.setZorderState((byte)2);
        zorder.setReceiveComment(receiveComment);
        res=zorderDao.updateByPrimaryKey(zorder);*/
        Map<String, Object> updateMap = new HashMap();
        updateMap.put("zorderId", zorderId);
        updateMap.put("zorderState", (byte) 2);
        updateMap.put("receiveComment", receiveComment);
        res = zorderDao.updateByPrimaryKey(updateMap);
        Map<String, Object> zo = zorderDao.singleZorderByPrimaryKey(zorderId);
        long orderId = (long) zo.get("order_id");
        Map<String, Object> order = orderDao.singleOrderByPrimaryKey(orderId);
        if ((int) order.get("order_state") != 4) {
            return res;
        }
        Map queryzorder = new HashMap();
        queryzorder.put("orderId", orderId);
        List<Map<String, Object>> zorders = zorderDao.listZordersByConditions(queryzorder);
        for (Map<String, Object> zor : zorders) {
            if ((int) zor.get("zorder_state") != 2) {
                return res;
            }
        }
        Map updateOrder = new HashMap();
        updateOrder.put("orderState", (byte) 5);
        updateOrder.put("orderId", orderId);
        BigDecimal saleSjNum = new BigDecimal(0);
        BigDecimal saleSjMoney = new BigDecimal(0);
        for (Map<String, Object> zor : zorders) {
            saleSjNum = saleSjNum.add((BigDecimal) zor.get("zorder_num"));
            saleSjMoney = saleSjMoney.add((BigDecimal) zor.get("zorder_money"));
        }
        updateOrder.put("saleSjNum", saleSjNum);
        updateOrder.put("saleSjMoney", saleSjMoney);
        res += orderDao.updateOrderByID(updateOrder);
        if ((int) order.get("is_self_car") != 1) {
            return res;
        }
        Map updateLogisticsInfoMap = new HashMap();
        updateLogisticsInfoMap.put("orderId", orderId);
        updateLogisticsInfoMap.put("state", 5);
        res += logisticsInfoDao.updateByConditions(updateLogisticsInfoMap);
        messageServicesk.handleReceiveProdMessage(orderId,zorderId);
        return res;
    }

    @Override
    public void zorderDoubtReceive(long orderId,long zorderId, String receiveComment) {
        Map<String, Object> updateMap = new HashMap();
        updateMap.put("zorderId", zorderId);
        updateMap.put("zorderState", (byte) 11);
        updateMap.put("receiveComment", receiveComment);
        zorderDao.updateByPrimaryKey(updateMap);
        Map<String,Object> updateOrder=new HashMap();
        updateOrder.put("orderId",orderId);
        updateOrder.put("hasQuestion",1);
        orderDao.updateOrderByID(updateOrder);
    }

    @Override
    public List<Map<String, Object>> logisticsInfoListInfo(Map param) {
        List<Map<String, Object>> res = logisticsInfoDao.customerLogisticsInfoListInfo(param);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        return res;
    }

    @Override
    public int logisticsInfoListInfoCount(Map param) {
        int res = logisticsInfoDao.customerLogisticsInfoListInfoCount(param);
        return res;
    }

    @Override
    public Map<String, Object> grabLogisticsListInfo(long liId) {
        Map<String,Object> info=new HashMap<>();
        Map query = new HashMap();
        query.put("logisticsInfoId", liId);
        query.put("grabState", 0);
        List<Map<String, Object>> vehicleGrabs = vehicleGrabDao.listVehicleGrabAttachUserinfoByConditions(query);
        ConvertUtil.convertDataBaseMapToJavaMap(vehicleGrabs);
        for (Map<String,Object> vehicleGrab:vehicleGrabs){
            String senderZcAddress=regionServiceImpl.getDetailAddress(Long.parseLong(vehicleGrab.get("zcRegionId").toString()),(String)vehicleGrab.get("zcXxAddress"));
            vehicleGrab.put("senderZcAddress",senderZcAddress);
        }
        info.put("vehicleGrabs",vehicleGrabs);
        Map<String,Object> logisticsInfoView=logisticsInfoDao.singleLogisticsInfoViewByPrimaryKey(liId);
        ConvertUtil.convertDataBaseMapToJavaMap(logisticsInfoView);
        info.put("logisticsInfoView",logisticsInfoView);
        return info;
    }

    /**
     * 选择物流 update wa_vehicle_grab 表 state，update wa_logistics_info 表 state 及其他
     */
    @Override
    @Transactional
    public int chooseGrab(Map param) {
        int res = 0;
        long logisticsInfoId = Long.parseLong((String) param.get("logisticsInfoId"));
        long vehicleGrabId = Long.parseLong((String) param.get("grabId"));
        //update wa_vehicle_grab 表 state
        Map queryVG = new HashMap();
        queryVG.put("logisticsInfoId", logisticsInfoId);
        List<Map<String, Object>> vehicleGrabs = vehicleGrabDao.listVehicleGrabByConditions(queryVG);
        ConvertUtil.convertDataBaseMapToJavaMap(vehicleGrabs);
        for (Map<String, Object> vehicleGrab : vehicleGrabs) {
            if (vehicleGrabId == (long) vehicleGrab.get("vehicleGrabId")) {
                vehicleGrab.put("grabState", (byte) 1);
            } else {
                vehicleGrab.put("grabState", (byte) -1);
            }
            res += vehicleGrabDao.updateByPrimaryKey(vehicleGrab);
        }

        Map<String, Object> vehicleGrab = vehicleGrabDao.singleVehicleGrabByPrimaryKey(vehicleGrabId);
        Map<String, Object> updateLogisticsInfo = new HashMap();
        updateLogisticsInfo.put("logisticsId", logisticsInfoId);
        updateLogisticsInfo.put("state", (byte) 2);
        updateLogisticsInfo.put("orderPrice", ((BigDecimal) vehicleGrab.get("grab_money")).doubleValue());
        updateLogisticsInfo.put("senderUserId", (long) vehicleGrab.get("sender_user_id"));
        updateLogisticsInfo.put("vehicleGrabId", vehicleGrabId);
        res += logisticsInfoDao.updateByPrimaryKey(updateLogisticsInfo);

        messageServicesk.handleChooseGrabMessage(logisticsInfoId);
        return res;
    }

    @Override
    public Map<String, Object> getPayOrderPageInfo(long orderId, long userId) {
        Map<String, Object> res = orderDao.singleOrderinfoByPrimaryKey(orderId);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        Map<String, Object> res1 = waUserDao.singleUserByPrimaryKey(userId);
        res.put("userMoney", res1.get("user_money"));
        res.put("userCreditMoney", res1.get("user_credit_money"));
        return res;
    }

    @Override
    public Map<String, Object> getPayLogisticsPageInfo(long logisticsInfoId, long userId) {
        Map<String, Object> res = logisticsInfoDao.singleLogisticsInfoViewByPrimaryKey(logisticsInfoId);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        Map<String, Object> res1 = waUserDao.singleUserByPrimaryKey(userId);
        res.put("userMoney", res1.get("user_money"));
        res.put("userCreditMoney", res1.get("user_credit_money"));
        return res;
    }

    @Override
    public boolean isBalanceEnough(long userId, BigDecimal payMoney, int zfMethod, BigDecimal balanceZfMoney, BigDecimal loanZfMoney) {
        Map<String, Object> user = waUserDao.singleUserByPrimaryKey(userId);
        BigDecimal userMoney = (BigDecimal) user.get("user_money");
        BigDecimal userCreditMoney = (BigDecimal) user.get("user_credit_money");
        if (zfMethod == 2) {
            if (userMoney.compareTo(payMoney) < 0) {
                return false;
            }
        } else if (zfMethod == 4) {
            if (userMoney.compareTo(balanceZfMoney) < 0 || userCreditMoney.compareTo(loanZfMoney) < 0) {
                return false;
            }
        } else if (zfMethod == 3) {
            if (userCreditMoney.compareTo(payMoney) < 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int applyCreditLimit(Map<String, Object> params) {
        params.put("other1", "");
        params.put("other2", "");
        params.put("other3", "");
        params.put("other4", "");
        return creditDao.applyCreditLimit(params);
    }

    @Override
    public Map<String, Object> getCurrentCredit(long userId) {
        Map param = new HashMap();
        param.put("orderBy", "credit_apply_date desc");
        param.put("userId", userId);
        Map<String, Object> res = creditDao.singleCreditByConditions(param);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        return res;
    }

    @Override
    public BigDecimal getUserCreditSjMoney(long userId) {
        Map param = new HashMap();
        param.put("creditState", 1);
        param.put("userId", userId);
        Map<String, Object> credit = creditDao.singleCreditByConditions(param);
        if (null != credit && credit.size() != 0 && null != credit.get("credit_sj_money")) {
            return (BigDecimal) credit.get("credit_sj_money");
        }
        return new BigDecimal(0);
    }

    @Override
    public Map<String, Object> getSjCredit(long userId) {
        Map param = new HashMap();
        param.put("creditState", 1);
        param.put("userId", userId);
        Map<String, Object> credit = creditDao.singleCreditByConditions(param);
        if (null != credit && credit.size() > 0) {
            ConvertUtil.convertDataBaseMapToJavaMap(credit);
            return credit;
        }
        Map<String, Object> param1 = new HashMap();
        param1.put("creditState", -2);
        param1.put("userId", userId);
        param1.put("orderBy", "credit_deadline desc");
        Map<String, Object> deadcredit = creditDao.singleCreditByConditions(param1);
        if (null != deadcredit && deadcredit.size() > 0) {
            ConvertUtil.convertDataBaseMapToJavaMap(deadcredit);
            return deadcredit;
        }
        return null;

    }

    @Override
    public void updateUserCreditMoney(long userId) {
        updateUserCreditMoney(userId, getUserCreditSjMoney(userId));
    }

    @Override
    public void updateUserCreditMoney(long userId, BigDecimal creditSjMoney) {
        Map<String, Object> updatemap = new HashMap<>();
        BigDecimal userCreditMoney = creditSjMoney.subtract(getLoansSum(userId));
        updatemap.put("userId", userId);
        updatemap.put("userCreditMoney", userCreditMoney);
        waUserDao.updateUserByUserId(updatemap);
    }

    @Override
    public Map<String, Object> findCreditAccountPageInfo(Long userId) {
        Map<String, Object> res = new HashMap<>();
        Map<String, Object> sjCredit = getSjCredit(userId);
        res.put("credit", sjCredit);
        Map<String, Object> user = waUserDao.singleUserByPrimaryKey(userId);
        ConvertUtil.convertDataBaseMapToJavaMap(user);
        res.put("user", user);
        return res;
    }

    @Override
    public boolean isCreditApplyAvailable(Long userId) {
        Map<String, Object> credit = getCurrentCredit(userId);
        if (null == credit || credit.size() == 0) {
            return true;
        }
        int creditState = (int) credit.get("creditState");
        if (creditState == 0) {
            return false;
        }
        return true;
    }

    @Override
    public Map<String, Object> findCreditApplyPageInfo(Long userId) {
        Map<String, Object> res = new HashMap<>();
        Map<String, Object> sjCredit = getSjCredit(userId);
        res.put("sjCredit", sjCredit);
        return res;
    }

    @Override
    public BigDecimal getLoansSum(Long userId) {
        BigDecimal sumLoans = loanDao.getLoansSum(userId);
        return null == sumLoans ? new BigDecimal(0) : sumLoans;
    }

    @Override
    @Transactional
    public int beforeRepayLoanByBalance(final long userId, final long loanId, final BigDecimal principal,final BigDecimal overdueFine, final BigDecimal interest, final String ip) throws Exception {
        Map<String, Object> user = waUserDao.singleUserByPrimaryKey(userId);
        final BigDecimal oldUserMoney = (BigDecimal) user.get("user_money");
        if (oldUserMoney.compareTo(principal.add(interest).add(overdueFine)) < 0) {
            return 0;
        }
        Map<String,Object> query=new HashMap<>();
        query.put("userId",userId);
        final UserSubAccount userSubAccount=userSubAccountDao.singleQuery(query);
        final AdminSubAccount loanTransfer=adminSubAccountServiceImpl.findLoanTransferAccount();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String,String> paramss=new HashMap<>();
                paramss.put("payAccNo",userSubAccount.getSubAccNo().toString());
                paramss.put("recvAccNo",loanTransfer.getSubAccNo());
                paramss.put("recvAccNm",loanTransfer.getSubAccNm());
                paramss.put("tranAmt",principal.add(interest).add(overdueFine).toString());
                JSONObject memo=new JSONObject();
                memo.put("loanId",loanId);
                memo.put("type",4);
                memo.put("content","还款");
                paramss.put("memo",memo.toString());
                String result= null;
                try {
                    result = CommonUtil.connectCNCBLocalServer(ConstantUtil.CNCB_SERVER_BASEURL+"forceTransfer",paramss);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                R r= JSON.parseObject(result,R.class);
                ForceTransferBasicInfo forceTransferBasicInfo=JSON.parseObject(r.get("forceTransferBasicInfo").toString(),ForceTransferBasicInfo.class);
                CncbTrans cncbTrans=new CncbTrans();
                cncbTrans.setXml(forceTransferBasicInfo.getXml());
                cncbTrans.setClientId(forceTransferBasicInfo.getClientID());
                cncbTrans.setTime(new Date());
                cncbTrans.setType((byte)4);
                JSONObject operationParamsObj=new JSONObject();
                operationParamsObj.put("userId",userId);
                operationParamsObj.put("loanId",loanId);
                operationParamsObj.put("principal",principal);
                operationParamsObj.put("overdueFine",overdueFine);
                operationParamsObj.put("interest",interest);
                operationParamsObj.put("ip",ip);
                cncbTrans.setOperationParams(operationParamsObj.toJSONString());
                cncbTransDao.create(cncbTrans);
                financeServiceImpl.calLocalBalance(userId,principal.add(interest).negate());
            }
        }).start();
        query.clear();
        query.put("loanId",loanId);
        query.put("loanState",21);
        loanDao.updateLoanByPrimaryKey(query);
        return 1;
    }

    @Override
    @Transactional
    public int repayLoanByBalance(long userId, long loanId, BigDecimal principal,BigDecimal overdueFine, BigDecimal interest, String ip) {
        Map<String, Object> user = waUserDao.singleUserByPrimaryKey(userId);
        //BigDecimal oldUserMoney = (BigDecimal) user.get("user_money");
        Map<String, Object> loan = loanDao.singleLoanByPrimaryKey(loanId);
        //update table wa_loan
        Map<String, Object> updateLoan = new HashMap<>();
        updateLoan.put("loanId", loanId);
        updateLoan.put("repayMoney", ((BigDecimal) loan.get("repay_money")).add(principal));
        updateLoan.put("repayLixi", ((BigDecimal) loan.get("repay_lixi")).add(interest));
        updateLoan.put("repayOverdueFine",((BigDecimal)loan.get("repayOverdueFine")).add(overdueFine));
        BigDecimal remainRepayMoney = ((BigDecimal) loan.get("remain_repay_money")).subtract(principal);
        BigDecimal remainOverdueFine=((BigDecimal) loan.get("remain_overdue_fine")).subtract(overdueFine);
        updateLoan.put("remainRepayMoney", remainRepayMoney);
        updateLoan.put("remainOverdueFine",remainOverdueFine);
        updateLoan.put("remainLixiMoney", new BigDecimal(0));
        if (new BigDecimal(0).compareTo(remainRepayMoney) == 0) {
            updateLoan.put("loanState", 3);
            financeServiceImpl.handleLoanRepayoff(loanId);
        }else {
            updateLoan.put("loanState", 2);
        }
        loanDao.updateLoanByPrimaryKey(updateLoan);

        //insert table wa_repay
        Repay repay = new Repay();
        repay.setLoanId(loanId);
        repay.setUserId(userId);
        repay.setRepayMoney(principal);
        repay.setRepayInterestMoney(interest);
        repay.setRepayDate(new Date());
        repay.setRepayIp(ip);
        repay.setRepayType((byte)0);
        repayDao.createRepay(repay);

       /* Map<String, Object> updateuser = new HashMap<>();
        updateuser.put("userId", userId);
        updateuser.put("userMoney", oldUserMoney.subtract(principal.add(interest)));
        waUserDao.updateUserByUserId(updateuser);*/

        messageServicesk.handleRepayLoanMessage(loanId,repay.getRepayId());
        checkLoanRepayedOff(userId, loanId);

        return 1;
    }

    @Override
    public boolean isLoanRepayedOff(long loanId) {
        Map<String, Object> loan = loanDao.singleLoanByPrimaryKey(loanId);
        BigDecimal zero = new BigDecimal(0);
        if (zero.compareTo((BigDecimal) loan.get("remain_pay_money")) == 0 && zero.compareTo((BigDecimal) loan.get("remain_lixi_money")) == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkLoanRepayedOff(long userId, long loanId) {
        Map<String, Object> loan = loanDao.singleLoanByPrimaryKey(loanId);
        if (3 == (int) loan.get("loan_state")) {
            updateUserCreditMoney(userId);
            messageServicesk.handleLoanRepayoffMessage(loanId);
            return true;
        }
        return false;
    }

    @Override
    public List<Map<String, Object>> getLoansIndebt(Map params) {
        params.put("inLoanState", "(2,21)");
        List<Map<String, Object>> res = loanDao.listLoanByConditions(params);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        return res;
    }

    @Override
    public int getLoansIndebtCount(Map params) {
        params.put("loanState", 2);
        int count = loanDao.listLoanByConditionsCount(params);
        return count;
    }

    @Override
    public List<Map<String, Object>> getLoansRepayDetail(Map params) {
        params.put("inLoanState", "(2,3,4)");
        List<Map<String, Object>> res = loanDao.listLoanOrderViewByConditions(params);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        if (null != res && res.size() > 0) {
            for (Map<String, Object> loan : res) {
                Map<String, Object> query = new HashMap<>();
                query.put("loanId", loan.get("loanId"));
                List<Map<String, Object>> repays = repayDao.listRepayByConditions(query);
                ConvertUtil.convertDataBaseMapToJavaMap(repays);
                loan.put("repays", repays);
            }
        }
        return res;
    }

    @Override
    public int getLoansRepayDetailCount(Map params) {
        params.put("inLoanState", "(2,3)");
        int res = loanDao.listLoanByConditionsCount(params);
        return res;
    }

    @Override
    public List<Map<String, Object>> getCreditList(Map params) {
        params.put("orderBy", "credit_apply_date desc");
        List<Map<String, Object>> res = creditDao.listCreditByConditions(params);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        return res;
    }

    @Override
    public int getCreditListCount(Map params) {
        int res = creditDao.listCreditByConditionsCount(params);
        return res;
    }

    @Override
    @Transactional
    public void handle2ndPayProd(long orderId, BigDecimal secondPayMoney, int zfMethod, BigDecimal balance, BigDecimal loan, String certificateImg) throws Exception {
        Map<String,Object> params=new HashMap<>();
        //update order prod2ndpayState字段
        params.put("orderId",orderId);
        params.put("prod2ndpayState",5);
        orderDao.updateOrderByID(params);
        params.clear();

        //update orderTrans
        params.put("orderId",orderId);
        params.put("inTransState","(1,2)");
        Map<String,Object> orderTrans=orderTransDao.singlePoByConditions(params);
        long orderTransId=(long)orderTrans.get("order_trans_id");
        long moneyId=(long)orderTrans.get("money_id");
        BigDecimal zfSjMoney=((BigDecimal)orderTrans.get("zf_money")).add(secondPayMoney);
        BigDecimal zero=new BigDecimal(0);
        params.clear();
        params.put("orderTransId",orderTrans.get("order_trans_id"));
        params.put("zfSjMoney",zfSjMoney);
        if (secondPayMoney.compareTo(zero)>0){
            params.put("zfMethod2",zfMethod);
            params.put("balanceZfMoney2",balance);
            params.put("loanZfMoney2",loan);
        }else if (secondPayMoney.compareTo(zero)<0){
            params.put("balanceZfMoney2",balance);
            params.put("loanZfMoney2",loan);
        }
        params.put("transState",3);
        orderTransDao.update(params);

        Map<String,Object> order=orderDao.singleOrderByPrimaryKey(orderId);
        long supplierId=(long)order.get("supplier_id");
        AdminSubAccount orderTransfer=adminSubAccountServiceImpl.findOrderTransferAccount();
        UserSubAccount seller=financeServiceImpl.getUserSubAccountByUserId(supplierId);
        Map<String,String> paramss=new HashMap<>();
        paramss.put("payAccNo",orderTransfer.getSubAccNo());
        paramss.put("recvAccNo",seller.getSubAccNo());
        paramss.put("recvAccNm",seller.getSubAccNm());
        paramss.put("tranAmt",zfSjMoney.toString());
        JSONObject memo=new JSONObject();
        memo.put("orderId",orderId);
        memo.put("type",6);
        memo.put("content","订单结算给卖方");
        paramss.put("memo",memo.toString());
        String result= org.wella.common.utils.CommonUtil.connectCNCBLocalServer(ConstantUtil.CNCB_SERVER_BASEURL+"forceTransfer",paramss);
        R r= JSON.parseObject(result,R.class);
        ForceTransferBasicInfo forceTransferBasicInfo=JSON.parseObject(r.get("forceTransferBasicInfo").toString(),ForceTransferBasicInfo.class);
        CncbTrans cncbTrans=new CncbTrans();
        cncbTrans.setXml(forceTransferBasicInfo.getXml());
        cncbTrans.setClientId(forceTransferBasicInfo.getClientID());
        cncbTrans.setTime(new Date());
        cncbTrans.setType((byte)6);
        JSONObject operationParamsObj=new JSONObject();
        operationParamsObj.put("orderId",orderId);
        operationParamsObj.put("zfSjMoney",zfSjMoney);
        operationParamsObj.put("orderTransId",orderTransId);
        operationParamsObj.put("moneyId",moneyId);
        cncbTrans.setOperationParams(operationParamsObj.toJSONString());
        cncbTransDao.create(cncbTrans);

        params.clear();
        params.put("orderId",orderId);
        params.put("prod2ndpayState",6);
        orderDao.updateOrderByID(params);
    }

    @Override
    public void handlePay2Seller(long orderId, long orderTransId, long moneyId, BigDecimal zfSjMoney) {
        //update wa_order prod2ndpayState
        Map<String,Object> params=new HashMap<>();
        params.put("orderId",orderId);
        Map<String,Object> order=orderDao.singleOrderByPrimaryKey(orderId);
        long supplierId=(long)order.get("supplier_id");
        int isSelfCar=(int)order.get("is_self_car");
        int prod2ndpayState=7;
        int logistics2ndpayState=(int)order.get("logistics_2ndpay_state");
        if (isSelfCar==0){
            params.put("orderState",(byte)6);
        }else if (isSelfCar==1){
            if (logistics2ndpayState==7){
                params.put("orderState",(byte)6);
            }
        }
        params.put("orderId",orderId);
        params.put("prod2ndpayState",prod2ndpayState);

        orderDao.updateOrderByID(params);
        //update wa_order_trans
        params.clear();
        params.put("orderTransId",orderTransId);
        params.put("transState",5);
        orderTransDao.update(params);
        //update wa_user_money
        params.clear();
        params.put("moneyId",moneyId);
        params.put("jySjMoney",zfSjMoney);
        params.put("completeDate",new Date());
        params.put("jyState",2);
        userMoneyDao.update(params);

        //update seller 余额
        financeServiceImpl.calLocalBalance(supplierId,zfSjMoney);

        /*waOrderServiceImpl.checkOrder2ndpayOff(orderId);*/
    }

    @Override
    public void handleSettleLogistics(long logisticsId,long orderId,BigDecimal zfSjMoney) {
        //update wa_order logistics2ndpayState && orderState
        Map<String,Object> params=new HashMap<>();
        params.put("orderId",orderId);
        Map<String,Object> order=orderDao.singleOrderByPrimaryKey(orderId);
        int isSelfCar=(int)order.get("is_self_car");
        int prod2ndpayState=(int)order.get("prod_2ndpay_state");
        int logistics2ndpayState=7;
        params.clear();
        params.put("orderId",orderId);
        params.put("logistics2ndpayState",logistics2ndpayState);
        if (isSelfCar==1){
            if (prod2ndpayState==7){
                params.put("orderState",(byte)6);
            }
        }
        orderDao.updateOrderByID(params);

        params.clear();
        params.put("logisticsInfoId",logisticsId);
        params.put("transState",1);
        Map<String,Object> logisticsTrans=logisticsTransDao.singlePoByConditions(params);
        long logisticsTransId=(long)logisticsTrans.get("logistics_trans_id");
        long moneyId=(long)logisticsTrans.get("money_id");

        Date now=new Date();

        //update wa_logistics_trans
        params.clear();
        params.put("logisticsTransId",logisticsTransId);
        params.put("zfSjMoney",zfSjMoney);
        params.put("completeDate",now);
        params.put("transState",5);
        logisticsTransDao.update(params);

        //update wa_user_money
        params.clear();
        params.put("moneyId",moneyId);
        params.put("jySjMoney",zfSjMoney);
        params.put("completeDate",now);
        params.put("jyState",5);
        userMoneyDao.update(params);

        //update wa_logistics_info
        params.clear();
        params.put("logisticsId",logisticsId);
        params.put("state",6);
        logisticsInfoDao.updateByPrimaryKey(params);

        //update 物流方余额
        params.clear();
        params=logisticsInfoDao.singleLogisticsInfoByPrimaryKey(logisticsId);
        long senderUserId=(long)params.get("sender_user_id");
        financeServiceImpl.calLocalBalance(senderUserId,zfSjMoney);
    }

    @Override
    public List<Map<String, Object>> billAvaliableOrderList(Map params) {
        params.put("orderState",6);
        params.put("kpState",0);
        List<Map<String,Object>> orders=orderDao.listOrderinfoviewByConditions(params);
        for (Map<String,Object> order:orders){
            long orderId=(long)order.get("order_id");
            Date completeDate=waOrderServiceImpl.getOrderCompleteDate(orderId);
            order.put("complete_date",completeDate);
        }
        ConvertUtil.convertDataBaseMapToJavaMap(orders);
        return orders;
    }

    @Override
    public int billAvaliableOrderListCount(Map params) {
        params.put("orderState",6);
        params.put("kpState",0);
        int res=orderDao.listOrderCount(params);
        return res;
    }

    @Override
    public List<Map<String, Object>> billAvaliableLogisticsList(Map params) {
        params.put("state",6);
        params.put("kpState",0);
        List<Map<String,Object>> logisticss=logisticsInfoDao.listLogisticsOrderInfoViewByConditions(params);
        for (Map<String,Object> logistics:logisticss){
            long orderId=(long)logistics.get("order_id");
            Date completeDate=waOrderServiceImpl.getOrderCompleteDate(orderId);
            logistics.put("complete_date",completeDate);
        }
        ConvertUtil.convertDataBaseMapToJavaMap(logisticss);
        return logisticss;
    }

    @Override
    public int billAvaliableLogisticsListCount(Map params) {
        params.put("state",6);
        params.put("kpState",0);
        int res=logisticsInfoDao.CountLogitticsInfoByConditions(params);
        return res;
    }

    @Override
    public List<Map<String, Object>> billOrders(String ids) {
        Map<String,Object> query=new HashMap<>();
        StringBuilder sb=new StringBuilder();
        sb.append("(").append(ids).append(")");
        query.put("orderIds",sb.toString());
        List<Map<String,Object>> orders=orderDao.listOrderinfoviewByConditions(query);
        for (Map<String,Object> order:orders){
            long orderId=(long)order.get("order_id");
            Date completeDate=waOrderServiceImpl.getOrderCompleteDate(orderId);
            order.put("complete_date",completeDate);
            long supplierId=(long)order.get("supplier_id");
            String sellerZcAddress=userinfoServiceImpl.getZcDetailAddress(supplierId);
            order.put("bill_from_address",sellerZcAddress);
        }
        ConvertUtil.convertDataBaseMapToJavaMap(orders);
        return orders;
    }

    @Override
    public List<Map<String, Object>> billLogisticss(String ids) {
        Map<String,Object> query=new HashMap<>();
        StringBuilder sb=new StringBuilder();
        sb.append("(").append(ids).append(")");
        query.put("logisticsIds",sb.toString());
        List<Map<String,Object>> logisticss=logisticsInfoDao.listLogisticsOrderInfoViewByConditions(query);
        for (Map<String,Object> logistics:logisticss){
            long orderId=(long)logistics.get("order_id");
            Date completeDate=waOrderServiceImpl.getOrderCompleteDate(orderId);
            logistics.put("complete_date",completeDate);
            long senderUserId=(long)logistics.get("sender_user_id");
            String billFromAddress=userinfoServiceImpl.getZcDetailAddress(senderUserId);
            logistics.put("bill_from_address",billFromAddress);
        }
        ConvertUtil.convertDataBaseMapToJavaMap(logisticss);
        return logisticss;
    }

    @Override
    @Transactional
    public int applyBill(Bill bill) {
        int res=0;
        Date now=new Date();
        bill.setBillState((byte)0);
        bill.setApplyDate(now);
        int orderType=bill.getOrderType();
        Map<String,Object> update=new HashMap<>();
        if (orderType==1){
            String orderIds=bill.getOrderIds();
            StringBuilder sb=new StringBuilder();
            sb.append("(").append(orderIds.trim()).append(")");
            update.put("inOrderIds",sb.toString());
            update.put("kpState",1);
            res +=orderDao.updateOrderByID(update);
            bill.setLogisticsInfoIds("");
        }else if (orderType==2){
            String logisticsIds=bill.getLogisticsInfoIds();
            StringBuilder sb=new StringBuilder();
            sb.append("(").append(logisticsIds.trim()).append(")");
            update.put("inLogisticsIds",sb.toString());
            update.put("kpState",1);
            res +=logisticsInfoDao.updateByPrimaryKey(update);
            bill.setOrderIds("");
        }
        res+=billDao.save(bill);
        return res;
    }

    @Override
    public List<Map<String, Object>> applyOrderBillsList(Map param) {
        param.put("orderType",1);
        param.put("orderBy","FIELD(bill_state,-1,1,0,2),apply_date desc");
        List<Map<String,Object>> list=billDao.listBilllistviewByConditions(param);
        for (Map<String,Object> bill:list){
            String orderIds=bill.get("order_ids").toString();
            StringBuilder inOrderIds=new StringBuilder();
            inOrderIds.append("(").append(orderIds.trim()).append(")");
            String order_nos=orderDao.concatOrderNos(inOrderIds.toString());
            bill.put("order_nos",order_nos);
        }
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        return list;
    }

    @Override
    public int applyOrderBillsListCount(Map param) {
        param.put("orderType",1);
        int res=billDao.listBilllistviewByConditionsCount(param);
        return res;
    }

    @Override
    @Transactional
    public int receiveBill(long billId, boolean flag) {
        Date now=new Date();
        int res=0;
        Bill bill=billDao.query(billId);
        String orderIds=bill.getOrderIds();
        StringBuilder sb=new StringBuilder();
        sb.append("(").append(orderIds.trim()).append(")");

        //update table wa_bill
        Bill update=new Bill();
        update.setBillId(billId);
        //update table wa_order
        Map<String,Object> updateOrder=new HashMap<>();
        updateOrder.put("inOrderIds",sb.toString());
        if (flag){
            update.setConfirmDate(now);
            update.setBillState((byte)2);

            updateOrder.put("kpState",3);
        }else {
            update.setBillState((byte)-1);

            updateOrder.put("kpState",1);
        }
        res+=billDao.update(update);
        res +=orderDao.updateOrderByID(updateOrder);
        return res;
    }

    @Override
    public int saveOrUpdateBillAddress(BillAddress billAddress) {
        long userId=billAddress.getUserId();
        BillAddress billAddress1=billAddressDao.queryByuserId(userId);
        if (null==billAddress1){
            billAddressDao.save(billAddress);
            return 1;
        }else {
            billAddressDao.updateByuserId(billAddress);
            return 2;
        }
    }

    @Override
    public BillAddress findBillAddress(long userId) {
        return billAddressDao.queryByuserId(userId);
    }

    @Override
    public int repayLoanByRefund(long loanId, BigDecimal repayMoney) {
        int res=0;
        Map<String,Object> loan=loanDao.singleLoanByPrimaryKey(loanId);

        Map<String, Object> updateLoan = new HashMap<>();
        updateLoan.put("loanId", loanId);
        updateLoan.put("repayMoney", ((BigDecimal) loan.get("repay_money")).add(repayMoney));
        BigDecimal remainRepayMoney = ((BigDecimal) loan.get("remain_repay_money")).subtract(repayMoney);
        updateLoan.put("remainRepayMoney", remainRepayMoney);
        if (new BigDecimal(0).compareTo(remainRepayMoney) == 0) {
            updateLoan.put("loanState", 3);
        }else {
            updateLoan.put("loanState", 2);
        }
        res+=loanDao.updateLoanByPrimaryKey(updateLoan);

        //insert table wa_repay
        Repay repay = new Repay();
        repay.setLoanId(loanId);
        repay.setUserId((long)loan.get("user_id"));
        repay.setRepayMoney(repayMoney);
        repay.setRepayInterestMoney(new BigDecimal(0));
        repay.setRepayDate(new Date());
        repay.setRepayIp("0:0:0:0:0:0:0:1");
        repay.setRepayType((byte)1);
        res+=repayDao.createRepay(repay);
        return res;
    }

    @Override
    public int handleCreditPayRefund(long orderId, BigDecimal secondPayMoney, long orderTransId) throws Exception {
        int res=0;
        Map<String,Object> orderTrans=orderTransDao.singlePoByPrimaryKey(orderTransId);
        long loanId=(long)orderTrans.get("loan_id");
        res+=repayLoanByRefund(loanId,secondPayMoney.abs());
        //res+=handle2ndPayProd();
        handle2ndPayProd(orderId,secondPayMoney,0,new BigDecimal(0),secondPayMoney,"");
        return res;
    }

    @Override
    public int handleComboPayRefundStep1(long orderId, long userId, BigDecimal secondPayMoney, long orderTransId, BigDecimal refundBalance, BigDecimal refundLoan) {
        int res=0;
        Map<String,Object> orderTrans=orderTransDao.singlePoByPrimaryKey(orderTransId);
        long loanId=(long)orderTrans.get("loan_id");
        res+=repayLoanByRefund(loanId,refundLoan.abs());

        UserSubAccount userSubAccount=financeServiceImpl.getUserSubAccountByUserId(userId);
        String subAccNo=userSubAccount.getSubAccNo();
        String subAccNm=userSubAccount.getSubAccNm();

        AdminSubAccount orderTransfer=adminSubAccountServiceImpl.findOrderTransferAccount();

        Map<String,String> paramss=new HashMap<>();
        paramss.put("payAccNo",orderTransfer.getSubAccNo());
        paramss.put("recvAccNo",subAccNo);
        paramss.put("recvAccNm",subAccNm);
        paramss.put("tranAmt",refundBalance.abs().toString());
        JSONObject memo=new JSONObject();
        memo.put("orderId",orderId);
        memo.put("type",10);
        memo.put("content","订单多退退至余额");
        paramss.put("memo",memo.toString());
        String result= null;
        try {
            result = org.wella.common.utils.CommonUtil.connectCNCBLocalServer(ConstantUtil.CNCB_SERVER_BASEURL+"forceTransfer",paramss);
        } catch (Exception e) {
            e.printStackTrace();
        }
        R r= JSON.parseObject(result,R.class);
        ForceTransferBasicInfo forceTransferBasicInfo=JSON.parseObject(r.get("forceTransferBasicInfo").toString(),ForceTransferBasicInfo.class);
        CncbTrans cncbTrans=new CncbTrans();
        cncbTrans.setXml(forceTransferBasicInfo.getXml());
        cncbTrans.setClientId(forceTransferBasicInfo.getClientID());
        cncbTrans.setTime(new Date());
        cncbTrans.setType((byte)10);
        JSONObject operationParamsObj=new JSONObject();
        operationParamsObj.put("orderId",orderId);
        operationParamsObj.put("secondPayMoney",secondPayMoney);
        operationParamsObj.put("orderTransId",orderTrans.get("order_trans_id"));
        operationParamsObj.put("refundBalance",refundBalance);
        operationParamsObj.put("refundLoan",refundLoan);
        operationParamsObj.put("userId",userId);
        cncbTrans.setOperationParams(operationParamsObj.toJSONString());
        res+=cncbTransDao.create(cncbTrans);
        return res;
    }

    @Override
    public int handleComboPayRefundStep2(long orderId, long userId, BigDecimal secondPayMoney, long orderTransId, BigDecimal refundBalance, BigDecimal refundLoan) throws Exception {
        handle2ndPayProd(orderId,secondPayMoney,0,refundBalance,refundLoan,"");
        return 0;
    }

    @Override
    public void handleCncbType11(long orderId, long userId, BigDecimal secondPayMoney, long orderTransId, int zfMethod, BigDecimal balance, BigDecimal loan,String ip) {
        Map<String,Object> user=waUserDao.singleUserByPrimaryKey(userId);
        BigDecimal lixiRate=(BigDecimal) user.get("lixi_rate");

        Map<String,Object> orderTrans=orderTransDao.singlePoByPrimaryKey(orderTransId);
        long moneyId=(long)orderTrans.get("money_id");
        BigDecimal zfMoney=(BigDecimal)orderTrans.get("zf_money");
        BigDecimal zfSjMoney=zfMoney.add(secondPayMoney);

        Loan newLoan=new Loan();
        newLoan.setMoneyId(moneyId);
        newLoan.setUserId(userId);
        newLoan.setLoanMoney(loan);
        newLoan.setApplyDate(new Date());
        newLoan.setLixiRate(lixiRate);
        newLoan.setLoanState((byte)0);
        newLoan.setLoanIp(ip);
        newLoan.setLoanType((byte)3);
        loanDao.create(newLoan);
        long loanId=newLoan.getLoanId();

        Map<String,Object> param=new HashMap<>();
        param.put("orderTransId",orderTransId);
        param.put("zfMethod2",zfMethod);
        param.put("balanceZfMoney2",balance);
        param.put("loanZfMoney2",loan);
        param.put("loanId2",loanId);
        param.put("zfSjMoney",zfSjMoney);
        param.put("transState",2);
        orderTransDao.update(param);

        param.clear();
        param.put("orderId",orderId);
        param.put("prod2ndpayState",1);
        orderDao.updateOrderByID(param);
    }
}
