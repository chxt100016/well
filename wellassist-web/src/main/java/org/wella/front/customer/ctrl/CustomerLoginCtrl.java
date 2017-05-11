package org.wella.front.customer.ctrl;

 import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
 import java.math.BigDecimal;
 import java.util.ArrayList;
import java.util.Date;
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
import org.wella.common.mail.MailUtil;
import org.wella.common.mapper.CommonMapper;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.vo.MyInfo;
import org.wella.front.customer.mapper.CustomerLoginMapper;
 import org.wella.service.CustomerService;
 import org.wella.service.impl.CustomerServiceImpl;

@Controller
public class CustomerLoginCtrl extends BaseController {
    @Autowired
    protected CommonMapper commonMapper;
    @Autowired
    private CustomerLoginMapper loginMapper;
    @Autowired
    CheckCodeCtrl checkCodeService;
    @Autowired
    CustomerService customerServiceImpl;

    public CustomerLoginCtrl() {
    }
/*(long)userId,(long)prodId,(S)toRegionId,toRegionAddr,saleNum,saleMoney,orderIp,isSelfCar*/
    @RequestMapping({"/front/customer/CustomerLoginCtrl-login"})
    public String login(HttpServletRequest request, HttpServletResponse response) {
        Map map=new HashMap();
        map.put("userId",2L);
        map.put("prodId",1L);
        map.put("toRegionId",330104L);
        map.put("toRegionAddr","asdfrgrehgtr");
        map.put("saleNum",new BigDecimal("10"));
        map.put("saleMoney",new BigDecimal("500"));
        map.put("orderIp","123456");
        map.put("isSelfCar",(byte)1);
        /*map.put("orderData","[{\"sjmc\":\"丁建文1\",\"sjdh\":\"13145678923\",\"cph\":\"scdsgv\"},{\"sjmc\":\"丁建文2\",\"sjdh\":\"13245678965\",\"cph\":\"vfdbg\"}]");
        map.put("ccDate","2017-05-18 13:58:05");
        map.put("ddDate","2017-05-31 13:58:05");*/
        map.put("vehicleLxr","DINGJIANWEN");
        map.put("vehicleLxrPhone","13616546855");
        customerServiceImpl.order(map);

        return "views/front/customer/login/login.jsp";
    }

    @RequestMapping({"/front/customer/CustomerLoginCtrl-register"})
    public String register(HttpServletRequest request, HttpServletResponse response, Model model) {
        ArrayList customerList = null;
        HashMap param = new HashMap();
        param.put("strsql", "select user_id,user_name from wa_user where user_type=0");
        customerList = this.commonMapper.simpleSelectReturnList(param);
        model.addAttribute("customerList", customerList);
        model.addAttribute("provinceList", this.getChildRegionList(0));
        return "views/front/customer/login/register.jsp";
    }

