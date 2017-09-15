package org.wella.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wellapay.cncb.model.ForceTransferBasicInfo;
import com.wellapay.cncb.service.CNCBPayConnectService;
import io.wellassist.utils.HttpContextUtils;
import io.wellassist.utils.IPUtils;
import io.wellassist.utils.R;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.vo.MyInfo;
import org.wella.dao.*;
import org.wella.entity.*;
import org.wella.front.customer.mapper.CustomerBackOrderMapper;
import org.wella.front.mapper.FrontBankOrderMapper;
import org.wella.front.mapper.FrontTixianMapper;
import org.wella.front.mapper.FrontUserMoneyMapper;
import org.wella.front.mapper.NewsMapper;
import org.wella.service.CustomerService;
import org.wella.service.FinanceService;
import org.wella.service.WaOrderService;
import org.wella.service.impl.CustomerServiceImpl;
import org.wella.service.impl.FinanceServiceImpl;
import org.wella.service.impl.SellerServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by liuwen on 2017/5/11.
 */
@Controller
@RequestMapping(value = "/customer/")
public class CustomerController extends BaseController {
   @Autowired
   private CustomerServiceImpl customerServiceImpl;

   @Autowired
   private WaUserDao userDao;

   @Autowired
   private ProdDao prodDao;

   @Autowired
   private OrderDao orderDao;

   @Autowired
   private CustomerBackOrderMapper customerBackOrderMapper;

   @Autowired
   private FrontUserMoneyMapper userMoneyMapper0;

   @Autowired
   private NewsMapper newsMapper;

   @Autowired
   private UserinfoDao userinfoDao;

   @Autowired
   private WithdrawDAO withdrawDAO;

   @Autowired
   private FrontBankOrderMapper bankOrderMapper0;

   @Autowired
   private WaOrderService waOrderServiceImpl;

   @Autowired
   private BankcardDao bankcardDao;

   @Autowired
   private TradeDAO tradeDao;

   @Autowired
   private FinanceService financeServiceImpl;

   @Autowired
   private BankOrderDao bankOrderDao;

   @Autowired
   private UserSubAccountDao userSubAccountDao;

   @Autowired
   private CNCBPayConnectService cncbPayConnectServiceImpl;

   @Autowired
   private CncbTransDao cncbTransDao;

   @Autowired
   private LogisticsInfoDao logisticsInfoDao;

   /**
    * 买家下订单
    *
    * @param map      prodId，saleNum，danjia，saleMoney，isSelfCar，contacts，conTel，toRegionId
    *                 toRegionAddr，orderData，deliverDate，receiveDate，customerExceptCarriage
    * @param response
    */
   @RequestMapping(value = "order", method = RequestMethod.POST)
   @ResponseBody
   public R order(@RequestParam Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
      User user = (User) HttpContextUtils.getHttpServletRequest().getSession().getAttribute("user");
      long userId = user.getUserId();
      map.put("userId", userId);
      String ip = getIpAddr(request);
      map.put("orderIp", ip);
      JSONObject res = new JSONObject();
      long orderId=0;
      try {
         orderId=customerServiceImpl.order(map);
      } catch (Exception e) {
         e.printStackTrace();
         return R.error();
      }
      return R.ok().put("orderId",orderId);
   }

   @RequestMapping("makeOrder")
   public String makeOrder(HttpServletRequest request, HttpServletResponse response, Model model) {
      String prodId = CommonUtil.GetRequestParam(request, "prodId", "0");
      HashMap param = new HashMap();
      param.put("prodId", prodId);
      Map prodMap = customerServiceImpl.findProdById(param);

      HashMap param1 = new HashMap();
      param1.put("parentRegionId", "0");
      List shengRegionList = customerServiceImpl.getRegionList(param1);

      MyInfo myInfo = this.getMyInfo(request);
      model.addAttribute("userName", myInfo.getUserName());
      model.addAttribute("shengRegionList", shengRegionList);
      model.addAttribute("prod", prodMap);
      model.addAttribute("prodId", prodId);
      model.addAttribute("parentMenuNo", "1");
      model.addAttribute("childMenuNo", "1");
      return "views/front/customer/orderPage_new.jsp";
   }

   @RequestMapping({"orderSuccess"})
   public String orderSuccess(@RequestParam("orderId")long orderId, HttpServletRequest request, Model model) {
      User user = (User) request.getSession().getAttribute("user");
      Map<String,Object> order=orderDao.singleOrderByPrimaryKey(orderId);
      model.addAttribute("username", user.getUserName());
      model.addAttribute("orderNo",order.get("order_no"));
      model.addAttribute("orderDate",order.get("order_date"));
      model.addAttribute("parentMenuNo", "1");
      model.addAttribute("childMenuNo", "1");
      return "views/front/customer/ordersheetresult.jsp";
   }

   /**
    * 跳转订单详情页面
    *
    * @param model
    * @return
    */
   @RequestMapping("orderDetail")
   public String orderDetail(@RequestParam("orderId") String orderId, HttpServletRequest request, Model model) {
      User user = (User) request.getSession().getAttribute("user");
      Map<String, Object> orderDetail = customerServiceImpl.getOrderDetailInfo(Long.parseLong(orderId));
      model.addAttribute("info", orderDetail);
      model.addAttribute("userName", user.getUserName());
      model.addAttribute("parentMenuNo", "1");
      model.addAttribute("childMenuNo", "1");
      return "views/front/customer/order/orderDetail_new.jsp";
   }

   @RequestMapping(value = "zorderConfirmReceive", method = RequestMethod.GET)
   @ResponseBody
   public R zorderConfirmReceive(@RequestParam("zorderId") String zorderId, @RequestParam("receiveComment") String receiveComment) {
      int res = customerServiceImpl.zorderConfirmReceive(Long.parseLong(zorderId), receiveComment);
      if (res > 0) {
         return R.ok();
      }
      return R.error();
   }

   @RequestMapping(value = "zorderDoubtReceive", method = RequestMethod.GET)
   @ResponseBody
   public R zorderDoubtReceive(@RequestParam("orderId")String orderId,@RequestParam("zorderId") String zorderId, @RequestParam("receiveComment") String receiveComment) {
      try {
         customerServiceImpl.zorderDoubtReceive(Long.parseLong(orderId),Long.parseLong(zorderId), receiveComment);
      } catch (NumberFormatException e) {
         e.printStackTrace();
         return R.error();
      }
      return R.ok();
   }


