package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Loan {
    private Long loanId;

    private Long moneyId;

    private Long creditId;

    private Long userId;

    private Long creditUserId;

    private BigDecimal loanInterestMoney;

    private Date applyDate;

    private Date loanDate;

    private Date interestFreeDate;

    private Date paymentDate;

    private BigDecimal lixiRate;

    private BigDecimal lixiRateFkf;

    private BigDecimal loanMoney;

    private BigDecimal repayMoney;

    private BigDecimal remainRepayMoney;

    private BigDecimal lixiMoneyFkf;

    private BigDecimal overdueFine;

    private BigDecimal repayOverdueFine;

    private BigDecimal remainOverdueFine;

    private BigDecimal lixiMoney;

    private BigDecimal repayLixi;

    private BigDecimal remainLixiMoney;

    private Byte loanState;

    private String content;

    private String loanIp;

    private Byte loanType;

    private BigDecimal settleMoney;

    private BigDecimal profit;

    private BigDecimal profitRate;

    private Date settleDate;

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Long getMoneyId() {
        return moneyId;
    }

    public void setMoneyId(Long moneyId) {
        this.moneyId = moneyId;
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

    public Long getCreditUserId() {
        return creditUserId;
    }

    public void setCreditUserId(Long creditUserId) {
        this.creditUserId = creditUserId;
    }

    public BigDecimal getLoanInterestMoney() {
        return loanInterestMoney;
    }

    public void setLoanInterestMoney(BigDecimal loanInterestMoney) {
        this.loanInterestMoney = loanInterestMoney;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getInterestFreeDate() {
        return interestFreeDate;
    }

    public void setInterestFreeDate(Date interestFreeDate) {
        this.interestFreeDate = interestFreeDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getLixiRate() {
        return lixiRate;
    }

    public void setLixiRate(BigDecimal lixiRate) {
        this.lixiRate = lixiRate;
    }

    public BigDecimal getLixiRateFkf() {
        return lixiRateFkf;
    }

    public void setLixiRateFkf(BigDecimal lixiRateFkf) {
        this.lixiRateFkf = lixiRateFkf;
    }

    public BigDecimal getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(BigDecimal loanMoney) {
        this.loanMoney = loanMoney;
    }

    public BigDecimal getRepayMoney() {
        return repayMoney;
    }

    public void setRepayMoney(BigDecimal repayMoney) {
        this.repayMoney = repayMoney;
    }

    public BigDecimal getRemainRepayMoney() {
        return remainRepayMoney;
    }

    public void setRemainRepayMoney(BigDecimal remainRepayMoney) {
        this.remainRepayMoney = remainRepayMoney;
    }

    public BigDecimal getLixiMoneyFkf() {
        return lixiMoneyFkf;
    }

    public void setLixiMoneyFkf(BigDecimal lixiMoneyFkf) {
        this.lixiMoneyFkf = lixiMoneyFkf;
    }

    public BigDecimal getOverdueFine() {
        return overdueFine;
    }

    public void setOverdueFine(BigDecimal overdueFine) {
        this.overdueFine = overdueFine;
    }

    public BigDecimal getRepayOverdueFine() {
        return repayOverdueFine;
    }

    public void setRepayOverdueFine(BigDecimal repayOverdueFine) {
        this.repayOverdueFine = repayOverdueFine;
    }

    public BigDecimal getRemainOverdueFine() {
        return remainOverdueFine;
    }

    public void setRemainOverdueFine(BigDecimal remainOverdueFine) {
        this.remainOverdueFine = remainOverdueFine;
    }

    public BigDecimal getLixiMoney() {
        return lixiMoney;
    }

    public void setLixiMoney(BigDecimal lixiMoney) {
        this.lixiMoney = lixiMoney;
    }

    public BigDecimal getRepayLixi() {
        return repayLixi;
    }

    public void setRepayLixi(BigDecimal repayLixi) {
        this.repayLixi = repayLixi;
    }

    public BigDecimal getRemainLixiMoney() {
        return remainLixiMoney;
    }

    public void setRemainLixiMoney(BigDecimal remainLixiMoney) {
        this.remainLixiMoney = remainLixiMoney;
    }

    public Byte getLoanState() {
        return loanState;
    }

    public void setLoanState(Byte loanState) {
        this.loanState = loanState;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getLoanIp() {
        return loanIp;
    }

    public void setLoanIp(String loanIp) {
        this.loanIp = loanIp == null ? null : loanIp.trim();
    }

    public Byte getLoanType() {
        return loanType;
    }

    public void setLoanType(Byte loanType) {
        this.loanType = loanType;
    }

    public BigDecimal getSettleMoney() {
        return settleMoney;
    }

    public void setSettleMoney(BigDecimal settleMoney) {
        this.settleMoney = settleMoney;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }
}