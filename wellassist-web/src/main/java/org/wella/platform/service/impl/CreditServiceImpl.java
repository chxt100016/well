package org.wella.platform.service.impl;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.CreditDao;
import org.wella.dao.CreditInfoDao;
import org.wella.dao.LoanAssignInfoDao;
import org.wella.dao.LoanDao;
import org.wella.entity.CreditInfo;
import org.wella.platform.service.CreditService;
import org.wella.service.CustomerService;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/18.
 */
@Service("creditServiceImpl")
public class CreditServiceImpl implements CreditService{

    @Autowired
    private CreditDao creditDao;
    @Autowired
    private CreditInfoDao creditInfoDao;
    @Autowired
    private CustomerService customerServiceImpl;
    @Autowired
    private LoanDao loanDao;
    @Autowired
    private LoanAssignInfoDao loanAssignInfoDao;


    @Override
    public Map<String, Object> findCreditApplyDetailInfo(long creditId) {
        Map<String,Object> res=creditDao.singleCreditInfoViewByPrimaryKey(creditId);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        BigDecimal currentCreditSjMoney=customerServiceImpl.getUserCreditSjMoney((long)res.get("userId"));
        res.put("currentCreditSjMoney",currentCreditSjMoney);
        return res;
    }

    @Override
    @Transactional
    public void checkCreditApply(Map<String, Object> params) {
        long creditId=(long)params.get("creditId");
        BigDecimal creditSjMoney=(BigDecimal)params.get("creditSjMoney");
        int flag=(int)params.get("flag");
        long creditUserId=(long)params.get("creditUserId");
        String ip=(String)params.get("ip");
        Date now=new Date();

        Map<String,Object> credit=creditDao.singleCreditByPrimaryKey(creditId);
        long userId=(long)credit.get("user_id");

        Map<String,Object> updateCreditMap=new HashMap<>();
        updateCreditMap.put("creditId",creditId);
        updateCreditMap.put("creditUserId",creditUserId);


        CreditInfo ci=new CreditInfo();
        ci.setCreditId(creditId);
        ci.setMgrUserId(creditUserId);
        ci.setAdminUserId(creditUserId);
        ci.setMgrDate(now);
        ci.setMgrIp(ip);

        if (flag == 1){
            //update wa_credit table
            //授信期限设置为3个月
            Calendar rightnow=Calendar.getInstance();
            rightnow.add(Calendar.MONTH,3);
            Date creditDeadline=rightnow.getTime();
            updateCreditMap.put("creditStartDate",now);
            updateCreditMap.put("creditDeadline",creditDeadline);

            updateCreditMap.put("creditSjMoney",creditSjMoney);
            updateCreditMap.put("creditState",1);

            //如果用户之前有授信额度，则将之前的那条记录state修改为2（已提额）
            Map<String,Object> oldcredit=customerServiceImpl.getSjCredit(userId);
            if (null != oldcredit && oldcredit.size()>0){
                Map<String,Object> updateOldcredit=new HashMap<>();
                updateOldcredit.put("creditId",oldcredit.get("credit_id"));
                updateOldcredit.put("creditState",2);
                creditDao.updateByPrimaryKey(updateOldcredit);
            }

            //insert wa_credit_info table
            ci.setCreditState((byte)1);
            ci.setContent("授信通过，额度为"+creditSjMoney.toString());

            //update wa_user user_credit_money
            customerServiceImpl.updateUserCreditMoney(userId,creditSjMoney);
        } else if (flag == -1){
            //update wa_credit table
            updateCreditMap.put("creditState",-1);

            //insert wa_credit_info table;
            ci.setCreditState((byte)-1);
            ci.setContent("授信拒绝");
        }

        creditDao.updateByPrimaryKey(updateCreditMap);
        creditInfoDao.createCreditInfo(ci);

    }

    /**
     * 授信指派
     * @param loanId
     * @param creditorId
     */
    @Override
    @Transactional
    public void assignSubmit(long loanId, long creditorId) {
        Map<String,Object> updateloan=new HashMap();
        updateloan.put("loanId",loanId);
        updateloan.put("creditUserId",creditorId);
        updateloan.put("loanState",1);
        loanDao.updateLoanByPrimaryKey(updateloan);

        Map<String,Object> loanAssignInfo=new HashMap<>();
        loanAssignInfo.put("loanId",loanId);
        loanAssignInfo.put("creditorId",creditorId);
        loanAssignInfo.put("state",0);
        loanAssignInfo.put("createDate",new Date());
        loanAssignInfoDao.insert(loanAssignInfo);
    }

    /**
     * 授信撤回
     * @param loanId
     */
    @Override
    @Transactional
    public void assignRollBack(long loanId,long mgrId,String ip) {
        Map<String,Object> updateloan=new HashMap();
        updateloan.put("loanId",loanId);
        updateloan.put("creditUserId",0);
        updateloan.put("loanState",0);
        loanDao.updateLoanByPrimaryKey(updateloan);

        Map<String,Object> queryLoanAssignInfo=new HashMap<>();
        queryLoanAssignInfo.put("loanId",loanId);
        queryLoanAssignInfo.put("state",0);
        Map<String,Object> loanAssignInfo=loanAssignInfoDao.singleLoanAssignInfoByConditions(queryLoanAssignInfo);

        Map<String,Object> updateLoanAssignInfo=new HashMap();
        updateLoanAssignInfo.put("loanAssignInfoId",loanAssignInfo.get("loan_assign_info"));
        updateLoanAssignInfo.put("state",-2);
        updateLoanAssignInfo.put("operateDate",new Date());
        updateLoanAssignInfo.put("operateIp",ip);
        updateLoanAssignInfo.put("mgrId",mgrId);
        loanAssignInfoDao.updateByPrimaryKey(updateLoanAssignInfo);
    }


}
