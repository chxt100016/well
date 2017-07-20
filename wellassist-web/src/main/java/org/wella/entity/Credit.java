package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Credit {
    private Long creditId;

    private Long userId;

    private BigDecimal creditMoney;

    private Date creditPaymentDays;

    private Date creditDeadline;

    private Date creditApplyDate;

    private Date creditLoanDate;

    private Long creditUserId;

    private BigDecimal creditSjMoney;

    private BigDecimal lixiRate;

    private BigDecimal retHkMoney;

    private BigDecimal remainHkMoney;

    private BigDecimal lixiMoney;

    private BigDecimal remainLxMoney;

    private BigDecimal retLxMoney;

    private Byte creditState;

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

    public BigDecimal getCreditMoney() {
        return creditMoney;
    }

    public void setCreditMoney(BigDecimal creditMoney) {
        this.creditMoney = creditMoney;
    }

    public Date getCreditPaymentDays() {
        return creditPaymentDays;
    }

    public void setCreditPaymentDays(Date creditPaymentDays) {
        this.creditPaymentDays = creditPaymentDays;
    }

    public Date getCreditDeadline() {
        return creditDeadline;
    }

    public void setCreditDeadline(Date creditDeadline) {
        this.creditDeadline = creditDeadline;
    }

    public Date getCreditApplyDate() {
        return creditApplyDate;
    }

    public void setCreditApplyDate(Date creditApplyDate) {
        this.creditApplyDate = creditApplyDate;
    }

    public Date getCreditLoanDate() {
        return creditLoanDate;
    }

    public void setCreditLoanDate(Date creditLoanDate) {
        this.creditLoanDate = creditLoanDate;
    }

    public Long getCreditUserId() {
        return creditUserId;
    }

    public void setCreditUserId(Long creditUserId) {
        this.creditUserId = creditUserId;
    }

    public BigDecimal getCreditSjMoney() {
        return creditSjMoney;
    }

    public void setCreditSjMoney(BigDecimal creditSjMoney) {
        this.creditSjMoney = creditSjMoney;
    }

    public BigDecimal getLixiRate() {
        return lixiRate;
    }

    public void setLixiRate(BigDecimal lixiRate) {
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

    public Byte getCreditState() {
        return creditState;
    }

    public void setCreditState(Byte creditState) {
        this.creditState = creditState;
    }
}