package org.wella.houtai.sxgl.ctrl;

import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.Date;
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
import org.wella.houtai.sxgl.mapper.HoutaiSxglCreditInfoMapper;
import org.wella.houtai.sxgl.mapper.HoutaiSxglCreditMapper;

@Controller
public class HoutaiSxglSxListCtrl extends BaseController {
    @Autowired
    private HoutaiSxglCreditMapper creditMapper;
    @Autowired
    private HoutaiSxglCreditInfoMapper creditInfoMapper;

    public HoutaiSxglSxListCtrl() {
    }

    @RequestMapping({"houtai/sxsz/HoutaiSxglSxListCtrl-getSxList"})
    public String getSxList(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map param = this.getConditionParam(request);
        ArrayList list = this.creditMapper.getSxList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.creditMapper.getSxListCount(param);
        model.addAttribute("list", list);
        model.addAttribute("start", param.get("start"));
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        return "views/houtai/sxgl/sxList.jsp";
    }

    @RequestMapping({"houtai/sxsz/HoutaiSxglSxListCtrl-getSxDetail"})
    public String getSxDetail(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map param = this.getConditionParam(request);
        Map info = this.creditMapper.getSxInfo(param);
        ConvertUtil.convertDataBaseMapToJavaMap(info);
        ArrayList list = this.creditInfoMapper.getSxCreditInfoList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        model.addAttribute("info", info);
        model.addAttribute("list", list);
        return "views/houtai/sxgl/sxDetail.jsp";
    }

    @RequestMapping({"houtai/sxsz/HoutaiSxglSxListCtrl-changeState"})
    public void changeState(HttpServletRequest request, HttpServletResponse response, Model model) {
        JSONObject res = new JSONObject();
        MyInfo myInfo = (MyInfo)request.getSession().getAttribute("MY_INFO");
        String adminId = myInfo.getUserId();
        String mgrIp = this.getIpAddr(request);
        Date mgrDate = new Date();
        String content = CommonUtil.GetRequestParam(request, "content", "");
        String creditState = CommonUtil.GetRequestParam(request, "creditState", "0");
        if(content.equals("")) {
            if(creditState.equals("1")) {
                content = "审核通过";
            } else if(creditState.equals("-1")) {
                content = "审核不通过";
            } else if(creditState.equals("2")) {
                content = "指派放款放完成";
            }
        }

        try {
            Map e = this.getBoClass(request, "wa_credit");
            Map param1 = this.getBoClass(request, "wa_credit_info");
            Map mapClass1 = (Map)param1.get("mapClass");
            mapClass1.put("admin_user_id", adminId);
            mapClass1.put("content", content);
            mapClass1.put("mgr_ip", mgrIp);
            mapClass1.put("mgr_date", mgrDate);
            this.commonMapper.updateSingleBO(e);
            this.commonMapper.insertSingleBO(param1);
            res.put("state", "1");
            res.put("content", ConstantUtil.MSG_SUCCESS);
        } catch (Exception var14) {
            var14.printStackTrace();
            res.put("state", "2");
            res.put("content", ConstantUtil.MSG_FAILS);
        }

        this.echo(response, res);
    }
}
