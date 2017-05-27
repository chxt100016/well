package org.wella.platform.controller;

import com.sun.javafx.sg.prism.NGShape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wella.common.ctrl.BaseController;
import org.wella.dao.WaUserDao;
import org.wella.platform.service.impl.MemberServiceImpl;
import org.wella.platform.service.impl.ProductManageServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/18.
 * 平台页面跳转控制
 */
@Controller
@RequestMapping("/platform/")
public class PageController extends BaseController {
    @Autowired
    private WaUserDao waUserDao;

    @Autowired
    private ProductManageServiceImpl productManageService;

    @Autowired
    private MemberServiceImpl memberService;
    /**
     * 控制用户管理界面跳转（卖家，买家，物流方，放款方）
     * @param url
     * @return
     */
    @RequestMapping("user/{url}.html")
    public String userPage(@PathVariable("url") String url){
        return "views/platform/user/" + url + ".html";
    }

    @RequestMapping("user/customerInfo/{userId}")
    public String customerInfoPage(@PathVariable("userId")String userId,Model model){
        model.addAttribute("userId",userId);
        return "views/platform/user/customerInfo.html";
    }

    @RequestMapping("user/accountDetail/{userId}")
    public String accountDetailPage(@PathVariable("userId")String userId,Model model){
        model.addAttribute("userId",userId);
        return "views/platform/user/accountDetail.html";
    }

    @RequestMapping("user/credit/{userId}")
    public String creditPage(@PathVariable("userId")String userId,Model model){
        model.addAttribute("userId",userId);
        return "views/platform/user/credit.html";
    }

    @RequestMapping("user/bill/{userId}")
    public String billPage(@PathVariable("userId")String userId,Model model) {
        model.addAttribute("userId", userId);
        return "views/platform/user/bill.html";
    }

    @RequestMapping("product/publish.html")
    public String productPage(Model model){
        Map map = new HashMap();
        map.put("userType",0);
        List arrayList =  waUserDao.findUser(map);
        model.addAttribute("sellerList", arrayList);
        model.addAttribute("provinceList", this.getChildRegionList(0));
        return "views/platform/product/publish.html" ;
    }
    @RequestMapping("product/list.html")
    public String listPage(Model model){
        return "views/platform/product/list.html" ;
    }
    @RequestMapping("user/customerInfo")
    public String customerInfo(@RequestParam Map<String,Object> requestMap, Model model){
        String id = requestMap.get("id").toString();
        model.addAttribute("testname","wellassist");
        return "views/platform/user/customerInfo.html";
    }
    @RequestMapping("product/edit")
    public String editProduct(@RequestParam Map<String,Object> requestMap, Model model){
        Long prodId = Long.parseLong(requestMap.get("prodId").toString());
        Map prod = productManageService.viewProductInfo(prodId);
        Map map = new HashMap();
        map.put("userType",0);
        List sellerList = waUserDao.findUser(map);
        model.addAttribute("prod",prod);
        model.addAttribute("sellerList",sellerList);
        model.addAttribute("provinceList", this.getChildRegionList(0));
        return "views/platform/product/edit.html";
    }
}
