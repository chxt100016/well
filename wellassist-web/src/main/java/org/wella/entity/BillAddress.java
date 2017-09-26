package org.wella.entity;

public class BillAddress {
    private Long waBillAddressId;

    private Long userId;

    private String receiveName;

    private String receiveAddress;

    private String receiveCompanyName;

    private String receiveSh;

    private String receivePhone;

    private String bankName;

    private String bankAccount;

    public Long getWaBillAddressId() {
        return waBillAddressId;
    }

    public void setWaBillAddressId(Long waBillAddressId) {
        this.waBillAddressId = waBillAddressId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName == null ? null : receiveName.trim();
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress == null ? null : receiveAddress.trim();
    }

    public String getReceiveCompanyName() {
        return receiveCompanyName;
    }

    public void setReceiveCompanyName(String receiveCompanyName) {
        this.receiveCompanyName = receiveCompanyName == null ? null : receiveCompanyName.trim();
    }

    public String getReceiveSh() {
        return receiveSh;
    }

    public void setReceiveSh(String receiveSh) {
        this.receiveSh = receiveSh == null ? null : receiveSh.trim();
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone == null ? null : receivePhone.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
    }
}