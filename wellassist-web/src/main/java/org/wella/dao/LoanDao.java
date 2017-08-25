package org.wella.dao;

import io.wellassist.utils.Query;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
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

    Map<String,Object> singleLoanByPrimaryKey(@Param("loanId") long loanId);

    BigDecimal getLoansSum(@Param("userId") Long userId);

    List<Map<String,Object>> listLoanByConditions(Map params);

    int listLoanByConditionsCount(Map params);

    Map<String,Object> singleLoanFkViewByPrimaryKey(long loanId);


    List<Map<Integer,Object>>loanAmount(Map map);


    List<Map<Integer,Object>> interest(Map map);


    List<Map<Integer,Object>> lendingAmount(Map<String,Object> map);


    List<Map<Integer,Object>> creditorInterest(Map<String,Object> map);

}
