package com.wellapay.cncb.model.output.AccountTransQuery;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/10/13.
 */
@XStreamAlias("row")
public class AccountTransQueryOutputListEntity {
    private String subAccNo;
    private int TRANTYPE;
    private String TRANDATE;
    private String TRANTIME;
    private String HOSTFLW;
    private String HOSTSEQ;
    private String OPPACCNO;
    private String OPPACCNAME;
    private String OPPBRANCHNAME;
    private String OPPBANKNO;
    private String CDFG;
    private BigDecimal TRANAMT;
    private BigDecimal ACCBAL;
    private BigDecimal XTSFAM;
    private String RESUME;
    private String CRYTYPE;

    public String getSubAccNo() {
        return subAccNo;
    }

    public void setSubAccNo(String subAccNo) {
        this.subAccNo = subAccNo;
    }

    public int getTRANTYPE() {
        return TRANTYPE;
    }

    public void setTRANTYPE(int TRANTYPE) {
        this.TRANTYPE = TRANTYPE;
    }

    public String getTRANDATE() {
        return TRANDATE;
    }

    public void setTRANDATE(String TRANDATE) {
        this.TRANDATE = TRANDATE;
    }

    public String getTRANTIME() {
        return TRANTIME;
    }

    public void setTRANTIME(String TRANTIME) {
        this.TRANTIME = TRANTIME;
    }

    public String getHOSTFLW() {
        return HOSTFLW;
    }

    public void setHOSTFLW(String HOSTFLW) {
        this.HOSTFLW = HOSTFLW;
    }

    public String getHOSTSEQ() {
        return HOSTSEQ;
    }

    public void setHOSTSEQ(String HOSTSEQ) {
        this.HOSTSEQ = HOSTSEQ;
    }

    public String getOPPACCNO() {
        return OPPACCNO;
    }

    public void setOPPACCNO(String OPPACCNO) {
        this.OPPACCNO = OPPACCNO;
    }

    public String getOPPACCNAME() {
        return OPPACCNAME;
    }

    public void setOPPACCNAME(String OPPACCNAME) {
        this.OPPACCNAME = OPPACCNAME;
    }

    public String getOPPBRANCHNAME() {
        return OPPBRANCHNAME;
    }

    public void setOPPBRANCHNAME(String OPPBRANCHNAME) {
        this.OPPBRANCHNAME = OPPBRANCHNAME;
    }

    public String getOPPBANKNO() {
        return OPPBANKNO;
    }

    public void setOPPBANKNO(String OPPBANKNO) {
        this.OPPBANKNO = OPPBANKNO;
    }

    public String getCDFG() {
        return CDFG;
    }

    public void setCDFG(String CDFG) {
        this.CDFG = CDFG;
    }

    public BigDecimal getTRANAMT() {
        return TRANAMT;
    }

    public void setTRANAMT(BigDecimal TRANAMT) {
        this.TRANAMT = TRANAMT;
    }

    public BigDecimal getACCBAL() {
        return ACCBAL;
    }

    public void setACCBAL(BigDecimal ACCBAL) {
        this.ACCBAL = ACCBAL;
    }

    public BigDecimal getXTSFAM() {
        return XTSFAM;
    }

    public void setXTSFAM(BigDecimal XTSFAM) {
        this.XTSFAM = XTSFAM;
    }

    public String getRESUME() {
        return RESUME;
    }

    public void setRESUME(String RESUME) {
        this.RESUME = RESUME;
    }

    public String getCRYTYPE() {
        return CRYTYPE;
    }

    public void setCRYTYPE(String CRYTYPE) {
        this.CRYTYPE = CRYTYPE;
    }
}
