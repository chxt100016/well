package org.wella.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.service.RegionService;
import sun.misc.Contended;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 */
@Controller
@RequestMapping(value = "/region/")
public class RegionController extends BaseController{
    @Autowired
    private RegionService regionServiceImpl;

    @RequestMapping("getChildRegionListInSite")
    public void getChildRegionListInSite(HttpServletRequest request, HttpServletResponse response, Model model) {
        int regionId = CommonUtil.getIntFromString(CommonUtil.GetRequestParam(request, "regionId", "0"));
        JSONObject res = new JSONObject();

        try {
            HashMap e = new HashMap();
            e.put("regionId", Integer.valueOf(regionId));
            List ret =  regionServiceImpl.getChildRegionList(e);
            ConvertUtil.convertDataBaseMapToJavaMap(ret);
            res.put("state", Integer.valueOf(1));
            res.put("regionList", ret);
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        this.echoJSON(response, res);
    }
}
