package org.wella.platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.wellapay.cncb.model.ForceTransferBasicInfo;
import com.wellapay.cncb.util.CNCBConstants;
import io.wellassist.utils.IPUtils;
import io.wellassist.utils.Query;
import io.wellassist.utils.R;
import io.wellassist.utils.ShiroUtils;
import org.apache.commons.collections.map.HashedMap;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.*;
import org.wella.entity.*;
import org.wella.platform.service.TradeService;
import org.wella.service.FinanceService;
import org.wella.service.MessageService;
import org.wella.service.WaOrderService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/29.
 */
@Service("tradeServiceImpl")
public class TradeServiceImpl implements TradeService{

    @Autowired
    private OrderTransDao orderTransDao;

    @Autowired
    private TradeDAO tradeDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderLogDao orderLogDao;

    @Autowired
    private ZorderDao zorderDao;

    @Autowired
    private OrderVehicleDao orderVehicleDao;

    @Autowired
    private MessageService messageServicesk;


    @Autowired
    private WaOrderService waOrderServiceImpl;

    @Autowired
    private UserMoneyDao userMoneyDao;

    @Autowired
    private LogisticsTransDao logisticsTransDao;

    @Autowired
    private WithdrawDAO withdrawDAO;

    @Autowired
    private UserMoneyInfoDao userMoneyInfoDao;

    @Autowired
    private FinanceService financeServiceImpl;

    @Autowired
    private BankcardDao bankcardDao;

    @Override
    public Map<String, Object> findOfflinePayInfo(long orderTransId) {
        Map<String,Object> res=orderTransDao.singleOrderTransAttachOrderinfoviewByPrimaryKey(orderTransId);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        return res;
    }

    @Override
    public int offlinePayCheckSubmit(Map<String, Object> param) {
        /*return tradeDao.prodOfflinePayCheckProcess(param);*/
        Long orderTransId=Long.parseLong((String) param.get("orderTransId"));
        Long orderId=Long.parseLong((String)param.get("orderId"));
        int passCheck=Integer.parseInt((String)param.get("passCheck"));
        String checkComment=(String)param.get("checkComment");
        Map<String,Object> orderTrans=orderTransDao.singlePoByPrimaryKey(orderTransId);
        long moneyId=(long)orderTrans.get("money_id");
        Map<String,Object> userMoney=userMoneyDao.singlePoByPrimaryKey(moneyId);
        Map<String,Object> update=new HashMap<>();
        if (passCheck==1){
            if ((int)userMoney.get("jy_type")==1){
                update.put("orderId",orderId);
                update.put("prodPayState",5);
                orderDao.updateOrderByID(update);
                update.clear();
            }else if ((int)userMoney.get("jy_type")==3){
                update.put("orderId",orderId);
                update.put("logisticsPayState",5);
                orderDao.updateOrderByID(update);
                update.clear();
            }
            update.put("moneyId",moneyId);
            update.put("jyState",1);
            userMoneyDao.update(update);
            update.clear();
            update.put("orderTransId",orderTransId);
            update.put("transState",1);
            update.put("checkComment",checkComment);
            orderTransDao.update(update);
            waOrderServiceImpl.checkOrderRepayOff(orderId);
        }else if (passCheck==0){
            if ((int)userMoney.get("jy_type")==1){
                update.put("orderId",orderId);
                update.put("prodPayState",-2);
                orderDao.updateOrderByID(update);
                update.clear();
            }else if ((int)userMoney.get("jy_type")==3){
                update.put("orderId",orderId);
                update.put("logisticsPayState",-2);
                orderDao.updateOrderByID(update);
                update.clear();
            }
            update.put("moneyId",moneyId);
            update.put("jyState",-1);
            userMoneyDao.update(update);
            update.clear();
            update.put("orderTransId",orderTransId);
            update.put("transState",-2);
            update.put("checkComment",checkComment);
            orderTransDao.update(update);
        }
        messageServicesk.handleProdOfflinePayCheckMessage(orderId,passCheck,checkComment);
        return 1;
    }

