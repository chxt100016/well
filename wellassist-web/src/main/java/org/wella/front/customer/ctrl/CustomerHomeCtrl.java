//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.wella.front.customer.ctrl;

import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.vo.MyInfo;
import org.wella.front.customer.mapper.CustomerLoginMapper;
import org.wella.front.customer.mapper.FrontCustomerProdMapper;
import org.wella.front.customer.mapper.FrontCustomerProdUserMapper;
import org.wella.houtai.user.mapper.HoutaiUserSellerMapper;

@Controller
public class CustomerHomeCtrl extends BaseController {
    @Autowired
    private CustomerLoginMapper loginMapper;
    @Autowired
    private FrontCustomerProdUserMapper prodUserMapper;
    @Autowired
    private HoutaiUserSellerMapper houtaiuserSellerMapper;
    @Autowired
    private FrontCustomerProdMapper prodMapper;

    public CustomerHomeCtrl() {
    }

    @RequestMapping({"/front/customer/CustomerHomeCtrl-home"})
    public String home(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        Map userInfo = this.getUserInfo(userId);
        HashMap param = new HashMap();
        param.put("userId", userId);
        ArrayList spList = this.prodUserMapper.getUserProdList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(spList);
        String sql = "";
        HashMap queryParam = new HashMap();
        sql = "SELECT notice_id,notice_title,DATE_FORMAT(create_date,\'%Y年%m月%d日\') as create_date FROM wa_notice ORDER BY notice_id DESC limit 0,15";
        queryParam.put("strsql", sql);
        ArrayList noticeList = this.commonMapper.simpleSelectReturnList(queryParam);

        try {
            String e = this.getRegionDetailName(myInfo.getzcRegionId().toString());
            model.addAttribute("address", e + myInfo.getzcXxAddress());
        } catch (Exception var13) {
            var13.printStackTrace();
        }

        ConvertUtil.convertDataBaseMapToJavaMap(spList);
        ConvertUtil.convertDataBaseMapToJavaMap(noticeList);
        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("spList", spList);
        model.addAttribute("noticeList", noticeList);
        model.addAttribute("myInfo", myInfo);
        model.addAttribute("userInfo", userInfo);
        return "views/front/customer/home.jsp";
    }

