package org.wella.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.vo.MyInfo;
import org.wella.dao.OrderDao;
import org.wella.dao.ProdDao;
import org.wella.entity.User;
import org.wella.entity.Userinfo;
import org.wella.service.impl.LoginServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/16.
 * 统一登录页面，
 */
@Controller
@RequestMapping("/login/")
public class LoginController extends BaseController {

    @Autowired
    private LoginServiceImpl loginServiceImpl;

    @Autowired
    private ProdDao prodDao;

    @Autowired
    private OrderDao orderDao;

    @RequestMapping(value = {"page"},method = {RequestMethod.GET})
    public String page(){
        return "views/login/login.jsp";
    }
    //登录时的相关处理
    @RequestMapping(value = {"in"}, method = {RequestMethod.POST})
    public void login(HttpServletRequest request , HttpServletResponse response){
        String account = request.getParameter("username");
        String password = request.getParameter("password");
        //登录的业务逻辑验证
        Map map = loginServiceImpl.login(account,password);
        String content = (String) map.get("content");
        JSONObject jsonObject = new JSONObject();
        if(!"".equals(content)){
            jsonObject.put("content",content);
            //验证成功标志，前端回调参数
            jsonObject.put("status",0);
        }else {
            //登录成功后将相关的信息保存到session中去
            HttpSession sessin =request.getSession();
            User user = (User) map.get("user");
            Userinfo userInfo= (Userinfo) map.get("userInfo");
            //获取用户类型0-供货商, 1-客户方, 2-放款方， 3-物流方
            byte type  = user.getUserType();
            sessin.setAttribute("user",user);
            sessin.setAttribute("userInfo",userInfo);
            sessin.setAttribute("initInfo",map.get("initInfo"));
            //验证成功标志，前端回调参数
            jsonObject.put("status",1);
            jsonObject.put("type",type);
        }
        echo(response,jsonObject);
    }

    //登录成功时根据不同的用户类别进行跳转
    //0-供货商, 1-客户方, 2-放款方， 3-物流方
    //视图界面逻辑
    @RequestMapping(value = {"success"},method = RequestMethod.GET)
    public String success(HttpServletRequest request, Model model){
        String type = request.getParameter("type");
        HttpSession session = request.getSession();
        if("0".equals(type)){
            //获取产品信息并保存在session中

            return "/views/front/seller/home.jsp";
        }else if("1".equals(type)){

            return "/views/front/customer/home.jsp";
        }else if("2".equals(type)){
            return "/views/front/fkf/home.jsp";
        }else if("3".equals(type)){
            return "/views/front/sender/home.jsp";
        }else{
            return "/views/login/login.jsp";
        }
    }

    //买家进入管理员界面
    @RequestMapping(value = {"customerMain"},method = RequestMethod.GET)
    public String customerMain(HttpServletRequest request,Model model){

        User user = (User) request.getSession().getAttribute("user");
        Map param = this.getConditionParam(request);
        param.put("userId", user.getUserId());
        ArrayList waOrderList = this.orderDao.findCustomerOrderList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(waOrderList);
        model.addAttribute("waOrderList", waOrderList);
        int totalCount = orderDao.findCustomerOrderListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "1");
        return "views/front/customer/order/prodOrderList.jsp";

//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//        HashMap param = new HashMap();
//        param.put("userId", user.getUserId());
//        List<Map<String ,Object>> prodList = prodDao.findUserProdList(param);
//        ConvertUtil.convertDataBaseMapToJavaMap(prodList);
//        model.addAttribute("spList", prodList);
//        model.addAttribute("userName", user.getUserName());
//        model.addAttribute("parentMenuNo", "5");
//        model.addAttribute("childMenuNo", "0");
//        //
//        return "views/front/customer/prod/prodList.jsp";
    }

    @RequestMapping(value = {"sellerMain"},method = RequestMethod.GET)
    public String sellerMain(HttpServletRequest request,Model model){


        return "views/front/seller/order/prodPub.jsp";
    }
}
