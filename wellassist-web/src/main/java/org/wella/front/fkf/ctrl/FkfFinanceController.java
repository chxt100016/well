package org.wella.front.fkf.ctrl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.vo.MyInfo;
import org.wella.front.fkf.mapper.FrontFkfCreditMapper;

@Controller
public class FkfFinanceController extends BaseController {
    @Autowired
    public FrontFkfCreditMapper creditMapper;

    public FkfFinanceController() {
    }

    @RequestMapping({"front/fkf/FkfFinanceController-lixiList"})
    public String lixiList(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        Map param = this.getConditionParam(request);
        param.put("creditUserId", myInfo.getUserId());
        param.put("creditState", "3");
        param.put("gtCreditOverDate", new Date());
        ArrayList list = this.creditMapper.getCreditList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.creditMapper.getCreditListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("parentMenuNo", "2");
        model.addAttribute("childMenuNo", "1");
        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("list", list);
        return "front/fkf/finance/lixiList";
    }

    @RequestMapping({"front/fkf/FkfFinanceController-hkList"})
    public String hkList(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        Map param = this.getConditionParam(request);
        param.put("creditUserId", myInfo.getUserId());
        param.put("creditState", "3");
        ArrayList list = this.creditMapper.getCreditList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.creditMapper.getCreditListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("parentMenuNo", "2");
        model.addAttribute("childMenuNo", "2");
        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("list", list);
        return "front/fkf/finance/hkList";
    }
}