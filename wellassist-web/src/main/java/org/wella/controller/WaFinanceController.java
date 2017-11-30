package org.wella.controller;

import io.wellassist.utils.HttpContextUtils;
import io.wellassist.utils.IPUtils;
import io.wellassist.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.BankcardDao;
import org.wella.dao.LoanDao;
import org.wella.dao.WaUserDao;
import org.wella.dao.WithdrawDAO;
import org.wella.entity.Bankcard;
import org.wella.entity.User;
import org.wella.entity.Withdraw;
import org.wella.service.AdminSubAccountService;
import org.wella.service.BankcardService;
import org.wella.service.FinanceService;
import org.wella.service.MessageService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by ailing on 2017/8/11.
 */
@Controller
@RequestMapping("/finance/")
public class WaFinanceController {

    @Autowired
    private WithdrawDAO  withdrawDAO;

    @Autowired
    private MessageService messageServicesk;

    @Autowired
    private FinanceService financeServiceImpl;

    @Autowired
    private WaUserDao waUserDao;

    @Autowired
    private BankcardService bankcardServiceImpl;

    @Autowired
    private LoanDao loanDao;

    @Autowired
    private AdminSubAccountService adminSubAccountServiceImpl;

    /**
     * 获取withdraw pojo
     * @param withdrawId withdrawId
     * @return R
     */
    @RequestMapping(value = "withdrawInfo",method = RequestMethod.GET)
    @ResponseBody
    public R withdrawInfo(@RequestParam("withdrawId")long withdrawId){
        Withdraw withdraw=withdrawDAO.querySingleByPk(withdrawId);
        return R.ok().put("withdraw",withdraw);
    }

    /**
     * 获取loan pojo
     * @param loanId loanId
     * @return R
     */
    @RequestMapping(value = "loanInfo",method = RequestMethod.GET)
    @ResponseBody
    public R loanInfo(@RequestParam("loanId")long loanId){
        Map<String,Object> loan=loanDao.singleLoanOrderViewByPrimaryKey(loanId);
        ConvertUtil.convertDataBaseMapToJavaMap(loan);
        return R.ok().put("loan",loan);
    }

    @RequestMapping(value = "withdrawProcess",method = RequestMethod.POST)
    @ResponseBody
    public R withdrawApply(@RequestParam(value = "bankcardId")long bankcardId,@RequestParam(value = "withdrawMoney")BigDecimal withdrawMoney){
        User user=(User)HttpContextUtils.getAttribute("user");
        long userId=user.getUserId();
        Bankcard bankcard=bankcardServiceImpl.getCard(bankcardId);
        if (null==bankcard || userId != bankcard.getUserId() ){
            return R.error("遭受攻击，提现失败");
        }
        BigDecimal localBalance=financeServiceImpl.getLocalBalance(userId);
        if (localBalance.compareTo(withdrawMoney)<0){
            return R.error("提现超额");
        }
        String ip=IPUtils.getIpAddr(HttpContextUtils.getHttpServletRequest());
        financeServiceImpl.withdrawApply(userId,withdrawMoney,bankcardId,ip);
        return R.ok();
    }

    /**
     * 充值申请
     * @param params 充值金额，充值方式
     * @return code:0成功/500异常 msg:异常信息
     */
    @RequestMapping("rechargeApply")
    @ResponseBody
    public R rechargeApply(@RequestParam Map<String, Object> params) {
        int res = financeServiceImpl.recharge(params);
        if (res == 1) {
            return R.ok().put("state", 1).put("content", ConstantUtil.MSG_SUCCESS);
        } else {
            return R.error().put("state", 0).put("content", ConstantUtil.MSG_FAILS);
        }
    }

    /**
     * 贷款还款详细
     * @param loanId loanId
     * @return code:0成功/500异常 msg:异常信息
     */
    @RequestMapping("loanRepayDetail")
    @ResponseBody
    public R loanRepayDetailPage(@RequestParam("loanId")String loanId){
        Map<String,Object> loan=financeServiceImpl.getLoanRepayInfo(Long.parseLong(loanId));
        return R.ok().put("loan",loan);
    }

    /**
     * 查询用户余额
     * @param request request
     * @return code:0成功/500异常 msg:异常信息
     * @throws Exception
     */
    @RequestMapping("balance")
    @ResponseBody
    public R balance(HttpServletRequest request) throws Exception {
        long userId=((User)request.getSession().getAttribute("user")).getUserId();
        return R.ok().put("balance",financeServiceImpl.getBalance(userId));
    }

    /**
     * 查询用户授信余额
     * @param request request
     * @return code:0成功/500异常 msg:异常信息
     */
    @RequestMapping("creditBalance")
    @ResponseBody
    public R creditBalance(HttpServletRequest request){
        long userId=((User)request.getSession().getAttribute("user")).getUserId();
        Map<String,Object> user=waUserDao.singleUserByPrimaryKey(userId);
        Object userCreditMoney=user.get("user_credit_money");
        return R.ok().put("userCreditMoney",userCreditMoney);
    }

    /**
     * 从服务器数据库查询用户余额
     * @return code:0成功/500异常 msg:异常信息
     */
    @RequestMapping(value = "localBalance",method = RequestMethod.GET)
    @ResponseBody
    public R localBalance(){
        User user=(User)HttpContextUtils.getAttribute("user");
        long userId=user.getUserId();
        BigDecimal userMoney=financeServiceImpl.getLocalBalance(userId);
        return R.ok().put("balance",userMoney);
    }

    /**
     * 从中信银行同步余额loanRepayDetail
     */
    @RequestMapping(value = "syncBalance",method = RequestMethod.GET)
    @ResponseBody
    public R syncBalance() throws Exception {
        User user=(User)HttpContextUtils.getAttribute("user");
        long userId=user.getUserId();
        BigDecimal balance=financeServiceImpl.syncBalance(userId);
        return R.ok().put("balance",balance);
    }

    @RequestMapping(value = "syncASABalance",method = RequestMethod.GET)
    @ResponseBody
    public R syncASABalance(@RequestParam("id")long id) throws Exception {
        BigDecimal balance=adminSubAccountServiceImpl.syncAdminSubAccountBalance(id);
        return R.ok().put("balance",balance);
    }
}
