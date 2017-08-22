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
        List<CreditRecord> creditrecordList = messageServicesk.getCreditRecordList(query);
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
        List <CreditRecord>creditrecordList = messageServicesk.getCreditRecordList(query);
        int total = messageServicesk.queryRecordCount(query);
        PageUtils pageUtil = new PageUtils(creditrecordList, total, query.getLimit(), query.getPage());

        return R.ok().put("page",pageUtil);
    }

    /*@ResponseBody
    @RequestMapping("tocreditcal/{id}")
    public R tocreditcal(@PathVariable("id") Long id){
        CreditRecord creditrecord = messageServicesk.getCreditRecord(id);
        return R.ok().put("creditrecord",creditrecord);
    }*/


    /**
     *页面转向
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("tocreditcal/{userId}")
    public R tocreditcal(@PathVariable("userId") Long userId){
        Userinfo userinfo = messageServicesk.getCreditRecord(userId);
        return R.ok().put("userinfo",userinfo);
    }

    /**
     * 保存征信计算信息
     */
    @ResponseBody
    @RequestMapping("creditcalsave")
    public R save(@RequestBody CreditRecord creditRecord){
        messageServicesk.addCreditRecord(creditRecord);
        return R.ok();
    }

    @RequestMapping("unreadCount")
    @ResponseBody
    public R unreadCount(){
        User user=(User)HttpContextUtils.getAttribute("user");
        int count;
        try {
            count=messageServicesk.unreadMsgCount(user.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
        return R.ok().put("count",count);
    }

    @RequestMapping("systemicMesList")
    @ResponseBody
    public R systemicMesList(@RequestParam Map param){
        PageUtils page;
        try {
            page=messageServicesk.systemicMesList(param);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
        return R.ok().put("page",page);
    }

    @RequestMapping("financeMesList")
    @ResponseBody
    public R financeMesList(@RequestParam Map param){
        PageUtils page;
        try {
            page=messageServicesk.financeMesList(param);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
        return R.ok().put("page",page);
    }

    @RequestMapping("shitMesList")
    @ResponseBody
    public R shitMesList(@RequestParam Map param){
        PageUtils page;
        try {
            page=messageServicesk.shitMesList(param);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
        return R.ok().put("page",page);
    }

    @RequestMapping("messageDetail")
    @ResponseBody
    public R messageDetail(@RequestParam long id){
        Message message=messageServicesk.singleMessageByPk(id);
        return R.ok().put("message",message);
    }

    @RequestMapping("delete1Msg")
    @ResponseBody
    public R delete1Msg(@RequestParam long id){
        messageServicesk.delete1Msg(id);
        return R.ok();
    }

    @RequestMapping("deleteMsgBatch")
    @ResponseBody
    public R deleteMsgBatch(@RequestParam String ids){
        messageServicesk.deleteMsgBatch(ids);
        return R.ok();
    }

    @RequestMapping("systemicMesPage")
    public String systemicMesList(Model model){
        User user=(User)HttpContextUtils.getAttribute("user");
        int userType=user.getUserType();
        model.addAttribute("parentMenuNo",3);
        model.addAttribute("childMenuNo",1);
        if (userType==2){
            return "views/front/creditor/news/systemicMes.html";
        }else {
            return "views/front/customer/news/systemicMes.jsp";
        }
    }

    @RequestMapping("financeMesPage")
    public String financeMesPage(Model model){
        User user=(User)HttpContextUtils.getAttribute("user");
        int userType=user.getUserType();
        model.addAttribute("parentMenuNo",3);
        model.addAttribute("childMenuNo",2);
        if (userType==2){
            return "views/front/creditor/news/financeMes.html";
        }else {
            return "views/front/customer/news/financeMes.jsp";
        }
    }

    @RequestMapping("shitMes")
    public String shitMes(Model model){
        User user=(User)HttpContextUtils.getAttribute("user");
        int userType=user.getUserType();
        model.addAttribute("parentMenuNo",3);
        model.addAttribute("childMenuNo",3);
        if (userType==2){
            return "views/front/creditor/news/shitMes.html";
        }else {
            return "views/front/customer/news/shitMes.jsp";
        }
    }

    @RequestMapping("messageDetailPage")
    public String messageDetailPage(Model model,@RequestParam long id){
        User user=(User)HttpContextUtils.getAttribute("user");
        int userType=user.getUserType();
        model.addAttribute("parentMenuNo",3);
        if (userType==2){
            model.addAttribute("id",id);
            return "views/front/creditor/news/messageDetail.html";
        }else {
            return "views/front/customer/news/messageDetail.jsp";
        }
    }

}
