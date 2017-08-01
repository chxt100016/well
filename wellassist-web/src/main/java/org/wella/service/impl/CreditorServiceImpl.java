package org.wella.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.CreditorAuthenticInfoDao;
import org.wella.dao.LoanAssignInfoDao;
import org.wella.dao.LoanDao;
import org.wella.dao.WaUserDao;
import org.wella.entity.CreditorAuthenticInfo;
import org.wella.service.CreditorService;
import org.wella.utils.DateUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/21.
 */

@Service("creditorServiceImpl")
public class CreditorServiceImpl implements CreditorService{

    @Autowired
    private WaUserDao waUserDao;

    @Autowired
    private LoanDao loanDao;

    @Autowired
    private LoanAssignInfoDao loanAssignInfoDao;

    @Autowired
    private CreditorAuthenticInfoDao creditorAuthenticInfoDao;


    /**
     * 先忽略放款方资质审核，以后改这个接口
     * @return
     */
    @Override
    public List<Map<String, Object>> findCreditorList() {
        Map<String,Object> param=new HashMap();
        param.put("userType",2);
        param.put("userState",1);
        param.put("creditorState",2);
        List<Map<String,Object>> creditors=waUserDao.listUserAttachUserinfoByConditions(param);
        ConvertUtil.convertDataBaseMapToJavaMap(creditors);
        return creditors;
    }

    @Override
    @Transactional
    public int acceptLoan(long loanId, long creditorUserId, int paymentDays,String operateIp) {
        Date now=new Date();

        Map<String,Object> loan=loanDao.singleLoanByPrimaryKey(loanId);
        if(creditorUserId!=(long)loan.get("credit_user_id")){
            return 0;
        }

        // update table wa_loan
        Map<String,Object> updateloan=new HashMap();
        updateloan.put("loanId",loanId);
        updateloan.put("loanDate",now);
        updateloan.put("interestFreeDate", DateUtils.addDays(now,7));
        updateloan.put("paymentDate",DateUtils.addDays(now,37));
        BigDecimal lixiRate=(BigDecimal) loan.get("lixi_rate");
        BigDecimal lixiRateFkf=lixiRate.multiply(new BigDecimal(0.9));
        updateloan.put("lixiRateFkf",lixiRateFkf);
        updateloan.put("loanState",2);
        updateloan.put("remainRepayMoney",loan.get("loan_money"));
        loanDao.updateLoanByPrimaryKey(updateloan);

        //update table wa_loan_assign_info
        Map<String,Object> query=new HashMap<>();
        query.put("loanId",loanId);
        query.put("creditorId",creditorUserId);
        query.put("state",0);
        Map<String,Object> loanAssignInfo=loanAssignInfoDao.singleLoanAssignInfoByConditions(query);

        Map<String,Object> updateloanAssignInfo=new HashMap<>();
        updateloanAssignInfo.put("loanAssignInfoId",loanAssignInfo.get("loan_assign_info_id"));
        updateloanAssignInfo.put("state",1);
        updateloanAssignInfo.put("operateDate",now);
        updateloanAssignInfo.put("operateIp",operateIp);
        loanAssignInfoDao.updateByPrimaryKey(updateloanAssignInfo);

        return 1;
    }

    @Override
    public int findCreditorState(long userId) {
        Map<String,Object> user=waUserDao.singleUserByPrimaryKey(userId);
        int creditorState=(int)user.get("creditor_state");
        return creditorState;
    }

    @Override
    @Transactional
    public void qualityApply(CreditorAuthenticInfo creditorAuthenticInfo) {
        creditorAuthenticInfoDao.save(creditorAuthenticInfo);
        Map<String,Object> updateuser=new HashMap<>();
        updateuser.put("userId",creditorAuthenticInfo.getUserId());
        updateuser.put("creditorState",1);
        waUserDao.updateUserByUserId(updateuser);
    }
}
