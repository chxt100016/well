package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class OrderVehicle {
    private long vehicleId;
    private long orderId;
    private String vehicleNo;
    private BigDecimal vehicleSize;
    private Date cfDate;
    private Date ddDate;
    private byte vehicleState;
    private int vehicleSjSize;
    private String sjLxr;
    private String sjLxrPhone;
    private Date lkDate;
    private Date tjDate;

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public BigDecimal getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(BigDecimal vehicleSize) {
        this.vehicleSize = vehicleSize;
    }


    public byte getVehicleState() {
        return vehicleState;
    }

    public void setVehicleState(byte vehicleState) {
        this.vehicleState = vehicleState;
    }

    public int getVehicleSjSize() {
        return vehicleSjSize;
    }

    public void setVehicleSjSize(int vehicleSjSize) {
        this.vehicleSjSize = vehicleSjSize;
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

    public Date getLkDate() {
        return lkDate;
    }

    public void setLkDate(Date lkDate) {
        this.lkDate = lkDate;
    }

    public Date getTjDate() {
        return tjDate;
    }

    public void setTjDate(Date tjDate) {
        this.tjDate = tjDate;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderVehicle that = (OrderVehicle) o;

        if (vehicleId != that.vehicleId) return false;
        if (orderId != that.orderId) return false;
        if (vehicleState != that.vehicleState) return false;
        if (vehicleSjSize != that.vehicleSjSize) return false;
        if (vehicleNo != null ? !vehicleNo.equals(that.vehicleNo) : that.vehicleNo != null) return false;
        if (vehicleSize != null ? !vehicleSize.equals(that.vehicleSize) : that.vehicleSize != null) return false;
        if (cfDate != null ? !cfDate.equals(that.cfDate) : that.cfDate != null) return false;
        if (ddDate != null ? !ddDate.equals(that.ddDate) : that.ddDate != null) return false;
        if (sjLxr != null ? !sjLxr.equals(that.sjLxr) : that.sjLxr != null) return false;
        if (sjLxrPhone != null ? !sjLxrPhone.equals(that.sjLxrPhone) : that.sjLxrPhone != null) return false;
        if (lkDate != null ? !lkDate.equals(that.lkDate) : that.lkDate != null) return false;
        if (tjDate != null ? !tjDate.equals(that.tjDate) : that.tjDate != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (vehicleId ^ (vehicleId >>> 32));
        result = 31 * result + (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (vehicleNo != null ? vehicleNo.hashCode() : 0);
        result = 31 * result + (vehicleSize != null ? vehicleSize.hashCode() : 0);
        result = 31 * result + (cfDate != null ? cfDate.hashCode() : 0);
        result = 31 * result + (ddDate != null ? ddDate.hashCode() : 0);
        result = 31 * result + (int) vehicleState;
        result = 31 * result + vehicleSjSize;
        result = 31 * result + (sjLxr != null ? sjLxr.hashCode() : 0);
        result = 31 * result + (sjLxrPhone != null ? sjLxrPhone.hashCode() : 0);
        result = 31 * result + (lkDate != null ? lkDate.hashCode() : 0);
        result = 31 * result + (tjDate != null ? tjDate.hashCode() : 0);
        return result;
    }
}
