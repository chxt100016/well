package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CompanyManagementinfo {
    private Long id;

    private BigDecimal salesGrowthRation;

    private BigDecimal totalProfit;

    private BigDecimal profitRateOfAsset;

    private BigDecimal inventoryTurnover;

    private BigDecimal effectiveNeWorth;

    private BigDecimal tangiblePlantAssets;

    private BigDecimal revenues;

    private String creditCode;

    private Date period;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSalesGrowthRation() {
        return salesGrowthRation;
    }

    public void setSalesGrowthRation(BigDecimal salesGrowthRation) {
        this.salesGrowthRation = salesGrowthRation;
    }

    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }

    public BigDecimal getProfitRateOfAsset() {
        return profitRateOfAsset;
    }

    public void setProfitRateOfAsset(BigDecimal profitRateOfAsset) {
        this.profitRateOfAsset = profitRateOfAsset;
    }

    public BigDecimal getInventoryTurnover() {
        return inventoryTurnover;
    }

    public void setInventoryTurnover(BigDecimal inventoryTurnover) {
        this.inventoryTurnover = inventoryTurnover;
    }

    public BigDecimal getEffectiveNeWorth() {
        return effectiveNeWorth;
    }

    public void setEffectiveNeWorth(BigDecimal effectiveNeWorth) {
        this.effectiveNeWorth = effectiveNeWorth;
    }

    public BigDecimal getTangiblePlantAssets() {
        return tangiblePlantAssets;
    }

    public void setTangiblePlantAssets(BigDecimal tangiblePlantAssets) {
        this.tangiblePlantAssets = tangiblePlantAssets;
    }

    public BigDecimal getRevenues() {
        return revenues;
    }

    public void setRevenues(BigDecimal revenues) {
        this.revenues = revenues;
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