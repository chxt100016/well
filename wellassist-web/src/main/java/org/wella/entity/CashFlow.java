package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/3.
 */
public class CashFlow {

    private Integer id;

    private Integer companyId;

    private BigDecimal opeTransactionIncrease;

    private BigDecimal opeCollectCash;

    private BigDecimal opeBorrowingIncrease;

    private BigDecimal opeBusinessIncrease;

    private BigDecimal opeCollectOtherCash;

    private BigDecimal opePaymentCash;

    private BigDecimal opeWorkersCash;

    private BigDecimal opeEveryTaxation;

    private BigDecimal opePaymentOtherCash;

    private BigDecimal opeCashSubtotal;

    private BigDecimal opeProduceIncrease;

    private BigDecimal invCollectCash;

    private BigDecimal invObtainCash;

    private BigDecimal invCollectOtherCash;

    private BigDecimal invCashInSubtotal;

    private BigDecimal invPaymentCash;

    private BigDecimal invStructureAssetsCash;

    private BigDecimal invPaymentOtherCash;

    private BigDecimal invCashOutSubtotal;

    private BigDecimal invProduceIncrease;

    private BigDecimal preInvestmentCash;

    private BigDecimal preBondCash;

    private BigDecimal preOtherCash;

    private BigDecimal preCashInSubtotal;

    private BigDecimal preDebtPaymentCash;

    private BigDecimal preDistributionCash;

    private BigDecimal prePaymentOtherCash;

    private BigDecimal preCashOutSubtotal;

    private BigDecimal exchangeRateInfluence;

    private BigDecimal cashEquivalentIncrease;

    private BigDecimal beginCashBalance;

    private BigDecimal lastCashBalance;

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

    public BigDecimal getOpeTransactionIncrease() {
        return opeTransactionIncrease;
    }

    public void setOpeTransactionIncrease(BigDecimal opeTransactionIncrease) {
        this.opeTransactionIncrease = opeTransactionIncrease;
    }

    public BigDecimal getOpeCollectCash() {
        return opeCollectCash;
    }

    public void setOpeCollectCash(BigDecimal opeCollectCash) {
        this.opeCollectCash = opeCollectCash;
    }

    public BigDecimal getOpeBorrowingIncrease() {
        return opeBorrowingIncrease;
    }

    public void setOpeBorrowingIncrease(BigDecimal opeBorrowingIncrease) {
        this.opeBorrowingIncrease = opeBorrowingIncrease;
    }

    public BigDecimal getOpeBusinessIncrease() {
        return opeBusinessIncrease;
    }

    public void setOpeBusinessIncrease(BigDecimal opeBusinessIncrease) {
        this.opeBusinessIncrease = opeBusinessIncrease;
    }

    public BigDecimal getOpeCollectOtherCash() {
        return opeCollectOtherCash;
    }

    public void setOpeCollectOtherCash(BigDecimal opeCollectOtherCash) {
        this.opeCollectOtherCash = opeCollectOtherCash;
    }

    public BigDecimal getOpePaymentCash() {
        return opePaymentCash;
    }

    public void setOpePaymentCash(BigDecimal opePaymentCash) {
        this.opePaymentCash = opePaymentCash;
    }

    public BigDecimal getOpeWorkersCash() {
        return opeWorkersCash;
    }

    public void setOpeWorkersCash(BigDecimal opeWorkersCash) {
        this.opeWorkersCash = opeWorkersCash;
    }

    public BigDecimal getOpeEveryTaxation() {
        return opeEveryTaxation;
    }

    public void setOpeEveryTaxation(BigDecimal opeEveryTaxation) {
        this.opeEveryTaxation = opeEveryTaxation;
    }

    public BigDecimal getOpePaymentOtherCash() {
        return opePaymentOtherCash;
    }

    public void setOpePaymentOtherCash(BigDecimal opePaymentOtherCash) {
        this.opePaymentOtherCash = opePaymentOtherCash;
    }

    public BigDecimal getOpeCashSubtotal() {
        return opeCashSubtotal;
    }

    public void setOpeCashSubtotal(BigDecimal opeCashSubtotal) {
        this.opeCashSubtotal = opeCashSubtotal;
    }

    public BigDecimal getOpeProduceIncrease() {
        return opeProduceIncrease;
    }

    public void setOpeProduceIncrease(BigDecimal opeProduceIncrease) {
        this.opeProduceIncrease = opeProduceIncrease;
    }

    public BigDecimal getInvCollectCash() {
        return invCollectCash;
    }

    public void setInvCollectCash(BigDecimal invCollectCash) {
        this.invCollectCash = invCollectCash;
    }

    public BigDecimal getInvObtainCash() {
        return invObtainCash;
    }

