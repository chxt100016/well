package org.wella.houtai.jygl.ctrl;

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
import org.wella.houtai.jygl.mapper.HoutaiJyglUserMoneyInfoMapper;
import org.wella.houtai.jygl.mapper.HoutaiJyglUserMoneyMapper;

@Controller
public class HoutaiJyglJyListCtrl extends BaseController {
    @Autowired
    private HoutaiJyglUserMoneyMapper userMoneyMapper;
    @Autowired
    private HoutaiJyglUserMoneyInfoMapper userMoneyInfoMapper;

    public HoutaiJyglJyListCtrl() {
    }

    @RequestMapping({"houtai/jygl/HoutaiJyglJyListCtrl-getJyList"})
    public String getJyList(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map param = this.getConditionParam(request);
        ArrayList list = this.userMoneyMapper.getJyList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.userMoneyMapper.getJyListCount(param);
        model.addAttribute("list", list);
        model.addAttribute("start", param.get("start"));
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        return "views/houtai/jygl/jyList.jsp";
    }

    @RequestMapping({"houtai/jygl/HoutaiJyglJyListCtrl-getJyDetailList"})
    public String getJyDetailList(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map param = this.getConditionParam(request);
        ArrayList list = this.userMoneyInfoMapper.getJyDetailList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        model.addAttribute("list", list);
        return "houtai/jygl/jyDetailList";
    }
}