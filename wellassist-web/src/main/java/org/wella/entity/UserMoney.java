package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class UserMoney {
    private long moneyId;
    private long userId;
    private byte jyType;
    private long orderId;
    private String jyMc;
    private String content;
    private BigDecimal jyMoney;
    private BigDecimal jySjMoney;
    private long mgrUserId;
    private String mgrIp;
    private Date jyDate;
    private Date completeDate;
    private byte jyState;

    public long getMoneyId() {
        return moneyId;
    }

    public void setMoneyId(long moneyId) {
        this.moneyId = moneyId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public byte getJyType() {
        return jyType;
    }

    public void setJyType(byte jyType) {
        this.jyType = jyType;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getJyMc() {
        return jyMc;
    }

    public void setJyMc(String jyMc) {
        this.jyMc = jyMc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigDecimal getJyMoney() {
        return jyMoney;
    }

    public void setJyMoney(BigDecimal jyMoney) {
        this.jyMoney = jyMoney;
    }

    public BigDecimal getJySjMoney() {
        return jySjMoney;
    }

    public void setJySjMoney(BigDecimal jySjMoney) {
        this.jySjMoney = jySjMoney;
    }

    public long getMgrUserId() {
        return mgrUserId;
    }

    public void setMgrUserId(long mgrUserId) {
        this.mgrUserId = mgrUserId;
    }

    public String getMgrIp() {
        return mgrIp;
    }

    public void setMgrIp(String mgrIp) {
        this.mgrIp = mgrIp;
    }

    public Date getJyDate() {
        return jyDate;
    }

    public void setJyDate(Date jyDate) {
        this.jyDate = jyDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public byte getJyState() {
        return jyState;
    }

    public void setJyState(byte jyState) {
        this.jyState = jyState;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserMoney that = (UserMoney) o;

        if (moneyId != that.moneyId) return false;
        if (userId != that.userId) return false;
        if (jyType != that.jyType) return false;
        if (orderId != that.orderId) return false;
        if (mgrUserId != that.mgrUserId) return false;
        if (jyState != that.jyState) return false;
        if (jyMc != null ? !jyMc.equals(that.jyMc) : that.jyMc != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (jyMoney != null ? !jyMoney.equals(that.jyMoney) : that.jyMoney != null) return false;
        if (jySjMoney != null ? !jySjMoney.equals(that.jySjMoney) : that.jySjMoney != null) return false;
        if (mgrIp != null ? !mgrIp.equals(that.mgrIp) : that.mgrIp != null) return false;
        if (jyDate != null ? !jyDate.equals(that.jyDate) : that.jyDate != null) return false;
        if (completeDate != null ? !completeDate.equals(that.completeDate) : that.completeDate != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (moneyId ^ (moneyId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) jyType;
        result = 31 * result + (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (jyMc != null ? jyMc.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (jyMoney != null ? jyMoney.hashCode() : 0);
        result = 31 * result + (jySjMoney != null ? jySjMoney.hashCode() : 0);
        result = 31 * result + (int) (mgrUserId ^ (mgrUserId >>> 32));
        result = 31 * result + (mgrIp != null ? mgrIp.hashCode() : 0);
        result = 31 * result + (jyDate != null ? jyDate.hashCode() : 0);
        result = 31 * result + (completeDate != null ? completeDate.hashCode() : 0);
        result = 31 * result + (int) jyState;
        return result;
    }
}
