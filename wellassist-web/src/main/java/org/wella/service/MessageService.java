package org.wella.service;

import org.wella.entity.CreditRecord;
import org.wella.entity.Message;
import org.wella.entity.Userinfo;

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


    int queryTotal(Map<String, Object> map);

    int queryRecordCount(Map<String, Object> map);

}
