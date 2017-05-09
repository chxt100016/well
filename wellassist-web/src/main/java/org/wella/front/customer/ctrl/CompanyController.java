package org.wella.front.customer.ctrl;

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.CommonUtil;
import org.wella.common.vo.MyInfo;

@Controller
public class CompanyController extends BaseController {
    public CompanyController() {
    }

    @RequestMapping({"front/customer/CompanyController-companyInfo"})
    public String companyInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        model.addAttribute("parentMenuNo", "4");
        model.addAttribute("childMenuNo", "1");
        model.addAttribute("userName", myInfo.getUserName());
        String strsql;
        if(!"0".equals(myInfo.getzcRegionId().toString()) && !"".equals(myInfo.getzcRegionId().toString())) {
            String param = myInfo.getzcRegionId().toString().substring(0, 2) + "0000";
            strsql = myInfo.getzcRegionId().toString().substring(0, 4) + "00";
            model.addAttribute("cityList", this.getChildRegionList(CommonUtil.getIntFromString(param)));
            model.addAttribute("countyList", this.getChildRegionList(CommonUtil.getIntFromString(strsql)));
            model.addAttribute("shengId", param);
            model.addAttribute("countyId", strsql);
        }

        model.addAttribute("provinceList", this.getChildRegionList(0));
        model.addAttribute("myInfo", myInfo);
        HashMap param1 = new HashMap();
        strsql = "SELECT COUNT(*) FROM wa_user_money WHERE user_id = \'" + myInfo.getUserId() + "\' AND DATE_ADD(jy_date,INTERVAL 3 MONTH) > NOW()";
        param1.put("strsql", strsql);
        model.addAttribute("threeJyCn", Integer.valueOf(this.commonMapper.simpleSelectReturnInt(param1)));
        strsql = "SELECT COUNT(*) FROM wa_user_money WHERE user_id = \'" + myInfo.getUserId() + "\' AND jy_state = 1";
        param1.put("strsql", strsql);
        model.addAttribute("ingJyCn", Integer.valueOf(this.commonMapper.simpleSelectReturnInt(param1)));
        return "front/customer/company/companyInfo";
    }

    @RequestMapping({"front/customer/CompanyController-contactMode"})
    public String contact_mode(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        model.addAttribute("parentMenuNo", "4");
        model.addAttribute("childMenuNo", "2");
        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("provinceList", this.getChildRegionList(0));
        model.addAttribute("myInfo", myInfo);
        return "views/front/customer/company/contactMode.jsp";
    }

    @RequestMapping({"front/customer/CompanyController-contactModeUpdate"})
    public void contactModeUpdate(HttpServletRequest request, HttpServletResponse response, Model model) {
        HashMap param = new HashMap();
        HashMap mapClass = new HashMap();
        JSONObject obj = new JSONObject();
        MyInfo myInfo = this.getMyInfo(request);
        String companyLxr = CommonUtil.GetRequestParam(request, "companyLxr", "");
        String companyLxrPhone = CommonUtil.GetRequestParam(request, "companyLxrPhone", "");
        mapClass.put("company_lxr", companyLxr);
        mapClass.put("company_lxr_phone", companyLxrPhone);
        param.put("mapClass", mapClass);
        param.put("tableName", "wa_userinfo");
        param.put("keyName", "user_id");
        param.put("keyValue", myInfo.getUserId());

        try {
            this.commonMapper.updateSingleBO(param);
            obj.put("state", Integer.valueOf(1));
            obj.put("msg", "操作成功！");
            this.echoJSON(response, obj);
        } catch (Exception var11) {
            var11.printStackTrace();
            obj.put("state", Integer.valueOf(-1));
            obj.put("msg", "操作错误！");
            this.echoJSON(response, obj);
        }

    }

    @RequestMapping({"front/customer/CompanyController-companyInfoUpdate"})
    public void companyInfoUpdate(HttpServletRequest request, HttpServletResponse response, Model model) {
        HashMap param = new HashMap();
        HashMap mapClass = new HashMap();
        JSONObject obj = new JSONObject();
        MyInfo myInfo = this.getMyInfo(request);
        String zcXxAddress = CommonUtil.GetRequestParam(request, "zcXxAddress", "");
        String zcRegionId = CommonUtil.GetRequestParam(request, "zc_region_id", "0");
        String userPhone = CommonUtil.GetRequestParam(request, "userPhone", "");
        String userEmail = CommonUtil.GetRequestParam(request, "userEmail", "");
        mapClass.put("zc_xx_address", zcXxAddress);
        mapClass.put("zc_region_id", zcRegionId);
        param.put("mapClass", mapClass);
        param.put("tableName", "wa_userinfo");
        param.put("keyName", "user_id");
        param.put("keyValue", myInfo.getUserId());

        try {
            this.commonMapper.updateSingleBO(param);
            param.put("tableName", "wa_user");
            mapClass.clear();
            mapClass.put("user_phone", userPhone);
            mapClass.put("user_email", userEmail);
            this.commonMapper.updateSingleBO(param);
            obj.put("state", Integer.valueOf(1));
            obj.put("msg", "操作成功！");
            this.echoJSON(response, obj);
        } catch (Exception var13) {
            var13.printStackTrace();
            obj.put("state", Integer.valueOf(-1));
            obj.put("msg", "操作错误！");
            this.echoJSON(response, obj);
        }

    }

    @RequestMapping({"front/customer/CompanyController-changePass"})
    public String changePass(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("parentMenuNo", "4");
        model.addAttribute("childMenuNo", "3");
        MyInfo myInfo = this.getMyInfo(request);
        model.addAttribute("userName", myInfo.getUserName());
        return "views/front/customer/company/changePass.jsp";
    }

    @RequestMapping({"front/customer/CompanyController-checkOrgPass"})
    public void checkOrgPass(HttpServletRequest request, HttpServletResponse response, Model model) {
        new HashMap();
        JSONObject obj = new JSONObject();
        MyInfo myInfo = this.getMyInfo(request);
        String oldPass = CommonUtil.GetRequestParam(request, "oldPass", "");

        try {
            Map ret = this.getMyOneSingBO("wa_user", "user_id", myInfo.getUserId());
            if(CommonUtil.MD5(oldPass).toString().equals(ret.get("userPass").toString())) {
                obj.put("state", Integer.valueOf(1));
                obj.put("msg", "操作成功！");
                this.echoJSON(response, obj);
            } else {
                obj.put("state", Integer.valueOf(-1));
                obj.put("msg", "原来密码不一致！");
                this.echoJSON(response, obj);
            }
        } catch (Exception var9) {
            var9.printStackTrace();
            obj.put("state", Integer.valueOf(-1));
            obj.put("msg", "操作错误！");
            this.echoJSON(response, obj);
        }

    }

    @RequestMapping({"front/customer/CompanyController-updateNewPass"})
    public void updateNewPass(HttpServletRequest request, HttpServletResponse response, Model model) {
        HashMap param = new HashMap();
        HashMap mapClass = new HashMap();
        JSONObject obj = new JSONObject();
        MyInfo myInfo = this.getMyInfo(request);
        String newpass = CommonUtil.GetRequestParam(request, "newpass", "");
        mapClass.put("user_pass", CommonUtil.MD5(newpass));
        param.put("mapClass", mapClass);
        param.put("tableName", "wa_user");
        param.put("keyName", "user_id");
        param.put("keyValue", myInfo.getUserId());

        try {
            this.commonMapper.updateSingleBO(param);
            obj.put("state", Integer.valueOf(1));
            obj.put("msg", "操作成功！");
            this.echoJSON(response, obj);
        } catch (Exception var10) {
            var10.printStackTrace();
            obj.put("state", Integer.valueOf(-1));
            obj.put("msg", "操作错误！");
            this.echoJSON(response, obj);
        }

    }

    @RequestMapping({"front/customer/CompanyController-checkPayOrgPass"})
    public void checkPayOrgPass(HttpServletRequest request, HttpServletResponse response, Model model) {
        new HashMap();
        JSONObject obj = new JSONObject();
        MyInfo myInfo = this.getMyInfo(request);
        String oldPass = CommonUtil.GetRequestParam(request, "oldPass", "");

        try {
            Map ret = this.getMyOneSingBO("wa_user", "user_id", myInfo.getUserId());
            if(CommonUtil.MD5(oldPass).toString().equals(ret.get("czPass").toString())) {
                obj.put("state", Integer.valueOf(1));
                obj.put("msg", "操作成功！");
                this.echoJSON(response, obj);
            } else {
                obj.put("state", Integer.valueOf(-1));
                obj.put("msg", "原来密码不一致！");
                this.echoJSON(response, obj);
            }
        } catch (Exception var9) {
            var9.printStackTrace();
            obj.put("state", Integer.valueOf(-1));
            obj.put("msg", "操作错误！");
            this.echoJSON(response, obj);
        }

    }

    @RequestMapping({"front/customer/CompanyController-updatePayNewPass"})
    public void updatePayNewPass(HttpServletRequest request, HttpServletResponse response, Model model) {
        HashMap param = new HashMap();
        HashMap mapClass = new HashMap();
        JSONObject obj = new JSONObject();
        MyInfo myInfo = this.getMyInfo(request);
        String newpass = CommonUtil.GetRequestParam(request, "paynewpass", "");
        mapClass.put("cz_pass", CommonUtil.MD5(newpass));
        param.put("mapClass", mapClass);
        param.put("tableName", "wa_user");
        param.put("keyName", "user_id");
        param.put("keyValue", myInfo.getUserId());

        try {
            this.commonMapper.updateSingleBO(param);
            obj.put("state", Integer.valueOf(1));
            obj.put("msg", "操作成功！");
            this.echoJSON(response, obj);
        } catch (Exception var10) {
            var10.printStackTrace();
            obj.put("state", Integer.valueOf(-1));
            obj.put("msg", "操作错误！");
            this.echoJSON(response, obj);
        }

    }
}