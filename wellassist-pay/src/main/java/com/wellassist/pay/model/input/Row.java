package com.wellassist.pay.model.input;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("row")
public class Row {
    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    private String contactName;//<!--联系人姓名 varchar(122) -->
    private String contactPhone;//<!--联系电话 varchar(20) -->
    private String mailAddress;//<!--邮件地址 varchar(152) -->
}
