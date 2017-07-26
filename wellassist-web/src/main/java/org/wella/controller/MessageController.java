package org.wella.controller;

import io.wellassist.utils.HttpContextUtils;
import io.wellassist.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wella.entity.Message;
import org.wella.entity.User;
import org.wella.service.MessageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by xuqinghuo on 2017/7/25.
 */

@Controller
@RequestMapping(value = {"/mes/"})
public class MessageController {

    @Autowired
    private MessageService messageServicesk;

    @RequestMapping("message")
    public String message(@RequestParam Map<String, Object> params, Model model){
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) httpSession.getAttribute("user");
        Query query = new Query(params);
            List<Message> message = messageServicesk.getMessageList(query);

        model.addAttribute("parentMenuNo", "3");
        model.addAttribute("childMenuNo", "1");
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("message",message);
        return "views/front/seller/news/message.jsp";
    }

}
