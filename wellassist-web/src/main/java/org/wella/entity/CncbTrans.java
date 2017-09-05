package org.wella.entity;

import java.util.Date;

public class CncbTrans {
    private Long id;

    private String clientId;

    private Byte state;

    private Date time;

    private String statusText;

    private Byte checkState;

    private String operationParams;

    private Byte type;

    private String status;

    private String xml;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText == null ? null : statusText.trim();
    }

    public Byte getCheckState() {
        return checkState;
    }

    public void setCheckState(Byte checkState) {
        this.checkState = checkState;
    }

    public String getOperationParams() {
        return operationParams;
    }

    public void setOperationParams(String operationParams) {
        this.operationParams = operationParams == null ? null : operationParams.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml == null ? null : xml.trim();
    }
}