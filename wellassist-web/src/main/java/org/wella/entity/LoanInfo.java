package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class LoanInfo {
    private long infoId;
    private long moneyId;
    private long creditId;
    private long userId;
    private BigDecimal loanMoney;
    private BigDecimal loanLixiMoney;
    private Date loanDate;
    private String loanIp;
    private String content;

    public long getInfoId() {
        return infoId;
    }

    public void setInfoId(long infoId) {
        this.infoId = infoId;
    }

    public long getMoneyId() {
        return moneyId;
    }

    public void setMoneyId(long moneyId) {
        this.moneyId = moneyId;
    }

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

    public BigDecimal getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(BigDecimal loanMoney) {
        this.loanMoney = loanMoney;
    }

    public BigDecimal getLoanLixiMoney() {
        return loanLixiMoney;
    }

    public void setLoanLixiMoney(BigDecimal loanLixiMoney) {
        this.loanLixiMoney = loanLixiMoney;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public String getLoanIp() {
        return loanIp;
    }

    public void setLoanIp(String loanIp) {
        this.loanIp = loanIp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoanInfo that = (LoanInfo) o;

        if (infoId != that.infoId) return false;
        if (moneyId != that.moneyId) return false;
        if (creditId != that.creditId) return false;
        if (userId != that.userId) return false;
        if (loanMoney != null ? !loanMoney.equals(that.loanMoney) : that.loanMoney != null) return false;
        if (loanLixiMoney != null ? !loanLixiMoney.equals(that.loanLixiMoney) : that.loanLixiMoney != null)
            return false;
        if (loanDate != null ? !loanDate.equals(that.loanDate) : that.loanDate != null) return false;
        if (loanIp != null ? !loanIp.equals(that.loanIp) : that.loanIp != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (infoId ^ (infoId >>> 32));
        result = 31 * result + (int) (moneyId ^ (moneyId >>> 32));
        result = 31 * result + (int) (creditId ^ (creditId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (loanMoney != null ? loanMoney.hashCode() : 0);
        result = 31 * result + (loanLixiMoney != null ? loanLixiMoney.hashCode() : 0);
        result = 31 * result + (loanDate != null ? loanDate.hashCode() : 0);
        result = 31 * result + (loanIp != null ? loanIp.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
