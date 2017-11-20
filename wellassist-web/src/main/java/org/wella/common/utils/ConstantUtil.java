package org.wella.common.utils;

import java.io.File;

public class ConstantUtil {
    public static int PAGESIZE = 5;
    public static int GAP = 5;
    public static String SERVER_HOST="http://192.168.0.171:8080/wellassist/";
    public static final String CNCB_SERVER_BASEURL="http://192.168.0.171:8079/";
    public static String ERR_ROLE = "很抱歉！您没有操作权限！";
    public static String MSG_SUCCESS = "操作成功！";
    public static String MSG_FAILS = "操作失败！";
    public static String MSG_PARAM_ERR = "参数错误！";
    public static String MSG_SESSION_ERR = "再登录！";
    public static String MSG_MONEY_ERR = "金额不够！";
    public static String TITLE_ = "wellassist供应连系统";
    public static String TITLE_ADMIN = "-管理";
    public static String TITLE_SHENHUI = "-审议";
    public static String PREFIX_FACTORY = "F";
    public static String PREFIX_LICENCE = "L";
    public static String PREFIX_MAINTENANCE = "M";
    public static String PREFIX_SERVICE = "S";
    public static String PREFIX_ERP_JZ = "J";
    public static String PREFIX_ERP_KH = "K";
    public static String PREFIX_ERP_ZY = "P";
    public static String ADMIN_NAME = "admin";
    public static String htTempletDir;
    public static String htTempletFile;
    public static String defaultUserImg;

    public ConstantUtil() {
    }

    static {
        htTempletDir = File.separatorChar + "resources" + File.separatorChar + "ht" + File.separatorChar;
        htTempletFile = File.separatorChar + "resources" + File.separatorChar + "ht" + File.separatorChar + "template.docx";
        defaultUserImg = "resources/common/images/icon_user_def.jpg";
    }

    public static enum Role {
        ADD,
        EDIT,
        DEL,
        VIEW;

        private Role() {
        }
    }
}