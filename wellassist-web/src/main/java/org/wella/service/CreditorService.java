package org.wella.service;

import io.wellassist.utils.Query;
import org.wella.entity.CreditorAuthenticInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/21.
 */
public interface CreditorService {
    /**
     * 得到已认证的放款方list
     * @return
     */
    List<Map<String,Object>> findCreditorList();

    /**
     * 放款方通过余额放款
     * @param loanId
     * @param creditorUserId
     * @param paymentDays
     * @param operateIp
     * @return
     */
    int payLoan(long loanId,long creditorUserId,int paymentDays,String operateIp) throws Exception;

    /**
     *放款方接受放款指派
     * test:暂定还款期为30天，免息期为7天，放款方利率=平台利率*0.9
     * @param paymentDays 还款期限
     * @return 0 失败 1成功
     */
    int acceptLoan(long loanId,long creditorUserId,int paymentDays,String operateIp);

    /**
     * 放款方资质状态
     * @param userId
     * @return 0:未通过，1：审核中，2：已通过
     */
    int findCreditorState(long userId);

    /**
     * 放款方提交资质审核
     * @param creditorAuthenticInfo
     */
    void qualityApply(CreditorAuthenticInfo creditorAuthenticInfo);

    /**
     * 得到待审核的那条wa_creditor_authentic_info 记录
     * @param userId
     * @return
     */
    CreditorAuthenticInfo getAuthenticingInfo(long userId);

    /**
     * 得到已被指派未被确认的loans
     * @param query:creditUserId,limit,offset
     * @return
     */
    List<Map<String,Object>> getAssignLoans(Map query);

    /**
     * 得到已被指派未被确认的loans count,分页参数
     * @param query:creditUserId,limit,offset
     * @return
     */
    int getAssignLoansCount(Map query);

    /**
     * 得到被指派的贷款 wa_loan_assign_info记录
     * @param loanId
     * @param creditorUserId
     * @return
     */
    Map<String,Object> getLoanAssignInfo(long loanId,long creditorUserId);

    /**
     *放款方拒绝放款指派
     */
    void refuseLoan(long loanId, long creditorUserId,String comment, String ip);

    /**
     * 放款方展示各种列表
     * @param params
     * @return
     */
    List<Map<String,Object>> listLoanOrderViewByConditions(Map params);

    /**
     * 放款方展示各种列表 count 分页参数
     * @param params
     * @return
     */
    int listLoanCount(Map params);

    /**
     * 得到该贷款最近的一次repay 记录
     * @param loanId
     * @return
     */
    Map<String,Object> getLatestRepay(long loanId);

    /**
     * 已还款列表，加上字段：latestRepayDate
     * @param query
     * @return
     */
    List<Map<String,Object>> repayOffList(Query query);



    List<BigDecimal> lendingAmount(Map<String,Object> map);


    List<BigDecimal> interest(Map<String,Object> map);









}
