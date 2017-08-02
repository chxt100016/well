package org.wella.controller;

import io.wellassist.utils.IPUtils;
import io.wellassist.utils.PageUtils;
import io.wellassist.utils.Query;
import io.wellassist.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wella.entity.CreditorAuthenticInfo;
import org.wella.entity.User;
import org.wella.service.CreditorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/21.
 */

@Controller
@RequestMapping(value = "/creditor/")
public class CreditorController {

    @Autowired
    private CreditorService creditorServiceImpl;

    @RequestMapping("creditors")
    @ResponseBody
    public R creditors(){
        List<Map<String,Object>> creditors=creditorServiceImpl.findCreditorList();
        return R.ok().put("creditors",creditors);
    }

    @RequestMapping("creditorState")
    @ResponseBody
    public R creditorState(HttpSession session){
        User user=(User)session.getAttribute("user");
        int state=creditorServiceImpl.findCreditorState(user.getUserId());
        return R.ok().put("state",state);
    }

    /**
     * 用户申请放款方认证
     * @param creditorAuthenticInfo
     * @param request
     * @return
     */
    @RequestMapping("creditorAuthApply")
    public String creditorAuthApply(@RequestBody CreditorAuthenticInfo creditorAuthenticInfo,HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        creditorAuthenticInfo.setUserId(user.getUserId());
        creditorAuthenticInfo.setApplyDate(new Date());
        creditorAuthenticInfo.setState((byte)1);
        creditorServiceImpl.qualityApply(creditorAuthenticInfo);
        return "";
    }

    /**
     * 获取被指派的贷款列表
     * @param params
     * @param request
     * @return
     */
    @RequestMapping("loanAssignList")
    @ResponseBody
    public R loanAssignList(@RequestParam Map<String,Object> params,HttpServletRequest request){
        Query query=new Query(params);
        User user=(User)request.getSession().getAttribute("user");
        query.put("creditUserId",user.getUserId());
        List list=creditorServiceImpl.getAssignLoans(query);
        int totalCount=creditorServiceImpl.getAssignLoansCount(query);
        PageUtils pageUtils=new PageUtils(list,totalCount,query.getLimit(),query.getPage());
        return R.ok().put("page",pageUtils);
    }

    /**
     * 放款方确认
     * @param loanId
     * @param paymentDays
     * @param request
     * @return
     */
    @RequestMapping("affirmLoan")
    @ResponseBody
    public R affirmLoan(@RequestParam("loanId")long loanId,@RequestParam("paymentDays")int paymentDays,HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        long userId=user.getUserId();
        String ip= IPUtils.getIpAddr(request);
        try {
            creditorServiceImpl.acceptLoan(loanId,userId,paymentDays,ip);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
        return R.ok();
    }

    @RequestMapping("refuseLoan")
    @ResponseBody
    public R refuseLoan(@RequestParam("loanId")long loanId){

        return R.ok();
    }



}
