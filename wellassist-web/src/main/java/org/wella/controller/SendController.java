package org.wella.controller;

import com.alibaba.fastjson.JSONObject;
import io.wellassist.utils.HttpContextUtils;
import io.wellassist.utils.IPUtils;
import io.wellassist.utils.R;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.vo.MyInfo;
import org.wella.dao.*;
import org.wella.entity.Bankcard;
import org.wella.entity.LogisticsInfo;
import org.wella.entity.User;
import org.wella.entity.Userinfo;
import org.wella.front.mapper.FrontBankOrderMapper;
import org.wella.front.mapper.FrontTixianMapper;
import org.wella.front.mapper.FrontUserMoneyMapper;
import org.wella.service.CustomerService;
import org.wella.service.FinanceService;
import org.wella.service.SellerService;
import org.wella.service.SenderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by djw on 2017/5/12.
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
    public FrontTixianMapper tixianMapper0;

    @Autowired
    private SenderService senderServiceImpl;

    @Autowired
    private SellerService sellerServiceImpl;

    @Autowired
    private CustomerService customerServiceImpl;


    @Autowired
    private FrontUserMoneyMapper userMoneyMapper0;

    @Autowired
    private TradeDAO tradeDao;


    @Autowired
    private UserinfoDao userinfoDao;


    @Autowired
    private FrontBankOrderMapper bankOrderMapper0;



    @Autowired
    private WithdrawDAO withdrawDAO;


    /**
     * 跳转抢单大厅
     * @param request 可传入参数size，page
     * @param response
     * @param model
     * @return
     */
    /*@RequestMapping("test1")
    public void qdList(HttpServletRequest request, HttpServletResponse response, Model model){
        JSONObject res = new JSONObject();
        Map param = this.getConditionParam(request);
        param.put("state",0);
        List<LogisticsInfo> logisticsInfos=senderServiceImpl.findLogisticsInfos(param);
        model.addAttribute("logisticsInfos",logisticsInfos);
        int totalCount=senderServiceImpl.findLogisticsInfosCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        res.put("logisticsInfos",logisticsInfos);
        echo(response,res);
    }*/

    /**
     * 跳转报价界面
     * @param request 需要传入的参数logisticsId
     * @return
     */
    /*@RequestMapping("test2")
    public void offerLogistics(HttpServletRequest request, HttpServletResponse response, Model model){
        JSONObject res = new JSONObject();
        Map param=new HashMap();
        param.put("logisticsId",request.getParameter("logisticsInfoId"));
        Map<String,Object> logisticsInfo=senderServiceImpl.(param);
        model.addAttribute("logisticsInfo",logisticsInfo);
        res.put("logisticsInfo",logisticsInfo);
        echo(response,res);
    }*/

    /**
     * 处理物流方抢单
     * [{"sjmc":"丁建文1","sjdh":"13145678923","cph":"scdsgv"},{"sjmc":"丁建文2","sjdh":"13245678965","cph":"vfdbg"}]
     * @param request 传入参数logisticsId物流订单id，wlUserId物流用户id，grabMoney报价，sjLxr车队联系人,sjLxPhone联系人电话，orderData车队信息，
     * @param response
     */
    /*@RequestMapping("test3")
    public void doOfferLogistics(HttpServletRequest request, HttpServletResponse response){
        MyInfo myInfo = this.getMyInfo(request);
        JSONObject res = new JSONObject();
        Map map = new HashMap();
        try {
            map.put("logisticsId",request.getParameter("logisticsId"));
            map.put("wlUserId",*//*myInfo.getUserId()*//*request.getParameter("wlUserId"));
            map.put("grabMoney",request.getParameter("grabMoney"));
            map.put("sjLxr",request.getParameter("sjLxr"));
            map.put("sjLxPhone",request.getParameter("sjLxPhone"));
            map.put("orderData",request.getParameter("orderData"));
        }catch (Exception e){
            e.printStackTrace();
            res.put("state", "2");
            res.put("content", ConstantUtil.MSG_FAILS);
            e.printStackTrace();
        }finally {
            try {
                senderServiceImpl.grabLogisticsOrder(map);
                res.put("state", "1");
                res.put("content", ConstantUtil.MSG_SUCCESS);
            }catch (Exception e){
                res.put("state", "2");
                res.put("content", ConstantUtil.MSG_FAILS);
                e.printStackTrace();
            }finally {
                this.echo(response,res);
            }
        }
    }*/
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


    @RequestMapping({"/logisticsGrabResult"})
    public String sqResult(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "views/front/sender/order/qdResult.jsp";
    }
    /**
     * 查看抢单记录
     * @param request 传入参数：orderNo,grabState,page
     * @param response
     * @param model
     * @return
     */
    @RequestMapping({"/logisticsGrabList"})
    public String qdList(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        Map param = this.getConditionParam(request);
        param.put("wlUserId", user.getUserId());
        List list=senderServiceImpl.grabLogisticsList(param);
        ArrayList list0 = ConvertUtil.groupList(list, "userId");
        int totalCount =senderServiceImpl.grabLogisticsListCount(param);
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "2");
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("list", list0);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        return "views/front/sender/order/qdList.jsp";
    }
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
     * @param request
     * @param vehicleGrabId
     * @return
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
     *
     */
    @RequestMapping("reGrab")
    @ResponseBody
    public R reGrab(HttpServletRequest request,@RequestParam("logisticsId")String logisticsId){
        int res=senderServiceImpl.reGrabLogistics(Long.parseLong(logisticsId));
        if(res==0){
            return R.ok();
        }
        if (res==-1){
            return R.error("此订单已被其他物流公司确认");
        }
        if(res==-5){
            return R.error("其他错误");
        }
        return R.error("未知错误请联系管理员");
    }



    @RequestMapping("accountInfo")
    public String accountInfo(HttpServletRequest request, Model model) {
        User user = (User) HttpContextUtils.getAttribute("user");
        Map userInfo = this.getUserInfo(user.getUserId().toString());
        Map param = this.getConditionParam(request);
        param.put("userId", user.getUserId());
        param.put("jyState", "2");
        ArrayList list = this.userMoneyMapper0.getJyList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.userMoneyMapper0.getJyListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("parentMenuNo", "2");
        model.addAttribute("childMenuNo", "1");
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("userMoney", userInfo.get("userMoney"));
        model.addAttribute("list", list);
        return "views/front/sender/finance/accountInfo.jsp";

    }




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




    @RequestMapping({"rechargeRecord"})
    public String rechargeRecord(HttpServletRequest request, Model model) {
        User user = (User) HttpContextUtils.getAttribute("user");
        String userId = user.getUserId().toString();
        Map param = this.getConditionParam(request);
        param.put("userId", userId);
        ArrayList list = this.bankOrderMapper0.getCzList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.bankOrderMapper0.getCzListCount(param);
        Map retInfo = this.bankOrderMapper0.getCzMoneyInfo(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("list", list);
        model.addAttribute("parentMenuNo", "2");
        model.addAttribute("childMenuNo", "5");
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("zfMoney", retInfo.get("zfMoney"));
        return "views/front/sender/finance/czSqList.jsp";
    }





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





    @RequestMapping({"/FinanceCtrl-txSq"})
    public String txSq(HttpServletRequest request, HttpServletResponse response, Model model) {
        String userMoney = CommonUtil.GetRequestParam(request, "userMoney", "0");
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        model.addAttribute("userMoney", userMoney);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("parentMenuNo", Integer.valueOf(2));
        return "views/front/sender/finance/txSq.jsp";
    }



    @RequestMapping({"/FinanceController-czSq"})
    public String czSq(HttpServletRequest request, HttpServletResponse response) {
        return "views/front/sender/finance/czSq.jsp";
    }




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


    @RequestMapping("password")
    public String changePassword(Model model) {
        HttpSession httpSession = HttpContextUtils.getHttpServletRequest().getSession();
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("parentMenuNo", "4");
        model.addAttribute("childMenuNo", "3");
        model.addAttribute("userName", user.getUserName());
        return "views/front/sender/company/changePass.jsp";
    }



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






    @RequestMapping("detail")
    public String txSq(Model model,HttpServletRequest request) {
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        long userId=user.getUserId();
        String logisticsId = CommonUtil.GetRequestParam(request, "logisticsId", "0");
        Map<String,Object> info=senderServiceImpl.grabLogisticsPageInfo(Long.parseLong(logisticsId));
        List <Map<String,Object>> driverList=senderServiceImpl.selectDriver(logisticsId);
        model.addAttribute("info", info);
        model.addAttribute("senderUserId", userId);
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("driverList",driverList);

       /* model.addAttribute("parentMenuNo", "5");
        model.addAttribute("childMenuNo", "1");*/
        return "views/front/sender/order/logisticsDetail.jsp";
    }





}
