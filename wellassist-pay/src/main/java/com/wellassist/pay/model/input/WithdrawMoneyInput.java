package com.wellassist.pay.model.input;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 出金输入参数
 */
@XStreamAlias("stream")
public class WithdrawMoneyInput {
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

    private String action;//DLFNDOUT</action>
    private String userName;//</userName><!--登录名char(30)-->
    private String clientID;//</clientID><!--客户流水号 char(20)-->
    private String accountNo;//</accountNo><!--付款账号varchar(19)-->
    private String recvAccNo;//</recvAccNo><!--收款账号varchar(32)-->
    private String recvAccNm;//</recvAccNm><!--收款账户名称varchar(122)-->
    private String tranAmt;//</tranAmt><!--交易金额decimal(15,2)-->
    private String sameBank;//</sameBank><!--中信标识char(1) 0：本行 1： 他行-->
    //<!--收款账户开户行信息begin-->
    //<!--收款账户若为他行，则收款账户开户行支付联行号与收款账户开户行名至少一项不为空-->
    private String payType;//</payType><!--支付方式varchar(2)，2H：大额；2B：小额；2E：网银跨行支付，可空-->
    private String recvTgfi;//</recvTgfi><!--收款账户开户行支付联行号varchar(20)，（注：当支付方式为网银跨行支付，此字段为收款人所属银行行号）-->
    private String recvBankNm;//</recvBankNm><!--收款账户开户行名varchar (122)-->
    //!--收款账户开户行信息end-->
    private String memo;//</memo><!--摘要varchar(102) 可空-->
    private String preFlg;//</preFlg><!--预约标志（0：非预约1：预约）char(1)-->
    private String preDate;//</preDate><!--预约日期（格式：YYYYMMDD 预约时非空）char(8)-->
    private String preTime;//</preTime><!--预约时间（格式：hhmmss 预约时非空，只限100000、120000、140000、160000四个时间点）char(6)-->
}
