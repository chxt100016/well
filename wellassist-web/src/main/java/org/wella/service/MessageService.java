package org.wella.service;

import org.springframework.stereotype.Service;
import org.wella.entity.Message;

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

}
