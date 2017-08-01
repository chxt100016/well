package org.wella.dao;

import org.springframework.stereotype.Repository;
import org.wella.entity.CreditorAuthenticInfo;

import java.util.Map;


/**
 * Created by liuwen on 2017/6/8.
 */
@Repository
public interface CreditorAuthenticInfoDao {

    void save(CreditorAuthenticInfo creditorAuthenticInfo);

    CreditorAuthenticInfo singlePoByConditons(Map<String, Object> query);

    void update(Map<String, Object> updatecreditorauthInfo);
}
