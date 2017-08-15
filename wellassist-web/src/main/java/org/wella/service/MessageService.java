package org.wella.service;

import org.wella.entity.CreditRecord;
import org.wella.entity.Message;
import org.wella.entity.Userinfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by xuqinghuo on 2017/7/25.
 */

public interface MessageService {


    List<Message> getMessageList(Map<String, Object> map);

    List<Message> getMessage(int id);

    void delMessage(Long[] id);

    void upMessage(Integer id);

     void addCreditRecord(CreditRecord creditRecord);

    List<CreditRecord> getCreditRecordList(Map<String, Object> map);

    Userinfo getCreditRecord(Long id);


    /*int queryTotal(Map<String, Object> map);*/

    int queryRecordCount(Map<String, Object> map);

    /**
     * 生成订单确认的消息
     * 消息关键字：订单编号，确认单价，数量
     * @param orderId
     * @return 数据库操作记录数
     */
    int handleOrderConfirmMessage(long orderId,long orderLogId);

    /**
     * 生成订单编辑的消息
     * 消息关键字：订单编号，确认单价，数量
     * @param orderId
     * @param orderLogId
     * @return
     */
    int handleOrderPriceEditMessage(long orderId, long orderLogId);

    /**
     * 生成物流抢单的消息
     * 消息关键字：订单编号
     * @param orderId
     * @return
     */
    int handleOrderGrabedMessage(long orderId);

    /**
     * 生成选择物流抢单的消息
     * 消息关键字：订单编号
     * @param logisticsInfoId
     * @return
     */
    int handleChooseGrabMessage(long logisticsInfoId);

    /**
     * 生成商品订单线下付款审核的消息
     * @param orderId
     * @param passCheck
     * @param checkComment
     * @return
     */
    int handleProdOfflinePayCheckMessage(Long orderId, int passCheck,String checkComment);

    /**
     * 生成物流订单线下付款审核的消息
     * @param orderId
     * @param passCheck
     * @param checkComment
     * @return
     */
    int handleLogisticsOfflinePayCheckMessage(Long orderId, int passCheck,String checkComment);

    /**
     * 生成贷款的消息
     * @param loanId
     * @return
     */
    int handleLoanCreatedMessage(long loanId);

    /**
     *授信付款驳回的消息
     * @param orderId
     * @return
     */
    int handleLoanSayNoMessage(long orderId);
}
