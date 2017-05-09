package org.wella.entity;


import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class Info {
    private long txId;
    private long userId;
    private String txAccount;
    private byte txType;
    private String txName;
    private String txContent;
    private byte state;
    private Date txDate;
    private Date fsDate;
    private String lxUrl;
    private int isRead;

    public long getTxId() {
        return txId;
    }

    public void setTxId(long txId) {
        this.txId = txId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTxAccount() {
        return txAccount;
    }

    public void setTxAccount(String txAccount) {
        this.txAccount = txAccount;
    }

    public byte getTxType() {
        return txType;
    }

    public void setTxType(byte txType) {
        this.txType = txType;
    }

    public String getTxName() {
        return txName;
    }

    public void setTxName(String txName) {
        this.txName = txName;
    }

    public String getTxContent() {
        return txContent;
    }

    public void setTxContent(String txContent) {
        this.txContent = txContent;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public Date getFsDate() {
        return fsDate;
    }

    public void setFsDate(Date fsDate) {
        this.fsDate = fsDate;
    }

    public String getLxUrl() {
        return lxUrl;
    }

    public void setLxUrl(String lxUrl) {
        this.lxUrl = lxUrl;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Info waInfo = (Info) o;

        if (txId != waInfo.txId) return false;
        if (userId != waInfo.userId) return false;
        if (txType != waInfo.txType) return false;
        if (state != waInfo.state) return false;
        if (isRead != waInfo.isRead) return false;
        if (txAccount != null ? !txAccount.equals(waInfo.txAccount) : waInfo.txAccount != null) return false;
        if (txName != null ? !txName.equals(waInfo.txName) : waInfo.txName != null) return false;
        if (txContent != null ? !txContent.equals(waInfo.txContent) : waInfo.txContent != null) return false;
        if (txDate != null ? !txDate.equals(waInfo.txDate) : waInfo.txDate != null) return false;
        if (fsDate != null ? !fsDate.equals(waInfo.fsDate) : waInfo.fsDate != null) return false;
        if (lxUrl != null ? !lxUrl.equals(waInfo.lxUrl) : waInfo.lxUrl != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (txId ^ (txId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (txAccount != null ? txAccount.hashCode() : 0);
        result = 31 * result + (int) txType;
        result = 31 * result + (txName != null ? txName.hashCode() : 0);
        result = 31 * result + (txContent != null ? txContent.hashCode() : 0);
        result = 31 * result + (int) state;
        result = 31 * result + (txDate != null ? txDate.hashCode() : 0);
        result = 31 * result + (fsDate != null ? fsDate.hashCode() : 0);
        result = 31 * result + (lxUrl != null ? lxUrl.hashCode() : 0);
        result = 31 * result + isRead;
        return result;
    }
}
