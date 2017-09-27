package org.wella.controller;

import io.wellassist.utils.HttpContextUtils;
import io.wellassist.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.wella.dao.BankcardDao;
import org.wella.entity.*;
import org.wella.service.UserinfoService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ailing on 2017/7/25.
 */
@Controller
@RequestMapping(value = {"/userinfo/"})
public class UserinfoController {
    Logger logger = LoggerFactory.getLogger(UserinfoController.class);

    @Autowired
    private UserinfoService userinfoServiceImpl;

    @Autowired
    private BankcardDao bankcardDao;

    /**
     * 更新公司基本信息
     * @param params 表单提交参数
     * @param request request
     * @return code:0成功/500异常 msg:异常信息
     */
    @RequestMapping(value = "updateCompanyInfo")
    @ResponseBody
    public R updateCompanyInfo(@RequestParam Map params, HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        System.out.println("params++++++"+params);
        params.put("userId",user.getUserId());
        try {
            userinfoServiceImpl.updateCompanyInfo(params);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
        return R.ok();
    }

    /**
     * 校验登录密码
     * @param oldPass 旧密码
     * @param request request
     * @return code:0成功/500异常 msg:异常信息
     */
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

    /**
     * 校验支付密码
     * @param oldPass 旧密码
     * @param request request
     * @return code:0成功/500异常 msg:异常信息
     */
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

    /**
     * 更新支付密码
     * @param payNewpass 新密码
     * @param request request
     * @return code:0成功/500异常 msg:异常信息
     */
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

    /**
     * 更新登录密码
     * @param loginNewPass 新密码
     * @param request request
     * @return code:0成功/500异常 msg:异常信息
     */
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
     * @param map 银行卡信息
     * @return code:0成功/500异常 msg:异常信息
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
     * 用户银行卡
     * @return wa_bankcard 列表
     */
    @RequestMapping("getCards")
    @ResponseBody
    public R getCards() {
        User user = (User) HttpContextUtils.getAttribute("user");
        long userId = user.getUserId();
        try {
            List<Bankcard> cards = bankcardDao.getCardListByUserId(userId);
            return R.ok().put("Cards", cards);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 删除银行卡
     * @param map 银行卡id
     * @return code:0成功/500异常 msg:异常信息
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


    /**
     * 添加地址
     * @param map 地址信息
     * @return code:0成功/500异常 msg:异常信息
     */
    @ResponseBody
    @RequestMapping("operationAddress")
    public R addAddress(@RequestBody Map<String,Object> map){
        User user=(User) HttpContextUtils.getAttribute("user");
        long userId=user.getUserId();
        map.put("userId",userId);
        Integer result=userinfoServiceImpl.addAddress(map);
        if(result>0){
            return R.ok();
        }
        return R.error();
    }


    /**
     * 查找地址
     * @return
     */
    @ResponseBody
    @RequestMapping("selectAddress")
    public R selectAddress(){
        User user=(User) HttpContextUtils.getAttribute("user");
        long userId=user.getUserId();
        List<Map<String,Object>> list=userinfoServiceImpl.selectAddress(userId);
        return null;
    }

    /**
     * 修改默认
     * @return
     */
    @ResponseBody
    @RequestMapping("updateDefault")
    public R updateDefault(Long id){
        Integer result=userinfoServiceImpl.updateDefault(id);
        if(result>0){
            return R.ok();
        }
        return R.error();

    }


    /**
     * 物流方和买方操作司机   修改或者增加
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("operationDriver")
    public R operationDriver(@RequestBody Map map){
        User user=(User) HttpContextUtils.getAttribute("user");
        Byte userType=user.getUserType();
        System.out.println("map+++++++++++"+map);
        Long userId=user.getUserId();
        map.put("userId",userId);
        Integer  result=userinfoServiceImpl.operationDriver(userType,map);
        if(result>0){
            return R.ok();
        }
        return  R.error();
    }


    /**
     * 物流方和买方 删除司机
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteDriver")
    public R deleteDriver(Long id){
        User user=(User) HttpContextUtils.getAttribute("user");
        Byte userType=user.getUserType();
        Integer result=userinfoServiceImpl.deleteDriver(userType,id);
        if(result>0){
            return R.ok();
        }
        return R.error();
    }


    @ResponseBody
    @RequestMapping("selectProduct")
    public R selectProduct(Long prodId){
        Prod prod=userinfoServiceImpl.selectProduct(prodId);
        return new R().put("prod",prod);
    }

    @RequestMapping("prodDetail")
    public String prodDetail(){
        return "views/front/seller/order/prodDetail_new.jsp";
    }


}
