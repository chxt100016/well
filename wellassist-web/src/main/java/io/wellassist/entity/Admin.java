package io.wellassist.entity;

import java.util.Date;

/**
 * Created by liuwen on 2017/5/9.
 */
public class Admin {
    private int id;
    private String type;
    private String adminName;
    private String adminPass;
    private byte adminLevel;
    private String adminPhone;
    private int state;
    private Date regTime;
    private int creator;
    private String token;
    private String permission;
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPass() {
        return adminPass;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }

    public byte getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(byte adminLevel) {
        this.adminLevel = adminLevel;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin waAdmin = (Admin) o;

        if (id != waAdmin.id) return false;
        if (adminLevel != waAdmin.adminLevel) return false;
        if (state != waAdmin.state) return false;
        if (creator != waAdmin.creator) return false;
        if (type != null ? !type.equals(waAdmin.type) : waAdmin.type != null) return false;
        if (adminName != null ? !adminName.equals(waAdmin.adminName) : waAdmin.adminName != null) return false;
        if (adminPass != null ? !adminPass.equals(waAdmin.adminPass) : waAdmin.adminPass != null) return false;
        if (adminPhone != null ? !adminPhone.equals(waAdmin.adminPhone) : waAdmin.adminPhone != null) return false;
        if (regTime != null ? !regTime.equals(waAdmin.regTime) : waAdmin.regTime != null) return false;
        if (token != null ? !token.equals(waAdmin.token) : waAdmin.token != null) return false;
        if (permission != null ? !permission.equals(waAdmin.permission) : waAdmin.permission != null) return false;
        if (role != null ? !role.equals(waAdmin.role) : waAdmin.role != null) return false;

        return true;
    }

    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (adminName != null ? adminName.hashCode() : 0);
        result = 31 * result + (adminPass != null ? adminPass.hashCode() : 0);
        result = 31 * result + (int) adminLevel;
        result = 31 * result + (adminPhone != null ? adminPhone.hashCode() : 0);
        result = 31 * result + state;
        result = 31 * result + (regTime != null ? regTime.hashCode() : 0);
        result = 31 * result + creator;
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (permission != null ? permission.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
