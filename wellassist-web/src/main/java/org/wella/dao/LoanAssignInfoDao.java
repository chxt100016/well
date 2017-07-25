package org.wella.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/6/8.
 */
@Repository
public interface LoanAssignInfoDao {

    Integer insert(Map param);

    Map<String,Object> singleLoanAssignInfoByConditions(Map<String, Object> query);

    void updateByPrimaryKey(Map<String, Object> param);
}
