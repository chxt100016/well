package io.wellassist.entity;


import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class ProdUser {
    private long logId;
    private long userId;
    private long prodId;
    private Date glDate;
    private Date modifyDate;
    private byte prodUserState;

    public long getLogId() {
        return logId;
    }

    public void setLogId(long logId) {
        this.logId = logId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getProdId() {
        return prodId;
    }

    public void setProdId(long prodId) {
        this.prodId = prodId;
    }

    public Date getGlDate() {
        return glDate;
    }

    public void setGlDate(Date glDate) {
        this.glDate = glDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public byte getProdUserState() {
        return prodUserState;
    }

    public void setProdUserState(byte prodUserState) {
        this.prodUserState = prodUserState;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProdUser that = (ProdUser) o;

        if (logId != that.logId) return false;
        if (userId != that.userId) return false;
        if (prodId != that.prodId) return false;
        if (prodUserState != that.prodUserState) return false;
        if (glDate != null ? !glDate.equals(that.glDate) : that.glDate != null) return false;
        if (modifyDate != null ? !modifyDate.equals(that.modifyDate) : that.modifyDate != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (logId ^ (logId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (prodId ^ (prodId >>> 32));
        result = 31 * result + (glDate != null ? glDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        result = 31 * result + (int) prodUserState;
        return result;
    }
}
