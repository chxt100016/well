package org.wella.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class Bill {
    private long billId;
    private String billNo;
    private long sortId;
    private long orderId;
    private long lixiId;
    private String billUnit;
    private long supplierId;
    private BigDecimal billMoney;
    private String billSh;
    private byte billState;
    private String toUserName;
    private Date billDate;
    private Date tjDate;
    private String kdNo;
    private String kdName;
    private Date confirmDate;
    private long confirmUserId;

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public long getSortId() {
        return sortId;
    }

    public void setSortId(long sortId) {
        this.sortId = sortId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getLixiId() {
        return lixiId;
    }

    public void setLixiId(long lixiId) {
        this.lixiId = lixiId;
    }

    public String getBillUnit() {
        return billUnit;
    }

    public void setBillUnit(String billUnit) {
        this.billUnit = billUnit;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public BigDecimal getBillMoney() {
        return billMoney;
    }

    public void setBillMoney(BigDecimal billMoney) {
        this.billMoney = billMoney;
    }

    public String getBillSh() {
        return billSh;
    }

    public void setBillSh(String billSh) {
        this.billSh = billSh;
    }

    public byte getBillState() {
        return billState;
    }

    public void setBillState(byte billState) {
        this.billState = billState;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }


    public String getKdNo() {
        return kdNo;
    }

    public void setKdNo(String kdNo) {
        this.kdNo = kdNo;
    }

    public String getKdName() {
        return kdName;
    }

    public void setKdName(String kdName) {
        this.kdName = kdName;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Date getTjDate() {
        return tjDate;
    }

    public void setTjDate(Date tjDate) {
        this.tjDate = tjDate;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public long getConfirmUserId() {
        return confirmUserId;
    }

    public void setConfirmUserId(long confirmUserId) {
        this.confirmUserId = confirmUserId;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bill waBill = (Bill) o;

        if (billId != waBill.billId) return false;
        if (sortId != waBill.sortId) return false;
        if (orderId != waBill.orderId) return false;
        if (lixiId != waBill.lixiId) return false;
        if (supplierId != waBill.supplierId) return false;
        if (billState != waBill.billState) return false;
        if (confirmUserId != waBill.confirmUserId) return false;
        if (billNo != null ? !billNo.equals(waBill.billNo) : waBill.billNo != null) return false;
        if (billUnit != null ? !billUnit.equals(waBill.billUnit) : waBill.billUnit != null) return false;
        if (billMoney != null ? !billMoney.equals(waBill.billMoney) : waBill.billMoney != null) return false;
        if (billSh != null ? !billSh.equals(waBill.billSh) : waBill.billSh != null) return false;
        if (toUserName != null ? !toUserName.equals(waBill.toUserName) : waBill.toUserName != null) return false;
        if (billDate != null ? !billDate.equals(waBill.billDate) : waBill.billDate != null) return false;
        if (tjDate != null ? !tjDate.equals(waBill.tjDate) : waBill.tjDate != null) return false;
        if (kdNo != null ? !kdNo.equals(waBill.kdNo) : waBill.kdNo != null) return false;
        if (kdName != null ? !kdName.equals(waBill.kdName) : waBill.kdName != null) return false;
        if (confirmDate != null ? !confirmDate.equals(waBill.confirmDate) : waBill.confirmDate != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (billId ^ (billId >>> 32));
        result = 31 * result + (billNo != null ? billNo.hashCode() : 0);
        result = 31 * result + (int) (sortId ^ (sortId >>> 32));
        result = 31 * result + (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (int) (lixiId ^ (lixiId >>> 32));
        result = 31 * result + (billUnit != null ? billUnit.hashCode() : 0);
        result = 31 * result + (int) (supplierId ^ (supplierId >>> 32));
        result = 31 * result + (billMoney != null ? billMoney.hashCode() : 0);
        result = 31 * result + (billSh != null ? billSh.hashCode() : 0);
        result = 31 * result + (int) billState;
        result = 31 * result + (toUserName != null ? toUserName.hashCode() : 0);
        result = 31 * result + (billDate != null ? billDate.hashCode() : 0);
        result = 31 * result + (tjDate != null ? tjDate.hashCode() : 0);
        result = 31 * result + (kdNo != null ? kdNo.hashCode() : 0);
        result = 31 * result + (kdName != null ? kdName.hashCode() : 0);
        result = 31 * result + (confirmDate != null ? confirmDate.hashCode() : 0);
        result = 31 * result + (int) (confirmUserId ^ (confirmUserId >>> 32));
        return result;
    }
}
