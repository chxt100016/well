package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class Order {
    private long orderId;
    private String orderNo;
    private long prodId;
    private String prodName;
    private long fromRegionId;
    private String fromRegionAddr;
    private long toRegionId;
    private String toRegionAddr;
    private long userId;
    private String userLxr;
    private String userLxrPhone;
    private long supplierId;
    private BigDecimal saleNum;
    private BigDecimal saleMoney;
    private Date djModifyDate;
    private BigDecimal saleSjNum;
    private BigDecimal saleSjMoney;
    private BigDecimal sqMoney;
    private byte orderState;
    private byte orderType;
    private Date orderDate;
    private String orderIp;
    private byte kpState;
    private Date kpCompleteDate;
    private byte isSelfCar;
    private byte pjState;
    private Date pjDate;
    private String pjContent;
    private BigDecimal confirmPrice;
    private BigDecimal confirmNumber;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public long getProdId() {
        return prodId;
    }

    public void setProdId(long prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getFromRegionAddr() {
        return fromRegionAddr;
    }

    public void setFromRegionAddr(String fromRegionAddr) {
        this.fromRegionAddr = fromRegionAddr;
    }

    public long getFromRegionId() {
        return fromRegionId;
    }

    public void setFromRegionId(long fromRegionId) {
        this.fromRegionId = fromRegionId;
    }

    public long getToRegionId() {
        return toRegionId;
    }

    public void setToRegionId(long toRegionId) {
        this.toRegionId = toRegionId;
    }

    public String getToRegionAddr() {
        return toRegionAddr;
    }

    public void setToRegionAddr(String toRegionAddr) {
        this.toRegionAddr = toRegionAddr;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserLxr() {
        return userLxr;
    }

    public void setUserLxr(String userLxr) {
        this.userLxr = userLxr;
    }

    public String getUserLxrPhone() {
        return userLxrPhone;
    }

    public void setUserLxrPhone(String userLxrPhone) {
        this.userLxrPhone = userLxrPhone;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
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

    public byte getOrderState() {
        return orderState;
    }

    public void setOrderState(byte orderState) {
        this.orderState = orderState;
    }

    public byte getOrderType() {
        return orderType;
    }

    public void setOrderType(byte orderType) {
        this.orderType = orderType;
    }


    public String getOrderIp() {
        return orderIp;
    }

    public void setOrderIp(String orderIp) {
        this.orderIp = orderIp;
    }

    public byte getKpState() {
        return kpState;
    }

    public void setKpState(byte kpState) {
        this.kpState = kpState;
    }

    public byte getIsSelfCar() {
        return isSelfCar;
    }

    public void setIsSelfCar(byte isSelfCar) {
        this.isSelfCar = isSelfCar;
    }

    public byte getPjState() {
        return pjState;
    }

    public void setPjState(byte pjState) {
        this.pjState = pjState;
    }

    public Date getDjModifyDate() {
        return djModifyDate;
    }

    public void setDjModifyDate(Date djModifyDate) {
        this.djModifyDate = djModifyDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getKpCompleteDate() {
        return kpCompleteDate;
    }

    public void setKpCompleteDate(Date kpCompleteDate) {
        this.kpCompleteDate = kpCompleteDate;
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
        this.pjContent = pjContent;
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
}
