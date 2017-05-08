package org.wella.houtai.sys.ctrl;

import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.houtai.sys.mapper.AdminMapper;
import org.wella.houtai.sys.mapper.RoleMapper;

@Controller
public class AdminCtrl extends BaseController {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RoleMapper roleMapper;

    public AdminCtrl() {
    }

    @RequestMapping({"ht/sys/AdminCtrl-adminList"})
    public String roleList(HttpServletRequest request, HttpServletResponse response, Model model) {
        String act = CommonUtil.GetRequestParam(request, "act", "");
        int page = CommonUtil.getIntFromString(request.getParameter("page"));
        HashMap param = new HashMap();
        byte pageSize = 10;
        param.put("pageNum", " limit " + page * pageSize + " ," + pageSize);
        ArrayList adminList = this.adminMapper.getAdminList(param);
        int totalCount = this.adminMapper.getAdminListCount(param);
        ConvertUtil.convertDataBaseMapToJavaMap(adminList);
        model.addAttribute("adminList", adminList);
        request.setAttribute("pageSize", Integer.valueOf(pageSize));
        request.setAttribute("totalCount", Integer.valueOf(totalCount));
        request.setAttribute("page", Integer.valueOf(page));
        request.setAttribute("act", act);
        return "houtai/sys/admin_list";
    }

    @RequestMapping({"ht/sys/AdminCtrl-editAdmin"})
    public String editAdmin(HttpServletRequest request, HttpServletResponse response, Model model) {
        String adminId = CommonUtil.GetRequestParam(request, "adminId", "0");
        Map adminInfo = this.getMyOneSingBO("wa_admin", "id", adminId);
        model.addAttribute("adminInfo", adminInfo);
        HashMap param = new HashMap();
        param.put("strsql", "select * from wa_user_role");
        ArrayList roleList = this.commonMapper.simpleSelectReturnList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(roleList);
        model.addAttribute("roleList", roleList);
        return "houtai/sys/admin_edit";
    }

    @RequestMapping({"ht/sys/AdminCtrl-updateAdminInfo"})
    public void updateNotice(HttpServletRequest request, HttpServletResponse response, Model model) {
        String adminId = CommonUtil.GetRequestParam(request, "adminId", "0");
        String roleId = CommonUtil.GetRequestParam(request, "roleId", "0");
        String adminName = CommonUtil.GetRequestParam(request, "adminName", "");
        String adminPhone = CommonUtil.GetRequestParam(request, "adminPhone", "0");
        String permission = CommonUtil.GetRequestParam(request, "permission", "");
        HashMap param = new HashMap();
        HashMap mapClass = new HashMap();
        JSONObject obj = new JSONObject();
        mapClass.put("id", adminId);
        mapClass.put("adminName", adminName);
        mapClass.put("adminPhone", adminPhone);
        mapClass.put("role", roleId);
        mapClass.put("permission", permission);
        param.put("mapClass", mapClass);
        param.put("tableName", "wa_admin");

        try {
            if("0".equals(adminId)) {
                this.commonMapper.insertSingleBO(param);
            } else {
                param.put("keyName", "id");
                param.put("keyValue", adminId);
                this.commonMapper.updateSingleBO(param);
            }

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

    @RequestMapping({"ht/sys/AdminCtrl-delRole"})
    public void delRole(HttpServletRequest request, HttpServletResponse response, Model model) {
        String adminId = CommonUtil.GetRequestParam(request, "adminId", "0");
        HashMap param = new HashMap();
        JSONObject obj = new JSONObject();

        try {
            param.put("keyName", "id");
            param.put("keyValue", adminId);
            param.put("tableName", "wa_admin");
            this.commonMapper.deleteSingleBO(param);
            obj.put("state", Integer.valueOf(1));
            obj.put("msg", "操作成功！");
            this.echoJSON(response, obj);
        } catch (Exception var8) {
            var8.printStackTrace();
            obj.put("state", Integer.valueOf(-1));
            obj.put("msg", "操作错误！");
            this.echoJSON(response, obj);
        }
    }
}