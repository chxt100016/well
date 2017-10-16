package com.wellapay.cncb.model.output.AccountTransQuery;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by Administrator on 2017/10/13.
 */
@XStreamAlias("stream")
public class AccountTransQueryOutput {
    private String status;
    private String statusText;
    private String returnRecords;
    private AccountTransQueryOutputList list;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getReturnRecords() {
        return returnRecords;
    }

    public void setReturnRecords(String returnRecords) {
        this.returnRecords = returnRecords;
    }

    public AccountTransQueryOutputList getList() {
        return list;
    }

    public void setList(AccountTransQueryOutputList list) {
        this.list = list;
    }
}
