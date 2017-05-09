package org.wella.houtai.order.ctrl;

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
import org.wella.common.utils.ConvertUtil;
import org.wella.houtai.order.mapper.OrderMapper;

@Controller
public class OrderCtrl extends BaseController {
    @Autowired
    private OrderMapper OrderMapper;

    public OrderCtrl() {
    }

    @RequestMapping({"/ht/Order/OrderCtrl-getOrderList"})
    public String getOrderList(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map param = this.getConditionParam(request);
        ArrayList orderList = this.OrderMapper.getOrderList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(orderList);
        model.addAttribute("orderList", orderList);
        int totalCount = this.OrderMapper.getOrderListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        return "views/houtai/order/order_list.jsp";
    }

    @RequestMapping({"/ht/Order/OrderCtrl-getOrderDetail"})
    public String getOrderDetail(HttpServletRequest request, HttpServletResponse response, Model model) {
        String orderId = CommonUtil.GetRequestParam(request, "orderId", "0");
        Map orderInfo = this.getMyOneSingBO("wa_order", "order_id", orderId);
        if(orderInfo != null) {
            Map param;
            if(orderInfo.get("userId") != null) {
                param = this.getMyOneSingBO("wa_user", "user_id", orderInfo.get("userId").toString());
                if(param != null) {
                    orderInfo.put("userName", param.get("userName") != null?param.get("userName").toString():"");
                }
            }

            if(orderInfo.get("prodId") != null) {
                param = this.getMyOneSingBO("wa_prod", "prod_id", orderInfo.get("prodId").toString());
                if(param != null) {
                    orderInfo.put("prodImg", param.get("prodImg") != null?param.get("prodImg").toString():"");
                }
            }
        }

        HashMap param1 = new HashMap();
        param1.put("orderId", orderId);
        ArrayList orderInfoList = this.OrderMapper.getOrderInfoList(param1);
        ConvertUtil.convertDataBaseMapToJavaMap(orderInfoList);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("orderInfoList", orderInfoList);
        return "views/houtai/order/order_detail.jsp";
    }
}