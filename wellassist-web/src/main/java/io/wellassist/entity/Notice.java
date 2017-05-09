package io.wellassist.entity;


import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class Notice {
    private long noticeId;
    private String noticeTitle;
    private String noticeContent;
    private long createUserId;
    private byte noticeType;
    private byte state;
    private Date createDate;
    private String createIp;

    public long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(long noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(long createUserId) {
        this.createUserId = createUserId;
    }

    public byte getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(byte noticeType) {
        this.noticeType = noticeType;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Notice waNotice = (Notice) o;

        if (noticeId != waNotice.noticeId) return false;
        if (createUserId != waNotice.createUserId) return false;
        if (noticeType != waNotice.noticeType) return false;
        if (state != waNotice.state) return false;
        if (noticeTitle != null ? !noticeTitle.equals(waNotice.noticeTitle) : waNotice.noticeTitle != null)
            return false;
        if (noticeContent != null ? !noticeContent.equals(waNotice.noticeContent) : waNotice.noticeContent != null)
            return false;
        if (createDate != null ? !createDate.equals(waNotice.createDate) : waNotice.createDate != null) return false;
        if (createIp != null ? !createIp.equals(waNotice.createIp) : waNotice.createIp != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (noticeId ^ (noticeId >>> 32));
        result = 31 * result + (noticeTitle != null ? noticeTitle.hashCode() : 0);
        result = 31 * result + (noticeContent != null ? noticeContent.hashCode() : 0);
        result = 31 * result + (int) (createUserId ^ (createUserId >>> 32));
        result = 31 * result + (int) noticeType;
        result = 31 * result + (int) state;
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (createIp != null ? createIp.hashCode() : 0);
        return result;
    }
}
