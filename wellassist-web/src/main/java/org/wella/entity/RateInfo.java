package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class RateInfo {
    private long infoId;
    private long userId;
    private long creditId;
    private BigDecimal qkMoney;
    private BigDecimal lxMoney;
    private Date jsDate;
    private BigDecimal sumLxMoney;
    private long jsUserId;
    private String jsIp;

    public long getInfoId() {
        return infoId;
    }

    public void setInfoId(long infoId) {
        this.infoId = infoId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCreditId() {
        return creditId;
    }

    public void setCreditId(long creditId) {
        this.creditId = creditId;
    }

    public BigDecimal getQkMoney() {
        return qkMoney;
    }

    public void setQkMoney(BigDecimal qkMoney) {
        this.qkMoney = qkMoney;
    }

    public BigDecimal getLxMoney() {
        return lxMoney;
    }

    public void setLxMoney(BigDecimal lxMoney) {
        this.lxMoney = lxMoney;
    }

    public Date getJsDate() {
        return jsDate;
    }

    public void setJsDate(Date jsDate) {
        this.jsDate = jsDate;
    }

    public BigDecimal getSumLxMoney() {
        return sumLxMoney;
    }

    public void setSumLxMoney(BigDecimal sumLxMoney) {
        this.sumLxMoney = sumLxMoney;
    }

    public long getJsUserId() {
        return jsUserId;
    }

    public void setJsUserId(long jsUserId) {
        this.jsUserId = jsUserId;
    }

    public String getJsIp() {
        return jsIp;
    }

    public void setJsIp(String jsIp) {
        this.jsIp = jsIp;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RateInfo that = (RateInfo) o;

        if (infoId != that.infoId) return false;
        if (userId != that.userId) return false;
        if (creditId != that.creditId) return false;
        if (jsUserId != that.jsUserId) return false;
        if (qkMoney != null ? !qkMoney.equals(that.qkMoney) : that.qkMoney != null) return false;
        if (lxMoney != null ? !lxMoney.equals(that.lxMoney) : that.lxMoney != null) return false;
        if (jsDate != null ? !jsDate.equals(that.jsDate) : that.jsDate != null) return false;
        if (sumLxMoney != null ? !sumLxMoney.equals(that.sumLxMoney) : that.sumLxMoney != null) return false;
        if (jsIp != null ? !jsIp.equals(that.jsIp) : that.jsIp != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (infoId ^ (infoId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (creditId ^ (creditId >>> 32));
        result = 31 * result + (qkMoney != null ? qkMoney.hashCode() : 0);
        result = 31 * result + (lxMoney != null ? lxMoney.hashCode() : 0);
        result = 31 * result + (jsDate != null ? jsDate.hashCode() : 0);
        result = 31 * result + (sumLxMoney != null ? sumLxMoney.hashCode() : 0);
        result = 31 * result + (int) (jsUserId ^ (jsUserId >>> 32));
        result = 31 * result + (jsIp != null ? jsIp.hashCode() : 0);
        return result;
    }
}
