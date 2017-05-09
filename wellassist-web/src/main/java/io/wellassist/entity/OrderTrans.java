package io.wellassist.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class OrderTrans {
    private long transId;
    private long orderId;
    private long moneyId;
    private BigDecimal zfMoney;
    private BigDecimal zfSjMoney;
    private int rate;
    private byte zfMethod;
    private long zfId;
    private long bankId;
    private String zfName;
    private String content;
    private String content1;
    private Date tjDate;
    private Date completeDate;
    private byte transState;

    public long getTransId() {
        return transId;
    }

    public void setTransId(long transId) {
        this.transId = transId;
    }

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

    public BigDecimal getZfMoney() {
        return zfMoney;
    }

    public void setZfMoney(BigDecimal zfMoney) {
        this.zfMoney = zfMoney;
    }

    public BigDecimal getZfSjMoney() {
        return zfSjMoney;
    }

    public void setZfSjMoney(BigDecimal zfSjMoney) {
        this.zfSjMoney = zfSjMoney;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public byte getZfMethod() {
        return zfMethod;
    }

    public void setZfMethod(byte zfMethod) {
        this.zfMethod = zfMethod;
    }

    public long getZfId() {
        return zfId;
    }

    public void setZfId(long zfId) {
        this.zfId = zfId;
    }

    public long getBankId() {
        return bankId;
    }

    public void setBankId(long bankId) {
        this.bankId = bankId;
    }

    public String getZfName() {
        return zfName;
    }

    public void setZfName(String zfName) {
        this.zfName = zfName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public Date getTjDate() {
        return tjDate;
    }

    public void setTjDate(Date tjDate) {
        this.tjDate = tjDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public byte getTransState() {
        return transState;
    }

    public void setTransState(byte transState) {
        this.transState = transState;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderTrans that = (OrderTrans) o;

        if (transId != that.transId) return false;
        if (orderId != that.orderId) return false;
        if (moneyId != that.moneyId) return false;
        if (rate != that.rate) return false;
        if (zfMethod != that.zfMethod) return false;
        if (zfId != that.zfId) return false;
        if (bankId != that.bankId) return false;
        if (transState != that.transState) return false;
        if (zfMoney != null ? !zfMoney.equals(that.zfMoney) : that.zfMoney != null) return false;
        if (zfSjMoney != null ? !zfSjMoney.equals(that.zfSjMoney) : that.zfSjMoney != null) return false;
        if (zfName != null ? !zfName.equals(that.zfName) : that.zfName != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (content1 != null ? !content1.equals(that.content1) : that.content1 != null) return false;
        if (tjDate != null ? !tjDate.equals(that.tjDate) : that.tjDate != null) return false;
        if (completeDate != null ? !completeDate.equals(that.completeDate) : that.completeDate != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (transId ^ (transId >>> 32));
        result = 31 * result + (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (int) (moneyId ^ (moneyId >>> 32));
        result = 31 * result + (zfMoney != null ? zfMoney.hashCode() : 0);
        result = 31 * result + (zfSjMoney != null ? zfSjMoney.hashCode() : 0);
        result = 31 * result + rate;
        result = 31 * result + (int) zfMethod;
        result = 31 * result + (int) (zfId ^ (zfId >>> 32));
        result = 31 * result + (int) (bankId ^ (bankId >>> 32));
        result = 31 * result + (zfName != null ? zfName.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (content1 != null ? content1.hashCode() : 0);
        result = 31 * result + (tjDate != null ? tjDate.hashCode() : 0);
        result = 31 * result + (completeDate != null ? completeDate.hashCode() : 0);
        result = 31 * result + (int) transState;
        return result;
    }
}
