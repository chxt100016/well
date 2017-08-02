package org.wella.controller;

import com.alibaba.fastjson.JSONObject;
import io.wellassist.utils.HttpContextUtils;
import io.wellassist.utils.SpringContextUtils;
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
import org.wella.dao.ProdUserDao;
import org.wella.dao.RegionDao;
import org.wella.entity.LogisticsInfo;
import org.wella.entity.Prod;
import org.wella.entity.User;
import org.wella.entity.Userinfo;
import org.wella.service.*;
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

    @Autowired
    private RegionDao regionDao;

    @Autowired
    private CustomerService customerServiceImpl;

    @Autowired
    private SenderService senderServiceImpl;

    @Autowired
    private SellerService sellerServiceImpl;

    @Autowired
    private RegionService regionServiceImpl;

    @Autowired
    private WaOrderService waOrderServiceImpl;

    @RequestMapping(value = {"page"},method = RequestMethod.GET)
    public String page(){
        return "views/login/login.jsp";
    }
    //登录时的相关处理
    @RequestMapping(value = {"in"}, method = RequestMethod.POST)
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
            HttpSession session =request.getSession();
            User user = (User) map.get("user");
            Userinfo userInfo= (Userinfo) map.get("userInfo");
            //获取用户类型0-供货商, 1-客户方, 2-放款方， 3-物流方
            byte type  = user.getUserType();
            session.setAttribute("user",user);
            session.setAttribute("userInfo",userInfo);
            session.setAttribute("initInfo",map.get("initInfo"));
            //MyInfo历史遗留问题
            MyInfo myInfo = new MyInfo();
            myInfo.setUserId(Long.toString(user.getUserId()));
            myInfo.setUserName(user.getUserName());
            myInfo.setUserName(user.getUserName());
            session.setAttribute("MY_INFO",myInfo);
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
        Userinfo userinfo=(Userinfo)session.getAttribute("userInfo");
        User user=((User)session.getAttribute("user"));
        if("0".equals(type)){
            //获取产品信息并保存在session中
            model.addAttribute("user",user);
            model.addAttribute("userInfo",userinfo);
            HashMap param=new HashMap();
            param.put("userId",user.getUserId());
            List<Prod> spList=sellerServiceImpl.findProductList(param);
            model.addAttribute("spList",spList);
            String address=regionServiceImpl.getDetailAddress(Long.parseLong(userinfo.getZcRegionId()),userinfo.getZcXxAddress());
            model.addAttribute("address",address);
            return "/views/front/seller/home.jsp";
        }else if("1".equals(type)){
            model.addAttribute("user",user);
            model.addAttribute("userInfo",userinfo);
            String zcAddress=customerServiceImpl.findZcAddress(userinfo);
            model.addAttribute("address",zcAddress);
            HashMap queryProd=new HashMap();
            queryProd.put("userId",user.getUserId());
            List spList=customerServiceImpl.findProdList(queryProd);
            model.addAttribute("spList",spList);
            return "/views/front/customer/home.jsp";
        }else if("2".equals(type)){
            String zcAddress=customerServiceImpl.findZcAddress(userinfo);
            model.addAttribute("address",zcAddress);
            return "views/front/creditor/home.html";
        }else if("3".equals(type)){
            model.addAttribute("userName",user.getUserName());
            Map queryLogistics = new HashMap();
            queryLogistics.put("size",8);
            queryLogistics.put("state",0);
            queryLogistics.put("wlUserId",user.getUserId());
            List<Map<String,Object>> logisticsInfos=senderServiceImpl.homePageLogisicsInfos(queryLogistics);
            model.addAttribute("logisticsInfos",logisticsInfos);
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
        ArrayList<Map<String,Object>> waOrderList = this.orderDao.findCustomerOrderList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(waOrderList);
        for (Map<String,Object> waOrder:waOrderList){
            Map<String,Object> orderlog=waOrderServiceImpl.findNewestOrderLog(Long.parseLong(waOrder.get("orderId").toString()));
            if(orderlog!=null &&orderlog.size()>0){
                waOrder.putAll(orderlog);
            }
        }
        model.addAttribute("waOrderList", waOrderList);
        int totalCount = orderDao.findCustomerOrderListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "1");
        return "views/front/customer/order/prodOrderList.jsp";
    }

    @RequestMapping(value = {"sellerMain"},method = RequestMethod.GET)
    public String sellerMain(HttpServletRequest request,Model model){
        return "views/front/seller/order/prodPub.jsp";
    }

    @RequestMapping(value = "out",method = RequestMethod.GET)
    public String loginOut (){
        HttpSession session = HttpContextUtils.getHttpServletRequest().getSession();
        session = null;
        return "views/login/login.jsp";
    }

}
