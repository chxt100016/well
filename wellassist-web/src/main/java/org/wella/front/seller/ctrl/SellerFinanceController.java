package org.wella.front.seller.ctrl;

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
import org.wella.front.mapper.FrontTixianMapper;
import org.wella.front.mapper.FrontUserMoneyMapper;

@Controller
public class SellerFinanceController extends BaseController {
    @Autowired
    public FrontUserMoneyMapper userMoneyMapper0;
    @Autowired
    public FrontTixianMapper tixianMapper0;

    public SellerFinanceController() {
    }

    @RequestMapping({"front/seller/SellerFinanceController-accountInfo"})
    public String accountInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        Map userInfo = this.getUserInfo(userId);
        Map param = this.getConditionParam(request);
        param.put("userId", userId);
        param.put("jyState", "2");
        ArrayList list = this.userMoneyMapper0.getJyList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.userMoneyMapper0.getJyListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("parentMenuNo", "2");
        model.addAttribute("childMenuNo", "1");
        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("userMoney", userInfo.get("userMoney"));
        model.addAttribute("list", list);
        return "front/seller/finance/accountInfo";
    }

    @RequestMapping({"front/seller/finance/fill_money "})
    public String fill_money(HttpServletRequest request, HttpServletResponse response) {
        return "front/seller/finance/fill_money";
    }

    @RequestMapping({"front/seller/SellerFinanceController-txSq"})
    public String txSq(HttpServletRequest request, HttpServletResponse response, Model model) {
        String userMoney = CommonUtil.GetRequestParam(request, "userMoney", "0");
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        model.addAttribute("userMoney", userMoney);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("parentMenuNo", Integer.valueOf(2));
        return "front/sender/finance/txSq";
    }

    @RequestMapping({"front/seller/SellerFinanceController-checkWorkPass"})
    public void checkWorkPass(HttpServletRequest request, HttpServletResponse response, Model model) {
        String userId = CommonUtil.GetRequestParam(request, "userId", "0");
        String pass = CommonUtil.GetRequestParam(request, "pass", "");
        Boolean res = Boolean.valueOf(false);
        String sql = "";
        sql = "SELECT COUNT(*) FROM wa_user WHERE user_id = \'" + userId + "\' AND cz_pass <> MD5(\'" + pass + "\')";
        if (sql.equals("")) {
            res = Boolean.valueOf(false);
        } else {
            HashMap param = new HashMap();
            param.put("strsql", sql);
            int totalCount = this.commonMapper.simpleSelectReturnInt(param);
            if (totalCount > 0) {
                res = Boolean.valueOf(false);
            } else {
                res = Boolean.valueOf(true);
            }
        }

        this.echo(response, res.toString());
    }

    @RequestMapping({"front/seller/SellerFinanceController-addTx"})
    public void addTx(HttpServletRequest request, HttpServletResponse response, Model model) {
        String txName = CommonUtil.GetRequestParam(request, "txName", "");
        String txKhh = CommonUtil.GetRequestParam(request, "txKhh", "");
        String account = CommonUtil.GetRequestParam(request, "account", "");
        String txMoney = CommonUtil.GetRequestParam(request, "txMoney", "0.00");
        String txIp = this.getIpAddr(request);
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        JSONObject res = new JSONObject();
        String sql = "";
        sql = "CALL txSqProcess(\'" + txName + "\', \'" + txKhh + "\', \'" + account + "\', \'" + txMoney + "\', \'" + txIp + "\', \'" + userId + "\')";
        HashMap queryParam = new HashMap();
        queryParam.put("strsql", sql);

        try {
            if (!sql.equals("")) {
                this.commonMapper.simpleSelectReturnList(queryParam);
            }

            res.put("state", "1");
            res.put("content", ConstantUtil.MSG_SUCCESS);
        } catch (Exception var15) {
            res.put("state", "-1");
            res.put("content", ConstantUtil.MSG_FAILS);
        }

        this.echo(response, res);
    }

    @RequestMapping({"front/seller/SellerFinanceController-txList"})
    public String txList(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        Map param = this.getConditionParam(request);
        param.put("geTxState", "0");
        param.put("ltTxState", "3");
        param.put("userId", userId);
        ArrayList list = this.tixianMapper0.getTxList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.tixianMapper0.getTxListCount(param);
        Map retInfo = this.tixianMapper0.getTixianMoneyInfo(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("parentMenuNo", "2");
        model.addAttribute("childMenuNo", "2");
        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("txMoney", retInfo.get("txMoney"));
        model.addAttribute("list", list);
        return "front/seller/finance/txList";
    }
}