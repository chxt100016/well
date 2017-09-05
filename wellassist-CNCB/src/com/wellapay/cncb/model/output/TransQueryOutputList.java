package com.wellapay.cncb.model.output;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by Administrator on 2017/9/4.
 */
@XStreamAlias("list")
public class TransQueryOutputList {
    @XStreamAsAttribute
    private String name;
    @XStreamImplicit
    private List<TransQueryOutputListEntity> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TransQueryOutputListEntity> getList() {
        return list;
    }

    public void setList(List<TransQueryOutputListEntity> list) {
        this.list = list;
    }
}
