package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class Prod {
    private long prodId;
    private byte prodType;
    private String prodName;
    private String prodImg;
    private String prodIntro;
    private int saleNum;
    private int prodNum;
    private long userId;
    private Date createDate;
    private long createUserId;
    private BigDecimal prodMoney;
    private byte vehicleSqMoneyType;
    private byte vehicleSqMoney;
    private byte prodState;
    private String prodLxr;
    private String prodLxrPhone;
    private long prodRegionId;
    private String prodRegionAddr;
    private BigDecimal prodPrice;


    public long getProdId() {
        return prodId;
    }

    public void setProdId(long prodId) {
        this.prodId = prodId;
    }

    public byte getProdType() {
        return prodType;
    }

    public void setProdType(byte prodType) {
        this.prodType = prodType;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdImg() {
        return prodImg;
    }

    public void setProdImg(String prodImg) {
        this.prodImg = prodImg;
    }

    public String getProdIntro() {
        return prodIntro;
    }

    public void setProdIntro(String prodIntro) {
        this.prodIntro = prodIntro;
    }

    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }

    public int getProdNum() {
        return prodNum;
    }

    public void setProdNum(int prodNum) {
        this.prodNum = prodNum;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(long createUserId) {
        this.createUserId = createUserId;
    }

    public BigDecimal getProdMoney() {
        return prodMoney;
    }

    public void setProdMoney(BigDecimal prodMoney) {
        this.prodMoney = prodMoney;
    }

    public byte getVehicleSqMoneyType() {
        return vehicleSqMoneyType;
    }

    public void setVehicleSqMoneyType(byte vehicleSqMoneyType) {
        this.vehicleSqMoneyType = vehicleSqMoneyType;
    }

    public byte getVehicleSqMoney() {
        return vehicleSqMoney;
    }

    public void setVehicleSqMoney(byte vehicleSqMoney) {
        this.vehicleSqMoney = vehicleSqMoney;
    }

    public byte getProdState() {
        return prodState;
    }

    public void setProdState(byte prodState) {
        this.prodState = prodState;
    }

    public String getProdLxr() {
        return prodLxr;
    }

    public void setProdLxr(String prodLxr) {
        this.prodLxr = prodLxr;
    }

    public String getProdLxrPhone() {
        return prodLxrPhone;
    }

    public void setProdLxrPhone(String prodLxrPhone) {
        this.prodLxrPhone = prodLxrPhone;
    }

    public long getProdRegionId() {
        return prodRegionId;
    }

    public void setProdRegionId(long prodRegionId) {
        this.prodRegionId = prodRegionId;
    }

    public String getProdRegionAddr() {
        return prodRegionAddr;
    }

    public void setProdRegionAddr(String prodRegionAddr) {
        this.prodRegionAddr = prodRegionAddr;
    }

    public BigDecimal getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(BigDecimal prodPrice) {
        this.prodPrice = prodPrice;
    }
}
