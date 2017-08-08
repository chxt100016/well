package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/3.
 */
public class AssetsLiabilities {

    private Integer id;

    private Integer conpanyId;

    private BigDecimal caMonetaryFund;

    private BigDecimal caTransactionalAssets;

    private BigDecimal caBillsReceivable;

    private BigDecimal caAccountsReceivable;

    private BigDecimal caAdvancePayment;

    private BigDecimal caInterestReceivable;

    private BigDecimal caDividendReceivable;

    private BigDecimal caOtherReceivables;

    private BigDecimal caStock;

    private BigDecimal caExpireNca;

    private BigDecimal caOtherCurrentAssets;

    private BigDecimal caTotalCurrentAssets;

    private BigDecimal ncaAvailableAssets;

    private BigDecimal ncaHeidInvestment;

    private BigDecimal ncaLongReceivables;

    private BigDecimal ncaLongInvestment;

    private BigDecimal ncaInvestmentEstate;

    private BigDecimal ncaFixedAssets;

    private BigDecimal ncaProject;

    private BigDecimal ncaEngineeringMaterials;

    private BigDecimal ncaLiquidationFixedAssets;

    private BigDecimal ncaProductiveBiologicalAssets;

    private BigDecimal ncaOilGasAssets;

    private BigDecimal ncaIntangibleAssets;

    private BigDecimal ncaDevelopmentExpenditure;

    private BigDecimal ncaGoodwill;

    private BigDecimal ncaLongPrepaidExpenses;

    private BigDecimal ncaDeferredTaxAssets;

    private BigDecimal ncaOtherNoncurrentAssets;

    private BigDecimal ncaTotalNoncurrentAssets;

    private BigDecimal totalAssets;

    private BigDecimal clShortBorrowing;

    private BigDecimal clTransactionalLiabilities;

    private BigDecimal clBillsPayable;

    private BigDecimal clAccountsPayable;

    private BigDecimal clAdvanceMoney;

    private BigDecimal clPayrollPayable;

    private BigDecimal clTaxPayable;

    private BigDecimal clInterestPayable;

    private BigDecimal clDividendsPayable;

    private BigDecimal clOtherPayable;

    private BigDecimal clCurrentLiabilities;

    private BigDecimal clOtherCurrentLiabilities;

    private BigDecimal clTotalCurrentLiabilities;

    private BigDecimal nclLongBorrowing;

    private BigDecimal nclBondsPayable;

    private BigDecimal nclLongTermPayables;

    private BigDecimal nelSpecialPayable;

    private BigDecimal nelProjectedLiabilities;

    private BigDecimal nelDeferredTaxLiability;

    private BigDecimal nelOtherCurrentLiabilities;

    private BigDecimal nelTotalCurrentLiabilities;

    private BigDecimal totalLiabilities;

    private BigDecimal oeShareCapital;

    private BigDecimal oeCapitalSurplus;

    private BigDecimal oeLessStock;

    private BigDecimal oeSurplusReserve;

    private BigDecimal oeUndistributedProfit;

    private BigDecimal oeTotal;

    private BigDecimal total;

    private Byte type;

    private Date createTime;

    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConpanyId() {
        return conpanyId;
    }

    public void setConpanyId(Integer conpanyId) {
        this.conpanyId = conpanyId;
    }

    public BigDecimal getCaMonetaryFund() {
        return caMonetaryFund;
    }

    public void setCaMonetaryFund(BigDecimal caMonetaryFund) {
        this.caMonetaryFund = caMonetaryFund;
    }

    public BigDecimal getCaTransactionalAssets() {
        return caTransactionalAssets;
    }

    public void setCaTransactionalAssets(BigDecimal caTransactionalAssets) {
        this.caTransactionalAssets = caTransactionalAssets;
    }

    public BigDecimal getCaBillsReceivable() {
        return caBillsReceivable;
    }

    public void setCaBillsReceivable(BigDecimal caBillsReceivable) {
        this.caBillsReceivable = caBillsReceivable;
    }

    public BigDecimal getCaAccountsReceivable() {
        return caAccountsReceivable;
    }

    public void setCaAccountsReceivable(BigDecimal caAccountsReceivable) {
        this.caAccountsReceivable = caAccountsReceivable;
    }

    public BigDecimal getCaAdvancePayment() {
        return caAdvancePayment;
    }

    public void setCaAdvancePayment(BigDecimal caAdvancePayment) {
        this.caAdvancePayment = caAdvancePayment;
    }

