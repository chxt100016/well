package org.wella.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wella.common.ctrl.BaseController;
import org.wella.service.impl.RegisterServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Created by liuwen on 2017/5/15.
 */
@Controller
@RequestMapping("/register/")
public class RegisterController extends BaseController{

    @Autowired
    private RegisterServiceImpl registerServiceImpl;


    /**
     * 注册请求处理方法
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("register")
    public void register(HttpServletRequest request, HttpServletResponse response){
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
        String userName = request.getParameter("phone");
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
