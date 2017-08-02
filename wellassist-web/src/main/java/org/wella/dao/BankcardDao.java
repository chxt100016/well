package org.wella.dao;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wella.entity.Bankcard;

import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/6/8.
 */
@Repository
public interface BankcardDao {
    List<Bankcard> getCardListByUserId(long userId);



    long addCard(Map<String,Object> map);

    int delCard(long bankcardId);
}
