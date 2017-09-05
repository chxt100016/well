package com.wellapay.cncb.model.output;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/9/4.
 */
@XStreamAlias("row")
public class BalanceQueryOutputListEntity {

    private String subAccNo;
    private String SUBACCNM;
    private BigDecimal TZAMT;
    private BigDecimal XSACVL;
    private BigDecimal KYAMT;
    private BigDecimal SJAMT;
    private BigDecimal DJAMT;

    public String getSubAccNo() {
        return subAccNo;
    }

    public void setSubAccNo(String subAccNo) {
        this.subAccNo = subAccNo;
    }

    public String getSUBACCNM() {
        return SUBACCNM;
    }

    public void setSUBACCNM(String SUBACCNM) {
        this.SUBACCNM = SUBACCNM;
    }

    public BigDecimal getTZAMT() {
        return TZAMT;
    }

    public void setTZAMT(BigDecimal TZAMT) {
        this.TZAMT = TZAMT;
    }

    public BigDecimal getXSACVL() {
        return XSACVL;
    }

    public void setXSACVL(BigDecimal XSACVL) {
        this.XSACVL = XSACVL;
    }

    public BigDecimal getKYAMT() {
        return KYAMT;
    }

    public void setKYAMT(BigDecimal KYAMT) {
        this.KYAMT = KYAMT;
    }

    public BigDecimal getSJAMT() {
        return SJAMT;
    }

    public void setSJAMT(BigDecimal SJAMT) {
        this.SJAMT = SJAMT;
    }

    public BigDecimal getDJAMT() {
        return DJAMT;
    }

    public void setDJAMT(BigDecimal DJAMT) {
        this.DJAMT = DJAMT;
    }
}