   /**
    * 跳转物流详情页面
    *
    * @param model
    * @return
    */
   @RequestMapping("logisticsDetail")
   public String logisticsDetail(HttpServletRequest request, @RequestParam("orderId") String orderId, Model model) {
      User user = (User) request.getSession().getAttribute("user");
      Map<String, Object> info = waOrderServiceImpl.findOrderLogisticsInfo(Long.parseLong(orderId));
      model.addAttribute("info", info);
      model.addAttribute("userName", user.getUserName());
      model.addAttribute("parentMenuNo", "1");
      model.addAttribute("childMenuNo", "1");
      return "views/front/customer/order/expressDetail.jsp";
   }


   /**
    * 跳转买家物流订单界面
    */
   @RequestMapping("logisticsInfoList")
   public String logisticsInfoList(HttpServletRequest request, Model model) {
      Map param = getConditionParam(request);
      User user = (User) request.getSession().getAttribute("user");
      long userId = user.getUserId();
      param.put("userId", userId);
      List<Map<String, Object>> info = customerServiceImpl.logisticsInfoListInfo(param);
      int totalCount = customerServiceImpl.logisticsInfoListInfoCount(param);
      this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
      model.addAttribute("info", info);
      model.addAttribute("parentMenuNo", "1");
      model.addAttribute("childMenuNo", "2");
      model.addAttribute("userName", user.getUserName());
      return "views/front/customer/order/wlOrderList.jsp";
   }

   /**
    * 跳转抢单信息页面
    */
   @RequestMapping("grabLogisticsList")
   public String grabLogisticsList(@RequestParam("logisticsInfoId") String logisticsInfoId, HttpServletRequest request, Model model) {
      User user = (User) request.getSession().getAttribute("user");
      long LIId = Long.parseLong(logisticsInfoId);
      Map<String, Object> info = customerServiceImpl.grabLogisticsListInfo(LIId);
      model.addAttribute("info", info);
      model.addAttribute("logisticsInfoId", LIId);
      model.addAttribute("username", user.getUserName());
      model.addAttribute("parentMenuNo", "1");
      model.addAttribute("childMenuNo", "2");
      return "views/front/customer/order/editQiangdan.jsp";
   }

   /**
    * 买家选择物流抢单
    *
    * @param
    * @param
    * @param
    */
   @RequestMapping("chooseGrab")
   @ResponseBody
   public R chooseGrab(@RequestParam Map param) {
      try {
         customerServiceImpl.chooseGrab(param);
      } catch (Exception e) {
         e.printStackTrace();
         return R.error();
      }
      return R.ok();
   }

   @RequestMapping("testPayLogistics")
   @ResponseBody
   public R testPayLogistics(@RequestParam("logisticsInfoId") String logisticsInfoId) {
      try {
         customerServiceImpl.testPayLogistics(Long.parseLong(logisticsInfoId));
      } catch (NumberFormatException e) {
         e.printStackTrace();
         return R.error();
      }
      return R.ok();
   }

   @RequestMapping("testPayOrder")
   @ResponseBody
   public R testPayOrder(@RequestParam("orderId") String orderId) {
      try {
         int res = customerServiceImpl.testPayOrder(Long.parseLong(orderId));
         if (res > 0) {
            return R.ok();
         }
      } catch (Exception e) {
         e.printStackTrace();
         return R.error();
      }
      return R.error();
   }

   /**
    * 买家支付订单的处理方法
    *
    * @param request
    * @param response
    */
   @RequestMapping(
           value = {"payOrder"},
           method = {RequestMethod.POST}
   )
   public void payOrder(HttpServletRequest request, HttpServletResponse response) {
      String ret = "-1";
      JSONObject obj = new JSONObject();
      obj.put("content", ConstantUtil.MSG_PARAM_ERR);
      String orderId = CommonUtil.GetRequestParam(request, "orderId", "0");
      String saleMoney = CommonUtil.GetRequestParam(request, "saleMoney", "0.00");
      String loan = CommonUtil.GetRequestParam(request, "loan", "0");
      String balance = CommonUtil.GetRequestParam(request, "balance", "0");
      String zfMethod = CommonUtil.GetRequestParam(request, "zfMethod", "2");
      String certificateImg = "";
      String ip = IPUtils.getIpAddr(request);
      try {
         //资金不能从session里面拿！！！
         User user = (User) request.getSession().getAttribute("user");
         if (!customerServiceImpl.isBalanceEnough(user.getUserId(), new BigDecimal(saleMoney), Integer.parseInt(zfMethod), new BigDecimal(balance), new BigDecimal(loan))) {
            obj.put("content", ConstantUtil.MSG_MONEY_ERR);
            obj.put("status", "-1");
            this.echo(response, obj);
            return;
         }
         if (zfMethod.equals("5")) {
            certificateImg = CommonUtil.GetRequestParam(request, "certificateImg", "");
            if ("".equals(certificateImg)) {
               obj.put("content", ConstantUtil.MSG_PARAM_ERR);
               obj.put("status", "-1");
               this.echo(response, obj);
               return;
            }
         }
         if (user != null && CommonUtil.getIntFromString(orderId) > 0) {
            Map orderObj = this.getMyOneSingBO("wa_order", "order_id", Long.parseLong(orderId));
            if (orderObj != null && orderObj.get("userId") != null && (long) orderObj.get("userId") == (user.getUserId()) && orderObj.get("orderState") != null && ((int) orderObj.get("orderState") == 1)) {

               int zfMethodi=Integer.parseInt(zfMethod);
               if (zfMethodi==2||zfMethodi==4){
                  BigDecimal Bbalance=new BigDecimal(balance);
                  if (zfMethodi==2){
                     Bbalance=new BigDecimal(saleMoney);
                  }
                  UserSubAccount userSubAccount=financeServiceImpl.getUserSubAccountByUserId(user.getUserId());
                  Map<String,String> paramss=new HashMap<>();
                  paramss.put("payAccNo",userSubAccount.getSubAccNo().toString());
                  paramss.put("tranAmt",Bbalance.toString());
                  String result=CommonUtil.connectCNCBLocalServer(ConstantUtil.CNCB_SERVER_BASEURL+"forceTransfer2TransferAccNo",paramss);
                  R r= JSON.parseObject(result,R.class);
                  ForceTransferBasicInfo forceTransferBasicInfo=JSON.parseObject(r.get("forceTransferBasicInfo").toString(),ForceTransferBasicInfo.class);
                  CncbTrans cncbTrans=new CncbTrans();
                  cncbTrans.setXml(forceTransferBasicInfo.getXml());
                  cncbTrans.setClientId(forceTransferBasicInfo.getClientID());
                  cncbTrans.setTime(new Date());
                  cncbTrans.setType((byte)1);
                  JSONObject operationParamsObj=new JSONObject();
                  operationParamsObj.put("sql"," CALL khFukuanProcess(\'" + user.getUserId() + "\',\'" + orderId + "\',\'" + saleMoney + "\',\'" + zfMethod + "\',\'" + balance + "\',\'" + loan + "\',\'" + certificateImg + "\',\'" + ip + "\')");
                  operationParamsObj.put("orderId",orderId);
                  cncbTrans.setOperationParams(operationParamsObj.toJSONString());
                  cncbTransDao.create(cncbTrans);
                  Map<String,Object> query=new HashMap<>();
                  query.put("orderId",Long.parseLong(orderId));
                  query.put("prodPayState",3);
                  orderDao.updateOrderByID(query);
                  ret="1";
                  obj.put("content","处理中...");
               }else {
                  String sql = "";
                  sql = " CALL khFukuanProcess(\'" + user.getUserId() + "\',\'" + orderId + "\',\'" + saleMoney + "\',\'" + zfMethod + "\',\'" + balance + "\',\'" + loan + "\',\'" + certificateImg + "\',\'" + ip + "\')";
                  HashMap queryParam = new HashMap();
                  queryParam.put("strsql", sql);
                  this.commonMapper.simpleSelectReturnList(queryParam);
                  waOrderServiceImpl.checkOrderRepayOff(Long.parseLong(orderId));
                  ret = "1";
                  obj.put("content", ConstantUtil.MSG_SUCCESS);
               }
            }
         }
      }catch (Exception var22) {
         ret = "-2";
         obj.put("content", ConstantUtil.MSG_FAILS);
      }
      obj.put("status", ret);
      this.echoJSON(response, obj);
   }

