package org.wella.dao;

import org.springframework.stereotype.Repository;
import org.wella.entity.UserMoneyInfo;


/**
 * Created by ailing on 2017/6/8.
 */
@Repository
public interface UserMoneyInfoDao {
    void create(UserMoneyInfo userMoneyInfo);
}
