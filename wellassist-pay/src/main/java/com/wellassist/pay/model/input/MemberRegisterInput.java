package com.wellassist.pay.model.input;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * 账户注册输入参数
 */
@XStreamAlias("stream")
public class MemberRegisterInput {
    private String action;//DLBREGSN
    private String userName;//<!--登录名varchar(30)-->
    private String mainAccNo;//<!--主体账号char(19)-->
    private String appFlag;//<!--应用系统char(1)， 2：B2B电子商务；3：投标保证金-->
    private String accGenType;//<!--附属账户生成方式char(1) ，0：自动输入 ；1：手动生成-->
    private String subAccNo;//<!--附属账号尾号 char(14) ，在accGenType生成方式为1：手动输入时，必输，2015年5月9日之前开立的主体账户，使用14位长度，2015年5月9日之后开立的主体账户，使用7位长度；为0：自动生成时可空-->
    private String subAccNm;//<!--附属账户名称 varchar(122)，可空，appFlag为2时必输，appFlag为3时可空，若不为空则其值必须为客户名称-->
    private String accType;//<!--附属账户类型 char(2)，03：一般交易账号；04：保证金账号；11：招投标保证金-->
    private String calInterestFlag;//<!--是否计算利息标志 char(1)， 0：不计息；1：不分段计息；2：分段计息；当appFlag为3时，是否计算利息标志必须为0-->
    private String interestRate;//<!--默认计息利率 decimal(9.7)，calInterestFlag为 0时，可空；appFlag为3时，必须为0-->
    private String overFlag;//<!--是否允许透支char(1)，0：不允许；1：限额透支；2：全额透支 ；appFlag为3时，必须为0-->
    private String overAmt;//<!--透支额度decimal(15.2)，当overFlag为 0时，可空；appFlag为3时，必须为空-->
    private String overRate;//<!--透支利率decimal(9.7)，当overFlag为 0时，可空；appFlag为3时，必须为空-->
    private String autoAssignInterestFlag;//<!--自动分配利息标示char(1)，0：否；1：是；appFlag为3时，必须为0-->
    private String autoAssignTranFeeFlag;//<!--自动分配转账手续费标char(1)，0：否；1：是；appFlag为3时，必须为0-->
    private String feeType;//<!--手续费收取方式 char(1)，0：不收取；1：实时收取；2：月末累计；appFlag为3时，必须为0-->
    private String realNameParm;//<!--实名制更换char(1) ，0：账户名与账号全不换；1：账户名与账号全换；2：换账户名；3：换账号；appFlag为3时，必须为0-->

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMainAccNo() {
        return mainAccNo;
    }

    public void setMainAccNo(String mainAccNo) {
        this.mainAccNo = mainAccNo;
    }

    public String getAppFlag() {
        return appFlag;
    }

    public void setAppFlag(String appFlag) {
        this.appFlag = appFlag;
    }

    public String getAccGenType() {
        return accGenType;
    }

    public void setAccGenType(String accGenType) {
        this.accGenType = accGenType;
    }

    public String getSubAccNo() {
        return subAccNo;
    }

    public void setSubAccNo(String subAccNo) {
        this.subAccNo = subAccNo;
    }

    public String getSubAccNm() {
        return subAccNm;
    }

    public void setSubAccNm(String subAccNm) {
        this.subAccNm = subAccNm;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getCalInterestFlag() {
        return calInterestFlag;
    }

    public void setCalInterestFlag(String calInterestFlag) {
        this.calInterestFlag = calInterestFlag;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getOverFlag() {
        return overFlag;
    }

    public void setOverFlag(String overFlag) {
        this.overFlag = overFlag;
    }

    public String getOverAmt() {
        return overAmt;
    }

    public void setOverAmt(String overAmt) {
        this.overAmt = overAmt;
    }

    public String getOverRate() {
        return overRate;
    }

    public void setOverRate(String overRate) {
        this.overRate = overRate;
    }

    public String getAutoAssignInterestFlag() {
        return autoAssignInterestFlag;
    }

    public void setAutoAssignInterestFlag(String autoAssignInterestFlag) {
        this.autoAssignInterestFlag = autoAssignInterestFlag;
    }

    public String getAutoAssignTranFeeFlag() {
        return autoAssignTranFeeFlag;
    }

    public void setAutoAssignTranFeeFlag(String autoAssignTranFeeFlag) {
        this.autoAssignTranFeeFlag = autoAssignTranFeeFlag;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getRealNameParm() {
        return realNameParm;
    }

    public void setRealNameParm(String realNameParm) {
        this.realNameParm = realNameParm;
    }

    public String getSubAccPrintParm() {
        return subAccPrintParm;
    }

    public void setSubAccPrintParm(String subAccPrintParm) {
        this.subAccPrintParm = subAccPrintParm;
    }

    public String getMngNode() {
        return mngNode;
    }

    public void setMngNode(String mngNode) {
        this.mngNode = mngNode;
    }

    public String getVtlCustNm() {
        return vtlCustNm;
    }

    public void setVtlCustNm(String vtlCustNm) {
        this.vtlCustNm = vtlCustNm;
    }

    public String getLegalPersonNm() {
        return legalPersonNm;
    }

    public void setLegalPersonNm(String legalPersonNm) {
        this.legalPersonNm = legalPersonNm;
    }

    public String getCustCertType() {
        return custCertType;
    }

    public void setCustCertType(String custCertType) {
        this.custCertType = custCertType;
    }

    public String getCustCertNo() {
        return custCertNo;
    }

    public void setCustCertNo(String custCertNo) {
        this.custCertNo = custCertNo;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCommAddress() {
        return commAddress;
    }

    public void setCommAddress(String commAddress) {
        this.commAddress = commAddress;
    }

    public List<Row> getList() {
        return list;
    }

    public void setList(List<Row> list) {
        this.list = list;
    }

    private String subAccPrintParm;//<!--附属账户凭证打印更换 char(1)，0：全部显示；1：显示附属账户名和账号；2：显示实体账户名和账号；3：显示附属账户名和实体账号；4：显示实体账户名和附属账号；appFlag为3时，必须为0-->
    private String mngNode;//<!--会员确认中心char(6)-->
    private String vtlCustNm;//<!--虚拟客户名称 varchar(122)-->
    private String legalPersonNm;//<!--法人名称 varchar(122)-->
    private String custCertType;//<!--客户证件类型 varchar(1)值域参见附录4.9-->
    private String custCertNo;//<!--客户证件号码 varchar(30) -->
    private String branch;//<!--所属机构 char(3) -->
    private String commAddress;//<!--通讯地址 varchar(152) -->
    private List<Row> list;

}
