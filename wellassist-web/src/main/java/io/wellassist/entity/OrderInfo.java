package io.wellassist.entity;


import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class OrderInfo {
    private long infoId;
    private long orderId;
    private String infoType;
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

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
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

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderInfo that = (OrderInfo) o;

        if (infoId != that.infoId) return false;
        if (orderId != that.orderId) return false;
        if (mgrUserId != that.mgrUserId) return false;
        if (infoType != null ? !infoType.equals(that.infoType) : that.infoType != null) return false;
        if (mgrDate != null ? !mgrDate.equals(that.mgrDate) : that.mgrDate != null) return false;
        if (mgrContent != null ? !mgrContent.equals(that.mgrContent) : that.mgrContent != null) return false;
        if (mgrIp != null ? !mgrIp.equals(that.mgrIp) : that.mgrIp != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (infoId ^ (infoId >>> 32));
        result = 31 * result + (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (infoType != null ? infoType.hashCode() : 0);
        result = 31 * result + (int) (mgrUserId ^ (mgrUserId >>> 32));
        result = 31 * result + (mgrDate != null ? mgrDate.hashCode() : 0);
        result = 31 * result + (mgrContent != null ? mgrContent.hashCode() : 0);
        result = 31 * result + (mgrIp != null ? mgrIp.hashCode() : 0);
        return result;
    }
}
