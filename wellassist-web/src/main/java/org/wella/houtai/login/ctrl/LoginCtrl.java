package org.wella.houtai.login.ctrl;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.vo.MyInfo;
import org.wella.houtai.login.mapper.LoginMapper;
import org.wella.houtai.login.mapper.LoginUserMainMapper;
import org.wella.houtai.sys.mapper.MenuMapper;

@Controller
public class LoginCtrl extends BaseController {
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private LoginUserMainMapper userMainMapper;

    public LoginCtrl() {
    }

    @RequestMapping({"/back"})
    public String startBack(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        session.setAttribute("MY_INFO", (Object)null);
        return "houtai/login/login";
    }

    @RequestMapping(
            value = {"/ht/Login/LoginCtrl-login"},
            method = {RequestMethod.POST}
    )
    public void login(HttpServletRequest request, HttpServletResponse response, Model model) {
        String state = "0";
        String msg = "";
        JSONObject res = new JSONObject();
        String userName = CommonUtil.GetRequestParam(request, "userName", "");
        String userPassword = CommonUtil.GetRequestParam(request, "userPassword", "");
        if(!userName.equals("") && !userPassword.equals("")) {
            HashMap param = new HashMap();
            param.put("userName", userName);
            Map userInfo = this.userMainMapper.getUserMainInfo(param);
            if(userInfo == null) {
                state = "0";
                msg = "输入的用户名不存在！";
            } else if(!CommonUtil.MD5(userPassword).equals(userInfo.get("adminPass").toString())) {
                state = "0";
                msg = "输入的密码错了！";
            } else {
                state = "1";
                msg = "登录成功！";
                HashMap menuParam = new HashMap();
                menuParam.put("parentId", "0");
                param.put("permission", userInfo.get("permission") == null?"":userInfo.get("permission").toString());
                param.put("adminLevel", userInfo.get("adminLevel"));
                ArrayList fMenuList = this.loginMapper.getMenuList(param);
                ConvertUtil.convertDataBaseMapToJavaMap(fMenuList);
                HashMap menuList = new HashMap();

                for(int myInfo = 0; myInfo < fMenuList.size(); ++myInfo) {
                    String session = this.getCtrlName(((Map)fMenuList.get(myInfo)).get("url") == null?"":((Map)fMenuList.get(myInfo)).get("url").toString());
                    if(!session.equals("")) {
                        menuList.put(session, ((Map)fMenuList.get(myInfo)).get("menuId").toString());
                    }
                }

                param.put("userId", userInfo.get("id").toString());
                MyInfo var16 = new MyInfo();
                var16.setData(userInfo.get("id").toString(), userInfo.get("adminLevel") == null?"":userInfo.get("adminLevel").toString(), userInfo.get("adminName").toString(), userInfo.get("adminPass").toString(), userInfo.get("adminPhone") == null?"":userInfo.get("adminPhone").toString(), userInfo.get("state") == null?"":userInfo.get("state").toString(), userInfo.get("regTime") == null?"":userInfo.get("regTime").toString(), userInfo.get("permission") == null?"":userInfo.get("permission").toString(), menuList, userInfo.get("role") == null?"":userInfo.get("role").toString(), userInfo.get("zcRegionId") != null?userInfo.get("zcRegionId").toString():"", userInfo.get("zcXxAddress") != null?userInfo.get("zcXxAddress").toString():"", userInfo.get("userEmail") != null?userInfo.get("userEmail").toString():"", userInfo.get("userPhone") != null?userInfo.get("userPhone").toString():"", userInfo.get("companyLxr") != null?userInfo.get("companyLxr").toString():"", userInfo.get("companyLxrPhone") != null?userInfo.get("companyLxrPhone").toString():"", userInfo.get("companyImg") != null?userInfo.get("companyImg").toString():"/resources/upload/images/company_mark/kunlun.png", "4");
                HttpSession var17 = request.getSession();
                var17.setAttribute("MY_INFO", var16);
            }
        } else {
            state = "0";
            msg = "请输入用户名和密码！";
        }

        res.put("state", state);
        res.put("msg", msg);
        this.echoJSON(response, res);
    }

    @RequestMapping(
            value = {"/main"},
            method = {RequestMethod.GET}
    )
    public String main(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        MyInfo myInfo = (MyInfo)session.getAttribute("MY_INFO");
        String level = myInfo.getAdminLevel() == null?"":myInfo.getAdminLevel();
        String permission = myInfo.getPermission();
        HashMap param = new HashMap();
        param.put("parentId", "0");
        param.put("permission", permission);
        param.put("adminLevel", level);
        ArrayList fMenuList = this.loginMapper.getMenuList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(fMenuList);
        Iterator title = fMenuList.iterator();

        while(title.hasNext()) {
            Map userInfo = (Map)title.next();
            param.put("parentId", userInfo.get("menuId"));
            ArrayList subMenuList = this.loginMapper.getMenuList(param);
            ConvertUtil.convertDataBaseMapToJavaMap(subMenuList);
            userInfo.put("subMenuList", subMenuList);
        }

        String title1 = "";
        if("1".equals(level)) {
            title1 = ConstantUtil.TITLE_ + ConstantUtil.TITLE_ADMIN;
        } else if("2".equals(level)) {
            title1 = ConstantUtil.TITLE_ + ConstantUtil.TITLE_SHENHUI;
        } else {
            title1 = ConstantUtil.TITLE_;
        }

        model.addAttribute("menuList", fMenuList);
        model.addAttribute("title", title1);
        HashMap userInfo1 = new HashMap();
        ((Map)userInfo1).put("userImg", ConstantUtil.defaultUserImg);
        ((Map)userInfo1).put("userName", myInfo.getUserName());
        model.addAttribute("userInfo", userInfo1);
        return "houtai/main";
    }

    @RequestMapping(
            value = {"/ht/Login/Login-getMyInfo"},
            method = {RequestMethod.GET}
    )
    public String getMyInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "houtai/login/info";
    }

    private String getCtrlName(String pathInfo) {
        String ret = pathInfo.split("-")[0];
        String[] strArr = ret.split("/");
        ret = strArr[strArr.length - 1];
        return ret;
    }

    public String getMenuUrl(int menuId) {
        HashMap param = new HashMap();
        param.put("menu_id", Integer.valueOf(menuId));
        Map menuInfo = this.menuMapper.getMenuInfo(param);
        ConvertUtil.convertDataBaseMapToJavaMap(menuInfo);
        String str = menuInfo.get("url").toString();
        return str == ""?"":str;
    }
}