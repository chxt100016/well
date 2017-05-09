package org.wella.houtai.enterprise.ctrl;

import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.vo.MyInfo;
import org.wella.houtai.enterprise.mapper.HoutaiEnterpriseUserMapper;

@Controller
public class UserCtrl extends BaseController {
    @Autowired
    private HoutaiEnterpriseUserMapper userMapper;

    public UserCtrl() {
    }

    @RequestMapping({"/ht/Enterprise/UserCtrl-userList/userType/{userType}"})
    public String start(@PathVariable int userType, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("userType", Integer.valueOf(userType));
        return "views/houtai/enterprise/user_list.jsp";
    }

    @RequestMapping({"/ht/Enterprise/UserCtrl-shenheList"})
    public String shenheList(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("userType", "4");
        return "views/houtai/enterprise/shenhe_list.jsp";
    }

    @RequestMapping(
            value = {"/ht/Enterprise/UserCtrl-tablePageSwitch"},
            method = {RequestMethod.POST}
    )
    public String tablePageSwitch(HttpServletRequest request, HttpServletResponse response, Model model) {
        byte pageNo = 1;
        String pageNumber = CommonUtil.GetRequestParam(request, "pageNumber", "");
        String pageSize = CommonUtil.GetRequestParam(request, "pageSize", "");
        String searchKey = CommonUtil.GetRequestParam(request, "searchKey", "");
        String searchType = CommonUtil.GetRequestParam(request, "searchType", "-1");
        String userType = CommonUtil.GetRequestParam(request, "userType", "");
        String sortFld = CommonUtil.GetRequestParam(request, "sortFld", "");
        String sortDesc = CommonUtil.GetRequestParam(request, "sortDesc", "");
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("searchKey", searchKey);
        model.addAttribute("sortFld", sortFld);
        model.addAttribute("sortDesc", sortDesc);
        model.addAttribute("userType", userType);
        model.addAttribute("searchType", searchType);
        if("".equals(pageNumber)) {
            pageNo = 1;
        }

        int start = (pageNo - 1) * CommonUtil.getIntFromString(pageSize);
        String sort = sortFld + " " + sortDesc;
        HashMap param = new HashMap();
        param.put("searchType", Integer.valueOf(CommonUtil.getIntFromString(searchType) - 1));
        param.put("searchKey", searchKey);
        param.put("start", Integer.valueOf(start));
        param.put("pageSize", Integer.valueOf(CommonUtil.getIntFromString(pageSize)));
        param.put("sort", sort);
        param.put("userType", Integer.valueOf(CommonUtil.getIntFromString(userType)));
        HttpSession session = request.getSession();
        MyInfo myInfo = (MyInfo)session.getAttribute("MY_INFO");
        String userID = myInfo.getUserId();
        int sess_adminId = CommonUtil.getIntFromString(userID);
        param.put("shenheren", Integer.valueOf(sess_adminId));
        Map countInfo = this.userMapper.getUserCount(param);
        ConvertUtil.convertDataBaseMapToJavaMap(countInfo);
        String totalCount = countInfo.get("totalCount").toString();
        ArrayList arrayList = this.userMapper.getUserList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(arrayList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("arrayList", arrayList);
        return "4".equals(userType)? "views/houtai/enterprise/shenhe_table.jsp" :  "views/houtai/enterprise/user_table.jsp";
    }

    @RequestMapping({"/ht/Enterprise/UserCtrl-onSetLockState"})
    public void onSetLockState(HttpServletRequest request, HttpServletResponse response, Model model) {
        String selIdx = CommonUtil.GetRequestParam(request, "selIdx", "");
        String state = CommonUtil.GetRequestParam(request, "state", "");
        HashMap param = new HashMap();
        param.put("state", Integer.valueOf(CommonUtil.getIntFromString(state)));
        param.put("selIdx", Integer.valueOf(CommonUtil.getIntFromString(selIdx)));
        this.userMapper.setLockState(param);
    }

    @RequestMapping({"/ht/Enterprise/UserCtrl-deleteData"})
    public void deleteData(HttpServletRequest request, HttpServletResponse response, Model model) {
        String selIdx = CommonUtil.GetRequestParam(request, "selIdx", "");
        HashMap param = new HashMap();
        param.put("selIdx", Integer.valueOf(CommonUtil.getIntFromString(selIdx)));
        this.userMapper.deleteData(param);
    }

    @RequestMapping({"/ht/Common/showDialog"})
    public String showDialog(HttpServletRequest request, HttpServletResponse response, Model model) {
        String dlgType = CommonUtil.GetRequestParam(request, "dlgType", "1");
        model.addAttribute("dlgType", dlgType);
        ArrayList arrayList1;
        if("1".equals(dlgType)) {
            arrayList1 = this.userMapper.getShenherenList();
            ConvertUtil.convertDataBaseMapToJavaMap(arrayList1);
            model.addAttribute("arrayList", arrayList1);
            return "houtai/common/dlg_status";
        } else if("2".equals(dlgType)) {
            arrayList1 = this.userMapper.getGonghuofangList();
            ConvertUtil.convertDataBaseMapToJavaMap(arrayList1);
            model.addAttribute("arrayList", arrayList1);
            return "views/houtai/common/dlg_shenhe.jsp";
        } else {
            return "views/houtai/common/dlg_edit.jsp";
        }
    }

    @RequestMapping({"/ht/Enterprise/UserCtrl-setStatus"})
    public void setStatus(HttpServletRequest request, HttpServletResponse response, Model model) {
        String selIdx = CommonUtil.GetRequestParam(request, "selIdx", "");
        String state = CommonUtil.GetRequestParam(request, "state", "");
        String reason = CommonUtil.GetRequestParam(request, "reason", "");
        String shenheren = CommonUtil.GetRequestParam(request, "shenheren", "");
        HashMap param = new HashMap();
        param.put("selIdx", Integer.valueOf(CommonUtil.getIntFromString(selIdx)));
        param.put("state", Integer.valueOf(CommonUtil.getIntFromString(state)));
        param.put("reason", reason);
        param.put("shenheren", Integer.valueOf(CommonUtil.getIntFromString(shenheren)));
        this.userMapper.setState(param);
    }

    @RequestMapping({"/ht/Enterprise/UserCtrl-setGonghuofang"})
    public void setGonghuofang(HttpServletRequest request, HttpServletResponse response, Model model) {
        String selIdx = CommonUtil.GetRequestParam(request, "selIdx", "");
        String state = CommonUtil.GetRequestParam(request, "state", "");
        String reason = CommonUtil.GetRequestParam(request, "reason", "");
        String gonghuofang = CommonUtil.GetRequestParam(request, "gonghuofang", "");
        HashMap param = new HashMap();
        param.put("selIdx", Integer.valueOf(CommonUtil.getIntFromString(selIdx)));
        param.put("state", Integer.valueOf(CommonUtil.getIntFromString(state)));
        param.put("reason", reason);
        param.put("gonghuofang", Integer.valueOf(CommonUtil.getIntFromString(gonghuofang)));
        this.userMapper.setGonghuofang(param);
    }

    @RequestMapping({"/ht/Enterprise/UserCtrl-getDetailData"})
    public String getDetailData(HttpServletRequest request, HttpServletResponse response, Model model) {
        String selIdx = CommonUtil.GetRequestParam(request, "selIdx", "");
        HashMap param = new HashMap();
        param.put("selIdx", Integer.valueOf(CommonUtil.getIntFromString(selIdx)));
        Map userInfo = this.userMapper.getUserInfo(param);
        ConvertUtil.convertDataBaseMapToJavaMap(userInfo);
        model.addAttribute("userInfo", userInfo);
        return "views/houtai/enterprise/user_edit.jsp";
    }

    @RequestMapping({"/ht/Enterprise/UserCtrl-saveUserInfo"})
    public void saveUserInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
        String selIdx = CommonUtil.GetRequestParam(request, "selIdx", "");
        String regName = CommonUtil.GetRequestParam(request, "regName", "");
        String mobileAddr = CommonUtil.GetRequestParam(request, "mobileAddr", "");
        String state = "0";
        JSONObject res = new JSONObject();
        HashMap param = new HashMap();
        param.put("selIdx", Integer.valueOf(CommonUtil.getIntFromString(selIdx)));
        param.put("regName", regName);
        param.put("mobileAddr", mobileAddr);
        if(!regName.equals("") && !mobileAddr.equals("")) {
            Map userCount = this.userMapper.getDuplicateCount(param);
            ConvertUtil.convertDataBaseMapToJavaMap(userCount);
            String cnt = userCount.get("cnt").toString();
            if(CommonUtil.getIntFromString(cnt) > 0) {
                state = "1";
            } else {
                this.userMapper.saveUserInfo(param);
                state = "2";
            }
        } else {
            state = "0";
        }

        res.put("state", state);
        this.echoJSON(response, res);
    }

    @RequestMapping({"/ht/Enterprise/UserCtrl-saveUserPass"})
    public void saveUserPass(HttpServletRequest request, HttpServletResponse response, Model model) {
        String selIdx = CommonUtil.GetRequestParam(request, "selIdx", "");
        String regPass = CommonUtil.GetRequestParam(request, "regPass", "");
        String state = "1";
        JSONObject res = new JSONObject();
        HashMap param = new HashMap();
        param.put("selIdx", Integer.valueOf(CommonUtil.getIntFromString(selIdx)));
        param.put("regPass", CommonUtil.MD5(regPass));
        this.userMapper.saveUserPass(param);
        res.put("state", state);
        this.echoJSON(response, res);
    }
}