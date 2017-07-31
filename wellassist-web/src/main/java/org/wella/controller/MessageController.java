package org.wella.controller;

import io.wellassist.utils.HttpContextUtils;
import io.wellassist.utils.PageUtils;
import io.wellassist.utils.Query;
import io.wellassist.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wella.entity.CreditRecord;
import org.wella.entity.Message;
import org.wella.entity.User;
import org.wella.entity.Userinfo;
import org.wella.service.MessageService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by xuqinghuo on 2017/7/25.
 */

@Controller
@RequestMapping(value = {"/mes/"})
public class MessageController {
    Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageServicesk;

    /**
     * 消息列表
     * @param params
     * @param model
     * @return
     */
    @RequestMapping("message")
    public String message(@RequestParam Map<String, Object> params, Model model){
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) httpSession.getAttribute("user");
        Query query = new Query(params);
            List<Message> message = messageServicesk.getMessageList(query);
        Message m = new Message();

        model.addAttribute("parentMenuNo", "3");
        model.addAttribute("childMenuNo", "1");
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("message",message);
        return "views/front/seller/news/message.jsp";
    }


    /**
     * 信誉度
     * @param params
     * @param model
     * @return
     */
    @RequestMapping("creditrecord")
    public String getCreditRecord(@RequestParam Map<String, Object> params, Model model){
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) httpSession.getAttribute("user");
        Query query = new Query(params);
        List<CreditRecord> creditrecordList =null;// messageServicesk.getCreditRecordList(query);
        Message m = new Message();

        model.addAttribute("parentMenuNo", "3");
        model.addAttribute("childMenuNo", "1");
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("creditrecordList",creditrecordList);
        return "views/front/seller/news/creditrecord.jsp";
    }


    /**
     * 征信列表
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("creditcalist")
    public R getCreditCulationList(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        //List <CreditRecord>creditrecordList = messageServicesk.getCreditRecordList(query);
        List <Userinfo>creditrecordList = messageServicesk.getCreditRecordList(query);
        int total = messageServicesk.queryRecordCount(query);
        PageUtils pageUtil = new PageUtils(creditrecordList, total, query.getLimit(), query.getPage());
        return R.ok().put("page",pageUtil);
    }
    @ResponseBody
    @RequestMapping("tocreditcal/{userId}")
    public R tocreditcal(@PathVariable("userId") Long userId){
        Userinfo userinfo = messageServicesk.getCreditRecord(userId);
        return R.ok().put("userinfo",userinfo);
    }

    /**
     * 保存征信信息
     */

    @RequestMapping("save")
    public R save(@RequestBody CreditRecord creditRecord){
        messageServicesk.addCreditRecord(creditRecord);
        return R.ok();
    }



}
