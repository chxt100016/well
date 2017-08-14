package com.wellassist.pay.model.input;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("stream")
public class CapitalInitInput {
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

    public String getSubAccNo() {
        return subAccNo;
    }

    public void setSubAccNo(String subAccNo) {
        this.subAccNo = subAccNo;
    }

    public String getSubAccNm() {
        return subAccNm;
    }

    public void setSubAccNm(String subAccNm) {
        this.subAccNm = subAccNm;
    }

    public String getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(String tranAmt) {
        this.tranAmt = tranAmt;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    private String action;
   private String userName;//<!--登录名 varchar(30)-->
   private String clientID;//<!--客户流水号 char(20)-->
   private String accountNo;//<!--资金初始化账号varchar(19)-->
   private String subAccNo;//<!--调入附属账号varchar(19)-->
   private String subAccNm;//<!--调入附属账户名称varchar(122)-->
   private String tranAmt;//<!--初始化交易金额decimal(15,2)-->
   private String memo;//<!--摘要varchar(102) 可空-->
}
