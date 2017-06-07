package org.wella.platform.controller;

import io.wellassist.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.TradeDAO;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/6/5.
 */
@RequestMapping("/platform/trade/")
@Controller
public class TradeController extends BaseController {

    @Autowired
    private TradeDAO tradeDAO;

    @RequestMapping("tradeList")
    @ResponseBody
    public R tradeList(@RequestParam Map<String,Object> params) {
        Query query = new Query(params);
        List list = tradeDAO.getTradeList(query);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = tradeDAO.tradeCount(params);
        PageUtils pageUtils = new PageUtils(list,totalCount,query.getLimit(),query.getPage());
         return R.ok().put("page",pageUtils);
    }

    @RequestMapping("tradeDetail/{moneyId}")
    public String getJyDetailList(@PathVariable("moneyId")String moneyId, Model model) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userId",moneyId);
        List list = tradeDAO.tradeDetailList(map);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        model.addAttribute("list", list);
        return "views/houtai/jygl/jyDetailList.jsp";
    }



    @RequestMapping("rechargeList")
    @ResponseBody
    public R rechargeList(@RequestParam Map<String,Object> param, Model model) {
        param.put("jyType", "0");
        Query query = new Query(param);
        List list = tradeDAO.rechargeList(query);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = tradeDAO.rechargeCount(query);
        PageUtils pageUtils = new PageUtils(list,totalCount,query.getLimit(),query.getPage());
        return R.ok().put("page",pageUtils);
    }

    /**
     * 充值确认时的业务处理
     */
    @RequestMapping("rechargeHandle")
    @ResponseBody
    public R rechargeHandle(@RequestParam Map<String,Object> parmas) {
        Long userId =  ShiroUtils.getUserId();
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        parmas.put("orderIp", IPUtils.getIpAddr(request));
        parmas.put("userId",userId);
        try {
            tradeDAO.rechargeHandle(parmas);
            return R.ok().put("state",1).put("content",ConstantUtil.MSG_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().put("state",0).put("content", ConstantUtil.MSG_FAILS);
        }
    }

    @RequestMapping("withdrawList")
    @ResponseBody
    public R withdrawList(@RequestParam Map<String,Object> param) {
        Query query = new Query(param);
        List list = tradeDAO.withdrawList(query);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = tradeDAO.withdrawCount(query);
        PageUtils pageUtils = new PageUtils(list,totalCount,query.getLimit(),query.getPage());
        return R.ok().put("page",pageUtils);
    }

    @RequestMapping("withdrawHandle")
    @ResponseBody
    public R withdrawHandle(@RequestParam Map<String,Object> param) {
        Query query = new Query(param);
        param.put("userId",ShiroUtils.getUserId());
        param.put("withdrawIp",IPUtils.getIpAddr(HttpContextUtils.getHttpServletRequest()));
        int res = tradeDAO.withdrawHandle(param);
        if(res==1){
            return R.ok().put("state",1);
        }else {
            return R.error().put("state",0);
        }
    }
    @RequestMapping("logisticsList")
    @ResponseBody
    public R logisticsList(@RequestParam Map<String,Object> param, Model model) {
        Query query = new Query(param);
        List list = tradeDAO.withdrawList(query);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = tradeDAO.withdrawCount(query);
        PageUtils pageUtils = new PageUtils(list,totalCount,query.getLimit(),query.getPage());
        return R.ok().put("page",pageUtils);
    }


}
