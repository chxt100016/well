package io.wellassist.entity;


import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class UserMoneyInfo {
    private long infoId;
    private long moneyId;
    private byte state;
    private String content;
    private long mgrUserId;
    private long mgrAdminId;
    private Date mgrDate;

    public long getInfoId() {
        return infoId;
    }

    public void setInfoId(long infoId) {
        this.infoId = infoId;
    }

    public long getMoneyId() {
        return moneyId;
    }

    public void setMoneyId(long moneyId) {
        this.moneyId = moneyId;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
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

    public long getMgrAdminId() {
        return mgrAdminId;
    }

    public void setMgrAdminId(long mgrAdminId) {
        this.mgrAdminId = mgrAdminId;
    }

    public Date getMgrDate() {
        return mgrDate;
    }

    public void setMgrDate(Date mgrDate) {
        this.mgrDate = mgrDate;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserMoneyInfo that = (UserMoneyInfo) o;

        if (infoId != that.infoId) return false;
        if (moneyId != that.moneyId) return false;
        if (state != that.state) return false;
        if (mgrUserId != that.mgrUserId) return false;
        if (mgrAdminId != that.mgrAdminId) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (mgrDate != null ? !mgrDate.equals(that.mgrDate) : that.mgrDate != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (infoId ^ (infoId >>> 32));
        result = 31 * result + (int) (moneyId ^ (moneyId >>> 32));
        result = 31 * result + (int) state;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (int) (mgrUserId ^ (mgrUserId >>> 32));
        result = 31 * result + (int) (mgrAdminId ^ (mgrAdminId >>> 32));
        result = 31 * result + (mgrDate != null ? mgrDate.hashCode() : 0);
        return result;
    }
}