    public BigDecimal getCaInterestReceivable() {
        return caInterestReceivable;
    }

    public void setCaInterestReceivable(BigDecimal caInterestReceivable) {
        this.caInterestReceivable = caInterestReceivable;
    }

    public BigDecimal getCaDividendReceivable() {
        return caDividendReceivable;
    }

    public void setCaDividendReceivable(BigDecimal caDividendReceivable) {
        this.caDividendReceivable = caDividendReceivable;
    }

    public BigDecimal getCaOtherReceivables() {
        return caOtherReceivables;
    }

    public void setCaOtherReceivables(BigDecimal caOtherReceivables) {
        this.caOtherReceivables = caOtherReceivables;
    }

    public BigDecimal getCaStock() {
        return caStock;
    }

    public void setCaStock(BigDecimal caStock) {
        this.caStock = caStock;
    }

    public BigDecimal getCaExpireNca() {
        return caExpireNca;
    }

    public void setCaExpireNca(BigDecimal caExpireNca) {
        this.caExpireNca = caExpireNca;
    }

    public BigDecimal getCaOtherCurrentAssets() {
        return caOtherCurrentAssets;
    }

    public void setCaOtherCurrentAssets(BigDecimal caOtherCurrentAssets) {
        this.caOtherCurrentAssets = caOtherCurrentAssets;
    }

    public BigDecimal getCaTotalCurrentAssets() {
        return caTotalCurrentAssets;
    }

    public void setCaTotalCurrentAssets(BigDecimal caTotalCurrentAssets) {
        this.caTotalCurrentAssets = caTotalCurrentAssets;
    }

    public BigDecimal getNcaAvailableAssets() {
        return ncaAvailableAssets;
    }

    public void setNcaAvailableAssets(BigDecimal ncaAvailableAssets) {
        this.ncaAvailableAssets = ncaAvailableAssets;
    }

    public BigDecimal getNcaHeidInvestment() {
        return ncaHeidInvestment;
    }

    public void setNcaHeidInvestment(BigDecimal ncaHeidInvestment) {
        this.ncaHeidInvestment = ncaHeidInvestment;
    }

    public BigDecimal getNcaLongReceivables() {
        return ncaLongReceivables;
    }

    public void setNcaLongReceivables(BigDecimal ncaLongReceivables) {
        this.ncaLongReceivables = ncaLongReceivables;
    }

    public BigDecimal getNcaLongInvestment() {
        return ncaLongInvestment;
    }

    public void setNcaLongInvestment(BigDecimal ncaLongInvestment) {
        this.ncaLongInvestment = ncaLongInvestment;
    }

    public BigDecimal getNcaInvestmentEstate() {
        return ncaInvestmentEstate;
    }

    public void setNcaInvestmentEstate(BigDecimal ncaInvestmentEstate) {
        this.ncaInvestmentEstate = ncaInvestmentEstate;
    }

    public BigDecimal getNcaFixedAssets() {
        return ncaFixedAssets;
    }

    public void setNcaFixedAssets(BigDecimal ncaFixedAssets) {
        this.ncaFixedAssets = ncaFixedAssets;
    }

    public BigDecimal getNcaProject() {
        return ncaProject;
    }

    public void setNcaProject(BigDecimal ncaProject) {
        this.ncaProject = ncaProject;
    }

    public BigDecimal getNcaEngineeringMaterials() {
        return ncaEngineeringMaterials;
    }

    public void setNcaEngineeringMaterials(BigDecimal ncaEngineeringMaterials) {
        this.ncaEngineeringMaterials = ncaEngineeringMaterials;
    }

    public BigDecimal getNcaLiquidationFixedAssets() {
        return ncaLiquidationFixedAssets;
    }

    public void setNcaLiquidationFixedAssets(BigDecimal ncaLiquidationFixedAssets) {
        this.ncaLiquidationFixedAssets = ncaLiquidationFixedAssets;
    }

    public BigDecimal getNcaProductiveBiologicalAssets() {
        return ncaProductiveBiologicalAssets;
    }

    public void setNcaProductiveBiologicalAssets(BigDecimal ncaProductiveBiologicalAssets) {
        this.ncaProductiveBiologicalAssets = ncaProductiveBiologicalAssets;
    }

    public BigDecimal getNcaOilGasAssets() {
        return ncaOilGasAssets;
    }

    public void setNcaOilGasAssets(BigDecimal ncaOilGasAssets) {
        this.ncaOilGasAssets = ncaOilGasAssets;
    }

