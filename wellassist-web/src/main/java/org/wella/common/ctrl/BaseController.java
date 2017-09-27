package org.wella.common.ctrl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.wella.common.mapper.CommonMapper;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.utils.ConstantUtil.Role;
import org.wella.common.vo.MyInfo;

public class BaseController {
    public static final Logger logger = LoggerFactory.getLogger(BaseController.class);
    String[] emos_arr = new String[]{"e001", "e002", "e003", "e004", "e005", "e00d", "e00e", "e00f", "e010", "e011", "e012", "e022", "e023", "e036", "e037", "e038", "e03b", "e03e", "e04d", "e04e", "e056", "e057", "e058", "e059", "e05a", "e105", "e106", "e107", "e108", "e10c", "e111", "e115", "e11a", "e11c", "e11d", "e122", "e13c", "e146", "e14c", "e14d", "e152", "e153", "e155", "e156", "e157", "e158", "e201", "e22e", "e22f", "e230", "e231", "e253", "e31d", "e31e", "e31f", "e326", "e327", "e328", "e329", "e32a", "e32b", "e32c", "e32d", "e32e", "e330", "e331", "e334", "e335", "e336", "e337", "e401", "e402", "e403", "e404", "e405", "e406", "e407", "e408", "e409", "e40a", "e40b", "e40c", "e40d", "e40e", "e40f", "e410", "e411", "e412", "e413", "e414", "e415", "e416", "e417", "e418", "e419", "e41a", "e41b", "e41c", "e41d", "e41e", "e41f", "e420", "e421", "e422", "e423", "e424", "e425", "e426", "e427", "e428", "e429", "e43d", "e449", "e44a", "e44b", "e501", "e504", "e505", "e506", "e508", "e509", "e50a", "e515", "e516", "e517", "e518", "e519", "e51a", "e51b", "e51c", "e51d", "e51e", "e51f", "e536"};
    @Autowired
    protected CommonMapper commonMapper;

    public BaseController() {
    }

    public JSONArray getData(ArrayList<Map<String, Object>> list) {
        JSONArray aryobj = new JSONArray();
        if(list != null && list.size() > 0) {
            Iterator var3 = list.iterator();

            while(var3.hasNext()) {
                Map map = (Map)var3.next();
                HashMap arymap = new HashMap();
                Set key = map.keySet();
                Iterator iterator = key.iterator();

                while(iterator.hasNext()) {
                    String keyName = (String)iterator.next();
                    Object valueName = map.get(keyName);
                    arymap.put(keyName, valueName.toString());
                }

                aryobj.add(arymap);
            }
        }

        return aryobj;
    }

