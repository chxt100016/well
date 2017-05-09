package org.wella.front.sender.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.vo.MyInfo;

@Controller
public class SenderHomeController extends BaseController {
    public SenderHomeController() {
    }

    @RequestMapping({"/front/sender/SenderHomeController-home"})
    public String before_start(HttpServletRequest request, HttpServletResponse response, Model model) {
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
            String recVehicleList = this.getRegionDetailName(myInfo.getzcRegionId().toString());
            model.addAttribute("address", recVehicleList + myInfo.getzcXxAddress());
        } catch (Exception var16) {
            var16.printStackTrace();
        }

        sql = "SELECT * FROM wa_user_vehicle a LEFT JOIN wa_vehicle_grab b  ON a.order_id = b.order_id  INNER JOIN wa_order c ON a.order_id = c.order_id  WHERE (b.order_id IS NULL OR b.wl_user_id <> " + userId + ") AND  a.vehicle_state = 1 GROUP BY a.vehicle_trans";
        queryParam.put("strsql", sql);
        ArrayList recVehicleList1 = this.commonMapper.simpleSelectReturnList(queryParam);
        ConvertUtil.convertDataBaseMapToJavaMap(spList);
        ConvertUtil.convertDataBaseMapToJavaMap(noticeList);
        ConvertUtil.convertDataBaseMapToJavaMap(recVehicleList1);
        Iterator var11 = recVehicleList1.iterator();

        while(var11.hasNext()) {
            Map ele = (Map)var11.next();

            try {
                ele.put("toRegionName", this.getRegionDetailName(ele.get("toRegionId").toString()));
            } catch (Exception var15) {
                ele.put("toRegionName", "");
            }

            try {
                ele.put("fromRegionName", this.getRegionDetailName(ele.get("fromRegionId").toString()));
            } catch (Exception var14) {
                ele.put("fromRegionName", "");
            }
        }

        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("spList", spList);
        model.addAttribute("noticeList", noticeList);
        model.addAttribute("recVehicleList", recVehicleList1);
        model.addAttribute("myInfo", myInfo);
        return "views/front/sender/home.jsp";
    }

    @RequestMapping({"/front/sender/SenderHomeController-main"})
    public String main(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "redirect:views/front/sender/FrontSenderOrderCtrl-orderList.jsp";
    }

    /**
     * 页面未完成
     * @param request
     * @param response
     * @return
     */
    @RequestMapping({"/front/sender/sender/zifu"})
    public String zifu(HttpServletRequest request, HttpServletResponse response) {
        return "views/front/sender/zifu.jsp";
    }
}
