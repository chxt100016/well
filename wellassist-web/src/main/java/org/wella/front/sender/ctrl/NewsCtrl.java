package org.wella.front.sender.ctrl;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.vo.MyInfo;
import org.wella.front.mapper.NewsMapper;

@Controller
public class NewsCtrl extends BaseController {
    @Autowired
    private NewsMapper newsMapper;

    public NewsCtrl() {
    }

    @RequestMapping({"front/sender/NewsCtrl-xxList"})
    public String news_list(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        Map param = this.getConditionParam(request);
        param.put("userId", myInfo.getUserId());
        ArrayList newsList = this.newsMapper.getNewsList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(newsList);
        model.addAttribute("newsList", newsList);
        int totalCount = this.newsMapper.getNewsListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        param.put("strsql", "SELECT COUNT(*) FROM wa_info WHERE is_read = 0 AND user_id = " + myInfo.getUserId());
        model.addAttribute("noReadCount", Integer.valueOf(this.commonMapper.simpleSelectReturnInt(param)));
        model.addAttribute("totalCount", Integer.valueOf(totalCount));
        model.addAttribute("parentMenuNo", "3");
        model.addAttribute("childMenuNo", "1");
        model.addAttribute("userName", myInfo.getUserName());
        return "front/sender/news/xxList";
    }

    @RequestMapping(
            value = {"front/sender/NewsCtrl-setIsRead"},
            method = {RequestMethod.POST}
    )
    public void setIsRead(HttpServletRequest request, HttpServletResponse response, Model model) {
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        String ids = CommonUtil.GetRequestParam(request, "ids", "");

        try {
            MyInfo e = this.getMyInfo(request);
            if(!ids.equals("")) {
                String strsql = "UPDATE wa_info SET is_read = 1 WHERE tx_id in (" + ids + ") AND user_id = \'" + e.getUserId() + "\'";
                HashMap param = new HashMap();
                param.put("strsql", strsql);
                this.commonMapper.simpleUpdate(param);
                ret = "1";
                obj.put("content", ConstantUtil.MSG_SUCCESS);
            }
        } catch (Exception var10) {
            ret = "-2";
            obj.put("content", ConstantUtil.MSG_FAILS);
        }

        obj.put("status", ret);
        this.echoJSON(response, obj);
    }

    @RequestMapping(
            value = {"front/sender/NewsCtrl-delXx"},
            method = {RequestMethod.POST}
    )
    public void delXx(HttpServletRequest request, HttpServletResponse response, Model model) {
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        String ids = CommonUtil.GetRequestParam(request, "ids", "");

        try {
            MyInfo e = this.getMyInfo(request);
            if(!ids.equals("")) {
                String strsql = "";
                if(ids.equals("0")) {
                    strsql = "DELETE FROM wa_info WHERE user_id = \'" + e.getUserId() + "\'";
                } else {
                    strsql = "DELETE FROM wa_info WHERE tx_id in (" + ids + ") AND user_id = \'" + e.getUserId() + "\'";
                }

                HashMap param = new HashMap();
                param.put("strsql", strsql);
                this.commonMapper.simpleUpdate(param);
                ret = "1";
                obj.put("content", ConstantUtil.MSG_SUCCESS);
            }
        } catch (Exception var10) {
            ret = "-2";
            obj.put("content", ConstantUtil.MSG_FAILS);
        }

        obj.put("status", ret);
        this.echoJSON(response, obj);
    }
}