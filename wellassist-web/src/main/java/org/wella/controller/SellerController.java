package org.wella.controller;


import com.alibaba.fastjson.JSONObject;
import io.wellassist.service.impl.UserServiceImpl;
import io.wellassist.utils.HttpContextUtils;
import io.wellassist.utils.IPUtils;
import io.wellassist.utils.PageUtils;
import io.wellassist.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.vo.MyInfo;
import org.wella.dao.WaUserDao;
import org.wella.entity.User;
import org.wella.entity.Userinfo;
import org.wella.front.seller.mapper.SellerOrderMapper;
import org.wella.platform.service.impl.ProductManageServiceImpl;
import org.wella.service.impl.ProductServiceImpl;
import org.wella.service.impl.SellerServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/12.
 */
@Controller
@RequestMapping(value = {"/seller/"})
public class SellerController extends BaseController {

    @Autowired
    private SellerOrderMapper sellerOrderMapper;

    @Autowired
    private SellerServiceImpl sellerServiceImpl;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private WaUserDao userDao;

    @RequestMapping("processOrder")
    public void processOrder(HttpServletRequest request, HttpServletResponse response, Model model){
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        String ipAddr = IPUtils.getIpAddr(request);
        try {
            String orderId = CommonUtil.GetRequestParam(request, "orderId", "0");
            String saleNum = CommonUtil.GetRequestParam(request, "saleNum", "0");
            String saleDj = CommonUtil.GetRequestParam(request, "saleDj", "0");
            Map editMap = new HashMap();
            editMap.put("confirmNumber",saleNum);
            editMap.put("confirmPrice",saleDj);
            editMap.put("operationIp",ipAddr);
            editMap.put("userId",userId);
            sellerServiceImpl.processOrder(Long.parseLong(orderId),editMap);
            ret = "1";
            obj.put("content",ConstantUtil.MSG_SUCCESS);
        } catch (Exception e) {
            ret = "-2";
            obj.put("content", ConstantUtil.MSG_FAILS);
        }
        obj.put("status", ret);
        this.echoJSON(response, obj);
    }

    @RequestMapping("confirmOrder")
    public String confirmOrder(Model model,@RequestParam("orderId")String orderId){
        Map<String,Object> modelMap=sellerServiceImpl.getInfoForConfirmOrderPage(Long.valueOf(orderId));
        model.addAttribute("orderInfo",modelMap);
        return "views/front/seller/order/confirmOrder.jsp";
    }

