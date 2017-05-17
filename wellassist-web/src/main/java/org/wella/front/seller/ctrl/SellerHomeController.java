package org.wella.front.seller.ctrl;

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
import org.wella.common.vo.MyInfo;
import org.wella.houtai.user.mapper.HoutaiUserSellerMapper;

@Controller
public class SellerHomeController extends BaseController {

    @Autowired
    private HoutaiUserSellerMapper houtaiUserSellerMapper;

    public SellerHomeController() {
    }

    @RequestMapping({"/front/SellerHomeController-home"})
    public String home(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        String sql = "SELECT * FROM wa_prod WHERE user_id = \'" + userId + "\' AND prod_state = 2 ORDER BY prod_id DESC";
        HashMap queryParam = new HashMap();
        queryParam.put("strsql", sql);
        ArrayList spList = this.commonMapper.simpleSelectReturnList(queryParam);
        sql = "SELECT notice_id,notice_title,DATE_FORMAT(create_date,\'%Y年%m月%d日\') as create_date FROM wa_notice ORDER BY notice_id DESC limit 0,15";
        queryParam.put("strsql", sql);
        ArrayList noticeList = this.commonMapper.simpleSelectReturnList(queryParam);

        try {
            String e = this.getRegionDetailName(myInfo.getzcRegionId().toString());
            model.addAttribute("address", e + myInfo.getzcXxAddress());
        } catch (Exception var11) {
            var11.printStackTrace();
        }

        ConvertUtil.convertDataBaseMapToJavaMap(spList);
        ConvertUtil.convertDataBaseMapToJavaMap(noticeList);
        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("spList", spList);
        model.addAttribute("noticeList", noticeList);
        model.addAttribute("myInfo", myInfo);
        return "views/front/seller/home.jsp";
    }

    @RequestMapping({"/front/SellerHomeController-main"})
    public String main(HttpServletRequest request, HttpServletResponse response, Model model) {

        MyInfo myInfo = this.getMyInfo(request);
        if(myInfo == null) {
            return "redirect:/front/SellerLoginController-login";
        } else {
            String isAllEdit = "0";
            String prodId = CommonUtil.GetRequestParam(request, "prodId", "0");
            if(CommonUtil.getIntFromString(prodId) > 0) {
                Map param = this.getMyOneSingBO("wa_prod", "prod_id", prodId);
                if(param != null && param.get("userId") != null && param.get("userId").toString().equals(myInfo.getUserId()) && param.get("prodState") != null && !param.get("prodState").toString().equals("1")) {
                    if(param.get("prodState").toString().equals("-1") || param.get("prodState").toString().equals("2")) {
                        isAllEdit = "1";
                    }

                    model.addAttribute("prodInfo", param);
                    if(param.get("prodRegionId") != null && !param.get("prodRegionId").toString().equals("") && param.get("prodRegionId").toString().length() > 4) {
                        String shengRegionList = param.get("prodRegionId").toString().substring(0, 2) + "0000";
                        String userInfo = param.get("prodRegionId").toString().substring(0, 4) + "00";
                        model.addAttribute("shengRegionId", shengRegionList);
                        model.addAttribute("shiRegionId", userInfo);
                        if(!shengRegionList.equals("000000")) {
                            HashMap strsql = new HashMap();
                            strsql.put("parentRegionId", shengRegionList);
                            ArrayList shiRegionList = this.houtaiUserSellerMapper.getRegionList(strsql);
                            ConvertUtil.convertDataBaseMapToJavaMap(shiRegionList);
                            model.addAttribute("shiRegionList", shiRegionList);
                        }
                    }
                }
            }
            HashMap param1 = new HashMap();
            param1.put("parentRegionId", "0");
            ArrayList shengRegionList1 = this.houtaiUserSellerMapper.getRegionList(param1);
            ConvertUtil.convertDataBaseMapToJavaMap(shengRegionList1);
            model.addAttribute("shengRegionList", shengRegionList1);
            Map userInfo1 = this.getMyOneSingBO("wa_userinfo", "user_id", myInfo.getUserId());
            if(userInfo1 != null && userInfo1.get("companyImg") != null) {
                model.addAttribute("companyImg", userInfo1.get("companyImg").toString());
            }

            String strsql1 = "SELECT COUNT(*) FROM wa_user_money WHERE user_id = \'" + myInfo.getUserId() + "\' AND DATE_ADD(jy_date,INTERVAL 3 MONTH) > NOW()";
            param1.put("strsql", strsql1);
            model.addAttribute("threeJyCn", Integer.valueOf(this.commonMapper.simpleSelectReturnInt(param1)));
            strsql1 = "SELECT COUNT(*) FROM wa_user_money WHERE user_id = \'" + myInfo.getUserId() + "\' AND jy_state = 1";
            param1.put("strsql", strsql1);
            model.addAttribute("ingJyCn", Integer.valueOf(this.commonMapper.simpleSelectReturnInt(param1)));
            model.addAttribute("isAllEdit", isAllEdit);
            model.addAttribute("parentMenuNo", "1");
            model.addAttribute("childMenuNo", "1");
            model.addAttribute("userName", myInfo.getUserName());
            return "views/front/seller/order/prodPub.jsp";
        }
    }
}