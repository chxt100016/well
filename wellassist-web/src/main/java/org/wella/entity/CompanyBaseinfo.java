package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CompanyBaseinfo {
    private Long id;

    private String legalPersonId;

    private String name;

    private Byte type;

    private BigDecimal registeredCaptital;

    private Date regDate;

    private Date businessStart;

    private Date businessEnd;

    private String regOffice;

    private Date checkDate;

    private String location;

    private String businessScope;

    private String relatedData;

    private String creditCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLegalPersonId() {
        return legalPersonId;
    }

    public void setLegalPersonId(String legalPersonId) {
        this.legalPersonId = legalPersonId == null ? null : legalPersonId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public BigDecimal getRegisteredCaptital() {
        return registeredCaptital;
    }

    public void setRegisteredCaptital(BigDecimal registeredCaptital) {
        this.registeredCaptital = registeredCaptital;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getBusinessStart() {
        return businessStart;
    }

    public void setBusinessStart(Date businessStart) {
        this.businessStart = businessStart;
    }

    public Date getBusinessEnd() {
        return businessEnd;
    }

    public void setBusinessEnd(Date businessEnd) {
        this.businessEnd = businessEnd;
    }

    public String getRegOffice() {
        return regOffice;
    }

    public void setRegOffice(String regOffice) {
        this.regOffice = regOffice == null ? null : regOffice.trim();
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope == null ? null : businessScope.trim();
    }

    public String getRelatedData() {
        return relatedData;
    }

    public void setRelatedData(String relatedData) {
        this.relatedData = relatedData == null ? null : relatedData.trim();
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode == null ? null : creditCode.trim();
    }

    public void update(CompanyBaseinfo companyBaseinfo) {
    }
}