    /**
     * 产品发布页面
     * @param model
     * @return
     */
    @RequestMapping("publishPage")
    public String publishPage(Model model){
        Map map = new HashMap();
        map.put("userType",0);
        model.addAttribute("provinceList", this.getChildRegionList(0));
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "1");
        return "views/front/seller/order/prodPub.jsp";
    }

    @RequestMapping("orderList")
    public void orderList(HttpServletRequest request, HttpServletResponse response){
        int ret = -1;
        JSONObject obj = new JSONObject();
        User user= (User) request.getSession().getAttribute("user");
        Long userId=user.getUserId();
        Map param=getConditionParam(request);
        param.put("supplierId",userId);
        List<Map<String,Object>> orderList=sellerServiceImpl.getOrderList(param);
        int orderListCount=sellerServiceImpl.getOrderListCount(param);
        PageUtils page=new PageUtils(orderList,orderListCount,Integer.valueOf(ConstantUtil.GAP),Integer.valueOf((String) param.get("page")));
        obj.put("code",1);
        obj.put("page",page);
        this.echo(response,obj);
    }


    @RequestMapping("publish")
    @ResponseBody
    public R publish(@RequestParam Map<String,Object> params){
        Map map = new HashMap();
        map.put("userType",0);
        HttpSession session = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) session.getAttribute("user");
        params.put("userId",user.getUserId());
        productService.publishProduct(params);
        return new R().ok();
    }

    @RequestMapping("productList")
    public String productList(HttpServletRequest request,Model model){
        Map map = this.getConditionParam(request);
        map.put("userType",0);
        HttpSession session = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) session.getAttribute("user");
        map.put("userId",user.getUserId());
        //产品列表展示

        ArrayList waProdList = this.sellerOrderMapper.getWaProdList(map);
        ConvertUtil.convertDataBaseMapToJavaMap(waProdList);
        int totalCount = this.sellerOrderMapper.getWaProdListCount(map);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(map.get("page").toString()));
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "2");
        model.addAttribute("waProdList", waProdList);
        return "views/front/seller/order/prodList.jsp";
    }

    /**
     * 编辑产品时的初始化信息的封装
     * @param params
     * @param model
     * @return
     */
    @RequestMapping("productEditPage")
    public String productEditPage(@RequestParam Map params,Model model){
        Long prodId = Long.parseLong(params.get("prodId").toString());
        Map prod = productService.viewProductInfo(prodId);
        Map map = new HashMap();
        map.put("userType",0);
        model.addAttribute("prod",prod);
        String pParam = prod.get("prodRegionId").toString().substring(0, 2) + "0000";
        String cParam = prod.get("prodRegionId").toString().substring(0, 4) + "00";
        //省列表
        model.addAttribute("provinceList", this.getChildRegionList(0));
        model.addAttribute("provinceId", pParam);
        //市列表
        model.addAttribute("cityList", this.getChildRegionList(CommonUtil.getIntFromString(pParam)));
        model.addAttribute("cityId", cParam);
        //区列表
        model.addAttribute("countyList", this.getChildRegionList(CommonUtil.getIntFromString(cParam)));

        //产品列表展示
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "2");
        model.addAttribute("prod",prod);
        return "views/front/seller/order/prodEdit.jsp";
    }

    @RequestMapping("updateproduct")
    @ResponseBody
    public R update(@RequestParam Map<String,Object> params){
        productService.updateProductById(params);
        return R.ok();
    }

    /**
     * 进入个人中心，查看企业信息
     * @param model
     * @return
     */
    @RequestMapping("companyInfo")
    public String companyInfo(Model model){
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user =(User) httpSession.getAttribute("user");
        Userinfo userinfo = (Userinfo) httpSession.getAttribute("userInfo");
        model.addAttribute("user",user);
        model.addAttribute("userInfo",userinfo);
        model.addAttribute("parentMenuNo", "4");
        model.addAttribute("childMenuNo", "1");
        String pParam = userinfo.getZcRegionId().toString().substring(0, 2) + "0000";
        String cParam = userinfo.getZcRegionId().toString().substring(0, 4) + "00";
        //省列表
        model.addAttribute("provinceList", this.getChildRegionList(0));
        model.addAttribute("provinceId", pParam);
        //市列表
        model.addAttribute("cityList", this.getChildRegionList(CommonUtil.getIntFromString(pParam)));
        model.addAttribute("cityId", cParam);
        //区列表
        model.addAttribute("countyList", this.getChildRegionList(CommonUtil.getIntFromString(cParam)));
        return "views/front/seller/company/companyInfo.jsp";
    }

    /**
     * 进入联系方式的修改页面
     * @param model
     * @return
     */
    @RequestMapping("contact")
    public String contact(Model model){
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user =(User) httpSession.getAttribute("user");
        Userinfo userinfo = (Userinfo) httpSession.getAttribute("userInfo");
        model.addAttribute("parentMenuNo", "4");
        model.addAttribute("childMenuNo", "2");
        model.addAttribute("user", user);
        model.addAttribute("userInfo", userinfo);
        return "views/front/seller/company/contactMode.jsp";
    }

    /**
     * 进入消息中心，并初始化相关内容
     * @param model
     * @return
     */
    @RequestMapping("message")
    public String message(Model model){
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) httpSession.getAttribute("user");
        //消息列表
//        MyInfo myInfo = this.getMyInfo(request);
//        Map param = this.getConditionParam(request);
//        param.put("userId", myInfo.getUserId());
//        ArrayList newsList = this.newsMapper.getNewsList(param);
//        ConvertUtil.convertDataBaseMapToJavaMap(newsList);
//        model.addAttribute("newsList", newsList);
////        int totalCount = this.newsMapper.getNewsListCount(param);
////        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
////        param.put("strsql", "SELECT COUNT(*) FROM wa_info WHERE is_read = 0 AND user_id = " + myInfo.getUserId());
//        model.addAttribute("noReadCount", Integer.valueOf(this.commonMapper.simpleSelectReturnInt(param)));
//        model.addAttribute("totalCount", Integer.valueOf(totalCount));
        model.addAttribute("parentMenuNo", "3");
        model.addAttribute("childMenuNo", "1");
        model.addAttribute("userName", user.getUserName());
        return "views/front/seller/news/xxList.jsp";
    }
    @RequestMapping("finance")
    public String finance(Model model){
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) httpSession.getAttribute("user");
//        MyInfo myInfo = this.getMyInfo(request);
//        String userId = myInfo.getUserId();
//        Map userInfo = this.getUserInfo(userId);
//        Map param = this.getConditionParam(request);
//        param.put("userId", userId);
//        param.put("jyState", "2");
//        ArrayList list = this.userMoneyMapper0.getJyList(param);
//        ConvertUtil.convertDataBaseMapToJavaMap(list);
//        int totalCount = this.userMoneyMapper0.getJyListCount(param);
//        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("parentMenuNo", "2");
        model.addAttribute("childMenuNo", "1");
//        model.addAttribute("userName", myInfo.getUserName());
//        model.addAttribute("userMoney", userInfo.get("userMoney"));
//        model.addAttribute("list", list);
        return "views/front/seller/finance/accountInfo.jsp";
    }

    @RequestMapping("order")
    public String order(HttpServletRequest request,Model model){
        Map param = this.getConditionParam(request);
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");
        param.put("userId", user.getUserId());
        ArrayList waOrderList = this.sellerOrderMapper.getWaOrderList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(waOrderList);
        model.addAttribute("waOrderList", waOrderList);
        int totalCount = this.sellerOrderMapper.getWaOrderListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "3");
        model.addAttribute("userName", user.getUserName());
        return "views/front/seller/order/orderList.jsp";
    }


}

