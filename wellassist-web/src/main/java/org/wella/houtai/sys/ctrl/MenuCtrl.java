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
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.houtai.sys.mapper.MenuMapper;
import org.wella.houtai.sys.mapper.RoleMapper;

@Controller
public class MenuCtrl extends BaseController {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMapper roleMapper;

    public MenuCtrl() {
    }

    @RequestMapping({"ht/sys/MenuCtrl-menuList"})
    public String menuList(HttpServletRequest request, HttpServletResponse response, Model model) {
        String act = CommonUtil.GetRequestParam(request, "act", "");
        int page = CommonUtil.getIntFromString(request.getParameter("page"));
        String parent_id = CommonUtil.GetRequestParam(request, "parent_id", "0");
        HashMap param = new HashMap();
        byte pageSize = 10;
        param.put("pageNum", " limit " + page * pageSize + " ," + pageSize);
        if(!act.equals("submenu")) {
            param.put("where", " where parent_id=\'0\'");
        } else {
            param.put("where", " where parent_id=\'" + parent_id + "\'");
        }

        ArrayList topMenuList = this.menuMapper.getMenuList(param);
        int totalCount = this.menuMapper.getMenuListCount(param);
        ConvertUtil.convertDataBaseMapToJavaMap(topMenuList);
        model.addAttribute("topMenuList", topMenuList);
        request.setAttribute("pageSize", Integer.valueOf(pageSize));
        request.setAttribute("totalCount", Integer.valueOf(totalCount));
        request.setAttribute("page", Integer.valueOf(page));
        request.setAttribute("parent_id", parent_id);
        request.setAttribute("act", act);
        return "views/houtai/sys/menu.jsp";
    }

    @RequestMapping({"ht/sys/MenuCtrl-getDetailData"})
    public String getDetailData(HttpServletRequest request, HttpServletResponse response, Model model) {
        HashMap param = new HashMap();
        param.put("where", " where parent_id=0");
        ArrayList topMenuList = this.menuMapper.getMenuList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(topMenuList);
        model.addAttribute("topMenuList", topMenuList);
        int selMenuId = Integer.parseInt(CommonUtil.GetRequestParam(request, "selIdx", ""));
        param.put("menu_id", Integer.valueOf(selMenuId));
        Map menuInfo = this.menuMapper.getMenuInfo(param);
        ConvertUtil.convertDataBaseMapToJavaMap(menuInfo);
        model.addAttribute("menuInfo", menuInfo);
        return "views/houtai/sys/menu_edit.jsp";
    }

