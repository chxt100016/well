package org.wella.front.sender.ctrl;

import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wella.common.ctrl.BaseController;
import org.wella.common.ctrl.CheckCodeCtrl;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.vo.MyInfo;
import org.wella.front.sender.mapper.SenderLoginMapper;

@Controller
public class SenderLoginController extends BaseController {
    @Autowired
    private SenderLoginMapper loginMapper;

    public SenderLoginController() {
    }

    @RequestMapping({"/front/sender/SenderLoginController-login"})
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        String vehicleTrans = CommonUtil.GetRequestParam(request, "vehicleTrans", "0");
        model.addAttribute("vehicleTrans", vehicleTrans);
        return "/front/sender/login/login";
    }

    @RequestMapping({"/front/sender/SenderLoginController-register"})
    public String register(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("provinceList", this.getChildRegionList(0));
        return "/front/sender/login/register";
    }

    public ArrayList<Map<String, Object>> getChildRegionList(int regionId) {
        ArrayList ret = null;
        HashMap param = new HashMap();
        param.put("regionId", Integer.valueOf(regionId));

        try {
            ret = this.commonMapper.getChildRegionList(param);
            ConvertUtil.convertDataBaseMapToJavaMap(ret);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return ret;
    }

    @RequestMapping({"/front/sender/SenderLoginController-register_result"})
    public String register_result(HttpServletRequest request, HttpServletResponse response) {
        return "/front/sender/login/register_result";
    }

    @RequestMapping({"/front/sender/SenderLoginController-getVerifyImage"})
    public void getVerifyImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CheckCodeCtrl checkCodeService = new CheckCodeCtrl();
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0L);
        response.setContentType("image/jpeg");
        String code = checkCodeService.generateRandomMixedCode();
        HttpSession session = request.getSession(true);
        session.setAttribute("VerifyCode", code);
        ImageIO.write(checkCodeService.getImage(code), "JPEG", response.getOutputStream());
    }

    @RequestMapping(
            value = {"/front/sender/SenderLoginController-loginNext"},
            method = {RequestMethod.POST}
    )
    public void loginNext(HttpServletRequest request, HttpServletResponse response, Model model) {
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        String username = CommonUtil.GetRequestParam(request, "username", "");
        String password = CommonUtil.GetRequestParam(request, "password", "");
        if(!username.equals("") && !password.equals("")) {
            try {
                HashMap e = new HashMap();
                e.put("username", username);
                e.put("password", password);
                if(!CommonUtil.check_inj_request_param(e)) {
                    String strsql = "SELECT * FROM wa_user inner join wa_userinfo on wa_user.user_id = wa_userinfo.user_id WHERE user_type = 3 AND (user_phone = \'" + username + "\' OR user_email = \'" + username + "\')";
                    e.put("strsql", strsql);
                    Map userInfo = this.commonMapper.simpleSelectReturnObject(e);
                    if(userInfo != null) {
                        ConvertUtil.convertDataBaseMapToJavaMap(userInfo);
                        String pass = userInfo.get("userPass") != null?userInfo.get("userPass").toString():"";
                        if(pass.equals(CommonUtil.MD5(password))) {
                            MyInfo myInfo = new MyInfo();
                            myInfo.setData(userInfo.get("userId").toString(), "", userInfo.get("userName") != null?userInfo.get("userName").toString():"", userInfo.get("userPass") != null?userInfo.get("userPass").toString():"", userInfo.get("userPhone") != null?userInfo.get("userPhone").toString():"", userInfo.get("userState") != null?userInfo.get("userState").toString():"", userInfo.get("createDate") != null?userInfo.get("createDate").toString():"0000-00-00 00:00:00", "", new HashMap(), "", userInfo.get("zcRegionId") != null?userInfo.get("zcRegionId").toString():"", userInfo.get("zcXxAddress") != null?userInfo.get("zcXxAddress").toString():"", userInfo.get("userEmail") != null?userInfo.get("userEmail").toString():"", userInfo.get("userPhone") != null?userInfo.get("userPhone").toString():"", userInfo.get("companyLxr") != null?userInfo.get("companyLxr").toString():"", userInfo.get("companyLxrPhone") != null?userInfo.get("companyLxrPhone").toString():"", userInfo.get("companyImg") != null?userInfo.get("companyImg").toString():"/resources/upload/images/company_mark/kunlun.png", "3");
                            HttpSession session = request.getSession(true);
                            session.setAttribute("MY_INFO", myInfo);
                            ret = "1";
                            obj.put("content", ConstantUtil.MSG_SUCCESS);
                        } else {
                            ret = "-5";
                            obj.put("content", "密码错误!");
                        }
                    } else {
                        ret = "-4";
                        obj.put("content", "用户不存在!");
                    }
                } else {
                    ret = "-3";
                    obj.put("content", "发现了恶意html代码！");
                }
            } catch (Exception var14) {
                ret = "-2";
                obj.put("content", ConstantUtil.MSG_FAILS);
            }
        }

        obj.put("status", ret);
        this.echoJSON(response, obj);
    }

    @RequestMapping({"/front/sender/SenderLoginController-getChildRegionListInSite"})
    public void getChildRegionListInSite(HttpServletRequest request, HttpServletResponse response, Model model) {
        int regionId = CommonUtil.getIntFromString(CommonUtil.GetRequestParam(request, "regionId", "0"));
        JSONObject res = new JSONObject();

        try {
            HashMap e = new HashMap();
            e.put("regionId", Integer.valueOf(regionId));
            ArrayList ret = this.commonMapper.getChildRegionList(e);
            ConvertUtil.convertDataBaseMapToJavaMap(ret);
            res.put("state", Integer.valueOf(1));
            res.put("regionList", ret);
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        this.echoJSON(response, res);
    }

    @RequestMapping({"/front/sender/SenderLoginController-onCheckMobileEmail"})
    public void onCheckMobileEmail(HttpServletRequest request, HttpServletResponse response, Model model) {
        String contactphone = CommonUtil.GetRequestParam(request, "contactphone", "");
        String contactemail = CommonUtil.GetRequestParam(request, "contactemail", "");
        JSONObject res = new JSONObject();
        res.put("state", "1");
        Map userEmailInfo = this.getMyOneSingBO("wa_user", "user_email", contactemail);
        Map userPhoneInfo = this.getMyOneSingBO("wa_user", "user_phone", contactphone);
        if(userEmailInfo != null) {
            res.put("state", "-3");
            res.put("content", "电子邮箱已存在!");
        }

        if(userPhoneInfo != null) {
            res.put("state", "-4");
            res.put("content", "手机号码已存在!");
        }

        this.echoJSON(response, res);
    }

    @RequestMapping({"/front/sender/SenderLoginController-onCheckCompanyName"})
    public void onCheckCompanyName(HttpServletRequest request, HttpServletResponse response, Model model) {
        String companyname = CommonUtil.GetRequestParam(request, "companyname", "");
        JSONObject res = new JSONObject();
        res.put("state", "1");
        Map userEmailInfo = this.getMyOneSingBO("wa_user", "user_name", companyname);
        if(userEmailInfo != null) {
            res.put("state", "-1");
            res.put("content", "企业名称已存在!");
        }

        this.echoJSON(response, res);
    }

    @RequestMapping({"/front/sender/SenderLoginController-registerNew"})
    public void registerNew(HttpServletRequest request, HttpServletResponse response, Model model) {
        HashMap paramlist = new HashMap();
        String companyname = CommonUtil.GetRequestParam(request, "companyname", "");
        paramlist.put("company_name", companyname);
        new HashMap();
        new HashMap();
        JSONObject res = new JSONObject();
        Map ret = this.loginMapper.get_user_count(paramlist);
        if(CommonUtil.getIntFromString(ret.get("cn").toString()) > 0) {
            res.put("state", Integer.valueOf(-1));
            res.put("content", "该企业已经存在！");
        } else {
            model.addAttribute("exist_count", ret.get("cn").toString());
            HttpSession session = request.getSession();
            Map userinfo = (Map)session.getAttribute("userinfo");
            HashMap wa_user = new HashMap();
            String user_name = CommonUtil.GetRequestParam(request, "companyname", "");
            String user_reg_no = CommonUtil.GetRequestParam(request, "companyaccount", "");
            String user_type = "3";
            String user_phone = CommonUtil.GetRequestParam(request, "contactphone", "");
            String user_email = CommonUtil.GetRequestParam(request, "contactemail", "");
            String user_pass = CommonUtil.GetRequestParam(request, "pass1", "");
            wa_user.put("user_type", user_type);
            wa_user.put("user_pass", CommonUtil.MD5(user_pass));
            wa_user.put("user_email", user_email);
            wa_user.put("user_phone", user_phone);
            wa_user.put("user_reg_no", user_reg_no);
            wa_user.put("user_name", user_name);
            this.loginMapper.insertWaUser(wa_user);
            String userId = wa_user.get("newId").toString();
            String waUserInfoId = "";
            if(CommonUtil.getIntFromString(userId) > 0) {
                HashMap wa_userinfo = new HashMap();
                String company_type = CommonUtil.GetRequestParam(request, "compkind", "");
                String sp_type = CommonUtil.GetRequestParam(request, "prodkind", "");
                String company_name = CommonUtil.GetRequestParam(request, "companyname", "");
                String company_img = CommonUtil.GetRequestParam(request, "yingye_img4", "");
                String company_yy_zz_img = CommonUtil.GetRequestParam(request, "yingye_img1", "");
                String company_xkz_img = CommonUtil.GetRequestParam(request, "yingye_img2", "");
                String company_txkz_img = CommonUtil.GetRequestParam(request, "yingye_img3", "");
                String company_yy_zz = CommonUtil.GetRequestParam(request, "company_yy_zz", "");
                String company_kh_xkz = CommonUtil.GetRequestParam(request, "company_xkz", "");
                String company_txkz = CommonUtil.GetRequestParam(request, "company_txkz", "");
                String zc_region_id = CommonUtil.GetRequestParam(request, "zc_region_id", "");
                String zc_xx_address = CommonUtil.GetRequestParam(request, "address", "");
                String company_lxr = CommonUtil.GetRequestParam(request, "contact", "");
                String company_lxr_phone = CommonUtil.GetRequestParam(request, "contactseat", "");
                wa_userinfo.put("company_type", company_type);
                wa_userinfo.put("info_userId", userId);
                wa_userinfo.put("sp_type", sp_type);
                wa_userinfo.put("company_name", company_name);
                wa_userinfo.put("company_img", company_img);
                wa_userinfo.put("company_yy_zz_img", company_yy_zz_img);
                wa_userinfo.put("company_xkz_img", company_xkz_img);
                wa_userinfo.put("company_txkz_img", company_txkz_img);
                wa_userinfo.put("company_yy_zz", company_yy_zz);
                wa_userinfo.put("company_kh_xkz", company_kh_xkz);
                wa_userinfo.put("company_txkz", company_txkz);
                wa_userinfo.put("zc_region_id", zc_region_id);
                wa_userinfo.put("zc_xx_address", zc_xx_address);
                wa_userinfo.put("cz_pass", CommonUtil.MD5("123456"));
                wa_userinfo.put("company_lxr", company_lxr);
                wa_userinfo.put("company_lxr_phone", company_lxr_phone);

                try {
                    this.loginMapper.insertWaUserInfo(wa_userinfo);
                    waUserInfoId = wa_user.get("newId").toString();
                    res.put("state", Integer.valueOf(1));
                    res.put("content", "注册成功！");
                } catch (Exception var37) {
                    res.put("state", Integer.valueOf(-1));
                    res.put("content", "注册失败！");
                }
            } else {
                res.put("state", Integer.valueOf(-1));
                res.put("content", "注册失败！");
            }
        }
        this.echoJSON(response, res);
    }

    @RequestMapping({"/front/sender/SenderLoginController-registerNext"})
    public String registerNext(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "front/customer/login/registerNext";
    }

    @RequestMapping({"/front/sender/SenderLoginController-logout"})
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
        this.clearSession(request);
        return "front/sender/login/login";
    }
}