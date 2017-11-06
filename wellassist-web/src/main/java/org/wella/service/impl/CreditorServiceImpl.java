package org.wella.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wellapay.cncb.model.ForceTransferBasicInfo;
import io.wellassist.utils.Query;
import io.wellassist.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.*;
import org.wella.entity.AdminSubAccount;
import org.wella.entity.CncbTrans;
import org.wella.entity.CreditorAuthenticInfo;
import org.wella.entity.UserSubAccount;
import org.wella.service.*;
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

    @Autowired
    private RepayDao repayDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderTransDao orderTransDao;

    @Autowired
    private LogisticsTransDao logisticsTransDao;

    @Autowired
    private UserMoneyDao userMoneyDao;

    @Autowired
    private WaOrderService waOrderServiceImpl;

    @Autowired
    private MessageService messageServicesk;

    @Autowired
    private UserSubAccountDao userSubAccountDao;


    @Autowired
    private CncbTransDao cncbTransDao;

    @Autowired
    private CustomerService customerServiceImpl;

    @Autowired
    private AdminSubAccountService adminSubAccountServiceImpl;

    @Autowired
    private FinanceService financeServiceImpl;



    /**
     * 得到已认证的放款方list
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
    public int payLoan(final long loanId, final long creditorUserId, final int paymentDays, final String operateIp) throws Exception {
        Map<String,Object> loan=loanDao.singleLoanByPrimaryKey(loanId);
        final int loanType=(int)loan.get("loan_type");
        Map<String,Object> loanFkView=loanDao.singleLoanFkViewByPrimaryKey(loanId);
        final long orderId=null==loanFkView.get("order_id")?0L:(long)loanFkView.get("order_id");
        final long logisticsInfoId=null==loanFkView.get("logistics_id")?0L:(long)loanFkView.get("logistics_id");
        final BigDecimal loanMoney=(BigDecimal)loan.get("loan_money");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String,Object> query=new HashMap<>();
                query.put("userId",creditorUserId);
                UserSubAccount userSubAccount=userSubAccountDao.singleQuery(query);
                AdminSubAccount orderTransfer=adminSubAccountServiceImpl.findOrderTransferAccount();
                Map<String,String> paramss=new HashMap<>();
                paramss.put("payAccNo",userSubAccount.getSubAccNo().toString());
                paramss.put("recvAccNo",orderTransfer.getSubAccNo());
                paramss.put("recvAccNm",orderTransfer.getSubAccNm());
                paramss.put("tranAmt",loanMoney.toString());
                JSONObject memo=new JSONObject();
                memo.put("loanId",loanId);
                memo.put("orderId",orderId);
                memo.put("logisticsInfoId",logisticsInfoId);
                memo.put("type",2);
                if (loanType==1){
                    memo.put("content","订单预付款授信付款");
                }else if (loanType==2){
                    memo.put("content","物流订单预付款授信付款");
                }else if (loanType==3){
                    memo.put("content","订单少补付款授信付款");
                }
                paramss.put("memo",memo.toString());
                String result= null;
                try {
                    result = CommonUtil.connectCNCBLocalServer(ConstantUtil.CNCB_SERVER_BASEURL+"forceTransfer",paramss);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                R r= JSON.parseObject(result,R.class);
                ForceTransferBasicInfo forceTransferBasicInfo=JSON.parseObject(r.get("forceTransferBasicInfo").toString(),ForceTransferBasicInfo.class);
                CncbTrans cncbTrans=new CncbTrans();
                cncbTrans.setXml(forceTransferBasicInfo.getXml());
                cncbTrans.setClientId(forceTransferBasicInfo.getClientID());
                cncbTrans.setTime(new Date());
                cncbTrans.setType((byte)2);
                JSONObject operationParamsObj=new JSONObject();
                operationParamsObj.put("loanId",loanId);
                operationParamsObj.put("creditorUserId",creditorUserId);
                operationParamsObj.put("paymentDays",paymentDays);
                operationParamsObj.put("operateIp",operateIp);
                cncbTrans.setOperationParams(operationParamsObj.toJSONString());
                cncbTransDao.create(cncbTrans);

            }
        }).start();
        Map<String,Object> query=new HashMap<>();
        query.clear();
        query.put("loanId",loanId);
        query.put("loanState",11);
        loanDao.updateLoanByPrimaryKey(query);
        return 1;
    }

    @Override
    @Transactional
    public int acceptLoan(long loanId, long creditorUserId, int paymentDays,String operateIp) throws Exception {
        Date now=new Date();

        Map<String,Object> loan=loanDao.singleLoanByPrimaryKey(loanId);
        int loanType=(int)loan.get("loan_type");
        if(creditorUserId!=(long)loan.get("credit_user_id")){
            return 0;
        }

        //授信账户余额减
        financeServiceImpl.calLocalBalance(creditorUserId,((BigDecimal) loan.get("loan_money")).negate());


        // update table wa_loan
        Map<String,Object> updateloan=new HashMap();
        updateloan.put("loanId",loanId);
        updateloan.put("loanDate",now);
        updateloan.put("interestFreeDate", DateUtils.addDays(now,0));
        updateloan.put("paymentDate",DateUtils.addDays(now,0+paymentDays));
        BigDecimal lixiRate=(BigDecimal) loan.get("lixi_rate");
        BigDecimal lixiRateFkf=lixiRate.multiply(new BigDecimal(0.9));
        updateloan.put("lixiRateFkf",lixiRateFkf);
        updateloan.put("loanState",2);
        updateloan.put("remainRepayMoney",loan.get("loan_money"));
        loanDao.updateLoanByPrimaryKey(updateloan);

        //update table wa_loan_assign_info
        Map<String,Object> loanAssignInfo=getLoanAssignInfo(loanId, creditorUserId);

        Map<String,Object> updateloanAssignInfo=new HashMap<>();
        updateloanAssignInfo.put("loanAssignInfoId",loanAssignInfo.get("loanAssignInfoId"));
        updateloanAssignInfo.put("state",1);
        updateloanAssignInfo.put("operateDate",now);
        updateloanAssignInfo.put("operateIp",operateIp);
        loanAssignInfoDao.updateByPrimaryKey(updateloanAssignInfo);

        Map<String,Object> loanFkView=loanDao.singleLoanFkViewByPrimaryKey(loanId);
        if (loanType==1 ||loanType==2){
            //订单授信付款已到账，修改相应订单状态
            if (loanFkView!=null && loanFkView.size()>0){
                Map<String,Object> update=new HashMap();
                if ((int)loanFkView.get("jy_type")==1){
                    update.put("orderId",loanFkView.get("order_id"));
                    update.put("prodPayState",5);
                    orderDao.updateOrderByID(update);
                    update.clear();
                    update.put("orderTransId",loanFkView.get("order_trans_id"));
                    update.put("transState",1);
                    orderTransDao.update(update);
                    update.clear();
                    update.put("moneyId",loanFkView.get("money_id"));
                    update.put("jyState",1);
                    userMoneyDao.update(update);
                    update.clear();
                }else if ((int)loanFkView.get("jy_type")==3){
                    update.put("orderId",loanFkView.get("order_id"));
                    update.put("logisticsPayState",5);
                    orderDao.updateOrderByID(update);
                    update.clear();
                    update.put("logisticsTransId",loanFkView.get("logistics_trans_id"));
                    update.put("transState",1);
                    logisticsTransDao.update(update);
                    update.clear();
                    update.put("moneyId",loanFkView.get("money_id"));
                    update.put("jyState",1);
                    userMoneyDao.update(update);
                    update.clear();
                }
                waOrderServiceImpl.checkOrderRepayOff((long)loanFkView.get("order_id"));
            }
            messageServicesk.handleLoanCreatedMessage(loanId);
        }else if (loanType==3){
            long orderTransId=(long)loanFkView.get("order_trans_id");
            long orderId=(long)loanFkView.get("order_id");
            Map<String,Object> orderTrans=orderTransDao.singlePoByPrimaryKey(orderTransId);
            BigDecimal zfMoney=(BigDecimal)orderTrans.get("zf_money");
            BigDecimal zfSjMoney=(BigDecimal)orderTrans.get("zf_sj_money");
            BigDecimal secondPayMoney=zfSjMoney.subtract(zfMoney);
            int zfMethod2=(int)orderTrans.get("zf_method2");
            BigDecimal balanceZfMoney2=(BigDecimal)orderTrans.get("balance_zf_money2");
            BigDecimal loanZfMoney2=(BigDecimal)orderTrans.get("loan_zf_money2");
            customerServiceImpl.handle2ndPayProd(orderId,secondPayMoney,zfMethod2,balanceZfMoney2,loanZfMoney2,"");
        }
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

    @Override
    public CreditorAuthenticInfo getAuthenticingInfo(long userId) {
        Map<String,Object> query=new HashMap<>();
        query.put("userId",userId);
        query.put("state",1);
        CreditorAuthenticInfo creditorAuthenticInfo=creditorAuthenticInfoDao.singlePoByConditons(query);
        return creditorAuthenticInfo;
    }

    @Override
    public List<Map<String,Object>> getAssignLoans(Map query) {
        query.put("loanState",1);
        query.put("orderBy","apply_date desc");
        List<Map<String, Object>> res=loanDao.listLoanOrderViewByConditions(query);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        return res;
    }

    @Override
    public int getAssignLoansCount(Map query) {
        query.put("loanState",1);
        int result=loanDao.listLoanByConditionsCount(query);
        return result;
    }

    @Override
    public Map<String, Object> getLoanAssignInfo(long loanId, long creditorUserId) {
        Map<String,Object> query=new HashMap<>();
        query.put("loanId",loanId);
        query.put("creditorId",creditorUserId);
        query.put("state",0);
        Map<String,Object> loanAssignInfo=loanAssignInfoDao.singleLoanAssignInfoByConditions(query);
        ConvertUtil.convertDataBaseMapToJavaMap(loanAssignInfo);
        return loanAssignInfo;
    }

    @Override
    @Transactional
    public void refuseLoan(long loanId, long creditorUserId, String comment,String ip) {
        Map<String,Object> updateloan=new HashMap<>();
        updateloan.put("loanId",loanId);
        updateloan.put("creditUserId",0);
        updateloan.put("loanState",0);
        loanDao.updateLoanByPrimaryKey(updateloan);

        Map<String,Object> loanAssignInfo=getLoanAssignInfo(loanId,creditorUserId);
        Map<String,Object> updateLoanAssignInfo=new HashMap<>();
        updateLoanAssignInfo.put("loanAssignInfoId",loanAssignInfo.get("loanAssignInfoId"));
        updateLoanAssignInfo.put("state",-1);
        updateLoanAssignInfo.put("comment",comment);
        updateLoanAssignInfo.put("operateDate",new Date());
        updateLoanAssignInfo.put("operateIp",ip);
        loanAssignInfoDao.updateByPrimaryKey(updateLoanAssignInfo);
    }

    @Override
    public List<Map<String, Object>> listLoanOrderViewByConditions(Map params) {
        List<Map<String, Object>> loans=loanDao.listLoanOrderViewByConditions(params);
        ConvertUtil.convertDataBaseMapToJavaMap(loans);
        return loans;
    }

    @Override
    public int listLoanCount(Map params) {
        int res=loanDao.listLoanByConditionsCount(params);
        return res;
    }

    @Override
    public Map<String, Object> getLatestRepay(long loanId) {
        Map<String,Object> query=new HashMap<>();
        query.put("loanId",loanId);
        query.put("orderBy","repay_date desc");
        Map<String,Object> res=repayDao.singleRepayByConditions(query);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        return res;
    }



    @Override
    public List<Map<String,Object>> repayOffList(Query query) {
        List<Map<String, Object>> loans=loanDao.listLoanOrderViewByConditions(query);
        ConvertUtil.convertDataBaseMapToJavaMap(loans);
        for (Map map:loans){
            Map<String,Object> repay=getLatestRepay((long)map.get("loanId"));
            map.put("latestRepayDate",repay.get("repayDate"));
        }
        return loans;
    }


    @Override
    public List<BigDecimal> lendingAmount(Map<String, Object> map) {
        List<Map<Integer,Object>> list=loanDao.lendingAmount(map);
        List<BigDecimal> list1=PlatformServiceImpl.Transformation(list);
        return list1;
    }


    @Override
    public List<BigDecimal> interest(Map<String, Object> map) {
        List<Map<Integer,Object>> list=loanDao.creditorInterest(map);
        return PlatformServiceImpl.Transformation(list);
    }
}
