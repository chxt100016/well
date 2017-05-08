package org.wella.front;

import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.front.mapper.FrontUserVehicleMapper;
import org.wella.front.mapper.UserMapper;

@Controller
public class FrontLoginController extends BaseController {
    @Autowired
    private UserMapper usermapper;
    @Autowired
    private FrontUserVehicleMapper userVehicleMapper;

    public FrontLoginController() {
    }

    @RequestMapping({"/"})
    public String start(HttpServletRequest request, HttpServletResponse response, Model model) {
        ArrayList recVehicleList = this.userVehicleMapper.getRecentQdList();
        ConvertUtil.convertDataBaseMapToJavaMap(recVehicleList);
        Iterator var5 = recVehicleList.iterator();

        while(var5.hasNext()) {
            Map ele = (Map)var5.next();

            try {
                ele.put("fromRegionName", this.getRegionDetailName(ele.get("fromRegionId").toString()));
            } catch (Exception var9) {
                ele.put("fromRegionName", "");
            }

            try {
                ele.put("toRegionName", this.getRegionDetailName(ele.get("toRegionId").toString()));
            } catch (Exception var8) {
                ele.put("toRegionName", "");
            }
        }

        model.addAttribute("recVehicleList", recVehicleList);
        return "front/home";
    }

    @RequestMapping({"/front/login"})
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "front/login";
    }

    @RequestMapping({"/front/require_login"})
    public void require_login(HttpServletRequest request, HttpServletResponse response, Model model) {
        String state = "0";
        String msg = "";
        JSONObject res = new JSONObject();
        String userid = CommonUtil.GetRequestParam(request, "userid", "");
        String userpass = CommonUtil.GetRequestParam(request, "userpass", "");
        if(userid.equals("") || userpass.equals("")) {
            state = "0";
            msg = "请输入用户名和密码！";
        }

        HashMap userinfo = new HashMap();
        userinfo.put("regName", "Arirang");
        state = "1";
        res.put("userinfo", userinfo);
        HttpSession session = request.getSession();
        session.setAttribute("userinfo", userinfo);
        res.put("state", state);
        res.put("msg", msg);
        this.echoJSON(response, res);
    }
}