package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CompanyProperty {
    private Long id;

    private BigDecimal assetLiabilityRatio;

    private BigDecimal liquidityRatio;

    private BigDecimal quickRatio;

    private BigDecimal interestCoverRatio;

    private BigDecimal cashFlow;

    private BigDecimal salesRevenue;

    private BigDecimal totalAssets;

    private BigDecimal totalLiabilities;

    private BigDecimal loadRatio;

    private BigDecimal externalGuarantees;

    private String creditCode;

    private Date period;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAssetLiabilityRatio() {
        return assetLiabilityRatio;
    }

    public void setAssetLiabilityRatio(BigDecimal assetLiabilityRatio) {
        this.assetLiabilityRatio = assetLiabilityRatio;
    }

    public BigDecimal getLiquidityRatio() {
        return liquidityRatio;
    }

    public void setLiquidityRatio(BigDecimal liquidityRatio) {
        this.liquidityRatio = liquidityRatio;
    }

    public BigDecimal getQuickRatio() {
        return quickRatio;
    }

    public void setQuickRatio(BigDecimal quickRatio) {
        this.quickRatio = quickRatio;
    }

    public BigDecimal getInterestCoverRatio() {
        return interestCoverRatio;
    }

    public void setInterestCoverRatio(BigDecimal interestCoverRatio) {
        this.interestCoverRatio = interestCoverRatio;
    }

    public BigDecimal getCashFlow() {
        return cashFlow;
    }

    public void setCashFlow(BigDecimal cashFlow) {
        this.cashFlow = cashFlow;
    }

    public BigDecimal getSalesRevenue() {
        return salesRevenue;
    }

    public void setSalesRevenue(BigDecimal salesRevenue) {
        this.salesRevenue = salesRevenue;
    }

    public BigDecimal getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(BigDecimal totalAssets) {
        this.totalAssets = totalAssets;
    }

    public BigDecimal getTotalLiabilities() {
        return totalLiabilities;
    }

    public void setTotalLiabilities(BigDecimal totalLiabilities) {
        this.totalLiabilities = totalLiabilities;
    }

    public BigDecimal getLoadRatio() {
        return loadRatio;
    }

    public void setLoadRatio(BigDecimal loadRatio) {
        this.loadRatio = loadRatio;
    }

    public BigDecimal getExternalGuarantees() {
        return externalGuarantees;
    }

    public void setExternalGuarantees(BigDecimal externalGuarantees) {
        this.externalGuarantees = externalGuarantees;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode == null ? null : creditCode.trim();
    }

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }
}