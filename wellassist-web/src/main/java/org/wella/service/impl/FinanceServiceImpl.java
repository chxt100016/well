package org.wella.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wellapay.cncb.model.ForceTransferBasicInfo;
import com.wellapay.cncb.model.output.AccountTransQuery.AccountTransQueryOutputListEntity;
import com.wellapay.cncb.model.output.BalanceQueryOutput;
import com.wellapay.cncb.util.CNCBConstants;
import io.wellassist.utils.HttpContextUtils;
import io.wellassist.utils.IPUtils;;
import io.wellassist.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.utils.DateUtil;
import org.wella.dao.*;
import org.wella.entity.AdminSubAccount;
import org.wella.entity.CncbTrans;
import org.wella.entity.User;
import org.wella.entity.UserSubAccount;
import org.wella.service.AdminSubAccountService;
import org.wella.service.FinanceService;
import org.wella.service.MessageService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/6/5.
 */
@Service("financeServiceImpl")
public class FinanceServiceImpl implements FinanceService {
    @Autowired
    private TradeDAO tradeDAO;

    @Autowired
    private LoanDao loanDao;

    @Autowired
    private MessageService messageServicesk;

    @Autowired
    private RepayDao repayDao;

    @Autowired
    private UserSubAccountDao userSubAccountDao;

    @Autowired
    private AdminSubAccountService adminSubAccountServiceImpl;

    @Autowired
    private CncbTransDao cncbTransDao;


    @Override
    public int recharge(Map<String, Object> map) {
        User user = (User) HttpContextUtils.getAttribute("user");
        map.put("userId", user.getUserId());
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        map.put("rechargeIp", IPUtils.getIpAddr(request));
        int result = tradeDAO.rechargeApply(map);
        BigDecimal rechargeMoney=new BigDecimal((String) map.get("rechargeMoney"));
        messageServicesk.handleRechargeApplyMessage(user.getUserId(),rechargeMoney);
        return result;
    }

    @Override
    public int withdraw(Map<String, Object> map) {
        return 0;
    }

    @Override
    public Map<String, Object> getLoanOrderInfo(long loanId) {
        Map<String,Object> res=loanDao.singleLoanOrderViewByPrimaryKey(loanId);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        return res;
    }

    @Override
    public Map<String, Object> getLoanRepayInfo(long loanId) {
        Map<String,Object> loan=loanDao.singleLoanOrderViewByPrimaryKey(loanId);
        Map<String, Object> query = new HashMap<>();
        query.put("loanId", loanId);
        List<Map<String, Object>> repays = repayDao.listRepayByConditions(query);
        ConvertUtil.convertDataBaseMapToJavaMap(repays);
        loan.put("repays", repays);
        return loan;
    }

    /*@Override
    public BigDecimal getBalance(long userId) {
        Map<String,Object> query=new HashMap<>();
        query.put("userId",userId);
        UserSubAccount userSubAccount=userSubAccountDao.singleQuery(query);
        BalanceQueryOutput balanceQueryOutput=null;
        try {
            balanceQueryOutput=cncbPayConnectServiceImpl.balanceQuery(userSubAccount.getSubAccNo());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return balanceQueryOutput.getList().getList().get(0).getKYAMT();
    }*/

    @Override
    public BigDecimal getBalance(long userId) throws Exception{
        Map<String,Object> query=new HashMap<>();
        query.put("userId",userId);
        UserSubAccount userSubAccount=userSubAccountDao.singleQuery(query);
        Map<String,String> params=new HashMap();
        params.put("subAccNo",userSubAccount.getSubAccNo().toString());
        String result= org.wella.common.utils.CommonUtil.connectCNCBLocalServer(ConstantUtil.CNCB_SERVER_BASEURL+"balanceQuery",params);
        R r = JSON.parseObject(result,R.class);
        BalanceQueryOutput balanceQueryOutput=JSON.parseObject(r.get("balanceQueryOutput").toString(),BalanceQueryOutput.class);
        return balanceQueryOutput.getList().getList().get(0).getKYAMT();
    }

    @Override
    public UserSubAccount getUserSubAccountByUserId(long userId) {
        Map<String,Object> query=new HashMap<>();
        query.put("userId",userId);
        UserSubAccount userSubAccount=userSubAccountDao.singleQuery(query);
        return userSubAccount;
    }

    @Override
    public List<AccountTransQueryOutputListEntity> getSubAccountFlowByDate(String subAccNo, Date date) {
        Map<String,String> paramss=new HashMap<>();
        paramss.put("userName", CNCBConstants.CNDB_USERNAME);
        paramss.put("accountNo",CNCBConstants.CNDB_MAINACCNO);
        paramss.put("subAccNo",subAccNo);
        paramss.put("startDate", DateUtil.cncbDate(date));
        String result= null;
        try {
            result = CommonUtil.connectCNCBLocalServer(ConstantUtil.CNCB_SERVER_BASEURL+"accountFlowByDay",paramss);
        } catch (Exception e) {
            e.printStackTrace();
        }
        R r= JSON.parseObject(result,R.class);
        List<AccountTransQueryOutputListEntity> list=null;
        if (null != r.get("list")){
            list=JSON.parseArray(r.get("list").toString(),AccountTransQueryOutputListEntity.class);
        }
        return list;

    }

    @Override
    public List<AccountTransQueryOutputListEntity> getOrderTransferAccountFlowByDate(Date date) {
        AdminSubAccount adminSubAccount=adminSubAccountServiceImpl.findOrderTransferAccount();
        List<AccountTransQueryOutputListEntity> list=getSubAccountFlowByDate(adminSubAccount.getSubAccNo(),date);
        return list;
    }

    @Override
    public List<AccountTransQueryOutputListEntity> getLoanTransferAccountFlowByDate(Date date) {
        AdminSubAccount adminSubAccount=adminSubAccountServiceImpl.findLoanTransferAccount();
        List<AccountTransQueryOutputListEntity> list=getSubAccountFlowByDate(adminSubAccount.getSubAccNo(),date);
        return list;
    }

    @Override
    public void handleLoanRepayoff(long loanId) {
        Map<String,Object> loan=loanDao.singleLoanByPrimaryKey(loanId);
        BigDecimal repayMoney=(BigDecimal)loan.get("repay_money");
        BigDecimal lixiMoneyFkf=(BigDecimal)loan.get("lixi_rate_fkf");
        BigDecimal tranAmt=repayMoney.add(lixiMoneyFkf);
        long creditUserId=(long)loan.get("credit_user_id");
        UserSubAccount creditor=getUserSubAccountByUserId(creditUserId);
        AdminSubAccount loanTransfer=adminSubAccountServiceImpl.findLoanTransferAccount();

        Map<String,String> paramss=new HashMap<>();
        paramss.put("payAccNo",loanTransfer.getSubAccNo());
        paramss.put("recvAccNo",creditor.getSubAccNo());
        paramss.put("recvAccNm",creditor.getSubAccNm());
        paramss.put("tranAmt",tranAmt.toString());
        paramss.put("memo","loanId:"+loanId+",content:结算");
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
        cncbTrans.setType((byte)12);
        JSONObject operationParamsObj=new JSONObject();
        operationParamsObj.put("loanId",loanId);
        cncbTrans.setOperationParams(operationParamsObj.toJSONString());
        cncbTransDao.create(cncbTrans);
    }
}
