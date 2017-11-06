package org.wella.platform.controller;

import io.wellassist.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.CreditDao;
import org.wella.dao.LoanDao;
import org.wella.platform.service.CreditService;
import org.wella.service.CreditorService;
import org.wella.service.FinanceService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/18.
 */
@RequestMapping("/platform/credit/")
@Controller
public class CreditController {

    @Autowired
    private CreditDao creditDao;

    @Autowired
    private LoanDao loanDao;

    @Autowired
    private CreditService creditServiceImpl;

    @Autowired
    private FinanceService financeServiceImpl;

    @Autowired
    private CreditorService creditorServiceImpl;

    /**
     * 额度申请页面
     * @return
     */
    @RequestMapping("creditList.html")
    public String creditList(){
        return "views/platform/credit/creditList.html";
    }

    /**
     * 授信指派页面
     * @return
     */
    @RequestMapping("creditAssignList.html")
    public String creditAssignList(){
        return "views/platform/credit/creditAssignList.html";
    }

    /**
     * 额度申请列表
     * @param params
     * @return
     */
    @RequestMapping("creditLimitList")
    @ResponseBody
    public R creditLimitList(@RequestParam Map<String,Object> params){
        Query query=new Query(params);
        query.put("orderBy","field(credit_state,0,1,2,-2,-1) asc");
        List list=creditDao.listCreditAttachUserinfoByConditions(query);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount=creditDao.listCreditAttachUserinfoByConditionsCount(query);
        PageUtils pageUtils=new PageUtils(list,totalCount,query.getLimit(),query.getPage());
        return R.ok().put("page",pageUtils);
    }

    /**
     * 授信指派列表
     * @param params
     * @return
     */
    @RequestMapping("assignList")
    @ResponseBody
    public R assignList(@RequestParam Map<String,Object> params){
        Query query=new Query(params);
        query.put("orderBy","field(loan_state,0,-2,1,2,3,-1),apply_date desc");
        List list=loanDao.listLoanOrderViewByConditions(query);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount=loanDao.listLoanOrderViewByConditionsCount(query);
        PageUtils pageUtils=new PageUtils(list,totalCount,query.getLimit(),query.getPage());
        return R.ok().put("page",pageUtils);
    }

    /**
     * 跳转授信审核页面
     * @param creditId
     * @param model
     * @return
     */
    @RequestMapping("creditLimitCheck")
    public String creditLimitCheck(@RequestParam("creditId")long creditId,Model model){
        Map<String,Object> info=creditServiceImpl.findCreditApplyDetailInfo(creditId);
        model.addAttribute("info",info);
        return "views/platform/credit/creditList/creditCheck.html";
    }

    @RequestMapping("creditLimitDetail")
    public String creditDetail(@RequestParam("creditId")long creditId,Model model){
        Map<String,Object> info=creditServiceImpl.findCreditApplyDetailInfo(creditId);
        model.addAttribute("info",info);
        return "views/platform/credit/creditList/creditDetail.html";
    }


    @RequestMapping("creditCheckSubmit")
    @ResponseBody
    public R creditCheckSubmit(@RequestParam Map<String,Object> params, HttpServletRequest request){
        params.put("creditId",Long.parseLong((String)params.get("creditId")));
        params.put("creditSjMoney",new BigDecimal((String)params.get("creditSjMoney")));
        params.put("flag",Integer.parseInt((String)params.get("flag")));
        params.put("creditUserId", ShiroUtils.getUserId());
        params.put("ip", IPUtils.getIpAddr(request));
        try {
            creditServiceImpl.checkCreditApply(params);
        }catch (Exception e){
            return R.error();
        }
        return R.ok();
    }

    @RequestMapping("creditAssign")
    public String creditAssign(@RequestParam("loanId")String loanId,Model model){
        model.addAttribute("loanId",loanId);
        return "views/platform/credit/creditAssignList/creditAssign.html";
    }

