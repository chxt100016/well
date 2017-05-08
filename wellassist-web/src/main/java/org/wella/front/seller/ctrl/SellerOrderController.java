package org.wella.front.seller.ctrl;

import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import org.wella.front.seller.mapper.FrontSellerProdMapper;
import org.wella.front.seller.mapper.FrontSellerProdUserMapper;
import org.wella.front.seller.mapper.FrontSellerUserRelationMapper;
import org.wella.front.seller.mapper.SellerOrderMapper;
import org.wella.houtai.user.mapper.HoutaiUserSellerMapper;

@Controller
public class SellerOrderController extends BaseController {
    @Autowired
    private UploadController uploadController;
    @Autowired
    public FrontSellerUserRelationMapper userRelationMapper;
    @Autowired
    public FrontSellerProdUserMapper prodUserMapper;
    @Autowired
    public FrontSellerProdMapper prodMapper;
    @Autowired
    private HoutaiUserSellerMapper houtaiuserSellerMapper;
    @Autowired
    private SellerOrderMapper sellerOrderMapper;

    public SellerOrderController() {
    }

    @RequestMapping({"front/seller/SellerOrderController-prodPub"})
    public String prod_publish(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        if(myInfo == null) {
            return "redirect:/front/SellerLoginController-login";
        } else {
            String isAllEdit = "0";
            String prodId = CommonUtil.GetRequestParam(request, "prodId", "0");
            if(CommonUtil.getIntFromString(prodId) > 0) {
                Map param = this.getMyOneSingBO("wa_prod", "prod_id", prodId);
                if(param != null && param.get("userId") != null && param.get("userId").toString().equals(myInfo.getUserId()) && param.get("prodState") != null && !param.get("prodState").toString().equals("1")) {
                    if(param.get("prodState").toString().equals("-1") || param.get("prodState").toString().equals("2")) {
                        isAllEdit = "1";
                    }

                    model.addAttribute("prodInfo", param);
                    if(param.get("prodRegionId") != null && !param.get("prodRegionId").toString().equals("") && param.get("prodRegionId").toString().length() > 4) {
                        String shengRegionList = param.get("prodRegionId").toString().substring(0, 2) + "0000";
                        String userInfo = param.get("prodRegionId").toString().substring(0, 4) + "00";
                        model.addAttribute("shengRegionId", shengRegionList);
                        model.addAttribute("shiRegionId", userInfo);
                        if(!shengRegionList.equals("000000")) {
                            HashMap strsql = new HashMap();
                            strsql.put("parentRegionId", shengRegionList);
                            ArrayList shiRegionList = this.houtaiuserSellerMapper.getRegionList(strsql);
                            ConvertUtil.convertDataBaseMapToJavaMap(shiRegionList);
                            model.addAttribute("shiRegionList", shiRegionList);
                        }
                    }
                }
            }

            HashMap param1 = new HashMap();
            param1.put("parentRegionId", "0");
            ArrayList shengRegionList1 = this.houtaiuserSellerMapper.getRegionList(param1);
            ConvertUtil.convertDataBaseMapToJavaMap(shengRegionList1);
            model.addAttribute("shengRegionList", shengRegionList1);
            Map userInfo1 = this.getMyOneSingBO("wa_userinfo", "user_id", myInfo.getUserId());
            if(userInfo1 != null && userInfo1.get("companyImg") != null) {
                model.addAttribute("companyImg", userInfo1.get("companyImg").toString());
            }

            String strsql1 = "SELECT COUNT(*) FROM wa_user_money WHERE user_id = \'" + myInfo.getUserId() + "\' AND DATE_ADD(jy_date,INTERVAL 3 MONTH) > NOW()";
            param1.put("strsql", strsql1);
            model.addAttribute("threeJyCn", Integer.valueOf(this.commonMapper.simpleSelectReturnInt(param1)));
            strsql1 = "SELECT COUNT(*) FROM wa_user_money WHERE user_id = \'" + myInfo.getUserId() + "\' AND jy_state = 1";
            param1.put("strsql", strsql1);
            model.addAttribute("ingJyCn", Integer.valueOf(this.commonMapper.simpleSelectReturnInt(param1)));
            model.addAttribute("isAllEdit", isAllEdit);
            model.addAttribute("parentMenuNo", "1");
            model.addAttribute("childMenuNo", "1");
            model.addAttribute("userName", myInfo.getUserName());
            return "front/seller/order/prodPub";
        }
    }

    @RequestMapping(
            value = {"front/seller/SellerOrderController-setProdPub"},
            method = {RequestMethod.POST}
    )
    public void setProdPub(HttpServletRequest request, HttpServletResponse response, Model model) {
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);

