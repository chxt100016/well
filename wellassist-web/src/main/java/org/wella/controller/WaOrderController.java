package org.wella.controller;

import io.wellassist.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wella.service.WaOrderService;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/2.
 */
@Controller
@RequestMapping(value = "/order/")
public class WaOrderController {

    @Autowired
    private WaOrderService waOrderServiceImpl;

    @RequestMapping("isZordersQuestion")
    @ResponseBody
    public R isZordersQuestion(@RequestParam("orderId")long orderId){
        boolean flag=waOrderServiceImpl.idZordersQuestion(orderId);
        return R.ok().put("flag",flag);
    }

    @RequestMapping(value = "orderinfo",method = RequestMethod.GET)
    @ResponseBody
    public R orderinfo(@RequestParam(value = "orderId")long orderId){
        Map<String,Object> orderinfo=waOrderServiceImpl.orderinfo(orderId);
        return R.ok().put("orderinfo",orderinfo);
    }
}
