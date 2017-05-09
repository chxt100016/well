package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class Zorder {
    private long zorderId;
    private long orderId;
    private String lxName;
    private BigDecimal zorderDj;
    private BigDecimal zorderNum;
    private BigDecimal zorderMoney;
    private Date zorderDate;
    private long vehicleId;
    private byte zorderState;
    private Date modifyDate;
    private long userId;
    private long adminId;
    private byte isAuto;

    public long getZorderId() {
        return zorderId;
    }

    public void setZorderId(long zorderId) {
        this.zorderId = zorderId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getLxName() {
        return lxName;
    }

    public void setLxName(String lxName) {
        this.lxName = lxName;
    }

    public BigDecimal getZorderDj() {
        return zorderDj;
    }

    public void setZorderDj(BigDecimal zorderDj) {
        this.zorderDj = zorderDj;
    }

    public BigDecimal getZorderNum() {
        return zorderNum;
    }

    public void setZorderNum(BigDecimal zorderNum) {
        this.zorderNum = zorderNum;
    }

    public BigDecimal getZorderMoney() {
        return zorderMoney;
    }

    public void setZorderMoney(BigDecimal zorderMoney) {
        this.zorderMoney = zorderMoney;
    }


    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public byte getZorderState() {
        return zorderState;
    }

    public void setZorderState(byte zorderState) {
        this.zorderState = zorderState;
    }

    public Date getZorderDate() {
        return zorderDate;
    }

    public void setZorderDate(Date zorderDate) {
        this.zorderDate = zorderDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    public byte getIsAuto() {
        return isAuto;
    }

    public void setIsAuto(byte isAuto) {
        this.isAuto = isAuto;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Zorder waZorder = (Zorder) o;

        if (zorderId != waZorder.zorderId) return false;
        if (orderId != waZorder.orderId) return false;
        if (vehicleId != waZorder.vehicleId) return false;
        if (zorderState != waZorder.zorderState) return false;
        if (userId != waZorder.userId) return false;
        if (adminId != waZorder.adminId) return false;
        if (isAuto != waZorder.isAuto) return false;
        if (lxName != null ? !lxName.equals(waZorder.lxName) : waZorder.lxName != null) return false;
        if (zorderDj != null ? !zorderDj.equals(waZorder.zorderDj) : waZorder.zorderDj != null) return false;
        if (zorderNum != null ? !zorderNum.equals(waZorder.zorderNum) : waZorder.zorderNum != null) return false;
        if (zorderMoney != null ? !zorderMoney.equals(waZorder.zorderMoney) : waZorder.zorderMoney != null)
            return false;
        if (zorderDate != null ? !zorderDate.equals(waZorder.zorderDate) : waZorder.zorderDate != null) return false;
        if (modifyDate != null ? !modifyDate.equals(waZorder.modifyDate) : waZorder.modifyDate != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (zorderId ^ (zorderId >>> 32));
        result = 31 * result + (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (lxName != null ? lxName.hashCode() : 0);
        result = 31 * result + (zorderDj != null ? zorderDj.hashCode() : 0);
        result = 31 * result + (zorderNum != null ? zorderNum.hashCode() : 0);
        result = 31 * result + (zorderMoney != null ? zorderMoney.hashCode() : 0);
        result = 31 * result + (zorderDate != null ? zorderDate.hashCode() : 0);
        result = 31 * result + (int) (vehicleId ^ (vehicleId >>> 32));
        result = 31 * result + (int) zorderState;
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (adminId ^ (adminId >>> 32));
        result = 31 * result + (int) isAuto;
        return result;
    }
}
