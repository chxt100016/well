package org.wella.controller;

import io.wellassist.utils.*;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.vo.MyInfo;
import org.wella.dao.*;
import org.wella.entity.Bankcard;
import org.wella.entity.User;
import org.wella.entity.Userinfo;
import org.wella.service.CustomerService;
import org.wella.service.FinanceService;
import org.wella.service.SenderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by ailing on 2017/5/12.
 */
@Controller
@RequestMapping(value = "/sender/")
public class SendController extends BaseController{


    @Autowired
    private FinanceService financeServiceImpl;


    @Autowired
    private BankcardDao bankcardDao;


    @Autowired
    private WaUserDao userDao;

    @Autowired
    private BankOrderDao bankOrderDao;

    @Autowired
    private SenderService senderServiceImpl;


    @Autowired
    private CustomerService customerServiceImpl;

    @Autowired
    private UserMoneyDao userMoneyDao;

    @Autowired
    private TradeDAO tradeDao;

    @Autowired
    private VehicleGrabDao vehicleGrabDao;

    @Autowired
    private UserinfoDao userinfoDao;


    @Autowired
    private WithdrawDAO withdrawDAO;

    /**
     * 跳转抢单页面
     * @param request request
     * @param response response
     * @param model model
     * @return view
     */
    @RequestMapping({"grabLogistics"})
    public String qdPage(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        long userId=user.getUserId();
        String logisticsId = CommonUtil.GetRequestParam(request, "logisticsId", "0");
        Map<String,Object> info=senderServiceImpl.grabLogisticsPageInfo(Long.parseLong(logisticsId));
        model.addAttribute("info", info);
        model.addAttribute("senderUserId", userId);
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "3");
        return "views/front/sender/order/quote.jsp";
    }

    /**
     * 跳转订单详情页面
     * @param orderId orderId
     * @param request request
     * @param model model
     * @return view
     */
    @RequestMapping("orderDetail")
    public String orderDetail(@RequestParam("orderId")String orderId,HttpServletRequest request, Model model){
        User user=(User)request.getSession().getAttribute("user");
        Map<String,Object> orderDetail=customerServiceImpl.getOrderDetailInfo(Long.parseLong(orderId));
        model.addAttribute("info",orderDetail);
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "1");
        return "views/front/sender/order/orderDetail_new.jsp";
    }

    /**
     * 抢单大厅
     * @param request request
     * @param model model
     * @return view
     */
    @RequestMapping("vehicleGrabHall")
    public String vehicleGrabHall(HttpServletRequest request,Model model){
        Map param = this.getConditionParam(request);
        HttpSession session=request.getSession();
        User u=(User)session.getAttribute("user");
        param.put("senderUserId",u.getUserId());
        List<Map<String,Object>> logisticsInfos=senderServiceImpl.grabHallInfos(param);
        int totalCount=senderServiceImpl.grabHallInfosCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("info",logisticsInfos);
        model.addAttribute("userName", u.getUserName());
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "3");
        return "views/front/sender/order/vehicleGrabHall.jsp";
    }

    /**
     * 物流抢单处理
     * @param param 物流抢单表单提交参数
     * @return code:0成功/500异常 msg:异常信息
     */
    @RequestMapping("grabLogisticsSubmit")
    @ResponseBody
    public R grabLogisticsSubmit(@RequestParam Map param){
        try {
            int res=senderServiceImpl.grabLogistics(param);
            if(res>0){
                return R.ok();
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
        return R.error();
    }


    /**
     * 抢单成功
     * @param request request
     * @param response response
     * @param model model
     * @return view
     */
    @RequestMapping({"/logisticsGrabResult"})
    public String sqResult(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "views/front/sender/order/qdResult.jsp";
    }

    /**
     * 查看抢单记录
     * @param request 传入参数：orderNo,grabState,page
     * @param response response
     * @param model model
     * @return view
     */
    @RequestMapping({"/logisticsGrabList"})
    public String qdList(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        Map param = this.getConditionParam(request);
        param.put("wlUserId", user.getUserId());
        List list=senderServiceImpl.grabLogisticsList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        /*ArrayList list0 = ConvertUtil.groupList(list, "userId");*/
        int totalCount =senderServiceImpl.grabLogisticsListCount(param);
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "2");
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("list", list);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        return "views/front/sender/order/qdList.jsp";
    }

    /**
     * 跳转物流订单页面
     * @param request request
     * @param model model
     * @return view
     */
    @RequestMapping("logisticsOrderList")
    public String logisticsOrderListPage(HttpServletRequest request,Model model){
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        Map param = this.getConditionParam(request);
        param.put("senderUserId", user.getUserId());
        List<Map<String,Object>> info=senderServiceImpl.logisticsOrderListInfo(param);
        int totalCount=senderServiceImpl.logisticsOrderListInfoCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("info",info);
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "1");
        return "views/front/sender/order/orderList.jsp";
    }

    /**
     * 物流方取消抢单
     * @param request request
     * @param vehicleGrabId wa_vehicle_grab id
     * @return code:0成功/500异常 msg:异常信息
     */
    @RequestMapping("cancelGrab")
    @ResponseBody
    public R cancelGrab(HttpServletRequest request,@RequestParam("vehicleGrabId")String vehicleGrabId){
        if(senderServiceImpl.calcelGrab(request,Long.parseLong(vehicleGrabId))){
            return R.ok();
        }
        return R.error("您的抢单已经被选择");
    }

    /**
     * 物流方再抢单
     * @param request request
     * @param logisticsId wa_logistics_info pk
     * @return code:0成功/500异常 msg:异常信息
     */
    @RequestMapping("reGrab")
    @ResponseBody
    public R reGrab(@RequestParam("logisticsId")String logisticsId){
        int res=senderServiceImpl.reGrabLogistics(Long.parseLong(logisticsId));
        if(res==0){
            return R.ok();
        }
        if (res==-1){
            return R.error("此订单已被其他物流公司确认");
        }
        if (res==-2){
            return R.error("您已选择过此订单");
        }
        if(res==-5){
            return R.error("其他错误");
        }
        return R.error("未知错误请联系管理员");
    }


    /**
     * 物流方账户信息
     * @param request request
     * @param model model
     * @return view
     */
    @RequestMapping("accountInfo")
    public String accountInfo(HttpServletRequest request, Model model) {
        User user = (User) HttpContextUtils.getAttribute("user");
        Map userInfo = this.getUserInfo(user.getUserId().toString());
        Map param = this.getConditionParam(request);
        param.put("userId", user.getUserId());
        param.put("jyState", "2");
        List list = this.userMoneyDao.getJyList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.userMoneyDao.getJyListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("parentMenuNo", "2");
        model.addAttribute("childMenuNo", "1");
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("userMoney", userInfo.get("userMoney"));
        model.addAttribute("list", list);
        return "views/front/sender/finance/accountInfo.jsp";

    }


    /**
     * 提现记录
     * @param request request
     * @param model model
     * @return view
     */
    @RequestMapping("withdrawRecordList")
    public String withdrawRecordList(HttpServletRequest request, Model model) {
        User user = (User) HttpContextUtils.getAttribute("user");
        Map<String, Object> param = this.getConditionParam(request);
        param.put("geTxState", "0");
        param.put("ltTxState", "3");
        param.put("userId", user.getUserId());
        List list = tradeDao.getWithdrawRecordList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = tradeDao.getWithdrawRecordCount(param);
        Map retInfo = tradeDao.getWithdrawMoneyTotal(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("parentMenuNo", "2");
        model.addAttribute("childMenuNo", "6");
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("withdrawMoney", retInfo.get("withdrawMoney"));
        model.addAttribute("list", list);
        return "views/front/sender/finance/txList.jsp";
    }


    /**
     * 充值记录
     * @param request request
     * @param model model
     * @return view
     */
    @RequestMapping({"rechargeRecord"})
    public String rechargeRecord(HttpServletRequest request, Model model) {
        User user = (User) HttpContextUtils.getAttribute("user");
        String userId = user.getUserId().toString();
        Map param = this.getConditionParam(request);
        param.put("userId", userId);
        List list = this.bankOrderDao.getCzList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.bankOrderDao.getCzListCount(param);
        Map retInfo = this.bankOrderDao.getCzMoneyInfo(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("list", list);
        model.addAttribute("parentMenuNo", "2");
        model.addAttribute("childMenuNo", "5");
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("zfMoney", retInfo.get("zfMoney"));
        return "views/front/sender/finance/czSqList.jsp";
    }


    /**
     * 提现申请
     * @param params 分页参数
     * @return code:0成功/500异常 msg:异常信息
     */
    @RequestMapping("withdrawProcess")
    @ResponseBody
    public R withdrawProcess(@RequestParam Map<String, Object> params) {
        User user = (User) HttpContextUtils.getAttribute("user");
        params.put("userId", user.getUserId());
        params.put("withdrawIp", IPUtils.getIpAddr(HttpContextUtils.getHttpServletRequest()));
        try {
            int result = withdrawDAO.withdrawApply(params);
            return R.ok().put("state", 1).put("content", "请求已经受理");
        } catch (Exception e) {
            e.printStackTrace();
            return R.ok().put("state", -1).put("content", "系统错误");
        }
    }


    /**
     * 提现申请
     * @param request request
     * @param response response
     * @param model model
     * @return view
     */
    /*@RequestMapping({"/FinanceCtrl-txSq"})
    public String txSq(HttpServletRequest request, HttpServletResponse response, Model model) {
        String userMoney = CommonUtil.GetRequestParam(request, "userMoney", "0");
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        model.addAttribute("userMoney", userMoney);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("parentMenuNo", Integer.valueOf(2));
        return "views/front/sender/finance/txSq.jsp";
    }*/


    /**
     * 跳转充值申请
     * @param request request
     * @param response response
     * @return view
     */
    /*@RequestMapping({"/FinanceController-czSq"})
    public String czSq(HttpServletRequest request, HttpServletResponse response) {
        return "views/front/sender/finance/czSq.jsp";
    }*/




   /* @RequestMapping({"/FinanceCtrl-addTx"})
    public void addTx(HttpServletRequest request, HttpServletResponse response, Model model) {
        String txName = CommonUtil.GetRequestParam(request, "txName", "");
        String txKhh = CommonUtil.GetRequestParam(request, "txKhh", "");
        String account = CommonUtil.GetRequestParam(request, "account", "");
        String txMoney = CommonUtil.GetRequestParam(request, "txMoney", "0.00");
        String txIp = this.getIpAddr(request);
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        JSONObject res = new JSONObject();
        String sql = "";
        sql = "CALL txSqProcess(\'" + txName + "\', \'" + txKhh + "\', \'" + account + "\', \'" + txMoney + "\', \'" + txIp + "\', \'" + userId + "\')";
        HashMap queryParam = new HashMap();
        queryParam.put("strsql", sql);

        try {
            if(!sql.equals("")) {
                this.commonMapper.simpleSelectReturnList(queryParam);
            }

            res.put("state", "1");
            res.put("content", ConstantUtil.MSG_SUCCESS);
        } catch (Exception var15) {
            res.put("state", "-1");
            res.put("content", ConstantUtil.MSG_FAILS);
        }

        this.echo(response, res);
    }*/


    /**
     * 校验支付密码
     * @param oldPass 旧密码
     * @param response response
     */
    @RequestMapping("checkCzPassword")
    public void checkWithdrawPassword(@RequestParam("pass") String oldPass, HttpServletResponse response) {
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) httpSession.getAttribute("user");
        //MD5加密
        String password = CommonUtil.MD5(oldPass);
        Boolean res = Boolean.valueOf(false);
        if (password.equals(user.getCzPass())) {
            res = Boolean.valueOf(true);
        } else {
            res = Boolean.valueOf(false);
        }
        this.echo(response, res.toString());
    }



   /* @RequestMapping({"/FinanceCtrl-txList"})
    public String txList(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        Map param = this.getConditionParam(request);
        param.put("geTxState", "0");
        param.put("ltTxState", "3");
        param.put("userId", userId);
        ArrayList list = this.tixianMapper0.getTxList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.tixianMapper0.getTxListCount(param);
        Map retInfo = this.tixianMapper0.getTixianMoneyInfo(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("parentMenuNo", "2");
        model.addAttribute("childMenuNo", "5");
        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("txMoney", retInfo.get("txMoney"));
        model.addAttribute("list", list);
        return "views/front/sender/finance/txList.jsp";
    }*/

    /**
     * 充值申请
     * @param params 充值申请表单提交参数
     * @return  code:0成功/500异常 msg:异常信息
     */
    @RequestMapping("rechargeApply")
    @ResponseBody
    public R rechargeApply(@RequestParam Map<String, Object> params) {
        int res = financeServiceImpl.recharge(params);
        if (res == 1) {
            return R.ok().put("state", 1).put("content", ConstantUtil.MSG_SUCCESS);
        } else {
            return R.error().put("state", 0).put("content", ConstantUtil.MSG_FAILS);
        }
    }


    /**
     * 公司基本信息
     * @param model model
     * @return view
     */
    @RequestMapping("companyInfo")
    public String companyInfo(Model model){
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user =(User) httpSession.getAttribute("user");
        Userinfo userinfo = (Userinfo) httpSession.getAttribute("userInfo");
        model.addAttribute("user",user);
        model.addAttribute("userInfo",userinfo);
        model.addAttribute("parentMenuNo", "4");
        model.addAttribute("childMenuNo", "1");
        String pParam = userinfo.getZcRegionId().toString().substring(0, 2) + "0000";
        String cParam = userinfo.getZcRegionId().toString().substring(0, 4) + "00";
        //省列表
        model.addAttribute("provinceList", this.getChildRegionList(0));
        model.addAttribute("provinceId", pParam);
        //市列表
        model.addAttribute("cityList", this.getChildRegionList(CommonUtil.getIntFromString(pParam)));
        model.addAttribute("cityId", cParam);
        //区列表
        model.addAttribute("countyList", this.getChildRegionList(CommonUtil.getIntFromString(cParam)));
        return "views/front/sender/company/companyInfo.jsp";
    }


    /**
     * 公司联系人
     * @param model model
     * @return view
     */
    @RequestMapping("contact")
    public String contact(Model model){
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user =(User) httpSession.getAttribute("user");
        Userinfo userinfo = (Userinfo) httpSession.getAttribute("userInfo");
        model.addAttribute("parentMenuNo", "4");
        model.addAttribute("childMenuNo", "2");
        model.addAttribute("user", user);
        model.addAttribute("userInfo", userinfo);
        return "views/front/sender/company/contactMode.jsp";
    }


    /**
     * 更新联系人
     * @param params 表单提交参数
     * @return code:0成功/500异常 msg:异常信息
     */
    @RequestMapping("updateContact")
    @ResponseBody
    public R updateContext(@RequestParam Map<String,Object> params){
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) httpSession.getAttribute("user");
        params.put("userId", user.getUserId());
        try {
            userinfoDao.updateUserinfoByUserId(params);
            return R.ok().put("state",1);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().put("state",0);
        }
    }

    /**
     * 修改密码页面
     * @param model model
     * @return view
     */
    @RequestMapping("password")
    public String changePassword(Model model) {
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("parentMenuNo", "4");
        model.addAttribute("childMenuNo", "3");
        model.addAttribute("userName", user.getUserName());
        return "views/front/sender/company/changePass.jsp";
    }


    /**
     * 检验登录密码
     * @param map 就密码
     * @return code:0成功/500异常 msg:异常信息
     */
    @RequestMapping("checkLoginPassword")
    @ResponseBody
    public R checkLoginPassword(@RequestParam Map<String,Object>map){
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) httpSession.getAttribute("user");
        String payoldpass = map.get("oldPass").toString();
        //MD5加密
        String password = CommonUtil.MD5(payoldpass);
        if(password.equals(user.getUserPass())){
            return R.ok().put("state",1);
        }else{
            return R.error().put("state",0);
        }
    }

    /**
     * 修改登录面
     * @param map 新密码
     * @return code:0成功/500异常 msg:异常信息
     */
    @RequestMapping("changeLoginPassword")
    @ResponseBody
    public R changeLoginPassword(@RequestParam Map<String,Object>map){
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) httpSession.getAttribute("user");
        String newpass = map.get("newpass").toString();
        //MD5加密
        String password = CommonUtil.MD5(newpass);
        user.setUserPass(password);
        Map<String,Object> updateMap = new HashedMap();
        updateMap.put("userId",user.getUserId());
        updateMap.put("userPassword",user.getUserPass());
        try{
            userDao.resetPassword(updateMap);
            return R.ok().put("state",1);
        }
        catch (Exception e){
            e.printStackTrace();
            return R.error().put("state",0);
        }
    }


    /**
     * 校验支付密码
     * @param map 旧密码
     * @return code:0成功/500异常 msg:异常信息
     */
    @RequestMapping("checkPayPassword")
    @ResponseBody
    public R checkPayPassword(@RequestParam Map<String,Object>map){
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) httpSession.getAttribute("user");
        String payoldpass = map.get("oldPass").toString();
        //MD5加密
        String password = CommonUtil.MD5(payoldpass);
        if(password.equals(user.getCzPass())){
            return R.ok().put("state",1);
        }else{
            return R.error().put("state",0);
        }
    }


    /**
     * 更新支付免
     * @param map 新密码
     * @return code:0成功/500异常 msg:异常信息
     */
    @RequestMapping("changePayPassword")
    @ResponseBody
    public R changePayPassword(@RequestParam Map<String,Object>map){
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) httpSession.getAttribute("user");
        String paynewpass = map.get("paynewpass").toString();
        //MD5加密
        String password = CommonUtil.MD5(paynewpass);
        user.setCzPass(password);
        Map<String,Object> updateMap = new HashedMap();
        updateMap.put("userId",user.getUserId());
        updateMap.put("czPassword",user.getCzPass());
        try {
            userDao.resetPassword(updateMap);
            return R.ok().put("state",1);
        }catch (Exception e){
            e.printStackTrace();
            return R.error().put("state",0);
        }
    }


    /**
     * 银行卡管理
     * @param model model
     * @return view
     */
    @RequestMapping("bankcardPage")
    public String bankcardPage(Model model) {
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) httpSession.getAttribute("user");
        Userinfo userinfo = (Userinfo) httpSession.getAttribute("userInfo");
        List<Bankcard> bankcardList = bankcardDao.getCardListByUserId(user.getUserId());
        model.addAttribute("user", user);
        model.addAttribute("userInfo", userinfo);
        model.addAttribute("parentMenuNo", "4");
        model.addAttribute("childMenuNo", "4");
        model.addAttribute("Cards", bankcardList);
        model.addAttribute("userName", user.getUserName());
        return "views/front/sender/company/bankcard.jsp";
    }

    /**
     * 添加银行卡
     * @param map 添加银行卡表单提交参数
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
     * 删除银行卡
     * @param map 删除银行卡表单提交参数
     * @return code:0成功/500异常 msg:异常信息
     */
    @RequestMapping("delBankcard")
    @ResponseBody
    public R delBankcard(@RequestParam Map<String, Object> map) {
        User user = (User) HttpContextUtils.getAttribute("user");
        long userId = user.getUserId();
        try {
            int count = bankcardDao.delCard(Long.parseLong(map.get("bankcardId").toString()));
            return R.ok().put("content", "添加成功").put("count", count);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }


    /**
     * 物流订单详情
     * @param model model
     * @param request request
     * @return view
     */
    @RequestMapping("detail")
    public String logisticsDetail(Model model,HttpServletRequest request) {
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        long userId=user.getUserId();
        String logisticsId = CommonUtil.GetRequestParam(request, "logisticsId", "0");
        Map<String,Object> info=senderServiceImpl.grabLogisticsPageInfo(Long.parseLong(logisticsId));
        model.addAttribute("info", info);
        model.addAttribute("senderUserId", userId);
        model.addAttribute("userName", user.getUserName());
       /* model.addAttribute("parentMenuNo", "5");
        model.addAttribute("childMenuNo", "1");*/
        return "views/front/sender/order/logisticsDetail.jsp";
    }

    /**
     * 物流抢单司机列表
     * @param logisticsId 物流订单id
     * @return code:0成功/500异常 msg:异常信息
     */
    @ResponseBody
    @RequestMapping("selectDriver")
    public  R selectDriver(@RequestParam Long logisticsId){
        List <Map<String,Object>> driverList=senderServiceImpl.selectDriver(logisticsId);
        Map<String,Object> vehicleGrab=vehicleGrabDao.selectLogisticsDateByLogisticsInfoId(logisticsId);
        ConvertUtil.convertDataBaseMapToJavaMap(vehicleGrab);
        System.out.println(driverList);
        R r=new R().ok().put("list",driverList);
        r.putAll(vehicleGrab);
        return r;
    }


    /**
     * 利润报表
     * @param map echarts请求参数
     * @param request request
     * @return code:0成功/500异常 msg:异常信息
     */
    @ResponseBody
    @RequestMapping("profit")
    public R profit(@RequestBody Map<String,Object> map,HttpServletRequest request){
       User user=(User)request.getSession().getAttribute("user");
        map.put("userId",user.getUserId());
        List<BigDecimal> list=senderServiceImpl.profit(map);
        return R.ok().put("data",list);
    }

    /**
     * 报表页面
     * @param model model
     * @return view
     */
    @RequestMapping("reportManagement")
    public String reportManagement(Model model){
        model.addAttribute("parentMenuNo","4");
        model.addAttribute("childMenuNo","5");
        return "views/front/sender/company/reportFrom.jsp";
    }

    /**
     * 跳转发票申请页面
     * @param model model
     * @return view
     */
    @RequestMapping(value = "goBillApply",method = RequestMethod.GET)
    public String goBillApply(Model model){
        model.addAttribute("parentMenuNo",7);
        model.addAttribute("childMenuNo",1);
        return "views/front/sender/bill/goBillApply.jsp";
    }

    /**
     * 跳转发票管理页面
     * @param model model
     * @return view
     */
    @RequestMapping(value = "goBillManage",method = RequestMethod.GET)
    public String goBillManage(Model model){
        model.addAttribute("parentMenuNo",7);
        model.addAttribute("childMenuNo",2);
        return "views/front/sender/bill/goBillManage.jsp";
    }

    /**
     * 物流方收到申请发票列表
     * @param params 分页参数
     * @param request request
     * @return 成功：code：0，page:发票列表；失败：code：500，msg：原因
     */
    @RequestMapping(value = "requestBillsList",method = RequestMethod.GET)
    @ResponseBody
    public R requestBillsList(HttpServletRequest request,@RequestParam Map params){
        User user=(User) request.getSession().getAttribute("user");
        long userId=user.getUserId();
        params.put("supplierId",userId);
        Query query=new Query(params);
        List list=senderServiceImpl.requestBillsList(query);
        int totalCount=senderServiceImpl.requestBillsListCount(query);
        PageUtils pageUtils=new PageUtils(list,totalCount,query.getLimit(),query.getPage());
        return R.ok().put("page",pageUtils);
    }

    /**
     * 跳转发票发送页面
     * @param model model
     * @return view
     */
    @RequestMapping(value = "goBillSend",method = RequestMethod.GET)
    public String goBillSend(Model model){
        model.addAttribute("parentMenuNo",7);
        model.addAttribute("childMenuNo",1);
        return "views/front/sender/bill/goBillSend.jsp";
    }

    /**
     * 物流方发送发票
     * @param billId 发票id
     * @param kpType 开票类型
     * @param eBill 电子发票url
     * @param kdNo 快递单号
     * @param kdName 快递名
     * @return 成功：code：0；失败：code：500，msg：原因
     */
    @RequestMapping(value = "sendBill",method = RequestMethod.POST)
    @ResponseBody
    public R sendBill(@RequestParam("billId")long billId,@RequestParam("billNo")String billNo,@RequestParam("kpType")int kpType,@RequestParam(value = "eBill",required = false,defaultValue = "")String eBill,
                      @RequestParam(value = "kdNo",required = false,defaultValue = "")String kdNo,@RequestParam(value = "kdName",required = false,defaultValue = "")String kdName){
        senderServiceImpl.sendBill(billId,billNo,kpType,eBill,kdNo,kdName);
        return R.ok();
    }

    /**
     * 已处理发票列表数据
     * @param request request
     * @param params 分页参数
     * @return code:0成功/500异常 msg:异常信息
     */
    @RequestMapping(value = "handledBillsList",method = RequestMethod.GET)
    @ResponseBody
    public R billsList(HttpServletRequest request,@RequestParam Map params){
        User user=(User) request.getSession().getAttribute("user");
        long userId=user.getUserId();
        params.put("supplierId",userId);
        Query query=new Query(params);
        List list=senderServiceImpl.handledBillsList(query);
        int totalCount=senderServiceImpl.handledBillsListCount(query);
        PageUtils pageUtils=new PageUtils(list,totalCount,query.getLimit(),query.getPage());
        return R.ok().put("page",pageUtils);
    }

}
