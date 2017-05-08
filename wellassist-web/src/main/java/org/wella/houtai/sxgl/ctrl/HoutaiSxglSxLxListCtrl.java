package org.wella.houtai.sxgl.ctrl;

import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.ConvertUtil;
import org.wella.houtai.sxgl.mapper.HoutaiSxglCreditMapper;
import org.wella.houtai.sxgl.mapper.HoutaiSxglRateInfoMapper;

@Controller
public class HoutaiSxglSxLxListCtrl extends BaseController {
    @Autowired
    private HoutaiSxglCreditMapper creditMapper;
    @Autowired
    private HoutaiSxglRateInfoMapper rateInfoMapper;

    public HoutaiSxglSxLxListCtrl() {
    }

    @RequestMapping({"houtai/sxsz/HoutaiSxglSxLxListCtrl-getSxLxList"})
    public String getSxLxList(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map param = this.getConditionParam(request);
        param.put("gtCreditState", "3");
        ArrayList list = this.creditMapper.getSxList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.creditMapper.getSxListCount(param);
        model.addAttribute("list", list);
        model.addAttribute("start", param.get("start"));
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        return "views/houtai/sxgl/sxLxList.jsp";
    }

    @RequestMapping({"houtai/sxsz/HoutaiSxglSxLxListCtrl-getSxLxDetail"})
    public String getSxDetail(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map param = this.getConditionParam(request);
        Map info = this.creditMapper.getSxInfo(param);
        ConvertUtil.convertDataBaseMapToJavaMap(info);
        ArrayList list = this.rateInfoMapper.getLixiHisList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        model.addAttribute("info", info);
        model.addAttribute("list", list);
        return "houtai/sxgl/sxLxDetail";
    }
}