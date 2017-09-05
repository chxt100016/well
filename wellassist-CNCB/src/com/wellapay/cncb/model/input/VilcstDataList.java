package com.wellapay.cncb.model.input;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by Administrator on 2017/8/31.
 */
@XStreamAlias("row")
public class VilcstDataList {
    private String contactName;
    private String contactPhone;
    private String mailAddress;

    public VilcstDataList() {
    }

    public VilcstDataList(String contactName, String contactPhone, String mailAddress) {
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.mailAddress = mailAddress;
    }

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
}
