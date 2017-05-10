package org.wella.entity;

/**
 * Created by liuwen on 2017/5/9.
 */
public class Menu {
    private int menuId;
    private String title;
    private String url;
    private int parentId;
    private int orderNum;
    private byte siteType;
    private String className;
    private Integer menuType;
    private int userType;
    private Byte isShow;

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public byte getSiteType() {
        return siteType;
    }

    public void setSiteType(byte siteType) {
        this.siteType = siteType;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public Byte getIsShow() {
        return isShow;
    }

    public void setIsShow(Byte isShow) {
        this.isShow = isShow;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu waMenu = (Menu) o;

        if (menuId != waMenu.menuId) return false;
        if (parentId != waMenu.parentId) return false;
        if (orderNum != waMenu.orderNum) return false;
        if (siteType != waMenu.siteType) return false;
        if (userType != waMenu.userType) return false;
        if (title != null ? !title.equals(waMenu.title) : waMenu.title != null) return false;
        if (url != null ? !url.equals(waMenu.url) : waMenu.url != null) return false;
        if (className != null ? !className.equals(waMenu.className) : waMenu.className != null) return false;
        if (menuType != null ? !menuType.equals(waMenu.menuType) : waMenu.menuType != null) return false;
        if (isShow != null ? !isShow.equals(waMenu.isShow) : waMenu.isShow != null) return false;

        return true;
    }

    public int hashCode() {
        int result = menuId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + parentId;
        result = 31 * result + orderNum;
        result = 31 * result + (int) siteType;
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + (menuType != null ? menuType.hashCode() : 0);
        result = 31 * result + userType;
        result = 31 * result + (isShow != null ? isShow.hashCode() : 0);
        return result;
    }
}
