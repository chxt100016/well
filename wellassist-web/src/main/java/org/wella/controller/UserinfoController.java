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
import org.wella.dao.BankcardDao;
import org.wella.entity.*;
import org.wella.service.MessageService;
import org.wella.service.UserinfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by xuqinghuo on 2017/7/25.
 */

@Controller
@RequestMapping(value = {"/userinfo/"})
public class UserinfoController {
    Logger logger = LoggerFactory.getLogger(UserinfoController.class);

    @Autowired
    private UserinfoService userinfoServiceImpl;

    @Autowired
    private BankcardDao bankcardDao;

    @RequestMapping(value = "updateCompanyInfo")
    @ResponseBody
    public R updateCompanyInfo(@RequestParam Map params, HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        params.put("userId",user.getUserId());
        try {
            userinfoServiceImpl.updateCompanyInfo(params);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
        return R.ok();
    }

    @RequestMapping("checkOrgPass")
    @ResponseBody
    public R checkOrgPass(@RequestParam("oldPass") String oldPass,HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        long userId=user.getUserId();
        boolean flag= false;
        try {
            flag = userinfoServiceImpl.checkOrgPass(userId,oldPass,0);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
        return R.ok().put("flag",flag);
    }

    @RequestMapping("checkPayOrgPass")
    @ResponseBody
    public R checkPayOrgPass(@RequestParam("oldPass") String oldPass,HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        long userId=user.getUserId();
        boolean flag= false;
        try {
            flag = userinfoServiceImpl.checkOrgPass(userId,oldPass,1);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
        return R.ok().put("flag",flag);
    }

    @RequestMapping("updatePayNewPass")
    @ResponseBody
    public R updatePayNewPass(@RequestParam("payNewpass")String payNewpass,HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        long userId=user.getUserId();
        try {
            userinfoServiceImpl.updatePayNewPass(userId,payNewpass);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
        return R.ok();
    }

    @RequestMapping("updateLoginNewPass")
    @ResponseBody
    public R updateLoginNewPass(@RequestParam("loginNewpass")String loginNewPass,HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        long userId=user.getUserId();
        try {
            userinfoServiceImpl.updateLoginNewPass(userId,loginNewPass);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
        return R.ok();
    }

    /**
     * 添加银行卡的异步请求
     *
     * @return
     */
    @RequestMapping("addBankcard")
    @ResponseBody
    public R addBankcard(@RequestParam Map<String, Object> map) {
        User user = (User) HttpContextUtils.getAttribute("user");
        long userId = user.getUserId();
        map.put("userId", userId);
        map.put("addTime", new Date());
        try {
            long key = bankcardDao.addCard(map);
            return R.ok().put("content", "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }


    /**
     * 添加银行卡的异步请求
     *
     * @return
     */
    @RequestMapping("getCards")
    @ResponseBody
    public R getCards(@RequestParam Map<String, Object> map) {
        User user = (User) HttpContextUtils.getAttribute("user");
        long userId = user.getUserId();
        try {
            List<Bankcard> cards = bankcardDao.getCardListByUserId(userId);
            return R.ok().put("content", "添加成功").put("Cards", cards);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 添加银行卡的异步请求
     *
     * @return
     */
    @RequestMapping("delBankcard")
    @ResponseBody
    public R delBankcard(@RequestParam Map<String, Object> map) {
        User user = (User) HttpContextUtils.getAttribute("user");
        long userId = user.getUserId();
        try {
            int count = bankcardDao.delCard(Long.parseLong(map.get("bankcardId").toString()));
            return R.ok().put("content", "删除成功").put("count", count);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
}