   @RequestMapping(value = {"payLogistics"},
           method = {RequestMethod.POST})
   @ResponseBody
   public JSONObject payLogistics(@RequestParam Map<String,Object> params, HttpServletRequest request, HttpServletResponse response) throws Exception {
      JSONObject obj = new JSONObject();
      String orderId = CommonUtil.GetRequestParam(request, "orderId", "0");
      String logisticsInfoId = CommonUtil.GetRequestParam(request, "logisticsInfoId", "0");
      String grabMoney = CommonUtil.GetRequestParam(request, "grabMoney", "0");
      String rate = CommonUtil.GetRequestParam(request, "rate", "0");
      String balance = CommonUtil.GetRequestParam(request, "balance", "0");
      String loan = CommonUtil.GetRequestParam(request, "loan", "0");
      String zfMethod = CommonUtil.GetRequestParam(request, "zfMethod", "2");
      String certificateImg = "";
      String ip = IPUtils.getIpAddr(request);
      User user = (User) request.getSession().getAttribute("user");
      if (!customerServiceImpl.isBalanceEnough(user.getUserId(), new BigDecimal(grabMoney), Integer.parseInt(zfMethod), new BigDecimal(balance), new BigDecimal(loan))) {
         obj.put("content", ConstantUtil.MSG_MONEY_ERR);
         obj.put("status", "-1");
         return obj;
      }
      if (zfMethod.equals("5")) {
         certificateImg = CommonUtil.GetRequestParam(request, "certificateImg", "");
         if ("".equals(certificateImg)) {
            obj.put("content", ConstantUtil.MSG_PARAM_ERR);
            obj.put("status", "-1");
            return obj;
         }
      }
      if (user != null && CommonUtil.getIntFromString(orderId) > 0) {
         Map orderObj = this.getMyOneSingBO("wa_order", "order_id", Long.parseLong(orderId));
         if (orderObj != null && orderObj.get("userId") != null && (long) orderObj.get("userId") == (user.getUserId()) && orderObj.get("orderState") != null && ((int) orderObj.get("orderState") == 1 || (int) orderObj.get("orderState") == 11) || (int) orderObj.get("orderState") == 13) {

            int zfMethodi=Integer.parseInt(zfMethod);
            if (zfMethodi==2||zfMethodi==4) {
               BigDecimal Bbalance = new BigDecimal(balance);
               if (zfMethodi == 2) {
                  Bbalance = new BigDecimal(grabMoney);
               }
               Map<String,Object> query=new HashMap<>();
               query.put("userId",user.getUserId());
               UserSubAccount userSubAccount=userSubAccountDao.singleQuery(query);
               Map<String,String> paramss=new HashMap<>();
               paramss.put("payAccNo",userSubAccount.getSubAccNo().toString());
               paramss.put("tranAmt",Bbalance.toString());
               String result=CommonUtil.connectCNCBLocalServer(ConstantUtil.CNCB_SERVER_BASEURL+"forceTransfer2TransferAccNo",paramss);
               R r= JSON.parseObject(result,R.class);
               ForceTransferBasicInfo forceTransferBasicInfo=JSON.parseObject(r.get("forceTransferBasicInfo").toString(),ForceTransferBasicInfo.class);
               CncbTrans cncbTrans=new CncbTrans();
               cncbTrans.setXml(forceTransferBasicInfo.getXml());
               cncbTrans.setClientId(forceTransferBasicInfo.getClientID());
               cncbTrans.setTime(new Date());
               cncbTrans.setType((byte)3);
               JSONObject operationParamsObj=new JSONObject();
               operationParamsObj.put("sql"," CALL logisticsPayProcess(\'" + user.getUserId() + "\',\'" + orderId + "\',\'" + logisticsInfoId + "\',\'" + grabMoney + "\',\'" + zfMethod + "\',\'" + balance + "\',\'" + loan + "\',\'" + rate + "\',\'" + certificateImg + "\',\'" + ip + "\')");
               operationParamsObj.put("orderId",orderId);
               cncbTrans.setOperationParams(operationParamsObj.toJSONString());
               cncbTransDao.create(cncbTrans);
               query.clear();
               query.put("orderId",Long.parseLong(orderId));
               query.put("logisticsPayState",3);
               orderDao.updateOrderByID(query);
               obj.put("status", "1");
               obj.put("content","处理中...");
               return obj;
            }else{
               String sql = "";
               sql = " CALL logisticsPayProcess(\'" + user.getUserId() + "\',\'" + orderId + "\',\'" + logisticsInfoId + "\',\'" + grabMoney + "\',\'" + zfMethod + "\',\'" + balance + "\',\'" + loan + "\',\'" + rate + "\',\'" + certificateImg + "\',\'" + ip + "\')";
               HashMap queryParam = new HashMap();
               queryParam.put("strsql", sql);
               ArrayList<Map<String, Object>> result = this.commonMapper.simpleSelectReturnList(queryParam);
               if ((int) result.get(0).get("result") == 1) {
                  waOrderServiceImpl.checkOrderRepayOff(Long.parseLong(orderId));
                  obj.put("content", ConstantUtil.MSG_SUCCESS);
                  obj.put("status", "1");
                  return obj;
               }
            }
         }
      }
      obj.put("content", ConstantUtil.MSG_FAILS);
      obj.put("status", "-1");
      return obj;
   }

