package org.wella.controller;

import io.wellassist.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("creditorAuthApply")
    public String creditorAuthApply(@RequestBody CreditorAuthenticInfo creditorAuthenticInfo,HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        creditorAuthenticInfo.setUserId(user.getUserId());
        creditorAuthenticInfo.setApplyDate(new Date());
        creditorAuthenticInfo.setState((byte)1);
        creditorServiceImpl.qualityApply(creditorAuthenticInfo);
        return "";
    }



}
