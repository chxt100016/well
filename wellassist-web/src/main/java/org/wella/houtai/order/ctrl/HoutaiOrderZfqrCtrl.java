package org.wella.houtai.order.ctrl;

import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.vo.MyInfo;
import org.wella.houtai.order.mapper.HoutaiOrderOrderTransMapper;
import org.wella.houtai.order.mapper.OrderMapper;

@Controller
public class HoutaiOrderZfqrCtrl extends BaseController {
    @Autowired
    private OrderMapper OrderMapper;
    @Autowired
    private HoutaiOrderOrderTransMapper OrderTransMapper;

    public HoutaiOrderZfqrCtrl() {
    }

    @RequestMapping({"/houtai/Order/HoutaiOrderZfqrCtrl-getZfQrList"})
    public String getZfQrList(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map param = this.getConditionParam(request);
        ArrayList orderList = this.OrderTransMapper.getZfQrList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(orderList);
        model.addAttribute("orderList", orderList);
        int totalCount = this.OrderTransMapper.getZfQrListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        return "views/houtai/order/orderZfQrList.jsp";
    }

    @RequestMapping({"/houtai/Order/HoutaiOrderZfqrCtrl-setZfQr"})
    public void setZfQr(HttpServletRequest request, HttpServletResponse response, Model model) {
        JSONObject res = new JSONObject();
        String orderId = CommonUtil.GetRequestParam(request, "orderId", "0");
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        String sql = "";
        HashMap queryParam = new HashMap();
        sql = "CALL khXianxiaFukuanProcess(\'" + userId + "\',\'" + orderId + "\') ";

        try {
            queryParam.put("strsql", sql);
            this.commonMapper.simpleSelectReturnList(queryParam);
            res.put("state", "1");
            res.put("content", ConstantUtil.MSG_SUCCESS);
        } catch (Exception var11) {
            var11.printStackTrace();
            res.put("state", "2");
            res.put("content", ConstantUtil.MSG_FAILS);
        }

        this.echo(response, res);
    }
}