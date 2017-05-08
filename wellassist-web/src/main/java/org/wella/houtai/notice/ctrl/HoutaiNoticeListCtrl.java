package org.wella.houtai.notice.ctrl;

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
import org.wella.houtai.notice.mapper.HoutaiNoticeMapper;

@Controller
public class HoutaiNoticeListCtrl extends BaseController {
    @Autowired
    private HoutaiNoticeMapper NoticeMapper;

    public HoutaiNoticeListCtrl() {
    }

    @RequestMapping({"/houtai/notice/HoutaiNoticeListCtrl-getNoticeList"})
    public String getNoticeList(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map param = this.getConditionParam(request);
        int page = CommonUtil.getIntFromString(request.getParameter("page"));
        int pageSize = ConstantUtil.PAGESIZE;
        param.put("pageNum", " limit " + page * pageSize + " ," + pageSize);
        ArrayList noticeList = this.NoticeMapper.getNoticeList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(noticeList);
        model.addAttribute("noticeList", noticeList);
        int totalCount = this.NoticeMapper.getNoticeListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        request.setAttribute("pageSize", Integer.valueOf(pageSize));
        request.setAttribute("totalCount", Integer.valueOf(totalCount));
        request.setAttribute("page", Integer.valueOf(page));
        request.setAttribute("param", param);
        return "views/houtai/notice/notice_list.jsp";
    }

    @RequestMapping({"/houtai/notice/HoutaiNoticeListCtrl-getNoticeDetail"})
    public String getOrderDetail(HttpServletRequest request, HttpServletResponse response, Model model) {
        String noticeId = CommonUtil.GetRequestParam(request, "noticeId", "0");
        Map noticeInfo = this.getMyOneSingBO("wa_notice", "notice_id", noticeId);
        model.addAttribute("noticeInfo", noticeInfo);
        return "views/houtai/notice/notice_detail.jsp";
    }

    @RequestMapping({"/houtai/notice/HoutaiNoticeListCtrl-updateNotice"})
    public void updateNotice(HttpServletRequest request, HttpServletResponse response, Model model) {
        String noticeId = CommonUtil.GetRequestParam(request, "noticeId", "0");
        String noticeTitle = CommonUtil.GetRequestParam(request, "noticeTitle", "");
        String noticeType = CommonUtil.GetRequestParam(request, "noticeType", "0");
        String state = CommonUtil.GetRequestParam(request, "state", "0");
        String noticeContent = CommonUtil.GetRequestParam(request, "noticeContent", "");
        HashMap param = new HashMap();
        HashMap mapClass = new HashMap();
        JSONObject obj = new JSONObject();
        mapClass.put("notice_id", noticeId);
        mapClass.put("notice_title", noticeTitle);
        mapClass.put("notice_type", noticeType);
        mapClass.put("state", state);
        mapClass.put("notice_content", noticeContent);
        SimpleDateFormat nDate = new SimpleDateFormat("yyyy-MM-dd");
        mapClass.put("create_date", nDate.format(new Date()));
        param.put("mapClass", mapClass);
        param.put("tableName", "wa_notice");

        try {
            if("0".equals(noticeId)) {
                this.commonMapper.insertSingleBO(param);
            } else {
                param.put("keyName", "notice_id");
                param.put("keyValue", noticeId);
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

    @RequestMapping({"/houtai/notice/HoutaiNoticeListCtrl-delNotice"})
    public void delNotice(HttpServletRequest request, HttpServletResponse response, Model model) {
        String noticeId = CommonUtil.GetRequestParam(request, "noticeId", "0");
        HashMap param = new HashMap();
        JSONObject obj = new JSONObject();

        try {
            param.put("keyName", "notice_id");
            param.put("keyValue", noticeId);
            param.put("tableName", "wa_notice");
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
