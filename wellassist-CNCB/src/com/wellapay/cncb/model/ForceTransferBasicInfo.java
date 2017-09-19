package com.wellapay.cncb.model;

import com.wellapay.cncb.model.output.CommonOutput;

/**
 * Created by Administrator on 2017/9/4.
 */
public class ForceTransferBasicInfo extends CommonOutput{
    private String xml;
    private String clientID;

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }
}
