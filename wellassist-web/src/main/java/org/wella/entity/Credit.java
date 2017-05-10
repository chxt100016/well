package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class Credit {
    private long creditId;
    private long userId;
    private BigDecimal creditMoney;
    private Date creditOverDate;
    private Date creditSqDate;
    private Date creditDate;
    private long creditUserId;
    private BigDecimal creditSjMoney;
    private int lixiRate;
    private BigDecimal retHkMoney;
    private BigDecimal remainHkMoney;
    private BigDecimal lixiMoney;
    private BigDecimal remainLxMoney;
    private BigDecimal retLxMoney;
    private byte creditState;

    public long getCreditId() {
        return creditId;
    }

    public void setCreditId(long creditId) {
        this.creditId = creditId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public BigDecimal getCreditMoney() {
        return creditMoney;
    }

    public void setCreditMoney(BigDecimal creditMoney) {
        this.creditMoney = creditMoney;
    }

    public Date getCreditOverDate() {
        return creditOverDate;
    }

    public void setCreditOverDate(Date creditOverDate) {
        this.creditOverDate = creditOverDate;
    }

    public Date getCreditSqDate() {
        return creditSqDate;
    }

    public void setCreditSqDate(Date creditSqDate) {
        this.creditSqDate = creditSqDate;
    }

    public Date getCreditDate() {
        return creditDate;
    }

    public void setCreditDate(Date creditDate) {
        this.creditDate = creditDate;
    }

    public long getCreditUserId() {
        return creditUserId;
    }

    public void setCreditUserId(long creditUserId) {
        this.creditUserId = creditUserId;
    }

    public BigDecimal getCreditSjMoney() {
        return creditSjMoney;
    }

    public void setCreditSjMoney(BigDecimal creditSjMoney) {
        this.creditSjMoney = creditSjMoney;
    }

    public int getLixiRate() {
        return lixiRate;
    }

    public void setLixiRate(int lixiRate) {
        this.lixiRate = lixiRate;
    }

    public BigDecimal getRetHkMoney() {
        return retHkMoney;
    }

    public void setRetHkMoney(BigDecimal retHkMoney) {
        this.retHkMoney = retHkMoney;
    }

    public BigDecimal getRemainHkMoney() {
        return remainHkMoney;
    }

    public void setRemainHkMoney(BigDecimal remainHkMoney) {
        this.remainHkMoney = remainHkMoney;
    }

    public BigDecimal getLixiMoney() {
        return lixiMoney;
    }

    public void setLixiMoney(BigDecimal lixiMoney) {
        this.lixiMoney = lixiMoney;
    }

    public BigDecimal getRemainLxMoney() {
        return remainLxMoney;
    }

    public void setRemainLxMoney(BigDecimal remainLxMoney) {
        this.remainLxMoney = remainLxMoney;
    }

    public BigDecimal getRetLxMoney() {
        return retLxMoney;
    }

    public void setRetLxMoney(BigDecimal retLxMoney) {
        this.retLxMoney = retLxMoney;
    }

    public byte getCreditState() {
        return creditState;
    }

    public void setCreditState(byte creditState) {
        this.creditState = creditState;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Credit waCredit = (Credit) o;

        if (creditId != waCredit.creditId) return false;
        if (userId != waCredit.userId) return false;
        if (creditUserId != waCredit.creditUserId) return false;
        if (lixiRate != waCredit.lixiRate) return false;
        if (creditState != waCredit.creditState) return false;
        if (creditMoney != null ? !creditMoney.equals(waCredit.creditMoney) : waCredit.creditMoney != null)
            return false;
        if (creditOverDate != null ? !creditOverDate.equals(waCredit.creditOverDate) : waCredit.creditOverDate != null)
            return false;
        if (creditSqDate != null ? !creditSqDate.equals(waCredit.creditSqDate) : waCredit.creditSqDate != null)
            return false;
        if (creditDate != null ? !creditDate.equals(waCredit.creditDate) : waCredit.creditDate != null) return false;
        if (creditSjMoney != null ? !creditSjMoney.equals(waCredit.creditSjMoney) : waCredit.creditSjMoney != null)
            return false;
        if (retHkMoney != null ? !retHkMoney.equals(waCredit.retHkMoney) : waCredit.retHkMoney != null) return false;
        if (remainHkMoney != null ? !remainHkMoney.equals(waCredit.remainHkMoney) : waCredit.remainHkMoney != null)
            return false;
        if (lixiMoney != null ? !lixiMoney.equals(waCredit.lixiMoney) : waCredit.lixiMoney != null) return false;
        if (remainLxMoney != null ? !remainLxMoney.equals(waCredit.remainLxMoney) : waCredit.remainLxMoney != null)
            return false;
        if (retLxMoney != null ? !retLxMoney.equals(waCredit.retLxMoney) : waCredit.retLxMoney != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (creditId ^ (creditId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (creditMoney != null ? creditMoney.hashCode() : 0);
        result = 31 * result + (creditOverDate != null ? creditOverDate.hashCode() : 0);
        result = 31 * result + (creditSqDate != null ? creditSqDate.hashCode() : 0);
        result = 31 * result + (creditDate != null ? creditDate.hashCode() : 0);
        result = 31 * result + (int) (creditUserId ^ (creditUserId >>> 32));
        result = 31 * result + (creditSjMoney != null ? creditSjMoney.hashCode() : 0);
        result = 31 * result + lixiRate;
        result = 31 * result + (retHkMoney != null ? retHkMoney.hashCode() : 0);
        result = 31 * result + (remainHkMoney != null ? remainHkMoney.hashCode() : 0);
        result = 31 * result + (lixiMoney != null ? lixiMoney.hashCode() : 0);
        result = 31 * result + (remainLxMoney != null ? remainLxMoney.hashCode() : 0);
        result = 31 * result + (retLxMoney != null ? retLxMoney.hashCode() : 0);
        result = 31 * result + (int) creditState;
        return result;
    }
}
