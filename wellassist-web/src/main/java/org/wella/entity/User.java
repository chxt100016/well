package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class User {
    private long userId;
    private String userAccount;
    private String userName;
    private String userRegNo;
    private byte userType;
    private String czPass;
    private Date createDate;
    private int czFailNum;
    private BigDecimal userMoney;
    private BigDecimal userLockMoney;
    private BigDecimal userCreditMoney;
    private BigDecimal userLockCreditMoney;
    private BigDecimal userLxMoney;
    private String userPhone;
    private String userEmail;
    private String userPass;
    private long createUserId;
    private Date recentDate;
    private String recentIp;
    private int loginFailNum;
    private int lixiRate;
    private byte lixiType;
    private long creditDengji;
    private int creditPf;
    private String commet;
    private byte userState;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRegNo() {
        return userRegNo;
    }

    public void setUserRegNo(String userRegNo) {
        this.userRegNo = userRegNo;
    }

    public byte getUserType() {
        return userType;
    }

    public void setUserType(byte userType) {
        this.userType = userType;
    }

    public String getCzPass() {
        return czPass;
    }

    public void setCzPass(String czPass) {
        this.czPass = czPass;
    }


    public int getCzFailNum() {
        return czFailNum;
    }

    public void setCzFailNum(int czFailNum) {
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
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
        this.recentIp = recentIp;
    }

    public int getLoginFailNum() {
        return loginFailNum;
    }

    public void setLoginFailNum(int loginFailNum) {
        this.loginFailNum = loginFailNum;
    }

    public int getLixiRate() {
        return lixiRate;
    }

    public void setLixiRate(int lixiRate) {
        this.lixiRate = lixiRate;
    }

    public byte getLixiType() {
        return lixiType;
    }

    public void setLixiType(byte lixiType) {
        this.lixiType = lixiType;
    }

    public long getCreditDengji() {
        return creditDengji;
    }

    public void setCreditDengji(long creditDengji) {
        this.creditDengji = creditDengji;
    }

    public int getCreditPf() {
        return creditPf;
    }

    public void setCreditPf(int creditPf) {
        this.creditPf = creditPf;
    }

    public String getCommet() {
        return commet;
    }

    public void setCommet(String commet) {
        this.commet = commet;
    }

    public byte getUserState() {
        return userState;
    }

    public void setUserState(byte userState) {
        this.userState = userState;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User waUser = (User) o;

        if (userId != waUser.userId) return false;
        if (userType != waUser.userType) return false;
        if (czFailNum != waUser.czFailNum) return false;
        if (createUserId != waUser.createUserId) return false;
        if (loginFailNum != waUser.loginFailNum) return false;
        if (lixiRate != waUser.lixiRate) return false;
        if (lixiType != waUser.lixiType) return false;
        if (creditDengji != waUser.creditDengji) return false;
        if (creditPf != waUser.creditPf) return false;
        if (userState != waUser.userState) return false;
        if (userAccount != null ? !userAccount.equals(waUser.userAccount) : waUser.userAccount != null) return false;
        if (userName != null ? !userName.equals(waUser.userName) : waUser.userName != null) return false;
        if (userRegNo != null ? !userRegNo.equals(waUser.userRegNo) : waUser.userRegNo != null) return false;
        if (czPass != null ? !czPass.equals(waUser.czPass) : waUser.czPass != null) return false;
        if (createDate != null ? !createDate.equals(waUser.createDate) : waUser.createDate != null) return false;
        if (userMoney != null ? !userMoney.equals(waUser.userMoney) : waUser.userMoney != null) return false;
        if (userLockMoney != null ? !userLockMoney.equals(waUser.userLockMoney) : waUser.userLockMoney != null)
            return false;
        if (userCreditMoney != null ? !userCreditMoney.equals(waUser.userCreditMoney) : waUser.userCreditMoney != null)
            return false;
        if (userLockCreditMoney != null ? !userLockCreditMoney.equals(waUser.userLockCreditMoney) : waUser.userLockCreditMoney != null)
            return false;
        if (userLxMoney != null ? !userLxMoney.equals(waUser.userLxMoney) : waUser.userLxMoney != null) return false;
        if (userPhone != null ? !userPhone.equals(waUser.userPhone) : waUser.userPhone != null) return false;
        if (userEmail != null ? !userEmail.equals(waUser.userEmail) : waUser.userEmail != null) return false;
        if (userPass != null ? !userPass.equals(waUser.userPass) : waUser.userPass != null) return false;
        if (recentDate != null ? !recentDate.equals(waUser.recentDate) : waUser.recentDate != null) return false;
        if (recentIp != null ? !recentIp.equals(waUser.recentIp) : waUser.recentIp != null) return false;
        if (commet != null ? !commet.equals(waUser.commet) : waUser.commet != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (userAccount != null ? userAccount.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userRegNo != null ? userRegNo.hashCode() : 0);
        result = 31 * result + (int) userType;
        result = 31 * result + (czPass != null ? czPass.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + czFailNum;
        result = 31 * result + (userMoney != null ? userMoney.hashCode() : 0);
        result = 31 * result + (userLockMoney != null ? userLockMoney.hashCode() : 0);
        result = 31 * result + (userCreditMoney != null ? userCreditMoney.hashCode() : 0);
        result = 31 * result + (userLockCreditMoney != null ? userLockCreditMoney.hashCode() : 0);
        result = 31 * result + (userLxMoney != null ? userLxMoney.hashCode() : 0);
        result = 31 * result + (userPhone != null ? userPhone.hashCode() : 0);
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        result = 31 * result + (userPass != null ? userPass.hashCode() : 0);
        result = 31 * result + (int) (createUserId ^ (createUserId >>> 32));
        result = 31 * result + (recentDate != null ? recentDate.hashCode() : 0);
        result = 31 * result + (recentIp != null ? recentIp.hashCode() : 0);
        result = 31 * result + loginFailNum;
        result = 31 * result + lixiRate;
        result = 31 * result + (int) lixiType;
        result = 31 * result + (int) (creditDengji ^ (creditDengji >>> 32));
        result = 31 * result + creditPf;
        result = 31 * result + (commet != null ? commet.hashCode() : 0);
        result = 31 * result + (int) userState;
        return result;
    }
}
