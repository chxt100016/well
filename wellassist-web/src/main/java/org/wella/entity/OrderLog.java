package org.wella.entity;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by liuwen on 2017/5/12.
 */
public class OrderLog {
    private Long id;
    private Long orderId;
    private Double orderPrice;
    private Double orderNumber;
    private Date operationTime;
    private Long userId;
    private String operationIp;

    public String getOperationIp() {
        return operationIp;
    }

    public void setOperationIp(String operationIip) {
        this.operationIp = operationIip;
    }

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

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Double getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Double orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderLog that = (OrderLog) o;
        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (orderPrice != null ? !orderPrice.equals(that.orderPrice) : that.orderPrice != null) return false;
        if (orderNumber != null ? !orderNumber.equals(that.orderNumber) : that.orderNumber != null) return false;
        if (operationTime != null ? !operationTime.equals(that.operationTime) : that.operationTime != null)
            return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (orderPrice != null ? orderPrice.hashCode() : 0);
        result = 31 * result + (orderNumber != null ? orderNumber.hashCode() : 0);
        result = 31 * result + (operationTime != null ? operationTime.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
