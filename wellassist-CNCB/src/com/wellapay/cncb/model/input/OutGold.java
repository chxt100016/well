package com.wellapay.cncb.model.input;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by Administrator on 2017/8/30.
 */

/**
 * 出金
 */
@XStreamAlias("stream")
public class OutGold {
    private String action;
    private String userName;
    private String clientID;
    private String accountNo;
    private String recvAccNo;
    private String recvAccNm;
    private String tranAmt;
    private String sameBank;
    private String payType;
    private String recvTgfi;
    private String recvBankNm;
    private String memo;
    private String preFlg;
    private String preDate;
    private String preTime;

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

    public String getSameBank() {
        return sameBank;
    }

    public void setSameBank(String sameBank) {
        this.sameBank = sameBank;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getRecvTgfi() {
        return recvTgfi;
    }

    public void setRecvTgfi(String recvTgfi) {
        this.recvTgfi = recvTgfi;
    }

    public String getRecvBankNm() {
        return recvBankNm;
    }

    public void setRecvBankNm(String recvBankNm) {
        this.recvBankNm = recvBankNm;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getPreFlg() {
        return preFlg;
    }

    public void setPreFlg(String preFlg) {
        this.preFlg = preFlg;
    }

    public String getPreDate() {
        return preDate;
    }

    public void setPreDate(String preDate) {
        this.preDate = preDate;
    }

    public String getPreTime() {
        return preTime;
    }

    public void setPreTime(String preTime) {
        this.preTime = preTime;
    }
}
