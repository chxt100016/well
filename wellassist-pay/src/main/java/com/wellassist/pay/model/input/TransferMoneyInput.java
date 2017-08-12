package com.wellassist.pay.model.input;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 转账输入参数
 */
@XStreamAlias("stream")
public class TransferMoneyInput {
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

    public String getPayAccNo() {
        return payAccNo;
    }

    public void setPayAccNo(String payAccNo) {
        this.payAccNo = payAccNo;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    private String action;//DLSUBTRN</action>
    private String userName;//</userName><!--登录名 varchar(30)-->
    private String clientID;//</clientID><!--客户流水号varchar (20)-->
    private String payAccNo;//</payAccNo><!--付款账号varchar(19)-->
    private String recvAccNo;//</recvAccNo><!--收款账号varchar(19)-->
    private String recvAccNm;//</recvAccNm><!--收款账户名称varchar(122)-->
    private String tranAmt;//</tranAmt><!--交易金额decimal(15,2)-->
    private String memo;//</memo><!--摘要varchar(102) 可空-->
}
