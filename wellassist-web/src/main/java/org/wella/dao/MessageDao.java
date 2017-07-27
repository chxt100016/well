package org.wella.dao;

import io.wellassist.dao.BaseDao;
import org.springframework.stereotype.Repository;
import org.wella.entity.CreditRecord;
import org.wella.entity.Message;

import java.util.List;
import java.util.Map;

/**
 * Created by xuqinghuo on 2017/7/25.
 */
public interface MessageDao extends BaseDao<Message> {

    List<Message> getMessageList(Map<String, Object> map);

    List<Message> getMessage(int id);

    void delMessage(Long[] id);

    void upMessage(Integer id);

    List<CreditRecord> getCreditRecordList(Map<String, Object> map);


    int queryRecordCount(Map<String, Object> map);

}
