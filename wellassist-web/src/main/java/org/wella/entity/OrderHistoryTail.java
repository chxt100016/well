package org.wella.entity;

import java.util.Date;

public class OrderHistoryTail {
    private Long orderHistoryTailId;

    private Long orderId;

    private String orderNo;

    private Byte tailType;

    private Date historyDate;

    private String content;

    private String content1;

    private String content2;

    private String content3;

    private Date tailDate;

    public Long getOrderHistoryTailId() {
        return orderHistoryTailId;
    }

    public void setOrderHistoryTailId(Long orderHistoryTailId) {
        this.orderHistoryTailId = orderHistoryTailId;
    }

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

    public Byte getTailType() {
        return tailType;
    }

    public void setTailType(Byte tailType) {
        this.tailType = tailType;
    }

    public Date getHistoryDate() {
        return historyDate;
    }

    public void setHistoryDate(Date historyDate) {
        this.historyDate = historyDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1 == null ? null : content1.trim();
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2 == null ? null : content2.trim();
    }

    public String getContent3() {
        return content3;
    }

    public void setContent3(String content3) {
        this.content3 = content3 == null ? null : content3.trim();
    }

    public Date getTailDate() {
        return tailDate;
    }

    public void setTailDate(Date tailDate) {
        this.tailDate = tailDate;
    }
}