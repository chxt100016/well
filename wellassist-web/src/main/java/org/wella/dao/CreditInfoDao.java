package org.wella.dao;

import org.springframework.stereotype.Repository;
import org.wella.entity.CreditInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/6/8.
 */
@Repository
public interface CreditInfoDao {


    void createCreditInfo(CreditInfo ci);
}
