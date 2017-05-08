package org.wella.houtai.notify.ctrl;

import com.alibaba.fastjson.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.wella.houtai.notify.mapper.HoutaiNotifyMapper;

@Controller
public class HoutaiNotifyListCtrl extends BaseController {
    @Autowired
    private HoutaiNotifyMapper notifyMapper;

    public HoutaiNotifyListCtrl() {
    }

    @RequestMapping({"/houtai/notify/HoutaiNotifyListCtrl-getNotifyList"})
    public String getNotifyList(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map param = this.getConditionParam(request);
        int page = CommonUtil.getIntFromString(request.getParameter("page"));
        int pageSize = ConstantUtil.PAGESIZE;
        param.put("pageNum", " limit " + page * pageSize + " ," + pageSize);
        ArrayList notifyList = this.notifyMapper.getNotifyList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(notifyList);
        model.addAttribute("notifyList", notifyList);
        int totalCount = this.notifyMapper.getNotifyListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        request.setAttribute("pageSize", Integer.valueOf(pageSize));
        request.setAttribute("totalCount", Integer.valueOf(totalCount));
        request.setAttribute("page", Integer.valueOf(page));
        request.setAttribute("param", param);
        return "views/houtai/notify/notify_list.jsp";
    }

    @RequestMapping({"/houtai/notify/HoutaiNotifyListCtrl-getNotifyDetail"})
    public String getOrderDetail(HttpServletRequest request, HttpServletResponse response, Model model) {
        String notifyId = CommonUtil.GetRequestParam(request, "notifyId", "0");
        Map notifyInfo = this.getMyOneSingBO("wa_info", "tx_id", notifyId);
        model.addAttribute("notifyInfo", notifyInfo);
        return "views/houtai/notify/notify_detail.jsp";
    }

    @RequestMapping({"/houtai/notify/HoutaiNotifyListCtrl-updatenotify"})
    public void updateNotify(HttpServletRequest request, HttpServletResponse response, Model model) {
        String notifyId = CommonUtil.GetRequestParam(request, "notifyId", "0");
        String notifyTitle = CommonUtil.GetRequestParam(request, "notifyTitle", "");
        String notifyType = CommonUtil.GetRequestParam(request, "notifyType", "0");
        String state = CommonUtil.GetRequestParam(request, "state", "0");
        String notifyContent = CommonUtil.GetRequestParam(request, "notifyContent", "");
        HashMap param = new HashMap();
        HashMap mapClass = new HashMap();
        JSONObject obj = new JSONObject();
        mapClass.put("tx_id", notifyId);
        mapClass.put("tx_name", notifyTitle);
        mapClass.put("tx_type", notifyType);
        mapClass.put("state", state);
        mapClass.put("tx_content", notifyContent);
        SimpleDateFormat nDate = new SimpleDateFormat("yyyy-MM-dd");
        mapClass.put("tx_date", nDate.format(new Date()));
        param.put("mapClass", mapClass);
        param.put("tableName", "wa_info");

        try {
            if("0".equals(notifyId)) {
                this.commonMapper.insertSingleBO(param);
            } else {
                param.put("keyName", "tx_id");
                param.put("keyValue", notifyId);
                this.commonMapper.updateSingleBO(param);
            }

            obj.put("state", Integer.valueOf(1));
            obj.put("msg", "操作成功！");
            this.echoJSON(response, obj);
        } catch (Exception var14) {
            var14.printStackTrace();
            obj.put("state", Integer.valueOf(-1));
            obj.put("msg", "操作错误！");
            this.echoJSON(response, obj);
        }

    }

    @RequestMapping({"/houtai/notify/HoutaiNotifyListCtrl-delNotify"})
    public void delNotify(HttpServletRequest request, HttpServletResponse response, Model model) {
        String notifyId = CommonUtil.GetRequestParam(request, "notifyId", "0");
        HashMap param = new HashMap();
        JSONObject obj = new JSONObject();

        try {
            param.put("keyName", "tx_id");
            param.put("keyValue", notifyId);
            param.put("tableName", "wa_info");
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