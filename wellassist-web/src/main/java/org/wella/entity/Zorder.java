package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Zorder {
    private Long zorderId;

    private Long orderId;

    private BigDecimal zorderPrice;

    private BigDecimal zorderNum;

    private BigDecimal zorderMoney;

    private Date zorderDate;

    private String zorderBill;

    private Byte zorderState;

    private Date modifyDate;

    private Long userId;

    private Long adminId;

    private String sendComment;

    private String receiveComment;

    public Long getZorderId() {
        return zorderId;
    }

    public void setZorderId(Long zorderId) {
        this.zorderId = zorderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getZorderPrice() {
        return zorderPrice;
    }

    public void setZorderPrice(BigDecimal zorderPrice) {
        this.zorderPrice = zorderPrice;
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

    public Date getZorderDate() {
        return zorderDate;
    }

    public void setZorderDate(Date zorderDate) {
        this.zorderDate = zorderDate;
    }

    public String getZorderBill() {
        return zorderBill;
    }

    public void setZorderBill(String zorderBill) {
        this.zorderBill = zorderBill == null ? null : zorderBill.trim();
    }

    public Byte getZorderState() {
        return zorderState;
    }

    public void setZorderState(Byte zorderState) {
        this.zorderState = zorderState;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getSendComment() {
        return sendComment;
    }

    public void setSendComment(String sendComment) {
        this.sendComment = sendComment == null ? null : sendComment.trim();
    }

    public String getReceiveComment() {
        return receiveComment;
    }

    public void setReceiveComment(String receiveComment) {
        this.receiveComment = receiveComment == null ? null : receiveComment.trim();
    }
}