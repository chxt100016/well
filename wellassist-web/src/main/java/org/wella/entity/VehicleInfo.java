package org.wella.entity;

/**
 * Created by Administrator on 2017/5/11.
 */
public class VehicleInfo {
    private long id;
    private long orderId;
    private String name;
    private String phone;
    private String carCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCarCode() {
        return carCode;
    }

    public void setCarCode(String carCode) {
        this.carCode = carCode;
    }
}
