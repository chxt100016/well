package org.wella.platform.controller;

import com.alibaba.fastjson.JSON;
import com.wellapay.cncb.model.ForceTransferBasicInfo;
import com.wellapay.cncb.model.output.AccountTransQuery.AccountTransQueryOutputListEntity;
import io.wellassist.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.utils.DateUtil;
import org.wella.dao.LogisticsTransDao;
import org.wella.dao.OrderTransDao;
import org.wella.dao.TradeDAO;
import org.wella.dao.WithdrawDAO;
import org.wella.entity.AdminSubAccount;
import org.wella.entity.UserSubAccount;
import org.wella.entity.Withdraw;
import org.wella.platform.service.TradeService;
import org.wella.service.AdminSubAccountService;
import org.wella.service.FinanceService;
import org.wella.service.MessageService;


import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by liuwen on 2017/6/5.
 */
@RequestMapping("/platform/trade/")
@Controller
public class TradeController extends BaseController {

    @Autowired
    private TradeDAO tradeDAO;

    @Autowired
    private OrderTransDao orderTransDao;

    @Autowired
    private TradeService tradeServiceImpl;

    @Autowired
    private LogisticsTransDao logisticsTransDao;

    @Autowired
    private MessageService messageServicesk;

    @Autowired
    private WithdrawDAO withdrawDAO;

    @Autowired
    private FinanceService financeServiceImpl;

    @Autowired
    private AdminSubAccountService adminSubAccountServiceImpl;

    @RequestMapping("tradeList")
    @ResponseBody
    public R tradeList(@RequestParam Map<String,Object> params) {
        Query query = new Query(params);
        List list = tradeDAO.getTradeList(query);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = tradeDAO.tradeCount(params);
        PageUtils pageUtils = new PageUtils(list,totalCount,query.getLimit(),query.getPage());
        return R.ok().put("page",pageUtils);
    }

