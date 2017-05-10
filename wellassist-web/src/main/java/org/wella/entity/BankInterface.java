package org.wella.entity;

/**
 * Created by liuwen on 2017/5/9.
 */
public class BankInterface {
    private long bankId;
    private String bankMc;
    private String khh;
    private String bankNo;
    private String bankMy;
    private String bankMy2;
    private String bankJkUrl;
    private String bankJkUrl2;
    private byte moneyType;
    private int moneyRate;
    private String otherInfo;
    private String khhMc;
    private String bankAccount;
    private byte bankState;
    private byte backType;

    public long getBankId() {
        return bankId;
    }

    public void setBankId(long bankId) {
        this.bankId = bankId;
    }

    public String getBankMc() {
        return bankMc;
    }

    public void setBankMc(String bankMc) {
        this.bankMc = bankMc;
    }

    public String getKhh() {
        return khh;
    }

    public void setKhh(String khh) {
        this.khh = khh;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getBankMy() {
        return bankMy;
    }

    public void setBankMy(String bankMy) {
        this.bankMy = bankMy;
    }

    public String getBankMy2() {
        return bankMy2;
    }

    public void setBankMy2(String bankMy2) {
        this.bankMy2 = bankMy2;
    }

    public String getBankJkUrl() {
        return bankJkUrl;
    }

    public void setBankJkUrl(String bankJkUrl) {
        this.bankJkUrl = bankJkUrl;
    }

    public String getBankJkUrl2() {
        return bankJkUrl2;
    }

    public void setBankJkUrl2(String bankJkUrl2) {
        this.bankJkUrl2 = bankJkUrl2;
    }

    public byte getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(byte moneyType) {
        this.moneyType = moneyType;
    }

    public int getMoneyRate() {
        return moneyRate;
    }

    public void setMoneyRate(int moneyRate) {
        this.moneyRate = moneyRate;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public String getKhhMc() {
        return khhMc;
    }

    public void setKhhMc(String khhMc) {
        this.khhMc = khhMc;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public byte getBankState() {
        return bankState;
    }

    public void setBankState(byte bankState) {
        this.bankState = bankState;
    }

    public byte getBackType() {
        return backType;
    }

    public void setBackType(byte backType) {
        this.backType = backType;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankInterface that = (BankInterface) o;

        if (bankId != that.bankId) return false;
        if (moneyType != that.moneyType) return false;
        if (moneyRate != that.moneyRate) return false;
        if (bankState != that.bankState) return false;
        if (backType != that.backType) return false;
        if (bankMc != null ? !bankMc.equals(that.bankMc) : that.bankMc != null) return false;
        if (khh != null ? !khh.equals(that.khh) : that.khh != null) return false;
        if (bankNo != null ? !bankNo.equals(that.bankNo) : that.bankNo != null) return false;
        if (bankMy != null ? !bankMy.equals(that.bankMy) : that.bankMy != null) return false;
        if (bankMy2 != null ? !bankMy2.equals(that.bankMy2) : that.bankMy2 != null) return false;
        if (bankJkUrl != null ? !bankJkUrl.equals(that.bankJkUrl) : that.bankJkUrl != null) return false;
        if (bankJkUrl2 != null ? !bankJkUrl2.equals(that.bankJkUrl2) : that.bankJkUrl2 != null) return false;
        if (otherInfo != null ? !otherInfo.equals(that.otherInfo) : that.otherInfo != null) return false;
        if (khhMc != null ? !khhMc.equals(that.khhMc) : that.khhMc != null) return false;
        if (bankAccount != null ? !bankAccount.equals(that.bankAccount) : that.bankAccount != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (bankId ^ (bankId >>> 32));
        result = 31 * result + (bankMc != null ? bankMc.hashCode() : 0);
        result = 31 * result + (khh != null ? khh.hashCode() : 0);
        result = 31 * result + (bankNo != null ? bankNo.hashCode() : 0);
        result = 31 * result + (bankMy != null ? bankMy.hashCode() : 0);
        result = 31 * result + (bankMy2 != null ? bankMy2.hashCode() : 0);
        result = 31 * result + (bankJkUrl != null ? bankJkUrl.hashCode() : 0);
        result = 31 * result + (bankJkUrl2 != null ? bankJkUrl2.hashCode() : 0);
        result = 31 * result + (int) moneyType;
        result = 31 * result + moneyRate;
        result = 31 * result + (otherInfo != null ? otherInfo.hashCode() : 0);
        result = 31 * result + (khhMc != null ? khhMc.hashCode() : 0);
        result = 31 * result + (bankAccount != null ? bankAccount.hashCode() : 0);
        result = 31 * result + (int) bankState;
        result = 31 * result + (int) backType;
        return result;
    }
}
