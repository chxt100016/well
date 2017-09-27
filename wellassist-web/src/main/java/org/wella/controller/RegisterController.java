package org.wella.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wella.common.ctrl.BaseController;
import org.wella.dao.WaUserDao;
import org.wella.service.impl.RegisterServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ailing on 2017/5/15.
 */
@Controller
@RequestMapping("/register/")
public class RegisterController extends BaseController{

    @Autowired
    private RegisterServiceImpl registerServiceImpl;

    @Autowired
    private WaUserDao waUserDao;

    /**
     * 跳转注册页面
     * @param request request
     * @param model model
     * @return view
     */
    @RequestMapping(value = {"registerPage"},method = RequestMethod.GET)
    public String registerPage(HttpServletRequest request, Model model){
        Map map = new HashMap();
        map.put("userType",0);
        List arrayList =  waUserDao.findUser(map);
        model.addAttribute("customerList", arrayList);
        model.addAttribute("provinceList", this.getChildRegionList(0));
        return "views/front/register.jsp";
    }

    /**
     * 注册请求处理方法
     * @param request request
     * @param response response
     * @return state：状态码，content：内容
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

    /**
     * 跳转注册成功页面
     * @param request request
     * @param response response
     * @param model model
     * @return view
     */
    @RequestMapping({"registerNext"})
    public String registerNext(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "views/front/customer/login/registerNext.jsp";
    }

    /**
     * 发送邮箱验证码
     * @param request request
     * @param response response
     */
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

    /**
     * 重置密码
     * @param request request
     * @param response response
     */
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
     * @param request request
     * @param response response
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

    /**
     * 根据请求的账号与电话判断是否属于同一用户的
     * @param contactphone 电话
     * @return state：状态码，content：内容
     */
    @RequestMapping("checkPhone")
    @ResponseBody
    public Object checkPhone(@RequestParam("contactphone")String contactphone){
        JSONObject object=new JSONObject();
        Map userPhoneInfo = this.getMyOneSingBO("wa_user", "user_phone", contactphone);
        if(userPhoneInfo != null) {
            object.put("result", false);
            return object;
        }
        object.put("result", true);
        return object;
    }

    /**
     * 根据请求的账号与邮箱判断是否属于同一用户的
     * @param contactemail 用户邮箱
     * @return state：状态码，content：内容
     */
    @RequestMapping("checkEmail")
    @ResponseBody
    public Object checkEmail(@RequestParam("contactemail")String contactemail){
        JSONObject object=new JSONObject();
        Map userEmailInfo = this.getMyOneSingBO("wa_user", "user_email", contactemail);
        if(userEmailInfo != null) {
            object.put("result", false);
            return object;
        }
        object.put("result", true);
        return object;
    }

}
