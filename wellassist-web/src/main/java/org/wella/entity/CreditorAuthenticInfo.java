package org.wella.entity;

import java.util.Date;
import java.util.Map;

public class CreditorAuthenticInfo {
    private Long creditorAuthenticInfoId;

    private Long userId;

    private Date applyDate;

    private Date mgrDate;

    private String income;

    private String liabilities;

    private String cashFlow;

    private String qualifition;

    private String tax;

    private String other1;

    private String other2;

    private String other3;

    private String other4;

    private Byte state;

    private String comment;

    public Long getCreditorAuthenticInfoId() {
        return creditorAuthenticInfoId;
    }

    public void setCreditorAuthenticInfoId(Long creditorAuthenticInfoId) {
        this.creditorAuthenticInfoId = creditorAuthenticInfoId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Date getMgrDate() {
        return mgrDate;
    }

    public void setMgrDate(Date mgrDate) {
        this.mgrDate = mgrDate;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income == null ? null : income.trim();
    }

    public String getLiabilities() {
        return liabilities;
    }

    public void setLiabilities(String liabilities) {
        this.liabilities = liabilities == null ? null : liabilities.trim();
    }

    public String getCashFlow() {
        return cashFlow;
    }

    public void setCashFlow(String cashFlow) {
        this.cashFlow = cashFlow == null ? null : cashFlow.trim();
    }

    public String getQualifition() {
        return qualifition;
    }

    public void setQualifition(String qualifition) {
        this.qualifition = qualifition == null ? null : qualifition.trim();
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax == null ? null : tax.trim();
    }

    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1 == null ? null : other1.trim();
    }

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2 == null ? null : other2.trim();
    }

    public String getOther3() {
        return other3;
    }

    public void setOther3(String other3) {
        this.other3 = other3 == null ? null : other3.trim();
    }

    public String getOther4() {
        return other4;
    }

    public void setOther4(String other4) {
        this.other4 = other4 == null ? null : other4.trim();
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

}