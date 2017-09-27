package org.wella.platform.service;

import java.util.Map;

/**
 * Created by liuwen on 2017/5/18.
 * 后台授信管理服务接口
 */
public interface CreditService {
    Map<String,Object> findCreditApplyDetailInfo(long creditId);

    void checkCreditApply(Map<String, Object> params);

    void assignSubmit(long loanId,long creditorId);

    void assignRollBack(long loanId,long mgrId,String ip);

    /**
     * 驳回贷款申请
     * @param loanId loanId
     */
    void loanSayno(long loanId);
}
