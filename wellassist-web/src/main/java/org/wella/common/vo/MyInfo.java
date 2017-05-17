package org.wella.common.vo;

import java.util.HashMap;
import java.util.Map;

public class MyInfo {

    private String userId = "";
    private String adminLevel = "";
    private String userName = "";
    private String userPass = "";
    private String avatar;
    private String companyImg = "";
    private String phoneNum = "";
    private String state = "";
    private String regDate = "";
    private String zcRegionId = "";
    private String zcXxAddress = "";
    private String userPhone = "";
    private String userEmail = "";
    private String companyLxr = "";
    private String companyLxrPhone = "";
    private String userType = "0";
    private String permission = "";
    private String roles = "";
    private Map<String, Object> menuList = new HashMap();
    private String hangye = "";
    private int menuId = 0;
    private int subId = 0;

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public MyInfo() {
    }

    public String getCompanyImg() {
        return this.companyImg;
    }

    public void setCompanyImg(String companyImg) {
        this.companyImg = companyImg;
    }

    public String getUserPhone() {
        return this.userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getzcRegionId() {
        return this.zcRegionId;
    }

    public void setZcRegionId(String zcRegionId) {
        this.zcRegionId = zcRegionId;
    }

    public String getzcXxAddress() {
        return this.zcXxAddress;
    }

    public void setZcXxAddress(String zcXxAddress) {
        this.zcXxAddress = zcXxAddress;
    }

    public Map<String, Object> getMenuList() {
        return this.menuList;
    }

    public void setMenuList(Map<String, Object> menuList) {
        this.menuList = (Map)(menuList == null?new HashMap():menuList);
    }

    public String getUserId() {
        return this.userId;
    }

    public int getMenuId() {
        return this.menuId;
    }

    public void setMenuId(int id) {
        this.menuId = id;
    }

    public int getSubId() {
        return this.subId;
    }

    public void setSubId(int id) {
        this.subId = id;
    }

    public void setUserId(String userId) {
        this.userId = userId == null?"":userId;
    }

    public String getRoles() {
        return this.roles;
    }

    public void setRoles(String roles) {
        this.roles = roles == null?"":roles;
    }

    public String getAdminLevel() {
        return this.adminLevel;
    }

    public void setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel == null?"":adminLevel;
    }

    public String getPermission() {
        return this.permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null?"":permission;
    }

    public String getRegDate() {
        return this.regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate == null?"":regDate;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null?"":userName;
    }

    public String getUserPass() {
        return this.userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass == null?"":userPass;
    }

    public String getPhoneNUm() {
        return this.phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null?"":phoneNum;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state == null?"":state;
    }

    public String getHangye() {
        return this.hangye;
    }

    public void setHangye(String hangye) {
        this.hangye = hangye == null?"":hangye;
    }

    public String getCompanyLxr() {
        return this.companyLxr;
    }

    public void setCompanyLxr(String companyLxr) {
        this.companyLxr = companyLxr;
    }

    public String getCompanyLxrPhone() {
        return this.companyLxrPhone;
    }

    public void setCompanyLxrPhone(String companyLxrPhone) {
        this.companyLxrPhone = companyLxrPhone;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setData(String userId, String adminLevel, String userName, String userPass, String phoneNum, String state, String regDate, String permission, Map<String, Object> menuList, String roles, String zcRegionId, String zcXxAddress, String userEmail, String userPhone, String companyLxr, String companyLxrPhone, String companyImg, String userType) {
        this.setUserId(userId);
        this.setAdminLevel(adminLevel);
        this.setUserName(userName);
        this.setUserPass(userPass);
        this.setPhoneNum(phoneNum);
        this.setState(state);
        this.setRegDate(regDate);
        this.setPermission(permission);
        this.setMenuList(menuList);
        this.setRoles(roles);
        this.setZcRegionId(zcRegionId);
        this.setZcXxAddress(zcXxAddress);
        this.setUserEmail(userEmail);
        this.setUserPhone(userPhone);
        this.setCompanyLxr(companyLxr);
        this.setCompanyLxrPhone(companyLxrPhone);
        this.setCompanyImg(companyImg);
        this.setUserType(userType);
    }
}