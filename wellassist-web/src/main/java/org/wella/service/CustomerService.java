package org.wella.service;

import io.wellassist.utils.Query;
import org.wella.entity.Bill;
import org.wella.entity.Order;
import org.wella.entity.Prod;
import org.wella.entity.Userinfo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/10.
 */
public interface CustomerService {
    /**
     * @param map 表单中提交的内容
     * 买方下订单操作,主要完成以下操作
     * 1.根据表单填写的内容，向表wa_order表中插入一条记录，此时订单的业务编发要根据相应的逻辑生成
     * 2.在表wa_order_info表中生成一条记录
     */
    long order(Map map);

    /**
     *订单支付
     * @param orderId
     *
     */
    void orderPay(int orderId);

    /**
     *订单取消支付
     * @param orderId
     */
    void cancelPay(int orderId);

    /**
     *物流订单处理，选择物流单位
     * @param deliveryOrderId
     *
     */
    void processDeliveryOrder(int deliveryOrderId);

    /**
     *物流订单支付处理
     * @param deliveryOrderId
     */
    void deliveryOrderPay(int deliveryOrderId);

    /**
     *确认发货
     * @param deliveryOrderId
     */
    void confirmDelivery(int deliveryOrderId);

    /**
     *评价对方
     * @param orderId
     */
    void evaluate(int orderId);

    /**
     *贷款申请
     * @param customerId
     */
    void loanApply(int customerId);

    /**
     *贷款到账确认
     */
    void loanConfirm();

    /**
     *查询订单列表
     * @return
     */
    List<Order> findOrderList();

    /**
     *查询订单列表
     * @param map
     * @return
     */
    List<Order> findOrderList(Map map);

    /**
     *创建订单
     */
    void createOrder();

    /**
     *更新订单
     * @param order
     */
    void updateOrder(Order order);

    /**
     *删除订单
     * @param orderId
     */
    void deleteOrder(int orderId);

    /**
     *获取产品列表
     * @return
     */
    List<Prod> findProdList(Map map);


    /**
     * 获取产品信息，并将其中的prod_region_id(编码)转换成fromRegionName（字符串）
     * @param param
     * @return
     */
    Map<String,Object> findProdById(Map param);

    /**
     *获取区域列表
     * @param param parentRegionId
     * @return
     */
    List<Map<String,Object>> getRegionList(Map param);

    /**
     * 获取公司所在地
     * @param userinfo
     * @return
     */
    String findZcAddress(Userinfo userinfo);

    /**
     * 订单详情页面信息
     * @param orderId 订单主键
     * @return 订单基本信息，订单实时价格，已收货收货量和成交额
     */
    Map<String,Object> getOrderDetailInfo(long orderId);

    /**
     * 判断有没有发货完成order_state:4？在判断其他子订单有没有确认收货zorder_state:2?都通过则order_state->5
     * @param zorderId wa_zorder表主键
     * @param receiveComment 收货意见
     * @return 数据库内update行数
     */
    int zorderConfirmReceive(long zorderId,String receiveComment);

    /**
     * 分批发货收货存疑
     * @param orderId wa_order表主键
     * @param zorderId wa_zorder表主键
     * @param receiveComment 收货意见
     */
    void zorderDoubtReceive(long orderId,long zorderId, String receiveComment);

    /**
     * 物流订单列表数据
     * @param param 分页参数
     * @return 物流订单列表
     */
    List<Map<String, Object>> logisticsInfoListInfo(Map param);

    /**
     * 物流订单列表总条
     * @param param 分页参数
     * @return 物流订单列表总条数
     */
    int logisticsInfoListInfoCount(Map param);

    /**
     * 物流抢单列表数据
     * @param liId wa_logistics_info 主键
     * @return 物流抢单列表
     */
    Map<String,Object> grabLogisticsListInfo(long liId);

    /**
     * 选择物流
     * @param param logisticsInfoId:wa_logistics_info表主键，vehicleGrabId：wa_vehicle_grab表主键
     * @return 数据库内update行数
     */
    int chooseGrab(Map param);

    void testPayLogistics(long logisticsInfoId);

    int testPayOrder(long orderId);


    Map<String,Object> getPayOrderPageInfo(long orderId,long userId);

    Map<String,Object> getPayLogisticsPageInfo(long logisticsInfoId,long userId);

    boolean isBalanceEnough(long userId, BigDecimal payMoney, int zfMethod,BigDecimal balanceZfMoney,BigDecimal loanZfMoney);

    /**
     * 申请授信额度
     * @param params
     */
    int applyCreditLimit(Map<String, Object> params);

    /**
     * 得到用户当前授信信息
     * @param userId
     * @return
     */
    Map<String,Object> getCurrentCredit(long userId);

    /**
     * 得到用户的授信总额度
     * @param userId
     * @return
     */
    BigDecimal getUserCreditSjMoney(long userId);

