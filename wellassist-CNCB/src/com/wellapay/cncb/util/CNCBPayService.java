package com.wellapay.cncb.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.wellapay.cncb.model.input.BalanceQuery;
import com.wellapay.cncb.model.input.InitAccount;
import com.wellapay.cncb.model.output.CommonOutput;

/**
 * Created by Administrator on 2017/8/30.
 */
public class CNCBPayService {

    public Object initAccount(InitAccount initAccount) throws Exception {
        HttpConnectionUtil http=new HttpConnectionUtil(CNCBConstants.CNCB_APIURL);
        http.init();
        XStream xStream=new XStream();
        xStream.processAnnotations(InitAccount.class);
        CommonUtil.fillJavabean(initAccount);
        String xml=xStream.toXML(initAccount);
        String top = "<?xml version=\"1.0\" encoding=\"GBK\"?> \n";
        xml=top+xml;
        System.out.println("请求报文为:");
        System.out.println(xml);
        System.out.println();
        System.out.println("响应报文为:");
        byte[] bys = http.postParams(xml, true);
        String result = new String(bys,"GB2312");
        System.out.println(result);
        Object obj=handle(result, CommonOutput.class);
        CommonOutput commonOutput=(CommonOutput)obj;
        System.out.println();
        System.out.println("响应model为:");
        System.out.println(commonOutput);
        return null;
    }

    public void sendStr(String xml) throws Exception {
        HttpConnectionUtil http=new HttpConnectionUtil(CNCBConstants.CNCB_APIURL);
        http.init();
        System.out.println("请求报文为:");
        System.out.println(xml);
        System.out.println();
        System.out.println("响应报文为:");
        byte[] bys = http.postParams(xml, true);
        String result = new String(bys,"GB2312");
        System.out.println(result);
    }

    public Object handle(String xml,Class clazz){
        XStream xStream = new XStream(new DomDriver());
        xStream.processAnnotations(clazz);
        Object obj = xStream.fromXML(xml);
        return obj;
    }

    public void balanceQuery(BalanceQuery bq) throws Exception {
        HttpConnectionUtil http=new HttpConnectionUtil(CNCBConstants.CNCB_APIURL);
        http.init();
        String xml=CommonUtil.CNCBModel2String(bq);
        System.out.println("请求报文为:");
        System.out.println(xml);
        System.out.println();
        System.out.println("响应报文为:");
        byte[] bys = http.postParams(xml, true);
        String result = new String(bys,"GBK");
        System.out.println(result);
    }
}
