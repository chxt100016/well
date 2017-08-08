package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Long orderId;

    private String orderNo;

    private Long prodId;

    private String prodName;

    private Long fromRegionId;

    private String fromRegionAddr;

    private Long toRegionId;

    private String toRegionAddr;

    private Long userId;

    private String companyLxr;

    private String companyLxrPhone;

    private Long supplierId;

    private BigDecimal saleNum;

    private BigDecimal saleMoney;

    private Date djModifyDate;

    private BigDecimal saleSjNum;

    private BigDecimal saleSjMoney;

    private BigDecimal sqMoney;

    private Byte orderState;

    private Byte orderType;

    private Date orderDate;

    private String orderIp;

    private Byte kpState;

    private Date kpCompleteDate;

    private Byte isSelfCar;

    private Byte pjState;

    private Date pjDate;

    private String pjContent;

    private BigDecimal confirmPrice;

    private BigDecimal confirmNumber;

    private BigDecimal customerExceptCarriage;

    private byte hasQuestion;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    public Long getFromRegionId() {
        return fromRegionId;
    }

    public void setFromRegionId(Long fromRegionId) {
        this.fromRegionId = fromRegionId;
    }

    public String getFromRegionAddr() {
        return fromRegionAddr;
    }

    public void setFromRegionAddr(String fromRegionAddr) {
        this.fromRegionAddr = fromRegionAddr == null ? null : fromRegionAddr.trim();
    }

    public Long getToRegionId() {
        return toRegionId;
    }

    public void setToRegionId(Long toRegionId) {
        this.toRegionId = toRegionId;
    }

    public String getToRegionAddr() {
        return toRegionAddr;
    }

    public void setToRegionAddr(String toRegionAddr) {
        this.toRegionAddr = toRegionAddr == null ? null : toRegionAddr.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCompanyLxr() {
        return companyLxr;
    }

    public void setCompanyLxr(String companyLxr) {
        this.companyLxr = companyLxr == null ? null : companyLxr.trim();
    }

    public String getCompanyLxrPhone() {
        return companyLxrPhone;
    }

    public void setCompanyLxrPhone(String companyLxrPhone) {
        this.companyLxrPhone = companyLxrPhone == null ? null : companyLxrPhone.trim();
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public BigDecimal getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(BigDecimal saleNum) {
        this.saleNum = saleNum;
    }

    public BigDecimal getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(BigDecimal saleMoney) {
        this.saleMoney = saleMoney;
    }

    public Date getDjModifyDate() {
        return djModifyDate;
    }

    public void setDjModifyDate(Date djModifyDate) {
        this.djModifyDate = djModifyDate;
    }

    public BigDecimal getSaleSjNum() {
        return saleSjNum;
    }

    public void setSaleSjNum(BigDecimal saleSjNum) {
        this.saleSjNum = saleSjNum;
    }

    public BigDecimal getSaleSjMoney() {
        return saleSjMoney;
    }

    public void setSaleSjMoney(BigDecimal saleSjMoney) {
        this.saleSjMoney = saleSjMoney;
    }

    public BigDecimal getSqMoney() {
        return sqMoney;
    }

    public void setSqMoney(BigDecimal sqMoney) {
        this.sqMoney = sqMoney;
    }

    public Byte getOrderState() {
        return orderState;
    }

    public void setOrderState(Byte orderState) {
        this.orderState = orderState;
    }

    public Byte getOrderType() {
        return orderType;
    }

    public void setOrderType(Byte orderType) {
        this.orderType = orderType;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderIp() {
        return orderIp;
    }

    public void setOrderIp(String orderIp) {
        this.orderIp = orderIp == null ? null : orderIp.trim();
    }

    public Byte getKpState() {
        return kpState;
    }

    public void setKpState(Byte kpState) {
        this.kpState = kpState;
    }

    public Date getKpCompleteDate() {
        return kpCompleteDate;
    }

    public void setKpCompleteDate(Date kpCompleteDate) {
        this.kpCompleteDate = kpCompleteDate;
    }

    public Byte getIsSelfCar() {
        return isSelfCar;
    }

    public void setIsSelfCar(Byte isSelfCar) {
        this.isSelfCar = isSelfCar;
    }

    public Byte getPjState() {
        return pjState;
    }

    public void setPjState(Byte pjState) {
        this.pjState = pjState;
    }

    public Date getPjDate() {
        return pjDate;
    }

    public void setPjDate(Date pjDate) {
        this.pjDate = pjDate;
    }

    public String getPjContent() {
        return pjContent;
    }

    public void setPjContent(String pjContent) {
        this.pjContent = pjContent == null ? null : pjContent.trim();
    }

    public BigDecimal getConfirmPrice() {
        return confirmPrice;
    }

    public void setConfirmPrice(BigDecimal confirmPrice) {
        this.confirmPrice = confirmPrice;
    }

    public BigDecimal getConfirmNumber() {
        return confirmNumber;
    }

    public void setConfirmNumber(BigDecimal confirmNumber) {
        this.confirmNumber = confirmNumber;
    }

    public BigDecimal getCustomerExceptCarriage() {
        return customerExceptCarriage;
    }

    public void setCustomerExceptCarriage(BigDecimal customerExceptCarriage) {
        this.customerExceptCarriage = customerExceptCarriage;
    }

    public byte getHasQuestion() {
        return hasQuestion;
    }

    public void setHasQuestion(byte hasQuestion) {
        this.hasQuestion = hasQuestion;
    }
}