    @Override
    public int wlOfflinePayCheckSubmit(Map<String, Object> param) {
        /*return tradeDao.prodOfflinePayCheckProcess(param);*/
        Long logisticsTransId=Long.parseLong((String) param.get("logisticsTransId"));
        Long orderId=Long.parseLong((String)param.get("orderId"));
        int passCheck=Integer.parseInt((String)param.get("passCheck"));
        String checkComment=(String)param.get("checkComment");
        Map<String,Object> logisticsTrans=logisticsTransDao.singlePoByPrimaryKey(logisticsTransId);
        long moneyId=(long)logisticsTrans.get("money_id");
        Map<String,Object> update=new HashMap<>();
        if (passCheck==1){
            update.put("orderId",orderId);
            update.put("logisticsPayState",5);
            orderDao.updateOrderByID(update);
            update.clear();
            update.put("moneyId",moneyId);
            update.put("jyState",1);
            userMoneyDao.update(update);
            update.clear();
            update.put("logisticsTransId",logisticsTransId);
            update.put("transState",1);
            update.put("checkComment",checkComment);
            logisticsTransDao.update(update);
            waOrderServiceImpl.checkOrderRepayOff(orderId);
        }else if (passCheck==0){
            update.put("orderId",orderId);
            update.put("logisticsPayState",-2);
            orderDao.updateOrderByID(update);
            update.clear();
            update.put("moneyId",moneyId);
            update.put("jyState",-1);
            userMoneyDao.update(update);
            update.clear();
            update.put("logisticsTransId",logisticsTransId);
            update.put("transState",-2);
            update.put("checkComment",checkComment);
            logisticsTransDao.update(update);
        }
        messageServicesk.handleLogisticsOfflinePayCheckMessage(orderId,passCheck,checkComment);
        return 1;
    }



    @Override
    public Map<String, Object> getOrderDetailPageInfo(long orderId) {
        Map<String,Object> res=orderDao.singleOrderinfoByPrimaryKey(orderId);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        int orderState=(int)res.get("orderState");
        int orderStateFirstNumber=0;
        if(orderState>0){
            orderStateFirstNumber=Integer.parseInt(new Integer(orderState).toString().substring(0,1));
        }
        if(orderState==0){
            return res;
        }
        //从wa_order_log表中得到订单价格和数量
        Map<String,Object> orderlog=waOrderServiceImpl.findNewestOrderLog(orderId);
        res.putAll(orderlog);
        if(orderStateFirstNumber<=2){
            return res;
        }
        //从wa_zorder表中得到发货量和成交额
        Map<String,Object> zorderDeliverCount=waOrderServiceImpl.findDeliverProdCount(orderId);
        res.putAll(zorderDeliverCount);

        List<Map<String,Object>> zorders=waOrderServiceImpl.findZorders(orderId);
        res.put("zorders",zorders);

        return res;
    }

    @Override
    @Transactional
    public void updateOrder(Map<String, Object> params) {
        //更新订单单价和数量
        OrderLog ol=new OrderLog();
        long orderId=Long.parseLong((String) params.get("orderId"));
        ol.setOrderId(orderId);
        ol.setOrderPrice(Double.parseDouble((String)params.get("orderPrice")));
        ol.setOrderNumber(Double.parseDouble((String)params.get("orderNumber")));
        ol.setOperationTime(new Date());
        ol.setUserId(ShiroUtils.getUserId());
        ol.setOperationIp((String)params.get("ip"));
        orderLogDao.createOrderLog(ol);

        String [] zorderIds= (String[]) params.get("zorderIds");
        String [] zorderStates=(String [])params.get("zorderStates");
        String [] zorderPrices=(String [])params.get("zorderPrices");
        String [] zorderNums=(String [])params.get("zorderNums");
        String [] orderVehicleIds=(String [])params.get("orderVehicleIds");
        String [] vehicleActualSizes=(String [])params.get("vehicleActualSizes");

        //更新子订单
        if ( null != zorderIds && zorderIds.length > 0 ){
            for (int i=0;i<zorderIds.length;i++){
                int zorderState=Integer.parseInt(zorderStates[i]);
                Map<String,Object> updateMap=new HashMap<String,Object>();
                updateMap.put("zorderId",Long.parseLong(zorderIds[i]));
                updateMap.put("zorderNum",new BigDecimal(zorderNums[i]));
                updateMap.put("zorderPrice",new BigDecimal(zorderPrices[i]));
                updateMap.put("zorderMoney",new BigDecimal(zorderNums[i]).multiply(new BigDecimal(zorderPrices[i])));
                //如果子订单状态未为已收货，存疑，则修改为确认
                if (zorderState==11){
                    updateMap.put("zorderState",2);
                }
                zorderDao.updateByPrimaryKey(updateMap);
            }
        }

        //更新发货车辆信息
        if (null != orderVehicleIds && orderVehicleIds.length > 0){
            for (int i=0;i<orderVehicleIds.length;i++){
                Map<String,Object> updateMap=new HashMap<String,Object>();
                updateMap.put("orderVehicleId",Long.parseLong(orderVehicleIds[i]));
                updateMap.put("vehicleActualSize",new BigDecimal(vehicleActualSizes[i]));
                orderVehicleDao.updateByPrimaryKey(updateMap);
            }
        }

        waOrderServiceImpl.checkZordersConfirmed(orderId);

        Map<String,Object> updateorder=new HashMap<>();
        updateorder.put("orderId",orderId);
        updateorder.put("hasQuestion",0);
        orderDao.updateOrderByID(updateorder);

        messageServicesk.handleAdminUpdateOrderMessage(orderId);

    }

