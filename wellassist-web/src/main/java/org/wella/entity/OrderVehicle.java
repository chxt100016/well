package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OrderVehicle {
    private Long orderVehicleId;

    private Long orderId;

    private Long zorderId;

    private String vehicleNo;

    private String vehicleHangingNo;

    private BigDecimal vehicleSize;

    private BigDecimal vehicleActualSize;

    private String driverName;

    private String driverPhone;

    private Date receiveActualDate;

    private Date deliverActualDate;

    private Date receiveExceptDate;

    public Long getOrderVehicleId() {
        return orderVehicleId;
    }

    public void setOrderVehicleId(Long orderVehicleId) {
        this.orderVehicleId = orderVehicleId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getZorderId() {
        return zorderId;
    }

    public void setZorderId(Long zorderId) {
        this.zorderId = zorderId;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo == null ? null : vehicleNo.trim();
    }

    public String getVehicleHangingNo() {
        return vehicleHangingNo;
    }

    public void setVehicleHangingNo(String vehicleHangingNo) {
        this.vehicleHangingNo = vehicleHangingNo == null ? null : vehicleHangingNo.trim();
    }

    public BigDecimal getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(BigDecimal vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public BigDecimal getVehicleActualSize() {
        return vehicleActualSize;
    }

    public void setVehicleActualSize(BigDecimal vehicleActualSize) {
        this.vehicleActualSize = vehicleActualSize;
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

    public Date getReceiveActualDate() {
        return receiveActualDate;
    }

    public void setReceiveActualDate(Date receiveActualDate) {
        this.receiveActualDate = receiveActualDate;
    }

    public Date getDeliverActualDate() {
        return deliverActualDate;
    }

    public void setDeliverActualDate(Date deliverActualDate) {
        this.deliverActualDate = deliverActualDate;
    }

    public Date getReceiveExceptDate() {
        return receiveExceptDate;
    }

    public void setReceiveExceptDate(Date receiveExceptDate) {
        this.receiveExceptDate = receiveExceptDate;
    }
}