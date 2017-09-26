package org.wella.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wella.common.ctrl.BaseController;
import org.wella.service.impl.MailServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ailing on 2017/5/13.
 * 处理注册相关业务逻辑
 */
@Controller
@RequestMapping("/mail/")
public class MailController extends BaseController {
    @Autowired
    private MailServiceImpl mailServiceImpl;

    /**
     * 如果注册成功，则跳转至登录页面，如果注册失败则跳转至。。。
     * @param request
     * @param response
     * @return view
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("register")
    public String register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String code=request.getParameter("code");
//        UserService userService=new UserServiceImpl();
//        if(userService.activeUser(code)){
//            return "/welcome.jsp";
//        }else{
//           return   "/fail.jsp";
//        }
        return "/welcome.jsp";
    }

    /**
     * 激活账号，将wa_user中的激活状态属性修改为1
     * @param request request
     * @param response response
     * @return view
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "active",method = RequestMethod.GET)
    public String active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String code = request.getParameter("code");
       if(mailServiceImpl.activeUser(code)){
           //修改成功后跳转值登录界面
           return "views/front/customer/login/activateNext.jsp";
       }else {
           return "views/home.jsp";
       }
    }

}
