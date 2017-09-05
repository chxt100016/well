package com.wellapay.cncb.model.input;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by Administrator on 2017/8/30.
 */

/**
 * 调账入款
 */
@XStreamAlias("stream")
public class TurnAccountsIn {
    private String action;
    private String userName;
    private String clientID;
    private String accountNo;
    private String recvAccNo;
    private String recvAccNm;
    private String tranAmt;
    private String hostDate;
    private String hostFlw;
    private String hostSeq;
    private String memo;

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

    public String getHostDate() {
        return hostDate;
    }

    public void setHostDate(String hostDate) {
        this.hostDate = hostDate;
    }

    public String getHostFlw() {
        return hostFlw;
    }

    public void setHostFlw(String hostFlw) {
        this.hostFlw = hostFlw;
    }

    public String getHostSeq() {
        return hostSeq;
    }

    public void setHostSeq(String hostSeq) {
        this.hostSeq = hostSeq;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
