package org.wella.controller;

import io.wellassist.utils.HttpContextUtils;
import io.wellassist.utils.IPUtils;
import io.wellassist.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wella.common.utils.ConstantUtil;
import org.wella.dao.TradeDAO;
import org.wella.dao.WithdrawDAO;
import org.wella.entity.User;
import org.wella.front.mapper.FrontUserMoneyMapper;
import org.wella.service.FinanceService;
import org.wella.service.MessageService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/11.
 */

@Controller
@RequestMapping("/finance/")
public class WaFinanceController {


    @Autowired
    private FrontUserMoneyMapper userMoneyMapper0;

    @Autowired
    private WithdrawDAO  withdrawDAO;

    @Autowired
    private MessageService messageServicesk;

    @Autowired
    private FinanceService financeServiceImpl;

    @Autowired
    private TradeDAO tradeDao;


    @RequestMapping("withdrawProcess")
    @ResponseBody
    public R withdrawProcess(@RequestParam Map<String, Object> params) {
        User user = (User) HttpContextUtils.getAttribute("user");
        params.put("userId", user.getUserId());
        params.put("withdrawIp", IPUtils.getIpAddr(HttpContextUtils.getHttpServletRequest()));
        BigDecimal withdrawMoney=new BigDecimal((String)params.get("withdrawMoney"));
        try {
            int result = withdrawDAO.withdrawApply(params);
            messageServicesk.handleWithdrawApplyMessage(user.getUserId(),withdrawMoney);
            return R.ok().put("state", 1).put("content", "请求已经受理");
        } catch (Exception e) {
            e.printStackTrace();
            return R.ok().put("state", -1).put("content", "系统错误");
        }
    }

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

    @RequestMapping("loanRepayDetail")
    @ResponseBody
    public R loanRepayDetailPage(@RequestParam("loanId")String loanId){
        Map<String,Object> loan=financeServiceImpl.getLoanRepayInfo(Long.parseLong(loanId));
        return R.ok().put("loan",loan);
    }

    //财务信息接口
    /*@RequestMapping("accountInfo")
    public String accountInfo(HttpServletRequest request, Model model) {
        User user = (User) HttpContextUtils.getAttribute("user");
        Byte userType = (Byte) user.getUserType();
        Map userInfo = this.getUserInfo(user.getUserId().toString());
        Map param = this.getConditionParam(request);
        param.put("userId", user.getUserId());
        param.put("jyState", "2");
        ArrayList list = this.userMoneyMapper0.getJyList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.userMoneyMapper0.getJyListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("parentMenuNo", "2");
        model.addAttribute("childMenuNo", "1");
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("userMoney", userInfo.get("userMoney"));
        model.addAttribute("list", list);
        if(userType==1){
            return "views/front/customer/finance/accountInfo.jsp";
        }else if(userType==0){
            return "views/front/seller/finance/accountInfo.jsp";
        }
        return null;
    }*/

    //提现记录
    /*@RequestMapping("withdrawRecordList")
    public String withdrawRecordList(HttpServletRequest request, Model model) {
        User user = (User) HttpContextUtils.getAttribute("user");
        Map<String, Object> param = this.getConditionParam(request);
        param.put("geTxState", "0");
        param.put("ltTxState", "3");
        param.put("userId", user.getUserId());
        List list = tradeDao.getWithdrawRecordList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = tradeDao.getWithdrawRecordCount(param);
        Map retInfo = tradeDao.getWithdrawMoneyTotal(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("parentMenuNo", "2");
        model.addAttribute("childMenuNo", "2");
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("withdrawMoney", retInfo.get("withdrawMoney"));
        model.addAttribute("list", list);
        if(userType==1){
            return "views/front/customer/finance/txList.jsp";
        }else if(userType==0){
            return "views/front/seller/finance/txList.jsp";
        }
        return null;
    }*/

    @RequestMapping("balance")
    @ResponseBody
    public R test(HttpServletRequest request) throws Exception {
        long userId=((User)request.getSession().getAttribute("user")).getUserId();
        return R.ok().put("balance",financeServiceImpl.getBalance(userId));
    }

}