    @Override
    public List orderList(Query query) {
        List<Map<String,Object>> res=tradeDao.orderList(query);
        if (res == null ||res.size()==0){
            return res;
        }
        for (Map<String,Object> order:res){
            if (waOrderServiceImpl.idZordersQuestion((long)order.get("orderId"))){
                order.put("isZordersQuestion",1);
            }else{
                order.put("isZordersQuestion",0);
            }
        }
        return res;
    }

    @Override
    public int orderListCount(Query query) {
        return tradeDao.orderListCount(query);
    }

    @Override
    public Map<String, Object> findWlOfflinePayInfo(long logisticsTransId) {
        Map<String,Object> res=logisticsTransDao.singleLogisticsTransAttachLogisticsOrderInfoViewByPrimaryKey(logisticsTransId);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        return res;
    }

    /**
     * 提现审核不通过：
     UPDATE wa_withdraw（withdraw_state = -1-不通过），UPDATE wa_user_money（jy_state = -1），INSERT wa_user_money_info （state = -1）
     * @param withdrawId  withdrawId
     * @param mgrId 管理员id
     * @param mgrIp 管理员ip
     */
    @Override
    public void withdrawRefuse(long withdrawId, long mgrId, String mgrIp) {
        Date now=new Date();
        Withdraw withdraw=withdrawDAO.querySingleByPk(withdrawId);
        long moneyId=withdraw.getMoneyId();

        Map<String,Object> update=new HashMap<>();
        update.put("withdrawId",withdrawId);
        update.put("withdrawState",-1);
        update.put("mgrUserId",mgrId);
        update.put("mgrIp",mgrIp);
        update.put("mgrDate",now);
        withdrawDAO.update(update);

        update.clear();
        update.put("moneyId",moneyId);
        update.put("jyState",-1);
        update.put("mgrUserId",mgrId);
        update.put("mgrIp",mgrIp);
        update.put("completeDate",now);
        userMoneyDao.update(update);

        UserMoneyInfo userMoneyInfo=new UserMoneyInfo();
        userMoneyInfo.setMoneyId(moneyId);
        userMoneyInfo.setContent("提现拒绝");
        userMoneyInfo.setMgrAdminId(mgrId);
        userMoneyInfo.setMgrDate(now);
        userMoneyInfo.setState((byte)-1);
        userMoneyInfoDao.create(userMoneyInfo);
    }

    /**
     * 提现再审核：
     UPDATE wa_withdraw，UPDATE wa_user_money，INSERT wa_user_money_info （0）
     * @param withdrawId withdrawId
     * @param mgrId 管理员id
     * @param mgrIp 管理员ip
     */
    @Override
    public void withdrawReCheck(long withdrawId, long mgrId, String mgrIp) {
        Date now=new Date();
        Withdraw withdraw=withdrawDAO.querySingleByPk(withdrawId);
        long moneyId=withdraw.getMoneyId();

        Map<String,Object> update=new HashMap<>();
        update.put("withdrawId",withdrawId);
        update.put("withdrawState",0);
        update.put("mgrUserId",mgrId);
        update.put("mgrIp",mgrIp);
        update.put("mgrDate",now);
        withdrawDAO.update(update);

        update.clear();
        update.put("moneyId",moneyId);
        update.put("jyState",0);
        update.put("mgrUserId",mgrId);
        update.put("mgrIp",mgrIp);
        userMoneyDao.update(update);

        UserMoneyInfo userMoneyInfo=new UserMoneyInfo();
        userMoneyInfo.setMoneyId(moneyId);
        userMoneyInfo.setContent("提现提交再审核");
        userMoneyInfo.setMgrAdminId(mgrId);
        userMoneyInfo.setMgrDate(now);
        userMoneyInfo.setState((byte)0);
        userMoneyInfoDao.create(userMoneyInfo);
    }

