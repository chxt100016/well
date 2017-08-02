package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

public class User {
    private Long userId;

    private String userAccount;

    private String userName;

    private String userRegNo;

    private Byte userType;

    private String czPass;

    private Date createDate;

    private Integer czFailNum;

    private BigDecimal userMoney;

    private BigDecimal userLockMoney;

    private BigDecimal userCreditMoney;

    private BigDecimal userLockCreditMoney;

    private BigDecimal userLxMoney;

    private String userPhone;

    private String userSeatPhone;

    private String userEmail;

    private String legalIdCard;

    private String userPass;

    private Long createUserId;

    private Date recentDate;

    private String recentIp;

    private Integer loginFailNum;

    private BigDecimal lixiRate;

    private Byte lixiType;

    private Long creditDengji;

    private Integer creditPf;

    private String commet;

    private Byte userState;

    private String activityCode;

    private String resetCode;

    private Long supplyId;

    private byte activityState;

    private byte creditorState;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserRegNo() {
        return userRegNo;
    }

    public void setUserRegNo(String userRegNo) {
        this.userRegNo = userRegNo == null ? null : userRegNo.trim();
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public String getCzPass() {
        return czPass;
    }

    public void setCzPass(String czPass) {
        this.czPass = czPass == null ? null : czPass.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCzFailNum() {
        return czFailNum;
    }

    public void setCzFailNum(Integer czFailNum) {
        this.czFailNum = czFailNum;
    }

    public BigDecimal getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(BigDecimal userMoney) {
        this.userMoney = userMoney;
    }

    public BigDecimal getUserLockMoney() {
        return userLockMoney;
    }

    public void setUserLockMoney(BigDecimal userLockMoney) {
        this.userLockMoney = userLockMoney;
    }

    public BigDecimal getUserCreditMoney() {
        return userCreditMoney;
    }

    public void setUserCreditMoney(BigDecimal userCreditMoney) {
        this.userCreditMoney = userCreditMoney;
    }

    public BigDecimal getUserLockCreditMoney() {
        return userLockCreditMoney;
    }

    public void setUserLockCreditMoney(BigDecimal userLockCreditMoney) {
        this.userLockCreditMoney = userLockCreditMoney;
    }

    public BigDecimal getUserLxMoney() {
        return userLxMoney;
    }

    public void setUserLxMoney(BigDecimal userLxMoney) {
        this.userLxMoney = userLxMoney;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserSeatPhone() {
        return userSeatPhone;
    }

    public void setUserSeatPhone(String userSeatPhone) {
        this.userSeatPhone = userSeatPhone == null ? null : userSeatPhone.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getLegalIdCard() {
        return legalIdCard;
    }

    public void setLegalIdCard(String legalIdCard) {
        this.legalIdCard = legalIdCard == null ? null : legalIdCard.trim();
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass == null ? null : userPass.trim();
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getRecentDate() {
        return recentDate;
    }

    public void setRecentDate(Date recentDate) {
        this.recentDate = recentDate;
    }

    public String getRecentIp() {
        return recentIp;
    }

    public void setRecentIp(String recentIp) {
        this.recentIp = recentIp == null ? null : recentIp.trim();
    }

    public Integer getLoginFailNum() {
        return loginFailNum;
    }

    public void setLoginFailNum(Integer loginFailNum) {
        this.loginFailNum = loginFailNum;
    }

    public BigDecimal getLixiRate() {
        return lixiRate;
    }

    public void setLixiRate(BigDecimal lixiRate) {
        this.lixiRate = lixiRate;
    }

    public Byte getLixiType() {
        return lixiType;
    }

    public void setLixiType(Byte lixiType) {
        this.lixiType = lixiType;
    }

    public Long getCreditDengji() {
        return creditDengji;
    }

    public void setCreditDengji(Long creditDengji) {
        this.creditDengji = creditDengji;
    }

    public Integer getCreditPf() {
        return creditPf;
    }

    public void setCreditPf(Integer creditPf) {
        this.creditPf = creditPf;
    }

    public String getCommet() {
        return commet;
    }

    public void setCommet(String commet) {
        this.commet = commet == null ? null : commet.trim();
    }

    public Byte getUserState() {
        return userState;
    }

    public void setUserState(Byte userState) {
        this.userState = userState;
    }

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode == null ? null : activityCode.trim();
    }

    public String getResetCode() {
        return resetCode;
    }

    public void setResetCode(String resetCode) {
        this.resetCode = resetCode == null ? null : resetCode.trim();
    }

    public Long getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(Long supplyId) {
        this.supplyId = supplyId;
    }

    public byte getActivityState() {
        return activityState;
    }

    public void setActivityState(byte activityState) {
        this.activityState = activityState;
    }

    public byte getCreditorState() {
        return creditorState;
    }

    public void setCreditorState(byte creditorState) {
        this.creditorState = creditorState;
    }
}