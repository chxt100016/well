//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.wella.front.customer.ctrl;

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
import org.wella.common.ctrl.UploadController;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.vo.MyInfo;
import org.wella.front.customer.mapper.FrontCustomerBillMapper;
import org.wella.front.mapper.FrontBankOrderMapper;
import org.wella.front.mapper.FrontCreditInfoMapper;
import org.wella.front.mapper.FrontCreditMapper;
import org.wella.front.mapper.FrontDegreeMapper;
import org.wella.front.mapper.FrontTixianMapper;
import org.wella.front.mapper.FrontUserMoneyMapper;

@Controller
public class FinanceController extends BaseController {
    @Autowired
    private UploadController uploadController;
    @Autowired
    public FrontUserMoneyMapper userMoneyMapper0;
    @Autowired
    public FrontTixianMapper tixianMapper0;
    @Autowired
    public FrontBankOrderMapper bankOrderMapper0;
    @Autowired
    public FrontCreditMapper creditMapper0;
    @Autowired
    public FrontCreditInfoMapper creditInfoMapper0;
    @Autowired
    public FrontDegreeMapper degreeMapper0;
    @Autowired
    public FrontCustomerBillMapper billMapper0;

    public FinanceController() {
    }

    @RequestMapping({"front/customer/FinanceController-accountInfo"})
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
        return "views/front/customer/finance/accountInfo.jsp";
    }

    @RequestMapping({"front/customer/FinanceController-czSq"})
    public String czSq(HttpServletRequest request, HttpServletResponse response) {
        return "views/front/customer/finance/czSq.jsp";
    }

    @RequestMapping({"front/customer/FinanceController-addCz"})
    public void addCz(HttpServletRequest request, HttpServletResponse response, Model model) {
        String zfMoney = CommonUtil.GetRequestParam(request, "zfMoney", "0.00");
        String zfType = CommonUtil.GetRequestParam(request, "zfType", "0");
        String zfIp = this.getIpAddr(request);
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        String certificateImg = CommonUtil.GetRequestParam(request, "certificateImg", "");
        if(!certificateImg.equals("") && certificateImg.indexOf("temp") > 0) {
            certificateImg = this.uploadController.moveFile(request, certificateImg, "finance/certificate");
        }

        JSONObject res = new JSONObject();
        String sql = "";
        sql = "CALL czSqProcess(\'" + zfMoney + "\', \'" + zfType + "\', \'" + zfIp + "\', \'" + userId + "\', \'" + certificateImg + "\')";
        HashMap queryParam = new HashMap();
        queryParam.put("strsql", sql);

        try {
            if(!sql.equals("")) {
                this.commonMapper.simpleSelectReturnList(queryParam);
            }

            res.put("state", "1");
            res.put("content", ConstantUtil.MSG_SUCCESS);
        } catch (Exception var14) {
            res.put("state", "-1");
            res.put("content", ConstantUtil.MSG_FAILS);
        }

        this.echo(response, res);
    }

    @RequestMapping({"front/customer/FinanceControlle-txSq"})
    public String txSq(HttpServletRequest request, HttpServletResponse response, Model model) {
        String userMoney = CommonUtil.GetRequestParam(request, "userMoney", "0");
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        model.addAttribute("userMoney", userMoney);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("parentMenuNo", Integer.valueOf(2));
        return "views/front/customer/finance/txSq.jsp";
    }

    @RequestMapping({"front/customer/FinanceControlle-addTx"})
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
        sql = "CALL withdrawProcess(\'" + txName + "\', \'" + txKhh + "\', \'" + account + "\', \'" + txMoney + "\', \'" + txIp + "\', \'" + userId + "\')";
        HashMap queryParam = new HashMap();
        queryParam.put("strsql", sql);

        try {
            if(!sql.equals("")) {
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

    @RequestMapping({"front/customer/FinanceController-txList"})
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
        model.addAttribute("childMenuNo", "6");
        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("txMoney", retInfo.get("txMoney"));
        model.addAttribute("list", list);
        return "views/front/customer/finance/txList.jsp";
    }

    @RequestMapping({"front/customer/FinanceController-czList"})
    public String czList(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        Map param = this.getConditionParam(request);
        param.put("userId", userId);
        ArrayList list = this.bankOrderMapper0.getCzList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.bankOrderMapper0.getCzListCount(param);
        Map retInfo = this.bankOrderMapper0.getCzMoneyInfo(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("list", list);
        model.addAttribute("parentMenuNo", "2");
        model.addAttribute("childMenuNo", "5");
        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("zfMoney", retInfo.get("zfMoney"));
        return "views/front/customer/finance/czSqList.jsp";
    }

    @RequestMapping({"front/customer/FinanceController-returnMoney"})
    public String returnMoney(HttpServletRequest request, HttpServletResponse response, Model model) {
        String creditId = CommonUtil.GetRequestParam(request, "creditId", "0");
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        Map userInfo = this.getUserInfo(userId);
        ConvertUtil.convertDataBaseMapToJavaMap(userInfo);
        HashMap param = new HashMap();
        param.put("creditId", creditId);
        Map info = this.creditMapper0.getCreditInfo(param);
        ConvertUtil.convertDataBaseMapToJavaMap(info);
        model.addAttribute("info", info);
        model.addAttribute("userInfo", userInfo);
        return "views/front/customer/finance/returnMoney.jsp";
    }

    @RequestMapping({"front/customer/FinanceControlle-clHk"})
    public void clHk(HttpServletRequest request, HttpServletResponse response, Model model) {
        String zhMethod = CommonUtil.GetRequestParam(request, "zhMethod", "1");
        String creditId = CommonUtil.GetRequestParam(request, "creditId", "0");
        String creditIp = this.getIpAddr(request);
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        JSONObject res = new JSONObject();
        String sql = "";
        sql = "CALL hkClProcess(\'" + zhMethod + "\', \'" + creditId + "\',\'0.00\' , \'" + creditIp + "\', \'" + userId + "\')";
        HashMap queryParam = new HashMap();
        queryParam.put("strsql", sql);

        try {
            if(!sql.equals("")) {
                this.commonMapper.simpleSelectReturnList(queryParam);
            }

            res.put("state", "1");
            res.put("content", ConstantUtil.MSG_SUCCESS);
        } catch (Exception var13) {
            res.put("state", "-1");
            res.put("content", ConstantUtil.MSG_FAILS);
        }

        this.echo(response, res);
    }

    @RequestMapping({"front/customer/FinanceController-creditAccount"})
    public String creditAccount(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        Map userInfo = this.getUserInfo(userId);
        Map param = this.getConditionParam(request);
        param.put("userId", userId);
        param.put("geCreditState", "3");
        ArrayList list = this.creditMapper0.getCreditList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.creditMapper0.getCreditListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("parentMenuNo", "2");
        model.addAttribute("childMenuNo", "2");
        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("sxMoney", userInfo.get("userCreditMoney"));
        model.addAttribute("list", list);
        return "views/front/customer/finance/creditAccount.jsp";
    }

    @RequestMapping({"front/customer/FinanceControlle-addSx"})
    public void addSx(HttpServletRequest request, HttpServletResponse response, Model model) {
        String creditMoney = CommonUtil.GetRequestParam(request, "creditMoney", "0.00");
        String creditIp = this.getIpAddr(request);
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        JSONObject res = new JSONObject();
        String sql = "";
        sql = "CALL sxSqProcess(\'" + creditMoney + "\', \'" + creditIp + "\', \'" + userId + "\')";
        HashMap queryParam = new HashMap();
        queryParam.put("strsql", sql);

        try {
            if(!sql.equals("")) {
                this.commonMapper.simpleSelectReturnList(queryParam);
            }

            res.put("state", "1");
            res.put("content", ConstantUtil.MSG_SUCCESS);
        } catch (Exception var12) {
            res.put("state", "-1");
            res.put("content", ConstantUtil.MSG_FAILS);
        }

        this.echo(response, res);
    }

    @RequestMapping({"front/customer/FinanceController-sxSq"})
    public String sxSq(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        Map userInfo = this.getUserInfo(userId);
        String creditDengji = userInfo.get("creditDengji").toString();
        String minMoney = "0.00";
        String maxMoney = "0.00";
        HashMap param = new HashMap();
        param.put("degreeId", creditDengji);
        Map info = this.degreeMapper0.getDegreeInfo(param);
        if(info != null) {
            ConvertUtil.convertDataBaseMapToJavaMap(info);
            minMoney = info.get("moneyFw").toString();
            maxMoney = info.get("moneyFw1").toString();
        } else {
            creditDengji = "0";
        }
        model.addAttribute("minMoney", minMoney);
        model.addAttribute("maxMoney", maxMoney);
        model.addAttribute("creditDengji", creditDengji);
        return "views/front/customer/finance/sxSq.jsp";
    }

    @RequestMapping({"front/customer/FinanceController-sxSqList"})
    public String sxSqList(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        Map param = this.getConditionParam(request);
        param.put("leCreditState", "3");
        param.put("userId", userId);
        ArrayList list = this.creditMapper0.getCreditList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.creditMapper0.getCreditListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("parentMenuNo", "2");
        model.addAttribute("childMenuNo", "3");
        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("list", list);
        return "views/front/customer/finance/sxSqList.jsp";
    }

    @RequestMapping({"front/customer/FinanceController-hkList"})
    public String hkList(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        Map param = this.getConditionParam(request);
        param.put("geCreditState", "4");
        param.put("userId", userId);
        ArrayList list = this.creditInfoMapper0.getCreditInfoList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.creditInfoMapper0.getCreditInfoListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("parentMenuNo", "2");
        model.addAttribute("childMenuNo", "4");
        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("list", list);
        return "views/front/customer/finance/hkList.jsp";
    }

    @RequestMapping({"front/customer/FinanceController-fapiaoList"})
    public String fapiaoList(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        Map param = this.getConditionParam(request);
        param.put("userId", userId);
        ArrayList fapiaoList = this.billMapper0.getFapiaoList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(fapiaoList);
        model.addAttribute("fapiaoList", fapiaoList);
        int totalCount = this.billMapper0.getFapiaoListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("parentMenuNo", "2");
        model.addAttribute("childMenuNo", "6");
        model.addAttribute("userName", myInfo.getUserName());
        return "views/front/customer/finance/fapiaoList.jsp";
    }

    @RequestMapping({"front/customer/FinanceController-setFapiao"})
    public void setFapiao(HttpServletRequest request, HttpServletResponse response, Model model) {
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        String billId = CommonUtil.GetRequestParam(request, "billId", "0");

        try {
            if(CommonUtil.getIntFromString(billId) > 0) {
                MyInfo e = this.getMyInfo(request);
                String userId = e.getUserId();
                String ipAddr = this.getIpAddr(request);
                Map billObj = this.getMyOneSingBO("wa_bill", "bill_id", billId);
                if(billObj != null && billObj.get("confirmUserId") != null && billObj.get("confirmUserId").toString().equals(e.getUserId()) && billObj.get("billState") != null && billObj.get("billState").toString().equals("0")) {
                    String sql = "";
                    sql = " CALL  orderCompleteProcess(\'" + billId + "\',\'" + userId + "\',\'" + ipAddr + "\')";
                    HashMap queryParam = new HashMap();
                    queryParam.put("strsql", sql);
                    this.commonMapper.simpleUpdate(queryParam);
                    ret = "1";
                    obj.put("content", ConstantUtil.MSG_SUCCESS);
                }
            }
        } catch (Exception var13) {
            ret = "-2";
            obj.put("content", ConstantUtil.MSG_FAILS);
        }

        obj.put("status", ret);
        this.echoJSON(response, obj);
    }
}