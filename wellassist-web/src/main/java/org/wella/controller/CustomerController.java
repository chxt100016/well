package org.wella.controller;

import io.wellassist.utils.SpringContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.wella.common.utils.CommonUtil;
import org.wella.service.CustomerService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/11.
 */
@Controller()
@RequestMapping(value = "/customer/")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

   @RequestMapping(value = "order",method = RequestMethod.POST)
    public void order(HttpServletRequest request , HttpServletResponse response){
//       HttpServletRequest servletRequest =(HttpRequest) RequestContextHolder.getRequestAttributes();
//       ServletContext servletContext =(ServletContext) SpringContextUtils.applicationContext;
       Map map = new HashMap();
//       String prodId = CommonUtil.GetRequestParam(request, "prodId", "0");
//       String saleNum = CommonUtil.GetRequestParam(request, "saleNum", "0");
//       String saleMoney = CommonUtil.GetRequestParam(request, "saleMoney", "0.00");
//       String isSelfCar = CommonUtil.GetRequestParam(request, "isSelfCar", "0");
//       String vehicleLxr = CommonUtil.GetRequestParam(request, "vehicleLxr", "");
//       String vehicleLxrPhone = CommonUtil.GetRequestParam(request, "vehicleLxrPhone", "");
//       String toRegionId = CommonUtil.GetRequestParam(request, "toRegionId", "0");
//       String toRegionAddr = CommonUtil.GetRequestParam(request, "toRegionAddr", "0");
//       String orderData = CommonUtil.GetRequestParam(request, "orderData", "");
//       String cfDate = CommonUtil.GetRequestParam(request, "cfDate", "2016-12-12 00:00:00");
//       String ddDate = CommonUtil.GetRequestParam(request, "ddDate", "2016-12-12 00:00:00");
       map.put("prodId",request.getParameter("prodId"));
       map.put("saleNum",request.getParameter("saleNum"));
       map.put("saleMoney",request.getParameter("saleMoney"));
       map.put("isSelfCar",request.getParameter("isSelfCar"));
       map.put("vehicleLxrPhone",request.getParameter("vehicleLxrPhone"));
       map.put("toRegionId",request.getParameter("toRegionId"));
       map.put("toRegionAddr",request.getParameter("toRegionAddr"));
       map.put("orderData",request.getParameter("orderData"));
       map.put("cfDate",request.getParameter("cfDate"));
       map.put("ddDate",request.getParameter("ddDate"));

//       map.put("vehi")


   }

}
