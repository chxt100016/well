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
import org.wella.houtai.sys.mapper.MenuMapper;
import org.wella.houtai.sys.mapper.RoleMapper;

@Controller
public class RoleCtrl extends BaseController {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMapper roleMapper;

    public RoleCtrl() {
    }

    @RequestMapping({"ht/sys/RoleCtrl-roleList"})
    public String roleList(HttpServletRequest request, HttpServletResponse response, Model model) {
        String act = CommonUtil.GetRequestParam(request, "act", "");
        int page = CommonUtil.getIntFromString(request.getParameter("page"));
        HashMap param = new HashMap();
        byte pageSize = 10;
        param.put("pageNum", " limit " + page * pageSize + " ," + pageSize);
        ArrayList roleList = this.roleMapper.getRoleList(param);
        int totalCount = this.roleMapper.getRoleListCount(param);
        ConvertUtil.convertDataBaseMapToJavaMap(roleList);
        model.addAttribute("roleList", roleList);
        request.setAttribute("pageSize", Integer.valueOf(pageSize));
        request.setAttribute("totalCount", Integer.valueOf(totalCount));
        request.setAttribute("page", Integer.valueOf(page));
        request.setAttribute("act", act);
        return "houtai/sys/role_list";
    }

    @RequestMapping({"ht/sys/RoleCtrl-editRole"})
    public String editRole(HttpServletRequest request, HttpServletResponse response, Model model) {
        String roleId = CommonUtil.GetRequestParam(request, "roleId", "0");
        Map roleInfo = this.getMyOneSingBO("wa_user_role", "role_id", roleId);
        model.addAttribute("roleInfo", roleInfo);
        HashMap param = new HashMap();
        param.put("strsql", "select * from wa_menu where parent_id=0");
        ArrayList menuList = this.commonMapper.simpleSelectReturnList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(menuList);

        for(int i = 0; i < menuList.size(); ++i) {
            param.put("strsql", "select * from wa_menu where parent_id=" + ((Map)menuList.get(i)).get("menuId").toString());
            ArrayList subMenuList = this.commonMapper.simpleSelectReturnList(param);
            ConvertUtil.convertDataBaseMapToJavaMap(subMenuList);
            ((Map)menuList.get(i)).put("submenu", subMenuList);
        }

        model.addAttribute("menuList", menuList);
        return "houtai/sys/role_edit";
    }

    @RequestMapping({"ht/sys/RoleCtrl-updateRoleInfo"})
    public void updateNotice(HttpServletRequest request, HttpServletResponse response, Model model) {
        String roleId = CommonUtil.GetRequestParam(request, "roleId", "0");
        String roleName = CommonUtil.GetRequestParam(request, "roleName", "");
        String orderNum = CommonUtil.GetRequestParam(request, "orderNum", "0");
        String permission = CommonUtil.GetRequestParam(request, "permission", "");
        HashMap param = new HashMap();
        HashMap mapClass = new HashMap();
        JSONObject obj = new JSONObject();
        mapClass.put("role_id", roleId);
        mapClass.put("role_name", roleName);
        mapClass.put("order_num", orderNum);
        mapClass.put("permission", permission);
        param.put("mapClass", mapClass);
        param.put("tableName", "wa_user_role");

        try {
            if("0".equals(roleId)) {
                this.commonMapper.insertSingleBO(param);
            } else {
                param.put("keyName", "role_id");
                param.put("keyValue", roleId);
                this.commonMapper.updateSingleBO(param);
            }

            obj.put("state", Integer.valueOf(1));
            obj.put("msg", "操作成功！");
            this.echoJSON(response, obj);
        } catch (Exception var12) {
            var12.printStackTrace();
            obj.put("state", Integer.valueOf(-1));
            obj.put("msg", "操作错误！");
            this.echoJSON(response, obj);
        }

    }

    @RequestMapping({"ht/sys/RoleCtrl-delRole"})
    public void delRole(HttpServletRequest request, HttpServletResponse response, Model model) {
        String roleId = CommonUtil.GetRequestParam(request, "roleId", "0");
        HashMap param = new HashMap();
        JSONObject obj = new JSONObject();

        try {
            param.put("keyName", "role_id");
            param.put("keyValue", roleId);
            param.put("tableName", "wa_user_role");
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