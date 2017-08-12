package com.wellassist.pay.model.output;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;
public class UserData {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Row> getRow() {
        return row;
    }

    public void setRow(List<Row> row) {
        this.row = row;
    }
    @XStreamAsAttribute
    private String name="userDataList";
    @XStreamImplicit
    private List<Row> row;
}
