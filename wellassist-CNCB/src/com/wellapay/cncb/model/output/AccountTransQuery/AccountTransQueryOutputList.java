package com.wellapay.cncb.model.output.AccountTransQuery;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by Administrator on 2017/10/13.
 */
@XStreamAlias("list")
public class AccountTransQueryOutputList {
    @XStreamAsAttribute
    private final String name="userDataList";

    @XStreamImplicit
    private List<AccountTransQueryOutputListEntity> list;

    public String getName() {
        return name;
    }

    public List<AccountTransQueryOutputListEntity> getList() {
        return list;
    }

    public void setList(List<AccountTransQueryOutputListEntity> list) {
        this.list = list;
    }
}
