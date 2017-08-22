package org.wella.service.impl;

import io.wellassist.utils.HttpContextUtils;
import io.wellassist.utils.PageUtils;
import io.wellassist.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wella.common.utils.ConstantUtil;
import org.wella.dao.*;
import org.wella.entity.CreditRecord;
import org.wella.entity.Message;
import org.wella.entity.User;
import org.wella.entity.Userinfo;
import org.wella.service.MessageService;
import org.wella.utils.DateUtils;
import org.wella.utils.MailUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xuqinghuo on 2017/7/25.
 */
@Service("messageServicesk")
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderLogDao orderLogDao;

    @Autowired
    private LogisticsInfoDao logisticsInfoDao;

    @Autowired
    private LoanDao loanDao;

    @Autowired
    private ZorderDao zorderDao;

    @Autowired
    private WaUserDao waUserDao;

    @Autowired
    private BankOrderDao bankOrderDao;

    @Autowired
    private WithdrawDAO withdrawDAO;

    @Autowired
    private CreditDao creditDao;

    @Autowired
    private RepayDao repayDao;

    @Override
    public List<Message> getMessageList(Map<String, Object> map) {
        return messageDao.getMessageList(map);
    }

    @Override
    public List<Message> getMessage(int id) {
        return null;
    }

    @Override
    public void delMessage(Long[] id) {

    }

    @Override
    public void upMessage(Integer id) {

    }


    @Transactional
    @Override
    public void addCreditRecord(CreditRecord creditRecord) {
       try {
           messageDao.addCreditRecord(creditRecord);
       }catch(Exception e){
            e.printStackTrace();
        }

    }



    public List<CreditRecord> getCreditRecordList(Map<String, Object> map) {
        return messageDao.getCreditRecordList(map);
    }


    @Override
    public  Userinfo getCreditRecord(Long id) {
        return messageDao.getCreditRecord(id);
    }

    /*@Override
    public int queryTotal(Map<String, Object> map) {
        *//*return messageDao.queryTotal(map);*//*
    }*/

    @Override
    public int queryRecordCount(Map<String, Object> map) {
        return messageDao.queryRecordCount(map);
    }

    @Override
    public int handleOrderConfirmMessage(long orderId,long orderLogId) {
        Map<String,Object> order=orderDao.singleOrderByPrimaryKey(orderId);
        Map<String,Object> orderLog=orderLogDao.singlePoByPrimaryKey(orderLogId);
        StringBuilder customermessageContent=new StringBuilder();
        customermessageContent.append("您的订单 ").append(order.get("order_no")).append(" 已确认,卖家审批单价 ").append(orderLog.get("order_price")).append(" 元,审批数量 ").append(orderLog.get("order_number")).append(" 吨。");
        if ((int)order.get("is_self_car")==0){
            customermessageContent.append("请及时付款。");
        }else if ((int)order.get("is_self_car")==1){
            customermessageContent.append("请选择物流并及时付款。");
        }
        int res=createOrderMessage((long)order.get("user_id"),"订单确认",customermessageContent.toString());

        StringBuilder sellerMessageContent=new StringBuilder();
        sellerMessageContent.append("您已确认订单 ").append(order.get("order_no")).append(" ,确认单价 ").append(orderLog.get("order_price")).append(" 元,确认数量 ").append(orderLog.get("order_number")).append(" 吨。等待买家付款。");
        res+=createOrderMessage((long)order.get("supplier_id"),"订单生成",sellerMessageContent.toString());
        return res;
    }

    @Override
    public int handleOrderPriceEditMessage(long orderId, long orderLogId) {
        Map<String,Object> order=orderDao.singleOrderByPrimaryKey(orderId);
        Map<String,Object> orderLog=orderLogDao.singlePoByPrimaryKey(orderLogId);
        StringBuilder customermessageContent=new StringBuilder();
        customermessageContent.append("您的订单 ").append(order.get("order_no")).append(" 已调整，实时单价 ").append(orderLog.get("order_price")).append(" 元,实时数量 ").append(orderLog.get("order_number")).append(" 吨。");
        int res=createOrderMessage((long)order.get("user_id"),"订单调整",customermessageContent.toString());
        StringBuilder sellermessageContent=new StringBuilder();
        sellermessageContent.append("您已调整订单 ").append(order.get("order_no")).append(" ，实时单价").append(orderLog.get("order_price")).append(" 元,实时数量 ").append(orderLog.get("order_number")).append(" 吨。");
        res+=createOrderMessage((long)order.get("supplier_id"),"订单调整",sellermessageContent.toString());
        return res;
    }

    public int createSystemMessage(long userId,String title,String content){
        return createMessage(userId,title,(byte)0,content);
    }

    public int createOrderMessage(long userId,String title,String content){
        return createMessage(userId,title,(byte)2,content);
    }

    public int createFinanceMessage(long userId,String title,String content){
        return createMessage(userId,title,(byte)1,content);
    }

    public int createMessage(long userId,String title,byte type,String content){
        Message message=new Message();
        message.setUserId(userId);
        message.setTitle(title);
        message.setType(type);
        message.setContent(content);
        message.setDate(new Date());
        return messageDao.createPo(message);
    }

    @Override
    public int handleOrderGrabedMessage(long orderId) {
        Map<String,Object> order=orderDao.singleOrderByPrimaryKey(orderId);
        StringBuilder content=new StringBuilder();
        content.append("有第三方物流公司为您的订单 ").append(order.get("order_no")).append(" 提供物流服务，请及时选择。");
        return createOrderMessage((long)order.get("user_id"),"物流抢单",content.toString());
    }

    @Override
    public int handleChooseGrabMessage(long logisticsInfoId) {
        Map<String,Object> logisticsInfoView=logisticsInfoDao.singleLogisticsInfoViewByPrimaryKey(logisticsInfoId);
        StringBuilder customerMessageContent=new StringBuilder();
        customerMessageContent.append("您已选择 ").append(logisticsInfoView.get("sender_user_name")).append(" 为订单 ").append(logisticsInfoView.get("order_no")).append(" 提供物流服务，请支付物流订单后，物流公司将前往卖方地址为您载货。");
        int res=createOrderMessage((long)logisticsInfoView.get("customer_user_id"),"物流选择",customerMessageContent.toString());
        StringBuilder senderMessageContent=new StringBuilder();
        senderMessageContent.append("买家已选择 订单编号 ").append(logisticsInfoView.get("order_no")).append(" 您的抢单，请等待买家预付款后，及时去提货地址载货。");
        res+=createOrderMessage((long)logisticsInfoView.get("sender_user_id"),"抢单成功",senderMessageContent.toString());
        return res;
    }

    @Override
    public int handleProdOfflinePayCheckMessage(Long orderId, int passCheck,String checkComment) {
        Map<String,Object> order=orderDao.singleOrderByPrimaryKey(orderId);
        StringBuilder content=new StringBuilder();
        if (passCheck==1){
            content.append("您的订单 ").append(order.get("order_no")).append(" 线下付款已通过管理员审核。");
        }else if (passCheck==0){
            content.append("您的订单 ").append(order.get("order_no")).append(" 提交的线下付款申请未通过管理员审核，理由：").append(checkComment).append(" ，请重新支付订单。");
        }
        return createOrderMessage((long)order.get("user_id"),"订单支付",content.toString());
    }

    @Override
    public int handleLogisticsOfflinePayCheckMessage(Long orderId, int passCheck,String checkComment) {
        Map<String,Object> order=orderDao.singleOrderByPrimaryKey(orderId);
        StringBuilder content=new StringBuilder();
        if (passCheck==1){
            content.append("您的订单 ").append(order.get("order_no")).append(" 物流订单线下付款已通过管理员审核。");
        }else if (passCheck==0){
            content.append("您的订单 ").append(order.get("order_no")).append(" 物流订单提交的线下付款申请未通过管理员审核，理由：").append(checkComment).append(" ，请重新支付订单。");
        }
        return createOrderMessage((long)order.get("user_id"),"订单支付",content.toString());
    }

    @Override
    public int handleLoanCreatedMessage(long loanId) {
        Map<String,Object> loanOrderView=loanDao.singleLoanOrderViewByPrimaryKey(loanId);
        StringBuilder content=new StringBuilder();
        Date interestFreeDate=(Date)loanOrderView.get("interest_free_date");
        Date paymentDate=(Date)loanOrderView.get("payment_date");
        String interestFreeDateStr= DateUtils.format(interestFreeDate,"yyyy 年 MM 月 dd 日");
        String paymentDateStr=DateUtils.format(paymentDate,"yyyy 年 MM 月 dd 日");
        content.append("您的订单 ").append(loanOrderView.get("order_no")).append(" 生成 ").append(loanOrderView.get("loan_money")).append(" 元授信贷款,");
        content.append("账期为 ").append(interestFreeDateStr).append(" ,还款期限为 ").append(paymentDateStr).append(" 。");
        content.append("在账期内完成还款将不计您的利息，还款期限内按").append(loanOrderView.get("lixi_rate")).append("%%/日计息,逾期将按照").append(loanOrderView.get("lixi_rate")).append("%%/日利滚利的方式计息。");
        return createFinanceMessage((long)loanOrderView.get("user_id"),"授信出账",content.toString());
    }

    @Override
    public int handleLoanSayNoMessage(long orderId) {
        Map<String,Object> order=orderDao.singleOrderByPrimaryKey(orderId);
        StringBuilder content=new StringBuilder();
        content.append("您的订单 ").append(order.get("order_no")).append(" 申请授信付款被管理员驳回,订单取消。");
        return createOrderMessage((long)order.get("user_id"),"订单支付",content.toString());
    }

    @Override
    public int handleSendProdMessage(long orderId, long zorderId) {
        Map<String,Object> order=orderDao.singleOrderByPrimaryKey(orderId);
        Map<String,Object> zorder=zorderDao.singleZorderByPrimaryKey(zorderId);
        StringBuilder content=new StringBuilder();
        content.append("您的订单 ").append(order.get("order_no")).append(" 已发送发货批次，发货数量 ").append(zorder.get("zorder_num")).append(" 吨，本批次单价 ").append(zorder.get("zorder_price")).append(" 元/吨。");
        return createOrderMessage((long)order.get("user_id"),"订单发货",content.toString());
    }

    @Override
    public int handleReceiveProdMessage(long orderId, long zorderId) {
        Map<String,Object> order=orderDao.singleOrderByPrimaryKey(orderId);
        Map<String,Object> zorder=zorderDao.singleZorderByPrimaryKey(zorderId);
        StringBuilder content=new StringBuilder();
        content.append("您的订单 ").append(order.get("order_no")).append(" 买家已收到发货批次，收货数量 ").append(zorder.get("zorder_num")).append(" 吨，本批次单价 ").append(zorder.get("zorder_price")).append(" 元/吨。");
        return createOrderMessage((long)order.get("supplier_id"),"订单收货",content.toString());
    }

    @Override
    public int handleSendProdOverMessage(long orderId) {
        Map<String,Object> order=orderDao.singleOrderByPrimaryKey(orderId);
        StringBuilder content=new StringBuilder();
        content.append("您的订单 ").append(order.get("order_no")).append(" 卖家已结束发货。");
        return createOrderMessage((long)order.get("user_id"),"订单发货",content.toString());
    }

    @Override
    public int handleAdminUpdateOrderMessage(long orderId) {
        Map<String,Object> order=orderDao.singleOrderByPrimaryKey(orderId);
        StringBuilder customerContent=new StringBuilder();
        customerContent.append("您的订单 ").append(order.get("order_no")).append(" 管理员已修改相关数据，如有疑问请联系平台管理员。");
        StringBuilder sellerContent=new StringBuilder();
        sellerContent.append("订单 ").append(order.get("order_no")).append(" 管理员已修改相关数据，如有疑问请联系平台管理员。");
        int res=createOrderMessage((long)order.get("supplier_id"),"订单修改", sellerContent.toString());
        res+=createOrderMessage((long)order.get("user_id"),"订单修改",customerContent.toString());
        return res;
    }

    @Override
    public int handleReceiveProdOverMessage(long orderId) {
        Map<String,Object> order=orderDao.singleOrderByPrimaryKey(orderId);
        StringBuilder customerContent=new StringBuilder();
        customerContent.append("您的订单 ").append(order.get("order_no")).append(" 已收货完成。");
        StringBuilder sellerContent=new StringBuilder();
        sellerContent.append("您的订单 ").append(order.get("order_no")).append(" 买家已完成收货。");
        int res=createOrderMessage((long)order.get("user_id"),"订单收货",customerContent.toString());
        res+=createOrderMessage((long)order.get("supplier_id"),"订单收货",sellerContent.toString());
        return res;
    }

    @Override
    public void handleRegisterReviewMessage(String userEmail, String reviewComment, boolean passReview) {
        if (passReview){
            String content="<html><head></head><body><h1>您的维助供应链平台账户已通过审核</h1><h1>点击进入<a href='"+ ConstantUtil.SERVER_HOST+"'  target = '_blank'>维助供应链</a></h1></body></html>";
            new Thread(new MailUtil(userEmail, content)).start();
        }else if (!passReview){
            String content="<html><head></head><body><h1>对不起，您的维助供应链平台账户未通过审核</h1><h1>审核意见："+reviewComment+"</h1><h1>点击进入<a href='"+ ConstantUtil.SERVER_HOST+"'  target = '_blank'>维助供应链</a></h1></body></html>";
            new Thread(new MailUtil(userEmail, content)).start();
        }
    }

    @Override
    public int handleResetPasswordMessage(long userId) {
        Map<String,Object> user=waUserDao.singleUserByPrimaryKey(userId);
        StringBuilder content=new StringBuilder();
        String now=DateUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss");
        content.append(user.get("user_name")).append(" 您好,您的密码已被重置,重置密码：123456,操作时间： ").append(now).append(",请您及时修改密码。");
        return createSystemMessage(userId,"个人信息",content.toString());
    }

    @Override
    public int handleAdminUpdateUserinfoMessage(long userId) {
        Map<String,Object> user=waUserDao.singleUserByPrimaryKey(userId);
        StringBuilder content=new StringBuilder();
        content.append(user.get("user_name")).append(" 您好，你的个人信息已被管理员修改,请您及时校验。");
        return createSystemMessage(userId,"个人信息",content.toString());
    }

    @Override
    public int handleRechargeApplyMessage(Long userId, BigDecimal rechargeMoney) {
        StringBuilder content=new StringBuilder();
        content.append("金额充值 ").append(rechargeMoney).append(" 元正在处理中。");
        return createFinanceMessage(userId,"资金充值",content.toString());
    }

    @Override
    public int handleRechargeHandleMessage(long bankOrderId) {
        Map<String,Object> bankOrder=bankOrderDao.singlePoByPrimaryKey(bankOrderId);
        StringBuilder content=new StringBuilder();
        content.append("金额充值 ").append(bankOrder.get("zf_money")).append(" 元已到账。");
        return createFinanceMessage((long)(bankOrder.get("user_id")),"资金充值",content.toString());
    }

    @Override
    public int handleWithdrawHandleMessage(long withdrawId, int withdrawState) {
        Map<String,Object> withdraw=withdrawDAO.singlePoByPrimaryKey(withdrawId);
        StringBuilder content=new StringBuilder();
        if (withdrawState==2){
            content.append("金额提现 ").append(withdraw.get("withdraw_money")).append(" 元已到账。");
        }else if (withdrawState==-2){
            content.append("金额提现 ").append(withdraw.get("withdraw_money")).append(" 已被拒绝，如有疑问请联系管理员");
        }
        return createFinanceMessage((long)withdraw.get("user_id"),"资金提现",content.toString());
    }

    @Override
    public int handleWithdrawApplyMessage(Long userId, BigDecimal withdrawMoney) {
        StringBuilder content=new StringBuilder();
        content.append("金额提现 ").append(withdrawMoney).append(" 元正在处理中。");
        return createFinanceMessage(userId,"资金提现",content.toString());
    }

    @Override
    public int handleCreditCheck(long creditId, int flag, String comment) {
        Map<String,Object> credit=creditDao.singleCreditByPrimaryKey(creditId);
        StringBuilder content=new StringBuilder();
        String creditApplyDateStr=DateUtils.format((Date)credit.get("credit_apply_date"),DateUtils.DATE_TIME_PATTERN);
        if (flag==1){
            content.append("您 ").append(creditApplyDateStr).append(" 提交的 ").append(credit.get("credit_money")).append(" 元授信申请已通过管理员审核，");
            content.append("审批额度为 ").append(credit.get("credit_sj_money")).append(" 元，您此前的授信额度已失效，以该额度为准。");
        }else if (flag==-1){
            content.append("您 ").append(creditApplyDateStr).append(" 提交的 ").append(credit.get("credit_money")).append(" 元授信申请没有通过审核，");
            content.append("理由： ").append(comment);
        }
        return createFinanceMessage((long)credit.get("user_id"),"授信申请",content.toString());
    }

    @Override
    public int handleRepayLoanMessage(long loanId, Long repayId) {
        Map<String,Object> loan=loanDao.singleLoanByPrimaryKey(loanId);
        Map<String,Object> repay=repayDao.singleRepayByPrimaryKey(repayId);
        String repayDateStr=DateUtils.format((Date)repay.get("repay_date"),DateUtils.DATE_TIME_PATTERN);
        BigDecimal repayMoney=(BigDecimal) repay.get("repay_money");
        BigDecimal repayInterestMoney=(BigDecimal)repay.get("repay_interest_money");
        BigDecimal repaytotal=repayMoney.add(repayInterestMoney);
        BigDecimal remainRepayMoney=(BigDecimal) loan.get("remain_repay_money");
        StringBuilder content=new StringBuilder();
        content.append("您在 ").append(repayDateStr).append(" 还款 ").append(repaytotal).append(" 元。其中还款本金 ").append(repayInterestMoney).append(" 元，");
        content.append("还款利息 ").append(repayInterestMoney).append(" 元。该笔贷款当前剩余尾款").append(remainRepayMoney).append(" 元。");
        return createFinanceMessage((long)loan.get("user_id"),"授信还款",content.toString());
    }

    @Override
    public int handleLoanRepayoffMessage(long loanId) {
        Map<String,Object> loan=loanDao.singleLoanByPrimaryKey(loanId);
        String applyDateStr=DateUtils.format((Date)loan.get("apply_date"),DateUtils.DATE_PATTERN);
        BigDecimal loanMoney=(BigDecimal)loan.get("loanMoney");
        StringBuilder content=new StringBuilder();
        content.append("您在 ").append(applyDateStr).append(" 申请的 ").append(loanMoney).append(" 元授信贷款已全部还清。");
        return createFinanceMessage((long)loan.get("user_id"),"授信还款",content.toString());
    }

    @Override
    public int unreadMsgCount(long userId) {
        return messageDao.unreadMsgCount(userId);
    }

    @Override
    public PageUtils systemicMesList(Map param) {
        Query query=new Query(param);
        User user=(User) HttpContextUtils.getAttribute("user");
        query.put("userId",user.getUserId());
        query.put("isDelete",0);
        query.put("orderBy","Field(is_read,0,1),id desc");
        query.put("inType","(0,2)");
        List list=messageDao.listPoByConditions(query);
        int totalCount=messageDao.listPoByConditionsCount(query);
        PageUtils page=new PageUtils(list, totalCount, query.getLimit(), query.getPage());
        return page;
    }

    @Override
    public PageUtils financeMesList(Map param) {
        Query query=new Query(param);
        User user=(User) HttpContextUtils.getAttribute("user");
        query.put("userId",user.getUserId());
        query.put("isDelete",0);
        query.put("orderBy","Field(is_read,0,1),id desc");
        query.put("type",1);
        List list=messageDao.listPoByConditions(query);
        int totalCount=messageDao.listPoByConditionsCount(query);
        PageUtils page=new PageUtils(list, totalCount, query.getLimit(), query.getPage());
        return page;
    }

    @Override
    public PageUtils shitMesList(Map param) {
        Query query=new Query(param);
        User user=(User) HttpContextUtils.getAttribute("user");
        query.put("userId",user.getUserId());
        query.put("isDelete",1);
        query.put("orderBy","id desc");
        List list=messageDao.listPoByConditions(query);
        int totalCount=messageDao.listPoByConditionsCount(query);
        PageUtils page=new PageUtils(list, totalCount, query.getLimit(), query.getPage());
        return page;
    }

    @Override
    public Message singleMessageByPk(long id) {
        Message message=messageDao.singlePoByPk(id);
        return message;
    }

    @Override
    public void delete1Msg(long id) {
        Map map=new HashMap();
        map.put("id",id);
        map.put("isDelete",(byte)1);
        messageDao.update(map);
    }

    @Override
    public void deleteMsgBatch(String ids) {
        Map map=new HashMap();
        StringBuilder idA=new StringBuilder();
        idA.append("(").append(ids).append(")");
        map.put("ids",idA.toString());
        map.put("isDelete",(byte)1);
        map.put("deleteTime",new Date());
        messageDao.updateByConditions(map);
    }

    @Override
    public void readMsg(long id) {
        Map update=new HashMap();
        update.put("id",id);
        update.put("isRead",(byte)1);
        messageDao.update(update);
    }
}
