package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/3.
 */
public class Profit {

    private Integer id;

    private Integer companyId;

    private BigDecimal businessIncome;

    private BigDecimal operatingCosts;

    private BigDecimal operatingTaxes;

    private BigDecimal sellingExpenses;

    private BigDecimal managementExpenses;

    private BigDecimal financialExpenses;

    private BigDecimal assetsImpairmentLoss;

    private BigDecimal variableIncome;

    private BigDecimal incomeInvestment;

    private BigDecimal inVjv;

    private BigDecimal operatingProfit;

    private BigDecimal nonOperatingIncome;

    private BigDecimal nonOperatingExpenses;

    private BigDecimal ncaDisposalLoss;

    private BigDecimal totalProfit;

    private BigDecimal incomeTaxExpense;

    private BigDecimal netProfit;

    private BigDecimal basicEps;

    private BigDecimal dilutedEps;

    private Byte type;

    private Date createTime;

    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public BigDecimal getBusinessIncome() {
        return businessIncome;
    }

    public void setBusinessIncome(BigDecimal businessIncome) {
        this.businessIncome = businessIncome;
    }

    public BigDecimal getOperatingCosts() {
        return operatingCosts;
    }

    public void setOperatingCosts(BigDecimal operatingCosts) {
        this.operatingCosts = operatingCosts;
    }

    public BigDecimal getOperatingTaxes() {
        return operatingTaxes;
    }

    public void setOperatingTaxes(BigDecimal operatingTaxes) {
        this.operatingTaxes = operatingTaxes;
    }

    public BigDecimal getSellingExpenses() {
        return sellingExpenses;
    }

    public void setSellingExpenses(BigDecimal sellingExpenses) {
        this.sellingExpenses = sellingExpenses;
    }

    public BigDecimal getManagementExpenses() {
        return managementExpenses;
    }

    public void setManagementExpenses(BigDecimal managementExpenses) {
        this.managementExpenses = managementExpenses;
    }

    public BigDecimal getFinancialExpenses() {
        return financialExpenses;
    }

    public void setFinancialExpenses(BigDecimal financialExpenses) {
        this.financialExpenses = financialExpenses;
    }

    public BigDecimal getAssetsImpairmentLoss() {
        return assetsImpairmentLoss;
    }

    public void setAssetsImpairmentLoss(BigDecimal assetsImpairmentLoss) {
        this.assetsImpairmentLoss = assetsImpairmentLoss;
    }

    public BigDecimal getVariableIncome() {
        return variableIncome;
    }

    public void setVariableIncome(BigDecimal variableIncome) {
        this.variableIncome = variableIncome;
    }

    public BigDecimal getIncomeInvestment() {
        return incomeInvestment;
    }

    public void setIncomeInvestment(BigDecimal incomeInvestment) {
        this.incomeInvestment = incomeInvestment;
    }

    public BigDecimal getInVjv() {
        return inVjv;
    }

    public void setInVjv(BigDecimal inVjv) {
        this.inVjv = inVjv;
    }

    public BigDecimal getOperatingProfit() {
        return operatingProfit;
    }

    public void setOperatingProfit(BigDecimal operatingProfit) {
        this.operatingProfit = operatingProfit;
    }

    public BigDecimal getNonOperatingIncome() {
        return nonOperatingIncome;
    }

    public void setNonOperatingIncome(BigDecimal nonOperatingIncome) {
        this.nonOperatingIncome = nonOperatingIncome;
    }

    public BigDecimal getNonOperatingExpenses() {
        return nonOperatingExpenses;
    }

    public void setNonOperatingExpenses(BigDecimal nonOperatingExpenses) {
        this.nonOperatingExpenses = nonOperatingExpenses;
    }

    public BigDecimal getNcaDisposalLoss() {
        return ncaDisposalLoss;
    }

    public void setNcaDisposalLoss(BigDecimal ncaDisposalLoss) {
        this.ncaDisposalLoss = ncaDisposalLoss;
    }

    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }

    public BigDecimal getIncomeTaxExpense() {
        return incomeTaxExpense;
    }

    public void setIncomeTaxExpense(BigDecimal incomeTaxExpense) {
        this.incomeTaxExpense = incomeTaxExpense;
    }

    public BigDecimal getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(BigDecimal netProfit) {
        this.netProfit = netProfit;
    }

    public BigDecimal getBasicEps() {
        return basicEps;
    }

    public void setBasicEps(BigDecimal basicEps) {
        this.basicEps = basicEps;
    }

    public BigDecimal getDilutedEps() {
        return dilutedEps;
    }

    public void setDilutedEps(BigDecimal dilutedEps) {
        this.dilutedEps = dilutedEps;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }



}