   /**
    * 跳转订单付款页面
    *
    * @param orderId
    * @param request
    * @param model
    * @return
    */
   @RequestMapping("goPayOrder")
   public String goPayOrder(@RequestParam("orderId") String orderId, HttpServletRequest request, Model model) {
      User user = (User) request.getSession().getAttribute("user");
      Map<String, Object> orderInfo = customerServiceImpl.getPayOrderPageInfo(Long.parseLong(orderId), user.getUserId());
      model.addAttribute("orderInfo", orderInfo);
      model.addAttribute("user", user);
      model.addAttribute("userSumCredit",customerServiceImpl.getUserCreditSjMoney(user.getUserId()));
      model.addAttribute("parentMenuNo", "2");
      return "views/front/customer/order/editFukuan.jsp";
   }

   @RequestMapping("goPayLogistics")
   public String goPayLogistics(@RequestParam("logisticsInfoId") String logisticsInfoId, HttpServletRequest request, Model model) {
      User user = (User) request.getSession().getAttribute("user");
      Map<String, Object> logisticsInfo = customerServiceImpl.getPayLogisticsPageInfo(Long.parseLong(logisticsInfoId), user.getUserId());
      model.addAttribute("logisticsInfo", logisticsInfo);
      model.addAttribute("user", user);
      model.addAttribute("userSumCredit",customerServiceImpl.getUserCreditSjMoney(user.getUserId()));
      model.addAttribute("parentMenuNo", "2");
      return "views/front/customer/order/payLogistics.jsp";
   }

   @RequestMapping(
           value = {"getRegionList"},
           method = {RequestMethod.POST}
   )
   public void getRegionList(HttpServletRequest request, HttpServletResponse response, Model model) {
      JSONObject obj = new JSONObject();
      String pid = CommonUtil.GetRequestParam(request, "pid", "0");
      Map param = this.getConditionParam(request);
      param.put("parentRegionId", pid);
      List regionList = customerServiceImpl.getRegionList(param);
      obj.put("regionList", regionList);
      this.echoJSON(response, obj);
   }

   @RequestMapping("password")
   public String changePassword(Model model) {
      HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
      User user = (User) httpSession.getAttribute("user");
      model.addAttribute("parentMenuNo", "4");
      model.addAttribute("childMenuNo", "3");
      model.addAttribute("userName", user.getUserName());
      return "views/front/customer/company/changePass.jsp";
   }

   /**
    * 修改支付密码
    *
    * @param map
    */
   @RequestMapping("changePayPassword")
   @ResponseBody
   public R changePayPassword(@RequestParam Map<String, Object> map) {
      HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
      User user = (User) httpSession.getAttribute("user");
      String paynewpass = map.get("paynewpass").toString();
      //MD5加密
      String password = CommonUtil.MD5(paynewpass);
      user.setCzPass(password);
      Map<String, Object> updateMap = new HashedMap();
      updateMap.put("userId", user.getUserId());
      updateMap.put("czPassword", user.getCzPass());
      try {
         userDao.resetPassword(updateMap);
         return R.ok().put("state", 1);
      } catch (Exception e) {
         e.printStackTrace();
         return R.error().put("state", 0);
      }
   }

   /**
    * 检验支付密码
    * 修改支付密码前要输入原有的支付密码，原有的支付密码从session中获取
    *
    * @param map
    */
   @RequestMapping("checkPayPassword")
   @ResponseBody
   public R checkPayPassword(@RequestParam Map<String, Object> map) {
      HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
      User user = (User) httpSession.getAttribute("user");
      String payoldpass = map.get("oldPass").toString();
      //MD5加密
      String password = CommonUtil.MD5(payoldpass);
      if (password.equals(user.getCzPass())) {
         return R.ok().put("state", 1);
      } else {
         return R.error().put("state", 0);
      }
   }

   /**
    * 修改登录密码
    * 1.修改session中的操作密码，2.修改数据库中session密码
    *
    * @param map
    */
   @RequestMapping("changeLoginPassword")
   @ResponseBody
   public R changeLoginPassword(@RequestParam Map<String, Object> map) {
      HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
      User user = (User) httpSession.getAttribute("user");
      String newpass = map.get("newpass").toString();
      //MD5加密
      String password = CommonUtil.MD5(newpass);
      user.setUserPass(password);
      Map<String, Object> updateMap = new HashedMap();
      updateMap.put("userId", user.getUserId());
      updateMap.put("userPassword", user.getUserPass());
      try {
         userDao.resetPassword(updateMap);
         return R.ok().put("state", 1);
      } catch (Exception e) {
         e.printStackTrace();
         return R.error().put("state", 0);
      }
   }

