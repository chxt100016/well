package org.wella.houtai.user.ctrl;

import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.Date;
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
import org.wella.common.ctrl.UploadController;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.vo.MyInfo;
import org.wella.houtai.user.mapper.HoutaiUserSellerMapper;

@Controller
public class HoutaiUserBuyerCtrl extends BaseController {
    @Autowired
    private UploadController uploadController;
    @Autowired
    private HoutaiUserSellerMapper sellerMapper;

    public HoutaiUserBuyerCtrl() {
    }

    @RequestMapping({"/houtai/user/HoutaiUserBuyerCtrl-getBuyerList"})
    public String getBuyerList(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map param = this.getConditionParam(request);
        param.put("userType", "1");
        ArrayList sellerList = this.sellerMapper.getUserList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(sellerList);
        model.addAttribute("sellerList", sellerList);
        int totalCount = this.sellerMapper.getUserListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        return "views/houtai/user/buyer/buyer_list.jsp";
    }

    @RequestMapping({"/houtai/user/HoutaiUserBuyerCtrl-getBuyerDetail"})
    public String getBuyerDetail(HttpServletRequest request, HttpServletResponse response, Model model) {
        String userId = CommonUtil.GetRequestParam(request, "userId", "0");
        if(CommonUtil.getIntFromString(userId) > 0) {
            Map userMainInfo = this.getMyOneSingBO("wa_user", "user_id", userId);
            Map userOtherInfo = this.getMyOneSingBO("wa_userinfo", "user_id", userId);
            model.addAttribute("userMainInfo", userMainInfo);
            model.addAttribute("userOtherInfo", userOtherInfo);
            if(userOtherInfo != null) {
                userOtherInfo.put("zcRegionName", "");

                try {
                    userOtherInfo.put("zcRegionName", this.getRegionDetailName(userOtherInfo.get("zcRegionId") != null?userOtherInfo.get("zcRegionId").toString():"0"));
                } catch (Exception var8) {
                    ;
                }
            }
        }

        model.addAttribute("userId", userId);
        return "views/houtai/user/buyer/buyer_detail.jsp";
    }

    @RequestMapping({"/houtai/user/HoutaiUserBuyerCtrl-editBuyerInfo"})
    public String editBuyerInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
        String userId = CommonUtil.GetRequestParam(request, "userId", "0");
        Map param;
        if(CommonUtil.getIntFromString(userId) > 0) {
            param = this.getMyOneSingBO("wa_user", "user_id", userId);
            Map regionList = this.getMyOneSingBO("wa_userinfo", "user_id", userId);
            model.addAttribute("userMainInfo", param);
            model.addAttribute("userOtherInfo", regionList);
        }

        param = this.getConditionParam(request);
        param.put("parentRegionId", "0");
        ArrayList regionList1 = this.sellerMapper.getRegionList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(regionList1);
        model.addAttribute("userId", userId);
        model.addAttribute("regionList", regionList1);
        return "views/houtai/user/buyer/buyer_edit.jsp";
    }

    @RequestMapping(
            value = {"/houtai/user/HoutaiUserBuyerCtrl-setBuyerInfo"},
            method = {RequestMethod.POST}
    )
    public void setBuyerInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        String userId = CommonUtil.GetRequestParam(request, "userId", "0");

        try {
            MyInfo e = null;
            HttpSession session = request.getSession();
            e = (MyInfo)session.getAttribute("MY_INFO");
            String adminId = e.getUserId();
            if(userId.equals("0")) {
                String userEmail = CommonUtil.GetRequestParam(request, "userEmail", "");
                String userPhone = CommonUtil.GetRequestParam(request, "userPhone", "");
                Map userEmailInfo = this.getMyOneSingBO("wa_user", "user_email", userEmail);
                Map userPhoneInfo = this.getMyOneSingBO("wa_user", "user_phone", userPhone);
                if(userEmailInfo != null) {
                    ret = "-3";
                    obj.put("content", "电子邮箱已存在!");
                }

                if(userPhoneInfo != null) {
                    ret = "-4";
                    obj.put("content", "手机号码已存在!");
                }

                if(ret.equals("-1")) {
                    String zcRegionId_0 = CommonUtil.GetRequestParam(request, "zcRegionId_0", "");
                    String zcRegionId_1 = CommonUtil.GetRequestParam(request, "zcRegionId_1", "");
                    String zcRegionId_2 = CommonUtil.GetRequestParam(request, "zcRegionId_2", "");
                    int zcRegionId = CommonUtil.getIntFromString(zcRegionId_0);
                    if(CommonUtil.getIntFromString(zcRegionId_1) > 0) {
                        zcRegionId = CommonUtil.getIntFromString(zcRegionId_1);
                    }

                    if(CommonUtil.getIntFromString(zcRegionId_2) > 0) {
                        zcRegionId = CommonUtil.getIntFromString(zcRegionId_2);
                    }

                    String companyImg = CommonUtil.GetRequestParam(request, "companyImg", "");
                    String companyYyZzImg = CommonUtil.GetRequestParam(request, "companyYyZzImg", "");
                    String companyKhXkzImg = CommonUtil.GetRequestParam(request, "companyKhXkzImg", "");
                    String companyTxkzImg = CommonUtil.GetRequestParam(request, "companyTxkzImg", "");
                    if(!companyImg.equals("") && companyImg.indexOf("temp") > 0) {
                        companyImg = this.uploadController.moveFile(request, companyImg, "business/license");
                    }

                    if(!companyYyZzImg.equals("") && companyYyZzImg.indexOf("temp") > 0) {
                        companyYyZzImg = this.uploadController.moveFile(request, companyYyZzImg, "business/license");
                    }

                    if(!companyKhXkzImg.equals("") && companyKhXkzImg.indexOf("temp") > 0) {
                        companyKhXkzImg = this.uploadController.moveFile(request, companyKhXkzImg, "business/license");
                    }

                    if(!companyTxkzImg.equals("") && companyTxkzImg.indexOf("temp") > 0) {
                        companyTxkzImg = this.uploadController.moveFile(request, companyTxkzImg, "business/license");
                    }

                    Map param = this.getBoClass(request, "wa_user");
                    Map mapClass = (Map)param.get("mapClass");
                    mapClass.put("user_type", Integer.valueOf(1));
                    mapClass.put("create_date", new Date());
                    mapClass.put("user_pass", CommonUtil.MD5("123456"));
                    mapClass.put("create_user_id", adminId);
                    mapClass.put("user_state", Integer.valueOf(1));
                    this.commonMapper.insertSingleBO(param);
                    userId = param.get("newId").toString();
                    param = this.getBoClass(request, "wa_userinfo");
                    mapClass = (Map)param.get("mapClass");
                    mapClass.put("user_id", userId);
                    mapClass.put("zc_region_id", Integer.valueOf(zcRegionId));
                    mapClass.put("company_img", companyImg);
                    mapClass.put("company_yy_zz_img", companyYyZzImg);
                    mapClass.put("company_xkz_img", companyKhXkzImg);
                    mapClass.put("company_txkz_img", companyTxkzImg);
                    this.commonMapper.insertSingleBO(param);
                    ret = "1";
                    obj.put("content", ConstantUtil.MSG_SUCCESS);
                }
            }
        } catch (Exception var24) {
            ret = "-2";
            obj.put("content", ConstantUtil.MSG_FAILS);
        }

        obj.put("status", ret);
        this.echoJSON(response, obj);
    }
}