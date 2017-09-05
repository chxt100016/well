package com.wellapay.cncb.model.input;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by Administrator on 2017/8/30.
 */
@XStreamAlias("stream")
public class BalanceQuery {
    private String action;
    private String userName;
    private String accountNo;
    private String subAccNo;

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
}
