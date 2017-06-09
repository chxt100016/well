package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

public class VehicleGrab {
    private Long vehicleGrabId;

    private Long logisticsInfoId;

    private Long orderId;

    private Long senderUserId;

    private String vehicleNo;

    private Integer vehicleSize;

    private BigDecimal grabMoney;

    private Date deliverDate;

    private Date receiveDate;

    private String driverName;

    private String driverPhone;

    private Date grabDate;

    private String grabReason;

    private Byte grabState;

    public Long getVehicleGrabId() {
        return vehicleGrabId;
    }

    public void setVehicleGrabId(Long vehicleGrabId) {
        this.vehicleGrabId = vehicleGrabId;
    }

    public Long getLogisticsInfoId() {
        return logisticsInfoId;
    }

    public void setLogisticsInfoId(Long logisticsInfoId) {
        this.logisticsInfoId = logisticsInfoId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(Long senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo == null ? null : vehicleNo.trim();
    }

    public Integer getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(Integer vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public BigDecimal getGrabMoney() {
        return grabMoney;
    }

    public void setGrabMoney(BigDecimal grabMoney) {
        this.grabMoney = grabMoney;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName == null ? null : driverName.trim();
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone == null ? null : driverPhone.trim();
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
        this.grabReason = grabReason == null ? null : grabReason.trim();
    }

    public Byte getGrabState() {
        return grabState;
    }

    public void setGrabState(Byte grabState) {
        this.grabState = grabState;
    }
}