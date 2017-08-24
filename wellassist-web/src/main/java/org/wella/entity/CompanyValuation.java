package org.wella.entity;

import java.util.Date;

public class CompanyValuation {
    private Long id;

    private String loanType;

    private String loanPaymentsInfo;

    private String growthPotential;

    private String earning;

    private String socialEffect;

    private String businessStrategy;

    private Date period;

    private String creditCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType == null ? null : loanType.trim();
    }

    public String getLoanPaymentsInfo() {
        return loanPaymentsInfo;
    }

    public void setLoanPaymentsInfo(String loanPaymentsInfo) {
        this.loanPaymentsInfo = loanPaymentsInfo == null ? null : loanPaymentsInfo.trim();
    }

    public String getGrowthPotential() {
        return growthPotential;
    }

    public void setGrowthPotential(String growthPotential) {
        this.growthPotential = growthPotential == null ? null : growthPotential.trim();
    }

    public String getEarning() {
        return earning;
    }

    public void setEarning(String earning) {
        this.earning = earning == null ? null : earning.trim();
    }

    public String getSocialEffect() {
        return socialEffect;
    }

    public void setSocialEffect(String socialEffect) {
        this.socialEffect = socialEffect == null ? null : socialEffect.trim();
    }

    public String getBusinessStrategy() {
        return businessStrategy;
    }

    public void setBusinessStrategy(String businessStrategy) {
        this.businessStrategy = businessStrategy == null ? null : businessStrategy.trim();
    }

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode == null ? null : creditCode.trim();
    }
}