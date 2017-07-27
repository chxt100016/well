package org.wella.dao;

import org.springframework.stereotype.Repository;
import org.wella.entity.Repay;


/**
 * Created by liuwen on 2017/6/8.
 */
@Repository
public interface RepayDao {

    void createRepay(Repay repay);
}
