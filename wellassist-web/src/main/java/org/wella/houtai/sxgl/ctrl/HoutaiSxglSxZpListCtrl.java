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
import org.wella.houtai.sxgl.mapper.HoutaiSxglCreditInfoMapper;
import org.wella.houtai.sxgl.mapper.HoutaiSxglCreditMapper;
import org.wella.houtai.sxgl.mapper.HoutaiSxglUserMapper;

@Controller
public class HoutaiSxglSxZpListCtrl extends BaseController {
    @Autowired
    private HoutaiSxglCreditMapper creditMapper;
    @Autowired
    private HoutaiSxglCreditInfoMapper creditInfoMapper;
    @Autowired
    private HoutaiSxglUserMapper userMapper;

    public HoutaiSxglSxZpListCtrl() {
    }

    @RequestMapping({"houtai/sxsz/HoutaiSxglSxZpListCtrl-getSxZpList"})
    public String getSxZpList(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map param = this.getConditionParam(request);
        param.put("creditState", "1");
        ArrayList list = this.creditMapper.getSxList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.creditMapper.getSxListCount(param);
        model.addAttribute("list", list);
        model.addAttribute("start", param.get("start"));
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        return "views/houtai/sxgl/sxZpList.jsp";
    }

    @RequestMapping({"houtai/sxsz/HoutaiSxglSxZpListCtrl-getSxZpDetail"})
    public String getSxDetail(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map param = this.getConditionParam(request);
        Map info = this.creditMapper.getSxInfo(param);
        ConvertUtil.convertDataBaseMapToJavaMap(info);
        ArrayList list = this.creditInfoMapper.getSxCreditInfoList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        model.addAttribute("info", info);
        model.addAttribute("list", list);
        return "views/houtai/sxgl/sxZpDetail.jsp";
    }

    @RequestMapping({"houtai/sxsz/HoutaiSxglSxZpListCtrl-getFkfList"})
    public String getFkfList(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map param = this.getConditionParam(request);
        ArrayList list = this.userMapper.getFkfList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.userMapper.getFkfListCount(param);
        model.addAttribute("list", list);
        model.addAttribute("start", param.get("start"));
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        return "views/houtai/sxgl/dlgFkfList.jsp";
    }
}