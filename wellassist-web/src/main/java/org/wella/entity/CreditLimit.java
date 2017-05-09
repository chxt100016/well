package org.wella.entity;

import java.math.BigDecimal;

/**
 * Created by liuwen on 2017/5/9.
 */
public class CreditLimit {
    private long limitId;
    private String limitMc;
    private BigDecimal limitRate;
    private BigDecimal limitMoney;
    private BigDecimal limitPer;

    public long getLimitId() {
        return limitId;
    }

    public void setLimitId(long limitId) {
        this.limitId = limitId;
    }

    public String getLimitMc() {
        return limitMc;
    }

    public void setLimitMc(String limitMc) {
        this.limitMc = limitMc;
    }

    public BigDecimal getLimitRate() {
        return limitRate;
    }

    public void setLimitRate(BigDecimal limitRate) {
        this.limitRate = limitRate;
    }

    public BigDecimal getLimitMoney() {
        return limitMoney;
    }

    public void setLimitMoney(BigDecimal limitMoney) {
        this.limitMoney = limitMoney;
    }

    public BigDecimal getLimitPer() {
        return limitPer;
    }

    public void setLimitPer(BigDecimal limitPer) {
        this.limitPer = limitPer;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditLimit that = (CreditLimit) o;

        if (limitId != that.limitId) return false;
        if (limitMc != null ? !limitMc.equals(that.limitMc) : that.limitMc != null) return false;
        if (limitRate != null ? !limitRate.equals(that.limitRate) : that.limitRate != null) return false;
        if (limitMoney != null ? !limitMoney.equals(that.limitMoney) : that.limitMoney != null) return false;
        if (limitPer != null ? !limitPer.equals(that.limitPer) : that.limitPer != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (limitId ^ (limitId >>> 32));
        result = 31 * result + (limitMc != null ? limitMc.hashCode() : 0);
        result = 31 * result + (limitRate != null ? limitRate.hashCode() : 0);
        result = 31 * result + (limitMoney != null ? limitMoney.hashCode() : 0);
        result = 31 * result + (limitPer != null ? limitPer.hashCode() : 0);
        return result;
    }
}
