package org.wella.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

public class ConvertUtil {
    public ConvertUtil() {
    }

    public static void convertDataBaseMapToJavaMap(Object obj) {
        Iterator var3;
        if(obj instanceof Map) {
            Map array = (Map)obj;
            HashMap array1 = new HashMap();
            var3 = array.keySet().iterator();

            while(var3.hasNext()) {
                String ele = (String)var3.next();
                String newEle = convertKeyStringFromDBToJava(ele);
                array1.put(newEle, array.get(ele));
            }

            ((Map)obj).clear();
            ((Map)obj).putAll(array1);
        } else if(obj instanceof ArrayList) {
            ArrayList array1 = (ArrayList)obj;
            ArrayList array11 = new ArrayList();
            var3 = array1.iterator();

            while(var3.hasNext()) {
                Object ele1 = var3.next();
                HashMap newEle1 = new HashMap();
                Iterator var6 = ((Map)ele1).keySet().iterator();

                while(var6.hasNext()) {
                    String key = (String)var6.next();
                    String newKey = convertKeyStringFromDBToJava(key);
                    newEle1.put(newKey, ((Map)ele1).get(key));
                }

                array11.add(newEle1);
            }

            array1.removeAll(array1);
            array1.addAll(array11);
        }

    }

    public static String convertKeyStringFromDBToJava(String keyName) {
        String[] array = keyName.split("_");
        keyName = array[0];

        for(int i = 1; i < array.length; ++i) {
            String ele = array[i];
            ele = ele.substring(0, 1).toUpperCase() + ele.substring(1);
            keyName = keyName + ele;
        }

        return keyName;
    }

    public static Map<String, Object> convertConditionClass(Map<String, Object> boClass) {
        HashMap ret = new HashMap();
        HashMap condition = new HashMap();
        if(boClass == null) {
            return ret;
        } else {
            ret.put("tableName", boClass.get("tableName") == null?"":boClass.get("tableName").toString());
            Map mapClass = (Map)boClass.get("mapClass");
            Iterator var4 = mapClass.keySet().iterator();

            while(var4.hasNext()) {
                String key = (String)var4.next();
                condition.put(key, mapClass.get(key));
            }

            condition.put(boClass.get("keyName").toString(), boClass.get("keyValue"));
            ret.put("condition", condition);
            return ret;
        }
    }

    public static void convertJavaMapToDataBaseMap(Object obj) {
        Iterator var3;
        if(obj instanceof Map) {
            Map array = (Map)obj;
            HashMap array1 = new HashMap();
            var3 = array.keySet().iterator();

            while(var3.hasNext()) {
                String ele = (String)var3.next();
                String newEle = convertDBToJavaFromKeyString(ele);
                array1.put(newEle, array.get(ele));
            }

            ((Map)obj).clear();
            ((Map)obj).putAll(array1);
        } else if(obj instanceof ArrayList) {
            ArrayList array1 = (ArrayList)obj;
            ArrayList array11 = new ArrayList();
            var3 = array1.iterator();

            while(var3.hasNext()) {
                Object ele1 = var3.next();
                HashMap newEle1 = new HashMap();
                Iterator var6 = ((Map)ele1).keySet().iterator();

                while(var6.hasNext()) {
                    String key = (String)var6.next();
                    String newKey = convertDBToJavaFromKeyString(key);
                    newEle1.put(newKey, ((Map)ele1).get(key));
                }

                array11.add(newEle1);
            }

            array1.removeAll(array1);
            array1.addAll(array11);
        }

    }

    public static String convertDBToJavaFromKeyString(String keyName) {
        String ret = keyName.replaceAll("([A-Z])", "_$1");
        return ret.toLowerCase();
    }

    public static String convertStringArrayToInArray(String conArr) {
        String ret = "";
        ret = conArr.replace(",", "\',\'");
        ret = "\'" + ret + "\'";
        return ret;
    }

    public static ArrayList<Map<String, Object>> converJSONtoArrayListMap(String json) {
        if(json.contains("&quot;")){
            json = json.replaceAll("&quot;","\'");
        }
        JSONArray array = JSONArray.parseArray(json);
        ArrayList ret = new ArrayList();

        for(int i = 0; i < array.size(); ++i) {
            JSONObject jobj = (JSONObject)array.get(i);
            Map ele = (Map)JSON.toJavaObject(jobj, Map.class);
            ret.add(ele);
        }

        return ret;
    }

    public static Map<String, Object> converJSONtoMap(String json) {
        Map ret = (Map)JSON.toJavaObject(JSON.parseObject(json), Map.class);
        return ret;
    }

    public static ArrayList<ArrayList<Map<String, Object>>> groupList(List<Map<String, Object>> list, String groupKey) {
        ArrayList retList = new ArrayList();
        if(list.size() == 0) {
            return retList;
        } else {
            String keyValue = "";
            ArrayList eleArr = new ArrayList();
            Iterator var5 = list.iterator();

            while(var5.hasNext()) {
                Map ele1 = (Map)var5.next();
                if(keyValue.equals("")) {
                    keyValue = ele1.get(groupKey).toString();
                    eleArr = new ArrayList();
                }

                if(keyValue.equals(ele1.get(groupKey).toString())) {
                    eleArr.add(ele1);
                } else {
                    retList.add(eleArr);
                    keyValue = ele1.get(groupKey).toString();
                    eleArr = new ArrayList();
                    eleArr.add(ele1);
                }
            }

            retList.add(eleArr);
            return retList;
        }
    }

    public static Map<String, Object> groupListInMap(ArrayList<Map<String, Object>> list, String groupKey) {
        HashMap retMap = new HashMap();
        if(list.size() == 0) {
            return retMap;
        } else {
            String keyValue = "";
            ArrayList eleArr = new ArrayList();
            Iterator var5 = list.iterator();

            while(var5.hasNext()) {
                Map ele1 = (Map)var5.next();
                if(keyValue.equals("")) {
                    keyValue = ele1.get(groupKey).toString();
                    eleArr = new ArrayList();
                }

                if(keyValue.equals(ele1.get(groupKey).toString())) {
                    eleArr.add(ele1);
                } else {
                    retMap.put(keyValue, eleArr);
                    keyValue = ele1.get(groupKey).toString();
                    eleArr = new ArrayList();
                    eleArr.add(ele1);
                }
            }

            retMap.put(keyValue, eleArr);
            return retMap;
        }
    }

    public static Map<String, Object> convertArrayToMap(ArrayList<Map<String, Object>> list, String keyName) {
        HashMap result = new HashMap();
        Iterator var3 = list.iterator();

        while(var3.hasNext()) {
            Map ele = (Map)var3.next();
            result.put(ele.get(keyName).toString(), ele);
        }

        return result;
    }
}