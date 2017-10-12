package org.wella.service;

import io.wellassist.utils.Query;
import org.wella.entity.CreditorAuthenticInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by ailing on 2017/7/21.
 */
public interface CreditorService {

    /**
     * 得到已认证的放款方list
     * @return 已认证的放款方list
     */
    List<Map<String,Object>> findCreditorList();

    /**
     * 放款方通过余额放款
     * @param loanId wa_loan主键
     * @param creditorUserId 放款方用户id
     * @param paymentDays 放款期限
     * @param operateIp 操作ip
     * @return 数据库更新记录数
     */
    int payLoan(long loanId,long creditorUserId,int paymentDays,String operateIp) throws Exception;

    /**
     *放款方接受放款指派
     * test:暂定还款期为30天，免息期为7天，放款方利率=平台利率*0.9
     * @param paymentDays 还款期限
     * @return 0 失败 1成功
     */
    int acceptLoan(long loanId,long creditorUserId,int paymentDays,String operateIp) throws Exception;

    /**
     * 放款方资质状态
     * @param userId userId
     * @return 0:未通过，1：审核中，2：已通过
     */
    int findCreditorState(long userId);

    /**
     * 放款方提交资质审核
     * @param creditorAuthenticInfo 认证资质信息pojo
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
     * @return 已被指派未被确认的loans
     */
    List<Map<String,Object>> getAssignLoans(Map query);

    /**
     * 得到已被指派未被确认的loans count,分页参数
     * @param query:creditUserId,limit,offset
     * @return 被指派贷款总记录条数
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
     * 放款方拒绝放款指派
     * @param loanId loanId
     * @param creditorUserId 放款方用户id
     * @param comment 拒绝理由
     * @param ip 操作ip
     */
    void refuseLoan(long loanId, long creditorUserId,String comment, String ip);

    /**
     * 放款方展示各种列表
     * @param params 查询参数
     * @return 放款方展示各种列表
     */
    List<Map<String,Object>> listLoanOrderViewByConditions(Map params);

    /**
     * 放款方展示各种列表 count
     * @param params 分页参数
     * @return 列表总记录数
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
     * @param query 查询参数
     * @return 已还款列表
     */
    List<Map<String,Object>> repayOffList(Query query);


    /**
     * 贷款总额
     * @param map echart查询参数
     * @return 贷款总额
     */
    List<BigDecimal> lendingAmount(Map<String,Object> map);

    /**
     * 利息总额
     * @param map echart查询参数
     * @return 利息总额
     */
    List<BigDecimal> interest(Map<String,Object> map);









}
