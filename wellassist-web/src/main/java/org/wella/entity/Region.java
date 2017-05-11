package org.wella.entity;


import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class Region {
    private Long regionId;
    private String regionName;
    private String regionCode;
    private Long parentRegionId;
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

    public Long getParentRegionId() {
        return parentRegionId;
    }

    public void setParentRegionId(Long parentRegionId) {
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


}
