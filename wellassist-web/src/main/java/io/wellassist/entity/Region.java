package io.wellassist.entity;


import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class Region {
    private Long regionId;
    private String regionName;
    private String regionCode;
    private int parentRegionId;
    private int layer;
    private int orderId;
    private int zipPhone;
    private int regionPost;
    private String isCity;
    private String regionMask;
    private String regionBanner;
    private String regionIcon;
    private String regionBannerOld;
    private int regionOnlineTotal;
    private int regionOnlineToday;
    private Date regionOnlineDate;

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public int getParentRegionId() {
        return parentRegionId;
    }

    public void setParentRegionId(int parentRegionId) {
        this.parentRegionId = parentRegionId;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getZipPhone() {
        return zipPhone;
    }

    public void setZipPhone(int zipPhone) {
        this.zipPhone = zipPhone;
    }

    public int getRegionPost() {
        return regionPost;
    }

    public void setRegionPost(int regionPost) {
        this.regionPost = regionPost;
    }

    public String getIsCity() {
        return isCity;
    }

    public void setIsCity(String isCity) {
        this.isCity = isCity;
    }

    public String getRegionMask() {
        return regionMask;
    }

    public void setRegionMask(String regionMask) {
        this.regionMask = regionMask;
    }

    public String getRegionBanner() {
        return regionBanner;
    }

    public void setRegionBanner(String regionBanner) {
        this.regionBanner = regionBanner;
    }

    public String getRegionIcon() {
        return regionIcon;
    }

    public void setRegionIcon(String regionIcon) {
        this.regionIcon = regionIcon;
    }

    public String getRegionBannerOld() {
        return regionBannerOld;
    }

    public void setRegionBannerOld(String regionBannerOld) {
        this.regionBannerOld = regionBannerOld;
    }

    public int getRegionOnlineTotal() {
        return regionOnlineTotal;
    }

    public void setRegionOnlineTotal(int regionOnlineTotal) {
        this.regionOnlineTotal = regionOnlineTotal;
    }

    public int getRegionOnlineToday() {
        return regionOnlineToday;
    }

    public void setRegionOnlineToday(int regionOnlineToday) {
        this.regionOnlineToday = regionOnlineToday;
    }

    public Date getRegionOnlineDate() {
        return regionOnlineDate;
    }

    public void setRegionOnlineDate(Date regionOnlineDate) {
        this.regionOnlineDate = regionOnlineDate;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Region waRegion = (Region) o;

        if (parentRegionId != waRegion.parentRegionId) return false;
        if (layer != waRegion.layer) return false;
        if (orderId != waRegion.orderId) return false;
        if (zipPhone != waRegion.zipPhone) return false;
        if (regionPost != waRegion.regionPost) return false;
        if (regionOnlineTotal != waRegion.regionOnlineTotal) return false;
        if (regionOnlineToday != waRegion.regionOnlineToday) return false;
        if (regionId != null ? !regionId.equals(waRegion.regionId) : waRegion.regionId != null) return false;
        if (regionName != null ? !regionName.equals(waRegion.regionName) : waRegion.regionName != null) return false;
        if (regionCode != null ? !regionCode.equals(waRegion.regionCode) : waRegion.regionCode != null) return false;
        if (isCity != null ? !isCity.equals(waRegion.isCity) : waRegion.isCity != null) return false;
        if (regionMask != null ? !regionMask.equals(waRegion.regionMask) : waRegion.regionMask != null) return false;
        if (regionBanner != null ? !regionBanner.equals(waRegion.regionBanner) : waRegion.regionBanner != null)
            return false;
        if (regionIcon != null ? !regionIcon.equals(waRegion.regionIcon) : waRegion.regionIcon != null) return false;
        if (regionBannerOld != null ? !regionBannerOld.equals(waRegion.regionBannerOld) : waRegion.regionBannerOld != null)
            return false;
        if (regionOnlineDate != null ? !regionOnlineDate.equals(waRegion.regionOnlineDate) : waRegion.regionOnlineDate != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result = regionId != null ? regionId.hashCode() : 0;
        result = 31 * result + (regionName != null ? regionName.hashCode() : 0);
        result = 31 * result + (regionCode != null ? regionCode.hashCode() : 0);
        result = 31 * result + parentRegionId;
        result = 31 * result + layer;
        result = 31 * result + orderId;
        result = 31 * result + zipPhone;
        result = 31 * result + regionPost;
        result = 31 * result + (isCity != null ? isCity.hashCode() : 0);
        result = 31 * result + (regionMask != null ? regionMask.hashCode() : 0);
        result = 31 * result + (regionBanner != null ? regionBanner.hashCode() : 0);
        result = 31 * result + (regionIcon != null ? regionIcon.hashCode() : 0);
        result = 31 * result + (regionBannerOld != null ? regionBannerOld.hashCode() : 0);
        result = 31 * result + regionOnlineTotal;
        result = 31 * result + regionOnlineToday;
        result = 31 * result + (regionOnlineDate != null ? regionOnlineDate.hashCode() : 0);
        return result;
    }
}
