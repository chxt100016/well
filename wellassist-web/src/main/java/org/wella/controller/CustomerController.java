package org.wella.controller;

import com.alibaba.fastjson.JSONObject;
import io.wellassist.utils.HttpContextUtils;
import io.wellassist.utils.R;
import io.wellassist.utils.SpringContextUtils;
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
import org.wella.dao.ProdDao;
import org.wella.dao.WaUserDao;
import org.wella.entity.Prod;
import org.wella.entity.User;
import org.wella.service.CustomerService;
import org.wella.service.impl.CustomerServiceImpl;
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

   /**
    * 买家下订单
    * @param map prodId，saleNum，danjia，saleMoney，isSelfCar，contacts，conTel，toRegionId
    *                toRegionAddr，orderData，deliverDate，receiveDate，customerExceptCarriage
    * @param response
    */
   @RequestMapping(value = "order",method = RequestMethod.POST)
   public void order(@RequestParam Map<String, Object> map, HttpServletResponse response) {
      User user = (User) HttpContextUtils.getHttpServletRequest().getSession().getAttribute("user");
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

   @RequestMapping("prodList")
   public String prodList(@RequestParam Map<String,Object>map,Model model){
      User user = (User) HttpContextUtils.getAttribute("user");
      map.put("supplyId",user.getSupplyId());
      List<Prod> prodList = prodDao.findProdByUserId(map);
      model.addAttribute("spList", prodList);
      model.addAttribute("userName", user.getUserName());
      model.addAttribute("parentMenuNo", "5");
      model.addAttribute("childMenuNo", "0");
      return "views/front/customer/prod/prodList.jsp";
   }

   @Re
}
