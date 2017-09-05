package com.wellapay.cncb.model.output;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by Administrator on 2017/8/31.
 */
@XStreamAlias("stream")
public class RegisterOutput {
    private String status;
    private String statusText;
    private String subAccNo;
    private String subAccNm;
    private String hostNo;

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
}
