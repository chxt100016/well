package org.wella.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wella.entity.Bankcard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/6/8.
 */
@Repository
public interface BankOrderDao {

    List<Map<String,Object>> listPoByConditions(Map params);

    int listPoByConditionsCount(Map params);

    Map<String,Object> singlePoByPrimaryKey(@Param("bankOrderId") long bankOrderId);

    List getCzList(Map param);

    int getCzListCount(Map param);

    Map getCzMoneyInfo(Map param);
}
