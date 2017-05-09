package io.wellassist.entity;

/**
 * Created by liuwen on 2017/5/9.
 */
public class VehicleGrabInfo {
    private long infoId;
    private long grabId;
    private String sjLxr;
    private String sjLxrPhone;
    private String vehicleNo;

    public long getInfoId() {
        return infoId;
    }

    public void setInfoId(long infoId) {
        this.infoId = infoId;
    }

    public long getGrabId() {
        return grabId;
    }

    public void setGrabId(long grabId) {
        this.grabId = grabId;
    }

    public String getSjLxr() {
        return sjLxr;
    }

    public void setSjLxr(String sjLxr) {
        this.sjLxr = sjLxr;
    }

    public String getSjLxrPhone() {
        return sjLxrPhone;
    }

    public void setSjLxrPhone(String sjLxrPhone) {
        this.sjLxrPhone = sjLxrPhone;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VehicleGrabInfo that = (VehicleGrabInfo) o;

        if (infoId != that.infoId) return false;
        if (grabId != that.grabId) return false;
        if (sjLxr != null ? !sjLxr.equals(that.sjLxr) : that.sjLxr != null) return false;
        if (sjLxrPhone != null ? !sjLxrPhone.equals(that.sjLxrPhone) : that.sjLxrPhone != null) return false;
        if (vehicleNo != null ? !vehicleNo.equals(that.vehicleNo) : that.vehicleNo != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (infoId ^ (infoId >>> 32));
        result = 31 * result + (int) (grabId ^ (grabId >>> 32));
        result = 31 * result + (sjLxr != null ? sjLxr.hashCode() : 0);
        result = 31 * result + (sjLxrPhone != null ? sjLxrPhone.hashCode() : 0);
        result = 31 * result + (vehicleNo != null ? vehicleNo.hashCode() : 0);
        return result;
    }
}
