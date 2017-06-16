package org.wella.controller;

import com.alibaba.fastjson.JSONObject;
import io.wellassist.utils.HttpContextUtils;
import io.wellassist.utils.IPUtils;
import io.wellassist.utils.R;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import org.wella.entity.Bankcard;
import org.wella.entity.Prod;
import org.wella.entity.User;
import org.wella.entity.Userinfo;
import org.wella.front.customer.mapper.CustomerBackOrderMapper;
import org.wella.front.mapper.FrontBankOrderMapper;
import org.wella.front.mapper.FrontTixianMapper;
import org.wella.front.mapper.FrontUserMoneyMapper;
import org.wella.front.mapper.NewsMapper;
import org.wella.service.CustomerService;
import org.wella.service.WaOrderService;
import org.wella.service.impl.CustomerServiceImpl;
import org.wella.service.impl.FinanceServiceImpl;
import org.wella.service.impl.SellerServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by liuwen on 2017/5/11.
 */
@Controller
@RequestMapping(value = "/customer/")
public class CustomerController extends BaseController{
    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @Autowired
    private WaUserDao userDao;

    @Autowired
    private ProdDao prodDao;

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
    private FinanceServiceImpl financeService;
   /**
    * 买家下订单
    * @param map prodId，saleNum，danjia，saleMoney，isSelfCar，contacts，conTel，toRegionId
    *                toRegionAddr，orderData，deliverDate，receiveDate，customerExceptCarriage
    * @param response
    */
   @RequestMapping(value = "order",method = RequestMethod.POST)
   public void order(@RequestParam Map<String, Object> map,HttpServletRequest request, HttpServletResponse response) {
      User user = (User) HttpContextUtils.getHttpServletRequest().getSession().getAttribute("user");
      long userId=user.getUserId();
      map.put("userId",userId);
      String ip=getIpAddr(request);
      map.put("orderIp",ip);
      JSONObject res = new JSONObject();
      try {
         customerServiceImpl.order(map);
         res.put("state", "1");
         res.put("content", ConstantUtil.MSG_SUCCESS);
      } catch (Exception e) {
         res.put("state", "2");
         res.put("content", ConstantUtil.MSG_FAILS);
         e.printStackTrace();
      } finally {
         this.echo(response, res);
      }
   }

