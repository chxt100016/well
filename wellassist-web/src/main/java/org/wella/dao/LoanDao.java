package org.wella.dao;

import io.wellassist.utils.Query;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wella.entity.Loan;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/6/8.
 */
@Repository
public interface LoanDao {

    void create(Loan loan);

    List listLoanOrderViewByConditions(Map<String,Object> params);

    int listLoanOrderViewByConditionsCount(Map<String,Object> params);

    //query loan_order_view 视图
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

    /**
     * 贷款盈利总额
     * @return 盈利总额
     */
    BigDecimal creditPrfitSum();
}
