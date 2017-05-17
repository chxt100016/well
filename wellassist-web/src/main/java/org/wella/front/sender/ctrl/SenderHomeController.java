package org.wella.front.sender.ctrl;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.vo.MyInfo;
import org.wella.entity.LogisticsInfo;
import org.wella.front.sender.mapper.FrontSenderOrderMapper;
import org.wella.service.SenderService;

@Controller
public class SenderHomeController extends BaseController {
    public SenderHomeController() {
    }
    @Autowired
    private FrontSenderOrderMapper frontSenderOrderMapper;
    @Autowired
    private SenderService senderServiceImpl;

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
        Map param = this.getConditionParam(request);
        param.put("size",8);
        param.put("state",0);
        param.put("wlUserId",myInfo.getUserId());
        List<LogisticsInfo> logisticsInfos=senderServiceImpl.findLogisticsInfos(param);

        ConvertUtil.convertDataBaseMapToJavaMap(spList);
        ConvertUtil.convertDataBaseMapToJavaMap(noticeList);

        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("spList", spList);
        model.addAttribute("noticeList", noticeList);

        model.addAttribute("logisticsInfos",logisticsInfos);

        model.addAttribute("myInfo", myInfo);
        return "views/front/sender/home.jsp";
    }

    @RequestMapping({"/front/sender/SenderHomeController-main"})
    public String main(HttpServletRequest request, HttpServletResponse response, Model model) {
//        return "redirect:/front/sender/FrontSenderOrderCtrl-orderList";
        MyInfo myInfo = this.getMyInfo(request);
        Map param = this.getConditionParam(request);
        param.put("userId", myInfo.getUserId());
        ArrayList waUserVehicleList = this.frontSenderOrderMapper.getWaUserVehicleList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(waUserVehicleList);
        model.addAttribute("waUserVehicleList", waUserVehicleList);
        int totalCount = this.frontSenderOrderMapper.getWaUserVehicleListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "1");
        model.addAttribute("userName", myInfo.getUserName());
        return "views/front/sender/order/orderList.jsp";
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
