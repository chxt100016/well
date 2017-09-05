package com.wellassist.pay.model.input;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("stream")
public class AccountQueryInput {
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getSubAccNo() {
        return subAccNo;
    }

    public void setSubAccNo(String subAccNo) {
        this.subAccNo = subAccNo;
    }

    private String action;//DLSBALQR</action>
    private String userName;//</userName><!--登录名 varchar(30)-->
    private String accountNo;//</accountNo><!--主体账号varchar(19)-->
    private String subAccNo;//</subAccNo><!--附属账号varchar(19)-->

    public static void main(String [] args){
        AccountQueryInput aqi=new AccountQueryInput();
        XStream xStream=new XStream();
        xStream.alias("xml",AccountQueryInput.class);
        aqi.setAccountNo("123456");
        aqi.setAction("sssss");
        aqi.setSubAccNo("cdcdcd");
        aqi.setUserName("fuck");
        String xml=xStream.toXML(aqi);
        System.out.println(xml);
    }
}