    public BigDecimal getNcaIntangibleAssets() {
        return ncaIntangibleAssets;
    }

    public void setNcaIntangibleAssets(BigDecimal ncaIntangibleAssets) {
        this.ncaIntangibleAssets = ncaIntangibleAssets;
    }

    public BigDecimal getNcaDevelopmentExpenditure() {
        return ncaDevelopmentExpenditure;
    }

    public void setNcaDevelopmentExpenditure(BigDecimal ncaDevelopmentExpenditure) {
        this.ncaDevelopmentExpenditure = ncaDevelopmentExpenditure;
    }

    public BigDecimal getNcaGoodwill() {
        return ncaGoodwill;
    }

    public void setNcaGoodwill(BigDecimal ncaGoodwill) {
        this.ncaGoodwill = ncaGoodwill;
    }

    public BigDecimal getNcaLongPrepaidExpenses() {
        return ncaLongPrepaidExpenses;
    }

    public void setNcaLongPrepaidExpenses(BigDecimal ncaLongPrepaidExpenses) {
        this.ncaLongPrepaidExpenses = ncaLongPrepaidExpenses;
    }

    public BigDecimal getNcaDeferredTaxAssets() {
        return ncaDeferredTaxAssets;
    }

    public void setNcaDeferredTaxAssets(BigDecimal ncaDeferredTaxAssets) {
        this.ncaDeferredTaxAssets = ncaDeferredTaxAssets;
    }

    public BigDecimal getNcaOtherNoncurrentAssets() {
        return ncaOtherNoncurrentAssets;
    }

    public void setNcaOtherNoncurrentAssets(BigDecimal ncaOtherNoncurrentAssets) {
        this.ncaOtherNoncurrentAssets = ncaOtherNoncurrentAssets;
    }

    public BigDecimal getNcaTotalNoncurrentAssets() {
        return ncaTotalNoncurrentAssets;
    }

    public void setNcaTotalNoncurrentAssets(BigDecimal ncaTotalNoncurrentAssets) {
        this.ncaTotalNoncurrentAssets = ncaTotalNoncurrentAssets;
    }

    public BigDecimal getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(BigDecimal totalAssets) {
        this.totalAssets = totalAssets;
    }

    public BigDecimal getClShortBorrowing() {
        return clShortBorrowing;
    }

    public void setClShortBorrowing(BigDecimal clShortBorrowing) {
        this.clShortBorrowing = clShortBorrowing;
    }

    public BigDecimal getClTransactionalLiabilities() {
        return clTransactionalLiabilities;
    }

    public void setClTransactionalLiabilities(BigDecimal clTransactionalLiabilities) {
        this.clTransactionalLiabilities = clTransactionalLiabilities;
    }

    public BigDecimal getClBillsPayable() {
        return clBillsPayable;
    }

    public void setClBillsPayable(BigDecimal clBillsPayable) {
        this.clBillsPayable = clBillsPayable;
    }

    public BigDecimal getClAccountsPayable() {
        return clAccountsPayable;
    }

    public void setClAccountsPayable(BigDecimal clAccountsPayable) {
        this.clAccountsPayable = clAccountsPayable;
    }

    public BigDecimal getClAdvanceMoney() {
        return clAdvanceMoney;
    }

    public void setClAdvanceMoney(BigDecimal clAdvanceMoney) {
        this.clAdvanceMoney = clAdvanceMoney;
    }

    public BigDecimal getClPayrollPayable() {
        return clPayrollPayable;
    }

    public void setClPayrollPayable(BigDecimal clPayrollPayable) {
        this.clPayrollPayable = clPayrollPayable;
    }

    public BigDecimal getClTaxPayable() {
        return clTaxPayable;
    }

    public void setClTaxPayable(BigDecimal clTaxPayable) {
        this.clTaxPayable = clTaxPayable;
    }

    public BigDecimal getClInterestPayable() {
        return clInterestPayable;
    }

    public void setClInterestPayable(BigDecimal clInterestPayable) {
        this.clInterestPayable = clInterestPayable;
    }

    public BigDecimal getClDividendsPayable() {
        return clDividendsPayable;
    }

    public void setClDividendsPayable(BigDecimal clDividendsPayable) {
        this.clDividendsPayable = clDividendsPayable;
    }

    public BigDecimal getClOtherPayable() {
        return clOtherPayable;
    }

    public void setClOtherPayable(BigDecimal clOtherPayable) {
        this.clOtherPayable = clOtherPayable;
    }

    public BigDecimal getClCurrentLiabilities() {
        return clCurrentLiabilities;
    }

