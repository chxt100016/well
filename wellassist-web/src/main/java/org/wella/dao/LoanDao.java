package org.wella.dao;

import io.wellassist.utils.Query;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/6/8.
 */
@Repository
public interface LoanDao {

    List listLoanOrderViewByConditions(Map<String,Object> params);

    int listLoanOrderViewByConditionsCount(Map<String,Object> params);

    Map<String,Object> singleLoanOrderViewByPrimaryKey(long loanId);

    Integer updateLoanByPrimaryKey(Map<String, Object> param);
}
