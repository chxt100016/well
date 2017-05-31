package org.wella.controller;


import com.alibaba.fastjson.JSONObject;
import io.wellassist.utils.IPUtils;
import io.wellassist.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.vo.MyInfo;
import org.wella.entity.Log;
import org.wella.entity.User;
import org.wella.front.seller.mapper.SellerOrderMapper;
import org.wella.service.impl.SellerServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/12.
 */
@Controller
@RequestMapping(value = {"/seller/"})
public class SellerController extends BaseController {

    @Autowired
    private SellerOrderMapper sellerOrderMapper;

    @Autowired
    private SellerServiceImpl sellerServiceImpl;

    @RequestMapping("processOrder")
    public void processOrder(HttpServletRequest request, HttpServletResponse response, Model model){
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        String ipAddr = IPUtils.getIpAddr(request);
        try {
            String operationIp = IPUtils.getIpAddr(request);
            String orderId = CommonUtil.GetRequestParam(request, "orderId", "0");
            String saleNum = CommonUtil.GetRequestParam(request, "saleNum", "0");
            String saleDj = CommonUtil.GetRequestParam(request, "saleDj", "0");
            Map editMap = new HashMap();
            editMap.put("confirmNumber",saleNum);
            editMap.put("confirmPrice",saleDj);
            editMap.put("operationIp",ipAddr);
            editMap.put("userId",userId);
            sellerServiceImpl.processOrder(Long.parseLong(orderId),editMap);
            ret = "1";
            obj.put("content",ConstantUtil.MSG_SUCCESS);
        } catch (Exception e) {
            ret = "-2";
            obj.put("content", ConstantUtil.MSG_FAILS);
        }
        obj.put("status", ret);
        this.echoJSON(response, obj);
    }

    /**
     * 产品发布页面
     * @param model
     * @return
     */
    @RequestMapping("publishPage")
    public String publishPage(Model model){
        Map map = new HashMap();
        map.put("userType",0);
        model.addAttribute("provinceList", this.getChildRegionList(0));
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "1");
        return "views/front/seller/order/prodPub.jsp";
    }

    @RequestMapping("orderList")
    public void orderList(HttpServletRequest request, HttpServletResponse response){
        int ret = -1;
        JSONObject obj = new JSONObject();
        User user= (User) request.getSession().getAttribute("user");
        Long userId=user.getUserId();
        Map param=getConditionParam(request);
        param.put("supplierId",userId);
        List<Map<String,Object>> orderList=sellerServiceImpl.getOrderList(param);
        int orderListCount=sellerServiceImpl.getOrderListCount(param);
        PageUtils page=new PageUtils(orderList,orderListCount,Integer.valueOf(ConstantUtil.GAP),Integer.valueOf((String) param.get("page")));
        obj.put("code",1);
        obj.put("page",page);
        this.echo(response,obj);
    }



}

