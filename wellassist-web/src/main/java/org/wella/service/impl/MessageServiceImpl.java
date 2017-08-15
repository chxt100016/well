package org.wella.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wella.dao.LogisticsInfoDao;
import org.wella.dao.MessageDao;
import org.wella.dao.OrderDao;
import org.wella.dao.OrderLogDao;
import org.wella.entity.CreditRecord;
import org.wella.entity.Message;
import org.wella.entity.Userinfo;
import org.wella.service.MessageService;

import java.util.Date;
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

    public int createOrderMessage(long userId,String title,String content){
        return createMessage(userId,title,(byte)2,content);
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
}
