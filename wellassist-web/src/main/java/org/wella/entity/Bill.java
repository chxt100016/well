package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Bill {
    private Long billId;

    private String billNo;

    private String logisticsInfoIds;

    private String orderIds;

    private Long lixiId;

    private String billUnit;

    private Long customerUserId;

    private Long supplierId;

    private BigDecimal billMoney;

    private Byte billType;

    private Byte orderType;

    private String receiveSh;

    private String receiveName;

    private String receivePhone;

    private String receiveAddress;

    private String receiveCompanyName;

    private Byte billState;

    private String toUserName;

    private Date applyDate;

    private Date sendDate;

    private Date confirmDate;

    private byte kpType;

    private String kdNo;

    private String kdName;

    private String eBill;

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo == null ? null : billNo.trim();
    }

    public String getLogisticsInfoIds() {
        return logisticsInfoIds;
    }

    public void setLogisticsInfoIds(String logisticsInfoIds) {
        this.logisticsInfoIds = logisticsInfoIds == null ? null : logisticsInfoIds.trim();
    }

    public String getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(String orderIds) {
        this.orderIds = orderIds == null ? null : orderIds.trim();
    }

    public Long getLixiId() {
        return lixiId;
    }

    public void setLixiId(Long lixiId) {
        this.lixiId = lixiId;
    }

    public String getBillUnit() {
        return billUnit;
    }

    public void setBillUnit(String billUnit) {
        this.billUnit = billUnit == null ? null : billUnit.trim();
    }

    public Long getCustomerUserId() {
        return customerUserId;
    }

    public void setCustomerUserId(Long customerUserId) {
        this.customerUserId = customerUserId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public BigDecimal getBillMoney() {
        return billMoney;
    }

    public void setBillMoney(BigDecimal billMoney) {
        this.billMoney = billMoney;
    }

    public Byte getBillType() {
        return billType;
    }

    public void setBillType(Byte billType) {
        this.billType = billType;
    }

    public Byte getOrderType() {
        return orderType;
    }

    public void setOrderType(Byte orderType) {
        this.orderType = orderType;
    }

    public String getReceiveSh() {
        return receiveSh;
    }

    public void setReceiveSh(String receiveSh) {
        this.receiveSh = receiveSh == null ? null : receiveSh.trim();
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName == null ? null : receiveName.trim();
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone == null ? null : receivePhone.trim();
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress == null ? null : receiveAddress.trim();
    }

    public String getReceiveCompanyName() {
        return receiveCompanyName;
    }

    public void setReceiveCompanyName(String receiveCompanyName) {
        this.receiveCompanyName = receiveCompanyName == null ? null : receiveCompanyName.trim();
    }

    public Byte getBillState() {
        return billState;
    }

    public void setBillState(Byte billState) {
        this.billState = billState;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName == null ? null : toUserName.trim();
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public byte getKpType() {
        return kpType;
    }

    public void setKpType(byte kpType) {
        this.kpType = kpType;
    }

    public String getKdNo() {
        return kdNo;
    }

    public void setKdNo(String kdNo) {
        this.kdNo = kdNo == null ? null : kdNo.trim();
    }

    public String getKdName() {
        return kdName;
    }

    public void setKdName(String kdName) {
        this.kdName = kdName == null ? null : kdName.trim();
    }

    public String geteBill() {
        return eBill;
    }

    public void seteBill(String eBill) {
        this.eBill = eBill == null ? null : eBill.trim();
    }
}