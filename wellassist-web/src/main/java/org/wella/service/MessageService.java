package org.wella.service;

import io.wellassist.utils.PageUtils;
import org.wella.entity.CreditRecord;
import org.wella.entity.Message;
import org.wella.entity.Userinfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by ailing on 2017/7/25.
 */

public interface MessageService {

     void addCreditRecord(CreditRecord creditRecord);

    List<CreditRecord> getCreditRecordList(Map<String, Object> map);

    Userinfo getCreditRecord(Long id);

    int queryRecordCount(Map<String, Object> map);

    /**
     * 生成订单确认的消息
     * 消息关键字：订单编号，确认单价，数量
     * @param orderId orderId
     * @return 数据库操作记录数
     */
    int handleOrderConfirmMessage(long orderId,long orderLogId);

    /**
     * 生成订单编辑的消息
     * 消息关键字：订单编号，确认单价，数量
     * @param orderId orderId
     * @param orderLogId wa_order_log 主键
     * @return 数据库操作记录数
     */
    int handleOrderPriceEditMessage(long orderId, long orderLogId);

    /**
     * 生成物流抢单的消息
     * 消息关键字：订单编号
     * @param orderId orderId
     * @return 数据库操作记录数
     */
    int handleOrderGrabedMessage(long orderId);

    /**
     * 生成选择物流抢单的消息
     * 消息关键字：订单编号
     * @param logisticsInfoId
     * @return 数据库操作记录数
     */
    int handleChooseGrabMessage(long logisticsInfoId);

    /**
     * 生成商品订单线下付款审核的消息
     * @param orderId orderId
     * @param passCheck 是否通过审核
     * @param checkComment 审核意见
     * @return 数据库操作记录数
     */
    int handleProdOfflinePayCheckMessage(Long orderId, int passCheck,String checkComment);

    /**
     * 生成物流订单线下付款审核的消息
     * @param orderId orderId
     * @param passCheck 是否通过审核
     * @param checkComment 审核意见
     * @return 数据库操作记录数
     */
    int handleLogisticsOfflinePayCheckMessage(Long orderId, int passCheck,String checkComment);

    /**
     * 生成贷款的消息
     * @param loanId wa_loan 主键
     * @return 数据库操作记录数
     */
    int handleLoanCreatedMessage(long loanId);

    /**
     *授信付款驳回的消息
     * @param orderId orderId
     * @return 数据库操作记录数
     */
    int handleLoanSayNoMessage(long orderId);

    /**
     * 卖家发货的消息
     * @param orderId orderId
     * @param zorderId wa_zorder 主键
     * @return 数据库操作记录数
     */
    int handleSendProdMessage(long orderId, long zorderId);

    /**
     * 买家收货的消息
     * @param orderId orderId
     * @param zorderId wa_zorder 主键
     * @return 数据库操作记录数
     */
    int handleReceiveProdMessage(long orderId, long zorderId);

    /**
     * 卖家结束发货的消息
     * @param orderId orderId
     * @return 数据库操作记录数
     */
    int handleSendProdOverMessage(long orderId);

    /**
     * 管理員修改订单的消息
     * @param orderId orderId
     * @return 数据库操作记录数
     */
    int handleAdminUpdateOrderMessage(long orderId);

    /**
     * 订单完成收货消息
     * @param orderId orderId
     * @return 数据库操作记录数
     */
    int handleReceiveProdOverMessage(long orderId);

    /**
     *注册审核通过或不通过向用户邮箱发送消息
     * @param userEmail 用户邮箱
     * @param reviewComment 审核意见
     * @param passReview 是否通过审核
     */
    void handleRegisterReviewMessage(String userEmail,String reviewComment,boolean passReview);

    /**
     * 重置密码消息提醒
     * @param userId userId
     * @return 数据库操作记录数
     */
    int handleResetPasswordMessage(long userId);

    /**
     * 管理员更改用户信息的消息
     * @param userId userId
     * @return 数据库操作记录数
     */
    int handleAdminUpdateUserinfoMessage(long userId);

    /**
     * 金额充值申请的消息
     * @param userId userId
     * @param rechargeMoney 充值金额
     * @return 数据库操作记录数
     */
    int handleRechargeApplyMessage(Long userId, BigDecimal rechargeMoney);

    /**
     * 充值审核的消息
     * @param bankOrderId wa_bank_order主键
     * @return 数据库操作记录数
     */
    int handleRechargeHandleMessage(long bankOrderId);

    /**
     * 提现审核的消息
     * @param withdrawId wa_withdraw 主键
     * @param withdrawState 审核状态
     * @return 数据库操作记录数
     */
    int handleWithdrawHandleMessage(long withdrawId, int withdrawState);

    /**
     * 提现申请的消息
     * @param userId userId
     * @param withdrawMoney 提现金额
     * @return 数据库操作记录数
     */
    int handleWithdrawApplyMessage(Long userId, BigDecimal withdrawMoney);

    /**
     * 处理授信申请的理由
     * @param creditId wa_credit主键
     * @param flag 是否通过授信
     * @param comment 审核意见
     * @return 数据库操作记录数
     */
    int handleCreditCheck(long creditId, int flag, String comment);

    /**
     * 处理还款的消息
     * @param loanId wa_loan主键
     * @param repayId wa_repay主键
     * @return 数据库操作记录数
     */
    int handleRepayLoanMessage(long loanId, Long repayId);

    /**
     * 处理贷款还清的消息
     * @param loanId wa_loan 主键
     * @return 数据库操作记录数
     */
    int handleLoanRepayoffMessage(long loanId);

    /**
     * 未读消息数
     * @param userId userId
     * @return 数据库操作记录数
     */
    int unreadMsgCount(long userId);

    /**
     * 系统消息page对象
     * @param param 查询参数
     * @return 系统消息page对象
     */
    PageUtils systemicMesList(Map param);

    /**
     * 财务消息page对象
     * @param param 分页参数
     * @return 财务消息page对象
     */
    PageUtils financeMesList(Map param);

    /**
     * 垃圾箱page对象
     * @param param 查询参数
     * @return 垃圾箱page对象
     */
    PageUtils shitMesList(Map param);

    /**
     * 消息详情
     * @param id id
     * @return wa_message pojo
     */
    Message singleMessageByPk(long id);

    /**
     * 删除一条消息
     * @param id id
     */
    void delete1Msg(long id);

    /**
     * 批量删除
     * @param ids id拼接字符串
     */
    void deleteMsgBatch(String ids);

    /**
     * 将未读置为已读
     * @param id 消息id
     */
    void readMsg(long id);
}
