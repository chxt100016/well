package com.wellapay.cncb.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.wellapay.cncb.model.input.InitAccount;
import org.apache.poi.ss.formula.functions.T;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Administrator on 2017/8/30.
 */
public class CommonUtil {

    /**
     * 填充model
     * @param bean
     */
    public static void fillJavabean(Object bean){
        Class clazz = bean.getClass();//加载这个类
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(clazz);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        PropertyDescriptor[] propertyDescriptors=beanInfo.getPropertyDescriptors();
        for(PropertyDescriptor pro:propertyDescriptors){
            Object name=pro.getName();
            Method getMethod=pro.getReadMethod();
            Method setMethod= pro.getWriteMethod();
            Object type= pro.getPropertyType();
            if(!"class".equals(name)){
                try {
                    if(setMethod!=null&&getMethod.invoke(bean)==null){
                        try {
                            if(type==boolean.class||type==Boolean.class){
                                setMethod.invoke(bean, true);
                            }
                            if(type==String.class){
                                setMethod.invoke(bean, "");
                            }
                            if(type==double.class||type==Double.class){
                                setMethod.invoke(bean, 0.00D);
                            }
                            if(type==int.class||type==Integer.class){
                                setMethod.invoke(bean, 0);
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String createClientID(int size) {
        String genKey = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        genKey = sdf.format(new Date()) + randomInt(size);
        return genKey;
    }

    public static String randomInt(int length) {
        char[] chars = "1234567980".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for(int i = 0; i < length; ++i) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }

        return sb.toString();
    }

    /**
     * 将报文模型转化成报文
     * @param object
     * @return
     */
    public static String CNCBModel2String(Object object){
        XStream xStream=new XStream();
        xStream.processAnnotations(object.getClass());
        CommonUtil.fillJavabean(object);
        StringBuffer xml=new StringBuffer("<?xml version=\"1.0\" encoding=\"GBK\"?> \n");
        xml.append(xStream.toXML(object));
        return xml.toString();
    }

    public static <T> T String2CNCBModel(String xml,Class<T> clazz){
        XStream xStream = new XStream(new DomDriver());
        xStream.processAnnotations(clazz);
        T obj = (T)xStream.fromXML(xml);
        return obj;
    }
}
