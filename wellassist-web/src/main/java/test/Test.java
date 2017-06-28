package test;

import java.util.Objects;

/**
 * Created by Administrator on 2017/6/28.
 */
public class Test {
    public static void main(String [] args){
        Object o1=(Object)1;
        Object o2=(Object)1.2;
        String s1=o1.toString();
        String s2=o2.toString();
        System.out.println(s1);
        System.out.println(s2);
    }
}
