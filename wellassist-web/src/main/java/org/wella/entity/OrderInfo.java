package org.wella.entity;


import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class OrderInfo {
    private long infoId;
    private long orderId;
    private byte infoType;
    private long mgrUserId;
    private Date mgrDate;
    private String mgrContent;
    private String mgrIp;

    public long getInfoId() {
        return infoId;
    }

    public void setInfoId(long infoId) {
        this.infoId = infoId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public byte getInfoType() {
        return infoType;
    }

    public void setInfoType(byte infoType) {
        this.infoType = infoType;
    }

    public long getMgrUserId() {
        return mgrUserId;
    }

    public void setMgrUserId(long mgrUserId) {
        this.mgrUserId = mgrUserId;
    }

    public Date getMgrDate() {
        return mgrDate;
    }

    public void setMgrDate(Date mgrDate) {
        this.mgrDate = mgrDate;
    }

    public String getMgrContent() {
        return mgrContent;
    }

    public void setMgrContent(String mgrContent) {
        this.mgrContent = mgrContent;
    }

    public String getMgrIp() {
        return mgrIp;
    }

    public void setMgrIp(String mgrIp) {
        this.mgrIp = mgrIp;
    }


}
