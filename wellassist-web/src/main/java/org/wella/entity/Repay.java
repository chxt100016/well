package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Repay {
    private Long repayId;

    private Long moneyId;

    private Long loanId;

    private Long creditId;

    private Long userId;

    private BigDecimal repayMoney;

    private BigDecimal repayOverdueFine;

    private BigDecimal repayInterestMoney;

    private Date repayDate;

    private String repayIp;

    private String content;

    private Byte repayType;

    public Long getRepayId() {
        return repayId;
    }

    public void setRepayId(Long repayId) {
        this.repayId = repayId;
    }

    public Long getMoneyId() {
        return moneyId;
    }

    public void setMoneyId(Long moneyId) {
        this.moneyId = moneyId;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Long getCreditId() {
        return creditId;
    }

    public void setCreditId(Long creditId) {
        this.creditId = creditId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getRepayMoney() {
        return repayMoney;
    }

    public void setRepayMoney(BigDecimal repayMoney) {
        this.repayMoney = repayMoney;
    }

    public BigDecimal getRepayInterestMoney() {
        return repayInterestMoney;
    }

    public BigDecimal getRepayOverdueFine() {
        return repayOverdueFine;
    }

    public void setRepayOverdueFine(BigDecimal repayOverdueFine) {
        this.repayOverdueFine = repayOverdueFine;
    }

    public void setRepayInterestMoney(BigDecimal repayInterestMoney) {
        this.repayInterestMoney = repayInterestMoney;
    }

    public Date getRepayDate() {
        return repayDate;
    }

    public void setRepayDate(Date repayDate) {
        this.repayDate = repayDate;
    }

    public String getRepayIp() {
        return repayIp;
    }

    public void setRepayIp(String repayIp) {
        this.repayIp = repayIp == null ? null : repayIp.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Byte getRepayType() {
        return repayType;
    }

    public void setRepayType(Byte repayType) {
        this.repayType = repayType;
    }
}