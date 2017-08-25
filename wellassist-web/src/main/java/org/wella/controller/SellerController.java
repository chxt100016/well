package org.wella.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.wellassist.utils.HttpContextUtils;
import io.wellassist.utils.IPUtils;
import io.wellassist.utils.PageUtils;
import io.wellassist.utils.R;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.vo.MyInfo;
import org.wella.dao.*;
import org.wella.entity.Bankcard;
import org.wella.entity.User;
import org.wella.entity.Userinfo;
import org.wella.front.mapper.FrontBankOrderMapper;
import org.wella.front.mapper.FrontUserMoneyMapper;
import org.wella.front.seller.mapper.SellerOrderMapper;
import org.wella.service.FinanceService;
import org.wella.service.MessageService;
import org.wella.service.WaOrderService;
import org.wella.service.impl.ProductServiceImpl;
import org.wella.service.impl.SellerServiceImpl;
import sun.plugin2.message.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by liuwen on 2017/5/12.
 */
@Controller
@RequestMapping(value = {"/seller/"})
public class SellerController extends BaseController {



    @Autowired
    private BankcardDao bankcardDao;


    @Autowired
    private FrontBankOrderMapper bankOrderMapper0;


    @Autowired
    private WithdrawDAO withdrawDAO;


    @Autowired
    private TradeDAO tradeDao;

    @Autowired
    private SellerOrderMapper sellerOrderMapper;

    @Autowired
    private SellerServiceImpl sellerServiceImpl;

    @Autowired
    private WaOrderService waOrderServiceImpl;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private WaUserDao userDao;

    @Autowired
    private UserinfoDao userinfoDao;

    @Autowired
    private FinanceService financeServiceImpl;

    @Autowired
    private MessageService messageServicesk;



    @Autowired
    private FrontUserMoneyMapper userMoneyMapper0;


    @Autowired
    private TradeDAO tradeDAO;

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

    @RequestMapping("orderListPage")
    public String ordersheet_list(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("user");
        if(user != null) {
            Map param = this.getConditionParam(request);
            param.put("userId", user.getUserId());
            ArrayList<Map<String,Object>> waOrderList = this.sellerOrderMapper.getWaOrderList(param);
            ConvertUtil.convertDataBaseMapToJavaMap(waOrderList);
            for (Map<String,Object> waOrder:waOrderList){
                Map<String,Object> orderlog=waOrderServiceImpl.findNewestOrderLog(Long.parseLong(waOrder.get("orderId").toString()));
                if(orderlog!=null &&orderlog.size()>0){
                    waOrder.putAll(orderlog);
                }
            }
            model.addAttribute("waOrderList", waOrderList);
            int totalCount = this.sellerOrderMapper.getWaOrderListCount(param);
            this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
            model.addAttribute("parentMenuNo", "5");
            model.addAttribute("childMenuNo", "1");
            model.addAttribute("userName", user.getUserName());
            return "views/front/seller/order/orderList.jsp";
        } else {
            return "redirect:views/login/login.jsp";
        }
    }