    @RequestMapping("getLoanOrderInfo")
    @ResponseBody
    public R getLoanOrderInfo(@RequestParam("loanId")String loanId){
        Map<String,Object> loan=financeServiceImpl.getLoanOrderInfo(Long.parseLong(loanId));
        return R.ok().put("loan",loan);
    }

    @RequestMapping("assignSubmit")
    @ResponseBody
    public R assignSubmit(@RequestParam("loanId")String loanId,@RequestParam("creditorId")String creditorId,HttpServletRequest request){
        try {
            creditServiceImpl.assignSubmit(Long.parseLong(loanId),Long.parseLong(creditorId));
        } catch (NumberFormatException e) {
            return R.error();
        }
        return R.ok();
    }

    @RequestMapping("assignRollback")
    @ResponseBody
    public R assignRollBack(@RequestParam("loanId")String loanId,HttpServletRequest request){
        long userId=ShiroUtils.getUserId();
        String ip=IPUtils.getIpAddr(request);
        try {
            creditServiceImpl.assignRollBack(Long.parseLong(loanId),userId,ip);
        } catch (Exception e) {
            return R.error();
        }
        return R.ok();
    }

    /**
     * 驳回贷款申请
     * @param loanId
     * @return
     */
    @RequestMapping("loanSayno")
    @ResponseBody
    public R loanSayno(@RequestParam("loanId")long loanId){
        try {
            creditServiceImpl.loanSayno(loanId);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
        return R.ok();
    }

    /**
     * 还款查看列表页面
     * @return view
     */
    @RequestMapping(value = "checkLoan.html",method = RequestMethod.GET)
    public String checkLoan(){
        return "views/platform/credit/checkLoan.html";
    }

    /**
     * 跳转贷款结算审核页面
     * @param loanId loanId
     * @param model model
     * @return view
     */
    @RequestMapping(value = "loanCheck",method = RequestMethod.GET)
    public String loanCheck(@RequestParam("loanId")long loanId,Model model){
        model.addAttribute("loanId",loanId);
        return "views/platform/credit/checkLoan/detail.html";
    }

    @RequestMapping(value = "settleLoan",method = RequestMethod.POST)
    @ResponseBody
    public R settleLoan(@RequestParam("loanId")long loanId){
        financeServiceImpl.handleLoanRepayoff(loanId);
        return R.ok();
    }

    /**
     * 筛选出待还款，已还款，已结算的贷款列表
     * @param params 查询参数
     * @return R
     */
    @RequestMapping(value = "loanList")
    @ResponseBody
    public R loanList(@RequestParam Map<String,Object> params){
        Query query=new Query(params);
        query.put("inLoanState","(2,21,3,4)");
        query.put("orderBy","field(loan_state,3,2,31,21,4),apply_date desc");
        List list=loanDao.listLoanOrderViewByConditions(query);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount=loanDao.listLoanOrderViewByConditionsCount(query);
        PageUtils pageUtils=new PageUtils(list,totalCount,query.getLimit(),query.getPage());
        return R.ok().put("page",pageUtils);
    }

    /**
     * 利息列表
     * @return view
     */
    @RequestMapping(value = "interestList.html",method = RequestMethod.GET)
    public String interestList(){
        return "views/platform/credit/interestList.html";
    }

    /**
     * 利息列表数据
     * @param params 查询阐述
     * @return R
     */
    @RequestMapping(value ="interestListData")
    @ResponseBody
    public R interestListData(@RequestParam Map<String,Object> params){
        Query query=new Query(params);
        query.put("loanState",4);
        query.put("orderBy","apply_date desc");
        List list=loanDao.listLoanOrderViewByConditions(query);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount=loanDao.listLoanOrderViewByConditionsCount(query);
        PageUtils pageUtils=new PageUtils(list,totalCount,query.getLimit(),query.getPage());
        return R.ok().put("page",pageUtils);
    }
}