        try {
            HttpSession e = request.getSession();
            MyInfo myInfo = (MyInfo)e.getAttribute("MY_INFO");
            if(myInfo != null) {
                String prodState = CommonUtil.GetRequestParam(request, "prodState", "0");
                if(!prodState.equals("1")) {
                    prodState = "0";
                }

                String prodRegionId_0 = CommonUtil.GetRequestParam(request, "prodRegionId_0", "");
                String prodRegionId_1 = CommonUtil.GetRequestParam(request, "prodRegionId_1", "");
                int prodRegionId = CommonUtil.getIntFromString(prodRegionId_0);
                if(CommonUtil.getIntFromString(prodRegionId_1) > 0) {
                    prodRegionId = CommonUtil.getIntFromString(prodRegionId_1);
                }

                String prodImg = CommonUtil.GetRequestParam(request, "prodImg", "");
                if(!prodImg.equals("") && prodImg.indexOf("temp") > 0) {
                    prodImg = this.uploadController.moveFile(request, prodImg, "prod/prod");
                }

                Object param = this.getBoClass(request, "wa_prod");
                Map mapClass = (Map)((Map)param).get("mapClass");
                mapClass.put("prod_state", prodState);
                mapClass.put("prod_region_id", Integer.valueOf(prodRegionId));
                mapClass.put("prod_img", prodImg);
                mapClass.put("create_date", new Date());
                mapClass.put("user_id", myInfo.getUserId());
                String prodId = CommonUtil.GetRequestParam(request, "prodId", "0");
                if(CommonUtil.getIntFromString(prodId) > 0) {
                    Map prodObj = this.getMyOneSingBO("wa_prod", "prod_id", prodId);
                    if(prodObj != null && prodObj.get("userId") != null && prodObj.get("userId").toString().equals(myInfo.getUserId())) {
                        if(prodObj.get("prodState") != null && (prodObj.get("prodState").toString().equals("-1") || prodObj.get("prodState").toString().equals("2"))) {
                            param = new HashMap();
                            ((Map)param).put("tableName", "wa_prod");
                            HashMap mapClass1 = new HashMap();
                            String prodNum = CommonUtil.GetRequestParam(request, "prodNum", "0");
                            String prodMoney = CommonUtil.GetRequestParam(request, "prodMoney", "0");
                            mapClass1.put("prod_num", prodNum);
                            mapClass1.put("prod_money", prodMoney);
                            ((Map)param).put("mapClass", mapClass1);
                        }

                        ((Map)param).put("keyName", "prod_id");
                        ((Map)param).put("keyValue", prodId);
                        this.commonMapper.updateSingleBO((Map)param);
                        ret = "1";
                        obj.put("content", ConstantUtil.MSG_SUCCESS);
                    }
                } else {
                    this.commonMapper.insertSingleBO((Map)param);
                    ret = "1";
                    obj.put("content", ConstantUtil.MSG_SUCCESS);
                }
            }
        } catch (Exception var19) {
            ret = "-2";
            obj.put("content", ConstantUtil.MSG_FAILS);
        }

