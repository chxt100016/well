package org.wella.entity;

public class VehicleGrabInfo {
    private Long vehicleGrabInfoId;

    private Long vehicleGrabId;

    private String driverName;

    private String driverPhone;

    private String vehicleNo;

    private String vehicleHangingNo;

    private Double vehicleSize;

    public Long getVehicleGrabInfoId() {
        return vehicleGrabInfoId;
    }

    public void setVehicleGrabInfoId(Long vehicleGrabInfoId) {
        this.vehicleGrabInfoId = vehicleGrabInfoId;
    }

    public Long getVehicleGrabId() {
        return vehicleGrabId;
    }

    public void setVehicleGrabId(Long vehicleGrabId) {
        this.vehicleGrabId = vehicleGrabId;
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

    public Double getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(Double vehicleSize) {
        this.vehicleSize = vehicleSize;
    }
}