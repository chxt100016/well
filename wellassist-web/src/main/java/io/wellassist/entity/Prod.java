package io.wellassist.entity;

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
    private String prodRegionId;
    private String prodRegionAddr;

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

    public String getProdRegionId() {
        return prodRegionId;
    }

    public void setProdRegionId(String prodRegionId) {
        this.prodRegionId = prodRegionId;
    }

    public String getProdRegionAddr() {
        return prodRegionAddr;
    }

    public void setProdRegionAddr(String prodRegionAddr) {
        this.prodRegionAddr = prodRegionAddr;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prod waProd = (Prod) o;

        if (prodId != waProd.prodId) return false;
        if (prodType != waProd.prodType) return false;
        if (saleNum != waProd.saleNum) return false;
        if (prodNum != waProd.prodNum) return false;
        if (userId != waProd.userId) return false;
        if (createUserId != waProd.createUserId) return false;
        if (vehicleSqMoneyType != waProd.vehicleSqMoneyType) return false;
        if (vehicleSqMoney != waProd.vehicleSqMoney) return false;
        if (prodState != waProd.prodState) return false;
        if (prodName != null ? !prodName.equals(waProd.prodName) : waProd.prodName != null) return false;
        if (prodImg != null ? !prodImg.equals(waProd.prodImg) : waProd.prodImg != null) return false;
        if (prodIntro != null ? !prodIntro.equals(waProd.prodIntro) : waProd.prodIntro != null) return false;
        if (createDate != null ? !createDate.equals(waProd.createDate) : waProd.createDate != null) return false;
        if (prodMoney != null ? !prodMoney.equals(waProd.prodMoney) : waProd.prodMoney != null) return false;
        if (prodLxr != null ? !prodLxr.equals(waProd.prodLxr) : waProd.prodLxr != null) return false;
        if (prodLxrPhone != null ? !prodLxrPhone.equals(waProd.prodLxrPhone) : waProd.prodLxrPhone != null)
            return false;
        if (prodRegionId != null ? !prodRegionId.equals(waProd.prodRegionId) : waProd.prodRegionId != null)
            return false;
        if (prodRegionAddr != null ? !prodRegionAddr.equals(waProd.prodRegionAddr) : waProd.prodRegionAddr != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (prodId ^ (prodId >>> 32));
        result = 31 * result + (int) prodType;
        result = 31 * result + (prodName != null ? prodName.hashCode() : 0);
        result = 31 * result + (prodImg != null ? prodImg.hashCode() : 0);
        result = 31 * result + (prodIntro != null ? prodIntro.hashCode() : 0);
        result = 31 * result + saleNum;
        result = 31 * result + prodNum;
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (int) (createUserId ^ (createUserId >>> 32));
        result = 31 * result + (prodMoney != null ? prodMoney.hashCode() : 0);
        result = 31 * result + (int) vehicleSqMoneyType;
        result = 31 * result + (int) vehicleSqMoney;
        result = 31 * result + (int) prodState;
        result = 31 * result + (prodLxr != null ? prodLxr.hashCode() : 0);
        result = 31 * result + (prodLxrPhone != null ? prodLxrPhone.hashCode() : 0);
        result = 31 * result + (prodRegionId != null ? prodRegionId.hashCode() : 0);
        result = 31 * result + (prodRegionAddr != null ? prodRegionAddr.hashCode() : 0);
        return result;
    }
}
