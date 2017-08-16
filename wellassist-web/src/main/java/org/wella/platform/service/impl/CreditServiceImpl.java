package org.wella.platform.service.impl;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.*;
import org.wella.entity.CreditInfo;
import org.wella.entity.Message;
import org.wella.platform.service.CreditService;
import org.wella.service.CustomerService;
import org.wella.service.MessageService;

import java.math.BigDecimal;
import java.util.*;

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
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private WaUserDao waUserDao;
    @Autowired
    private OrderTransDao orderTransDao;
    @Autowired
    private LogisticsTransDao logisticsTransDao;
    @Autowired
    private UserMoneyDao userMoneyDao;
    @Autowired
    private MessageService messageServicesk;


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
        String comment=(String)params.get("comment");
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
                updateOldcredit.put("creditId",oldcredit.get("creditId"));
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
            updateCreditMap.put("comment",comment);

            //insert wa_credit_info table;
            ci.setCreditState((byte)-1);
            ci.setContent("授信拒绝");
        }

        creditDao.updateByPrimaryKey(updateCreditMap);
        creditInfoDao.createCreditInfo(ci);
        messageServicesk.handleCreditCheck(creditId,flag,comment);
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
        updateLoanAssignInfo.put("loanAssignInfoId",loanAssignInfo.get("loan_assign_info_id"));
        updateLoanAssignInfo.put("state",-2);
        updateLoanAssignInfo.put("operateDate",new Date());
        updateLoanAssignInfo.put("operateIp",ip);
        updateLoanAssignInfo.put("mgrId",mgrId);
        loanAssignInfoDao.updateByPrimaryKey(updateLoanAssignInfo);
    }

    @Override
    @Transactional
    public void loanSayno(long loanId) {
        Map<String,Object> loanFkView=loanDao.singleLoanFkViewByPrimaryKey(loanId);
        Map<String,Object> loan=loanDao.singleLoanByPrimaryKey(loanId);
        long orderId=(long)loanFkView.get("order_id");
        long moneyId=(long)loanFkView.get("money_id");
        long userId=(long)loan.get("user_id");
        Map<String,Object> user=waUserDao.singleUserByPrimaryKey(userId);

        Map query=new HashMap();
        query.put("orderId",orderId);
        List<Map<String,Object>> loanOrderViews=loanDao.listLoanOrderViewByConditions(query);
        for (Map lov:loanOrderViews){
            Map<String,Object> updateloan=new HashMap<>();
            updateloan.put("loanId",lov.get("loan_id"));
            updateloan.put("loanState",-1);
            loanDao.updateLoanByPrimaryKey(updateloan);
        }

        //使该订单失效
        Map<String,Object> updateorder=new HashMap<>();
        updateorder.put("orderId",orderId);
        updateorder.put("orderState",-2);
        if ((int)loanFkView.get("jy_type")==1){
            updateorder.put("prodPayState",-1);
        }else if ((int)loanFkView.get("jy_type")==3){
            updateorder.put("logisticsPayState",-1);
        }
        orderDao.updateOrderByID(updateorder);

        //回滚已支付金额
        BigDecimal userMoney=(BigDecimal) user.get("user_money");
        BigDecimal userLockMoney=(BigDecimal) user.get("user_lock_money");
        BigDecimal userCreditMoney=(BigDecimal) user.get("user_credit_money");
        BigDecimal userLockCreditMoney=(BigDecimal) user.get("user_lock_credit_money");
        Map<String,Object> queryOrderTrans=new HashMap<>();
        queryOrderTrans.put("orderId",orderId);
        queryOrderTrans.put("inTransState","(0,1)");
        Map<String,Object> orderTrans=orderTransDao.singlePoByConditions(queryOrderTrans);
        if (orderTrans!=null&&orderTrans.size()>0){
            int zfMethod=(int)orderTrans.get("zf_method");
            BigDecimal zfMoney=(BigDecimal)orderTrans.get("zf_money");
            BigDecimal balanceZfMoney=(BigDecimal)orderTrans.get("balance_zf_money");
            BigDecimal loanZfMoney=(BigDecimal)orderTrans.get("loan_zf_money");
            if (zfMethod==2){
                userMoney=userMoney.add(zfMoney);
                userLockMoney=userLockMoney.subtract(zfMoney);
            }else if (zfMethod==3||zfMethod==4){
                userMoney=userMoney.add(balanceZfMoney);
                userLockMoney=userLockMoney.subtract(balanceZfMoney);
                userCreditMoney=userCreditMoney.add(loanZfMoney);
                userLockCreditMoney=userLockCreditMoney.subtract(loanZfMoney);
            }
        }
        Map<String,Object> logisticsTrans=logisticsTransDao.singlePoByConditions(queryOrderTrans);
        if (logisticsTrans!=null&&logisticsTrans.size()>0){
            int zfMethod=(int)logisticsTrans.get("zf_method");
            BigDecimal zfMoney=(BigDecimal)logisticsTrans.get("zf_money");
            BigDecimal balanceZfMoney=(BigDecimal)logisticsTrans.get("balance_zf_money");
            BigDecimal loanZfMoney=(BigDecimal)logisticsTrans.get("loan_zf_money");
            if (zfMethod==2){
                userMoney=userMoney.add(zfMoney);
                userLockMoney=userLockMoney.subtract(zfMoney);
            }else if (zfMethod==3||zfMethod==4){
                userMoney=userMoney.add(balanceZfMoney);
                userLockMoney=userLockMoney.subtract(balanceZfMoney);
                userCreditMoney=userCreditMoney.add(loanZfMoney);
                userLockCreditMoney=userLockCreditMoney.subtract(loanZfMoney);
            }
        }
        Map<String,Object> updateuser=new HashMap<>();
        updateuser.put("userId",userId);
        updateuser.put("userMoney",userMoney);
        updateuser.put("userLockMoney",userLockMoney);
        updateuser.put("userCreditMoney",userCreditMoney);
        updateuser.put("userLockCreditMoney",userLockCreditMoney);
        waUserDao.updateUserByUserId(updateuser);

        //update wa_user_money
        Map<String,Object> updateusermoney=new HashMap<>();
        updateusermoney.put("moneyId",moneyId);
        updateusermoney.put("jyState",-1);
        userMoneyDao.update(updateusermoney);

        messageServicesk.handleLoanSayNoMessage(orderId);
    }
}