    public void setInvObtainCash(BigDecimal invObtainCash) {
        this.invObtainCash = invObtainCash;
    }

    public BigDecimal getInvCollectOtherCash() {
        return invCollectOtherCash;
    }

    public void setInvCollectOtherCash(BigDecimal invCollectOtherCash) {
        this.invCollectOtherCash = invCollectOtherCash;
    }

    public BigDecimal getInvCashInSubtotal() {
        return invCashInSubtotal;
    }

    public void setInvCashInSubtotal(BigDecimal invCashInSubtotal) {
        this.invCashInSubtotal = invCashInSubtotal;
    }

    public BigDecimal getInvPaymentCash() {
        return invPaymentCash;
    }

    public void setInvPaymentCash(BigDecimal invPaymentCash) {
        this.invPaymentCash = invPaymentCash;
    }

    public BigDecimal getInvStructureAssetsCash() {
        return invStructureAssetsCash;
    }

    public void setInvStructureAssetsCash(BigDecimal invStructureAssetsCash) {
        this.invStructureAssetsCash = invStructureAssetsCash;
    }

    public BigDecimal getInvPaymentOtherCash() {
        return invPaymentOtherCash;
    }

    public void setInvPaymentOtherCash(BigDecimal invPaymentOtherCash) {
        this.invPaymentOtherCash = invPaymentOtherCash;
    }

    public BigDecimal getInvCashOutSubtotal() {
        return invCashOutSubtotal;
    }

    public void setInvCashOutSubtotal(BigDecimal invCashOutSubtotal) {
        this.invCashOutSubtotal = invCashOutSubtotal;
    }

    public BigDecimal getInvProduceIncrease() {
        return invProduceIncrease;
    }

    public void setInvProduceIncrease(BigDecimal invProduceIncrease) {
        this.invProduceIncrease = invProduceIncrease;
    }

    public BigDecimal getPreInvestmentCash() {
        return preInvestmentCash;
    }

    public void setPreInvestmentCash(BigDecimal preInvestmentCash) {
        this.preInvestmentCash = preInvestmentCash;
    }

    public BigDecimal getPreBondCash() {
        return preBondCash;
    }

    public void setPreBondCash(BigDecimal preBondCash) {
        this.preBondCash = preBondCash;
    }

    public BigDecimal getPreOtherCash() {
        return preOtherCash;
    }

    public void setPreOtherCash(BigDecimal preOtherCash) {
        this.preOtherCash = preOtherCash;
    }

    public BigDecimal getPreCashInSubtotal() {
        return preCashInSubtotal;
    }

    public void setPreCashInSubtotal(BigDecimal preCashInSubtotal) {
        this.preCashInSubtotal = preCashInSubtotal;
    }

    public BigDecimal getPreDebtPaymentCash() {
        return preDebtPaymentCash;
    }

    public void setPreDebtPaymentCash(BigDecimal preDebtPaymentCash) {
        this.preDebtPaymentCash = preDebtPaymentCash;
    }

    public BigDecimal getPreDistributionCash() {
        return preDistributionCash;
    }

    public void setPreDistributionCash(BigDecimal preDistributionCash) {
        this.preDistributionCash = preDistributionCash;
    }

    public BigDecimal getPrePaymentOtherCash() {
        return prePaymentOtherCash;
    }

    public void setPrePaymentOtherCash(BigDecimal prePaymentOtherCash) {
        this.prePaymentOtherCash = prePaymentOtherCash;
    }

    public BigDecimal getPreCashOutSubtotal() {
        return preCashOutSubtotal;
    }

    public void setPreCashOutSubtotal(BigDecimal preCashOutSubtotal) {
        this.preCashOutSubtotal = preCashOutSubtotal;
    }

    public BigDecimal getExchangeRateInfluence() {
        return exchangeRateInfluence;
    }

    public void setExchangeRateInfluence(BigDecimal exchangeRateInfluence) {
        this.exchangeRateInfluence = exchangeRateInfluence;
    }

    public BigDecimal getCashEquivalentIncrease() {
        return cashEquivalentIncrease;
    }

    public void setCashEquivalentIncrease(BigDecimal cashEquivalentIncrease) {
        this.cashEquivalentIncrease = cashEquivalentIncrease;
    }

    public BigDecimal getBeginCashBalance() {
        return beginCashBalance;
    }

    public void setBeginCashBalance(BigDecimal beginCashBalance) {
        this.beginCashBalance = beginCashBalance;
    }

    public BigDecimal getLastCashBalance() {
        return lastCashBalance;
    }

    public void setLastCashBalance(BigDecimal lastCashBalance) {
        this.lastCashBalance = lastCashBalance;
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
