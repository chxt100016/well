package com.wellapay.cncb.model.input;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by Administrator on 2017/9/4.
 */
public class RegisterList {
    @XStreamAsAttribute
    private String name;
    @XStreamImplicit
    private List<VilcstDataList> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<VilcstDataList> getList() {
        return list;
    }

    public void setList(List<VilcstDataList> list) {
        this.list = list;
    }
}
