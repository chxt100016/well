package org.wella.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wella.common.ctrl.BaseController;
import org.wella.dao.WaUserDao;
import org.wella.service.impl.RegisterServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/15.
 */
@Controller
@RequestMapping("/register/")
public class RegisterController extends BaseController{

    @Autowired
    private RegisterServiceImpl registerServiceImpl;

    @Autowired
    private WaUserDao waUserDao;

    @RequestMapping(value = {"registerPage"},method = RequestMethod.GET)
    public String registerPage(HttpServletRequest request, Model model){
//        return "views/front/register.jsp";
        Map map = new HashMap();
        map.put("userType",0);
        List arrayList =  waUserDao.findUser(map);
        model.addAttribute("customerList", arrayList);
        model.addAttribute("provinceList", this.getChildRegionList(0));
        return "views/front/register.jsp";
        //return "views/front/customer/login/register.jsp";
    }

    /**
     * 注册请求处理方法
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("register")
    public void register(HttpServletRequest request, HttpServletResponse response){
        Map param=this.getConditionParam(request);
        HashMap result = registerServiceImpl.register(request);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("state",result.get("state"));
        jsonObject.put("content",result.get("content"));
        this.echoJSON(response, jsonObject);
    }

    @RequestMapping("sendCheckCode")
    public void sendCheckCode(HttpServletRequest request,HttpServletResponse response){
        String email = request.getParameter("email");
        JSONObject jsonObject = new JSONObject();
        try{
            registerServiceImpl.sentValidCode(email);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("state","0");
        }finally {
            jsonObject.put("state","1");
        }
        this.echo(response,jsonObject);
    }

    @RequestMapping("resetPassword")
    public void resetPassword(HttpServletRequest request,HttpServletResponse response){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");
        JSONObject jsonObject = new JSONObject();
        try {
            registerServiceImpl.resetPassword(email, password,checkCode);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("state",0);
        }
        jsonObject.put("state",1);
        this.echo(response,jsonObject);
    }

    /**
     * 根据请求的账号与邮箱判断是否属于同一用户的
     * @param request
     * @param response
     */
    @RequestMapping("checkAccount")
    public void checkAccount(HttpServletRequest request,HttpServletResponse response){
        String userName = request.getParameter("userName");
        String eamil = request.getParameter("email");
        boolean ifSame =  registerServiceImpl.checkAccount(eamil,userName);
        JSONObject jsonObject = new JSONObject();
        if(ifSame){
            jsonObject.put("state",1);
        }else {
            jsonObject.put("state",0);
        }
        echo(response,jsonObject);
    }

}