    @RequestMapping({"front/customer/CustomerHomeCtrl-prodDetail"})
    public String prodDetail(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        String prodId = CommonUtil.GetRequestParam(request, "prodId", "0");
        Map prodInfo = this.getMyOneSingBO("wa_prod", "prod_id", prodId);
        prodInfo.put("prodRegionName", "");
        if(prodInfo.get("prodRegionId") != null) {
            try {
                prodInfo.put("prodRegionName", this.getRegionDetailName(prodInfo.get("prodRegionId").toString()));
            } catch (Exception var13) {
                var13.printStackTrace();
            }
        }

        prodInfo.put("companyImg", "");
        prodInfo.put("companyName", "");
        Map supInfo = this.getMyOneSingBO("wa_userinfo", "user_id", prodInfo.get("userId") != null?prodInfo.get("userId").toString():"0");
        if(supInfo != null && supInfo.get("companyName") != null) {
            prodInfo.put("companyName", supInfo.get("companyName").toString());
        }

        if(supInfo != null && supInfo.get("companyImg") != null) {
            prodInfo.put("companyImg", supInfo.get("companyImg").toString());
        }

        model.addAttribute("prodInfo", prodInfo);
        String strsql = "SELECT prod_id, prod_type, prod_name, prod_img FROM wa_prod WHERE user_id = \'" + (prodInfo.get("userId") != null?prodInfo.get("userId").toString():"0") + "\' and prod_id <> \'" + prodId + "\'  ORDER BY sale_num DESC LIMIT 8";
        HashMap param = new HashMap();
        param.put("strsql", strsql);
        ArrayList prodList = this.commonMapper.simpleSelectReturnList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(prodList);
        model.addAttribute("prodList", prodList);
        String userId = myInfo.getUserId();
        Map userInfo = this.getUserInfo(userId);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "2");
        model.addAttribute("userName", myInfo.getUserName());
        return "views/front/customer/order/prodDetail.jsp";
    }

    @RequestMapping({"/front/customer/CustomerHomeCtrl-main"})
    public String main(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        HashMap param = new HashMap();
        param.put("userId", userId);
        ArrayList spList = this.prodUserMapper.getUserProdList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(spList);
        model.addAttribute("spList", spList);
        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("parentMenuNo", "5");
        model.addAttribute("childMenuNo", "0");
        return "views/front/customer/prod/prodList.jsp";
    }

    @RequestMapping({"front/customer/CustomerHomeCtrl-makeOrder"})
    public String makeOrder(HttpServletRequest request, HttpServletResponse response, Model model) {
        String prodId = CommonUtil.GetRequestParam(request, "prodId", "0");
        HashMap param = new HashMap();
        param.put("prodId", prodId);
        Map spInfo = this.prodMapper.getProdInfo(param);
        ConvertUtil.convertDataBaseMapToJavaMap(spInfo);
        String regionId = "";
        String regionName = "";
        if(spInfo != null) {
            regionId = spInfo.get("prodRegionId").toString();

            try {
                regionName = this.getRegionDetailName(regionId);
            } catch (Exception var12) {
                var12.printStackTrace();
            }
        }

        spInfo.put("fromRegionName", regionName);
        HashMap param1 = new HashMap();
        param1.put("parentRegionId", "0");
        ArrayList shengRegionList = this.houtaiuserSellerMapper.getRegionList(param1);
        ConvertUtil.convertDataBaseMapToJavaMap(shengRegionList);
        MyInfo myInfo = this.getMyInfo(request);
        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("shengRegionList", shengRegionList);
        model.addAttribute("spInfo", spInfo);
        model.addAttribute("prodId", prodId);
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "1");
        return "views/front/customer/orderPage_new.jsp";
    }

    @RequestMapping({"front/customer/CustomerHomeCtrl-createOrder"})
    public void createOrder(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        JSONObject res = new JSONObject();
        String prodId = CommonUtil.GetRequestParam(request, "prodId", "0");
        String saleNum = CommonUtil.GetRequestParam(request, "saleNum", "0");
        String saleMoney = CommonUtil.GetRequestParam(request, "saleMoney", "0.00");
        String isSelfCar = CommonUtil.GetRequestParam(request, "isSelfCar", "0");
        String vehicleLxr = CommonUtil.GetRequestParam(request, "vehicleLxr", "");
        String vehicleLxrPhone = CommonUtil.GetRequestParam(request, "vehicleLxrPhone", "");
        String toRegionId = CommonUtil.GetRequestParam(request, "toRegionId", "0");
        String toRegionAddr = CommonUtil.GetRequestParam(request, "toRegionAddr", "0");
        String orderData = CommonUtil.GetRequestParam(request, "orderData", "");
        String cfDate = CommonUtil.GetRequestParam(request, "cfDate", "2016-12-12 00:00:00");
        String ddDate = CommonUtil.GetRequestParam(request, "ddDate", "2016-12-12 00:00:00");
        String userId = myInfo.getUserId();
        String orderIp = this.getIpAddr(request);
        ArrayList zOrderList = null;
        String cphList = "";
        String sjdhList = "";
        String sjmcList = "";
        if(orderData.contains("&quot;")){
           orderData= orderData.replaceAll("&quot;","\"");
        }
        if(isSelfCar.equals("0")) {
            if(orderData.equals("")) {
                res.put("state", "2");
                res.put("content", ConstantUtil.MSG_PARAM_ERR);
                this.echo(response, res);
                return;
            }

            zOrderList = ConvertUtil.converJSONtoArrayListMap(orderData);
            Iterator prodInfo = zOrderList.iterator();

            while(prodInfo.hasNext()) {
                Map sqlstr = (Map)prodInfo.next();
                if(sqlstr.get("cph") != null) {
                    cphList = cphList + ("".equals(cphList)?sqlstr.get("cph"):"," + sqlstr.get("cph"));
                }

                if(sqlstr.get("sjdh") != null) {
                    sjdhList = sjdhList + ("".equals(sjdhList)?sqlstr.get("sjdh"):"," + sqlstr.get("sjdh"));
                }

                if(sqlstr.get("sjmc") != null) {
                    sjmcList = sjmcList + ("".equals(sjmcList)?sqlstr.get("sjmc"):"," + sqlstr.get("sjmc"));
                }
            }
        }

        if(CommonUtil.getIntFromString(prodId) > 0 && CommonUtil.getDoubleFromString(saleNum) > 0.0D && CommonUtil.getDoubleFromString(saleMoney) > 0.0D) {
            Map prodInfo1 = this.getMyOneSingBO("wa_prod", "prod_id", prodId);
            if(prodInfo1 != null && prodInfo1.get("prodMoney") != null) {
                saleMoney = Double.toString(CommonUtil.getDoubleFromString(saleNum) * CommonUtil.getDoubleFromString(prodInfo1.get("prodMoney").toString()));
                String sqlstr1 = "SELECT log_id FROM wa_prod_user WHERE user_id = \'" + myInfo.getUserId() + "\' AND prod_id = \'" + prodId + "\'";
                HashMap queryPm = new HashMap();
                queryPm.put("strsql", sqlstr1);
                ArrayList prodUserList = this.commonMapper.simpleSelectReturnList(queryPm);
                if(prodUserList.size() != 1) {
                    res.put("state", "2");
                    res.put("content", ConstantUtil.MSG_PARAM_ERR);
                    this.echo(response, res);
                } else {
                    String sql = "CALL createOrderProcess(\'" + prodId + "\', \'" + saleNum + "\', \'" + saleMoney + "\', \'" + isSelfCar + "\', \'" + vehicleLxr + "\', \'" + vehicleLxrPhone + "\', \'" + toRegionId + "\', \'" + toRegionAddr + "\', \'" + userId + "\', \'" + orderIp + "\', \'" + cphList + "\',\'" + sjdhList + "\', \'" + sjmcList + "\', \'" + cfDate + "\', \'" + ddDate + "\')";

                    try {
                        HashMap e = new HashMap();
                        e.put("strsql", sql);
                        this.commonMapper.simpleSelectReturnList(e);
                        res.put("state", "1");
                        res.put("content", ConstantUtil.MSG_SUCCESS);
                    } catch (Exception var29) {
                        var29.printStackTrace();
                        res.put("state", "2");
                        res.put("content", ConstantUtil.MSG_FAILS);
                    }

                    this.echo(response, res);
                }
            } else {
                res.put("state", "2");
                res.put("content", ConstantUtil.MSG_PARAM_ERR);
                this.echo(response, res);
            }
        } else {
            res.put("state", "2");
            res.put("content", ConstantUtil.MSG_PARAM_ERR);
            this.echo(response, res);
        }
    }

    @RequestMapping({"front/customer/CustomerHomeCtrl-prod_details"})
    public String prod_details(HttpServletRequest request, HttpServletResponse response, Model model) {
        String prodid = CommonUtil.GetRequestParam(request, "id", "");
        model.addAttribute("id", prodid);
        return "views/front/customer/prod_details.jsp";
    }

    @RequestMapping({"front/customer/CustomerHomeCtrl-orderSuccess"})
    public String orderSuccess(HttpServletRequest request, HttpServletResponse response) {
        return "views/front/customer/ordersheetresult.jsp";
    }
}