package io.wellassist.entity;

/**
 * Created by liuwen on 2017/5/9.
 */
public class Userinfo {
    private long userId;
    private byte companyType;
    private byte spType;
    private String companyName;
    private String companyImg;
    private String companyYyZz;
    private String companyYyZzImg;
    private String companyKhXkz;
    private String companyXkzImg;
    private String companyTxkz;
    private String companyTxkzImg;
    private String companySh;
    private String zcRegionId;
    private String zcXxAddress;
    private String companyLpName;
    private String bgRegionId;
    private String bgXxAddress;
    private String companyLxr;
    private String companyLxrPhone;
    private String otherInfo;
    private String companyKhh;
    private String khAccount;
    private String khName;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public byte getCompanyType() {
        return companyType;
    }

    public void setCompanyType(byte companyType) {
        this.companyType = companyType;
    }

    public byte getSpType() {
        return spType;
    }

    public void setSpType(byte spType) {
        this.spType = spType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyImg() {
        return companyImg;
    }

    public void setCompanyImg(String companyImg) {
        this.companyImg = companyImg;
    }

    public String getCompanyYyZz() {
        return companyYyZz;
    }

    public void setCompanyYyZz(String companyYyZz) {
        this.companyYyZz = companyYyZz;
    }

    public String getCompanyYyZzImg() {
        return companyYyZzImg;
    }

    public void setCompanyYyZzImg(String companyYyZzImg) {
        this.companyYyZzImg = companyYyZzImg;
    }

    public String getCompanyKhXkz() {
        return companyKhXkz;
    }

    public void setCompanyKhXkz(String companyKhXkz) {
        this.companyKhXkz = companyKhXkz;
    }

    public String getCompanyXkzImg() {
        return companyXkzImg;
    }

    public void setCompanyXkzImg(String companyXkzImg) {
        this.companyXkzImg = companyXkzImg;
    }

    public String getCompanyTxkz() {
        return companyTxkz;
    }

    public void setCompanyTxkz(String companyTxkz) {
        this.companyTxkz = companyTxkz;
    }

    public String getCompanyTxkzImg() {
        return companyTxkzImg;
    }

    public void setCompanyTxkzImg(String companyTxkzImg) {
        this.companyTxkzImg = companyTxkzImg;
    }

    public String getCompanySh() {
        return companySh;
    }

    public void setCompanySh(String companySh) {
        this.companySh = companySh;
    }

    public String getZcRegionId() {
        return zcRegionId;
    }

    public void setZcRegionId(String zcRegionId) {
        this.zcRegionId = zcRegionId;
    }

    public String getZcXxAddress() {
        return zcXxAddress;
    }

    public void setZcXxAddress(String zcXxAddress) {
        this.zcXxAddress = zcXxAddress;
    }

    public String getCompanyLpName() {
        return companyLpName;
    }

    public void setCompanyLpName(String companyLpName) {
        this.companyLpName = companyLpName;
    }

    public String getBgRegionId() {
        return bgRegionId;
    }

    public void setBgRegionId(String bgRegionId) {
        this.bgRegionId = bgRegionId;
    }

    public String getBgXxAddress() {
        return bgXxAddress;
    }

    public void setBgXxAddress(String bgXxAddress) {
        this.bgXxAddress = bgXxAddress;
    }

    public String getCompanyLxr() {
        return companyLxr;
    }

    public void setCompanyLxr(String companyLxr) {
        this.companyLxr = companyLxr;
    }

    public String getCompanyLxrPhone() {
        return companyLxrPhone;
    }

    public void setCompanyLxrPhone(String companyLxrPhone) {
        this.companyLxrPhone = companyLxrPhone;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public String getCompanyKhh() {
        return companyKhh;
    }

    public void setCompanyKhh(String companyKhh) {
        this.companyKhh = companyKhh;
    }

    public String getKhAccount() {
        return khAccount;
    }

    public void setKhAccount(String khAccount) {
        this.khAccount = khAccount;
    }

    public String getKhName() {
        return khName;
    }

    public void setKhName(String khName) {
        this.khName = khName;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Userinfo that = (Userinfo) o;

        if (userId != that.userId) return false;
        if (companyType != that.companyType) return false;
        if (spType != that.spType) return false;
        if (companyName != null ? !companyName.equals(that.companyName) : that.companyName != null) return false;
        if (companyImg != null ? !companyImg.equals(that.companyImg) : that.companyImg != null) return false;
        if (companyYyZz != null ? !companyYyZz.equals(that.companyYyZz) : that.companyYyZz != null) return false;
        if (companyYyZzImg != null ? !companyYyZzImg.equals(that.companyYyZzImg) : that.companyYyZzImg != null)
            return false;
        if (companyKhXkz != null ? !companyKhXkz.equals(that.companyKhXkz) : that.companyKhXkz != null) return false;
        if (companyXkzImg != null ? !companyXkzImg.equals(that.companyXkzImg) : that.companyXkzImg != null)
            return false;
        if (companyTxkz != null ? !companyTxkz.equals(that.companyTxkz) : that.companyTxkz != null) return false;
        if (companyTxkzImg != null ? !companyTxkzImg.equals(that.companyTxkzImg) : that.companyTxkzImg != null)
            return false;
        if (companySh != null ? !companySh.equals(that.companySh) : that.companySh != null) return false;
        if (zcRegionId != null ? !zcRegionId.equals(that.zcRegionId) : that.zcRegionId != null) return false;
        if (zcXxAddress != null ? !zcXxAddress.equals(that.zcXxAddress) : that.zcXxAddress != null) return false;
        if (companyLpName != null ? !companyLpName.equals(that.companyLpName) : that.companyLpName != null)
            return false;
        if (bgRegionId != null ? !bgRegionId.equals(that.bgRegionId) : that.bgRegionId != null) return false;
        if (bgXxAddress != null ? !bgXxAddress.equals(that.bgXxAddress) : that.bgXxAddress != null) return false;
        if (companyLxr != null ? !companyLxr.equals(that.companyLxr) : that.companyLxr != null) return false;
        if (companyLxrPhone != null ? !companyLxrPhone.equals(that.companyLxrPhone) : that.companyLxrPhone != null)
            return false;
        if (otherInfo != null ? !otherInfo.equals(that.otherInfo) : that.otherInfo != null) return false;
        if (companyKhh != null ? !companyKhh.equals(that.companyKhh) : that.companyKhh != null) return false;
        if (khAccount != null ? !khAccount.equals(that.khAccount) : that.khAccount != null) return false;
        if (khName != null ? !khName.equals(that.khName) : that.khName != null) return false;

        return true;
    }

    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) companyType;
        result = 31 * result + (int) spType;
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (companyImg != null ? companyImg.hashCode() : 0);
        result = 31 * result + (companyYyZz != null ? companyYyZz.hashCode() : 0);
        result = 31 * result + (companyYyZzImg != null ? companyYyZzImg.hashCode() : 0);
        result = 31 * result + (companyKhXkz != null ? companyKhXkz.hashCode() : 0);
        result = 31 * result + (companyXkzImg != null ? companyXkzImg.hashCode() : 0);
        result = 31 * result + (companyTxkz != null ? companyTxkz.hashCode() : 0);
        result = 31 * result + (companyTxkzImg != null ? companyTxkzImg.hashCode() : 0);
        result = 31 * result + (companySh != null ? companySh.hashCode() : 0);
        result = 31 * result + (zcRegionId != null ? zcRegionId.hashCode() : 0);
        result = 31 * result + (zcXxAddress != null ? zcXxAddress.hashCode() : 0);
        result = 31 * result + (companyLpName != null ? companyLpName.hashCode() : 0);
        result = 31 * result + (bgRegionId != null ? bgRegionId.hashCode() : 0);
        result = 31 * result + (bgXxAddress != null ? bgXxAddress.hashCode() : 0);
        result = 31 * result + (companyLxr != null ? companyLxr.hashCode() : 0);
        result = 31 * result + (companyLxrPhone != null ? companyLxrPhone.hashCode() : 0);
        result = 31 * result + (otherInfo != null ? otherInfo.hashCode() : 0);
        result = 31 * result + (companyKhh != null ? companyKhh.hashCode() : 0);
        result = 31 * result + (khAccount != null ? khAccount.hashCode() : 0);
        result = 31 * result + (khName != null ? khName.hashCode() : 0);
        return result;
    }
}
