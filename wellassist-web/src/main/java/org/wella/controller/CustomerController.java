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
import java.util.ArrayList;
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
       customerService.order(map);
   }

}
