package org.wella.controller;

import com.alibaba.fastjson.JSONObject;
import io.wellassist.utils.SpringContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.vo.MyInfo;
import org.wella.service.CustomerService;
import org.wella.service.impl.CustomerServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/11.
 */
@Controller
@RequestMapping(value = "/customer/")
public class CustomerController extends BaseController{
    @Autowired
    private CustomerServiceImpl customerServiceImpl;

   @RequestMapping(value = "order",method = RequestMethod.POST)
    public void order(HttpServletRequest request , HttpServletResponse response){
       MyInfo myInfo = this.getMyInfo(request);
       JSONObject res = new JSONObject();
       Map map = new HashMap();
       try {
          map.put("userId", myInfo.getUserId());
          map.put("prodId", request.getParameter("prodId") != "" ? request.getParameter("prodId") : "1");
          map.put("saleNum", request.getParameter("saleNum"));
          map.put("saleMoney", request.getParameter("saleMoney"));
          map.put("isSelfCar", request.getParameter("isSelfCar"));
          map.put("vehicleLxr", request.getParameter("vehicleLxr"));
          map.put("vehicleLxrPhone", request.getParameter("vehicleLxrPhone"));
          map.put("toRegionId", request.getParameter("toRegionId") != "" ? request.getParameter("toRegionId") : "1");
          map.put("toRegionAddr", request.getParameter("toRegionAddr"));
          map.put("orderData", request.getParameter("orderData"));
          map.put("cfDate", request.getParameter("cfDate"));
          map.put("ddDate", request.getParameter("ddDate"));
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



}
