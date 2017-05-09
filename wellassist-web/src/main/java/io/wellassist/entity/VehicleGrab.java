package io.wellassist.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class VehicleGrab {
    private long grabId;
    private long orderId;
    private long wlUserId;
    private String vehicleNo;
    private int vehicleSize;
    private BigDecimal grabMoney;
    private Date cfDate;
    private Date ddDate;
    private String sjLxr;
    private String sjLxPhone;
    private Date grabDate;
    private String grabReason;
    private byte grabState;

    public long getGrabId() {
        return grabId;
    }

    public void setGrabId(long grabId) {
        this.grabId = grabId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getWlUserId() {
        return wlUserId;
    }

    public void setWlUserId(long wlUserId) {
        this.wlUserId = wlUserId;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public int getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(int vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public BigDecimal getGrabMoney() {
        return grabMoney;
    }

    public void setGrabMoney(BigDecimal grabMoney) {
        this.grabMoney = grabMoney;
    }


    public String getSjLxr() {
        return sjLxr;
    }

    public void setSjLxr(String sjLxr) {
        this.sjLxr = sjLxr;
    }

    public String getSjLxPhone() {
        return sjLxPhone;
    }

    public void setSjLxPhone(String sjLxPhone) {
        this.sjLxPhone = sjLxPhone;
    }

    public Date getCfDate() {
        return cfDate;
    }

    public void setCfDate(Date cfDate) {
        this.cfDate = cfDate;
    }

    public Date getDdDate() {
        return ddDate;
    }

    public void setDdDate(Date ddDate) {
        this.ddDate = ddDate;
    }

    public Date getGrabDate() {
        return grabDate;
    }

    public void setGrabDate(Date grabDate) {
        this.grabDate = grabDate;
    }

    public String getGrabReason() {
        return grabReason;
    }

    public void setGrabReason(String grabReason) {
        this.grabReason = grabReason;
    }

    public byte getGrabState() {
        return grabState;
    }

    public void setGrabState(byte grabState) {
        this.grabState = grabState;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VehicleGrab that = (VehicleGrab) o;

        if (grabId != that.grabId) return false;
        if (orderId != that.orderId) return false;
        if (wlUserId != that.wlUserId) return false;
        if (vehicleSize != that.vehicleSize) return false;
        if (grabState != that.grabState) return false;
        if (vehicleNo != null ? !vehicleNo.equals(that.vehicleNo) : that.vehicleNo != null) return false;
        if (grabMoney != null ? !grabMoney.equals(that.grabMoney) : that.grabMoney != null) return false;
        if (cfDate != null ? !cfDate.equals(that.cfDate) : that.cfDate != null) return false;
        if (ddDate != null ? !ddDate.equals(that.ddDate) : that.ddDate != null) return false;
        if (sjLxr != null ? !sjLxr.equals(that.sjLxr) : that.sjLxr != null) return false;
        if (sjLxPhone != null ? !sjLxPhone.equals(that.sjLxPhone) : that.sjLxPhone != null) return false;
        if (grabDate != null ? !grabDate.equals(that.grabDate) : that.grabDate != null) return false;
        if (grabReason != null ? !grabReason.equals(that.grabReason) : that.grabReason != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (grabId ^ (grabId >>> 32));
        result = 31 * result + (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (int) (wlUserId ^ (wlUserId >>> 32));
        result = 31 * result + (vehicleNo != null ? vehicleNo.hashCode() : 0);
        result = 31 * result + vehicleSize;
        result = 31 * result + (grabMoney != null ? grabMoney.hashCode() : 0);
        result = 31 * result + (cfDate != null ? cfDate.hashCode() : 0);
        result = 31 * result + (ddDate != null ? ddDate.hashCode() : 0);
        result = 31 * result + (sjLxr != null ? sjLxr.hashCode() : 0);
        result = 31 * result + (sjLxPhone != null ? sjLxPhone.hashCode() : 0);
        result = 31 * result + (grabDate != null ? grabDate.hashCode() : 0);
        result = 31 * result + (grabReason != null ? grabReason.hashCode() : 0);
        result = 31 * result + (int) grabState;
        return result;
    }
}