   @RequestMapping("makeOrder")
   public String makeOrder(HttpServletRequest request, HttpServletResponse response, Model model) {
      String prodId = CommonUtil.GetRequestParam(request, "prodId", "0");
      HashMap param = new HashMap();
      param.put("prodId", prodId);
      Map prodMap=customerServiceImpl.findProdById(param);

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

   /**
    * 跳转订单详情页面
    * @param model
    * @return
    */
   @RequestMapping("orderDetail")
   public String orderDetail(@RequestParam("orderId")String orderId, Model model){
      Map<String,Object> orderDetail=customerServiceImpl.getOrderDetailInfo(Long.parseLong(orderId));
      model.addAttribute("info",orderDetail);
      model.addAttribute("parentMenuNo", "1");
      model.addAttribute("childMenuNo", "1");
      return "views/front/customer/order/orderDetail_new.jsp";
   }

   @RequestMapping(value = "zorderConfirmReceive",method = RequestMethod.GET)
   @ResponseBody
   public R zorderConfirmReceive(@RequestParam("zorderId")String zorderId){
      int res=customerServiceImpl.zorderConfirmReceive(Long.parseLong(zorderId));
      if(res>0){
         return R.ok();
      }
      return R.error();
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
      model.addAttribute("childMenuNo", "1");
      return "views/front/customer/order/expressDetail.jsp";
   }


   /**
    * 跳转买家物流订单界面
    */
   @RequestMapping("logisticsInfoList")
   public String logisticsInfoList(HttpServletRequest request,Model model){
      Map param=getConditionParam(request);
      User user=(User)request.getSession().getAttribute("user");
      long userId=user.getUserId();
      param.put("userId",userId);
      List<Map<String, Object>> info=customerServiceImpl.logisticsInfoListInfo(param);
      int totalCount=customerServiceImpl.logisticsInfoListInfoCount(param);
      this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
      model.addAttribute("info",info);
      model.addAttribute("parentMenuNo","1");
      model.addAttribute("childMenuNo","2");
      model.addAttribute("userName",user.getUserName());
      return "views/front/customer/order/wlOrderList.jsp";
   }

   /**
    * 跳转抢单信息页面
    */
   @RequestMapping("grabLogisticsList")
   public String grabLogisticsList(@RequestParam("logisticsInfoId")String logisticsInfoId, HttpServletRequest request,Model model){
      long LIId=Long.parseLong(logisticsInfoId);
      List<Map<String,Object>> info=customerServiceImpl.grabLogisticsListInfo(LIId);
      model.addAttribute("info",info);
      model.addAttribute("logisticsInfoId",LIId);
      model.addAttribute("parentMenuNo","1");
      model.addAttribute("childMenuNo","2");
      return "views/front/customer/order/editQiangdan.jsp";
   }

   /**
    * 买家选择物流抢单
    * @param
    * @param
    * @param
    */
   @RequestMapping("chooseGrab")
   @ResponseBody
   public R chooseGrab(@RequestParam Map param){
      try {
         customerServiceImpl.chooseGrab(param);
      }catch (Exception e){
         e.printStackTrace();
         return R.error();
      }
      return R.ok();
   }

   @RequestMapping("testPayLogistics")
   @ResponseBody
   public R testPayLogistics(@RequestParam("logisticsInfoId") String logisticsInfoId){
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
   public R testPayOrder(@RequestParam("orderId")String orderId){
      try {
         int res=customerServiceImpl.testPayOrder(Long.parseLong(orderId));
         if(res>0){
            return R.ok();
         }
      } catch (Exception e) {
         e.printStackTrace();
         return R.error();
      }
      return R.error();
   }

   @RequestMapping("goPayOrder")
   public String goPayOrder(){

      return "views/front/customer/order/editFukuan.jsp";
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

   @RequestMapping("checkWithdrawPassword")
   public void checkWithdrawPassword(@RequestParam("pass") String oldPass,HttpServletResponse response){
      HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
      User user = (User) httpSession.getAttribute("user");
      //MD5加密
      String password = CommonUtil.MD5(oldPass);
      Boolean res = Boolean.valueOf(false);
      if(password.equals(user.getUserPass())){
         res = Boolean.valueOf(true);
      }else{
         res = Boolean.valueOf(false);
      }
      this.echo(response,res.toString());
   }

   @RequestMapping("prodList")
   public String prodList(@RequestParam Map<String,Object>map,Model model){
      User user = (User) HttpContextUtils.getAttribute("user");
      map.put("userId",user.getSupplyId());
      List<Prod> prodList = prodDao.findProdByUserId(map);
      model.addAttribute("spList", prodList);
      model.addAttribute("userName", user.getUserName());
      model.addAttribute("parentMenuNo", "5");
      model.addAttribute("childMenuNo", "0");
      return "views/front/customer/prod/prodList.jsp";
   }


   /**
    * 买方订单列表
    * @param request
    * @param model
    * @return
    */
   @RequestMapping("orderList")
   public String orderList(HttpServletRequest request,Model model){
      User user = (User) HttpContextUtils.getAttribute("user");
      Map param = this.getConditionParam(request);
      param.put("userId", user.getUserId());
      ArrayList waOrderList = this.customerBackOrderMapper.getWaOrderList(param);
      ConvertUtil.convertDataBaseMapToJavaMap(waOrderList);
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
    * @param prodId
    * @param model
    * @return
    */
   @RequestMapping(value = "orderPage",method = RequestMethod.GET)
   public String orderPage(@RequestParam String prodId,Model model){
      HashMap param = new HashMap();
      param.put("prodId", prodId);
      Map prod = this.prodDao.findProdById(param);
      ConvertUtil.convertDataBaseMapToJavaMap(prod);
      String regionId = "";
      String regionName = "";
      if(prod != null) {
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
      model.addAttribute("userName", user.getUserName());
      return "views/front/customer/company/companyInfo.jsp";
   }

   /**
    * 进入个人中心，查看企业信息
    * @param model
    * @return
    */
   @RequestMapping("bankcardPage")
   public String bankcardPage(Model model){
      HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
      User user =(User) httpSession.getAttribute("user");
      Userinfo userinfo = (Userinfo) httpSession.getAttribute("userInfo");
      List<Bankcard> bankcardList = bankcardDao.getCardListByUserId(user.getUserId());
      model.addAttribute("user",user);
      model.addAttribute("userInfo",userinfo);
      model.addAttribute("parentMenuNo", "4");
      model.addAttribute("childMenuNo", "4");
      model.addAttribute("Cards",bankcardList);
      model.addAttribute("userName",user.getUserName());
      return "views/front/customer/company/bankcard.jsp";
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
      model.addAttribute("userName",user.getUserName());
      return "views/front/customer/company/contactMode.jsp";
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

   @RequestMapping("withdraw")
   public String withdraw(HttpServletRequest request,  Model model) {
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
   public R withdrawProcess(@RequestParam Map<String,Object> params){
      User user = (User) HttpContextUtils.getAttribute("user");
      params.put("userId",user.getUserId());
      params.put("withdrawIp", IPUtils.getIpAddr(HttpContextUtils.getHttpServletRequest()));
      try {
         int result = withdrawDAO.withdrawApply(params);
         return R.ok().put("state",1).put("content","请求已经受理");
      } catch (Exception e) {
         e.printStackTrace();
         return R.ok().put("state",-1).put("content","系统错误");
      }
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
      return "views/front/customer/finance/czSqList.jsp";
   }

   @RequestMapping("rechargeApply")
   @ResponseBody
   public R rechargeApply(@RequestParam Map<String,Object> params) {
      int res = financeService.recharge(params);
      if(res==1){
         return R.ok().put("state",1).put("content",ConstantUtil.MSG_SUCCESS);
      }else {
         return R.error().put("state", 0).put("content", ConstantUtil.MSG_FAILS);
      }
   }

   @RequestMapping("withdrawRecordList")
   public String withdrawRecordList(HttpServletRequest request, Model model) {
      User user = (User) HttpContextUtils.getAttribute("user");
      Map<String,Object> param = this.getConditionParam(request);
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
    * @return
    */
   @RequestMapping("addBankcard")
   @ResponseBody
   public R addBankcard(@RequestParam Map<String,Object> map){
     User user = (User) HttpContextUtils.getAttribute("user");
     long userId = user.getUserId();
     map.put("userId",userId);
     map.put("addTime",new Date());
      try {
         long key = bankcardDao.addCard(map);
         return R.ok().put("content","添加成功");
      } catch (Exception e) {
         e.printStackTrace();
         return R.error();
      }
   }
   /**
    * 添加银行卡的异步请求
    * @return
    */
   @RequestMapping("getCards")
   @ResponseBody
   public R getCards(@RequestParam Map<String,Object> map){
      User user = (User) HttpContextUtils.getAttribute("user");
      long userId = user.getUserId();
      try {
         List<Bankcard> cards = bankcardDao.getCardListByUserId(userId);
         return R.ok().put("content","添加成功").put("Cards",cards);
      } catch (Exception e) {
         e.printStackTrace();
         return R.error();
      }
   }

   /**
    * 添加银行卡的异步请求
    * @return
    */
   @RequestMapping("delBankcard")
   @ResponseBody
   public R delBankcard(@RequestParam Map<String,Object> map){
      User user = (User) HttpContextUtils.getAttribute("user");
      long userId = user.getUserId();
      try {
        int count = bankcardDao.delCard(Long.parseLong(map.get("bankcardId").toString()));
         return R.ok().put("content","添加成功").put("count",count);
      } catch (Exception e) {
         e.printStackTrace();
         return R.error();
      }
   }

}
