package org.wella.front.fkf.ctrl;

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
import org.wella.front.fkf.mapper.FrontFkfCreditMapper;

@Controller
public class FkfOrderCtrl extends BaseController {
    @Autowired
    public FrontFkfCreditMapper creditMapper;

    public FkfOrderCtrl() {
    }

    @RequestMapping({"front/fkf/FkfOrderCtrl-sxList"})
    public String sxList(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        Map param = this.getConditionParam(request);
        param.put("creditUserId", myInfo.getUserId());
        ArrayList list0 = this.creditMapper.getCreditList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list0);
        ArrayList list = ConvertUtil.groupList(list0, "userId");
        int totalCount = this.creditMapper.getCreditListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "1");
        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("list", list);
        return "views/front/fkf/order/sxList.jsp";
    }

    @RequestMapping({"front/fkf/FkfOrderCtrl-sxDetail"})
    public String sxDetail(HttpServletRequest request, HttpServletResponse response, Model model) {
        String creditId = CommonUtil.GetRequestParam(request, "creditId", "0");
        HashMap param = new HashMap();
        param.put("creditId", creditId);
        Map info = this.creditMapper.getCreditInfo(param);
        ConvertUtil.convertDataBaseMapToJavaMap(info);
        model.addAttribute("info", info);
        return "views/front/fkf/order/sxDetail.jsp";
    }

    @RequestMapping({"front/fkf/FkfOrderCtrl-sxProcess"})
    public void sxProcess(HttpServletRequest request, HttpServletResponse response, Model model) {
        JSONObject res = new JSONObject();
        String creditId = CommonUtil.GetRequestParam(request, "creditId", "0");
        String creditSjMoney = CommonUtil.GetRequestParam(request, "creditSjMoney", "0.00");
        String creditState = CommonUtil.GetRequestParam(request, "creditState", "-2");
        String creditOverDate = CommonUtil.GetRequestParam(request, "creditOverDate", "3");
        String creditIp = this.getIpAddr(request);
        String sql = "";

        try {
            sql = "CALL sxClProcess(\'" + creditId + "\', \'" + creditSjMoney + "\', \'" + creditState + "\', \'" + creditOverDate + "\', \'" + creditIp + "\')";
            HashMap e = new HashMap();
            e.put("strsql", sql);
            this.commonMapper.simpleSelectReturnList(e);
            res.put("state", "1");
            res.put("content", ConstantUtil.MSG_SUCCESS);
        } catch (Exception var12) {
            var12.printStackTrace();
            res.put("state", "2");
            res.put("content", ConstantUtil.MSG_FAILS);
        }

        this.echo(response, res);
    }

    @RequestMapping({"front/fkf/FkfOrderCtrl-send_sheet"})
    public String send_ordersheet(HttpServletRequest request, HttpServletResponse response) {
        return "views/front/customer/ordersheet/send_sheet.jsp";
    }

    @RequestMapping({"front/fkf/FkfOrderCtrl-esti_list"})
    public String estimate_list(HttpServletRequest request, HttpServletResponse response) {
        return "views/front/customer/ordersheet/esti_list.jsp";
    }

    @RequestMapping({"front/fkf/FkfOrderCtrl-detailOrder"})
    public String detailOrder(HttpServletRequest request, HttpServletResponse response) {
        return "views/front/customer/ordersheet/prodsheet_detail.jsp";
    }
}