package org.wella.platform.controller;

import org.springframework.stereotype.Controller;
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

}
