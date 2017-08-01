package org.wella.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wella.dao.MessageDao;
import org.wella.entity.CreditRecord;
import org.wella.entity.Message;
import org.wella.entity.Userinfo;
import org.wella.service.MessageService;

import java.util.List;
import java.util.Map;

/**
 * Created by xuqinghuo on 2017/7/25.
 */
@Service("messageServicesk")
public class MessageServiceImpl implements MessageService{


    @Autowired
    private MessageDao messageDaosk;

    @Override
    public List<Message> getMessageList(Map<String, Object> map) {
        return messageDaosk.getMessageList(map);
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
           messageDaosk.addCreditRecord(creditRecord);
       }catch(Exception e){
            e.printStackTrace();
        }

    }



    public List<CreditRecord> getCreditRecordList(Map<String, Object> map) {
        return messageDaosk.getCreditRecordList(map);
    }


    @Override
    public  Userinfo getCreditRecord(Long id) {
        return messageDaosk.getCreditRecord(id);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return messageDaosk.queryTotal(map);
    }

    @Override
    public int queryRecordCount(Map<String, Object> map) {
        return messageDaosk.queryRecordCount(map);
    }
}