    public void echoJSON(HttpServletResponse response, JSONObject obj) {
        try {
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(obj.toString());
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

    public void echo(HttpServletResponse response, JSONObject obj) {
        try {
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(obj.toString());
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

    public void echo(HttpServletResponse response, String obj) {
        try {
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(obj);
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

    public String getPath() throws UnsupportedEncodingException {
        String path = this.getClass().getClassLoader().getResource("").getPath();
        String fullPath = URLDecoder.decode(path, "UTF-8");
        String[] pathArr = fullPath.split("/WEB-INF/classes/");
        fullPath = pathArr[0];
        String reponsePath = "";
        reponsePath = (new File(fullPath)).getPath();
        return reponsePath;
    }

    public static String getExtention(String fileName) {
        int pos = fileName.lastIndexOf(".");
        return fileName.substring(pos + 1).toLowerCase();
    }

    public void setCookie(HttpServletResponse response, String key, String value, int time) {
        Cookie c = new Cookie(key, URLEncoder.encode(value));
        c.setMaxAge(time);
        c.setPath("/");
        response.addCookie(c);
    }

    public void delCookie(HttpServletResponse response, String key) {
        Cookie c = new Cookie(key, (String)null);
        c.setMaxAge(0);
        c.setPath("/");
        response.addCookie(c);
    }

    public String getCookie(HttpServletRequest request, String key) {
        String res = "";
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(int i = 0; i < cookies.length; ++i) {
                Cookie c = cookies[i];
                if(c.getName().equals(key)) {
                    res = URLDecoder.decode(c.getValue());
                    return res;
                }
            }
        }

        return res;
    }

    public Map<String, Object> getConditionParam(HttpServletRequest request) {
        HashMap param = new HashMap();
        Enumeration e = request.getParameterNames();

        while(e.hasMoreElements()) {
            String keyName = e.nextElement().toString();
            String keyValue = request.getParameter(keyName);
            if(keyValue != null && !keyValue.equals("") && !keyName.equals("file")) {
                param.put(keyName, keyValue);
            }
        }

        param.put("size", Integer.valueOf(ConstantUtil.PAGESIZE));
        if(param.get("page") != null && !param.get("page").equals("")) {
            param.put("start", Integer.valueOf((Integer.parseInt(param.get("page").toString()) - 1) * ConstantUtil.PAGESIZE));
        } else {
            param.put("start", "0");
            param.put("page", "1");
        }

        return param;
    }

    public Map<String, Object> getConditionParam(HttpServletRequest request,int size) {
        HashMap param = new HashMap();
        Enumeration e = request.getParameterNames();

        while(e.hasMoreElements()) {
            String keyName = e.nextElement().toString();
            String keyValue = request.getParameter(keyName);
            if(keyValue != null && !keyValue.equals("") && !keyName.equals("file")) {
                param.put(keyName, keyValue);
            }
        }

        param.put("size", size);
        if(param.get("page") != null && !param.get("page").equals("")) {
            param.put("start", Integer.valueOf((Integer.parseInt(param.get("page").toString()) - 1) * size));
        } else {
            param.put("start", "0");
            param.put("page", "1");
        }

        return param;
    }

    public Map<String, Object> getBoClass(HttpServletRequest request, String tableName) throws Exception {
        HashMap result = new HashMap();
        HashMap mapClass = new HashMap();
        HashMap param = new HashMap();
        param.put("tableName", tableName);
        ArrayList fieldList = this.commonMapper.getTableAllFieldNames(param);
        Iterator var7 = fieldList.iterator();

        while(var7.hasNext()) {
            Map ele = (Map)var7.next();
            String fieldName = ele.get("Field").toString();
            String className = ConvertUtil.convertKeyStringFromDBToJava(fieldName);
            if(request.getParameter(className) != null) {
                if(ele.get("Key").toString().equals("PRI")) {
                    result.put("keyName", fieldName);
                    result.put("keyValue", request.getParameter(className));
                } else {
                    mapClass.put(fieldName, request.getParameter(className));
                    if(ele.get("Type").toString().equals("date") && request.getParameter(className).equals("")) {
                        mapClass.put(fieldName, "0000-00-00");
                    }
                }
            }
        }

        result.put("tableName", tableName);
        result.put("mapClass", mapClass);
        return result;
    }

    /**
     * pagination.jsp 翻页插件装配参数
     * @param request request
     * @param totalCount 总记录数
     * @param page 当前页数
     */
    public void setPagenationInfo(HttpServletRequest request, int totalCount, int page) {
        request.setAttribute("gap", Integer.valueOf(ConstantUtil.GAP));
        request.setAttribute("pageCount", Integer.valueOf((totalCount + ConstantUtil.PAGESIZE - 1) / ConstantUtil.PAGESIZE));
        request.setAttribute("page", Integer.valueOf(page));
    }

    /**
     * pagination.jsp 翻页插件装配参数
     * @param request request
     * @param totalCount 总记录数
     * @param page 当前页数
     * @param gap 自定义每页记录数
     */
    public void setPagenationInfo(HttpServletRequest request, int totalCount, int page,int gap) {
        request.setAttribute("gap", gap);
        request.setAttribute("pageCount", Integer.valueOf((totalCount + gap - 1) / gap));
        request.setAttribute("page", Integer.valueOf(page));
    }

    public String getRegionDetailName(String regionId) throws Exception {
        if(regionId != null && !regionId.equals("")) {
            HashMap param = new HashMap();
            param.put("regionId", regionId);
            String ret = this.commonMapper.getRegionDetailName(param);
            return ret;
        } else {
            return "";
        }
    }

    public String getRegionIDs(HttpServletRequest request) {
        return "";
    }

    public boolean checkRole(HttpServletRequest request, HttpServletResponse response, Role roleType) {
        return true;
    }

    public Map<String, Object> getMyOneSingBO(String tableName, String keyName, Object keyValue) {
        new HashMap();
        HashMap param = new HashMap();
        param.put("tableName", tableName);
        param.put("keyName", keyName);
        param.put("keyValue", keyValue);
        Map tableObject = null;

        try {
            tableObject = this.commonMapper.selectOneSingleBO(param);
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        ConvertUtil.convertDataBaseMapToJavaMap(tableObject);
        return tableObject;
    }

    protected int getMenuRole(HttpServletRequest request, int type) {
        return 0;
    }

    protected void echoRoleError(HttpServletResponse response) {
        JSONObject obj = new JSONObject();
        obj.put("result", "-10");
        obj.put("msg", ConstantUtil.ERR_ROLE);
        this.echoJSON(response, obj);
    }

    protected String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader(" x-forwarded-for ");
        if(ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader(" Proxy-Client-IP ");
        }

        if(ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader(" WL-Proxy-Client-IP ");
        }

        if(ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    protected MyInfo getMyInfo(HttpServletRequest request) {
        MyInfo myInfo = null;
        HttpSession session = request.getSession();
        myInfo = (MyInfo)session.getAttribute("MY_INFO");
        return myInfo;
    }

    protected Map<String, Object> getUserInfo(String userId) {
        Map userInfo = null;
        HashMap param = new HashMap();
        param.put("userId", userId);
        userInfo = this.commonMapper.getUserInfo(param);
        ConvertUtil.convertDataBaseMapToJavaMap(userInfo);
        return userInfo;
    }

    protected void clearSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("MY_INFO");
    }

    /**
     * 通过父区域id查询子区域list
     * @param regionId 父区域id
     * @return 子区域list
     */
    public ArrayList<Map<String, Object>> getChildRegionList(int regionId) {
        ArrayList ret = null;
        HashMap param = new HashMap();
        param.put("regionId", Integer.valueOf(regionId));

        try {
            ret = this.commonMapper.getChildRegionList(param);
            ConvertUtil.convertDataBaseMapToJavaMap(ret);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return ret;
    }
}