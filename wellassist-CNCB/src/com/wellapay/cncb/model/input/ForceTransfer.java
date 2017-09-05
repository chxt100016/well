package com.wellapay.cncb.model.input;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 强制转账
 */
@XStreamAlias("stream")
public class ForceTransfer {
    private String action;
    private String userName;
    private String clientID;
    private String accountNo;
    private String payAccNo;
    private String tranType;
    private String recvAccNo;
    private String recvAccNm;
    private String tranAmt;
    private String freezeNo;
    private String ofreezeamt;
    private String memo;
    private String tranFlag;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getPayAccNo() {
        return payAccNo;
    }

    public void setPayAccNo(String payAccNo) {
        this.payAccNo = payAccNo;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getRecvAccNo() {
        return recvAccNo;
    }

    public void setRecvAccNo(String recvAccNo) {
        this.recvAccNo = recvAccNo;
    }

    public String getRecvAccNm() {
        return recvAccNm;
    }

    public void setRecvAccNm(String recvAccNm) {
        this.recvAccNm = recvAccNm;
    }

    public String getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(String tranAmt) {
        this.tranAmt = tranAmt;
    }

    public String getFreezeNo() {
        return freezeNo;
    }

    public void setFreezeNo(String freezeNo) {
        this.freezeNo = freezeNo;
    }

    public String getOfreezeamt() {
        return ofreezeamt;
    }

    public void setOfreezeamt(String ofreezeamt) {
        this.ofreezeamt = ofreezeamt;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTranFlag() {
        return tranFlag;
    }

    public void setTranFlag(String tranFlag) {
        this.tranFlag = tranFlag;
    }
}
