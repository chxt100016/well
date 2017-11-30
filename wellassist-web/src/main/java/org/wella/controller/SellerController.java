package org.wella.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.wellassist.utils.*;
import org.apache.commons.collections.map.HashedMap;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.vo.MyInfo;
import org.wella.dao.*;
import org.wella.entity.Bankcard;
import org.wella.entity.User;
import org.wella.entity.Userinfo;
import org.wella.service.BillService;
import org.wella.service.FinanceService;
import org.wella.service.MessageService;
import org.wella.service.WaOrderService;
import org.wella.service.impl.ProductServiceImpl;
import org.wella.service.impl.SellerServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by ailing on 2017/5/12.
 */
@Controller
@RequestMapping(value = {"/seller/"})
public class SellerController extends BaseController {

    @Autowired
    private BankcardDao bankcardDao;

    @Autowired
    private ProdDao prodDao;

    @Autowired
    private UserMoneyDao userMoneyDao;

    @Autowired
    private WithdrawDAO withdrawDAO;

    @Autowired
    private TradeDAO tradeDao;


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
    private BankOrderDao bankOrderDao;


    @Autowired
    private OrderDao orderDao;

    /**
     * 卖家确认订单
     * @param request request
     * @param response response
     * @param model model
     */
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

    /**
     * 订单列表
     * @param request request
     * @param model model
     * @return view
     */
    @RequestMapping("order")
    public String order(HttpServletRequest request,Model model){
        Map param = this.getConditionParam(request);
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");
        param.put("userId", user.getUserId());
        List<Map> waOrderList = this.orderDao.getSellerOrderList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(waOrderList);
        for (Map waOrder:waOrderList){
            Map<String,Object> orderlog=waOrderServiceImpl.findNewestOrderLog(Long.parseLong(waOrder.get("orderId").toString()));
            if(orderlog!=null &&orderlog.size()>0){
                waOrder.putAll(orderlog);
            }
        }
        model.addAttribute("waOrderList", waOrderList);
        int totalCount = this.orderDao.getSellerOrderListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("parentMenuNo", "5");
        model.addAttribute("childMenuNo", "5");
        model.addAttribute("userName", user.getUserName());
        return "views/front/seller/order/orderList.jsp";
    }

