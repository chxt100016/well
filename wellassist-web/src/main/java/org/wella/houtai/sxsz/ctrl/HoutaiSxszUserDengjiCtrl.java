//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.wella.houtai.sxsz.ctrl;

import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
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
import org.wella.houtai.sxsz.mapper.HoutaiSxszDegreeMapper;

@Controller
public class HoutaiSxszUserDengjiCtrl extends BaseController {
    @Autowired
    private HoutaiSxszDegreeMapper degreeMapper;

    public HoutaiSxszUserDengjiCtrl() {
    }

    @RequestMapping({"houtai/sxsz/HoutaiSxszUserDengjiCtrl-getUserDengjiList"})
    public String getUserDengjiList(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map param = this.getConditionParam(request);
        ArrayList list = this.degreeMapper.getUserDengjiList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.degreeMapper.getUserDengjiListCount(param);
        model.addAttribute("list", list);
        model.addAttribute("start", param.get("start"));
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        return "views/houtai/sxsz/userDengjiList.jsp";
    }

    @RequestMapping({"houtai/sxsz/HoutaiSxszUserDengjiCtrl-saveUserDengji"})
    public void saveUserDengji(HttpServletRequest request, HttpServletResponse response, Model model) {
        JSONObject res = new JSONObject();
        String degreeId = CommonUtil.GetRequestParam(request, "degreeId", "0");

        try {
            Map e = this.getBoClass(request, "wa_degree");
            if(degreeId.equals("0")) {
                this.commonMapper.insertSingleBO(e);
            } else {
                this.commonMapper.updateSingleBO(e);
            }

            res.put("state", "1");
            res.put("content", ConstantUtil.MSG_SUCCESS);
        } catch (Exception var7) {
            var7.printStackTrace();
            res.put("state", "2");
            res.put("content", ConstantUtil.MSG_FAILS);
        }

        this.echo(response, res);
    }

    @RequestMapping({"houtai/sxsz/HoutaiSxszUserDengjiCtrl-delUserDengji"})
    public void delUserDengji(HttpServletRequest request, HttpServletResponse response, Model model) {
        JSONObject res = new JSONObject();

        try {
            Map e = this.getBoClass(request, "wa_degree");
            this.commonMapper.deleteSingleBO(e);
            res.put("state", "1");
            res.put("content", ConstantUtil.MSG_SUCCESS);
        } catch (Exception var6) {
            var6.printStackTrace();
            res.put("state", "2");
            res.put("content", ConstantUtil.MSG_FAILS);
        }

        this.echo(response, res);
    }
}