   /**
    * 检验登录密码
    * 修改登录密码前要输入原有的登录密码，原有的登录密码可以从session中获取
    *
    * @param map
    */
   @RequestMapping("checkLoginPassword")
   @ResponseBody
   public R checkLoginPassword(@RequestParam Map<String, Object> map) {
      HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
      User user = (User) httpSession.getAttribute("user");
      String payoldpass = map.get("oldPass").toString();
      //MD5加密
      String password = CommonUtil.MD5(payoldpass);
      if (password.equals(user.getUserPass())) {
         return R.ok().put("state", 1);
      } else {
         return R.error().put("state", 0);
      }
   }

   @RequestMapping("checkCzPassword")
   @ResponseBody
   public R checkWithdrawPassword(@RequestParam("pass") String oldPass, HttpServletResponse response) {
      HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
      User user = (User) httpSession.getAttribute("user");
      //MD5加密
      String password = CommonUtil.MD5(oldPass);
      if (password.equals(user.getCzPass())) {
         return R.ok();
      } else {
         return R.error().put("msg","支付密码错误");
      }
   }

   @RequestMapping("prodDetail")
   public String prodDetail(@RequestParam("prodId") String prodId, Model model) {
      User user = (User) HttpContextUtils.getAttribute("user");


      model.addAttribute("userName", user.getUserName());
      model.addAttribute("parentMenuNo", "5");
      return "views/front/customer/order/prodDetail.jsp";
   }

   @RequestMapping("prodList")
   public String prodList(@RequestParam Map<String, Object> map, Model model) {
      User user = (User) HttpContextUtils.getAttribute("user");
      map.put("userId", user.getSupplyId());
      map.put("prodState",2);
      List<Prod> prodList = prodDao.findProdByUserId(map);
      model.addAttribute("spList", prodList);
      model.addAttribute("userName", user.getUserName());
      model.addAttribute("parentMenuNo", "5");
      model.addAttribute("childMenuNo", "0");
      return "views/front/customer/prod/prodList.jsp";
   }


   /**
    * 买方订单列表
    *
    * @param request
    * @param model
    * @return
    */
   @RequestMapping("orderList")
   public String orderList(HttpServletRequest request, Model model) {
      User user = (User) HttpContextUtils.getAttribute("user");
      Map param = this.getConditionParam(request);
      param.put("userId", user.getUserId());
      ArrayList<Map<String, Object>> waOrderList = this.customerBackOrderMapper.getWaOrderList(param);
      ConvertUtil.convertDataBaseMapToJavaMap(waOrderList);
      for (Map<String, Object> waOrder : waOrderList) {
         Map<String, Object> orderlog = waOrderServiceImpl.findNewestOrderLog(Long.parseLong(waOrder.get("orderId").toString()));
         if (orderlog != null && orderlog.size() > 0) {
            waOrder.putAll(orderlog);
         }
      }
      model.addAttribute("waOrderList", waOrderList);
      int totalCount = this.customerBackOrderMapper.getWaOrderListCount(param);
      this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
      model.addAttribute("userName", user.getUserName());
      model.addAttribute("parentMenuNo", "1");
      model.addAttribute("childMenuNo", "1");
      return "views/front/customer/order/prodOrderList.jsp";
   }

   /**
    * 重复，要删
    * 下订单界面
    *
    * @param
    * @param model
    * @return
    */
   @RequestMapping(value = "orderPage", method = RequestMethod.GET)
   public String orderPage(@RequestParam String prodId, Model model) {
      HashMap param = new HashMap();
      param.put("prodId", prodId);
      Map prod = this.prodDao.findProdById(param);
      ConvertUtil.convertDataBaseMapToJavaMap(prod);
      String regionId = "";
      String regionName = "";
      if (prod != null) {
         regionId = prod.get("prodRegionId").toString();
         try {
            regionName = this.getRegionDetailName(regionId);
         } catch (Exception var12) {
            var12.printStackTrace();
         }
      }
      prod.put("fromRegionName", regionName);
      HashMap param1 = new HashMap();
      param1.put("parentRegionId", "0");
      model.addAttribute("provinceList", this.getChildRegionList(0));
      User user = (User) HttpContextUtils.getAttribute("user");
      model.addAttribute("userName", user.getUserName());
      model.addAttribute("prod", prod);
      model.addAttribute("prodId", prodId);
      model.addAttribute("parentMenuNo", "1");
      model.addAttribute("childMenuNo", "1");
      return "views/front/customer/orderPage_new.jsp";
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
      return "views/front/customer/finance/accountInfo.jsp";
   }

   @RequestMapping({"messagePage"})
   public String news_list(HttpServletRequest request, Model model) {
      User user = (User) HttpContextUtils.getAttribute("user");
      Map param = this.getConditionParam(request);
      param.put("userId", user.getUserId());
      ArrayList newsList = this.newsMapper.getNewsList(param);
      ConvertUtil.convertDataBaseMapToJavaMap(newsList);
      model.addAttribute("newsList", newsList);
      int totalCount = this.newsMapper.getNewsListCount(param);
      this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
      param.put("strsql", "SELECT COUNT(*) FROM wa_info WHERE is_read = 0 AND user_id = " + user.getUserId());
      model.addAttribute("noReadCount", Integer.valueOf(this.commonMapper.simpleSelectReturnInt(param)));
      model.addAttribute("totalCount", Integer.valueOf(totalCount));
      model.addAttribute("parentMenuNo", "3");
      model.addAttribute("childMenuNo", "1");
      model.addAttribute("userName", user.getUserName());
      return "views/front/customer/news/xxList.jsp";
   }

   /**
    * 进入个人中心，查看企业信息
    *
    * @param model
    * @return
    */
   @RequestMapping("companyInfo")
   public String companyInfo(Model model) {
      HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
      Map user = userDao.singleUserByPrimaryKey(((User) httpSession.getAttribute("user")).getUserId());
      ConvertUtil.convertDataBaseMapToJavaMap(user);
      Map userinfo = userinfoDao.singleUserinfoByPrimaryKey(((Userinfo) httpSession.getAttribute("userInfo")).getUserId());
      ConvertUtil.convertDataBaseMapToJavaMap(userinfo);
      model.addAttribute("user", user);
      model.addAttribute("userInfo", userinfo);
      model.addAttribute("parentMenuNo", "4");
      model.addAttribute("childMenuNo", "1");
      String pParam = userinfo.get("zcRegionId").toString().substring(0, 2) + "0000";
      String cParam = userinfo.get("zcRegionId").toString().substring(0, 4) + "00";
      //省列表
      model.addAttribute("provinceList", this.getChildRegionList(0));
      model.addAttribute("provinceId", pParam);
      //市列表
      model.addAttribute("cityList", this.getChildRegionList(CommonUtil.getIntFromString(pParam)));
      model.addAttribute("cityId", cParam);
      //区列表
      model.addAttribute("countyList", this.getChildRegionList(CommonUtil.getIntFromString(cParam)));
      model.addAttribute("userName", user.get("userName"));
      return "views/front/customer/company/companyInfo.jsp";
   }

