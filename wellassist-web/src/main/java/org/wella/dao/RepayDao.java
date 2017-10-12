package org.wella.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wella.entity.Repay;

import java.util.List;
import java.util.Map;


/**
 * Created by liuwen on 2017/6/8.
 */
@Repository
public interface RepayDao {

    int createRepay(Repay repay);

    List<Map<String,Object>> listRepayByConditions(Map<String, Object> query);

    Map<String,Object> singleRepayByConditions(Map<String, Object> query);

    Map<String,Object> singleRepayByPrimaryKey(@Param("repayId") Long repayId);
}
