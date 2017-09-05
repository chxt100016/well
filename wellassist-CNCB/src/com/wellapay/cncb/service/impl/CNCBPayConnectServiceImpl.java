package com.wellapay.cncb.service.impl;

import com.wellapay.cncb.Exception.CNCBException;
import com.wellapay.cncb.model.ForceTransferBasicInfo;
import com.wellapay.cncb.model.input.BalanceQuery;
import com.wellapay.cncb.model.input.ForceTransfer;
import com.wellapay.cncb.model.input.Register;
import com.wellapay.cncb.model.input.TransQuery;
import com.wellapay.cncb.model.output.*;
import com.wellapay.cncb.service.CNCBPayConnectService;
import com.wellapay.cncb.util.CNCBConstants;
import com.wellapay.cncb.util.CommonUtil;
import com.wellapay.cncb.util.HttpConnectionUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


/**
 * Created by Administrator on 2017/9/4.
 */
@Service("cncbPayConnectServiceImpl")
public class CNCBPayConnectServiceImpl implements CNCBPayConnectService{

    @Override
    public RegisterOutput register(Register register) throws Exception {
        HttpConnectionUtil http=new HttpConnectionUtil(CNCBConstants.CNCB_APIURL);
        http.init();
        String xml= CommonUtil.CNCBModel2String(register);
        byte[] bys = http.postParams(xml, true);
        String result = new String(bys,"GB2312");
        RegisterOutput registerOutput=CommonUtil.String2CNCBModel(result,RegisterOutput.class);
        if (CNCBConstants.CNCB_STATUS_SUCCESS.equals(registerOutput.getStatus())){
            return registerOutput;
        }else {
            throw new CNCBException(registerOutput.getStatus(),registerOutput.getStatusText());
        }
    }

    @Override
    public BalanceQueryOutput balanceQuery(String subAccNo) throws Exception {
        BalanceQuery balanceQuery=new BalanceQuery();
        balanceQuery.setAction("DLSBALQR");
        balanceQuery.setUserName(CNCBConstants.CNDB_USERNAME);
        balanceQuery.setAccountNo(CNCBConstants.CNDB_MAINACCNO);
        balanceQuery.setSubAccNo(subAccNo);
        BalanceQueryOutput balanceQueryOutput=handleRequest(balanceQuery,BalanceQueryOutput.class);
        if (CNCBConstants.CNCB_STATUS_SUCCESS.equals(balanceQueryOutput.getStatus())){
            return balanceQueryOutput;
        }else {
            throw new CNCBException(balanceQueryOutput.getStatus(), balanceQueryOutput.getStatusText());
        }
    }

    public <T> T handleRequest(Object o,Class<T> tClass) throws Exception {
        HttpConnectionUtil http=new HttpConnectionUtil(CNCBConstants.CNCB_APIURL);
        http.init();
        String xml= CommonUtil.CNCBModel2String(o);
        byte[] bys = http.postParams(xml, true);
        String result = new String(bys,"GB2312");
        T t=CommonUtil.String2CNCBModel(result,tClass);
        return t;
    }

    @Override
    public ForceTransferBasicInfo forceTransfer(String payAccNo, String recvAccNo, String recvAccNm, BigDecimal tranAmt) throws Exception {
        ForceTransfer forceTransfer=new ForceTransfer();
        forceTransfer.setAction("DLMDETRN");
        forceTransfer.setUserName(CNCBConstants.CNDB_USERNAME);
        String clientID=CommonUtil.createClientID(4);
        forceTransfer.setClientID(clientID);
        forceTransfer.setAccountNo(CNCBConstants.CNDB_MAINACCNO);
        forceTransfer.setPayAccNo(payAccNo);
        forceTransfer.setTranType("BF");
        forceTransfer.setRecvAccNo(recvAccNo);
        forceTransfer.setRecvAccNm(recvAccNm);
        forceTransfer.setTranAmt(tranAmt.toString());
        forceTransfer.setFreezeNo("");
        forceTransfer.setOfreezeamt("");
        forceTransfer.setMemo("");
        forceTransfer.setTranFlag("0");
        CommonOutput commonOutput=handleRequest(forceTransfer,CommonOutput.class);
        if (CNCBConstants.CNCB_STATIC_COMMIT.equals(commonOutput.getStatus())){
            ForceTransferBasicInfo forceTransferBasicInfo=new ForceTransferBasicInfo();
            forceTransferBasicInfo.setXml(CommonUtil.CNCBModel2String(forceTransfer));
            forceTransferBasicInfo.setClientID(clientID);
            return forceTransferBasicInfo;
        }else {
            throw new CNCBException(commonOutput.getStatus(), commonOutput.getStatusText());
        }
    }

    @Override
    public ForceTransferBasicInfo forceTransfer2TransferAccNo(String payAccNo, BigDecimal tranAmt) throws Exception {
        return forceTransfer(payAccNo,CNCBConstants.CNCB_TRANSFER_ACCNO,CNCBConstants.CNCB_TRANSFER_ACCNM,tranAmt);
    }

    @Override
    public ForceTransferBasicInfo forceTransferFromTransferAccNo(String recvAccNo, String recvAccNm, BigDecimal tranAmt) throws Exception {
        return forceTransfer(CNCBConstants.CNCB_TRANSFER_ACCNO,recvAccNo,recvAccNm,tranAmt);
    }

    @Override
    public TransQueryOutputListEntity transQuery(String clientID) throws Exception {
        TransQuery transQuery=new TransQuery();
        transQuery.setAction("DLCIDSTT");
        transQuery.setUserName(CNCBConstants.CNDB_USERNAME);
        transQuery.setClientID(clientID);
        transQuery.setType("");
        TransQueryOutput transQueryOutput=handleRequest(transQuery,TransQueryOutput.class);
        if (CNCBConstants.CNCB_STATUS_SUCCESS.equals(transQueryOutput.getStatus())){
            return transQueryOutput.getList().getList().get(0);
        }else {
            throw new CNCBException(transQueryOutput.getStatus(), transQueryOutput.getStatusText());
        }
    }
}