   /**
    * @param model
    * @return
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
      return "views/front/customer/company/bankcard.jsp";
   }

   /**
    * 进入联系方式的修改页面
    * @param model
    * @return
    */
   @RequestMapping("contact")
   public String contact(Model model) {
      HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
      User user = (User) httpSession.getAttribute("user");
      Userinfo userinfo = (Userinfo) httpSession.getAttribute("userInfo");
      model.addAttribute("parentMenuNo", "4");
      model.addAttribute("childMenuNo", "2");
      model.addAttribute("user", user);
      model.addAttribute("userInfo", userinfo);
      model.addAttribute("userName", user.getUserName());
      return "views/front/customer/company/contactMode.jsp";
   }

   @RequestMapping("updateContact")
   @ResponseBody
   public R updateContext(@RequestParam Map<String, Object> params) {
      HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
      User user = (User) httpSession.getAttribute("user");
      params.put("userId", user.getUserId());
      try {
         userinfoDao.updateUserinfoByUserId(params);
         return R.ok().put("state", 1);
      } catch (Exception e) {
         e.printStackTrace();
         return R.error().put("state", 0);
      }
   }

   @RequestMapping("withdraw")
   public String withdraw(HttpServletRequest request, Model model) {
      User user = (User) HttpContextUtils.getAttribute("user");
      String userId = user.getUserId().toString();
      Map param = this.getConditionParam(request);
      param.put("geTxState", "0");
      param.put("ltTxState", "3");
      param.put("userId", userId);
      List list = withdrawDAO.getWithdrawListByUserId(param);
      ConvertUtil.convertDataBaseMapToJavaMap(list);
      int totalCount = withdrawDAO.total(param);
      Map retInfo = withdrawDAO.getMoneyInfo(param);
      this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
      model.addAttribute("parentMenuNo", "2");
      model.addAttribute("childMenuNo", "6");
      model.addAttribute("userName", user.getUserName());
      model.addAttribute("txMoney", retInfo.get("txMoney"));
      model.addAttribute("list", list);
      return "views/front/customer/finance/txList.jsp";
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

   @RequestMapping({"rechargeRecord"})
   public String rechargeRecord(HttpServletRequest request, Model model) {
      User user = (User) HttpContextUtils.getAttribute("user");
      String userId = user.getUserId().toString();
      Map param = this.getConditionParam(request);
      param.put("userId", userId);
      List list = bankOrderDao.listPoByConditions(param);
      ConvertUtil.convertDataBaseMapToJavaMap(list);
      int totalCount = bankOrderDao.listPoByConditionsCount(param);
      /*Map retInfo = this.bankOrderMapper0.getCzMoneyInfo(param);*/
      this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
      model.addAttribute("list", list);
      model.addAttribute("parentMenuNo", "2");
      model.addAttribute("childMenuNo", "5");
      model.addAttribute("userName", user.getUserName());
      /*model.addAttribute("zfMoney", retInfo.get("zfMoney"));*/
      return "views/front/customer/finance/czSqList.jsp";
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
      model.addAttribute("childMenuNo", "2");
      model.addAttribute("userName", user.getUserName());
      model.addAttribute("withdrawMoney", retInfo.get("withdrawMoney"));
      model.addAttribute("list", list);
      return "views/front/customer/finance/txList.jsp";
   }


   /**
    * 添加银行卡的异步请求
    *
    * @return
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
    * 添加银行卡的异步请求
    *
    * @return
    */
   @RequestMapping("getCards")
   @ResponseBody
   public R getCards(@RequestParam Map<String, Object> map) {
      User user = (User) HttpContextUtils.getAttribute("user");
      long userId = user.getUserId();
      try {
         List<Bankcard> cards = bankcardDao.getCardListByUserId(userId);
         return R.ok().put("content", "添加成功").put("Cards", cards);
      } catch (Exception e) {
         e.printStackTrace();
         return R.error();
      }
   }

   /**
    * 添加银行卡的异步请求
    *
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
    * 买家取消订单
    *
    * @param request
    * @return
    */
   @RequestMapping("cancelOrder")
   @ResponseBody
   public Object cancelOrder(HttpServletRequest request) {
      JSONObject obj = new JSONObject();
      String orderId = CommonUtil.GetRequestParam(request, "orderId", "0");
      User user = (User) request.getSession().getAttribute("user");
      String ip = IPUtils.getIpAddr(request);
      String sql = "";
      sql = " CALL customerCancelOrder(\'" + user.getUserId() + "\',\'" + orderId + "\',\'" + ip + "\')";
      HashMap queryParam = new HashMap();
      queryParam.put("strsql", sql);
      ArrayList<Map<String, Object>> result = this.commonMapper.simpleSelectReturnList(queryParam);
      if ((int) result.get(0).get("result") == 1) {
         obj.put("content", ConstantUtil.MSG_SUCCESS);
         obj.put("status", 1);
         return obj;
      }
      obj.put("status", -1);
      return obj;
   }

   /**
    * 授信账户页面
    *
    * @param request
    * @param model
    * @return
    */
   @RequestMapping({"creditAccount"})
   public String creditAccount(HttpServletRequest request, Model model) {
      User user = (User) request.getSession().getAttribute("user");
      long userId = user.getUserId();
      Map<String, Object> info = customerServiceImpl.findCreditAccountPageInfo(userId);
      model.addAttribute("info", info);
      //已用额度
      BigDecimal sumLoans = customerServiceImpl.getLoansSum(userId);
      model.addAttribute("sumLoans", sumLoans);
      //未还款授信
      Map param = this.getConditionParam(request);
      param.put("userId", userId);

      //loanlist
      List<Map<String, Object>> loans = customerServiceImpl.getLoansIndebt(param);
      int totalCount = customerServiceImpl.getLoansIndebtCount(param);
      this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
      model.addAttribute("loans", loans);

      model.addAttribute("parentMenuNo", "6");
      model.addAttribute("childMenuNo", "1");
      return "views/front/customer/finance/creditAccount_new.jsp";
   }

   /**
    * 跳转授信申请页面
    *
    * @param request
    * @return
    */
   @RequestMapping("creditApply")
   public String creditApply(HttpServletRequest request, Model model) {
      User user = (User) request.getSession().getAttribute("user");
      Map<String, Object> info = customerServiceImpl.findCreditApplyPageInfo(user.getUserId());
      model.addAttribute("info", info);
      return "views/front/customer/finance/sxsq_new.jsp";
   }

   /**
    * 提交授信额度申请
    *
    * @param params
    * @param request
    * @return
    */
   @RequestMapping("applyCreditLimit")
   public String applyCreditLimit(@RequestParam Map<String, Object> params, HttpServletRequest request) {
      User user = (User) request.getSession().getAttribute("user");
      params.put("creditMoney", Integer.parseInt((String) params.get("creditMoney")));
      params.put("userId", user.getUserId());
      params.put("ip", IPUtils.getIpAddr(request));
      customerServiceImpl.applyCreditLimit(params);
      return "views/front/customer/finance/applyCreditSuccess.jsp";
   }

   @RequestMapping("isCreditApplyAvailable")
   @ResponseBody
   public R isCreditApplyAvailable(HttpServletRequest request) {
      User user = (User) request.getSession().getAttribute("user");
      boolean flag = customerServiceImpl.isCreditApplyAvailable(user.getUserId());
      if (flag) {
         return R.ok();
      }
      return R.error("当前申请正在审核中");
   }

   /**
    * 跳转还款页面
    *
    * @param loanId
    * @return
    */
   @RequestMapping("goRepayLoan")
   public String goRepayLoan(@RequestParam("loanId") String loanId, Model model) {

      Map<String, Object> loan = financeServiceImpl.getLoanOrderInfo(Long.parseLong(loanId));
      model.addAttribute("loan", loan);

      model.addAttribute("parentMenuNo", "2");
      return "views/front/customer/finance/repayment.jsp";
   }

   /**
    * 还贷款处理方法
    *
    * @param loanId
    * @param repayMoney
    * @param interest
    * @param request
    * @return
    */
   @RequestMapping("repayLoan")
   @ResponseBody
   public R repayLoan(@RequestParam("loanId") String loanId, @RequestParam("repayMoney") String repayMoney, @RequestParam("interest") String interest, HttpServletRequest request) {
      User user = (User) request.getSession().getAttribute("user");
      long userId = user.getUserId();
      String ip = IPUtils.getIpAddr(request);
      BigDecimal bInterest = new BigDecimal(interest);
      BigDecimal principal = new BigDecimal(repayMoney).subtract(bInterest);
      try {
         int res = customerServiceImpl.beforeRepayLoanByBalance(userId, Long.parseLong(loanId), principal, bInterest, ip);
         if (res == 0) {
            return R.error("账户余额不足");
         }
      } catch (Exception e) {
         e.printStackTrace();
         return R.error();
      }
      return R.ok();
   }

   @RequestMapping("creditApplys")
   public String creditApplys(Model model, HttpServletRequest request) {
      User user = (User) request.getSession().getAttribute("user");
      Map param = this.getConditionParam(request);
      param.put("userId", user.getUserId());
      List<Map<String, Object>> credits = customerServiceImpl.getCreditList(param);
      int totalCount = customerServiceImpl.getCreditListCount(param);
      this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
      model.addAttribute("credits", credits);

      model.addAttribute("parentMenuNo", "6");
      model.addAttribute("childMenuNo", "2");
      return "views/front/customer/finance/creditApplyRecords.jsp";
   }

   @RequestMapping("loansRepayRecords")
   public String loansRepayRecords(Model model,HttpServletRequest request){
      User user=(User)request.getSession().getAttribute("user");
      Map param = this.getConditionParam(request,2);
      param.put("userId",user.getUserId());
      List<Map<String,Object>> loans=customerServiceImpl.getLoansRepayDetail(param);
      int totalCount=customerServiceImpl.getLoansRepayDetailCount(param);
      this.setPagenationInfo(request,totalCount,Integer.parseInt(param.get("page").toString()),2);
      model.addAttribute("loans",loans);
      model.addAttribute("parentMenuNo", "6");
      model.addAttribute("childMenuNo", "3");
      return "views/front/customer/finance/repayRecords.jsp";
   }

   @RequestMapping("loanRepayDetailPage")
   public String loanRepayDetailPage(Model model){
      model.addAttribute("parentMenuNo", "6");
      model.addAttribute("childMenuNo", "3");
      return "views/front/customer/finance/loanRepayDetail.jsp";
   }




   @RequestMapping("reportManagement")
   public String reportManagement(Model model){
      model.addAttribute("parentMenuNo","4");
      model.addAttribute("childMenuNo","5");
      return "views/front/customer/company/reportFrom.jsp";
   }

   @RequestMapping(value = "goSecondPayOrder",method = RequestMethod.GET)
   public String goSecondPay(@RequestParam(value = "orderId")long orderId,Model model){
      model.addAttribute("parentMenuNo", "2");
      return "views/front/customer/order/secondPayOrder.jsp";
   }


   @RequestMapping(value = "secondPayOrderSub",method = RequestMethod.POST)
   @ResponseBody
   @Transactional
   public R secondPayOrderSub(@RequestParam("orderId")long orderId,@RequestParam("secondPayMoney")BigDecimal secondPayMoney,
                              @RequestParam(value = "zfMethod",required = false,defaultValue = "2")int zfMethod,@RequestParam(value = "balance",required = false,defaultValue = "0")BigDecimal balance,
                              @RequestParam(value = "loan",required = false,defaultValue = "0")BigDecimal loan,@RequestParam(value = "certificateImg",required = false,defaultValue = "")String certificateImg,
                              HttpServletRequest request
                     ) throws Exception {
      BigDecimal zero=new BigDecimal(0);
      User user=(User)request.getSession().getAttribute("user");
      UserSubAccount userSubAccount=financeServiceImpl.getUserSubAccountByUserId(user.getUserId());
      String subAccNo=userSubAccount.getSubAccNo();
      String subAccNm=userSubAccount.getSubAccNm();
      Map<String,Object> param=new HashMap<>();
      Map<String,String> paramss=new HashMap<>();
      //多退
      if (secondPayMoney.compareTo(zero)<0){
         paramss.clear();
         paramss.put("recvAccNo",subAccNo);
         paramss.put("recvAccNm",subAccNm);
         paramss.put("tranAmt",secondPayMoney.abs().toString());
         String result=CommonUtil.connectCNCBLocalServer(ConstantUtil.CNCB_SERVER_BASEURL+"forceTransferFromTransferAccNo",paramss);
         R r= JSON.parseObject(result,R.class);
         ForceTransferBasicInfo forceTransferBasicInfo=JSON.parseObject(r.get("forceTransferBasicInfo").toString(),ForceTransferBasicInfo.class);
         CncbTrans cncbTrans=new CncbTrans();
         cncbTrans.setXml(forceTransferBasicInfo.getXml());
         cncbTrans.setClientId(forceTransferBasicInfo.getClientID());
         cncbTrans.setTime(new Date());
         cncbTrans.setType((byte)5);
         JSONObject operationParamsObj=new JSONObject();
         operationParamsObj.put("orderId",orderId);
         operationParamsObj.put("secondPayMoney",secondPayMoney.abs());
         operationParamsObj.put("zfMethod",zfMethod);
         operationParamsObj.put("balance",balance);
         operationParamsObj.put("loan",loan);
         operationParamsObj.put("certificateImg",certificateImg);
         cncbTrans.setOperationParams(operationParamsObj.toJSONString());
         cncbTransDao.create(cncbTrans);
         param.put("orderId",orderId);
         param.put("prod2ndpayState",3);
         orderDao.updateOrderByID(param);
         return R.ok().put("msg","结算中...");
      }else if (secondPayMoney.compareTo(zero)==0){
         customerServiceImpl.handle2ndPayProd(orderId,secondPayMoney,zfMethod,balance,loan,certificateImg);
         return R.ok().put("msg","结算中...");
      }else if(secondPayMoney.compareTo(zero)>0){
         if(zfMethod==2){
            paramss.clear();
            paramss.put("payAccNo",subAccNo);
            paramss.put("tranAmt",secondPayMoney.toString());
            String result=CommonUtil.connectCNCBLocalServer(ConstantUtil.CNCB_SERVER_BASEURL+"forceTransfer2TransferAccNo",paramss);
            R r= JSON.parseObject(result,R.class);
            ForceTransferBasicInfo forceTransferBasicInfo=JSON.parseObject(r.get("forceTransferBasicInfo").toString(),ForceTransferBasicInfo.class);
            CncbTrans cncbTrans=new CncbTrans();
            cncbTrans.setXml(forceTransferBasicInfo.getXml());
            cncbTrans.setClientId(forceTransferBasicInfo.getClientID());
            cncbTrans.setTime(new Date());
            cncbTrans.setType((byte)5);
            JSONObject operationParamsObj=new JSONObject();
            operationParamsObj.put("orderId",orderId);
            operationParamsObj.put("secondPayMoney",secondPayMoney);
            operationParamsObj.put("zfMethod",zfMethod);
            operationParamsObj.put("balance",balance);
            operationParamsObj.put("loan",loan);
            operationParamsObj.put("certificateImg",certificateImg);
            cncbTrans.setOperationParams(operationParamsObj.toJSONString());
            cncbTransDao.create(cncbTrans);
            param.put("orderId",orderId);
            param.put("prod2ndpayState",3);
            orderDao.updateOrderByID(param);
            return R.ok().put("msg","结算中...");
         }else {
            return R.error().put("msg","暂不支持余额外的支付方式");
         }
      }
      return R.ok();
   }

   @RequestMapping(value = "secondPayLogisticsSub",method = RequestMethod.POST)
   @ResponseBody
   @Transactional
   public R secondPayLogisticsSub(@RequestParam("logisticsId")long logisticsId) throws Exception {
      Map<String,Object> logisticsInfo=logisticsInfoDao.singleLogisticsInfoByPrimaryKey(logisticsId);
      BigDecimal prePayment=(BigDecimal)logisticsInfo.get("pre_payment");
      long senderUserId=(long)logisticsInfo.get("sender_user_id");
      long orderId=(long)logisticsInfo.get("order_id");
      UserSubAccount senderSubAccount=financeServiceImpl.getUserSubAccountByUserId(senderUserId);
      String subAccNo=senderSubAccount.getSubAccNo();
      String subAccNm=senderSubAccount.getSubAccNm();
      Map<String,String> paramss=new HashMap<>();
      paramss.put("recvAccNo",subAccNo);
      paramss.put("recvAccNm",subAccNm);
      paramss.put("tranAmt",prePayment.toString());
      String result=CommonUtil.connectCNCBLocalServer(ConstantUtil.CNCB_SERVER_BASEURL+"forceTransferFromTransferAccNo",paramss);
      R r= JSON.parseObject(result,R.class);
      ForceTransferBasicInfo forceTransferBasicInfo=JSON.parseObject(r.get("forceTransferBasicInfo").toString(),ForceTransferBasicInfo.class);
      CncbTrans cncbTrans=new CncbTrans();
      cncbTrans.setXml(forceTransferBasicInfo.getXml());
      cncbTrans.setClientId(forceTransferBasicInfo.getClientID());
      cncbTrans.setTime(new Date());
      cncbTrans.setType((byte)7);
      JSONObject operationParamsObj=new JSONObject();
      operationParamsObj.put("logisticsId",logisticsId);
      operationParamsObj.put("orderId",orderId);
      operationParamsObj.put("zfSjMoney",prePayment);
      cncbTrans.setOperationParams(operationParamsObj.toJSONString());
      cncbTransDao.create(cncbTrans);
      Map<String,Object> params=new HashMap<>();
      params.put("orderId",orderId);
      params.put("logistics2ndpayState",6);
      orderDao.updateOrderByID(params);
      return R.ok().put("mag","结算中...");
   }

}
