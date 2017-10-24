package org.wella.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wella.dao.BankcardDao;
import org.wella.entity.Bankcard;
import org.wella.service.BankcardService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ailing on 2017/7/25.
 */
@Service("bankcardServiceImpl")
public class BankcardServiceImpl implements BankcardService {

    @Autowired
    private BankcardDao bankcardDao;

    @Override
    public void addBankcard(Bankcard bankcard) {
        bankcardDao.create(bankcard);
    }

    @Override
    public void delBankcard(long bankcardId) {
        Map<String,Object> update=new HashMap();
        update.put("bankcardId",bankcardId);
        update.put("state",-1);
        bankcardDao.update(update);
    }

    @Override
    public List<Bankcard> getCards(long userId) {
        Map<String,Object> query=new HashMap<>();
        query.put("userId",userId);
        List<Bankcard> list=bankcardDao.queryList(query);
        return list;
    }
}
