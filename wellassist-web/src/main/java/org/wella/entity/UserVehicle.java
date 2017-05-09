package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class UserVehicle {
    private long vehicleTrans;
    private long orderId;
    private String orderNo;
    private long prodId;
    private long wlUserId;
    private long userId;
    private String vehicleLxr;
    private String vehicleLxrPhone;
    private long grabId;
    private long moneyId;
    private BigDecimal payMoney;
    private BigDecimal vehicleSize;
    private Date createDate;
    private String createIp;
    private byte vehicleState;

    public long getVehicleTrans() {
        return vehicleTrans;
    }

    public void setVehicleTrans(long vehicleTrans) {
        this.vehicleTrans = vehicleTrans;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public long getProdId() {
        return prodId;
    }

    public void setProdId(long prodId) {
        this.prodId = prodId;
    }

    public long getWlUserId() {
        return wlUserId;
    }

    public void setWlUserId(long wlUserId) {
        this.wlUserId = wlUserId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getVehicleLxr() {
        return vehicleLxr;
    }

    public void setVehicleLxr(String vehicleLxr) {
        this.vehicleLxr = vehicleLxr;
    }

    public String getVehicleLxrPhone() {
        return vehicleLxrPhone;
    }

    public void setVehicleLxrPhone(String vehicleLxrPhone) {
        this.vehicleLxrPhone = vehicleLxrPhone;
    }

    public long getGrabId() {
        return grabId;
    }

    public void setGrabId(long grabId) {
        this.grabId = grabId;
    }

    public long getMoneyId() {
        return moneyId;
    }

    public void setMoneyId(long moneyId) {
        this.moneyId = moneyId;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public BigDecimal getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(BigDecimal vehicleSize) {
        this.vehicleSize = vehicleSize;
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

    public byte getVehicleState() {
        return vehicleState;
    }

    public void setVehicleState(byte vehicleState) {
        this.vehicleState = vehicleState;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserVehicle that = (UserVehicle) o;

        if (vehicleTrans != that.vehicleTrans) return false;
        if (orderId != that.orderId) return false;
        if (prodId != that.prodId) return false;
        if (wlUserId != that.wlUserId) return false;
        if (userId != that.userId) return false;
        if (grabId != that.grabId) return false;
        if (moneyId != that.moneyId) return false;
        if (vehicleState != that.vehicleState) return false;
        if (orderNo != null ? !orderNo.equals(that.orderNo) : that.orderNo != null) return false;
        if (vehicleLxr != null ? !vehicleLxr.equals(that.vehicleLxr) : that.vehicleLxr != null) return false;
        if (vehicleLxrPhone != null ? !vehicleLxrPhone.equals(that.vehicleLxrPhone) : that.vehicleLxrPhone != null)
            return false;
        if (payMoney != null ? !payMoney.equals(that.payMoney) : that.payMoney != null) return false;
        if (vehicleSize != null ? !vehicleSize.equals(that.vehicleSize) : that.vehicleSize != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (createIp != null ? !createIp.equals(that.createIp) : that.createIp != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (vehicleTrans ^ (vehicleTrans >>> 32));
        result = 31 * result + (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (orderNo != null ? orderNo.hashCode() : 0);
        result = 31 * result + (int) (prodId ^ (prodId >>> 32));
        result = 31 * result + (int) (wlUserId ^ (wlUserId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (vehicleLxr != null ? vehicleLxr.hashCode() : 0);
        result = 31 * result + (vehicleLxrPhone != null ? vehicleLxrPhone.hashCode() : 0);
        result = 31 * result + (int) (grabId ^ (grabId >>> 32));
        result = 31 * result + (int) (moneyId ^ (moneyId >>> 32));
        result = 31 * result + (payMoney != null ? payMoney.hashCode() : 0);
        result = 31 * result + (vehicleSize != null ? vehicleSize.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (createIp != null ? createIp.hashCode() : 0);
        result = 31 * result + (int) vehicleState;
        return result;
    }
}
