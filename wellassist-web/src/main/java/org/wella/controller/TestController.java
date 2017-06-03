package org.wella.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wella.service.SellerService;

/**
 * Created by Administrator on 2017/6/2.
 */
@Controller
@RequestMapping(value = "/test/")
public class TestController {
    @Autowired
    private SellerService sellerServiceImpl;

    @RequestMapping("test1")
    @ResponseBody
    public Object test1(String orderId){
        return sellerServiceImpl.getOrderLogisticsInfo(Long.parseLong(orderId));
    }
}