    /**
     * 得到用户授信额度的该条授信记录
     */
    Map<String,Object> getSjCredit(long userId);

    /**
     * 在授信总额度变化时修改用户的可用额度
     * @param userId
     */
    void updateUserCreditMoney(long userId);

    /**
     * 在授信总额度变化时修改用户的可用额度
     * @param userId
     */
    void updateUserCreditMoney(long userId,BigDecimal creditSjMoney);

    Map<String,Object> findCreditAccountPageInfo(Long userId);

    /**
     * 判断用户是否已经提交了授信申请，是则不能再提交
     * @param userId
     * @return
     */
    boolean isCreditApplyAvailable(Long userId);

    Map<String,Object> findCreditApplyPageInfo(Long userId);

    /**
     * 授信负债总额（不算利息）
     * @param userId
     * @return
     */
    BigDecimal getLoansSum(Long userId);

    /**
     * 通过余额还款，提交银行订单
     * @param userId
     * @param loanId
     * @param principal
     * @param interest
     * @param ip
     */
    int beforeRepayLoanByBalance(long userId,long loanId,BigDecimal principal,BigDecimal interest,String ip) throws Exception;

    /**
     * 通过余额还款
     * @param userId
     * @param loanId
     * @param principal 本金
     * @param interest 利息
     * @return 0:失败，1成功
     */
    int repayLoanByBalance(long userId,long loanId,BigDecimal principal,BigDecimal interest,String ip);

    /**
     * 单笔授信是否还清
     * @param loanId
     * @return
     */
    boolean isLoanRepayedOff(long loanId);

    /**
     * 每次还款后，校验单笔贷款是否还清，并相应提升可用额度
     * @param loanId
     * @return
     */
    boolean checkLoanRepayedOff(long userId,long loanId);

    /**
     * 未还清的贷款记录
     * @param params:long userId,int start,int size
     * @return
     */
    List<Map<String,Object>> getLoansIndebt(Map params);
    /**
     * 未还清的贷款记录count
     * @param params:long userId,int start,int size
     * @return
     */
    int getLoansIndebtCount(Map params);

    /**
     * 所有贷款及相应的还款详情
     * @param params:long userId,int start,int size
     * @return
     */
    List<Map<String,Object>> getLoansRepayDetail(Map params);

    /**
     * 所有贷款及相应的还款详情count
     * @param params:long userId,int start,int size
     * @return
     */
    int getLoansRepayDetailCount(Map params);

    /**
     * 用户额度申请记录
     * @param params:long userId,int start,int size
     * @return
     */
    List<Map<String,Object>> getCreditList(Map params);

    /**
     * 用户额度申请记录count
     * @param params:long userId,int start,int size
     * @return
     */
    int getCreditListCount(Map params);

    /**
     * 买家多退少补
     * @param orderId
     * @param secondPayMoney
     * @param zfMethod
     * @param balance
     * @param loan
     * @param certificateImg
     */
    void handle2ndPayProd(long orderId, BigDecimal secondPayMoney, int zfMethod, BigDecimal balance, BigDecimal loan, String certificateImg) throws Exception;

    /**
     * 结算给卖家成功后数据入库
     * @param orderId
     * @param orderTransId
     * @param moneyId
     * @param zfSjMoney
     */
    void handlePay2Seller(long orderId,long orderTransId,long moneyId,BigDecimal zfSjMoney);

    /**
     * 结算给物流成功后数据入库
     * @param logisticsId
     * @param orderId
     * @param zfSjMoney
     */
    void handleSettleLogistics(long logisticsId,long orderId,BigDecimal zfSjMoney);

    /**
     * 可开发票订单列表
     * @param params 分页参数
     * @return 可开发票订单列表
     */
    List<Map<String,Object>> billAvaliableOrderList(Map params);

    /**
     * 可开发票订单列表总数
     * @param params 分页参数
     * @return 可开发票订单列表总数
     */
    int billAvaliableOrderListCount(Map params);

    /**
     * 可开发票物流订单列表
     * @param params 分页参数
     * @return 可开发票物流订单列表
     */
    List<Map<String,Object>> billAvaliableLogisticsList(Map params);

    /**
     * 可开发票物流订单列表总数
     * @param params 分页参数
     * @return 可开发票物流订单列表总数
     */
    int billAvaliableLogisticsListCount(Map params);

    /**
     * 申请发票的订单信息
     * @param ids 订单id拼接的字符串
     * @return 申请发票的订单信息
     */
    List<Map<String,Object>> billOrders(String ids);


    /**
     * 申请发票的订单信息
     * @param ids 物流订单id拼接的字符串
     * @return 申请发票的订单信息
     */
    List<Map<String,Object>> billLogisticss(String ids);

    int applyBill(Bill bill);

    List<Map<String,Object>> applyOrderBillsList(Map param);

    int applyOrderBillsListCount(Map param);
}
