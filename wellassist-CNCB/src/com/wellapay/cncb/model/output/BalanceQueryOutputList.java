package com.wellapay.cncb.model.output;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by Administrator on 2017/9/4.
 */
@XStreamAlias("list")
public class BalanceQueryOutputList {
    @XStreamImplicit
    private List<BalanceQueryOutputListEntity> list;
    @XStreamAsAttribute
    private String name;

    public List<BalanceQueryOutputListEntity> getList() {
        return list;
    }

    public void setList(List<BalanceQueryOutputListEntity> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
