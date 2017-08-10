package org.wella.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wella.entity.Bankcard;

import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/6/8.
 */
@Repository
public interface UserMoneyDao {

    void update(Map map);

    Map<String,Object> singlePoByPrimaryKey(@Param("moneyId") long moneyId);
}