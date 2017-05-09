package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class CreditInfo {
    private long infoId;
    private long creditId;
    private byte creditState;
    private String content;
    private long mgrUserId;
    private long adminUserId;
    private Date mgrDate;
    private String mgrIp;
    private BigDecimal money;
    private BigDecimal lixiMoney;

    public long getInfoId() {
        return infoId;
    }

    public void setInfoId(long infoId) {
        this.infoId = infoId;
    }

    public long getCreditId() {
        return creditId;
    }

    public void setCreditId(long creditId) {
        this.creditId = creditId;
    }

    public byte getCreditState() {
        return creditState;
    }

    public void setCreditState(byte creditState) {
        this.creditState = creditState;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getMgrUserId() {
        return mgrUserId;
    }

    public void setMgrUserId(long mgrUserId) {
        this.mgrUserId = mgrUserId;
    }

    public long getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(long adminUserId) {
        this.adminUserId = adminUserId;
    }

    public Date getMgrDate() {
        return mgrDate;
    }

    public void setMgrDate(Date mgrDate) {
        this.mgrDate = mgrDate;
    }

    public String getMgrIp() {
        return mgrIp;
    }

    public void setMgrIp(String mgrIp) {
        this.mgrIp = mgrIp;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getLixiMoney() {
        return lixiMoney;
    }

    public void setLixiMoney(BigDecimal lixiMoney) {
        this.lixiMoney = lixiMoney;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditInfo that = (CreditInfo) o;

        if (infoId != that.infoId) return false;
        if (creditId != that.creditId) return false;
        if (creditState != that.creditState) return false;
        if (mgrUserId != that.mgrUserId) return false;
        if (adminUserId != that.adminUserId) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (mgrDate != null ? !mgrDate.equals(that.mgrDate) : that.mgrDate != null) return false;
        if (mgrIp != null ? !mgrIp.equals(that.mgrIp) : that.mgrIp != null) return false;
        if (money != null ? !money.equals(that.money) : that.money != null) return false;
        if (lixiMoney != null ? !lixiMoney.equals(that.lixiMoney) : that.lixiMoney != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (infoId ^ (infoId >>> 32));
        result = 31 * result + (int) (creditId ^ (creditId >>> 32));
        result = 31 * result + (int) creditState;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (int) (mgrUserId ^ (mgrUserId >>> 32));
        result = 31 * result + (int) (adminUserId ^ (adminUserId >>> 32));
        result = 31 * result + (mgrDate != null ? mgrDate.hashCode() : 0);
        result = 31 * result + (mgrIp != null ? mgrIp.hashCode() : 0);
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (lixiMoney != null ? lixiMoney.hashCode() : 0);
        return result;
    }
}
