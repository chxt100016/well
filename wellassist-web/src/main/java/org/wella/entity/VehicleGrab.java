package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class VehicleGrab {
    private long grabId;
    private long logisticsId;
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

    public long getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(long logisticsId) {
        this.logisticsId = logisticsId;
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

}
