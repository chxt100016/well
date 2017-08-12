package com.wellassist.pay.model.output;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 出金输出参数
 */
@XStreamAlias("stream")
public class WithdrawMoneyOutput {
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

    private String status;//</status><!--交易状态 char(7)-->
    private String statusText;//</statusText><!--交易状态信息 varchar(254)-->
}
