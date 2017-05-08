//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.wella.front.customer.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.vo.MyInfo;
import org.wella.front.customer.mapper.FrontCustomerProdUserMapper;

@Controller
public class CustomerProdCtrl extends BaseController {
    @Autowired
    private FrontCustomerProdUserMapper prodUserMapper;

    public CustomerProdCtrl() {
    }

    @RequestMapping({"front/customer/CustomerProdCtrl-prodList"})
    public String prodList(HttpServletRequest request, HttpServletResponse response, Model model) {
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
        return "front/customer/prod/prodList";
    }
}