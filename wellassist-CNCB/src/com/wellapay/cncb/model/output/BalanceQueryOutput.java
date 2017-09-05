package com.wellapay.cncb.model.output;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by Administrator on 2017/9/4.
 */
@XStreamAlias("stream")
public class BalanceQueryOutput {
    private String status;
    private String statusText;

    private BalanceQueryOutputList list;

    public BalanceQueryOutputList getList() {
        return list;
    }

    public void setList(BalanceQueryOutputList list) {
        this.list = list;
    }

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
}
