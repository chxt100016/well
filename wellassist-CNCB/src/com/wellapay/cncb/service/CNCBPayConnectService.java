package com.wellapay.cncb.service;

import com.wellapay.cncb.model.ForceTransferBasicInfo;
import com.wellapay.cncb.model.input.Register;
import com.wellapay.cncb.model.output.*;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/9/4.
 */
public interface CNCBPayConnectService {

    RegisterOutput register(Register register) throws Exception;

    BalanceQueryOutput balanceQuery(String subAccNo) throws Exception;

    ForceTransferBasicInfo forceTransfer(String payAccNo, String recvAccNo, String recvAccNm, BigDecimal tranAmt)throws Exception;

    ForceTransferBasicInfo forceTransfer2TransferAccNo(String payAccNo,BigDecimal tranAmt)throws Exception;

    ForceTransferBasicInfo forceTransferFromTransferAccNo(String recvAccNo,String recvAccNm,BigDecimal tranAmt) throws Exception;

    TransQueryOutputListEntity transQuery(String clientID) throws Exception;
}
