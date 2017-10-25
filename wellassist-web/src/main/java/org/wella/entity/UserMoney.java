package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

public class UserMoney {
    private Long moneyId;

    private Long userId;

    private Byte jyType;

    private Long orderId;

    private String jyMc;

    private String content;

    private BigDecimal jyMoney;

    private BigDecimal jySjMoney;

    private Long mgrUserId;

    private String mgrIp;

    private Date jyDate;

    private Date completeDate;

    private Byte jyState;

    public Long getMoneyId() {
        return moneyId;
    }

    public void setMoneyId(Long moneyId) {
        this.moneyId = moneyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Byte getJyType() {
        return jyType;
    }

    public void setJyType(Byte jyType) {
        this.jyType = jyType;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getJyMc() {
        return jyMc;
    }

    public void setJyMc(String jyMc) {
        this.jyMc = jyMc == null ? null : jyMc.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public BigDecimal getJyMoney() {
        return jyMoney;
    }

    public void setJyMoney(BigDecimal jyMoney) {
        this.jyMoney = jyMoney;
    }

    public BigDecimal getJySjMoney() {
        return jySjMoney;
    }

    public void setJySjMoney(BigDecimal jySjMoney) {
        this.jySjMoney = jySjMoney;
    }

    public Long getMgrUserId() {
        return mgrUserId;
    }

    public void setMgrUserId(Long mgrUserId) {
        this.mgrUserId = mgrUserId;
    }

    public String getMgrIp() {
        return mgrIp;
    }

    public void setMgrIp(String mgrIp) {
        this.mgrIp = mgrIp == null ? null : mgrIp.trim();
    }

    public Date getJyDate() {
        return jyDate;
    }

    public void setJyDate(Date jyDate) {
        this.jyDate = jyDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public Byte getJyState() {
        return jyState;
    }

    public void setJyState(Byte jyState) {
        this.jyState = jyState;
    }
}