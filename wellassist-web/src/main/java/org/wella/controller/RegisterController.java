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

    @RequestMapping("reSetPassword")
    public void resetPassword(HttpServletRequest request){

    }


}
