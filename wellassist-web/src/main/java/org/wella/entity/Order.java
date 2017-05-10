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
    private String fromRegionId;
    private String fromRegionAddr;
    private String toRegionId;
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

    public String getFromRegionId() {
        return fromRegionId;
    }

    public void setFromRegionId(String fromRegionId) {
        this.fromRegionId = fromRegionId;
    }

    public String getFromRegionAddr() {
        return fromRegionAddr;
    }

    public void setFromRegionAddr(String fromRegionAddr) {
        this.fromRegionAddr = fromRegionAddr;
    }

    public String getToRegionId() {
        return toRegionId;
    }

    public void setToRegionId(String toRegionId) {
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

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order waOrder = (Order) o;

        if (orderId != waOrder.orderId) return false;
        if (prodId != waOrder.prodId) return false;
        if (userId != waOrder.userId) return false;
        if (supplierId != waOrder.supplierId) return false;
        if (orderState != waOrder.orderState) return false;
        if (orderType != waOrder.orderType) return false;
        if (kpState != waOrder.kpState) return false;
        if (isSelfCar != waOrder.isSelfCar) return false;
        if (pjState != waOrder.pjState) return false;
        if (orderNo != null ? !orderNo.equals(waOrder.orderNo) : waOrder.orderNo != null) return false;
        if (prodName != null ? !prodName.equals(waOrder.prodName) : waOrder.prodName != null) return false;
        if (fromRegionId != null ? !fromRegionId.equals(waOrder.fromRegionId) : waOrder.fromRegionId != null)
            return false;
        if (fromRegionAddr != null ? !fromRegionAddr.equals(waOrder.fromRegionAddr) : waOrder.fromRegionAddr != null)
            return false;
        if (toRegionId != null ? !toRegionId.equals(waOrder.toRegionId) : waOrder.toRegionId != null) return false;
        if (toRegionAddr != null ? !toRegionAddr.equals(waOrder.toRegionAddr) : waOrder.toRegionAddr != null)
            return false;
        if (userLxr != null ? !userLxr.equals(waOrder.userLxr) : waOrder.userLxr != null) return false;
        if (userLxrPhone != null ? !userLxrPhone.equals(waOrder.userLxrPhone) : waOrder.userLxrPhone != null)
            return false;
        if (saleNum != null ? !saleNum.equals(waOrder.saleNum) : waOrder.saleNum != null) return false;
        if (saleMoney != null ? !saleMoney.equals(waOrder.saleMoney) : waOrder.saleMoney != null) return false;
        if (djModifyDate != null ? !djModifyDate.equals(waOrder.djModifyDate) : waOrder.djModifyDate != null)
            return false;
        if (saleSjNum != null ? !saleSjNum.equals(waOrder.saleSjNum) : waOrder.saleSjNum != null) return false;
        if (saleSjMoney != null ? !saleSjMoney.equals(waOrder.saleSjMoney) : waOrder.saleSjMoney != null) return false;
        if (sqMoney != null ? !sqMoney.equals(waOrder.sqMoney) : waOrder.sqMoney != null) return false;
        if (orderDate != null ? !orderDate.equals(waOrder.orderDate) : waOrder.orderDate != null) return false;
        if (orderIp != null ? !orderIp.equals(waOrder.orderIp) : waOrder.orderIp != null) return false;
        if (kpCompleteDate != null ? !kpCompleteDate.equals(waOrder.kpCompleteDate) : waOrder.kpCompleteDate != null)
            return false;
        if (pjDate != null ? !pjDate.equals(waOrder.pjDate) : waOrder.pjDate != null) return false;
        if (pjContent != null ? !pjContent.equals(waOrder.pjContent) : waOrder.pjContent != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (orderNo != null ? orderNo.hashCode() : 0);
        result = 31 * result + (int) (prodId ^ (prodId >>> 32));
        result = 31 * result + (prodName != null ? prodName.hashCode() : 0);
        result = 31 * result + (fromRegionId != null ? fromRegionId.hashCode() : 0);
        result = 31 * result + (fromRegionAddr != null ? fromRegionAddr.hashCode() : 0);
        result = 31 * result + (toRegionId != null ? toRegionId.hashCode() : 0);
        result = 31 * result + (toRegionAddr != null ? toRegionAddr.hashCode() : 0);
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (userLxr != null ? userLxr.hashCode() : 0);
        result = 31 * result + (userLxrPhone != null ? userLxrPhone.hashCode() : 0);
        result = 31 * result + (int) (supplierId ^ (supplierId >>> 32));
        result = 31 * result + (saleNum != null ? saleNum.hashCode() : 0);
        result = 31 * result + (saleMoney != null ? saleMoney.hashCode() : 0);
        result = 31 * result + (djModifyDate != null ? djModifyDate.hashCode() : 0);
        result = 31 * result + (saleSjNum != null ? saleSjNum.hashCode() : 0);
        result = 31 * result + (saleSjMoney != null ? saleSjMoney.hashCode() : 0);
        result = 31 * result + (sqMoney != null ? sqMoney.hashCode() : 0);
        result = 31 * result + (int) orderState;
        result = 31 * result + (int) orderType;
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (orderIp != null ? orderIp.hashCode() : 0);
        result = 31 * result + (int) kpState;
        result = 31 * result + (kpCompleteDate != null ? kpCompleteDate.hashCode() : 0);
        result = 31 * result + (int) isSelfCar;
        result = 31 * result + (int) pjState;
        result = 31 * result + (pjDate != null ? pjDate.hashCode() : 0);
        result = 31 * result + (pjContent != null ? pjContent.hashCode() : 0);
        return result;
    }
}