    @RequestMapping("confirmOrder")
    public String confirmOrder(Model model,@RequestParam("orderId")String orderId){
        Map<String,Object> modelMap=sellerServiceImpl.getInfoForConfirmOrderPage(Long.valueOf(orderId));
        //Map<String,Object> modelMap=sellerServiceImpl.getOrderDetail(Long.valueOf(orderId));
        model.addAttribute("orderInfo",modelMap);
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "3");
        return "views/front/seller/order/confirmOrder.jsp";
    }



    @RequestMapping("editOrder")
    public String editOrder(Model model,@RequestParam("orderId")String orderId){
        Map<String,Object> modelMap=sellerServiceImpl.getOrderDetail(Long.valueOf(orderId));
        model.addAttribute("orderInfo",modelMap);
        return "views/front/seller/order/editOrder.jsp";
    }

    @RequestMapping("editOrderSubmit")
    public void editOrderSubmit(HttpServletRequest request, HttpServletResponse response, Model model){
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        Long userId=((User)request.getSession().getAttribute("user")).getUserId();
        String ipAddr = IPUtils.getIpAddr(request);
        try {
            String orderId = CommonUtil.GetRequestParam(request, "orderId", "0");
            String saleNum = CommonUtil.GetRequestParam(request, "saleNum", "0");
            String saleDj = CommonUtil.GetRequestParam(request, "saleDj", "0");
            Map editMap = new HashMap();
            editMap.put("orderNumber",saleNum);
            editMap.put("orderPrice",saleDj);
            editMap.put("operationIp",ipAddr);
            editMap.put("userId",userId);
            long orderLogId=sellerServiceImpl.createOrderLog(Long.valueOf(orderId),editMap);
            messageServicesk.handleOrderPriceEditMessage(Long.valueOf(orderId),orderLogId);
            ret = "1";
            obj.put("content",ConstantUtil.MSG_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            ret = "-2";
            obj.put("content", ConstantUtil.MSG_FAILS);
        }
        obj.put("status", ret);
        this.echoJSON(response, obj);
    }

    @RequestMapping("sendProd")
    public String sendProdPage(@RequestParam("orderId")String orderId, Model model){
        Map<String,Object> info=sellerServiceImpl.getSendProdPageInfo(Long.parseLong(orderId));
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "3");
        model.addAttribute("info",info);
        return "views/front/seller/order/sendProd_new.jsp";
    }

    @RequestMapping("getOrderVehicles")
    @ResponseBody
    public Object getOrderVehicles(@RequestParam("orderId")String orderId){
        List<Map<String,Object>> vehicles=waOrderServiceImpl.findOrderVehicles(Long.parseLong(orderId));
        String jsonString= JSON.toJSONString(vehicles);
        return jsonString;
    }



    @RequestMapping("sendProdSubmit")
    public void sendProdSubmit(@RequestParam Map params, HttpServletRequest request,HttpServletResponse response){
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        long userId=((User)request.getSession().getAttribute("user")).getUserId();
        params.put("userId",userId);


        try {
            sellerServiceImpl.sendProd(params);
            ret = "1";
            obj.put("content",ConstantUtil.MSG_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            ret = "-2";
            obj.put("content", ConstantUtil.MSG_FAILS);
        }
        obj.put("status", ret);
        this.echoJSON(response, obj);
    }

    @RequestMapping("sendProdOver")
    public void sendProdOver(HttpServletResponse response,@RequestParam("orderId")String orderId){
        String ret = "-1";
        JSONObject obj=new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        try{
            sellerServiceImpl.sendProdStop(Long.parseLong(orderId));
            ret = "1";
            obj.put("content",ConstantUtil.MSG_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            ret="-2";
            obj.put("content",ConstantUtil.MSG_FAILS);
        }
        obj.put("status", ret);
        this.echoJSON(response, obj);
    }

    /**
     * 跳转订单详情页面
     * @param model
     * @return
     */
    @RequestMapping("orderDetail")
    public String orderDetail(@RequestParam("orderId")String orderId, Model model){
        Map<String,Object> orderDetail=sellerServiceImpl.getOrderDetailInfo(Long.parseLong(orderId));
        model.addAttribute("info",orderDetail);
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "3");
        return "views/front/seller/order/orderDetail_new.jsp";
    }

    /**
     * 跳转物流详情页面
     * @param model
     * @return
     */
    @RequestMapping("logisticsDetail")
    public String logisticsDetail(@RequestParam("orderId")String orderId, Model model){
        Map<String,Object> info=waOrderServiceImpl.findOrderLogisticsInfo(Long.parseLong(orderId));
        model.addAttribute("info",info);
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "3");
        return "views/front/seller/order/expressDetail.jsp";
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
        User user= (User) request.getSession().getAttribute("use r");
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
    public String productList(HttpServletRequest request,Model model) {
        Map map = this.getConditionParam(request);
        map.put("userType", 0);
        HttpSession session = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) session.getAttribute("user");
        map.put("userId", user.getUserId());
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
        return "views/front/seller/order/prodEdit_new.jsp";
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

    @RequestMapping("updateContact")
    @ResponseBody
    public R updateContext(@RequestParam Map<String,Object> params){
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) httpSession.getAttribute("user");
        params.put("userId", user.getUserId());
        try {
            userinfoDao.updateUserinfoByUserId(params);
            return R.ok().put("state",1);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().put("state",0);
        }
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


        model.addAttribute("parentMenuNo", "3");
        model.addAttribute("childMenuNo", "1");
        model.addAttribute("userName", user.getUserName());
        return "views/front/seller/news/message.jsp";
    }
    @RequestMapping("finance")
    public String finance(Model model){
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("parentMenuNo", "2");
        model.addAttribute("childMenuNo", "1");

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

    @RequestMapping("password")
    public String changePassword(Model model) {
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("parentMenuNo", "4");
        model.addAttribute("childMenuNo", "3");
        model.addAttribute("userName", user.getUserName());
        return "views/front/seller/company/changePass.jsp";
    }

    /**修改支付密码
     *
     * @param map
     */
    @RequestMapping("changePayPassword")
    @ResponseBody
    public R changePayPassword(@RequestParam Map<String,Object>map){
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) httpSession.getAttribute("user");
        String paynewpass = map.get("paynewpass").toString();
        //MD5加密
        String password = CommonUtil.MD5(paynewpass);
        user.setCzPass(password);
        Map<String,Object> updateMap = new HashedMap();
        updateMap.put("userId",user.getUserId());
        updateMap.put("czPassword",user.getCzPass());
        try {
            userDao.resetPassword(updateMap);
            return R.ok().put("state",1);
        }catch (Exception e){
            e.printStackTrace();
            return R.error().put("state",0);
        }
    }

    /**检验支付密码
     *修改支付密码前要输入原有的支付密码，原有的支付密码从session中获取
     * @param map
     */
    @RequestMapping("checkPayPassword")
    @ResponseBody
    public R checkPayPassword(@RequestParam Map<String,Object>map){
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) httpSession.getAttribute("user");
        String payoldpass = map.get("oldPass").toString();
        //MD5加密
        String password = CommonUtil.MD5(payoldpass);
        if(password.equals(user.getCzPass())){
            return R.ok().put("state",1);
        }else{
            return R.error().put("state",0);
        }
    }

    /**修改登录密码
     *1.修改session中的操作密码，2.修改数据库中session密码
     * @param map
     */
    @RequestMapping("changeLoginPassword")
    @ResponseBody
    public R changeLoginPassword(@RequestParam Map<String,Object>map){
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) httpSession.getAttribute("user");
        String newpass = map.get("newpass").toString();
        //MD5加密
        String password = CommonUtil.MD5(newpass);
        user.setUserPass(password);
        Map<String,Object> updateMap = new HashedMap();
        updateMap.put("userId",user.getUserId());
        updateMap.put("userPassword",user.getUserPass());
        try{
            userDao.resetPassword(updateMap);
            return R.ok().put("state",1);
        }
        catch (Exception e){
            e.printStackTrace();
            return R.error().put("state",0);
        }
    }

    /**检验登录密码
     *修改登录密码前要输入原有的登录密码，原有的登录密码可以从session中获取
     * @param map
     */
    @RequestMapping("checkLoginPassword")
    @ResponseBody
    public R checkLoginPassword(@RequestParam Map<String,Object>map){
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) httpSession.getAttribute("user");
        String payoldpass = map.get("oldPass").toString();
        //MD5加密
        String password = CommonUtil.MD5(payoldpass);
        if(password.equals(user.getUserPass())){
            return R.ok().put("state",1);
        }else{
            return R.error().put("state",0);
        }
    }



    @RequestMapping("accountInfo")
    public String accountInfo(HttpServletRequest request, Model model) {
        User user = (User) HttpContextUtils.getAttribute("user");
        Map userInfo = this.getUserInfo(user.getUserId().toString());
        Map param = this.getConditionParam(request);
        param.put("userId", user.getUserId());
        param.put("jyState", "2");
        ArrayList list = this.userMoneyMapper0.getJyList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.userMoneyMapper0.getJyListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("parentMenuNo", "2");
        model.addAttribute("childMenuNo", "1");
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("userMoney", userInfo.get("userMoney"));
        model.addAttribute("list", list);
        return "views/front/seller/finance/accountInfo.jsp";
    }




    @RequestMapping("withdrawRecordList")
    public String withdrawRecordList(HttpServletRequest request, Model model) {
        User user = (User) HttpContextUtils.getAttribute("user");
        Map<String, Object> param = this.getConditionParam(request);
        param.put("geTxState", "0");
        param.put("ltTxState", "3");
        param.put("userId", user.getUserId());
        List list = tradeDao.getWithdrawRecordList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = tradeDao.getWithdrawRecordCount(param);
        Map retInfo = tradeDao.getWithdrawMoneyTotal(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("parentMenuNo", "2");
        model.addAttribute("childMenuNo", "6");
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("withdrawMoney", retInfo.get("withdrawMoney"));
        model.addAttribute("list", list);
        return "views/front/seller/finance/txList.jsp";
    }




    @RequestMapping({"rechargeRecord"})
    public String rechargeRecord(HttpServletRequest request, Model model) {
        User user = (User) HttpContextUtils.getAttribute("user");
        String userId = user.getUserId().toString();
        Map param = this.getConditionParam(request);
        param.put("userId", userId);
        ArrayList list = this.bankOrderMapper0.getCzList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.bankOrderMapper0.getCzListCount(param);
        Map retInfo = this.bankOrderMapper0.getCzMoneyInfo(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("list", list);
        model.addAttribute("parentMenuNo", "2");
        model.addAttribute("childMenuNo", "5");
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("zfMoney", retInfo.get("zfMoney"));
        return "views/front/seller/finance/czSqList.jsp";
    }


    @RequestMapping("rechargeApply")
    @ResponseBody
    public R rechargeApply(@RequestParam Map<String, Object> params) {
        int res = financeServiceImpl.recharge(params);
        if (res == 1) {
            return R.ok().put("state", 1).put("content", ConstantUtil.MSG_SUCCESS);
        } else {
            return R.error().put("state", 0).put("content", ConstantUtil.MSG_FAILS);
        }
    }



    @RequestMapping("checkCzPassword")
    public void checkWithdrawPassword(@RequestParam("pass") String oldPass, HttpServletResponse response) {
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) httpSession.getAttribute("user");
        //MD5加密
        String password = CommonUtil.MD5(oldPass);
        Boolean res = Boolean.valueOf(false);
        if (password.equals(user.getCzPass())) {
            res = Boolean.valueOf(true);
        } else {
            res = Boolean.valueOf(false);
        }
        this.echo(response, res.toString());
    }






    @RequestMapping("withdrawProcess")
    @ResponseBody
    public R withdrawProcess(@RequestParam Map<String, Object> params) {
        User user = (User) HttpContextUtils.getAttribute("user");
        params.put("userId", user.getUserId());
        params.put("withdrawIp", IPUtils.getIpAddr(HttpContextUtils.getHttpServletRequest()));
        try {
            int result = withdrawDAO.withdrawApply(params);
            return R.ok().put("state", 1).put("content", "请求已经受理");
        } catch (Exception e) {
            e.printStackTrace();
            return R.ok().put("state", -1).put("content", "系统错误");
        }
    }




    @RequestMapping("bankcardPage")
    public String bankcardPage(Model model) {
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) httpSession.getAttribute("user");
        Userinfo userinfo = (Userinfo) httpSession.getAttribute("userInfo");
        List<Bankcard> bankcardList = bankcardDao.getCardListByUserId(user.getUserId());
        model.addAttribute("user", user);
        model.addAttribute("userInfo", userinfo);
        model.addAttribute("parentMenuNo", "4");
        model.addAttribute("childMenuNo", "4");
        model.addAttribute("Cards", bankcardList);
        model.addAttribute("userName", user.getUserName());
        return "views/front/seller/company/bankcard.jsp";
    }



    @RequestMapping("addBankcard")
    @ResponseBody
    public R addBankcard(@RequestParam Map<String, Object> map) {
        User user = (User) HttpContextUtils.getAttribute("user");
        long userId = user.getUserId();
        map.put("userId", userId);
        map.put("addTime", new Date());
        try {
            long key = bankcardDao.addCard(map);
            return R.ok().put("content", "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }



    @RequestMapping("delBankcard")
    @ResponseBody
    public R delBankcard(@RequestParam Map<String, Object> map) {
        User user = (User) HttpContextUtils.getAttribute("user");
        long userId = user.getUserId();
        try {
            int count = bankcardDao.delCard(Long.parseLong(map.get("bankcardId").toString()));
            return R.ok().put("content", "添加成功").put("count", count);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }


    /**
     * 销售量
     * @param map
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("saleVolume")
    public R salesVolume(@RequestBody Map<String,Object> map,HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        map.put("userId",user.getUserId());
        map.put("userId",91);
        List<BigDecimal> list=sellerServiceImpl.salesVolume(map);
        return R.ok().put("data",list);
    }


    /**
     * 获得利益
     * @param map
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("profit")
    public R profit(@RequestBody Map<String,Object> map,HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        map.put("userId",user.getUserId());
        List<BigDecimal> list=sellerServiceImpl.profit(map);
        return R.ok().put("data",list);
    }


    @RequestMapping("reportManagement")
    public String reportManagement(Model model){
        model.addAttribute("parentMenuNo","4");
        model.addAttribute("childMenuNo","5");
        return "views/front/seller/company/reportFrom.jsp";
    }


}

