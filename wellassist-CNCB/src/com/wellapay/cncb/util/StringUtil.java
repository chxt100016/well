package com.wellapay.cncb.util;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/8/31.
 */
public class StringUtil {

    public static String setCharsetGBK(String origin) throws UnsupportedEncodingException {
        return new String(origin.getBytes(),"GBK");
    }

}
