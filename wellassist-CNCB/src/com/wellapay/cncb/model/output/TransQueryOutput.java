package com.wellapay.cncb.model.output;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by Administrator on 2017/9/4.
 */
@XStreamAlias("stream")
public class TransQueryOutput {
    private String status;
    private String statusText;
    private TransQueryOutputList list;

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

    public TransQueryOutputList getList() {
        return list;
    }

    public void setList(TransQueryOutputList list) {
        this.list = list;
    }
}
