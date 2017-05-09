package io.wellassist.entity;


import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class Log {
    private long logId;
    private long userId;
    private String progYm;
    private byte logType;
    private String logContent;
    private Date logDate;
    private String logIp;
    private String logContent1;

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

    public String getProgYm() {
        return progYm;
    }

    public void setProgYm(String progYm) {
        this.progYm = progYm;
    }

    public byte getLogType() {
        return logType;
    }

    public void setLogType(byte logType) {
        this.logType = logType;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public String getLogIp() {
        return logIp;
    }

    public void setLogIp(String logIp) {
        this.logIp = logIp;
    }

    public String getLogContent1() {
        return logContent1;
    }

    public void setLogContent1(String logContent1) {
        this.logContent1 = logContent1;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Log waLog = (Log) o;

        if (logId != waLog.logId) return false;
        if (userId != waLog.userId) return false;
        if (logType != waLog.logType) return false;
        if (progYm != null ? !progYm.equals(waLog.progYm) : waLog.progYm != null) return false;
        if (logContent != null ? !logContent.equals(waLog.logContent) : waLog.logContent != null) return false;
        if (logDate != null ? !logDate.equals(waLog.logDate) : waLog.logDate != null) return false;
        if (logIp != null ? !logIp.equals(waLog.logIp) : waLog.logIp != null) return false;
        if (logContent1 != null ? !logContent1.equals(waLog.logContent1) : waLog.logContent1 != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (logId ^ (logId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (progYm != null ? progYm.hashCode() : 0);
        result = 31 * result + (int) logType;
        result = 31 * result + (logContent != null ? logContent.hashCode() : 0);
        result = 31 * result + (logDate != null ? logDate.hashCode() : 0);
        result = 31 * result + (logIp != null ? logIp.hashCode() : 0);
        result = 31 * result + (logContent1 != null ? logContent1.hashCode() : 0);
        return result;
    }
}
