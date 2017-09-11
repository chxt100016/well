package org.wella.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.javafx.collections.MappingChange;
import com.wellapay.cncb.Exception.CNCBException;
import com.wellapay.cncb.model.output.TransQueryOutputListEntity;
import com.wellapay.cncb.service.CNCBPayConnectService;
import com.wellapay.cncb.util.CNCBConstants;
import io.wellassist.utils.R;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.wella.common.mapper.CommonMapper;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.dao.CncbTransDao;
import org.wella.dao.LoanDao;
import org.wella.dao.OrderDao;
import org.wella.entity.CncbTrans;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/4.
 */
@Component
public class WaScheduleJobService {
    private static Log log = LogFactory.getLog(WaScheduleJobService.class);
    @Autowired
    private CncbTransDao cncbTransDao;

    @Autowired
    private CNCBPayConnectService cncbPayConnectServiceImpl;

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



    @Scheduled(fixedRate = 300000)
    public void checkCncbTrans(){
        Map<String,Object> query=new HashMap();
        query.put("state",0);
        List<CncbTrans> list=cncbTransDao.queryList(query);
        if( null != list && list.size() >0 ){
            for (CncbTrans cncbTrans:list){
                String clientID=cncbTrans.getClientId();
                try {
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

    private void handleType4(CncbTrans cncbTrans, TransQueryOutputListEntity entity) {
        String operationParams=cncbTrans.getOperationParams();
        Map params=JSON.parseObject(operationParams,Map.class);
        long userId=(long)(int)params.get("userId");
        long loanId=(long)(int)params.get("loanId");
        BigDecimal principal=(BigDecimal)params.get("principal");
        BigDecimal interest=(BigDecimal)params.get("interest");
        String ip=(String)params.get("ip");
        Map updateLoan=new HashMap();
        Map update=new HashMap();
        update.put("id",cncbTrans.getId());
        update.put("status",entity.getStatus());
        update.put("statusText",entity.getStatusText());
        if(CNCBConstants.CNCB_STATUS_SUCCESS.equals(entity.getStatus())){
            update.put("state",1);
            customerServiceImpl.repayLoanByBalance(userId,loanId,principal,interest,ip);
        }else if(entity.getStatus().startsWith("AAAAAA")){

        }else {
            updateLoan.put("loanId",loanId);
            updateLoan.put("loanState",2);
            loanDao.updateLoanByPrimaryKey(updateLoan);
            update.put("state",-1);
        }
        cncbTransDao.update(update);
    }

    public void handleType2(CncbTrans cncbTrans,TransQueryOutputListEntity entity){
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
