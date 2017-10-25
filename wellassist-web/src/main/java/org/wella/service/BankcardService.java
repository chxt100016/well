package org.wella.service;


import org.wella.entity.Bankcard;

import java.util.List;

/**
 * Created by ailing on 2017/7/25.
 */
public interface BankcardService {

    void addBankcard(Bankcard bankcard);

    void delBankcard(long bankcardId);

    List<Bankcard> getCards(long userId);

    Bankcard getCard(long bankcardId);

}
