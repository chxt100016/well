package com.wellassist.pay.model.output;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("row")
public class Row {
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

    public String getTZAMT() {
        return TZAMT;
    }

    public void setTZAMT(String TZAMT) {
        this.TZAMT = TZAMT;
    }

    public String getXSACVL() {
        return XSACVL;
    }

    public void setXSACVL(String XSACVL) {
        this.XSACVL = XSACVL;
    }

    public String getKYAMT() {
        return KYAMT;
    }

    public void setKYAMT(String KYAMT) {
        this.KYAMT = KYAMT;
    }

    public String getSJAMT() {
        return SJAMT;
    }

    public void setSJAMT(String SJAMT) {
        this.SJAMT = SJAMT;
    }

    public String getDJAMT() {
        return DJAMT;
    }

    public void setDJAMT(String DJAMT) {
        this.DJAMT = DJAMT;
    }

    private String subAccNo;//</subAccNo><!--附属账号varchar(19)-->
    private String SUBACCNM;//</SUBACCNM><!--附属账户名称varchar(122)-->
    private String TZAMT;//</TZAMT><!--透支额度decimal(15,2)-->
    private String XSACVL;//</XSACVL><!--实体账户可用资金decimal(15,2)-->
    private String KYAMT;//</KYAMT><!--可用余额 decimal(15,2)-->
    private String SJAMT;//</SJAMT><!--实际余额decimal(15,2)-->
    private String DJAMT;//</DJAMT><!--冻结金额decimal(15,2)-->

}