    /**
     * UPDATE wa_withdraw（mgr_user_id，mgr_ip =，withdraw_state = 1-审核通过(待付款）），UPDATE wa_user_money（jy_state = 1）
     走平台出金接口
     * @param withdrawId
     * @param mgrId
     * @param mgrIp
     */
    @Override
    public void withdrawApprove(long withdrawId, long mgrId, String mgrIp) throws Exception {
        Date now=new Date();
        Withdraw withdraw=withdrawDAO.querySingleByPk(withdrawId);
        long userId=withdraw.getUserId();
        long bankcardId=withdraw.getBankcardId();
        BigDecimal withdrawMoney=withdraw.getWithdrawMoney();
        Map<String,Object> update=new HashMap<>();
        update.put("bankcardId",bankcardId);
        Bankcard bankcard=bankcardDao.querySingle(update);
        long moneyId=withdraw.getMoneyId();

        update.clear();
        update.put("withdrawId",withdrawId);
        update.put("withdrawState",1);
        update.put("mgrUserId",mgrId);
        update.put("mgrIp",mgrIp);
        update.put("mgrDate",now);
        withdrawDAO.update(update);

        update.clear();
        update.put("moneyId",moneyId);
        update.put("jyState",1);
        update.put("mgrUserId",mgrId);
        update.put("mgrIp",mgrIp);
        update.put("completeDate",now);
        userMoneyDao.update(update);

        //accountNo, recvAccNo, recvAccNm, tranAmt, sameBank, payType, recvTgfi, recvBankNm, memo
        Map<String,String> paramss=new HashMap<>();
        UserSubAccount userSubAccount=financeServiceImpl.getUserSubAccountByUserId(userId);
        paramss.put("accountNo",userSubAccount.getSubAccNo());
        paramss.put("recvAccNo",bankcard.getAccount());
        paramss.put("recvAccNm",bankcard.getAccountName());
        paramss.put("tranAmt",withdrawMoney.toString());
        if ("中信银行".equals(bankcard.getBankName())){
            paramss.put("sameBank","0");
            BigDecimal payTypeFlag=new BigDecimal(20000);
            if (withdrawMoney.compareTo(payTypeFlag)<0){
                paramss.put("payType","2B");
            }else {
                paramss.put("payType","2H");
            }
        }else {
            paramss.put("sameBank","1");
            paramss.put("payType","2E");
        }
        paramss.put("recvTgfi",bankcard.getOpenBankTgfi());
        paramss.put("recvBankNm",bankcard.getOpenBankName());
        paramss.put("memo","提现");
        String result= CommonUtil.connectCNCBLocalServer(ConstantUtil.CNCB_SERVER_BASEURL+"notprePlatformOutGold",paramss);
        R r= JSON.parseObject(result,R.class);
        ForceTransferBasicInfo forceTransferBasicInfo=JSON.parseObject(r.get("forceTransferBasicInfo").toString(),ForceTransferBasicInfo.class);
        if (!forceTransferBasicInfo.getStatus().startsWith("AAAAAA")){
            update.put("withdrawId",withdrawId);
            update.put("withdrawState",-2);
            update.put("content",forceTransferBasicInfo.getStatusText());
            withdrawDAO.update(update);
        }else if (forceTransferBasicInfo.getStatus().equals(CNCBConstants.CNCB_STATUS_SUCCESS)){

        }
    }

    /**
     * 平台出金完成后：
     * UPDATE wa_withdraw（withdraw_state = 2-已完成），UPDATE wa_user_money（jy_state = 2-已完成），update user余额，INSERT wa_user_money_info （state = 2, content ）
     * @param withdrawId
     * @param mgrId
     * @param mgrIp
     */
    @Override
    public void withdrawComplete(long withdrawId, long mgrId, String mgrIp) {
        Date now=new Date();
        Withdraw withdraw=withdrawDAO.querySingleByPk(withdrawId);
        long moneyId=withdraw.getMoneyId();

        Map<String,Object> update=new HashMap<>();
        update.put("withdrawId",withdrawId);
        update.put("withdrawState",2);
        update.put("mgrUserId",mgrId);
        update.put("mgrIp",mgrIp);
        update.put("mgrDate",now);
        withdrawDAO.update(update);

        update.clear();
        update.put("moneyId",moneyId);
        update.put("jyState",2);
        update.put("mgrUserId",mgrId);
        update.put("mgrIp",mgrIp);
        update.put("completeDate",now);
        userMoneyDao.update(update);

        UserMoneyInfo userMoneyInfo=new UserMoneyInfo();
        userMoneyInfo.setMoneyId(moneyId);
        userMoneyInfo.setContent("提现完成");
        userMoneyInfo.setMgrAdminId(mgrId);
        userMoneyInfo.setMgrDate(now);
        userMoneyInfo.setState((byte)2);
        userMoneyInfoDao.create(userMoneyInfo);
    }
}
