package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class MoneyInfo {
    private long infoId;
    private long userId;
    private BigDecimal preMoney;
    private BigDecimal nextMoney;
    private BigDecimal diffMoney;
    private String reason;
    private Date infoDate;
    private String mgrIp;
    private long mgrUserId;
    private byte isCredit;

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

    public BigDecimal getPreMoney() {
        return preMoney;
    }

    public void setPreMoney(BigDecimal preMoney) {
        this.preMoney = preMoney;
    }

    public BigDecimal getNextMoney() {
        return nextMoney;
    }

    public void setNextMoney(BigDecimal nextMoney) {
        this.nextMoney = nextMoney;
    }

    public BigDecimal getDiffMoney() {
        return diffMoney;
    }

    public void setDiffMoney(BigDecimal diffMoney) {
        this.diffMoney = diffMoney;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getInfoDate() {
        return infoDate;
    }

    public void setInfoDate(Date infoDate) {
        this.infoDate = infoDate;
    }

    public String getMgrIp() {
        return mgrIp;
    }

    public void setMgrIp(String mgrIp) {
        this.mgrIp = mgrIp;
    }

    public long getMgrUserId() {
        return mgrUserId;
    }

    public void setMgrUserId(long mgrUserId) {
        this.mgrUserId = mgrUserId;
    }

    public byte getIsCredit() {
        return isCredit;
    }

    public void setIsCredit(byte isCredit) {
        this.isCredit = isCredit;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoneyInfo that = (MoneyInfo) o;

        if (infoId != that.infoId) return false;
        if (userId != that.userId) return false;
        if (mgrUserId != that.mgrUserId) return false;
        if (isCredit != that.isCredit) return false;
        if (preMoney != null ? !preMoney.equals(that.preMoney) : that.preMoney != null) return false;
        if (nextMoney != null ? !nextMoney.equals(that.nextMoney) : that.nextMoney != null) return false;
        if (diffMoney != null ? !diffMoney.equals(that.diffMoney) : that.diffMoney != null) return false;
        if (reason != null ? !reason.equals(that.reason) : that.reason != null) return false;
        if (infoDate != null ? !infoDate.equals(that.infoDate) : that.infoDate != null) return false;
        if (mgrIp != null ? !mgrIp.equals(that.mgrIp) : that.mgrIp != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (infoId ^ (infoId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (preMoney != null ? preMoney.hashCode() : 0);
        result = 31 * result + (nextMoney != null ? nextMoney.hashCode() : 0);
        result = 31 * result + (diffMoney != null ? diffMoney.hashCode() : 0);
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        result = 31 * result + (infoDate != null ? infoDate.hashCode() : 0);
        result = 31 * result + (mgrIp != null ? mgrIp.hashCode() : 0);
        result = 31 * result + (int) (mgrUserId ^ (mgrUserId >>> 32));
        result = 31 * result + (int) isCredit;
        return result;
    }
}