    /**
     * 跳转卖家确认订单页面
     * @param model model
     * @param orderId orderId
     * @return view
     */
    @RequestMapping("confirmOrder")
    public String confirmOrder(Model model,@RequestParam("orderId")String orderId){
        Map<String,Object> modelMap=sellerServiceImpl.getInfoForConfirmOrderPage(Long.valueOf(orderId));
        //Map<String,Object> modelMap=sellerServiceImpl.getOrderDetail(Long.valueOf(orderId));
        model.addAttribute("orderInfo",modelMap);
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "3");
        return "views/front/seller/order/confirmOrder.jsp";
    }

    /**
     * 跳转卖家编辑订单页面
     * @param model model
     * @param orderId orderId
     * @return view
     */
    @RequestMapping("editOrder")
    public String editOrder(Model model,@RequestParam("orderId")String orderId){
        Map<String,Object> modelMap=sellerServiceImpl.getOrderDetail(Long.valueOf(orderId));
        model.addAttribute("orderInfo",modelMap);
        return "views/front/seller/order/editOrder.jsp";
    }

    /**
     * 编辑订单处理
     * @param request request
     * @param response response
     * @param model model
     */
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

    /**
     * 跳转发货页面
     * @param orderId orderId
     * @param model model
     * @return view
     */
    @RequestMapping("sendProd")
    public String sendProdPage(@RequestParam("orderId")String orderId, Model model){
        Map<String,Object> info=sellerServiceImpl.getSendProdPageInfo(Long.parseLong(orderId));
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "3");
        model.addAttribute("info",info);
        return "views/front/seller/order/sendProd_new.jsp";
    }

    /**
     * 发货页面选择取货车辆
     * @param orderId orderId
     * @return 取货车辆list
     */
    @RequestMapping("getOrderVehicles")
    @ResponseBody
    public Object getOrderVehicles(@RequestParam("orderId")String orderId){
        List<Map<String,Object>> vehicles=waOrderServiceImpl.findOrderVehicles(Long.parseLong(orderId));
        String jsonString= JSON.toJSONString(vehicles);
        return jsonString;
    }

    /**
     * 发货提交处理
     * @param params 发货页面提交参数
     * @param request request
     * @param response response
     */
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

    /**
     * 处理卖家结束发货
     * @param response response
     * @param orderId orderId
     */
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
     * @param model model
     * @return view
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
     * @param model model
     * @return view
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
     * @param model model
     * @return view
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

    /**
     * 发布产品
     * @param params 产品详细
     * @return code:0成功/500异常 msg:异常信息
     */
    @RequestMapping("publish")
    @ResponseBody
    public R publish(@RequestParam Map<String,Object> params){
        HttpSession session = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) session.getAttribute("user");
        params.put("userId",user.getUserId());
        productService.publishProduct(params);
        return new R().ok();
    }

    /**
     * 产品列表
     * @param request request
     * @param model model
     * @return view
     */
    @RequestMapping("productList")
    public String productList(HttpServletRequest request,Model model) {
        Map map = this.getConditionParam(request);
        map.put("userType", 0);
        HttpSession session = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) session.getAttribute("user");
        map.put("userId", user.getUserId());
        //产品列表展示

        List waProdList = this.prodDao.getWaProdList(map);
        ConvertUtil.convertDataBaseMapToJavaMap(waProdList);
        int totalCount = this.prodDao.getWaProdListCount(map);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(map.get("page").toString()));
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "2");
        model.addAttribute("waProdList", waProdList);
        return "views/front/seller/order/prodList.jsp";
    }

    @RequestMapping(
            value = {"setProdState"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public R setProdState(@RequestParam("prodId")long prodId) {
        try {
            User user=(User) HttpContextUtils.getAttribute("user");
            if(user != null) {
                Map<String,Object> prod=prodDao.singleProdByPrimaryKey(prodId);
                ConvertUtil.convertDataBaseMapToJavaMap(prod);
                if(prod != null && prod.get("userId") != null && prod.get("userId").toString().equals(user.getUserId().toString()) && prod.get("prodState") != null) {
                    int prodState = (int)prod.get("prodState");
                    if(prodState!=1) {
                        if(prodState!=-2&&prodState!=0) {
                            if(prodState==-1) {
                                prodState =2;
                            } else if(prodState==2) {
                                prodState =-1;
                            }
                        } else {
                            prodState = 1;
                        }

                        HashMap param = new HashMap();
                        param.put("prodId",prodId);
                        param.put("prodState",prodState);
                        prodDao.updateProdByPrimaryKey(param);
                    }
                }
            }
        } catch (Exception var13) {
            return R.error(ConstantUtil.MSG_FAILS);
        }

        return R.ok();
    }

    @RequestMapping(
            value = {"deleteProd"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public R deleteProd(@RequestParam("prodId")long prodId) {
        try {
            User user=(User) HttpContextUtils.getAttribute("user");
            if(user != null ) {
                Map<String,Object> prod=prodDao.singleProdByPrimaryKey(prodId);
                ConvertUtil.convertDataBaseMapToJavaMap(prod);
                if(prod != null && prod.get("userId") != null && prod.get("userId").toString().equals(user.getUserId().toString()) && prod.get("prodState") != null) {
                    HashMap param = new HashMap();
                    param.put("prodId",prodId);
                    param.put("prodState",-3);
                    prodDao.updateProdByPrimaryKey(param);
                }
            }
        } catch (Exception var12) {
            return R.error(ConstantUtil.MSG_FAILS);
        }
        return R.ok().put("msg",ConstantUtil.MSG_SUCCESS);
    }

    /**
     * 编辑产品时的初始化信息的封装
     * @param params prod主键
     * @param model model
     * @return view
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

    /**
     * 编辑产品
     * @param params 编辑产品表单提交参数表
     * @return code:0成功/500异常 msg:异常信息
     */
    @RequestMapping("updateproduct")
    @ResponseBody
    public R update(@RequestParam Map<String,Object> params){
        productService.updateProductById(params);
        return R.ok();
    }

    /**
     * 进入个人中心，查看企业信息
     * @param model model
     * @return view
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
     * @param model model
     * @return view
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
     * 处理更新客户联系人信息
     * @param params 联系人信息
     * @return code:0成功/500异常 msg:异常信息
     */
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
     * @param model model
     * @return view
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

    /**
     * 跳转财务中心
     * @param model model
     * @return view
     */
    @RequestMapping("finance")
    public String finance(Model model){
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("parentMenuNo", "2");
        model.addAttribute("childMenuNo", "1");
        return "views/front/seller/finance/accountInfo.jsp";
    }

    /**
     * 跳转修密码页面
     * @param model model
     * @return view
     */
    @RequestMapping("password")
    public String changePassword(Model model) {
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("parentMenuNo", "4");
        model.addAttribute("childMenuNo", "3");
        model.addAttribute("userName", user.getUserName());
        return "views/front/seller/company/changePass.jsp";
    }

    /**
     * 修改支付密码
     * @param map 修改支付密码
     * @return code:0成功/500异常 msg:异常信息
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

    /**
     *修改支付密码前要输入原有的支付密码，原有的支付密码从session中获取
     * @param map 检验支付密码
     * @return code:0成功/500异常 msg:异常信息
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

    /**
     *1.修改session中的操作密码，2.修改数据库中session密码
     * @param map 修改登录密码
     * @return code:0成功/500异常 msg:异常信息
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

    /**
     *修改登录密码前要输入原有的登录密码，原有的登录密码可以从session中获取
     * @param map 检验登录密码
     * @return code:0成功/500异常 msg:异常信息
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


    /**
     * 跳转账户信息页面
     * @param request request
     * @param model model
     * @return view
     */
    @RequestMapping("accountInfo")
    public String accountInfo(HttpServletRequest request, Model model) {
        User user = (User) HttpContextUtils.getAttribute("user");
        Map userInfo = this.getUserInfo(user.getUserId().toString());
        Map param = this.getConditionParam(request);
        param.put("userId", user.getUserId());
        param.put("jyState", "2");
        List list = this.userMoneyDao.getJyList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.userMoneyDao.getJyListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("parentMenuNo", "2");
        model.addAttribute("childMenuNo", "1");
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("userMoney", userInfo.get("userMoney"));
        model.addAttribute("list", list);
        return "views/front/seller/finance/accountInfo.jsp";
    }

    /**
     * 跳转提现列表界面
     * @param request request
     * @param model model
     * @return view
     */
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

    /**
     * 跳转充值记录页面
     * @param request request
     * @param model model
     * @return view
     */
    @RequestMapping({"rechargeRecord"})
    public String rechargeRecord(HttpServletRequest request, Model model) {
        User user = (User) HttpContextUtils.getAttribute("user");
        String userId = user.getUserId().toString();
        Map param = this.getConditionParam(request);
        param.put("userId", userId);
        List list = this.bankOrderDao.getCzList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.bankOrderDao.getCzListCount(param);
        Map retInfo = this.bankOrderDao.getCzMoneyInfo(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("list", list);
        model.addAttribute("parentMenuNo", "2");
        model.addAttribute("childMenuNo", "5");
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("zfMoney", retInfo.get("zfMoney"));
        return "views/front/seller/finance/czSqList.jsp";
    }

    /**
     * 充值申请
     * @param params 充值申请表单提交参数
     * @return code:0成功/500异常 msg:异常信息
     */
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

    /**
     * 校验支付密码
     * @param oldPass 旧密码
     * @param response response
     */
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

    /**
     * 申请提现
     * @param params 申请提现提交参数
     * @return code:0成功/500异常 msg:异常信息
     */
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

    /**
     * 跳转银行卡管理页面
     * @param model model
     * @return view
     */
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

    /**
     * 添加银行卡
     * @param map 添加银行卡表单提交参数
     * @return  code:0成功/500异常 msg:异常信息
     */
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

    /**
     * 删除银行卡
     * @param map 删除银行卡表单提交参数
     * @return
     */
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
     * @param map echart查询参数
     * @param request request
     * @return code:0成功/500异常 msg:异常信息
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
     * @param map echart查询参数
     * @param request request
     * @return code:0成功/500异常 msg:异常信息
     */
    @ResponseBody
    @RequestMapping("profit")
    public R profit(@RequestBody Map<String,Object> map,HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        map.put("userId",user.getUserId());
        List<BigDecimal> list=sellerServiceImpl.profit(map);
        return R.ok().put("data",list);
    }

    /**
     * 跳转报表管理页面
     * @param model model
     * @return view
     */
    @RequestMapping("reportManagement")
    public String reportManagement(Model model){
        model.addAttribute("parentMenuNo","4");
        model.addAttribute("childMenuNo","5");
        return "views/front/seller/company/reportFrom.jsp";
    }

    /**
     * 跳转发票申请页面
     * @param model model
     * @return view
     */
    @RequestMapping(value = "goBillApply",method = RequestMethod.GET)
    public String goBillApply(Model model){
        model.addAttribute("parentMenuNo",7);
        model.addAttribute("childMenuNo",1);
        return "views/front/seller/bill/goBillApply.jsp";
    }

    /**
     * 跳转发票管理页面
     * @param model model
     * @return view
     */
    @RequestMapping(value = "goBillManage",method = RequestMethod.GET)
    public String goBillManage(Model model){
        model.addAttribute("parentMenuNo",7);
        model.addAttribute("childMenuNo",2);
        return "views/front/seller/bill/goBillManage.jsp";
    }

    /**
     * 卖家收到申请发票列表
     * @param params 分页参数
     * @param request request
     * @return 成功：code：0，page:发票列表；失败：code：500，msg：原因
     */
    @RequestMapping(value = "requestBillsList",method = RequestMethod.GET)
    @ResponseBody
    public R requestBillsList(@RequestParam Map params,HttpServletRequest request){
        User user=(User) request.getSession().getAttribute("user");
        long userId=user.getUserId();
        params.put("supplierId",userId);
        /*params.put("supplierId",25L);*/
        Query query=new Query(params);
        List list=sellerServiceImpl.requestBillsList(query);
        int totalCount=sellerServiceImpl.requestBillsListCount(query);
        PageUtils pageUtils=new PageUtils(list,totalCount,query.getLimit(),query.getPage());
        return R.ok().put("page",pageUtils);
    }

    /**
     * 跳转发票发送页面
     * @param model model
     * @return view
     */
    @RequestMapping(value = "goBillSend",method = RequestMethod.GET)
    public String goBillSend(Model model){
        model.addAttribute("parentMenuNo",7);
        model.addAttribute("childMenuNo",1);
        return "views/front/seller/bill/goBillSend.jsp";
    }

    /**
     * 卖家发送发票
     * @param billId 发票id
     * @param kpType 开票类型
     * @param eBill 电子发票url
     * @param kdNo 快递单号
     * @param kdName 快递名
     * @return 成功：code：0；失败：code：500，msg：原因
     */
    @RequestMapping(value = "sendBill",method = RequestMethod.POST)
    @ResponseBody
    public R sendBill(@RequestParam("billId")long billId,@RequestParam("billNo")String billNo,@RequestParam("kpType")int kpType,@RequestParam(value = "eBill",required = false,defaultValue = "")String eBill,
                      @RequestParam(value = "kdNo",required = false,defaultValue = "")String kdNo,@RequestParam(value = "kdName",required = false,defaultValue = "")String kdName)
    {
        try {
             sellerServiceImpl.sendBill(billId,billNo,kpType,eBill,kdNo,kdName);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
        return R.ok();
    }

    /**
     * 已处理发票列表数据
     * @param request request
     * @param params 分页参数
     * @return code:0成功/500异常 msg:异常信息
     */
    @RequestMapping(value = "handledBillsList",method = RequestMethod.GET)
    @ResponseBody
    public R billsList(HttpServletRequest request,@RequestParam Map params){
        User user=(User) request.getSession().getAttribute("user");
        long userId=user.getUserId();
        params.put("supplierId",userId);
        Query query=new Query(params);
        List list=sellerServiceImpl.handledBillsList(query);
        int totalCount=sellerServiceImpl.handledBillsListCount(query);
        PageUtils pageUtils=new PageUtils(list,totalCount,query.getLimit(),query.getPage());
        return R.ok().put("page",pageUtils);
    }

}

