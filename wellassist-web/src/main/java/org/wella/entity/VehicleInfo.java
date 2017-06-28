package org.wella.entity;

public class VehicleInfo {
    private Long id;

    private Long orderId;

    private String driverName;

    private String driverPhone;

    private String vehicleNo;

    private String vehicleHangingNo;

    private double vehicleSize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
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

    public double getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(double vehicleSize) {
        this.vehicleSize = vehicleSize;
    }
}