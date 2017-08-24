package org.wella.entity;

import java.math.BigDecimal;

public class ManagerInfo {
    private Long id;

    private String creditCode;

    private Short age;

    private String name;

    private String education;

    private String position;

    private String domicile;

    private String marriage;

    private String health;

    private Short expirenceAge;

    private Short negativeEffectsCount;

    private Short renegeCount;

    private Long debtRatio;

    private BigDecimal residenceEstate;

    private Long property;

    private String politicalStatus;

    private Long loan;

    private Long guarantee;

    private String consumptionType;

    private String honorLevel;

    private String identityCard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode == null ? null : creditCode.trim();
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile == null ? null : domicile.trim();
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage == null ? null : marriage.trim();
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health == null ? null : health.trim();
    }

    public Short getExpirenceAge() {
        return expirenceAge;
    }

    public void setExpirenceAge(Short expirenceAge) {
        this.expirenceAge = expirenceAge;
    }

    public Short getNegativeEffectsCount() {
        return negativeEffectsCount;
    }

    public void setNegativeEffectsCount(Short negativeEffectsCount) {
        this.negativeEffectsCount = negativeEffectsCount;
    }

    public Short getRenegeCount() {
        return renegeCount;
    }

    public void setRenegeCount(Short renegeCount) {
        this.renegeCount = renegeCount;
    }

    public Long getDebtRatio() {
        return debtRatio;
    }

    public void setDebtRatio(Long debtRatio) {
        this.debtRatio = debtRatio;
    }

    public BigDecimal getResidenceEstate() {
        return residenceEstate;
    }

    public void setResidenceEstate(BigDecimal residenceEstate) {
        this.residenceEstate = residenceEstate;
    }

    public Long getProperty() {
        return property;
    }

    public void setProperty(Long property) {
        this.property = property;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus == null ? null : politicalStatus.trim();
    }

    public Long getLoan() {
        return loan;
    }

    public void setLoan(Long loan) {
        this.loan = loan;
    }

    public Long getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(Long guarantee) {
        this.guarantee = guarantee;
    }

    public String getConsumptionType() {
        return consumptionType;
    }

    public void setConsumptionType(String consumptionType) {
        this.consumptionType = consumptionType == null ? null : consumptionType.trim();
    }

    public String getHonorLevel() {
        return honorLevel;
    }

    public void setHonorLevel(String honorLevel) {
        this.honorLevel = honorLevel == null ? null : honorLevel.trim();
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard == null ? null : identityCard.trim();
    }
}