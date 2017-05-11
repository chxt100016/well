package org.wella.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CommonUtil {
    public CommonUtil() {
    }
    public static String genKey(int size) {
        String genKey = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
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
}