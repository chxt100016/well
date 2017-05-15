package org.wella.entity;

import java.util.Date;

/**
 * Created by liuwen on 2017/5/12.
 */
public class LogisticsInfo {
    private long logisticsId;
    private Long orderId;
    private String fromAddress;
    private String toAddress;
    private Double number;
    private Double prePayment;
    private byte state;
    private long prodId;
    private String prodName;
    private long customerUserId;
    private String customerUserName;
    private long sellerUserId;
    private String sellerUserName;
    private Date orderDate;

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    private Date deliverDate;



    public long getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(long logisticsId) {
        this.logisticsId = logisticsId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public Double getPrePayment() {
        return prePayment;
    }

    public void setPrePayment(Double prePayment) {
        this.prePayment = prePayment;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public long getProdId() {
        return prodId;
    }

    public void setProdId(long prodId) {
        this.prodId = prodId;
    }

    public long getCustomerUserId() {
        return customerUserId;
    }

    public void setCustomerUserId(long customerUserId) {
        this.customerUserId = customerUserId;
    }

    public long getSellerUserId() {
        return sellerUserId;
    }

    public void setSellerUserId(long sellerUserId) {
        this.sellerUserId = sellerUserId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getCustomerUserName() {
        return customerUserName;
    }

    public void setCustomerUserName(String customerUserName) {
        this.customerUserName = customerUserName;
    }

    public String getSellerUserName() {
        return sellerUserName;
    }

    public void setSellerUserName(String sellerUserName) {
        this.sellerUserName = sellerUserName;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogisticsInfo that = (LogisticsInfo) o;

        if (state != that.state) return false;
        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (fromAddress != null ? !fromAddress.equals(that.fromAddress) : that.fromAddress != null) return false;
        if (toAddress != null ? !toAddress.equals(that.toAddress) : that.toAddress != null) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (prePayment != null ? !prePayment.equals(that.prePayment) : that.prePayment != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (logisticsId ^ (logisticsId >>> 32));
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (fromAddress != null ? fromAddress.hashCode() : 0);
        result = 31 * result + (toAddress != null ? toAddress.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (prePayment != null ? prePayment.hashCode() : 0);
        result = 31 * result + (int) state;
        return result;
    }
}
