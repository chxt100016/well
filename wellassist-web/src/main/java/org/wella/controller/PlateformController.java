package org.wella.controller;

import io.wellassist.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wella.entity.User;
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
     * 新增用户
     * @param map 用户信息
     * @return code:0成功/500异常 msg:异常信息
     */
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
     * @param map echart请求参数
     * @return code:0成功/500异常 msg:异常信息
     */
    @ResponseBody
    @RequestMapping("tradingVolume")
    public R tradingVolume(@RequestBody Map<String,Object> map,HttpServletRequest request){
        if(map.get("userId").equals("")){
            User user=(User)request.getSession().getAttribute("user");
            map.put("userId",user.getUserId());
        }
        List<BigDecimal> list=platformService.tradingVolume(map);
        return R.ok().put("data",list).put("unit","1");
    }


    /**
     * 贷款
     * @param map echart请求参数
     * @return code:0成功/500异常 msg:异常信息
     */
    @ResponseBody
    @RequestMapping("loanAmount")
    public R  loanAmount(@RequestBody Map<String,Object> map,HttpServletRequest request){
        if(map.get("userId").equals("")){
            User user=(User)request.getSession().getAttribute("user");
            map.put("userId",user.getUserId());
        }
        List<BigDecimal> list=platformService.loanAmount(map);
        return R.ok().put("data",list);
    }

    /**
     * 成交额
     * @param map echart请求参数
     * @param request request
     * @return code:0成功/500异常 msg:异常信息
     */
    @ResponseBody
    @RequestMapping("turnover")
    public R turnover(@RequestBody Map<String,Object> map,HttpServletRequest request){
        if(map.get("userId").equals("")){
            User user=(User)request.getSession().getAttribute("user");
            map.put("userId",user.getUserId());
        }
        List<BigDecimal> list=platformService.turnover(map);
        return R.ok().put("data",list);
    }


    /**
     * 利息
     * @param map echart请求参数
     * @param request request
     * @return code:0成功/500异常 msg:异常信息
     */
    @ResponseBody
    @RequestMapping("interest")
    public R interest(@RequestBody Map<String,Object> map,HttpServletRequest request){
        if(map.get("userId").equals("")){
            User user=(User)request.getSession().getAttribute("user");
            map.put("userId",user.getUserId());
        }
        List<BigDecimal> list=platformService.interest(map);
        return R.ok().put("data",list);
    }

    /**
     * 资金流量
     * @param map echart请求参数
     * @param request request
     * @return code:0成功/500异常 msg:异常信息
     */
    @ResponseBody
    @RequestMapping("fundFlow")
    public R fundFlow(@RequestBody Map<String,Object> map,HttpServletRequest request){
        if(map.get("userId").equals("")){
            User user=(User)request.getSession().getAttribute("user");
            map.put("userId",user.getUserId());
        }
        Map<String,List> maplist=platformService.fundFlow(map);
        return R.ok().put("data",maplist.get("in")).put("data1",maplist.get("out"));
    }



}
