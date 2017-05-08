package org.wella.front.fkf.ctrl;

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
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.vo.MyInfo;
import org.wella.front.customer.mapper.CustomerLoginMapper;
import org.wella.front.mapper.FrontUserVehicleMapper;

@Controller
public class FkfHomeCtrl extends BaseController {
    @Autowired
    private CustomerLoginMapper loginMapper;
    @Autowired
    private FrontUserVehicleMapper userVehicleMapper;

    public FkfHomeCtrl() {
    }

    @RequestMapping({"/front/fkf/FkfHomeCtrl-home"})
    public String mainpage(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        ArrayList recVehicleList = this.userVehicleMapper.getRecentQdList();
        ConvertUtil.convertDataBaseMapToJavaMap(recVehicleList);
        Iterator userId = recVehicleList.iterator();

        while(userId.hasNext()) {
            Map sql = (Map)userId.next();

            try {
                sql.put("fromRegionName", this.getRegionDetailName(sql.get("fromRegionId").toString()));
            } catch (Exception var14) {
                sql.put("fromRegionName", "");
            }

            try {
                sql.put("toRegionName", this.getRegionDetailName(sql.get("toRegionId").toString()));
            } catch (Exception var13) {
                sql.put("toRegionName", "");
            }
        }

        model.addAttribute("recVehicleList", recVehicleList);
        model.addAttribute("userName", myInfo.getUserName());
        String userId1 = myInfo.getUserId();
        String sql1 = "SELECT * FROM wa_prod WHERE user_id = \'" + userId1 + "\' AND prod_state = 2 ORDER BY prod_id DESC";
        HashMap queryParam = new HashMap();
        queryParam.put("strsql", sql1);
        ArrayList spList = this.commonMapper.simpleSelectReturnList(queryParam);
        sql1 = "SELECT notice_id,notice_title,DATE_FORMAT(create_date,\'%Y年%m月%d日\') as create_date FROM wa_notice ORDER BY notice_id DESC limit 0,15";
        queryParam.put("strsql", sql1);
        ArrayList noticeList = this.commonMapper.simpleSelectReturnList(queryParam);

        try {
            String e = this.getRegionDetailName(myInfo.getzcRegionId().toString());
            model.addAttribute("address", e + myInfo.getzcXxAddress());
        } catch (Exception var12) {
            var12.printStackTrace();
        }

        ConvertUtil.convertDataBaseMapToJavaMap(spList);
        ConvertUtil.convertDataBaseMapToJavaMap(noticeList);
        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("spList", spList);
        model.addAttribute("noticeList", noticeList);
        model.addAttribute("myInfo", myInfo);
        return "front/fkf/home";
    }

    @RequestMapping({"/front/fkf/FkfHomeCtrl-main"})
    public String home(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "redirect:/front/fkf/FkfOrderCtrl-sxList";
    }

    @RequestMapping({"front/fkf/FkfHomeCtrl-prod_details"})
    public String prod_details(HttpServletRequest request, HttpServletResponse response, Model model) {
        String prodid = CommonUtil.GetRequestParam(request, "id", "");
        model.addAttribute("id", prodid);
        return "front/customer/prod_details";
    }

    @RequestMapping({"front/customer/FkfHomeCtrl-makeOrder"})
    public String makeOrder(HttpServletRequest request, HttpServletResponse response, Model model) {
        String prodid = CommonUtil.GetRequestParam(request, "id", "");
        model.addAttribute("id", prodid);
        return "front/customer/writeordersheet";
    }

    @RequestMapping({"front/fkf/FkfHomeCtrl-orderSuccess"})
    public String orderSuccess(HttpServletRequest request, HttpServletResponse response) {
        return "front/customer/ordersheetresult";
    }
}
