package io.wellassist.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class BankOrder {
    private long orderId;
    private long moneyId;
    private long userId;
    private long bankId;
    private String bankName;
    private BigDecimal zfMoney;
    private Date zfDate;
    private String otherContent;
    private String otherContent1;
    private byte zfType;
    private byte zfState;
    private Date completeDate;
    private Date tjDate;
    private Date createDate;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getMoneyId() {
        return moneyId;
    }

    public void setMoneyId(long moneyId) {
        this.moneyId = moneyId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBankId() {
        return bankId;
    }

    public void setBankId(long bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public BigDecimal getZfMoney() {
        return zfMoney;
    }

    public void setZfMoney(BigDecimal zfMoney) {
        this.zfMoney = zfMoney;
    }

    public String getOtherContent() {
        return otherContent;
    }

    public void setOtherContent(String otherContent) {
        this.otherContent = otherContent;
    }

    public String getOtherContent1() {
        return otherContent1;
    }

    public void setOtherContent1(String otherContent1) {
        this.otherContent1 = otherContent1;
    }

    public byte getZfType() {
        return zfType;
    }

    public void setZfType(byte zfType) {
        this.zfType = zfType;
    }

    public byte getZfState() {
        return zfState;
    }

    public void setZfState(byte zfState) {
        this.zfState = zfState;
    }

    public Date getZfDate() {
        return zfDate;
    }

    public void setZfDate(Date zfDate) {
        this.zfDate = zfDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public Date getTjDate() {
        return tjDate;
    }

    public void setTjDate(Date tjDate) {
        this.tjDate = tjDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankOrder that = (BankOrder) o;

        if (orderId != that.orderId) return false;
        if (moneyId != that.moneyId) return false;
        if (userId != that.userId) return false;
        if (bankId != that.bankId) return false;
        if (zfType != that.zfType) return false;
        if (zfState != that.zfState) return false;
        if (bankName != null ? !bankName.equals(that.bankName) : that.bankName != null) return false;
        if (zfMoney != null ? !zfMoney.equals(that.zfMoney) : that.zfMoney != null) return false;
        if (zfDate != null ? !zfDate.equals(that.zfDate) : that.zfDate != null) return false;
        if (otherContent != null ? !otherContent.equals(that.otherContent) : that.otherContent != null) return false;
        if (otherContent1 != null ? !otherContent1.equals(that.otherContent1) : that.otherContent1 != null)
            return false;
        if (completeDate != null ? !completeDate.equals(that.completeDate) : that.completeDate != null) return false;
        if (tjDate != null ? !tjDate.equals(that.tjDate) : that.tjDate != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (int) (moneyId ^ (moneyId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (bankId ^ (bankId >>> 32));
        result = 31 * result + (bankName != null ? bankName.hashCode() : 0);
        result = 31 * result + (zfMoney != null ? zfMoney.hashCode() : 0);
        result = 31 * result + (zfDate != null ? zfDate.hashCode() : 0);
        result = 31 * result + (otherContent != null ? otherContent.hashCode() : 0);
        result = 31 * result + (otherContent1 != null ? otherContent1.hashCode() : 0);
        result = 31 * result + (int) zfType;
        result = 31 * result + (int) zfState;
        result = 31 * result + (completeDate != null ? completeDate.hashCode() : 0);
        result = 31 * result + (tjDate != null ? tjDate.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }
}
