package org.wella.common.utils;

import org.wella.utils.HttpConnectionUtil;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

public class CommonUtil {
    public CommonUtil() {
    }

    public static String DispPagination(int totalPage, int pageNum, int gap, String funcName, String prevStr, String nextStr) {
        String retStr = "";
        if(totalPage <= 0) {
            return retStr;
        } else {
            retStr = "<div class=\'pagination page\'>";
            if(pageNum == 0) {
                retStr = retStr + "<span class=\'previous_page disabled\' style=\'border-left-width: 1px;\'>" + prevStr + "</span>";
            } else {
                retStr = retStr + "<a class=\'previous_page\' style=\'border-left-width: 1px;\' onclick=\'" + funcName + "(" + (pageNum - 1) + ")\'>" + prevStr + "</a>";
            }

            boolean initGap = true;

            for(int i = 0; i <= totalPage; ++i) {
                if(pageNum == i) {
                    retStr = retStr + "<span class=\'active\'>" + (i + 1) + "</span>";
                    initGap = true;
                } else if(i < gap || i + gap > totalPage || i >= pageNum - gap && i <= pageNum + gap) {
                    retStr = retStr + "<a onclick=\'" + funcName + "(" + i + ");\'>" + (i + 1) + "</a> ";
                    initGap = true;
                } else if(initGap) {
                    retStr = retStr + "<span class=\'gap\'>...</span> ";
                    initGap = false;
                }
            }

            if(pageNum == totalPage) {
                retStr = retStr + "<span class=\'next_page disabled\'>" + nextStr + "</span>";
            } else {
                retStr = retStr + "<a class=\'next_page\'  style=\'background-position:65px 50%;\' onclick=\'" + funcName + "(" + (pageNum + 1) + ");\'>" + nextStr + "</a>";
            }

            retStr = retStr + "</div>";
            return retStr;
        }
    }

    public static int getIntFromString(String str) {
        int ret = 0;
        if(str != null) {
            try {
                ret = Integer.parseInt(str);
            } catch (NumberFormatException var3) {
                ret = 0;
            }
        }
        return ret;
    }

    public static double getDoubleFromString(String str) {
        double ret = 0.0D;
        if(str != null) {
            try {
                ret = Double.parseDouble(str);
            } catch (NumberFormatException var4) {
                ret = 0.0D;
            }
        }

        return ret;
    }

    /**
     * 得到请求参数
     * @param request request
     * @param name 请求参数key
     * @param def 默认值
     * @return 请求参数value
     */
    public static String GetRequestParam(HttpServletRequest request, String name, String def) {
        if(request == null) {
            return def;
        } else {
            String value = request.getParameter(name);
            return value != null && !value.equals("")?value:def;
        }
    }

    public static String getExtention(String fileName) {
        int pos = fileName.lastIndexOf(".");
        return fileName.substring(pos + 1).toLowerCase();
    }

    public static String MD5(String plainText) {
        try {
            MessageDigest e = MessageDigest.getInstance("MD5");
            e.update(plainText.getBytes());
            byte[] b = e.digest();
            StringBuffer buf = new StringBuffer("");

            for(int offset = 0; offset < b.length; ++offset) {
                int i = b[offset];
                if(i < 0) {
                    i += 256;
                }

                if(i < 16) {
                    buf.append("0");
                }

                buf.append(Integer.toHexString(i));
            }

            return buf.toString();
        } catch (NoSuchAlgorithmException var6) {
            var6.printStackTrace();
            return "";
        }
    }

    public static String getRootHttpPath(HttpServletRequest request) {
        String path = request.getContextPath();
        String getProtocol = request.getScheme();
        String getDomain = request.getServerName();
        String getPort = String.format("%d", new Object[]{Integer.valueOf(request.getServerPort())});
        path = getProtocol + "://" + getDomain + ":" + getPort + path + "/";
        return path;
    }

    public static String convertISOtoUtf8String(String str) {
        String ret = "";

        try {
            if(str != null) {
                ret = new String(str.getBytes("iso8859-1"), "utf-8");
            }
        } catch (UnsupportedEncodingException var3) {
            ;
        }

        return ret;
    }

    public static String randomString(int length) {
        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567980".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for(int i = 0; i < length; ++i) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }

        return sb.toString();
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

    public static String genKey(int size) {
        String genKey = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
        genKey = sdf.format(new Date()) + randomString(size);
        return genKey;
    }

    public static boolean sql_inj(String str) {
        str = str.toLowerCase();
        String inj_str = "\'| and | exec| insert| select| delete| update| count|select*|select *| chr| mid|master|truncate|char|declare|;| or |+|>|<|& ";
        String[] inj_stra = inj_str.split("\\|");

        for(int daum_arr = 0; daum_arr < inj_stra.length; ++daum_arr) {
            if(str.indexOf(inj_stra[daum_arr]) >= 0) {
                return true;
            }
        }

        String[] var8 = str.split("\"");
        String str_temp = "";

        for(int i = 1; i < var8.length; ++i) {
            try {
                str_temp = new String(var8[i].getBytes("ISO-8859-1"), "UTF-8");
                if(str_temp.equals(var8[i]) && var8[i].indexOf("=") >= 0) {
                    return true;
                }
            } catch (UnsupportedEncodingException var7) {
                return true;
            }
        }

        return false;
    }

    public static boolean check_inj_request_param(Map<String, Object> param) {
        boolean flag = false;
        Set key = param.keySet();
        Iterator iterator = key.iterator();

        while(iterator.hasNext()) {
            String keyName = (String)iterator.next();
            Object valueName = param.get(keyName);
            if(valueName != null && !sql_inj(valueName.toString())) {
                ;
            }
        }

        return flag;
    }

    public static String connectCNCBLocalServer(String url,Map<String,String> params) throws Exception {
        HttpConnectionUtil http=new HttpConnectionUtil(url);
        http.init();
        byte[] bys = http.postParams(params, true);
        String result = new String(bys,"UTF-8");
        return result;
    }

    public static String connectCNCBLocalServer(String url,String params,String contentType) throws Exception {
        HttpConnectionUtil http=new HttpConnectionUtil(url);
        http.init();
        byte[] bys = http.postParams(params, true,contentType);
        String result = new String(bys,"UTF-8");
        return result;
    }
}