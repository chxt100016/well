package com.wellassist.pay.model.output;

import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * 账户注册输出
 */
@XStreamAlias("stream")
public class MemberRegisterOutput {
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

    public String getHostNo() {
        return hostNo;
    }

    public void setHostNo(String hostNo) {
        this.hostNo = hostNo;
    }

    private String status;//<!--交易状态 char(7)-->
    private String statusText;//<!--交易状态信息 varchar(254)-->
    private String subAccNo;//<!--附属账号 char(19)-->
    private String subAccNm;//<!--附属账户名称 varchar(122)-->
    private String hostNo;//<!--客户号 char(12)-->
}
