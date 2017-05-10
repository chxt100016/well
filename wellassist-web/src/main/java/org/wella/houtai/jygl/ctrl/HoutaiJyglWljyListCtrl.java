package org.wella.houtai.jygl.ctrl;

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
import org.wella.houtai.jygl.mapper.HoutaiJyglWljyMapper;

@Controller
public class HoutaiJyglWljyListCtrl extends BaseController {
    @Autowired
    private HoutaiJyglWljyMapper jyglWljyMapper;

    public HoutaiJyglWljyListCtrl() {
    }

    @RequestMapping({"houtai/jygl/HoutaiJyglWljyListCtrl-getWljyList"})
    public String getWljyList(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map param = this.getConditionParam(request);
        param.put("jyType", "3");
        ArrayList list = this.jyglWljyMapper.getJyglWljyList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.jyglWljyMapper.getJyglWljyListCount(param);
        model.addAttribute("list", list);
        model.addAttribute("start", param.get("start"));
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        return "views/houtai/jygl/wljyList.jsp";
    }

    @RequestMapping({"houtai/jygl/HoutaiJyglWljyListCtrl-getWljyDetail"})
    public String getWljyDetail(HttpServletRequest request, HttpServletResponse response, Model model) {
        String orderId = CommonUtil.GetRequestParam(request, "orderId", "0");
        if(CommonUtil.getIntFromString(orderId) > 0) {
            String strsql = "SELECT a.* FROM wa_order_vehicle as a WHERE a.order_id = " + orderId;
            HashMap param = new HashMap();
            param.put("strsql", strsql);
            ArrayList list = this.commonMapper.simpleSelectReturnList(param);
            ConvertUtil.convertDataBaseMapToJavaMap(list);
            model.addAttribute("list", list);
        }

        return "views/houtai/jygl/wljyDetail.jsp";
    }
}