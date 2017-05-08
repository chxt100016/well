package org.wella.houtai.sxsz.ctrl;

import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.houtai.sxsz.mapper.HoutaiSxszDegreeMapper;
import org.wella.houtai.sxsz.mapper.HoutaiSxszUserMapper;

@Controller
public class HoutaiSxszDengjiSzCtrl extends BaseController {
    @Autowired
    private HoutaiSxszUserMapper userMapper;
    @Autowired
    private HoutaiSxszDegreeMapper degreeMapper;

    public HoutaiSxszDengjiSzCtrl() {
    }

    @RequestMapping({"houtai/sxsz/HoutaiSxszDengjiSzCtrl-getYhDengjiList"})
    public String getUserDengjiList(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map param = this.getConditionParam(request);
        ArrayList list = this.userMapper.getYhDengjiList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.userMapper.getYhDengjiListCount(param);
        HashMap param1 = new HashMap();
        param1.put("noPage", "1");
        ArrayList djList = this.degreeMapper.getUserDengjiList(param1);
        ConvertUtil.convertDataBaseMapToJavaMap(djList);
        Map djMap = ConvertUtil.convertArrayToMap(djList, "degreeId");
        Iterator var10 = list.iterator();

        while(var10.hasNext()) {
            Map ele = (Map)var10.next();
            if(!ele.get("creditDengji").toString().equals("0")) {
                Map djInfo = (Map)djMap.get(ele.get("creditDengji").toString());
                if(djInfo != null) {
                    ele.put("moneyFw", djInfo.get("moneyFw"));
                    ele.put("moneyFw1", djInfo.get("moneyFw1"));
                } else {
                    ele.put("moneyFw", "0.00");
                    ele.put("moneyFw1", "0.00");
                }
            } else {
                ele.put("moneyFw", "0.00");
                ele.put("moneyFw1", "0.00");
            }
        }

        model.addAttribute("list", list);
        model.addAttribute("djList", djList);
        model.addAttribute("start", param.get("start"));
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        return "views/houtai/sxsz/yhDengjiSzList.jsp";
    }

    @RequestMapping({"houtai/sxsz/HoutaiSxszDengjiSzCtrl-saveUserDengji"})
    public void saveUserDengji(HttpServletRequest request, HttpServletResponse response, Model model) {
        JSONObject res = new JSONObject();

        try {
            Map e = this.getBoClass(request, "wa_user");
            this.commonMapper.updateSingleBO(e);
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