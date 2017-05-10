package org.wella.houtai.product.ctrl;

import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.houtai.product.mapper.ProductMapper;

@Controller
public class ProductCtrl extends BaseController {
    @Autowired
    private ProductMapper ProductMapper;

    public ProductCtrl() {
    }

    @RequestMapping({"/ht/Product/ProductCtrl-getProductList"})
    public String getProductList(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map param = this.getConditionParam(request);
        ArrayList productList = this.ProductMapper.getProductList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(productList);
        model.addAttribute("productList", productList);
        int totalCount = this.ProductMapper.getProductListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        return "views/houtai/product/product_list.jsp";
    }

    @RequestMapping({"/ht/Product/ProductCtrl-getProductDetail"})
    public String getProductDetail(HttpServletRequest request, HttpServletResponse response, Model model) {
        String prodId = CommonUtil.GetRequestParam(request, "prodId", "0");
        Map prodInfo = this.getMyOneSingBO("wa_prod", "prod_id", prodId);
        if(prodInfo != null) {
            Map waUserInfo;
            if(prodInfo.get("userId") != null) {
                waUserInfo = this.getMyOneSingBO("wa_user", "user_id", prodInfo.get("userId").toString());
                if(waUserInfo != null) {
                    prodInfo.put("userName", waUserInfo.get("userName") != null?waUserInfo.get("userName").toString():"");
                }
            }

            if(prodInfo.get("createUserId") != null) {
                waUserInfo = this.getMyOneSingBO("wa_user", "user_id", prodInfo.get("createUserId").toString());
                if(waUserInfo != null) {
                    prodInfo.put("createUserName", waUserInfo.get("userName") != null?waUserInfo.get("userName").toString():"");
                }
            }
        }

        model.addAttribute("prodInfo", prodInfo);
        return "views/houtai/product/product_detail.jsp";
    }

    @RequestMapping(
            value = {"/ht/Product/ProductCtrl-setProdState"},
            method = {RequestMethod.POST}
    )
    public void setProdState(HttpServletRequest request, HttpServletResponse response, Model model) {
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        String prodId = CommonUtil.GetRequestParam(request, "prodId", "0");
        String prodState = CommonUtil.GetRequestParam(request, "prodState", "0");
        if(!prodState.equals("2")) {
            prodState = "-2";
        }

        if(CommonUtil.getIntFromString(prodId) > 0) {
            try {
                String e = "CALL setProdStateProcess(\'" + prodId + "\', \'" + prodState + "\')";
                HashMap queryParam = new HashMap();
                queryParam.put("strsql", e);
                this.commonMapper.simpleSelectReturnList(queryParam);
                ret = "1";
                obj.put("content", ConstantUtil.MSG_SUCCESS);
            } catch (Exception var10) {
                ret = "-2";
                obj.put("content", ConstantUtil.MSG_FAILS);
            }
        }

        obj.put("status", ret);
        this.echoJSON(response, obj);
    }
}