package com.wellassist.pay.model.output;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("stream")
public class AccountQueryOutput {
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public UserData getUserDataList() {
        return userDataList;
    }

    public void setUserDataList(UserData userDataList) {
        this.userDataList = userDataList;
    }

    private String status;//</status><!--交易状态 char(7)-->
    private String statusText;//</statusText><!--交易状态信息 varchar(254)-->
    @XStreamAlias("list")
    private UserData userDataList;
}
