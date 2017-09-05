package com.wellapay.cncb.model.input;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wellapay.cncb.util.CNCBConstants;

import java.util.ConcurrentModificationException;
import java.util.List;

/**
 * Created by Administrator on 2017/8/31.
 */

/**
 *
 <stream>
 <action>DLBREGSN</action>
 <userName>wzzl</userName>
 <mainAccNo>8110701013700026166</mainAccNo>
 <appFlag>2</appFlag>
 <accGenType>0</accGenType>
 <subAccNo></subAccNo>
 <subAccNm>wellassist</subAccNm>
 <accType>03</accType>
 <calInterestFlag>0</calInterestFlag>
 <interestRate>0</interestRate>
 <overFlag>0</overFlag>
 <overAmt></overAmt>
 <overRate></overRate>
 <autoAssignInterestFlag>0</autoAssignInterestFlag>
 <autoAssignTranFeeFlag>0</autoAssignTranFeeFlag>
 <feeType>0</feeType>
 <realNameParm>0</realNameParm>
 <subAccPrintParm>0</subAccPrintParm>
 <mngNode>211101</mngNode>
 <vtlCustNm>wellassist</vtlCustNm>
 <legalPersonNm>dingjianwen</legalPersonNm>
 <custCertType>0</custCertType>
 <custCertNo>421022199310201835</custCertNo>
 <branch>030</branch>
 <commAddress>yilexincun</commAddress>
 <list name="VilcstDataList">
 <row>
 <contactName>dingjianwen</contactName>
 <contactPhone>13616546855</contactPhone>
 <mailAddress>zzzowdp@163.com</mailAddress>
 </row>
 </list>
 </stream>
 */
@XStreamAlias("stream")
public class Register {
    private String action;
    private String userName;
    private String mainAccNo;
    private String appFlag;
    private String accGenType;
    private String subAccNo;
    private String subAccNm;
    private String accType;
    private String calInterestFlag;
    private String interestRate;
    private String overFlag;
    private String overAmt;
    private String overRate;
    private String autoAssignInterestFlag;
    private String autoAssignTranFeeFlag;
    private String feeType;
    private String realNameParm;
    private String subAccPrintParm;
    private String mngNode;
    private String vtlCustNm;
    private String legalPersonNm;
    private String custCertType;
    private String custCertNo;
    private String branch;
    private String commAddress;
    private RegisterList list;

    public Register() {
    }

    public Register(String subAccNm, String vtlCustNm, String legalPersonNm, String custCertType, String custCertNo, String commAddress, RegisterList list) {
        this.action="DLBREGSN";
        this.userName= CNCBConstants.CNDB_USERNAME;
        this.mainAccNo=CNCBConstants.CNDB_MAINACCNO;
        this.appFlag="2";
        this.accGenType="0";
        this.subAccNo="";
        this.subAccNm = subAccNm;
        this.accType="03";
        this.calInterestFlag="0";
        this.interestRate="0";
        this.overFlag="0";
        this.overAmt="";
        this.overRate="";
        this.autoAssignInterestFlag="0";
        this.autoAssignTranFeeFlag="0";
        this.feeType="0";
        this.realNameParm="0";
        this.subAccPrintParm="0";
        this.mngNode="211101";
        this.vtlCustNm = vtlCustNm;
        this.legalPersonNm = legalPersonNm;
        this.custCertType = custCertType;
        this.custCertNo = custCertNo;
        this.branch="030";
        this.commAddress = commAddress;
        this.list = list;
    }

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

    public RegisterList getList() {
        return list;
    }

    public void setList(RegisterList list) {
        this.list = list;
    }
}
