package org.wella.controller;


import com.alibaba.fastjson.JSONObject;
import io.wellassist.utils.IPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.vo.MyInfo;
import org.wella.front.seller.mapper.SellerOrderMapper;
import org.wella.service.impl.SellerServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
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

}