    @RequestMapping("tradeDetail/{moneyId}")
    public String getJyDetailList(@PathVariable("moneyId")String moneyId, Model model) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userId",moneyId);
        List list = tradeDAO.tradeDetailList(map);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        model.addAttribute("list", list);
        return "views/houtai/jygl/jyDetailList.jsp";
    }

    @RequestMapping("offlinePayList")
    @ResponseBody
    public R offlinePayList(@RequestParam Map<String,Object> param){
        param.put("zfMethod",5);
        param.put("orderNo",param.get("queryStr"));
        param.put("orderBy","FIELD(a.trans_state,0,1,-2,-1),tj_date desc");
        Query query=new Query(param);
        List list=orderTransDao.listOrderTransAttachOrderinfoviewByConditions(query);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount=orderTransDao.listOrderTransAttachOrderinfoviewByConditionsCount(query);
        PageUtils pageUtils=new PageUtils(list,totalCount,query.getLimit(),query.getPage());
        return R.ok().put("page",pageUtils);
    }

    @RequestMapping("wlOfflinePayList")
    @ResponseBody
    public R wlOfflinePayList(@RequestParam Map<String,Object> param){
        param.put("zfMethod",5);
        param.put("orderNo",param.get("queryStr"));
        param.put("orderBy","FIELD(a.trans_state,0,1,-2,-1),tj_date desc");
        Query query=new Query(param);
        List list=logisticsTransDao.listLogisticsTransAttachOrderinfoviewByConditions(query);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount=logisticsTransDao.listLogisticsTransAttachOrderinfoviewByConditionsCount(query);
        PageUtils pageUtils=new PageUtils(list,totalCount,query.getLimit(),query.getPage());
        return R.ok().put("page",pageUtils);
    }

    /**
     * 跳转线下付款审核界面
     * @param orderTransId
     * @param model
     * @return
     */
    @RequestMapping("offlinePayCheck")
    public String offlinePayCheck(@RequestParam("orderTransId")String orderTransId,Model model){
        Map<String,Object> info=tradeServiceImpl.findOfflinePayInfo(Long.parseLong(orderTransId));
        model.addAttribute("info",info);
        return "views/platform/trade/offlinePay/offlinePayCheck.html";
    }

    /**
     * 跳转wl线下付款审核界面
     * @param logisticsTransId
     * @param model
     * @return
     */
    @RequestMapping("wlOfflinePayCheck")
    public String wlOfflinePayCheck(@RequestParam("logisticsTransId")String logisticsTransId,Model model){
        Map<String,Object> info=tradeServiceImpl.findWlOfflinePayInfo(Long.parseLong(logisticsTransId));
        model.addAttribute("info",info);
        return "views/platform/trade/wlOfflinePay/wlOfflinePayCheck.html";
    }

    @RequestMapping("offlinePayCheckSubmit")
    public String offlinePayCheckSubmit(@RequestParam Map<String,Object> param){
        int res=tradeServiceImpl.offlinePayCheckSubmit(param);
        return "redirect:./offlinePay.html";
    }

    @RequestMapping("wlOfflinePayCheckSubmit")
    public String wlOfflinePayCheckSubmit(@RequestParam Map<String,Object> param){
        int res=tradeServiceImpl.wlOfflinePayCheckSubmit(param);
        return "redirect:./wlOfflinePay.html";
    }

    @RequestMapping("offlinePayDetail")
    public String offlinePayDetail(@RequestParam("orderTransId")String orderTransId,Model model){
        Map<String,Object> info=tradeServiceImpl.findOfflinePayInfo(Long.parseLong(orderTransId));
        model.addAttribute("info",info);
        return "views/platform/trade/offlinePay/offlinePayDetail.html";
    }

    @RequestMapping("wlOfflinePayDetail")
    public String wlOfflinePayDetail(@RequestParam("logisticsTransId")String logisticsTransId,Model model){
        Map<String,Object> info=tradeServiceImpl.findWlOfflinePayInfo(Long.parseLong(logisticsTransId));
        model.addAttribute("info",info);
        return "views/platform/trade/wlOfflinePay/wlOfflinePayDetail.html";
    }

    @RequestMapping("rechargeList")
    @ResponseBody
    public R rechargeList(@RequestParam Map<String,Object> param, Model model) {
        param.put("jyType", "0");
        Query query = new Query(param);
        List list = tradeDAO.rechargeList(query);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = tradeDAO.rechargeCount(query);
        PageUtils pageUtils = new PageUtils(list,totalCount,query.getLimit(),query.getPage());
        return R.ok().put("page",pageUtils);
    }

    /**
     * 充值确认时的业务处理
     */
    @RequestMapping("rechargeHandle")
    @ResponseBody
    public R rechargeHandle(@RequestParam Map<String,Object> parmas) {
        Long userId =  ShiroUtils.getUserId();
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        parmas.put("orderIp", IPUtils.getIpAddr(request));
        parmas.put("userId",userId);
        long orderId=Long.parseLong(parmas.get("orderId").toString());
        try {
            tradeDAO.rechargeHandle(parmas);
            messageServicesk.handleRechargeHandleMessage(orderId);
            return R.ok().put("state",1).put("content",ConstantUtil.MSG_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().put("state",0).put("content", ConstantUtil.MSG_FAILS);
        }
    }

    @RequestMapping("withdrawList")
    @ResponseBody
    public R withdrawList(@RequestParam Map<String,Object> param) {
        Query query = new Query(param);
        List list = tradeDAO.withdrawList(query);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = tradeDAO.withdrawCount(query);
        PageUtils pageUtils = new PageUtils(list,totalCount,query.getLimit(),query.getPage());
        return R.ok().put("page",pageUtils);
    }

    @RequestMapping(value = "withdrawHandle",method = RequestMethod.POST)
    @ResponseBody
    public R withdrawHandle(@RequestParam("withdrawId")long withdrawId,@RequestParam("withdrawState")int withdrawState )throws Exception {
        long userId=ShiroUtils.getUserId();
        String ip=IPUtils.getIpAddr(HttpContextUtils.getHttpServletRequest());
        if (-1==withdrawState){
            tradeServiceImpl.withdrawRefuse(withdrawId,userId,ip);
            return R.ok();
        }
        if (0==withdrawState){
            tradeServiceImpl.withdrawReCheck(withdrawId,userId,ip);
            return R.ok();
        }
        if (1==withdrawState){
            tradeServiceImpl.withdrawApprove(withdrawId,userId,ip);
            return R.ok();
        }



        //param.put("userId",ShiroUtils.getUserId());
        //param.put("withdrawIp",IPUtils.getIpAddr(HttpContextUtils.getHttpServletRequest()));
        /*if (withdrawState==1){
            int res = tradeDAO.withdrawHandle(param);
            messageServicesk.handleWithdrawHandleMessage(withdrawId,withdrawState);
            if(res==1){
                return R.ok().put("state",1);
            }else {
                return R.error().put("state",0);
            }
        }else if (withdrawState==2){
            Map<String,Object> withdraw=withdrawDAO.singlePoByPrimaryKey(withdrawId);
            long userId=(long)withdraw.get("user_id");
            String account=withdraw.get("account").toString();
            BigDecimal withdrawMoney=(BigDecimal) withdraw.get("withdraw_money");
            UserSubAccount userSubAccount=financeServiceImpl.getUserSubAccountByUserId(userId);
            Map<String,String> paramss=new HashMap<>();
            paramss.put("accountNo",userSubAccount.getSubAccNo());
            paramss.put("recvAccNo",account);
            paramss.put("recvAccNm","潘晓燕");
            paramss.put("tranAmt",withdrawMoney.toString());
            paramss.put("payType","2B");
            String result= CommonUtil.connectCNCBLocalServer(ConstantUtil.CNCB_SERVER_BASEURL+"SamebankNotprePlatformOutGold",paramss);
            R r= JSON.parseObject(result,R.class);
            ForceTransferBasicInfo forceTransferBasicInfo=JSON.parseObject(r.get("forceTransferBasicInfo").toString(),ForceTransferBasicInfo.class);
            if (!forceTransferBasicInfo.getStatus().startsWith("AAAAAA")){
                Map<String,Object> update=new HashMap<>();
                update.put("withdrawId",withdrawId);
                update.put("withdrawState",-2);
                update.put("content",forceTransferBasicInfo.getStatusText());
                withdrawDAO.update(update);
            }
        }
        return R.ok().put("state",1);*/
        return R.ok();
    }
//    @RequestMapping("logisticsList")
//    @ResponseBody
//    public R logisticsList(@RequestParam Map<String,Object> param, Model model) {
//        Query query = new Query(param);
//        List list = tradeDAO.withdrawList(query);
//        ConvertUtil.convertDataBaseMapToJavaMap(list);
//        int totalCount = tradeDAO.withdrawCount(query);
//        PageUtils pageUtils = new PageUtils(list,totalCount,query.getLimit(),query.getPage());
//        return R.ok().put("page",pageUtils);
//    }

    @RequestMapping("orders")
    @ResponseBody
    public R orders(@RequestParam Map<String,Object> param) {
        Query query = new Query(param);
        List list = tradeServiceImpl.orderList(query);
        int totalCount = tradeServiceImpl.orderListCount(query);
        PageUtils pageUtils = new PageUtils(list,totalCount,query.getLimit(),query.getPage());
        return R.ok().put("page",pageUtils);
    }

    @RequestMapping("orderDetail")
    public String orderDetail(@RequestParam("orderId")long orderId,Model model){
        Map<String,Object> orderDetail=tradeServiceImpl.getOrderDetailPageInfo(orderId);
        model.addAttribute("info",orderDetail);
        return "views/platform/trade/order/orderDetail.html";
    }

    @RequestMapping("orderDetailSubmit")
    @ResponseBody
    public R orderDetailSubmit(@RequestParam Map<String,Object> params,HttpServletRequest request){
        if (params.get("zorderId")!=null){
            params.put("zorderIds",request.getParameterValues("zorderId"));
            params.put("zorderStates",request.getParameterValues("zorderState"));
            params.put("zorderPrices",request.getParameterValues("zorderPrice"));
            params.put("zorderNums",request.getParameterValues("zorderNum"));
        }
        if(params.get("orderVehicleId")!=null){
            params.put("orderVehicleIds",request.getParameterValues("orderVehicleId"));
            params.put("vehicleActualSizes",request.getParameterValues("vehicleActualSize"));
        }
        params.put("ip",IPUtils.getIpAddr(request));
        try {
            tradeServiceImpl.updateOrder(params);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
        return R.ok();
    }

    @RequestMapping("logisticsList")
    @ResponseBody
    public R logisticsList(@RequestParam Map<String,Object> param) {
        Query query = new Query(param);
        List list = tradeDAO.logisticsViewList(query);
        int totalCount = tradeDAO.logisticsListCount(query);
        PageUtils pageUtils = new PageUtils(list,totalCount,query.getLimit(),query.getPage());
        return R.ok().put("page",pageUtils);
    }

    /**
     * 订单中转户交易流水页面跳转
     * @return view
     */
    @RequestMapping(value = "orderTransfer.html",method = RequestMethod.GET)
    public String orderTransFlow(){
        return "views/platform/trade/orderTransfer.html";
    }

    /**
     * 还款中转户交易流水页面跳转
     * @return view
     */
    @RequestMapping(value = "loanTransfer.html",method = RequestMethod.GET)
    public String loanTransFlow(){
        return "views/platform/trade/loanTransfer.html";
    }

    /**
     * 中转户设置页面跳转
     * @return view
     */
    @RequestMapping(value = "adminAccountManage.html",method = RequestMethod.GET)
    public String adminAccountManage(){return "views/platform/trade/adminAccountManage.html";}

    /**
     * 账户交易流水查询页面
     * @return view
     */
    @RequestMapping(value = "accountQuery.html",method = RequestMethod.GET)
    public String accountQuery(){return "views/platform/trade/accountQuery.html";}

    /**
     * 查询交易中转户某一天的交易流水
     * @param date 日期 yyyy-MM-dd
     * @return R
     */
    @RequestMapping(value = "orderTransFlowByDay",method = RequestMethod.GET)
    @ResponseBody
    public R orderTransFlowByDay(@RequestParam(value="date",required = false,defaultValue = "")String date){
        List<AccountTransQueryOutputListEntity> list=null;
        if (!"".equals(date)){
            Date queryDate=DateUtil.parse(date,new SimpleDateFormat("yyyy-MM-dd"));
            list=financeServiceImpl.getOrderTransferAccountFlowByDate(queryDate);
        }else{
            list=financeServiceImpl.getOrderTransferAccountFlowByDate(new Date());
        }
        //页面接收到空集合而不是null
        if (null==list){
            list=new ArrayList<>();
        }
        return R.ok().put("list",list);
    }

    /**
     * 查询还款中转户某一天的交易流水
     * @param date 日期 yyyy-MM-dd
     * @return R
     */
    @RequestMapping(value = "loanTransFlowByDay",method = RequestMethod.GET)
    @ResponseBody
    public R loanTransFlowByDay(@RequestParam(value="date",required = false,defaultValue = "")String date){
        List<AccountTransQueryOutputListEntity> list=null;
        if (!"".equals(date)){
            Date queryDate=DateUtil.parse(date,new SimpleDateFormat("yyyy-MM-dd"));
            list=financeServiceImpl.getLoanTransferAccountFlowByDate(queryDate);
        }else{
            list=financeServiceImpl.getLoanTransferAccountFlowByDate(new Date());
        }
        //页面接收到空集合而不是null
        if (null==list){
            list=new ArrayList<>();
        }
        return R.ok().put("list",list);
    }

    @RequestMapping(value = "accountFlowByDay",method = RequestMethod.GET)
    @ResponseBody
    public R accountFlowByDay(@RequestParam(value = "subAccNo")String subAccNo,@RequestParam(value="date",required = false,defaultValue = "")String date){
        int code=financeServiceImpl.validateSubAccount(subAccNo);
        if (code==-1){
            return R.error("无此账户");
        }
        List<AccountTransQueryOutputListEntity> list=null;
        if (!"".equals(date)){
            Date queryDate=DateUtil.parse(date,new SimpleDateFormat("yyyy-MM-dd"));
            list=financeServiceImpl.getSubAccountFlowByDate(subAccNo,queryDate);
        }else{
            list=financeServiceImpl.getSubAccountFlowByDate(subAccNo,new Date());
        }
        //页面接收到空集合而不是null
        if (null==list){
            list=new ArrayList<>();
        }
        return R.ok().put("list",list);
    }

    /**
     * 校验添加的中转账户
     * @param subAccNo 账号
     * @param subAccNm 账户名
     * @return R
     */
    @RequestMapping(value = "validateAdminSubAccount",method = RequestMethod.GET)
    @ResponseBody
    public R validateAccount(@RequestParam("subAccNo")String subAccNo,@RequestParam("subAccNm")String subAccNm){
        int code=adminSubAccountServiceImpl.validateAdminSubAccount(subAccNo,subAccNm);
        if (code==0){
            return R.ok("success!");
        }else if (code==-1){
            return R.error("无此账户.");
        }else if (code==-2){
            return R.error("重复添加.");
        }
        return R.ok();
    }



    /**
     * 添加中转账户
     * @param subAccNo 账号
     * @param subAccNm 账户名
     * @param type 1-付款中转户，2-还款中转户
     * @return
     */
    @RequestMapping(value = "addAdminSubAccount",method = RequestMethod.POST)
    @ResponseBody
    public R addAdminSubAccount(@RequestParam("subAccNo")String subAccNo,@RequestParam("subAccNm")String subAccNm,@RequestParam("type")int type){
        AdminSubAccount adminSubAccount=new AdminSubAccount();
        adminSubAccount.setSubAccNo(subAccNo);
        adminSubAccount.setSubAccNm(subAccNm);
        adminSubAccount.setType((byte)type);
        adminSubAccount.setStatus((byte)2);
        adminSubAccountServiceImpl.create(adminSubAccount);
        return R.ok();
    }

    /**
     * 通过类型查询所有可选的中转账户
     * @param type type 1-付款中转户，2-还款中转户
     * @return
     */
    @RequestMapping(value = "adminSubAccountList",method = RequestMethod.GET)
    @ResponseBody
    public R adminSubAccountList(@RequestParam("type")int type){
        List<AdminSubAccount> list=adminSubAccountServiceImpl.findAdminSubAccounts(type);
        return R.ok().put("list",list);
    }

    /**
     * 调整中转账户
     * @param id 选择的中转户id
     * @param type type type 1-付款中转户，2-还款中转户
     * @return
     */
    @RequestMapping(value = "chooseAdminSubAccount",method = RequestMethod.POST)
    @ResponseBody
    public R chooseAdminSubAccount(@RequestParam("id")long id,@RequestParam("type")int type){
        int code=0;
        if (1==type){
            code=adminSubAccountServiceImpl.updateOrderTransferAccount(id);
        }else if (2==type){
            code=adminSubAccountServiceImpl.updateLoanTransferAccount(id);
        }
        if (code==-1){
            return R.error();
        }
        return R.ok();
    }

    /**
     * 正在使用的交易中转户信息
     * @param type
     * @return
     */
    @RequestMapping(value = "adminSubAccount",method = RequestMethod.GET)
    @ResponseBody
    public R orderTransferAccount(@RequestParam("type")int type){
        AdminSubAccount adminSubAccount=null;
        if (1==type){
            adminSubAccount=adminSubAccountServiceImpl.findOrderTransferAccount();
        } else if (2==type){
            adminSubAccount=adminSubAccountServiceImpl.findLoanTransferAccount();
        }
        return R.ok().put("account",adminSubAccount);
    }

}