        obj.put("status", ret);
        this.echoJSON(response, obj);
    }

    @RequestMapping({"front/seller/SellerOrderController-prodList"})
    public String prod_sheet(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        MyInfo myInfo = (MyInfo)session.getAttribute("MY_INFO");
        if(myInfo == null) {
            return "redirect:/front/SellerLoginController-login";
        } else {
            Map param = this.getConditionParam(request);
            param.put("userId", myInfo.getUserId());
            ArrayList waProdList = this.sellerOrderMapper.getWaProdList(param);
            ConvertUtil.convertDataBaseMapToJavaMap(waProdList);
            if(waProdList != null && waProdList.size() > 0) {
                String totalCount = "";
                String dateObjStr = "";

                for(int strsql = 0; strsql < waProdList.size(); ++strsql) {
                    dateObjStr = "\'" + (((Map)waProdList.get(strsql)).get("createDate") != null?((Map)waProdList.get(strsql)).get("createDate").toString():"0000-00-00") + "\'";
                    totalCount = totalCount + "," + dateObjStr;
                }

                if(!totalCount.equals("")) {
                    String var17 = "SELECT prod_id, prod_name, prod_img, prod_num, prod_money, prod_state, DATE(create_date) as create_date FROM wa_prod WHERE user_id = \'" + myInfo.getUserId() + "\' AND prod_state > -3 AND DATE(create_date) in (" + totalCount + ")";
                    String prodState = CommonUtil.GetRequestParam(request, "prodState", "");
                    if(!prodState.equals("")) {
                        var17 = var17 + " AND prod_state = " + prodState;
                    }

                    param.put("strsql", var17);
                    ArrayList spList = this.commonMapper.simpleSelectReturnList(param);
                    ConvertUtil.convertDataBaseMapToJavaMap(spList);

                    for(int i = 0; i < waProdList.size(); ++i) {
                        ArrayList spSubList = new ArrayList();

                        for(int j = 0; j < spList.size(); ++j) {
                            if(((Map)waProdList.get(i)).get("createDate") != null && ((Map)spList.get(j)).get("createDate") != null && ((Map)waProdList.get(i)).get("createDate").toString().equals(((Map)spList.get(j)).get("createDate").toString())) {
                                spSubList.add((Map)spList.get(j));
                            }
                        }

                        ((Map)waProdList.get(i)).put("spList", spSubList);
                    }
                }
            }

            model.addAttribute("waProdList", waProdList);
            int var16 = this.sellerOrderMapper.getWaProdListCount(param);
            this.setPagenationInfo(request, var16, Integer.parseInt(param.get("page").toString()));
            model.addAttribute("parentMenuNo", "1");
            model.addAttribute("childMenuNo", "2");
            model.addAttribute("userName", myInfo.getUserName());
            return "front/seller/order/prodList";
        }
    }

    @RequestMapping({"front/seller/SellerOrderController-prodDetail"})
    public String prodDetail(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        MyInfo myInfo = (MyInfo)session.getAttribute("MY_INFO");
        if(myInfo != null) {
            String prodId = CommonUtil.GetRequestParam(request, "prodId", "0");
            Map prodInfo = this.getMyOneSingBO("wa_prod", "prod_id", prodId);
            if(prodInfo != null && prodInfo.get("userId") != null && prodInfo.get("userId").toString().equals(myInfo.getUserId())) {
                prodInfo.put("prodRegionName", "");
                if(prodInfo.get("prodRegionId") != null) {
                    try {
                        prodInfo.put("prodRegionName", this.getRegionDetailName(prodInfo.get("prodRegionId").toString()));
                    } catch (Exception var12) {
                        var12.printStackTrace();
                    }
                }

                prodInfo.put("companyImg", "");
                prodInfo.put("companyName", "");
                Map userInfo = this.getMyOneSingBO("wa_userinfo", "user_id", myInfo.getUserId());
                if(userInfo != null && userInfo.get("companyName") != null) {
                    prodInfo.put("companyName", userInfo.get("companyName").toString());
                }

                if(userInfo != null && userInfo.get("companyImg") != null) {
                    prodInfo.put("companyImg", userInfo.get("companyImg").toString());
                }

                model.addAttribute("prodInfo", prodInfo);
                String strsql = "SELECT prod_id, prod_type, prod_name, prod_img FROM wa_prod WHERE user_id = \'" + myInfo.getUserId() + "\' and prod_id <> \'" + prodId + "\' ORDER BY sale_num DESC LIMIT 8";
                HashMap param = new HashMap();
                param.put("strsql", strsql);
                ArrayList prodList = this.commonMapper.simpleSelectReturnList(param);
                ConvertUtil.convertDataBaseMapToJavaMap(prodList);
                model.addAttribute("prodList", prodList);
            }

            model.addAttribute("parentMenuNo", "1");
            model.addAttribute("childMenuNo", "2");
            model.addAttribute("userName", myInfo.getUserName());
            return "front/seller/order/prodDetail";
        } else {
            return "redirect:/front/SellerLoginController-login";
        }
    }

    @RequestMapping(
            value = {"front/seller/SellerOrderController-setProdState"},
            method = {RequestMethod.POST}
    )
    public void setProdState(HttpServletRequest request, HttpServletResponse response, Model model) {
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        String prodId = CommonUtil.GetRequestParam(request, "prodId", "0");

        try {
            HttpSession e = request.getSession();
            MyInfo myInfo = (MyInfo)e.getAttribute("MY_INFO");
            if(myInfo != null && CommonUtil.getIntFromString(prodId) > 0) {
                Map prodObj = this.getMyOneSingBO("wa_prod", "prod_id", prodId);
                if(prodObj != null && prodObj.get("userId") != null && prodObj.get("userId").toString().equals(myInfo.getUserId()) && prodObj.get("prodState") != null) {
                    String prodState = prodObj.get("prodState").toString();
                    if(!prodState.equals("1")) {
                        if(!prodState.equals("-2") && !prodState.equals("0")) {
                            if(prodState.equals("-1")) {
                                prodState = "2";
                            } else if(prodState.equals("2")) {
                                prodState = "-1";
                            }
                        } else {
                            prodState = "1";
                        }

                        HashMap param = new HashMap();
                        param.put("tableName", "wa_prod");
                        HashMap mapClass = new HashMap();
                        mapClass.put("prod_state", prodState);
                        param.put("mapClass", mapClass);
                        param.put("keyName", "prod_id");
                        param.put("keyValue", prodId);
                        this.commonMapper.updateSingleBO(param);
                        ret = "1";
                        obj.put("content", ConstantUtil.MSG_SUCCESS);
                    }
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
            value = {"front/seller/SellerOrderController-deleteProd"},
            method = {RequestMethod.POST}
    )
    public void deleteProd(HttpServletRequest request, HttpServletResponse response, Model model) {
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        String prodId = CommonUtil.GetRequestParam(request, "prodId", "0");

        try {
            HttpSession e = request.getSession();
            MyInfo myInfo = (MyInfo)e.getAttribute("MY_INFO");
            if(myInfo != null && CommonUtil.getIntFromString(prodId) > 0) {
                Map prodObj = this.getMyOneSingBO("wa_prod", "prod_id", prodId);
                if(prodObj != null && prodObj.get("userId") != null && prodObj.get("userId").toString().equals(myInfo.getUserId())) {
                    HashMap param = new HashMap();
                    param.put("tableName", "wa_prod");
                    HashMap mapClass = new HashMap();
                    mapClass.put("prod_state", "-3");
                    param.put("mapClass", mapClass);
                    param.put("keyName", "prod_id");
                    param.put("keyValue", prodId);
                    this.commonMapper.updateSingleBO(param);
                    ret = "1";
                    obj.put("content", ConstantUtil.MSG_SUCCESS);
                }
            }
        } catch (Exception var12) {
            ret = "-2";
            obj.put("content", ConstantUtil.MSG_FAILS);
        }

        obj.put("status", ret);
        this.echoJSON(response, obj);
    }

    @RequestMapping({"front/seller/SellerOrderController-orderList"})
    public String ordersheet_list(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        MyInfo myInfo = (MyInfo)session.getAttribute("MY_INFO");
        if(myInfo != null) {
            Map param = this.getConditionParam(request);
            param.put("userId", myInfo.getUserId());
            ArrayList waOrderList = this.sellerOrderMapper.getWaOrderList(param);
            ConvertUtil.convertDataBaseMapToJavaMap(waOrderList);
            model.addAttribute("waOrderList", waOrderList);
            int totalCount = this.sellerOrderMapper.getWaOrderListCount(param);
            this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
            model.addAttribute("parentMenuNo", "1");
            model.addAttribute("childMenuNo", "3");
            model.addAttribute("userName", myInfo.getUserName());
            return "front/seller/order/orderList";
        } else {
            return "redirect:/front/SellerLoginController-login";
        }
    }

    @RequestMapping({"front/seller/SellerOrderController-detailOrder"})
    public String detailOrder(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        MyInfo myInfo = (MyInfo)session.getAttribute("MY_INFO");
        if(myInfo == null) {
            return "redirect:/front/SellerLoginController-login";
        } else {
            Map param = this.getConditionParam(request);
            param.put("userId", myInfo.getUserId());
            String isEdit = CommonUtil.GetRequestParam(request, "isEdit", "0");
            String orderType = CommonUtil.GetRequestParam(request, "orderType", "0");
            String orderId = CommonUtil.GetRequestParam(request, "orderId", "0");
            if(!isEdit.equals("1")) {
                isEdit = "0";
            }

            if(!orderType.equals("1")) {
                orderType = "0";
            }

            if(CommonUtil.getIntFromString(orderId) > 0) {
                Map orderInfo = this.getMyOneSingBO("wa_order", "order_id", orderId);
                if(orderInfo != null && orderInfo.get("supplierId") != null && orderInfo.get("supplierId").toString().equals(myInfo.getUserId())) {
                    orderInfo.put("companyName", "");
                    if(orderInfo.get("userId") != null) {
                        Map vehicleList = this.getMyOneSingBO("wa_userinfo", "user_id", orderInfo.get("userId").toString());
                        if(vehicleList != null && vehicleList.get("companyName") != null) {
                            orderInfo.put("companyName", vehicleList.get("companyName").toString());
                        }
                    }

                    model.addAttribute("orderInfo", orderInfo);
                    if(isEdit.equals("1")) {
                        if(orderInfo.get("orderState") != null && (orderInfo.get("orderState").toString().equals("0") || orderInfo.get("orderState").toString().equals("1") || orderInfo.get("orderState").toString().equals("3"))) {
                            if(orderInfo.get("orderState").toString().equals("3")) {
                                if(!orderType.equals("1")) {
                                    isEdit = "0";
                                }
                            } else if(!orderType.equals("0")) {
                                isEdit = "0";
                            }
                        } else {
                            isEdit = "0";
                        }
                    }

                    if(orderInfo.get("orderState") != null) {
                        ArrayList vehicleList1 = null;
                        String strsql = "SELECT a.zorder_id, a.zorder_dj, a.zorder_num, a.zorder_money, a.zorder_date, a.zorder_state, b.vehicle_id, b.vehicle_no, b.vehicle_size, b.vehicle_sj_size, b.sj_lxr, b.sj_lxr_phone, b.vehicle_state, b.cf_date, b.dd_date FROM wa_zorder as a INNER JOIN wa_order_vehicle as b ON a.vehicle_id = b.vehicle_id WHERE a.order_id = \'" + orderId + "\'";
                        HashMap pm = new HashMap();
                        pm.put("strsql", strsql);

                        try {
                            vehicleList1 = this.commonMapper.simpleSelectReturnList(pm);
                            ConvertUtil.convertDataBaseMapToJavaMap(vehicleList1);
                        } catch (Exception var15) {
                            var15.printStackTrace();
                        }

                        model.addAttribute("vehicleList", vehicleList1);
                    }
                } else {
                    isEdit = "0";
                }
            } else {
                isEdit = "0";
            }

            model.addAttribute("isEdit", isEdit);
            model.addAttribute("orderType", orderType);
            model.addAttribute("parentMenuNo", "1");
            model.addAttribute("childMenuNo", "3");
            model.addAttribute("userName", myInfo.getUserName());
            return "front/seller/order/orderDetail";
        }
    }

    @RequestMapping({"front/seller/SellerOrderController-editFapiao"})
    public String editFapiao(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        String orderId = CommonUtil.GetRequestParam(request, "orderId", "0");
        if(CommonUtil.getIntFromString(orderId) > 0) {
            Map orderInfo = this.getMyOneSingBO("wa_order", "order_id", orderId);
            if(orderInfo != null && orderInfo.get("supplierId") != null && orderInfo.get("supplierId").toString().equals(myInfo.getUserId()) && orderInfo.get("orderState") != null && orderInfo.get("orderState").toString().equals("4")) {
                Map userInfo = this.getMyOneSingBO("wa_userinfo", "user_id", myInfo.getUserId());
                model.addAttribute("billUnit", userInfo.get("companyName") != null?userInfo.get("companyName").toString():"");
                model.addAttribute("billNo", CommonUtil.genKey(0));
                model.addAttribute("orderInfo", orderInfo);
                model.addAttribute("userName", myInfo.getUserName());
                model.addAttribute("parentMenuNo", Integer.valueOf(1));
            }
        }

        return "front/seller/order/editFapiao";
    }

    @RequestMapping(
            value = {"front/seller/SellerOrderController-setFapiao"},
            method = {RequestMethod.POST}
    )
    public void setFapiao(HttpServletRequest request, HttpServletResponse response, Model model) {
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        String orderId = CommonUtil.GetRequestParam(request, "orderId", "0");
        String billNo = CommonUtil.GetRequestParam(request, "billNo", "");

        try {
            MyInfo e = this.getMyInfo(request);
            if(CommonUtil.getIntFromString(orderId) > 0) {
                Map orderObj = this.getMyOneSingBO("wa_order", "order_id", orderId);
                if(orderObj != null && orderObj.get("supplierId") != null && orderObj.get("supplierId").toString().equals(e.getUserId()) && orderObj.get("orderState") != null && orderObj.get("orderState").toString().equals("4")) {
                    Map billObj = this.getMyOneSingBO("wa_bill", "bill_no", billNo);
                    if(billObj == null) {
                        Map param = this.getBoClass(request, "wa_bill");
                        Map mapClass = (Map)param.get("mapClass");
                        mapClass.put("bill_no", billNo);
                        mapClass.put("order_id", orderId);
                        mapClass.put("supplier_id", e.getUserId());
                        mapClass.put("bill_money", orderObj.get("saleSjMoney") != null?orderObj.get("saleSjMoney").toString():"0");
                        mapClass.put("bill_date", new Date());
                        mapClass.put("tj_date", new Date());
                        mapClass.put("confirm_user_id", orderObj.get("userId") != null?orderObj.get("userId").toString():"0");
                        this.commonMapper.insertSingleBO(param);
                        HashMap param1 = new HashMap();
                        HashMap mapClass1 = new HashMap();
                        mapClass1.put("order_state", "5");
                        param1.put("tableName", "wa_order");
                        param1.put("keyName", "order_id");
                        param1.put("keyValue", orderId);
                        param1.put("mapClass", mapClass1);
                        this.commonMapper.updateSingleBO(param1);
                        ret = "1";
                        obj.put("content", ConstantUtil.MSG_SUCCESS);
                    }
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
            value = {"front/seller/SellerOrderController-setOrderInfo"},
            method = {RequestMethod.POST}
    )
    public void setOrderInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        String ipAddr = this.getIpAddr(request);
        String orderId = CommonUtil.GetRequestParam(request, "orderId", "0");

        try {
            if(myInfo != null && CommonUtil.getIntFromString(orderId) > 0) {
                Map e = this.getMyOneSingBO("wa_order", "order_id", orderId);
                if(e != null && e.get("supplierId") != null && e.get("supplierId").toString().equals(myInfo.getUserId()) && e.get("orderState") != null) {
                    HashMap param = new HashMap();
                    param.put("tableName", "wa_order");
                    HashMap mapClass = new HashMap();
                    boolean flag = false;
                    String orderState = e.get("orderState").toString();
                    String sql;
                    String queryParam;
                    if(orderState.equals("0")) {
                        mapClass.put("order_state", "1");
                        sql = CommonUtil.GetRequestParam(request, "saleNum", "0");
                        queryParam = CommonUtil.GetRequestParam(request, "saleDj", "0");
                        if(CommonUtil.getDoubleFromString(sql) > 0.0D && CommonUtil.getDoubleFromString(queryParam) > 0.0D) {
                            mapClass.put("sale_num", sql);
                            mapClass.put("sale_money", Double.valueOf(CommonUtil.getDoubleFromString(sql) * CommonUtil.getDoubleFromString(queryParam)));
                            flag = true;
                        } else {
                            flag = false;
                        }
                    } else if(orderState.equals("1")) {
                        sql = CommonUtil.GetRequestParam(request, "saleNum", "0");
                        queryParam = CommonUtil.GetRequestParam(request, "saleDj", "0");
                        if(CommonUtil.getDoubleFromString(sql) > 0.0D && CommonUtil.getDoubleFromString(queryParam) > 0.0D) {
                            mapClass.put("sale_num", sql);
                            mapClass.put("sale_money", Double.valueOf(CommonUtil.getDoubleFromString(sql) * CommonUtil.getDoubleFromString(queryParam)));
                            flag = true;
                        } else {
                            flag = false;
                        }
                    } else if(orderState.equals("3")) {
                        flag = true;
                    }

                    if(flag) {
                        param.put("mapClass", mapClass);
                        param.put("keyName", "order_id");
                        param.put("keyValue", orderId);
                        this.commonMapper.updateSingleBO(param);
                        sql = "";
                        HashMap queryParam1 = new HashMap();
                        if(orderState.equals("0")) {
                            sql = "CALL orderQrAfterProcess(\'" + orderId + "\',\'" + userId + "\',\'" + ipAddr + "\')";
                            queryParam1.put("strsql", sql);
                            this.commonMapper.simpleUpdate(queryParam1);
                        }

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

    @RequestMapping(
            value = {"front/seller/SellerOrderController-setOrderState"},
            method = {RequestMethod.POST}
    )
    public void setOrderState(HttpServletRequest request, HttpServletResponse response, Model model) {
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        String orderId = CommonUtil.GetRequestParam(request, "orderId", "0");

        try {
            HttpSession e = request.getSession();
            MyInfo myInfo = (MyInfo)e.getAttribute("MY_INFO");
            if(myInfo != null && CommonUtil.getIntFromString(orderId) > 0) {
                Map orderObj = this.getMyOneSingBO("wa_order", "order_id", orderId);
                if(orderObj != null && orderObj.get("supplierId") != null && orderObj.get("supplierId").toString().equals(myInfo.getUserId()) && orderObj.get("orderState") != null) {
                    HashMap param = new HashMap();
                    param.put("tableName", "wa_order");
                    HashMap mapClass = new HashMap();
                    if(orderObj.get("orderState").toString().equals("2")) {
                        mapClass.put("order_state", "3");
                    }

                    param.put("mapClass", mapClass);
                    param.put("keyName", "order_id");
                    param.put("keyValue", orderId);
                    this.commonMapper.updateSingleBO(param);
                    ret = "1";
                    obj.put("content", ConstantUtil.MSG_SUCCESS);
                }
            }
        } catch (Exception var12) {
            ret = "-2";
            obj.put("content", ConstantUtil.MSG_FAILS);
        }

        obj.put("status", ret);
        this.echoJSON(response, obj);
    }

    @RequestMapping(
            value = {"front/seller/SellerOrderController-cancelOrder"},
            method = {RequestMethod.POST}
    )
    public void cancelOrder(HttpServletRequest request, HttpServletResponse response, Model model) {
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        String orderId = CommonUtil.GetRequestParam(request, "orderId", "0");

        try {
            HttpSession e = request.getSession();
            MyInfo myInfo = (MyInfo)e.getAttribute("MY_INFO");
            if(myInfo != null && CommonUtil.getIntFromString(orderId) > 0) {
                Map orderObj = this.getMyOneSingBO("wa_order", "order_id", orderId);
                if(orderObj != null && orderObj.get("supplierId") != null && orderObj.get("supplierId").toString().equals(myInfo.getUserId())) {
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
        } catch (Exception var12) {
            ret = "-2";
            obj.put("content", ConstantUtil.MSG_FAILS);
        }

        obj.put("status", ret);
        this.echoJSON(response, obj);
    }

    @RequestMapping(
            value = {"front/seller/SellerOrderController-setVehicle"},
            method = {RequestMethod.POST}
    )
    public void setVehicle(HttpServletRequest request, HttpServletResponse response, Model model) {
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        String vehicleId = CommonUtil.GetRequestParam(request, "vehicleId", "0");
        String vehicleSjSize = CommonUtil.GetRequestParam(request, "vehicleSjSize", "0");
        String zorderMoney = CommonUtil.GetRequestParam(request, "zorderMoney", "0");
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        String sql = "";
        sql = " CALL gysFhQuerenProcess(\'" + vehicleId + "\', \'" + vehicleSjSize + "\',\'" + zorderMoney + "\', \'" + userId + "\')";
        HashMap queryParam = new HashMap();
        queryParam.put("strsql", sql);

        try {
            if(myInfo != null && CommonUtil.getIntFromString(vehicleId) > 0 && CommonUtil.getDoubleFromString(vehicleSjSize) > 0.0D && CommonUtil.getDoubleFromString(zorderMoney) > 0.0D) {
                this.commonMapper.simpleSelectReturnList(queryParam);
                ret = "1";
                obj.put("content", ConstantUtil.MSG_SUCCESS);
            }
        } catch (Exception var14) {
            ret = "-2";
            obj.put("content", ConstantUtil.MSG_FAILS);
        }

        obj.put("status", ret);
        this.echoJSON(response, obj);
    }

    @RequestMapping(
            value = {"front/seller/SellerOrderController-setAllVehicle"},
            method = {RequestMethod.POST}
    )
    public void setAllVehicle(HttpServletRequest request, HttpServletResponse response, Model model) {
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        String vehicleData = CommonUtil.GetRequestParam(request, "vehicleData", "");
        ArrayList vehicleList = ConvertUtil.converJSONtoArrayListMap(vehicleData);
        MyInfo myInfo = this.getMyInfo(request);
        String userId = myInfo.getUserId();
        if(vehicleList.size() > 0) {
            for(int ii = 0; ii < vehicleList.size(); ++ii) {
                String vehicleId = ((Map)vehicleList.get(ii)).get("vehicleId") != null?((Map)vehicleList.get(ii)).get("vehicleId").toString():"0";
                String vehicleSjSize = ((Map)vehicleList.get(ii)).get("vehicleSjSize") != null?((Map)vehicleList.get(ii)).get("vehicleSjSize").toString():"0";
                String zorderMoney = ((Map)vehicleList.get(ii)).get("zorderMoney") != null?((Map)vehicleList.get(ii)).get("zorderMoney").toString():"0";
                String sql = "";
                sql = " CALL gysFhQuerenProcess(\'" + vehicleId + "\', \'" + vehicleSjSize + "\',\'" + zorderMoney + "\', \'" + userId + "\')";
                HashMap queryParam = new HashMap();
                queryParam.put("strsql", sql);

                try {
                    if(myInfo != null && CommonUtil.getIntFromString(vehicleId) > 0 && CommonUtil.getDoubleFromString(vehicleSjSize) > 0.0D && CommonUtil.getDoubleFromString(zorderMoney) > 0.0D) {
                        this.commonMapper.simpleSelectReturnList(queryParam);
                        ret = "1";
                        obj.put("content", ConstantUtil.MSG_SUCCESS);
                    }
                } catch (Exception var17) {
                    ret = "-2";
                    obj.put("content", ConstantUtil.MSG_FAILS);
                }
            }
        }

        obj.put("status", ret);
        this.echoJSON(response, obj);
    }

    @RequestMapping({"front/seller/SellerOrderController-userOrderList"})
    public String userOrderList(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        Map param = this.getConditionParam(request);
        param.put("userId", myInfo.getUserId());
        ArrayList userOrderList = this.sellerOrderMapper.getUserOrderList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(userOrderList);
        if(userOrderList != null && userOrderList.size() > 0) {
            Iterator totalCount = userOrderList.iterator();

            while(totalCount.hasNext()) {
                Map ele = (Map)totalCount.next();
                if(ele.get("zcRegionId") != null) {
                    try {
                        ele.put("zcRegionName", this.getRegionDetailName(ele.get("zcRegionId").toString()));
                    } catch (Exception var10) {
                        ;
                    }
                }
            }
        }

        model.addAttribute("userOrderList", userOrderList);
        int totalCount1 = this.sellerOrderMapper.getUserOrderListCount(param);
        this.setPagenationInfo(request, totalCount1, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "4");
        model.addAttribute("userName", myInfo.getUserName());
        return "front/seller/order/userOrderList";
    }

    @RequestMapping({"front/seller/SellerOrderController-userProdList"})
    public String userProdList(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        Map param = this.getConditionParam(request);
        param.put("gysId", myInfo.getUserId());
        ArrayList list = this.userRelationMapper.getUserList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.userRelationMapper.getUserListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        String userInStr = "";

        String spMap;
        for(Iterator param1 = list.iterator(); param1.hasNext(); userInStr = userInStr + ("".equals(userInStr)?"\'" + spMap + "\'":",\'" + spMap + "\'")) {
            Map spList = (Map)param1.next();
            spMap = spList.get("userId").toString();
        }

        HashMap param11 = new HashMap();
        if(!userInStr.equals("")) {
            param11.put("userInStr", userInStr);
            ArrayList spList1 = this.prodUserMapper.getUserProdList(param11);
            ConvertUtil.convertDataBaseMapToJavaMap(spList1);
            Map spMap1 = ConvertUtil.groupListInMap(spList1, "userId");
            Iterator var12 = list.iterator();

            while(var12.hasNext()) {
                Map ele = (Map)var12.next();
                if(((Map)spMap1).get(ele.get("userId").toString()) != null) {
                    ele.put("spList", ((Map)spMap1).get(ele.get("userId").toString()));
                }
            }
        }

        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "6");
        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("list", list);
        return "front/seller/order/userProdList";
    }

    @RequestMapping({"front/seller/SellerOrderController-addProdList"})
    public String addProdList(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map param = this.getConditionParam(request);
        ArrayList list = this.prodMapper.getAddUserProdList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount = this.prodMapper.getAddUserProdListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("list", list);
        return "front/seller/order/dlgAddProdList";
    }

    @RequestMapping({"front/seller/SellerOrderController-delUserProd"})
    public void delUserProd(HttpServletRequest request, HttpServletResponse response, Model model) {
        JSONObject res = new JSONObject();

        try {
            Map e = this.getBoClass(request, "wa_prod_user");
            this.commonMapper.deleteSingleBO(e);
            res.put("state", "1");
            res.put("content", ConstantUtil.MSG_SUCCESS);
        } catch (Exception var6) {
            var6.printStackTrace();
            res.put("state", "2");
            res.put("content", ConstantUtil.MSG_FAILS);
        }

        this.echo(response, res);
    }

    @RequestMapping({"front/seller/SellerOrderController-addUserProd"})
    public void addUserProd(HttpServletRequest request, HttpServletResponse response, Model model) {
        JSONObject res = new JSONObject();

        try {
            Map e = this.getBoClass(request, "wa_prod_user");
            Date glDate = new Date();
            Map mapClass = (Map)e.get("mapClass");
            mapClass.put("gl_date", glDate);
            this.commonMapper.insertSingleBO(e);
            res.put("state", "1");
            res.put("content", ConstantUtil.MSG_SUCCESS);
        } catch (Exception var8) {
            var8.printStackTrace();
            res.put("state", "2");
            res.put("content", ConstantUtil.MSG_FAILS);
        }

        this.echo(response, res);
    }

    @RequestMapping({"front/seller/SellerOrderController-estiList"})
    public String esti_list(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        Map param = this.getConditionParam(request);
        param.put("userId", myInfo.getUserId());
        ArrayList estiList = this.sellerOrderMapper.getEstiList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(estiList);
        model.addAttribute("estiList", estiList);
        int totalCount = this.sellerOrderMapper.getEstiListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "5");
        model.addAttribute("userName", myInfo.getUserName());
        return "front/seller/order/estiList";
    }

    @RequestMapping({"front/seller/SellerOrderController-cancel"})
    public String cancel(HttpServletRequest request, HttpServletResponse response) {
        return "front/seller/order/cancel";
    }

    @RequestMapping(
            value = {"front/seller/SellerOrderController-getRegionList"},
            method = {RequestMethod.POST}
    )
    public void getRegionList(HttpServletRequest request, HttpServletResponse response, Model model) {
        JSONObject obj = new JSONObject();
        String pid = CommonUtil.GetRequestParam(request, "pid", "0");
        Map param = this.getConditionParam(request);
        param.put("parentRegionId", pid);
        ArrayList regionList = this.houtaiuserSellerMapper.getRegionList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(regionList);
        obj.put("regionList", regionList);
        this.echoJSON(response, obj);
    }
}