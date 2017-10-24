package org.wella.dao;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wella.entity.Bankcard;

import java.util.List;
import java.util.Map;

/**
 * Created by ailing on 2017/6/8.
 */
@Repository
public interface BankcardDao {
    List<Bankcard> getCardListByUserId(long userId);

    /**
     * 遗留代码，要删
     * @param map
     * @return
     */
    long addCard(Map<String,Object> map);

    int delCard(long bankcardId);

    void create(Bankcard bankcard);
}
