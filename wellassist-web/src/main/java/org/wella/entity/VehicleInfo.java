package org.wella.entity;

public class VehicleInfo {
    private Long id;

    private Long orderId;

    private String name;

    private String phone;

    private String vehicleNo;

    private String vehicleHangingNo;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
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
}