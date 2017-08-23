package org.wella.controller;

import io.wellassist.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wella.service.impl.PlatformServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/17.
 */
@Controller
@RequestMapping("/platform/")
public class PlateformController {


    @Autowired
    private PlatformServiceImpl platformService;




    /**
     * 模糊查询所买方
     * @param request
     * @param response
     */
    @RequestMapping("customerList")
    public void customerList(HttpServletRequest request, HttpServletResponse response){

    }



    @ResponseBody
    @RequestMapping("insertCustomer")
    public R insertCustomer(@RequestBody  Map<String,Object> map){
       boolean result= platformService.insertCustomer(map);
       if(result==true){
           return R.ok();
       }
       return R.error();
    }


    /**
     * 交易量
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("tradingVolume")
    public R tradingVolume(@RequestBody Map<String,Object> map){
        List<BigDecimal> list=platformService.tradingVolume(map);
        return R.ok().put("data",list);
    }


    /**
     * 贷款
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("loanAmount")
    public R  loanAmount(@RequestBody Map<String,Object> map){
        List<BigDecimal> list=platformService.loanAmount(map);
        return R.ok().put("data",list);
    }


    @ResponseBody
    @RequestMapping("turnover")
    public R turnover(@RequestBody Map<String,Object> map){
        List<BigDecimal> list=platformService.turnover(map);
        return R.ok().put("data",list);
    }



    @ResponseBody
    @RequestMapping("interest")
    public R interest(@RequestBody Map<String,Object> map){
        List<BigDecimal> list=platformService.interest(map);
        return R.ok().put("data",list);
    }


    @ResponseBody
    @RequestMapping("fundFlow")
    public R fundFlow(@RequestBody Map<String,Object> map){
        Map<String,List> maplist=platformService.fundFlow(map);
        return R.ok().put("data1",maplist.get("out")).put("data2",maplist.get("in"));
    }



}
