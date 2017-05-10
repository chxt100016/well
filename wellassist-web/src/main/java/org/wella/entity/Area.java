package org.wella.entity;

/**
 * Created by liuwen on 2017/5/9.
 */
public class Area {
    private long areaIdx;
    private long parentIdx;
    private String areaName;

    public long getAreaIdx() {
        return areaIdx;
    }

    public void setAreaIdx(long areaIdx) {
        this.areaIdx = areaIdx;
    }

    public long getParentIdx() {
        return parentIdx;
    }

    public void setParentIdx(long parentIdx) {
        this.parentIdx = parentIdx;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Area waArea = (Area) o;

        if (areaIdx != waArea.areaIdx) return false;
        if (parentIdx != waArea.parentIdx) return false;
        if (areaName != null ? !areaName.equals(waArea.areaName) : waArea.areaName != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (areaIdx ^ (areaIdx >>> 32));
        result = 31 * result + (int) (parentIdx ^ (parentIdx >>> 32));
        result = 31 * result + (areaName != null ? areaName.hashCode() : 0);
        return result;
    }
}
