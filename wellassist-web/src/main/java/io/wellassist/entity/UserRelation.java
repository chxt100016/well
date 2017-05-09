package io.wellassist.entity;


import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class UserRelation {
    private long relId;
    private long userId;
    private long gysId;
    private byte relState;
    private Date relDate;

    public long getRelId() {
        return relId;
    }

    public void setRelId(long relId) {
        this.relId = relId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getGysId() {
        return gysId;
    }

    public void setGysId(long gysId) {
        this.gysId = gysId;
    }

    public byte getRelState() {
        return relState;
    }

    public void setRelState(byte relState) {
        this.relState = relState;
    }

    public Date getRelDate() {
        return relDate;
    }

    public void setRelDate(Date relDate) {
        this.relDate = relDate;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRelation that = (UserRelation) o;

        if (relId != that.relId) return false;
        if (userId != that.userId) return false;
        if (gysId != that.gysId) return false;
        if (relState != that.relState) return false;
        if (relDate != null ? !relDate.equals(that.relDate) : that.relDate != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (relId ^ (relId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (gysId ^ (gysId >>> 32));
        result = 31 * result + (int) relState;
        result = 31 * result + (relDate != null ? relDate.hashCode() : 0);
        return result;
    }
}
