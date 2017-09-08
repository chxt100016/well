package org.wella.utils;


import com.wellapay.cncb.util.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Map;

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

    /**
     * 字符串转Date
     * @param dateStr
     * @param format
     * @return
     */
    public Date str2Date(String dateStr,String format){
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        Date date=null;
        try {
            date=sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    /**
     * 判断变量是否为空
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        if (null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s)) {
            return true;
        } else {
            return false;
        }
    }

    public static String connectCNCBLocalServer(String url,Map<String,String> params) throws Exception {
        HttpConnectionUtil http=new HttpConnectionUtil(url);
        http.init();
        byte[] bys = http.postParams(params, true);
        String result = new String(bys,"UTF-8");
        return result;
    }


}