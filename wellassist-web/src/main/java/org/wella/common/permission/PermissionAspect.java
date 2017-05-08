package org.wella.common.permission;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.wella.common.vo.MyInfo;
import org.wella.common.vo.MyRole;

@Aspect
public class PermissionAspect {
//    static String name = "";
//    static String type = "";
//
//    public PermissionAspect() {
//    }
//
//    @Around("execution(* org.wella.houtai..*Ctrl.*(..))")
//    public Object checkPermission(ProceedingJoinPoint joinPoint) throws Throwable {
//        HttpServletRequest request = null;
//        HttpServletResponse response = null;
//        type = joinPoint.getSignature().getDeclaringTypeName();
//        name = joinPoint.getSignature().getName();
//        if((type.indexOf("LoginCtrl") <= -1 || name.equals("main")) && type.indexOf("ChattingCtrl") <= -1 && type.indexOf("FwzhRegCtrl") <= -1 && type.indexOf("GylRegCtrl") <= -1 && type.indexOf("WbsRegCtrl") <= -1) {
//            Object[] ctrlName = joinPoint.getArgs();
//            int callMethod = ctrlName.length;
//
//            for(int e = 0; e < callMethod; ++e) {
//                Object myInfo = ctrlName[e];
//                if(myInfo instanceof HttpServletRequest) {
//                    request = (HttpServletRequest)myInfo;
//                }
//
//                if(myInfo instanceof HttpServletResponse) {
//                    response = (HttpServletResponse)myInfo;
//                }
//            }
//
//            String var17 = this.getControllerName(request);
//            String var18 = request.getMethod();
//
//            try {
//                HttpSession var19 = request.getSession(true);
//                MyInfo var20 = (MyInfo)var19.getAttribute("MY_INFO");
//                if(var20 == null) {
//                    return "houtai/logout";
//                }
//
//                if(!var20.getUserType().equals("4")) {
//                    return "houtai/logout";
//                }
//
//                if(!"admin".equals(var20.getUserName()) && (var20.getMenuList().get(var17) == null || Integer.parseInt(var20.getMenuList().get(var17).toString()) <= 0)) {
//                    return "houtai/logout";
//                }
//
//                if("admin".equals(var20.getUserName())) {
//                    return joinPoint.proceed();
//                }
//
//                String roles = var20.getRoles();
//                new HashMap();
//                String[] txsc = roles.split(";");
//                var20.getMenuList().get(var17).toString();
//                MyRole myRole = new MyRole();
//                myRole.setAdd(1);
//                myRole.setEdit(1);
//                myRole.setDel(1);
//                myRole.setView(1);
//
//                for(int i = 0; i < txsc.length; ++i) {
//                    String[] temp = new String[]{null, null};
//                    temp = txsc[i].split(":");
//                    if(temp[0].equals(var20.getMenuList().get(var17).toString()) && temp.length > 1) {
//                        String[] tmp_role = new String[4];
//                        tmp_role = temp[1].split(",");
//                        myRole.setAdd(Integer.parseInt(tmp_role[0]));
//                        myRole.setEdit(Integer.parseInt(tmp_role[1]));
//                        myRole.setDel(Integer.parseInt(tmp_role[2]));
//                        myRole.setView(Integer.parseInt(tmp_role[3]));
//                        break;
//                    }
//                }
//
//                request.setAttribute("myMenuRole", myRole);
//            } catch (Exception var16) {
//                var16.printStackTrace();
//            }
//
//            return joinPoint.proceed();
//        } else {
//            return joinPoint.proceed();
//        }
//    }
//
//    @Around("execution(* org.wella.front.seller..*Controller.*(..))")
//    public Object checkGysPermission(ProceedingJoinPoint joinPoint) throws Throwable {
//        HttpServletRequest request = null;
//        type = joinPoint.getSignature().getDeclaringTypeName();
//        name = joinPoint.getSignature().getName();
//        Object[] e = joinPoint.getArgs();
//        int myInfo = e.length;
//
//        for(int var5 = 0; var5 < myInfo; ++var5) {
//            Object o = e[var5];
//            if(o instanceof HttpServletRequest) {
//                request = (HttpServletRequest)o;
//            }
//        }
//
//        if(type.indexOf("LoginController") <= -1 && !name.equals("checkWorkPass") && !name.equals("getRegionList") && type.indexOf("ChattingCtrl") <= -1 && type.indexOf("FwzhRegCtrl") <= -1 && type.indexOf("GylRegCtrl") <= -1 && type.indexOf("WbsRegCtrl") <= -1) {
//            try {
//                HttpSession var8 = request.getSession(true);
//                MyInfo var9 = (MyInfo)var8.getAttribute("MY_INFO");
//                if(var9 == null) {
//                    return "front/seller/login/login";
//                }
//
//                if(!var9.getUserType().equals("0")) {
//                    return "front/seller/login/login";
//                }
//            } catch (Exception var7) {
//                var7.printStackTrace();
//            }
//
//            return joinPoint.proceed();
//        } else {
//            return joinPoint.proceed();
//        }
//    }
//
//    @Around("execution(* org.wella.front.customer..*Controller.*(..))")
//    public Object checkKhPermission(ProceedingJoinPoint joinPoint) throws Throwable {
//        HttpServletRequest request = null;
//        type = joinPoint.getSignature().getDeclaringTypeName();
//        name = joinPoint.getSignature().getName();
//        Object[] e = joinPoint.getArgs();
//        int myInfo = e.length;
//
//        for(int var5 = 0; var5 < myInfo; ++var5) {
//            Object o = e[var5];
//            if(o instanceof HttpServletRequest) {
//                request = (HttpServletRequest)o;
//            }
//        }
//
//        if(type.indexOf("CustomerLoginCtrl") <= -1 && !name.equals("getRegionList") && type.indexOf("ChattingCtrl") <= -1 && type.indexOf("FwzhRegCtrl") <= -1 && type.indexOf("GylRegCtrl") <= -1 && type.indexOf("WbsRegCtrl") <= -1) {
//            try {
//                HttpSession var8 = request.getSession(true);
//                MyInfo var9 = (MyInfo)var8.getAttribute("MY_INFO");
//                if(var9 == null) {
//                    return "front/customer/login/login";
//                }
//
//                if(!var9.getUserType().equals("1")) {
//                    return "front/customer/login/login";
//                }
//            } catch (Exception var7) {
//                var7.printStackTrace();
//            }
//
//            return joinPoint.proceed();
//        } else {
//            return joinPoint.proceed();
//        }
//    }
//
//    @Around("execution(* org.wella.front.customer..*Ctrl.*(..))")
//    public Object checkKhPermission1(ProceedingJoinPoint joinPoint) throws Throwable {
//        return this.checkKhPermission(joinPoint);
//    }
//
//    @Around("execution(* org.wella.front.sender..*Controller.*(..))")
//    public Object checkWlPermission(ProceedingJoinPoint joinPoint) throws Throwable {
//        HttpServletRequest request = null;
//        type = joinPoint.getSignature().getDeclaringTypeName();
//        name = joinPoint.getSignature().getName();
//        Object[] e = joinPoint.getArgs();
//        int myInfo = e.length;
//
//        for(int var5 = 0; var5 < myInfo; ++var5) {
//            Object o = e[var5];
//            if(o instanceof HttpServletRequest) {
//                request = (HttpServletRequest)o;
//            }
//        }
//
//        if(type.indexOf("LoginController") <= -1 && !name.equals("getRegionList") && type.indexOf("ChattingCtrl") <= -1 && type.indexOf("FwzhRegCtrl") <= -1 && type.indexOf("GylRegCtrl") <= -1 && type.indexOf("WbsRegCtrl") <= -1) {
//            try {
//                HttpSession var8 = request.getSession(true);
//                MyInfo var9 = (MyInfo)var8.getAttribute("MY_INFO");
//                if(var9 == null) {
//                    return "front/sender/login/login";
//                }
//
//                if(!var9.getUserType().equals("3")) {
//                    return "front/sender/login/login";
//                }
//            } catch (Exception var7) {
//                var7.printStackTrace();
//            }
//
//            return joinPoint.proceed();
//        } else {
//            return joinPoint.proceed();
//        }
//    }
//
//    @Around("execution(* org.wella.front.sender..*Ctrl.*(..))")
//    public Object checkWlPermission1(ProceedingJoinPoint joinPoint) throws Throwable {
//        return this.checkWlPermission(joinPoint);
//    }
//
//    @Around("execution(* org.wella.front.fkf..*Controller.*(..))")
//    public Object checkFkfPermission(ProceedingJoinPoint joinPoint) throws Throwable {
//        HttpServletRequest request = null;
//        type = joinPoint.getSignature().getDeclaringTypeName();
//        name = joinPoint.getSignature().getName();
//        Object[] e = joinPoint.getArgs();
//        int myInfo = e.length;
//
//        for(int var5 = 0; var5 < myInfo; ++var5) {
//            Object o = e[var5];
//            if(o instanceof HttpServletRequest) {
//                request = (HttpServletRequest)o;
//            }
//        }
//
//        if(type.indexOf("FkfLoginCtrl") <= -1 && !name.equals("getRegionList") && type.indexOf("ChattingCtrl") <= -1 && type.indexOf("FwzhRegCtrl") <= -1 && type.indexOf("GylRegCtrl") <= -1 && type.indexOf("WbsRegCtrl") <= -1) {
//            try {
//                HttpSession var8 = request.getSession(true);
//                MyInfo var9 = (MyInfo)var8.getAttribute("MY_INFO");
//                if(var9 == null) {
//                    return "front/fkf/login/login";
//                }
//
//                if(!var9.getUserType().equals("2")) {
//                    return "front/fkf/login/login";
//                }
//            } catch (Exception var7) {
//                var7.printStackTrace();
//            }
//
//            return joinPoint.proceed();
//        } else {
//            return joinPoint.proceed();
//        }
//    }
//
//    @Around("execution(* org.wella.front.fkf..*Ctrl.*(..))")
//    public Object checkFkfPermission1(ProceedingJoinPoint joinPoint) throws Throwable {
//        return this.checkFkfPermission(joinPoint);
//    }
//
//    public String getControllerName(HttpServletRequest request) {
//        String ret = "";
//        String pathInfo = request.getRequestURI();
//        ret = pathInfo.split("-")[0];
//        String[] strArr = ret.split("/");
//        ret = strArr[strArr.length - 1];
//        return ret;
//    }
}