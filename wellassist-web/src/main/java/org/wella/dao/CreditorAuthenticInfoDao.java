package org.wella.dao;

import org.springframework.stereotype.Repository;
import org.wella.entity.CreditorAuthenticInfo;


/**
 * Created by liuwen on 2017/6/8.
 */
@Repository
public interface CreditorAuthenticInfoDao {

    void save(CreditorAuthenticInfo creditorAuthenticInfo);

}
