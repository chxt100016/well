package org.wella.service;

import com.alibaba.fastjson.JSON;
import com.wellapay.cncb.Exception.CNCBException;
import com.wellapay.cncb.model.output.TransQueryOutputListEntity;
import com.wellapay.cncb.util.CNCBConstants;
import io.wellassist.utils.R;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.wella.common.mapper.CommonMapper;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.dao.CncbTransDao;
import org.wella.dao.LoanDao;
import org.wella.dao.OrderDao;
import org.wella.dao.WaUserDao;
import org.wella.entity.CncbTrans;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ailing on 2017/9/4.
 */
@Component
public class WaScheduleJobService {
    private static Log log = LogFactory.getLog(WaScheduleJobService.class);
    @Autowired
    private CncbTransDao cncbTransDao;


    @Autowired
    private CommonMapper commonMapper;

    @Autowired
    private WaOrderService waOrderServiceImpl;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private CreditorService creditorServiceImpl;

    @Autowired
    private LoanDao loanDao;

    @Autowired
    private CustomerService customerServiceImpl;

    @Autowired
    private WaUserDao waUserDao;

    @Autowired
    private FinanceService financeServiceImpl;


    /**
     * 中信银行交易请求提交后轮询交易处理结果
     */
    @Scheduled(fixedRate = 60000)
    public void checkCncbTrans(){
        Map<String,Object> query=new HashMap();
        query.put("state",0);
        List<CncbTrans> list=cncbTransDao.queryList(query);
        if( null != list && list.size() >0 ){
            for (CncbTrans cncbTrans:list){
                String clientID=cncbTrans.getClientId();
                try {
                    Thread.sleep(1000);
                    Map<String,String> params=new HashMap<>();
                    params.put("clientId",clientID);
                    String result= CommonUtil.connectCNCBLocalServer(ConstantUtil.CNCB_SERVER_BASEURL+"transQuery",params);
                    R r=JSON.parseObject(result,R.class);
                    TransQueryOutputListEntity entity=JSON.parseObject(r.get("transQueryOutputListEntity").toString(),TransQueryOutputListEntity.class);
                    int type=cncbTrans.getType();
                    switch (type){
                        case 1:
                            handleType1(cncbTrans,entity);
                            break;
                        case 2:
                            handleType2(cncbTrans,entity);
                            break;
                        case 3:
                            handleType3(cncbTrans,entity);
                            break;
                        case 4:
                            handleType4(cncbTrans,entity);
                            break;
                        case 5:
                            handleType5(cncbTrans,entity);
                            break;
                        case 6:
                            handleType6(cncbTrans,entity);
                            break;
                        case 7:
                            handleType7(cncbTrans,entity);
                            break;
                        case 8:
                            handleType8(cncbTrans,entity);
                            break;
                        case 9:
                            handleType9(cncbTrans,entity);
                            break;
                        case 10:
                            handleType10(cncbTrans,entity);
                            break;
                        case 11:
                            handleType11(cncbTrans,entity);
                            break;
                        case 12:
                            handleType12(cncbTrans,entity);
                            break;
                    }
                }catch (CNCBException e){
                    e.printStackTrace();
                    log.error(e.getMessage(),e);
                    continue;
                } catch (Exception e) {
                    log.error(e.getMessage(),e);
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 定时同步数据库中的用户余额字段
     * @throws Exception
     */
    @Scheduled(fixedRate = 3600000)
    public void syncUserBalance() throws Exception {
        Map<String,Object> param=new HashMap<>();
        List<Map<String,Object>> allUser=waUserDao.listUserByConditions(param);
        for (Map<String,Object> user : allUser){
            long userId=(long)user.get("user_id");
            financeServiceImpl.syncBalance(userId);
        }
    }

    @Transactional
    private void handleType12(CncbTrans cncbTrans, TransQueryOutputListEntity entity) {
        String operationParams=cncbTrans.getOperationParams();
        Map params=JSON.parseObject(operationParams,Map.class);
        long loanId=(long)(int)params.get("loanId");

        Map update=new HashMap();
        update.put("id",cncbTrans.getId());
        update.put("status",entity.getStatus());
        update.put("statusText",entity.getStatusText());
        if(CNCBConstants.CNCB_STATUS_SUCCESS.equals(entity.getStatus())){
            update.put("state",1);
            Map<String,Object> updateLoan=new HashMap<>();
            updateLoan.put("loanId",loanId);
            updateLoan.put("loanState",4);
            loanDao.updateLoanByPrimaryKey(updateLoan);
        }else if(entity.getStatus().startsWith("AAAAAA")){

        }else {
        }
        cncbTransDao.update(update);
    }

    @Transactional
    private void handleType11(CncbTrans cncbTrans, TransQueryOutputListEntity entity) {
        String operationParams=cncbTrans.getOperationParams();
        Map params=JSON.parseObject(operationParams,Map.class);
        long orderId=(long)(int)params.get("orderId");
        long userId=(long)(int)params.get("userId");
        BigDecimal secondPayMoney=new BigDecimal(params.get("secondPayMoney").toString());
        long orderTransId=(long)(int)params.get("orderTransId");
        int zfMethod=(int)params.get("zfMethod");
        BigDecimal balance=new BigDecimal(params.get("balance").toString());
        BigDecimal loan=new BigDecimal(params.get("loan").toString());
        String ip=params.get("ip").toString();

        Map update=new HashMap();
        update.put("id",cncbTrans.getId());
        update.put("status",entity.getStatus());
        update.put("statusText",entity.getStatusText());
        if(CNCBConstants.CNCB_STATUS_SUCCESS.equals(entity.getStatus())){
            update.put("state",1);
            customerServiceImpl.handleCncbType11(orderId,userId,secondPayMoney,orderTransId,zfMethod,balance,loan,ip);
        }else if(entity.getStatus().startsWith("AAAAAA")){

        }else {
            update.put("state",-1);
            Map order=new HashMap();
            order.put("orderId",orderId);
            order.put("prod2ndpayState",0);
            orderDao.updateOrderByID(order);
        }
        cncbTransDao.update(update);
    }

    @Transactional
    private void handleType10(CncbTrans cncbTrans, TransQueryOutputListEntity entity) throws Exception {
        String operationParams=cncbTrans.getOperationParams();
        Map params=JSON.parseObject(operationParams,Map.class);
        long orderId=(long)(int)params.get("orderId");
        long userId=(long)(int)params.get("userId");
        BigDecimal secondPayMoney=new BigDecimal(params.get("secondPayMoney").toString());
        long orderTransId=(long)(int)params.get("orderTransId");
        BigDecimal refundBalance=new BigDecimal(params.get("refundBalance").toString());
        BigDecimal refundLoan=new BigDecimal(params.get("refundLoan").toString());

        Map update=new HashMap();
        update.put("id",cncbTrans.getId());
        update.put("status",entity.getStatus());
        update.put("statusText",entity.getStatusText());
        if(CNCBConstants.CNCB_STATUS_SUCCESS.equals(entity.getStatus())){
            update.put("state",1);
            customerServiceImpl.handleComboPayRefundStep2(orderId,userId,secondPayMoney,orderTransId,refundBalance,refundLoan);
        }else if(entity.getStatus().startsWith("AAAAAA")){

        }else {
            update.put("state",-1);
            Map order=new HashMap();
            order.put("orderId",orderId);
            order.put("prod2ndpayState",0);
            orderDao.updateOrderByID(order);
        }
        cncbTransDao.update(update);
    }

    @Transactional
    private void handleType9(CncbTrans cncbTrans, TransQueryOutputListEntity entity) {
        String operationParams=cncbTrans.getOperationParams();
        Map params=JSON.parseObject(operationParams,Map.class);
        long orderId=(long)(int)params.get("orderId");
        long userId=(long)(int)params.get("userId");
        BigDecimal secondPayMoney=new BigDecimal(params.get("secondPayMoney").toString());
        long orderTransId=(long)(int)params.get("orderTransId");
        BigDecimal refundBalance=new BigDecimal(params.get("refundBalance").toString());
        BigDecimal refundLoan=new BigDecimal(params.get("refundLoan").toString());

        Map update=new HashMap();
        update.put("id",cncbTrans.getId());
        update.put("status",entity.getStatus());
        update.put("statusText",entity.getStatusText());
        if(CNCBConstants.CNCB_STATUS_SUCCESS.equals(entity.getStatus())){
            update.put("state",1);
            customerServiceImpl.handleComboPayRefundStep1(orderId,userId,secondPayMoney,orderTransId,refundBalance,refundLoan);
        }else if(entity.getStatus().startsWith("AAAAAA")){

        }else {
            update.put("state",-1);
            Map order=new HashMap();
            order.put("orderId",orderId);
            order.put("prod2ndpayState",0);
            orderDao.updateOrderByID(order);
        }
        cncbTransDao.update(update);
    }

    /**
     * 授信支付退款后处理：将退款部分作为该贷款本金还款
     * @param cncbTrans wa_cncb_trans pojo
     * @param entity 轮询结果
     */
    @Transactional
    private void handleType8(CncbTrans cncbTrans, TransQueryOutputListEntity entity) throws Exception {
        String operationParams=cncbTrans.getOperationParams();
        Map params=JSON.parseObject(operationParams,Map.class);
        long orderId=(long)(int)params.get("orderId");
        BigDecimal secondPayMoney=new BigDecimal(params.get("secondPayMoney").toString());
        long orderTransId=(long)(int)params.get("orderTransId");

        Map update=new HashMap();
        update.put("id",cncbTrans.getId());
        update.put("status",entity.getStatus());
        update.put("statusText",entity.getStatusText());
        if(CNCBConstants.CNCB_STATUS_SUCCESS.equals(entity.getStatus())){
            update.put("state",1);
            customerServiceImpl.handleCreditPayRefund(orderId,secondPayMoney,orderTransId);
        }else if(entity.getStatus().startsWith("AAAAAA")){

        }else {
            update.put("state",-1);
            Map order=new HashMap();
            order.put("orderId",orderId);
            order.put("prod2ndpayState",0);
            orderDao.updateOrderByID(order);
        }
        cncbTransDao.update(update);
    }

    /**
     * 物流结算给卖方交易成功后处理
     * @param cncbTrans wa_cncb_trans pojo
     * @param entity 轮询结果
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    private void handleType7(CncbTrans cncbTrans, TransQueryOutputListEntity entity) {
        String operationParams=cncbTrans.getOperationParams();
        Map params=JSON.parseObject(operationParams,Map.class);
        long logisticsId=(long)(int)params.get("logisticsId");
        long orderId=(long)(int)params.get("orderId");
        long senderUserId=(long)(int)params.get("senderUserId");
        BigDecimal zfSjMoney=(BigDecimal)params.get("zfSjMoney");

        Map update=new HashMap();
        update.put("id",cncbTrans.getId());
        update.put("status",entity.getStatus());
        update.put("statusText",entity.getStatusText());
        if(CNCBConstants.CNCB_STATUS_SUCCESS.equals(entity.getStatus())){
            update.put("state",1);
            customerServiceImpl.handleSettleLogistics(logisticsId,orderId,zfSjMoney);
        }else if(entity.getStatus().startsWith("AAAAAA")){

        }else {
            update.put("state",-1);
            Map order=new HashMap();
            order.put("orderId",orderId);
            order.put("logistics2ndpayState",5);
            orderDao.updateOrderByID(order);
        }
        cncbTransDao.update(update);
    }

    /**
     * 商品订单结算给卖方交易成功后处理
     * @param cncbTrans wa_cncb_trans pojo
     * @param entity 轮询结果
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    private void handleType6(CncbTrans cncbTrans, TransQueryOutputListEntity entity) {
        String operationParams=cncbTrans.getOperationParams();
        Map params=JSON.parseObject(operationParams,Map.class);
        long orderId=(long)(int)params.get("orderId");
        long orderTransId=(long)(int)params.get("orderTransId");
        long moneyId=(long)(int)params.get("moneyId");
        BigDecimal zfSjMoney=(BigDecimal)params.get("zfSjMoney");

        Map update=new HashMap();
        update.put("id",cncbTrans.getId());
        update.put("status",entity.getStatus());
        update.put("statusText",entity.getStatusText());
        if(CNCBConstants.CNCB_STATUS_SUCCESS.equals(entity.getStatus())){
            update.put("state",1);
            customerServiceImpl.handlePay2Seller(orderId,orderTransId,moneyId,zfSjMoney);
        }else if(entity.getStatus().startsWith("AAAAAA")){

        }else {
            update.put("state",-1);
            Map order=new HashMap();
            order.put("orderId",orderId);
            order.put("prod2ndpayState",5);
            orderDao.updateOrderByID(order);
        }
        cncbTransDao.update(update);
    }

    /**
     * 多退少补交易成功后处理
     * @param cncbTrans wa_cncb_trans pojo
     * @param entity 轮询结果
     * @throws Exception
     */
    private void handleType5(CncbTrans cncbTrans, TransQueryOutputListEntity entity) throws Exception {
        String operationParams=cncbTrans.getOperationParams();
        Map params=JSON.parseObject(operationParams,Map.class);
        long orderId=(long)(int)params.get("orderId");
        BigDecimal secondPayMoney=new BigDecimal(params.get("secondPayMoney").toString());
        int zfMethod=(int)params.get("zfMethod");
        BigDecimal balance=new BigDecimal(params.get("balance").toString());
        BigDecimal loan=new BigDecimal(params.get("loan").toString());
        String certificateImg=(String)params.get("certificateImg");
        Map update=new HashMap();
        update.put("id",cncbTrans.getId());
        update.put("status",entity.getStatus());
        update.put("statusText",entity.getStatusText());
        if(CNCBConstants.CNCB_STATUS_SUCCESS.equals(entity.getStatus())){
            update.put("state",1);
            customerServiceImpl.handle2ndPayProd(orderId,secondPayMoney,zfMethod,balance,loan,certificateImg);
        }else if(entity.getStatus().startsWith("AAAAAA")){

        }else {
            update.put("state",-1);
            Map order=new HashMap();
            order.put("orderId",orderId);
            order.put("prod2ndpayState",0);
            orderDao.updateOrderByID(order);
        }
        cncbTransDao.update(update);
    }

    /**
     * 物流订单预付款交易成功后处理
     * @param cncbTrans wa_cncb_trans pojo
     * @param entity 轮询结果
     */
    private void handleType3(CncbTrans cncbTrans, TransQueryOutputListEntity entity) {
        String operationParams=cncbTrans.getOperationParams();
        Map params=JSON.parseObject(operationParams,Map.class);
        String sql=(String)params.get("sql");
        String orderId=(String) params.get("orderId");
        HashMap queryParam = new HashMap();
        Map update=new HashMap();
        update.put("id",cncbTrans.getId());
        update.put("status",entity.getStatus());
        update.put("statusText",entity.getStatusText());
        if(CNCBConstants.CNCB_STATUS_SUCCESS.equals(entity.getStatus())){
            update.put("state",1);
            queryParam.put("strsql", sql);
            this.commonMapper.simpleSelectReturnList(queryParam);
            waOrderServiceImpl.checkOrderRepayOff(Long.parseLong(orderId));
        }else if(entity.getStatus().startsWith("AAAAAA")){

        }else {
            update.put("state",-1);
            queryParam.put("orderId",Long.parseLong(orderId));
            queryParam.put("logisticsPayState",0);
            orderDao.updateOrderByID(queryParam);
        }
        cncbTransDao.update(update);
    }

    /**
     * 商品订单预付款交易成功后处理
     * @param cncbTrans wa_cncb_trans pojo
     * @param entity 轮询结果
     */
    @Transactional
    private void handleType1(CncbTrans cncbTrans,TransQueryOutputListEntity entity){
        String operationParams=cncbTrans.getOperationParams();
        Map params=JSON.parseObject(operationParams,Map.class);
        String sql=(String)params.get("sql");
        String orderId=(String) params.get("orderId");
        HashMap queryParam = new HashMap();
        Map update=new HashMap();
        update.put("id",cncbTrans.getId());
        update.put("status",entity.getStatus());
        update.put("statusText",entity.getStatusText());
        if(CNCBConstants.CNCB_STATUS_SUCCESS.equals(entity.getStatus())){
            update.put("state",1);
            queryParam.put("strsql", sql);
            this.commonMapper.simpleSelectReturnList(queryParam);
            waOrderServiceImpl.checkOrderRepayOff(Long.parseLong(orderId));
        }else if(entity.getStatus().startsWith("AAAAAA")){

        }else {
            update.put("state",-1);
            queryParam.put("orderId",Long.parseLong(orderId));
            queryParam.put("prodPayState",0);
            orderDao.updateOrderByID(queryParam);
        }
        cncbTransDao.update(update);
    }

    /**
     * 余额还款交易成功后处理
     * @param cncbTrans wa_cncb_trans pojo
     * @param entity 轮询结果
     */
    private void handleType4(CncbTrans cncbTrans, TransQueryOutputListEntity entity) {
        String operationParams=cncbTrans.getOperationParams();
        Map params=JSON.parseObject(operationParams,Map.class);
        long userId=(long)(int)params.get("userId");
        long loanId=(long)(int)params.get("loanId");
        BigDecimal principal=new BigDecimal(params.get("principal").toString());
        BigDecimal overdueFine=new BigDecimal(params.get("overdueFine").toString());
        BigDecimal interest=new BigDecimal(params.get("interest").toString());
        String ip=(String)params.get("ip");
        Map updateLoan=new HashMap();
        Map update=new HashMap();
        update.put("id",cncbTrans.getId());
        update.put("status",entity.getStatus());
        update.put("statusText",entity.getStatusText());
        if(CNCBConstants.CNCB_STATUS_SUCCESS.equals(entity.getStatus())){
            update.put("state",1);
            customerServiceImpl.repayLoanByBalance(userId,loanId,principal,overdueFine,interest,ip);
        }else if(entity.getStatus().startsWith("AAAAAA")){

        }else {
            updateLoan.put("loanId",loanId);
            updateLoan.put("loanState",2);
            loanDao.updateLoanByPrimaryKey(updateLoan);
            update.put("state",-1);
        }
        cncbTransDao.update(update);
    }

    /**
     * 放款方放款交易成功后处理
     * @param cncbTrans wa_cncb_trans pojo
     * @param entity 轮询结果
     */
    public void handleType2(CncbTrans cncbTrans,TransQueryOutputListEntity entity) throws Exception {
        String operationParams=cncbTrans.getOperationParams();
        Map params=JSON.parseObject(operationParams,Map.class);
        long loanId=(long)(int)params.get("loanId");
        long creditorUserId=(long)(int)params.get("creditorUserId");
        int paymentDays=(int) params.get("paymentDays");
        String operateIp=(String)params.get("operateIp");
        Map updateLoan=new HashMap();
        Map update=new HashMap();
        update.put("id",cncbTrans.getId());
        update.put("status",entity.getStatus());
        update.put("statusText",entity.getStatusText());
        if(CNCBConstants.CNCB_STATUS_SUCCESS.equals(entity.getStatus())){
            update.put("state",1);
            creditorServiceImpl.acceptLoan(loanId,creditorUserId,paymentDays,operateIp);
        }else if(entity.getStatus().startsWith("AAAAAA")){

        }else {
            updateLoan.put("loanId",loanId);
            updateLoan.put("loanState",1);
            loanDao.updateLoanByPrimaryKey(updateLoan);
            update.put("state",-1);
        }
        cncbTransDao.update(update);
    }
}
