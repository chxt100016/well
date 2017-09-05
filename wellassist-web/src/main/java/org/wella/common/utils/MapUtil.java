package org.wella.common.utils;

import java.util.Map;
/**
 * Created by Administrator on 2017/9/4.
 */
public class MapUtil {

    public static String getStringfromMap(Map map,String key){
        return null==map.get(key)?"":map.get(key).toString();
    }
}
