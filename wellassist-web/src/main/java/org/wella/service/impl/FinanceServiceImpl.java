package org.wella.service.impl;

import io.wellassist.utils.HttpContextUtils;
import io.wellassist.utils.IPUtils;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.LoanDao;
import org.wella.dao.RepayDao;
import org.wella.dao.TradeDAO;
import org.wella.entity.User;
import org.wella.service.FinanceService;
import org.wella.service.MessageService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
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
        query.put("loanId", loan.get("loanId"));
        List<Map<String, Object>> repays = repayDao.listRepayByConditions(query);
        ConvertUtil.convertDataBaseMapToJavaMap(repays);
        loan.put("repays", repays);
        return loan;
    }
}
