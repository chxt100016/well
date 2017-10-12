package org.wella.service;

import org.wella.entity.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by ailing on 2017/5/10.
 */
public interface CustomerService {

    /**
     * 买方下订单操作,主要完成以下操作
     * 1.根据表单填写的内容，向表wa_order表中插入一条记录，此时订单的业务编发要根据相应的逻辑生成
     * 2.在表wa_order_info表中生成一条记录
     * @param map 表单中提交的内容
     * @return orderId
     */
    long order(Map map);

    /**
     * 获取产品列表
     * @param map 查询参数
     * @return 可购买的产品列表
     */
    List<Prod> findProdList(Map map);

    /**
     * 获取产品信息，并将其中的prod_region_id(编码)转换成fromRegionName（字符串）
     * @param param 查询参数
     * @return 产品信息
     */
    Map<String,Object> findProdById(Map param);

    /**
     * 获取区域列表
     * @param param parentRegionId
     * @return 区域列表
     */
    List<Map<String,Object>> getRegionList(Map param);

    /**
     * 获取公司所在地
     * @param userinfo
     * @return 公司所在地
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

    /**
     * 订单预付款页面model信息
     * @param orderId orderId
     * @param userId userId
     * @return 订单预付款页面model信息
     */
    Map<String,Object> getPayOrderPageInfo(long orderId,long userId);

    /**
     * 物流预付款页面model信息
     * @param logisticsInfoId wa_logistics_info主键
     * @param userId userId
     * @return 物流预付款页面model信息
     */
    Map<String,Object> getPayLogisticsPageInfo(long logisticsInfoId,long userId);

    /**
     * 付款所需余额是否足够
     * @param userId userId
     * @param payMoney 支付金额
     * @param zfMethod 支付方式
     * @param balanceZfMoney 余额支付部分
     * @param loanZfMoney 授信支付部分
     * @return true 足够
     * @return false 不够
     */
    boolean isBalanceEnough(long userId, BigDecimal payMoney, int zfMethod,BigDecimal balanceZfMoney,BigDecimal loanZfMoney);

    /**
     * 申请授信额度
     * @param params 表单提交参数
     * @return 数据库更新记录数
     */
    int applyCreditLimit(Map<String, Object> params);

    /**
     * 得到用户当前授信信息
     * @param userId userId
     * @return 得到用户当前授信信息
     */
    Map<String,Object> getCurrentCredit(long userId);

    /**
     * 得到用户的授信总额度
     * @param userId userId
     * @return 得到用户的授信总额度
     */
    BigDecimal getUserCreditSjMoney(long userId);

    /**
     * 得到用户授信额度的该条授信记录
     * @param userId userId
     * @return 得到用户授信额度的该条授信记录
     */
    Map<String,Object> getSjCredit(long userId);

    /**
     * 在授信总额度变化时修改用户的可用额度
     * @param userId userId
     */
    void updateUserCreditMoney(long userId);

    /**
     * 在授信总额度变化时修改用户的可用额度
     * @param userId userId
     */
    void updateUserCreditMoney(long userId,BigDecimal creditSjMoney);

    /**
     * 页面信息model：授信账户页面
     * @param userId userId
     * @return model页面信息：授信账户页面
     */
    Map<String,Object> findCreditAccountPageInfo(Long userId);

    /**
     * 判断用户是否已经提交了授信申请，是则不能再提交
     * @param userId userId
     * @return true：已提交，false：未提交
     */
    boolean isCreditApplyAvailable(Long userId);

    /**
     * model页面信息：买家授信申请
     * @param userId userId
     * @return model页面信息：买家授信申请
     */
    Map<String,Object> findCreditApplyPageInfo(Long userId);

    /**
     * 授信负债总额（不算利息）
     * @param userId userId
     * @return 授信负债总额（不算利息）
     */
    BigDecimal getLoansSum(Long userId);

    /**
     * 通过余额还款，提交银行订单
     * @param userId userId
     * @param loanId wa_loan表主键
     * @param principal 还款本金
     * @param interest 还款利息
     * @param ip 还款ip
     * @return 数据库update记录数
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
     * @return 未还清的贷款记录
     */
    List<Map<String,Object>> getLoansIndebt(Map params);

