package org.wella.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liuwen on 2017/5/18.
 * 平台页面跳转控制
 */
@Controller
@RequestMapping("/platform/")
public class PageController {

    /**
     * 控制用户管理界面跳转（卖家，买家，物流方，放款方）
     * @param url
     * @return
     */
    @RequestMapping("user/{url}.html")
    public String platformPage(@PathVariable("url") String url){
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
    public String billPage(@PathVariable("userId")String userId,Model model){
        model.addAttribute("userId",userId);
        return "views/platform/user/bill.html";
    }
}
