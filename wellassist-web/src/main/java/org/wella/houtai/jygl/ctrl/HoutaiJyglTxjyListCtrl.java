package org.wella.houtai.jygl.ctrl;

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
import org.wella.common.vo.MyInfo;
import org.wella.houtai.jygl.mapper.HoutaiJyglTixianMapper;
import org.wella.houtai.jygl.mapper.HoutaiJyglUserMoneyMapper;

@Controller
public class HoutaiJyglTxjyListCtrl extends BaseController {
    @Autowired
    private HoutaiJyglUserMoneyMapper userMoneyMapper;
    @Autowired
    private HoutaiJyglTixianMapper tixianMapper;

    public HoutaiJyglTxjyListCtrl() {
    }

    @RequestMapping({"houtai/jygl/HoutaiJyglTxjyListCtrl-getTxjyList"})
    public String getTxjyList(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map param = this.getConditionParam(request);
        ArrayList list = this.tixianMapper.getTxList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.tixianMapper.getTxListCount(param);
        model.addAttribute("list", list);
        model.addAttribute("start", param.get("start"));
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        return "views/houtai/jygl/txjyList.jsp";
    }

    @RequestMapping({"houtai/jygl/HoutaiJyglTxjyListCtrl-updateTxState"})
    public void updateTxState(HttpServletRequest request, HttpServletResponse response, Model model) {
        JSONObject res = new JSONObject();
        String txId = CommonUtil.GetRequestParam(request, "txId", "0");
        String txState = CommonUtil.GetRequestParam(request, "txState", "0");
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        String ipAddr = this.getIpAddr(request);
        String sql = " CALL txClProcess(\'" + txId + "\',\'" + txState + "\',\'" + ipAddr + "\',\'" + userId + "\')";
        HashMap queryParam = new HashMap();

        try {
            queryParam.put("strsql", sql);
            this.commonMapper.simpleUpdate(queryParam);
            res.put("state", "1");
            res.put("content", ConstantUtil.MSG_SUCCESS);
        } catch (Exception var13) {
            var13.printStackTrace();
            res.put("state", "2");
            res.put("content", ConstantUtil.MSG_FAILS);
        }

        this.echo(response, res);
    }
}