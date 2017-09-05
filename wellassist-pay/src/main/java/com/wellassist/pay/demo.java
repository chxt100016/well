package com.wellassist.pay;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/8/25.
 */
public class demo {
    public static void main(String [] args){
        ClassPathXmlApplicationContext xmlApplication = new ClassPathXmlApplicationContext("spring-http.xml");
    }
}