    @RequestMapping({"/ht/sys/MenuCtrl-saveMenuInfo"})
    public void saveMenuInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
        String act = CommonUtil.GetRequestParam(request, "act", "");
        int menuId = CommonUtil.getIntFromString(CommonUtil.GetRequestParam(request, "menuId", "0"));
        HashMap param = new HashMap();
        JSONObject obj = new JSONObject();
        String menuUrl;
        if(act.equals("del")) {
            param.put("menu_id", Integer.valueOf(menuId));

            try {
                int menuTitle = this.menuMapper.deleteMenu(param);
                menuUrl = "";
                if(menuTitle > 0) {
                    menuUrl = "1";
                } else {
                    menuUrl = "0";
                }

                obj.put("result", menuUrl);
                this.echoJSON(response, obj);
            } catch (Exception var13) {
                var13.printStackTrace();
                obj.put("result", "0");
                this.echoJSON(response, obj);
            }
        } else {
            String menuTitle1 = CommonUtil.GetRequestParam(request, "menuTitle", "");
            menuUrl = CommonUtil.GetRequestParam(request, "menuUrl", "");
            int sortNum = CommonUtil.getIntFromString(request.getParameter("sortNum"));
            int parentIdx = CommonUtil.getIntFromString(request.getParameter("parentIdx"));
            int is_show = CommonUtil.getIntFromString(request.getParameter("is_show"));
            param.put("menu_name", menuTitle1);
            param.put("menu_addr", menuUrl);
            param.put("menu_order", Integer.valueOf(sortNum));
            param.put("menu_icon", "icon-star");
            param.put("is_show", Integer.valueOf(is_show));
            if(menuId == 0) {
                param.put("parent_id", Integer.valueOf(parentIdx));
                this.menuMapper.addNewMenu(param);
                menuId = 1;
            } else {
                param.put("menuIndex", Integer.valueOf(menuId));
                this.menuMapper.updateMenuInfo(param);
            }

            obj.put("result", Integer.valueOf(menuId));
            this.echoJSON(response, obj);
        }

    }

    @RequestMapping({"ht/sys/MenuCtrl-search"})
    public String search(HttpServletRequest request, HttpServletResponse response, Model model) {
        String pageSize = CommonUtil.GetRequestParam(request, "pageSize", "0");
        String searchKey = CommonUtil.GetRequestParam(request, "searchKey", "");
        String sortFld = CommonUtil.GetRequestParam(request, "sortFld", "0");
        String searchType = CommonUtil.GetRequestParam(request, "searchType", "-1");
        String sortDesc = CommonUtil.GetRequestParam(request, "sortDesc", "0");
        HashMap param = new HashMap();
        int pageNumber = CommonUtil.getIntFromString(request.getParameter("pageNumber"));
        param.put("pageNum", " limit " + (pageNumber - 1) * CommonUtil.getIntFromString(pageSize) + " ," + pageSize);
        if(searchType.equals("-1")) {
            param.put("where", " where parent_id<>0");
        } else {
            param.put("where", " where parent_id=\'" + searchType + "\'");
        }

        if(!searchKey.equals("")) {
            param.put("like", " AND title like \'%" + searchKey + "%\'");
        }

        ArrayList menuList = this.menuMapper.getMenuList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(menuList);
        int totalCount = this.menuMapper.getMenuListCount(param);
        model.addAttribute("menuList", menuList);
        model.addAttribute("totalCount", Integer.valueOf(totalCount));
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNumber", Integer.valueOf(pageNumber));
        model.addAttribute("sortFld", sortFld);
        model.addAttribute("sortDesc", sortDesc);
        return "views/houtai/sys/menu_list.jsp";
    }

    @RequestMapping({"/ht/sys/MenuCtrl-getMenuInfo"})
    public void getMenuInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
        int menuId = CommonUtil.getIntFromString(CommonUtil.GetRequestParam(request, "menuId", "0"));
        new HashMap();
        HashMap param = new HashMap();
        JSONObject obj = new JSONObject();
        param.put("menu_id", Integer.valueOf(menuId));

        try {
            Map menuInfo = this.menuMapper.getMenuInfo(param);
            obj.put("result", menuInfo);
            this.echoJSON(response, obj);
        } catch (Exception var9) {
            var9.printStackTrace();
            obj.put("result", "0");
            this.echoJSON(response, obj);
        }

    }

    @RequestMapping({"/ht/sys/MenuCtrl-delMenu"})
    public void delMenuInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
        int menuId = CommonUtil.getIntFromString(CommonUtil.GetRequestParam(request, "menuId", "0"));
        HashMap param = new HashMap();
        JSONObject obj = new JSONObject();
        param.put("menu_id", Integer.valueOf(menuId));

        try {
            int e = this.menuMapper.deleteMenu(param);
            String retVal = "";
            if(e > 0) {
                retVal = "1";
            } else {
                retVal = "0";
            }

            obj.put("result", retVal);
            this.echoJSON(response, obj);
        } catch (Exception var9) {
            var9.printStackTrace();
            obj.put("result", "0");
            this.echoJSON(response, obj);
        }

    }

    @RequestMapping({"/ht/sys/MenuCtrl-updateMenuInfo"})
    public void updateMenuInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
        JSONObject res = new JSONObject();
        String menu_id = CommonUtil.GetRequestParam(request, "menu_id", "0");

        try {
            Map e = this.getBoClass(request, "wa_menu");
            if(menu_id.equals("0")) {
                this.commonMapper.insertSingleBO(e);
            } else {
                this.commonMapper.updateSingleBO(e);
            }

            res.put("state", "1");
            res.put("content", ConstantUtil.MSG_SUCCESS);
        } catch (Exception var7) {
            var7.printStackTrace();
            res.put("state", "2");
            res.put("content", ConstantUtil.MSG_FAILS);
        }

        this.echo(response, res);
    }
}