    public void setClCurrentLiabilities(BigDecimal clCurrentLiabilities) {
        this.clCurrentLiabilities = clCurrentLiabilities;
    }

    public BigDecimal getClOtherCurrentLiabilities() {
        return clOtherCurrentLiabilities;
    }

    public void setClOtherCurrentLiabilities(BigDecimal clOtherCurrentLiabilities) {
        this.clOtherCurrentLiabilities = clOtherCurrentLiabilities;
    }

    public BigDecimal getClTotalCurrentLiabilities() {
        return clTotalCurrentLiabilities;
    }

    public void setClTotalCurrentLiabilities(BigDecimal clTotalCurrentLiabilities) {
        this.clTotalCurrentLiabilities = clTotalCurrentLiabilities;
    }

    public BigDecimal getNclLongBorrowing() {
        return nclLongBorrowing;
    }

    public void setNclLongBorrowing(BigDecimal nclLongBorrowing) {
        this.nclLongBorrowing = nclLongBorrowing;
    }

    public BigDecimal getNclBondsPayable() {
        return nclBondsPayable;
    }

    public void setNclBondsPayable(BigDecimal nclBondsPayable) {
        this.nclBondsPayable = nclBondsPayable;
    }

    public BigDecimal getNclLongTermPayables() {
        return nclLongTermPayables;
    }

    public void setNclLongTermPayables(BigDecimal nclLongTermPayables) {
        this.nclLongTermPayables = nclLongTermPayables;
    }

    public BigDecimal getNelSpecialPayable() {
        return nelSpecialPayable;
    }

    public void setNelSpecialPayable(BigDecimal nelSpecialPayable) {
        this.nelSpecialPayable = nelSpecialPayable;
    }

    public BigDecimal getNelProjectedLiabilities() {
        return nelProjectedLiabilities;
    }

    public void setNelProjectedLiabilities(BigDecimal nelProjectedLiabilities) {
        this.nelProjectedLiabilities = nelProjectedLiabilities;
    }

    public BigDecimal getNelDeferredTaxLiability() {
        return nelDeferredTaxLiability;
    }

    public void setNelDeferredTaxLiability(BigDecimal nelDeferredTaxLiability) {
        this.nelDeferredTaxLiability = nelDeferredTaxLiability;
    }

    public BigDecimal getNelOtherCurrentLiabilities() {
        return nelOtherCurrentLiabilities;
    }

    public void setNelOtherCurrentLiabilities(BigDecimal nelOtherCurrentLiabilities) {
        this.nelOtherCurrentLiabilities = nelOtherCurrentLiabilities;
    }

    public BigDecimal getNelTotalCurrentLiabilities() {
        return nelTotalCurrentLiabilities;
    }

    public void setNelTotalCurrentLiabilities(BigDecimal nelTotalCurrentLiabilities) {
        this.nelTotalCurrentLiabilities = nelTotalCurrentLiabilities;
    }

    public BigDecimal getTotalLiabilities() {
        return totalLiabilities;
    }

    public void setTotalLiabilities(BigDecimal totalLiabilities) {
        this.totalLiabilities = totalLiabilities;
    }

    public BigDecimal getOeShareCapital() {
        return oeShareCapital;
    }

    public void setOeShareCapital(BigDecimal oeShareCapital) {
        this.oeShareCapital = oeShareCapital;
    }

    public BigDecimal getOeCapitalSurplus() {
        return oeCapitalSurplus;
    }

    public void setOeCapitalSurplus(BigDecimal oeCapitalSurplus) {
        this.oeCapitalSurplus = oeCapitalSurplus;
    }

    public BigDecimal getOeLessStock() {
        return oeLessStock;
    }

    public void setOeLessStock(BigDecimal oeLessStock) {
        this.oeLessStock = oeLessStock;
    }

    public BigDecimal getOeSurplusReserve() {
        return oeSurplusReserve;
    }

    public void setOeSurplusReserve(BigDecimal oeSurplusReserve) {
        this.oeSurplusReserve = oeSurplusReserve;
    }

    public BigDecimal getOeUndistributedProfit() {
        return oeUndistributedProfit;
    }

    public void setOeUndistributedProfit(BigDecimal oeUndistributedProfit) {
        this.oeUndistributedProfit = oeUndistributedProfit;
    }

    public BigDecimal getOeTotal() {
        return oeTotal;
    }

    public void setOeTotal(BigDecimal oeTotal) {
        this.oeTotal = oeTotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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
