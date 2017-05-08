package org.wella.front.seller.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.vo.MyInfo;

@Controller
public class SellerHomeController extends BaseController {
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
        return "front/seller/home";
    }

    @RequestMapping({"/front/SellerHomeController-main"})
    public String main(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "redirect:/front/seller/SellerOrderController-prodPub";
    }
}