    @RequestMapping({"/front/customer/CustomerLoginCtrl-registerNew"})
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
            String contactcustomer = CommonUtil.GetRequestParam(request, "contactcustomer", "");
            String user_type = "1";
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
                String company_img = CommonUtil.GetRequestParam(request, "yingye_img4", "");
                String company_name = CommonUtil.GetRequestParam(request, "companyname", "");
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
                    HashMap e = new HashMap();
                    HashMap mapClass = new HashMap();
                    mapClass.put("user_id", userId);
                    mapClass.put("gys_id", contactcustomer);
                    mapClass.put("rel_state", "0");
                    mapClass.put("rel_date", new Date());
                    e.put("mapClass", mapClass);
                    e.put("tableName", "wa_user_relation");
                    this.commonMapper.insertSingleBO(e);
                    res.put("state", Integer.valueOf(1));
                    res.put("content", "注册成功！");
                } catch (Exception var39) {
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

    @RequestMapping({"/front/customer/CustomerLoginCtrl-registerNext"})
    public String registerNext(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "views/front/customer/login/registerNext.jsp";
    }

    @RequestMapping(
            value = {"/front/customer/CustomerLoginCtrl-loginNext"},
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
                    String strsql = "SELECT * FROM wa_user inner join wa_userinfo on wa_user.user_id = wa_userinfo.user_id WHERE user_type = 1 AND (user_phone = \'" + username + "\' OR user_email = \'" + username + "\')";
                    e.put("strsql", strsql);
                    Map userInfo = this.commonMapper.simpleSelectReturnObject(e);
                    if(userInfo != null) {
                        ConvertUtil.convertDataBaseMapToJavaMap(userInfo);
                        String pass = userInfo.get("userPass") != null?userInfo.get("userPass").toString():"";
                        if(pass.equals(CommonUtil.MD5(password))) {
                            MyInfo myInfo = new MyInfo();
                            myInfo.setData(userInfo.get("userId").toString(), "", userInfo.get("userName") != null?userInfo.get("userName").toString():"", userInfo.get("userPass") != null?userInfo.get("userPass").toString():"", userInfo.get("userPhone") != null?userInfo.get("userPhone").toString():"", userInfo.get("userState") != null?userInfo.get("userState").toString():"", userInfo.get("createDate") != null?userInfo.get("createDate").toString():"0000-00-00 00:00:00", "", new HashMap(), "", userInfo.get("zcRegionId") != null?userInfo.get("zcRegionId").toString():"", userInfo.get("zcXxAddress") != null?userInfo.get("zcXxAddress").toString():"", userInfo.get("userEmail") != null?userInfo.get("userEmail").toString():"", userInfo.get("userPhone") != null?userInfo.get("userPhone").toString():"", userInfo.get("companyLxr") != null?userInfo.get("companyLxr").toString():"", userInfo.get("companyLxrPhone") != null?userInfo.get("companyLxrPhone").toString():"", userInfo.get("companyImg") != null?userInfo.get("companyImg").toString():"/resources/upload/images/company_mark/kunlun.png", "1");
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

    @RequestMapping({"/front/customer/CustomerLoginCtrl-logout"})
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
        this.clearSession(request);
        return "views/front/customer/login/login.jsp";
    }

    @RequestMapping({"/front/customer/CustomerLoginCtrl-getPass"})
    public String getPass(HttpServletRequest request, HttpServletResponse response, Model model) {
        String userType = CommonUtil.GetRequestParam(request, "userType", "0");
        model.addAttribute("userType", userType);
        return "views/front/customer/login/getPassInfo.jsp";
    }

    @RequestMapping({"/front/customer/CustomerLoginCtrl-checkUserAndEmail"})
    public void checkUserAndEmail(HttpServletRequest request, HttpServletResponse response, Model model) {
        String state = "1";
        String msg = "";
        JSONObject res = new JSONObject();
        HttpSession session = request.getSession(true);
        String userName = CommonUtil.GetRequestParam(request, "userName", "");
        String eMail = CommonUtil.GetRequestParam(request, "eMail", "");
        String code = "";
        if(session.getAttribute("verifyCode") != null) {
            code = session.getAttribute("verifyCode").toString();
        }

        String checkCode = CommonUtil.GetRequestParam(request, "checkCode", "").toUpperCase();
        String userType = CommonUtil.GetRequestParam(request, "userType", "0");
        String sql = "";
        HashMap queryParam = new HashMap();
        if(!checkCode.equals(code)) {
            state = "0";
            msg = "验证码错误！";
        } else if(userName.equals("")) {
            state = "0";
            msg = "请输入用户名和密码！";
        } else {
            sql = "SELECT * FROM wa_user WHERE user_phone = \'" + userName + "\' AND user_type = \'" + userType + "\'";
            queryParam.put("strsql", sql);
            Map userInfo = this.commonMapper.simpleSelectReturnObject(queryParam);
            if(userInfo != null && !"".equals(userInfo)) {
                ConvertUtil.convertDataBaseMapToJavaMap(userInfo);
                if(!userInfo.get("userEmail").toString().equals(eMail)) {
                    state = "0";
                    msg = "eMail地址不一致！,请输入正确的eMail地址！";
                } else {
                    String token = CommonUtil.MD5(userName + userType);
                    session.setAttribute("token", token);
                    String url = CommonUtil.getRootHttpPath(request);
                    String message = "<a href=\'" + url + "front/customer/CustomerLoginCtrl-setNewPassPage?userType=" + userType + "&userName=" + userName + "&token=" + token + "\'>进去</a>";
                    StringBuilder builder = new StringBuilder();
                    builder.append("<html><head>");
                    builder.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
                    builder.append("</head><body>");
                    builder.append(message);
                    builder.append("</body></html>");
                    String htmlContent = builder.toString();
                    MailUtil.sendMail(eMail, "忘记密码", htmlContent);
                }
            } else {
                state = "0";
                msg = "该用户名不存在！";
            }
        }

        res.put("state", state);
        res.put("msg", msg);
        this.echoJSON(response, res);
    }

    @RequestMapping({"/front/customer/CustomerLoginCtrl-setNewPassPage"})
    public String setNewPassPage(HttpServletRequest request, HttpServletResponse response, Model model) {
        String userName = CommonUtil.GetRequestParam(request, "userName", "");
        String userType = CommonUtil.GetRequestParam(request, "userType", "0");
        String token = CommonUtil.GetRequestParam(request, "token", "");
        String compare_token = CommonUtil.MD5(userName + userType);
        if(!token.equals(compare_token)) {
            return "error";
        } else {
            model.addAttribute("userName", userName);
            model.addAttribute("userType", userType);
            return "views/front/customer/login/setNewPass.jsp";
        }
    }

    @RequestMapping({"/front/customer/CustomerLoginCtrl-sendToEmail"})
    public String sendToEmail(HttpServletRequest request, HttpServletResponse response, Model model) {
        String userName = CommonUtil.GetRequestParam(request, "userName", "");
        String userType = CommonUtil.GetRequestParam(request, "userType", "0");
        HashMap param = new HashMap();
        param.put("userName", userName);
        String sql = "";
        HashMap queryParam = new HashMap();
        sql = "SELECT * FROM wa_user WHERE user_phone = \'" + userName + "\' AND user_type = \'" + userType + "\'";
        queryParam.put("strsql", sql);
        Map userInfo = this.commonMapper.simpleSelectReturnObject(queryParam);
        model.addAttribute("userName", userName);
        model.addAttribute("userType", userType);
        model.addAttribute("eMail", userInfo.get("user_email").toString());
        return "views/front/customer/login/sendEmailSuccess.jsp";
    }

    @RequestMapping({"/front/customer/CustomerLoginCtrl-setNewPass"})
    public void setNewPass(HttpServletRequest request, HttpServletResponse response, Model model) {
        String state = "1";
        String msg = "";
        JSONObject res = new JSONObject();
        String userName = CommonUtil.GetRequestParam(request, "userName", "");
        String userType = CommonUtil.GetRequestParam(request, "userType", "0");
        String userPass = CommonUtil.GetRequestParam(request, "userPass", "");
        String sql = "";
        HashMap queryParam = new HashMap();
        if("".equals(userName)) {
            state = "0";
            msg = "用户名错误！";
        } else {
            sql = "UPDATE wa_user SET user_pass = MD5(\'" + userPass + "\') WHERE user_phone = \'" + userName + "\' AND user_type = \'" + userType + "\'";
            queryParam.put("strsql", sql);

            try {
                this.commonMapper.simpleUpdate(queryParam);
            } catch (Exception var13) {
                var13.printStackTrace();
                state = "0";
                msg = "更新错误！";
            }
        }

        res.put("state", state);
        res.put("msg", msg);
        this.echoJSON(response, res);
    }

    @RequestMapping({"/front/customer/CustomerLoginCtrl-goToSuccess"})
    public String goToSuccess(HttpServletRequest request, HttpServletResponse response, Model model) {
        String userName = CommonUtil.GetRequestParam(request, "userName", "");
        String userType = CommonUtil.GetRequestParam(request, "userType", "0");
        String userPass = CommonUtil.GetRequestParam(request, "userPass", "");
        model.addAttribute("userName", userName);
        model.addAttribute("userType", userType);
        model.addAttribute("userPass", userPass);
        return "views/front/customer/login/registerSuccess.jsp";
    }

    @RequestMapping({"/front/customer/CustomerLoginCtrl-getVerifyImage"})
    public void getVerifyImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0L);
        response.setContentType("image/jpeg");
        String code = this.checkCodeService.generateRandomMixedCode();
        HttpSession session = request.getSession(true);
        session.setAttribute("verifyCode", code);
        ImageIO.write(this.checkCodeService.getImage(code), "JPEG", response.getOutputStream());
    }
}


