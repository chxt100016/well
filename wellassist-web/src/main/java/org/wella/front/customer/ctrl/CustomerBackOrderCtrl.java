//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.wella.front.customer.ctrl;

import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wella.common.ctrl.BaseController;
import org.wella.common.ctrl.UploadController;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.vo.MyInfo;
import org.wella.front.customer.mapper.CustomerBackOrderMapper;

@Controller
public class CustomerBackOrderCtrl extends BaseController {
    @Autowired
    private UploadController uploadController;
    @Autowired
    private CustomerBackOrderMapper customerBackOrderMapper;

    public CustomerBackOrderCtrl() {
    }

    @RequestMapping({"front/customer/CustomerBackOrderCtrl-prodOrderList"})
    public String prodOrderList(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        Map param = this.getConditionParam(request);
        param.put("userId", myInfo.getUserId());
        ArrayList waOrderList = this.customerBackOrderMapper.getWaOrderList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(waOrderList);
        model.addAttribute("waOrderList", waOrderList);
        int totalCount = this.customerBackOrderMapper.getWaOrderListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "1");
        return "front/customer/order/prodOrderList";
    }

    @RequestMapping({"front/customer/CustomerBackOrderCtrl-detailOrder"})
    public String detailOrder(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        String orderType = CommonUtil.GetRequestParam(request, "orderType", "0");
        String orderId = CommonUtil.GetRequestParam(request, "orderId", "0");
        if(!orderType.equals("1")) {
            orderType = "0";
        }

        if(CommonUtil.getIntFromString(orderId) > 0) {
            Map orderInfo = this.getMyOneSingBO("wa_order", "order_id", orderId);
            if(orderInfo != null && orderInfo.get("userId") != null && orderInfo.get("userId").toString().equals(myInfo.getUserId())) {
                orderInfo.put("companyName", "");
                if(orderInfo.get("userId") != null) {
                    Map vehicleList = this.getMyOneSingBO("wa_userinfo", "user_id", orderInfo.get("supplierId") != null?orderInfo.get("supplierId").toString():"0");
                    if(vehicleList != null && vehicleList.get("companyName") != null) {
                        orderInfo.put("companyName", vehicleList.get("companyName").toString());
                    }
                }

                model.addAttribute("orderInfo", orderInfo);
                if(orderInfo.get("orderState") != null) {
                    ArrayList vehicleList1 = null;
                    HashMap pm = new HashMap();
                    pm.put("tableName", "wa_order_vehicle");
                    HashMap conditionMap = new HashMap();
                    conditionMap.put("order_id", orderId);
                    pm.put("condition", conditionMap);

                    try {
                        vehicleList1 = this.commonMapper.selectSingleBO(pm);
                        ConvertUtil.convertDataBaseMapToJavaMap(vehicleList1);
                    } catch (Exception var12) {
                        var12.printStackTrace();
                    }

                    model.addAttribute("vehicleList", vehicleList1);
                }
            }
        }

        model.addAttribute("orderType", orderType);
        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "1");
        return "views/front/customer/order/orderDetail.jsp";
    }

    @RequestMapping({"front/customer/CustomerBackOrderCtrl-querenShouhuo"})
    public String querenShouhuo(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        String orderId = CommonUtil.GetRequestParam(request, "orderId", "0");
        if(CommonUtil.getIntFromString(orderId) > 0) {
            Map orderInfo = this.getMyOneSingBO("wa_order", "order_id", orderId);
            if(orderInfo != null && orderInfo.get("userId") != null && orderInfo.get("userId").toString().equals(myInfo.getUserId()) && orderInfo.get("orderState") != null && orderInfo.get("orderState").toString().equals("1")) {
                orderInfo.put("companyName", "");
                Map userInfo = this.getMyOneSingBO("wa_userinfo", "user_id", orderInfo.get("supplierId") != null?orderInfo.get("supplierId").toString():"0");
                if(userInfo != null && userInfo.get("companyName") != null) {
                    orderInfo.put("companyName", userInfo.get("companyName").toString());
                }

                model.addAttribute("orderInfo", orderInfo);
            }
        }

        model.addAttribute("parentMenuNo", Integer.valueOf(1));
        model.addAttribute("userName", myInfo.getUserName());
        return "views/front/customer/order/editFukuan.jsp";
    }

    @RequestMapping({"front/customer/CustomerBackOrderCtrl-editFukuan"})
    public String editFukuan(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        Map userInfos = this.getUserInfo(userId);
        String orderId = CommonUtil.GetRequestParam(request, "orderId", "0");
        if(CommonUtil.getIntFromString(orderId) > 0) {
            Map orderInfo = this.getMyOneSingBO("wa_order", "order_id", orderId);
            if(orderInfo != null && orderInfo.get("userId") != null && orderInfo.get("userId").toString().equals(myInfo.getUserId()) && orderInfo.get("orderState") != null && orderInfo.get("orderState").toString().equals("1")) {
                orderInfo.put("companyName", "");
                Map userInfo = this.getMyOneSingBO("wa_userinfo", "user_id", orderInfo.get("supplierId") != null?orderInfo.get("supplierId").toString():"0");
                if(userInfo != null && userInfo.get("companyName") != null) {
                    orderInfo.put("companyName", userInfo.get("companyName").toString());
                }

                model.addAttribute("orderInfo", orderInfo);
                model.addAttribute("userInfo", userInfos);
                model.addAttribute("userId", userId);
            }
        }

        model.addAttribute("parentMenuNo", Integer.valueOf(1));
        model.addAttribute("userName", myInfo.getUserName());
        return "views/front/customer/order/editFukuan.jsp";
    }

    @RequestMapping(
            value = {"front/customer/CustomerBackOrderCtrl-setFukuan"},
            method = {RequestMethod.POST}
    )
    public void setFukuan(HttpServletRequest request, HttpServletResponse response, Model model) {
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        String orderId = CommonUtil.GetRequestParam(request, "orderId", "0");
        String saleMoney = CommonUtil.GetRequestParam(request, "saleMoney", "0.00");
        String rate = CommonUtil.GetRequestParam(request, "rate", "0");
        String zfMethod = CommonUtil.GetRequestParam(request, "zfMethod", "2");

        try {
            MyInfo e = this.getMyInfo(request);
            String userId = e.getUserId();
            Map userInfo = this.getUserInfo(userId);
            String userMoney = userInfo.get("userMoney").toString();
            String userCreditMoney = userInfo.get("userCreditMoney").toString();
            if(zfMethod.equals("2")) {
                if(Double.parseDouble(userMoney) < Double.parseDouble(saleMoney)) {
                    obj.put("content", ConstantUtil.MSG_MONEY_ERR);
                    obj.put("status", "-1");
                    this.echo(response, obj);
                }
            } else if(zfMethod.equals("3")) {
                if(Double.parseDouble(userMoney) * (double)(100 - Integer.parseInt(rate)) / 100.0D + Double.parseDouble(userCreditMoney) * (double)Integer.parseInt(rate) / 100.0D < Double.parseDouble(saleMoney)) {
                    obj.put("content", ConstantUtil.MSG_MONEY_ERR);
                    obj.put("status", "-1");
                    this.echo(response, obj);
                }
            } else if(zfMethod.equals("4")) {
                if(Double.parseDouble(userCreditMoney) < Double.parseDouble(saleMoney)) {
                    obj.put("content", ConstantUtil.MSG_MONEY_ERR);
                    obj.put("status", "-1");
                    this.echo(response, obj);
                }
            } else if(zfMethod.equals("5")) {
                String orderObj1 = CommonUtil.GetRequestParam(request, "certificateImg", "");
                if(!orderObj1.equals("") && orderObj1.indexOf("temp") > 0) {
                    orderObj1 = this.uploadController.moveFile(request, orderObj1, "order/certificate");
                }

                HashMap sql1 = new HashMap();
                sql1.put("tableName", "wa_user_money");
                sql1.put("keyName", "order_id");
                sql1.put("keyValue", orderId);
                Map queryParam2 = this.commonMapper.selectOneSingleBO(sql1);
                ConvertUtil.convertDataBaseMapToJavaMap(queryParam2);
                if(queryParam2 == null) {
                    obj.put("content", ConstantUtil.MSG_PARAM_ERR);
                    obj.put("status", "-1");
                    this.echo(response, obj);
                    return;
                }

                HashMap mapClass = new HashMap();
                mapClass.put("order_id", orderId);
                mapClass.put("money_id", queryParam2.get("moneyId"));
                mapClass.put("zf_money", saleMoney);
                mapClass.put("zf_method", "5");
                mapClass.put("tj_date", new Date());
                mapClass.put("trans_state", "-1");
                sql1.put("mapClass", mapClass);
                sql1.put("tableName", "wa_order_trans");

                try {
                    this.commonMapper.insertSingleBO(sql1);
                    String e1 = "UPDATE wa_order SET order_state = \'22\', certificate_img = \'" + orderObj1 + "\' WHERE order_id = \'" + orderId + "\'";
                    HashMap queryParam1 = new HashMap();
                    queryParam1.put("strsql", e1);
                    this.commonMapper.simpleUpdate(queryParam1);
                    obj.put("content", ConstantUtil.MSG_SUCCESS);
                    obj.put("status", "1");
                } catch (Exception var21) {
                    obj.put("content", ConstantUtil.MSG_FAILS);
                    obj.put("status", "-1");
                }

                this.echo(response, obj);
                return;
            }

            if(e != null && CommonUtil.getIntFromString(orderId) > 0) {
                Map orderObj = this.getMyOneSingBO("wa_order", "order_id", orderId);
                if(orderObj != null && orderObj.get("userId") != null && orderObj.get("userId").toString().equals(e.getUserId()) && orderObj.get("orderState") != null && orderObj.get("orderState").toString().equals("1")) {
                    String sql = "";
                    sql = " CALL khFukuanProcess(\'" + userId + "\',\'" + orderId + "\',\'" + saleMoney + "\',\'" + zfMethod + "\',\'" + rate + "\')";
                    HashMap queryParam = new HashMap();
                    queryParam.put("strsql", sql);
                    this.commonMapper.simpleSelectReturnList(queryParam);
                    ret = "1";
                    obj.put("content", ConstantUtil.MSG_SUCCESS);
                }
            }
        } catch (Exception var22) {
            ret = "-2";
            obj.put("content", ConstantUtil.MSG_FAILS);
        }

        obj.put("status", ret);
        this.echoJSON(response, obj);
    }

    @RequestMapping({"front/customer/CustomerBackOrderCtrl-editPingjia"})
    public String editPingjia(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        String orderId = CommonUtil.GetRequestParam(request, "orderId", "0");
        if(CommonUtil.getIntFromString(orderId) > 0) {
            Map orderInfo = this.getMyOneSingBO("wa_order", "order_id", orderId);
            if(orderInfo != null && orderInfo.get("userId") != null && orderInfo.get("userId").toString().equals(myInfo.getUserId()) && orderInfo.get("orderState") != null && orderInfo.get("orderState").toString().equals("6")) {
                model.addAttribute("orderInfo", orderInfo);
            }
        }

        model.addAttribute("parentMenuNo", Integer.valueOf(1));
        model.addAttribute("userName", myInfo.getUserName());
        return "views/front/customer/order/editPingjia.jsp";
    }

    @RequestMapping(
            value = {"front/customer/CustomerBackOrderCtrl-setPingjia"},
            method = {RequestMethod.POST}
    )
    public void setPingjia(HttpServletRequest request, HttpServletResponse response, Model model) {
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        String orderId = CommonUtil.GetRequestParam(request, "orderId", "0");
        String pjState = CommonUtil.GetRequestParam(request, "pjState", "0");
        String pjContent = CommonUtil.GetRequestParam(request, "pjContent", "");
        if(!pjState.equals("1") && !pjState.equals("2")) {
            pjState = "0";
        }

        try {
            MyInfo e = this.getMyInfo(request);
            if(e != null && CommonUtil.getIntFromString(orderId) > 0 && !pjContent.equals("")) {
                Map orderObj = this.getMyOneSingBO("wa_order", "order_id", orderId);
                if(orderObj != null && orderObj.get("userId") != null && orderObj.get("userId").toString().equals(e.getUserId()) && orderObj.get("orderState") != null && orderObj.get("orderState").toString().equals("6")) {
                    HashMap param = new HashMap();
                    param.put("tableName", "wa_order");
                    HashMap mapClass = new HashMap();
                    mapClass.put("order_state", "7");
                    mapClass.put("pj_state", pjState);
                    mapClass.put("pj_date", new Date());
                    mapClass.put("pj_content", pjContent);
                    param.put("mapClass", mapClass);
                    param.put("keyName", "order_id");
                    param.put("keyValue", orderId);
                    this.commonMapper.updateSingleBO(param);
                    ret = "1";
                    obj.put("content", ConstantUtil.MSG_SUCCESS);
                }
            }
        } catch (Exception var13) {
            ret = "-2";
            obj.put("content", ConstantUtil.MSG_FAILS);
        }

        obj.put("status", ret);
        this.echoJSON(response, obj);
    }

    @RequestMapping({"front/customer/CustomerBackOrderCtrl-editFahuo"})
    public String editFahuo(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        String orderId = CommonUtil.GetRequestParam(request, "orderId", "0");
        if(CommonUtil.getIntFromString(orderId) > 0) {
            Map orderInfo = this.getMyOneSingBO("wa_order", "order_id", orderId);
            if(orderInfo != null && orderInfo.get("userId") != null && orderInfo.get("userId").toString().equals(myInfo.getUserId()) && orderInfo.get("orderState") != null && (orderInfo.get("orderState").toString().equals("2") || orderInfo.get("orderState").toString().equals("3")) && orderInfo.get("isSelfCar") != null && orderInfo.get("isSelfCar").toString().equals("0")) {
                String strsql = "SELECT a.zorder_id, a.zorder_dj, a.zorder_num, a.zorder_money, a.zorder_date, a.zorder_state, b.vehicle_id, b.vehicle_no, b.vehicle_size, b.vehicle_sj_size, b.sj_lxr, b.sj_lxr_phone, b.vehicle_state FROM wa_zorder as a INNER JOIN wa_order_vehicle as b ON a.vehicle_id = b.vehicle_id WHERE a.order_id = \'" + orderId + "\' and a.zorder_state <2";
                HashMap param = new HashMap();
                param.put("strsql", strsql);
                ArrayList zorderList = this.commonMapper.simpleSelectReturnList(param);
                ConvertUtil.convertDataBaseMapToJavaMap(zorderList);
                boolean eFlag = true;
                if(zorderList != null && zorderList.size() > 0) {
                    eFlag = false;
                }

                model.addAttribute("userName", myInfo.getUserName());
                model.addAttribute("orderId", orderId);
                model.addAttribute("zorderList", zorderList);
                model.addAttribute("parentMenuNo", Integer.valueOf(1));
                if(eFlag) {
                    return "views/front/customer/order/editFahuo.jsp";
                }

                return "views/front/customer/order/editShouhuo.jsp";
            }
        }

        return null;
    }

    @RequestMapping(
            value = {"front/customer/CustomerBackOrderCtrl-setFahuo"},
            method = {RequestMethod.POST}
    )
    public void setFahuo(HttpServletRequest request, HttpServletResponse response, Model model) {
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        String orderId = CommonUtil.GetRequestParam(request, "orderId", "0");
        String cfDate = CommonUtil.GetRequestParam(request, "cfDate", "");
        String ddDate = CommonUtil.GetRequestParam(request, "ddDate", "");
        String orderData = CommonUtil.GetRequestParam(request, "orderData", "");
        ArrayList dataList = ConvertUtil.converJSONtoArrayListMap(orderData);
        String cphList = "";
        String sjdhList = "";
        String sjmcList = "";

        Map queryParam;
        for(Iterator sql = dataList.iterator(); sql.hasNext(); sjmcList = sjmcList + ("".equals(sjmcList)?queryParam.get("sjmc"):"," + queryParam.get("sjmc"))) {
            queryParam = (Map)sql.next();
            cphList = cphList + ("".equals(cphList)?queryParam.get("cph"):"," + queryParam.get("cph"));
            sjdhList = sjdhList + ("".equals(sjdhList)?queryParam.get("sjdh"):"," + queryParam.get("sjdh"));
        }

        String sql1 = "";
        sql1 = " CALL createZOrderProcessZj(\'" + orderId + "\',\'" + cfDate + "\',\'" + ddDate + "\',\'" + cphList + "\',\'" + sjdhList + "\',\'" + sjmcList + "\')";
        HashMap queryParam1 = new HashMap();
        ((Map)queryParam1).put("strsql", sql1);

        try {
            if(CommonUtil.getIntFromString(orderId) > 0 && !cfDate.equals("") && !ddDate.equals("") && !orderData.equals("")) {
                this.commonMapper.simpleSelectReturnList((Map)queryParam1);
                ret = "1";
                obj.put("content", ConstantUtil.MSG_SUCCESS);
            }
        } catch (Exception var17) {
            ret = "-2";
            obj.put("content", ConstantUtil.MSG_FAILS);
        }

        obj.put("status", ret);
        this.echoJSON(response, obj);
    }

    @RequestMapping(
            value = {"front/customer/CustomerBackOrderCtrl-setShouhuo"},
            method = {RequestMethod.POST}
    )
    public void setShouhuo(HttpServletRequest request, HttpServletResponse response, Model model) {
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        String orderId = CommonUtil.GetRequestParam(request, "orderId", "0");
        String vehicleId = CommonUtil.GetRequestParam(request, "vehicleId", "0");
        String zorderId = CommonUtil.GetRequestParam(request, "zorderId", "0");

        try {
            MyInfo e = this.getMyInfo(request);
            if(e != null && CommonUtil.getIntFromString(orderId) > 0) {
                Map orderObj = this.getMyOneSingBO("wa_order", "order_id", orderId);
                if(orderObj != null && orderObj.get("userId") != null && orderObj.get("userId").toString().equals(e.getUserId())) {
                    String sql = "";
                    sql = "CALL khFahuoQrProcess(\'" + vehicleId + "\', \'" + zorderId + "\') ";
                    HashMap queryParam = new HashMap();
                    queryParam.put("strsql", sql);
                    this.commonMapper.simpleSelectReturnList(queryParam);
                    ret = "1";
                    obj.put("content", ConstantUtil.MSG_SUCCESS);
                }
            }
        } catch (Exception var13) {
            ret = "-2";
            obj.put("content", ConstantUtil.MSG_FAILS);
        }

        obj.put("status", ret);
        this.echoJSON(response, obj);
    }

    @RequestMapping(
            value = {"front/customer/CustomerBackOrderCtrl-setShouhuoAll"},
            method = {RequestMethod.POST}
    )
    public void setShouhuoAll(HttpServletRequest request, HttpServletResponse response, Model model) {
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        String orderId = CommonUtil.GetRequestParam(request, "orderId", "0");
        MyInfo myInfo = this.getMyInfo(request);
        if(myInfo != null && CommonUtil.getIntFromString(orderId) > 0) {
            Map orderObj = this.getMyOneSingBO("wa_order", "order_id", orderId);
            if(orderObj != null && orderObj.get("userId") != null && orderObj.get("userId").toString().equals(myInfo.getUserId())) {
                HashMap pm = new HashMap();
                pm.put("tableName", "wa_zorder");
                HashMap conditionMap = new HashMap();
                conditionMap.put("order_id", orderId);
                conditionMap.put("zorder_state", Integer.valueOf(1));
                pm.put("condition", conditionMap);
                ArrayList vehicleList = new ArrayList();

                try {
                    vehicleList = this.commonMapper.selectSingleBO(pm);
                    ConvertUtil.convertDataBaseMapToJavaMap(vehicleList);
                } catch (Exception var17) {
                    ret = "-2";
                    obj.put("content", ConstantUtil.MSG_FAILS);
                }

                for(int ii = 0; ii < vehicleList.size(); ++ii) {
                    try {
                        String e = "";
                        String zorderId = ((Map)vehicleList.get(ii)).get("zorderId") != null?((Map)vehicleList.get(ii)).get("zorderId").toString():"0";
                        String vehicleId = ((Map)vehicleList.get(ii)).get("vehicleId") != null?((Map)vehicleList.get(ii)).get("vehicleId").toString():"0";
                        e = "CALL khFahuoQrProcess(\'" + vehicleId + "\', \'" + zorderId + "\') ";
                        HashMap queryParam = new HashMap();
                        queryParam.put("strsql", e);
                        this.commonMapper.simpleSelectReturnList(queryParam);
                        ret = "1";
                        obj.put("content", ConstantUtil.MSG_SUCCESS);
                    } catch (Exception var18) {
                        ret = "-2";
                        obj.put("content", ConstantUtil.MSG_FAILS);
                        break;
                    }
                }
            }
        }

        obj.put("status", ret);
        this.echoJSON(response, obj);
    }

    @RequestMapping(
            value = {"front/customer/CustomerBackOrderCtrl-cancelOrder"},
            method = {RequestMethod.POST}
    )
    public void cancelOrder(HttpServletRequest request, HttpServletResponse response, Model model) {
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        String orderId = CommonUtil.GetRequestParam(request, "orderId", "0");

        try {
            MyInfo e = this.getMyInfo(request);
            if(e != null && CommonUtil.getIntFromString(orderId) > 0) {
                Map orderObj = this.getMyOneSingBO("wa_order", "order_id", orderId);
                if(orderObj != null && orderObj.get("userId") != null && orderObj.get("userId").toString().equals(e.getUserId())) {
                    HashMap param = new HashMap();
                    param.put("tableName", "wa_order");
                    HashMap mapClass = new HashMap();
                    mapClass.put("order_state", "-1");
                    param.put("mapClass", mapClass);
                    param.put("keyName", "order_id");
                    param.put("keyValue", orderId);
                    this.commonMapper.updateSingleBO(param);
                    ret = "1";
                    obj.put("content", ConstantUtil.MSG_SUCCESS);
                }
            }
        } catch (Exception var11) {
            ret = "-2";
            obj.put("content", ConstantUtil.MSG_FAILS);
        }

        obj.put("status", ret);
        this.echoJSON(response, obj);
    }

    @RequestMapping({"front/customer/CustomerBackOrderCtrl-wlOrderList"})
    public String wlOrderList(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        Map param = this.getConditionParam(request);
        param.put("userId", myInfo.getUserId());
        ArrayList waUserVehicleList = this.customerBackOrderMapper.getWaUserVehicleList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(waUserVehicleList);
        model.addAttribute("waUserVehicleList", waUserVehicleList);
        int totalCount = this.customerBackOrderMapper.getWaUserVehicleListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "2");
        return "views/front/customer/order/wlOrderList.jsp";
    }

    @RequestMapping({"front/customer/CustomerBackOrderCtrl-detailVehicle"})
    public String detailVehicle(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        String vehicleTrans = CommonUtil.GetRequestParam(request, "vehicleTrans", "0");
        Map vehicleInfo = this.getMyOneSingBO("wa_user_vehicle", "vehicle_trans", vehicleTrans);
        if(vehicleInfo != null && vehicleInfo.get("userId") != null && vehicleInfo.get("userId").toString().equals(myInfo.getUserId()) && vehicleInfo.get("orderId") != null) {
            ArrayList vehicleList = null;
            HashMap pm = new HashMap();
            pm.put("tableName", "wa_order_vehicle");
            HashMap conditionMap = new HashMap();
            conditionMap.put("order_id", vehicleInfo.get("orderId").toString());
            conditionMap.put("vehicle_state", Integer.valueOf(2));
            pm.put("condition", conditionMap);

            try {
                vehicleList = this.commonMapper.selectSingleBO(pm);
                ConvertUtil.convertDataBaseMapToJavaMap(vehicleList);
            } catch (Exception var11) {
                var11.printStackTrace();
            }

            model.addAttribute("vehicleList", vehicleList);
        }

        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "2");
        model.addAttribute("userName", myInfo.getUserName());
        return "views/front/customer/order/detailVehicle.jsp";
    }

    @RequestMapping({"front/customer/CustomerBackOrderCtrl-editQiangdan"})
    public String editQiangdan(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        String vehicleTrans = CommonUtil.GetRequestParam(request, "vehicleTrans", "0");
        Map vehicleInfo = this.getMyOneSingBO("wa_user_vehicle", "vehicle_trans", vehicleTrans);
        if(vehicleInfo != null && vehicleInfo.get("userId") != null && vehicleInfo.get("userId").toString().equals(myInfo.getUserId()) && vehicleInfo.get("orderId") != null) {
            ArrayList vehicleGrabList = null;
            String strsql = "SELECT a.grab_id, a.grab_money, b.company_name, b.company_lxr, b.company_lxr_phone FROM wa_vehicle_grab as a INNER JOIN wa_userinfo as b ON a.wl_user_id = b.user_id WHERE a.order_id=\'" + vehicleInfo.get("orderId").toString() + "\' and a.grab_state=0";
            HashMap pm = new HashMap();
            pm.put("strsql", strsql);

            try {
                vehicleGrabList = this.commonMapper.simpleSelectReturnList(pm);
                ConvertUtil.convertDataBaseMapToJavaMap(vehicleGrabList);
            } catch (Exception var11) {
                var11.printStackTrace();
            }

            model.addAttribute("vehicleGrabList", vehicleGrabList);
        }

        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("vehicleTrans", vehicleTrans);
        model.addAttribute("parentMenuNo", Integer.valueOf(1));
        return "views/front/customer/order/editQiangdan.jsp";
    }

    @RequestMapping(
            value = {"front/customer/CustomerBackOrderCtrl-setQiangdan"},
            method = {RequestMethod.POST}
    )
    public void setQiangdan(HttpServletRequest request, HttpServletResponse response, Model model) {
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        String mgrIp = this.getIpAddr(request);
        String vehicleTrans = CommonUtil.GetRequestParam(request, "vehicleTrans", "0");
        String grabId = CommonUtil.GetRequestParam(request, "grabId", "0");
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        Map userInfo = this.getUserInfo(userId);
        Map grabInfo = this.getMyOneSingBO("wa_vehicle_grab", "grab_id", grabId);
        if(grabInfo != null) {
            String e = grabInfo.get("grabMoney").toString();
            String grabObj = userInfo.get("userMoney").toString();
            if(Double.parseDouble(e) > Double.parseDouble(grabObj)) {
                obj.put("content", ConstantUtil.MSG_MONEY_ERR);
                obj.put("status", "-3");
                this.echo(response, obj);
                return;
            }
        }

        try {
            if(myInfo != null && CommonUtil.getIntFromString(vehicleTrans) > 0 && CommonUtil.getIntFromString(grabId) > 0) {
                Map e1 = this.getMyOneSingBO("wa_user_vehicle", "vehicle_trans", vehicleTrans);
                if(e1 != null && e1.get("userId") != null && e1.get("userId").toString().equals(myInfo.getUserId())) {
                    Map grabObj1 = this.getMyOneSingBO("wa_vehicle_grab", "grab_id", grabId);
                    if(grabObj1 != null && grabObj1.get("orderId") != null && e1.get("orderId") != null && e1.get("orderId").toString().equals(grabObj1.get("orderId").toString())) {
                        String sql = "";
                        sql = " CALL khQiangdanProcess(\'" + vehicleTrans + "\',\'" + grabId + "\', \'" + mgrIp + "\')";
                        HashMap queryParam = new HashMap();
                        queryParam.put("strsql", sql);
                        this.commonMapper.simpleSelectReturnList(queryParam);
                        ret = "1";
                        obj.put("content", ConstantUtil.MSG_SUCCESS);
                    }
                }
            }
        } catch (Exception var17) {
            ret = "-2";
            obj.put("content", ConstantUtil.MSG_FAILS);
        }

        obj.put("status", ret);
        this.echoJSON(response, obj);
    }

    @RequestMapping({"front/customer/CustomerBackOrderCtrl-editVehicleShouhuo"})
    public String editVehicleShouhuo(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        String vehicleTrans = CommonUtil.GetRequestParam(request, "vehicleTrans", "0");
        String orderId = CommonUtil.GetRequestParam(request, "orderId", "0");
        Map vehicleInfo = this.getMyOneSingBO("wa_user_vehicle", "vehicle_trans", vehicleTrans);
        if(vehicleInfo != null && vehicleInfo.get("userId") != null && vehicleInfo.get("userId").toString().equals(myInfo.getUserId()) && vehicleInfo.get("orderId") != null && vehicleInfo.get("orderId").toString().equals(orderId)) {
            ArrayList zorderList = null;
            String strsql = "SELECT a.zorder_id, a.zorder_num, a.zorder_money, b.vehicle_no, b.sj_lxr, b.sj_lxr_phone, b.vehicle_id  FROM wa_zorder AS a INNER JOIN wa_order_vehicle as b ON a.vehicle_id = b.vehicle_id WHERE a.order_id=\'" + orderId + "\' and a.zorder_state=1";
            HashMap pm = new HashMap();
            pm.put("strsql", strsql);

            try {
                zorderList = this.commonMapper.simpleSelectReturnList(pm);
                ConvertUtil.convertDataBaseMapToJavaMap(zorderList);
            } catch (Exception var12) {
                var12.printStackTrace();
            }

            model.addAttribute("zorderList", zorderList);
        }

        model.addAttribute("orderId", orderId);
        model.addAttribute("vehicleTrans", vehicleTrans);
        return "views/front/customer/order/editVehicleShouhuo.jsp";
    }

    @RequestMapping(
            value = {"front/customer/CustomerBackOrderCtrl-setVehicleShouhuo"},
            method = {RequestMethod.POST}
    )
    public void setVehicleShouhuo(HttpServletRequest request, HttpServletResponse response, Model model) {
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        String vehicleTrans = CommonUtil.GetRequestParam(request, "vehicleTrans", "0");
        String vehicleId = CommonUtil.GetRequestParam(request, "vehicleId", "0");
        String zorderId = CommonUtil.GetRequestParam(request, "zorderId", "0");

        try {
            MyInfo e = this.getMyInfo(request);
            if(e != null && CommonUtil.getIntFromString(vehicleTrans) > 0) {
                Map vehicleObj = this.getMyOneSingBO("wa_user_vehicle", "vehicle_trans", vehicleTrans);
                if(vehicleObj != null && vehicleObj.get("userId") != null && vehicleObj.get("userId").toString().equals(e.getUserId())) {
                    String sql = "";
                    sql = "CALL khFahuoQrProcess(\'" + vehicleId + "\', \'" + zorderId + "\') ";
                    HashMap queryParam = new HashMap();
                    queryParam.put("strsql", sql);
                    this.commonMapper.simpleSelectReturnList(queryParam);
                    ret = "1";
                    obj.put("content", ConstantUtil.MSG_SUCCESS);
                }
            }
        } catch (Exception var13) {
            ret = "-2";
            obj.put("content", ConstantUtil.MSG_FAILS);
        }

        obj.put("status", ret);
        this.echoJSON(response, obj);
    }

    @RequestMapping(
            value = {"front/customer/CustomerBackOrderCtrl-setVehicleFukuan"},
            method = {RequestMethod.POST}
    )
    public void setVehicleFukuan(HttpServletRequest request, HttpServletResponse response, Model model) {
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        String vehicleTrans = CommonUtil.GetRequestParam(request, "vehicleTrans", "0");

        try {
            MyInfo e = this.getMyInfo(request);
            if(e != null && CommonUtil.getIntFromString(vehicleTrans) > 0) {
                Map vehicleObj = this.getMyOneSingBO("wa_user_vehicle", "vehicle_trans", vehicleTrans);
                if(vehicleObj != null && vehicleObj.get("userId") != null && vehicleObj.get("userId").toString().equals(e.getUserId())) {
                    ret = "1";
                    obj.put("content", ConstantUtil.MSG_SUCCESS);
                }
            }
        } catch (Exception var9) {
            ret = "-2";
            obj.put("content", ConstantUtil.MSG_FAILS);
        }

        obj.put("status", ret);
        this.echoJSON(response, obj);
    }

    @RequestMapping(
            value = {"front/customer/CustomerBackOrderCtrl-cancelVehicle"},
            method = {RequestMethod.POST}
    )
    public void cancelVehicle(HttpServletRequest request, HttpServletResponse response, Model model) {
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        String vehicleTrans = CommonUtil.GetRequestParam(request, "vehicleTrans", "0");

        try {
            MyInfo e = this.getMyInfo(request);
            if(e != null && CommonUtil.getIntFromString(vehicleTrans) > 0) {
                Map vehicleObj = this.getMyOneSingBO("wa_user_vehicle", "vehicle_trans", vehicleTrans);
                if(vehicleObj != null && vehicleObj.get("userId") != null && vehicleObj.get("userId").toString().equals(e.getUserId())) {
                    HashMap param = new HashMap();
                    param.put("tableName", "wa_user_vehicle");
                    HashMap mapClass = new HashMap();
                    mapClass.put("vehicle_state", "-1");
                    param.put("mapClass", mapClass);
                    param.put("keyName", "vehicle_trans");
                    param.put("keyValue", vehicleTrans);
                    this.commonMapper.updateSingleBO(param);
                    ret = "1";
                    obj.put("content", ConstantUtil.MSG_SUCCESS);
                }
            }
        } catch (Exception var11) {
            ret = "-2";
            obj.put("content", ConstantUtil.MSG_FAILS);
        }

        obj.put("status", ret);
        this.echoJSON(response, obj);
    }

    @RequestMapping({"front/customer/CustomerBackOrderCtrl-estiList"})
    public String estiList(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        Map param = this.getConditionParam(request);
        param.put("userId", myInfo.getUserId());
        ArrayList estiList = this.customerBackOrderMapper.getEstiList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(estiList);
        model.addAttribute("estiList", estiList);
        int totalCount = this.customerBackOrderMapper.getEstiListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "3");
        return "views/front/customer/order/estiList.jsp";
    }

    @RequestMapping({"front/customer/CustomerBackOrderCtrl-detailWuliu"})
    public String detailWuliu(HttpServletRequest request, HttpServletResponse response) {
        return "views/front/customer/ordersheet/sendsheet_detail.jsp";
    }

    @RequestMapping({"front/customer/ordersheet_center"})
    public String ordersheet_center(HttpServletRequest request, HttpServletResponse response) {
        return "views/front/customer/ordersheet_center.jsp";
    }

    @RequestMapping({"front/customer/ordersheet/estimate"})
    public String estimate(HttpServletRequest request, HttpServletResponse response) {
        return "views/front/customer/ordersheet/estimate.jsp";
    }

    @RequestMapping({"front/customer/ordersheet/payment"})
    public String pay_money(HttpServletRequest request, HttpServletResponse response) {
        return "views/front/customer/ordersheet/payment.jsp";
    }

    @RequestMapping({"front/customer/ordersheet/cancel"})
    public String cancel(HttpServletRequest request, HttpServletResponse response) {
        return "views/front/customer/ordersheet/cancel.jsp";
    }
}