    /**
     * 未还清的贷款记录count
     * @param params:long userId,int start,int size
     * @return 未还清的贷款记录count
     */
    int getLoansIndebtCount(Map params);

    /**
     * 所有贷款及相应的还款详情
     * @param params:long userId,int start,int size
     * @return 所有贷款及相应的还款详情
     */
    List<Map<String,Object>> getLoansRepayDetail(Map params);

    /**
     * 所有贷款及相应的还款详情count
     * @param params:long userId,int start,int size
     * @return 所有贷款及相应的还款详情count
     */
    int getLoansRepayDetailCount(Map params);

    /**
     * 用户额度申请记录
     * @param params:long userId,int start,int size
     * @return 用户额度申请记录
     */
    List<Map<String,Object>> getCreditList(Map params);

    /**
     * 用户额度申请记录count
     * @param params:long userId,int start,int size
     * @return 用户额度申请记录count
     */
    int getCreditListCount(Map params);

    /**
     * 买家多退少补后将订单结算金额转入卖方账户
     * @param orderId orderId
     * @param secondPayMoney 结算金额：为负为退款，为正为补款
     * @param zfMethod 支付方式
     * @param balance 余额支付部分
     * @param loan 授信支付部分
     * @param certificateImg 线下支付文件上传回调url
     */
    void handle2ndPayProd(long orderId, BigDecimal secondPayMoney, int zfMethod, BigDecimal balance, BigDecimal loan, String certificateImg) throws Exception;

    /**
     * 结算给卖家成功后数据入库
     * @param orderId orderId
     * @param orderTransId wa_order_trans 主键
     * @param moneyId wa_user_money主键
     * @param zfSjMoney 订单实际金额
     */
    void handlePay2Seller(long orderId,long orderTransId,long moneyId,BigDecimal zfSjMoney);

    /**
     * 结算给物流成功后数据入库
     * @param logisticsId wa_logistics_info主键
     * @param orderId orderId
     * @param zfSjMoney 订单实际金额
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

    /**
     * 请求开具发票
     * @param bill 接收vo数据
     * @return 更新的表行数
     */
    int applyBill(Bill bill);

    /**
     * 已申请的商品订单发票列表数据
     * @param param 分页参数
     * @return 已申请的商品订单发票列表数据
     */
    List<Map<String,Object>> applyOrderBillsList(Map param);

    /**
     * 已申请的商品订单发票列表记录总条数
     * @param param 分页参数
     * @return 已申请的商品订单发票列表记录总条数
     */
    int applyOrderBillsListCount(Map param);

    /**
     * 处理买家收到发票
     * @param billId 发票主键
     * @param flag true：确认收到
     * @return 数据库更新条目
     */
    int receiveBill(long billId, boolean flag);

    /**
     * 保存或更新用户发票地址
     * @param billAddress 发票地址pojo
     * @return 1：save；2：update
     */
    int saveOrUpdateBillAddress(BillAddress billAddress);

    /**
     * 得到用户发票地址信息
     * @param userId
     * @return
     */
    BillAddress findBillAddress(long userId);

    /**
     * 授信支付的退款--直接还款到贷款本金
     * @param loanId wa_loan 主键
     * @param repayMoney 还款金额
     * @return 数据库更新行数
     */
    int repayLoanByRefund(long loanId,BigDecimal repayMoney);

    /**
     *
     * @param orderId
     * @param secondPayMoney
     * @param orderTransId
     * @return
     */
    int handleCreditPayRefund(long orderId, BigDecimal secondPayMoney, long orderTransId) throws Exception;

    int handleComboPayRefundStep1(long orderId, long userId, BigDecimal secondPayMoney, long orderTransId, BigDecimal refundBalance, BigDecimal refundLoan);

    int handleComboPayRefundStep2(long orderId, long userId, BigDecimal secondPayMoney, long orderTransId, BigDecimal refundBalance, BigDecimal refundLoan) throws Exception;

    void handleCncbType11(long orderId, long userId, BigDecimal secondPayMoney, long orderTransId, int zfMethod, BigDecimal balance, BigDecimal loan,String ip);
}
