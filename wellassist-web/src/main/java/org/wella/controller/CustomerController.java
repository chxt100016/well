package org.wella.controller;

import com.alibaba.fastjson.JSONObject;
import io.wellassist.utils.SpringContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.vo.MyInfo;
import org.wella.service.CustomerService;
import org.wella.service.impl.CustomerServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by liuwen on 2017/5/11.
 */
@Controller
@RequestMapping(value = "/customer/")
public class CustomerController extends BaseController{
    @Autowired
    private CustomerServiceImpl customerServiceImpl;

   /**
    * 买家下订单
    * @param request prodId，saleNum，danjia，saleMoney，isSelfCar，contacts，conTel，toRegionId
    *                toRegionAddr，orderData，deliverDate，receiveDate，customerExceptCarriage
    * @param response
    */
   @RequestMapping(value = "order",method = RequestMethod.POST)
    public void order(HttpServletRequest request , HttpServletResponse response){
       MyInfo myInfo = this.getMyInfo(request);
       JSONObject res = new JSONObject();
       Map map = new HashMap();
       try {
          map.put("userId", myInfo.getUserId());

          map.put("prodId", request.getParameter("prodId"));
          map.put("saleNum", request.getParameter("saleNum"));
          map.put("danjia",request.getParameter("danjia"));
          map.put("saleMoney", request.getParameter("saleMoney"));
          map.put("isSelfCar", request.getParameter("isSelfCar"));
          map.put("contacts", request.getParameter("contacts"));
          map.put("conTel", request.getParameter("conTel"));
          map.put("toRegionId", request.getParameter("toRegionId"));
          map.put("toRegionAddr", request.getParameter("toRegionAddr"));
          map.put("orderData", request.getParameter("orderData"));
          map.put("deliverDate", request.getParameter("deliverDate"));
          map.put("receiveDate", request.getParameter("receiveDate"));
          map.put("customerExceptCarriage",request.getParameter("customerExceptCarriage"));


          map.put("orderIp",getIpAddr(request));
       }catch (Exception e){
          e.printStackTrace();
          res.put("state", "2");
          res.put("content", ConstantUtil.MSG_FAILS);
          e.printStackTrace();
       }finally {
          try {
             customerServiceImpl.order(map);
             res.put("state", "1");
             res.put("content", ConstantUtil.MSG_SUCCESS);
          }catch (Exception e){
             res.put("state", "2");
             res.put("content", ConstantUtil.MSG_FAILS);
             e.printStackTrace();
          }finally {
            this.echo(response,res);
          }
       }

   }

   @RequestMapping(value = "test",method = RequestMethod.POST)
   public void test(HttpServletRequest request , HttpServletResponse response){
         Map map=this.getConditionParam(request);
         int  i=0;
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

}
