package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class Tixian {
    private long txId;
    private long moneyId;
    private long userId;
    private BigDecimal txMoney;
    private String txName;
    private String txKhh;
    private String account;
    private byte txState;
    private Date txDate;
    private String txIp;
    private long mgrUserId;
    private Date mgrDate;
    private String content;
    private String mgrIp;

    public long getTxId() {
        return txId;
    }

    public void setTxId(long txId) {
        this.txId = txId;
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

    public BigDecimal getTxMoney() {
        return txMoney;
    }

    public void setTxMoney(BigDecimal txMoney) {
        this.txMoney = txMoney;
    }

    public String getTxName() {
        return txName;
    }

    public void setTxName(String txName) {
        this.txName = txName;
    }

    public String getTxKhh() {
        return txKhh;
    }

    public void setTxKhh(String txKhh) {
        this.txKhh = txKhh;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public byte getTxState() {
        return txState;
    }

    public void setTxState(byte txState) {
        this.txState = txState;
    }


    public String getTxIp() {
        return txIp;
    }

    public void setTxIp(String txIp) {
        this.txIp = txIp;
    }

    public long getMgrUserId() {
        return mgrUserId;
    }

    public void setMgrUserId(long mgrUserId) {
        this.mgrUserId = mgrUserId;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public Date getMgrDate() {
        return mgrDate;
    }

    public void setMgrDate(Date mgrDate) {
        this.mgrDate = mgrDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMgrIp() {
        return mgrIp;
    }

    public void setMgrIp(String mgrIp) {
        this.mgrIp = mgrIp;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tixian waTixian = (Tixian) o;

        if (txId != waTixian.txId) return false;
        if (moneyId != waTixian.moneyId) return false;
        if (userId != waTixian.userId) return false;
        if (txState != waTixian.txState) return false;
        if (mgrUserId != waTixian.mgrUserId) return false;
        if (txMoney != null ? !txMoney.equals(waTixian.txMoney) : waTixian.txMoney != null) return false;
        if (txName != null ? !txName.equals(waTixian.txName) : waTixian.txName != null) return false;
        if (txKhh != null ? !txKhh.equals(waTixian.txKhh) : waTixian.txKhh != null) return false;
        if (account != null ? !account.equals(waTixian.account) : waTixian.account != null) return false;
        if (txDate != null ? !txDate.equals(waTixian.txDate) : waTixian.txDate != null) return false;
        if (txIp != null ? !txIp.equals(waTixian.txIp) : waTixian.txIp != null) return false;
        if (mgrDate != null ? !mgrDate.equals(waTixian.mgrDate) : waTixian.mgrDate != null) return false;
        if (content != null ? !content.equals(waTixian.content) : waTixian.content != null) return false;
        if (mgrIp != null ? !mgrIp.equals(waTixian.mgrIp) : waTixian.mgrIp != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (txId ^ (txId >>> 32));
        result = 31 * result + (int) (moneyId ^ (moneyId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (txMoney != null ? txMoney.hashCode() : 0);
        result = 31 * result + (txName != null ? txName.hashCode() : 0);
        result = 31 * result + (txKhh != null ? txKhh.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (int) txState;
        result = 31 * result + (txDate != null ? txDate.hashCode() : 0);
        result = 31 * result + (txIp != null ? txIp.hashCode() : 0);
        result = 31 * result + (int) (mgrUserId ^ (mgrUserId >>> 32));
        result = 31 * result + (mgrDate != null ? mgrDate.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (mgrIp != null ? mgrIp.hashCode() : 0);
        return result;
    }
}
