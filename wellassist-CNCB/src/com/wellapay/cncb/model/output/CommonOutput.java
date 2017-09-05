package com.wellapay.cncb.model.output;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by Administrator on 2017/8/30.
 */
@XStreamAlias("stream")
public class CommonOutput {
    private String status;
    private String statusText;

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

    @Override
    public String toString() {
        return "CommonOutput{" +
                "status='" + status + '\'' +
                ", statusText='" + statusText + '\'' +
                '}';
    }
}
