package org.wella.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xuqinghuo on 2017/7/26.
 */
public class CreditRecord {

    private Integer id;
    private Integer userId;
    private Integer enterpriseId;
    private String creditLevel;
    private String creditType;
    private String creditDate;
    private String evaluationInstitution;
    private String memo;

    private String dataType;
    private String dataDate;
    private String dataUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getCreditLevel() {
        return creditLevel;
    }

    public void setCreditLevel(String creditLevel) {
        this.creditLevel = creditLevel;
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    public String getCreditDate() {
        return creditDate;
    }

    public void setCreditDate(Date creditDate) {
        SimpleDateFormat dateF = new SimpleDateFormat("yyyy-MM-dd");
        this.creditDate = dateF.format(creditDate);
    }

    public String getEvaluationInstitution() {
        return evaluationInstitution;
    }

    public void setEvaluationInstitution(String evaluationInstitution) {
        this.evaluationInstitution = evaluationInstitution;
    }


    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
