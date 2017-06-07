package org.wella.front.sender.ctrl;

import com.alibaba.fastjson.JSONObject;

import java.util.*;
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
import org.wella.common.vo.MyInfo;
import org.wella.entity.LogisticsInfo;
import org.wella.front.mapper.FrontUserVehicleMapper;
import org.wella.front.sender.mapper.FrontSenderOrderMapper;
import org.wella.front.sender.mapper.FrontSenderVehicleGrabInfoMapper;
import org.wella.front.sender.mapper.FrontSenderVehicleGrabMapper;
import org.wella.service.SenderService;

@Controller
public class FrontSenderOrderCtrl extends BaseController {
    @Autowired
    private FrontSenderOrderMapper frontSenderOrderMapper;
    @Autowired
    private FrontUserVehicleMapper userVehicleMapper0;
    @Autowired
    private FrontSenderVehicleGrabMapper vehicleGrabMapper;
    @Autowired
    private FrontSenderVehicleGrabInfoMapper vehicleGrabInfoMapper;

    @Autowired
    private SenderService senderServiceImpl;

    public FrontSenderOrderCtrl() {
    }

    @RequestMapping({"/front/sender/FrontSenderOrderCtrl-orderList"})
    public String orderList(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        Map param = this.getConditionParam(request);
        param.put("userId", myInfo.getUserId());
        ArrayList waUserVehicleList = this.frontSenderOrderMapper.getWaUserVehicleList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(waUserVehicleList);
        model.addAttribute("waUserVehicleList", waUserVehicleList);
        int totalCount = this.frontSenderOrderMapper.getWaUserVehicleListCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "1");
        model.addAttribute("userName", myInfo.getUserName());
        return "views/front/sender/order/orderList.jsp";
    }

    @RequestMapping({"/front/sender/FrontSenderOrderCtrl-editFahuo"})
    public String editFahuo(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        String vehicleTrans = CommonUtil.GetRequestParam(request, "vehicleTrans", "0");
        model.addAttribute("vehicleTrans", vehicleTrans);
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "2");
        model.addAttribute("userName", myInfo.getUserName());
        return "views/front/sender/order/editFahuo.jsp";
    }

    @RequestMapping(
            value = {"front/sender/FrontSenderOrderCtrl-setFahuo"},
            method = {RequestMethod.POST}
    )
    public void setFahuo(HttpServletRequest request, HttpServletResponse response, Model model) {
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        String vehicleTrans = CommonUtil.GetRequestParam(request, "vehicleTrans", "");
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
        sql1 = " CALL createZOrderProcess(\'" + vehicleTrans + "\',\'" + cfDate + "\',\'" + ddDate + "\',\'" + cphList + "\',\'" + sjdhList + "\',\'" + sjmcList + "\')";
        HashMap queryParam1 = new HashMap();
        ((Map)queryParam1).put("strsql", sql1);

        try {
            if(CommonUtil.getIntFromString(vehicleTrans) > 0 && !cfDate.equals("") && !ddDate.equals("") && !orderData.equals("")) {
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

    @RequestMapping({"/front/sender/FrontSenderOrderCtrl-detailVehicle"})
    public String detailVehicle(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        String vehicleTrans = CommonUtil.GetRequestParam(request, "vehicleTrans", "0");
        Map vehicleInfo = this.getMyOneSingBO("wa_user_vehicle", "vehicle_trans", vehicleTrans);
        if(vehicleInfo != null && vehicleInfo.get("wlUserId") != null && vehicleInfo.get("wlUserId").toString().equals(myInfo.getUserId()) && vehicleInfo.get("orderId") != null) {
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
        return "views/front/sender/order/detailVehicle.jsp";
    }

    @RequestMapping({"/front/sender/FrontSenderOrderCtrl-qdPage"})
    public String qdPage(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        String logisticsId = CommonUtil.GetRequestParam(request, "logisticsId", "0");
        HashMap param = new HashMap();
        param.put("logisticsId",logisticsId);
        Map<String,Object> info=senderServiceImpl.findLogisticsInfo(param);

        model.addAttribute("info", info);

        model.addAttribute("wlUserId", myInfo.getUserId());
        return "views/front/sender/order/qdPage.jsp";
    }

    @RequestMapping({"/front/sender/FrontSenderOrderCtrl-sqQd"})
    public void sqQd(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        JSONObject res = new JSONObject();
        Map map = new HashMap();
        try {
            map.put("logisticsId",request.getParameter("logisticsId"));
            map.put("wlUserId",myInfo.getUserId());
            map.put("grabMoney",request.getParameter("grabMoney"));
            map.put("sjLxr",request.getParameter("sjLxr"));
            map.put("sjLxPhone",request.getParameter("sjLxPhone"));
            map.put("orderData",CommonUtil.GetRequestParam(request, "orderData", ""));
        }catch (Exception e){
            e.printStackTrace();
            res.put("state", "2");
            res.put("content", ConstantUtil.MSG_FAILS);
            e.printStackTrace();
        }finally {
            try {
                senderServiceImpl.grabLogisticsOrder(map);
                res.put("state", "1");
                res.put("content", ConstantUtil.MSG_SUCCESS);
            }catch (Exception e){
                res.put("state", "2");
                res.put("content", ConstantUtil.MSG_FAILS);
                e.printStackTrace();
            }finally {
                this.echo(response,res);
            }
        }
    }

    /**
     * 查看抢单记录
     * @param request 传入参数：orderNo,grabState,page
     * @param response
     * @param model
     * @return
     */
    @RequestMapping({"/front/sender/FrontSenderOrderCtrl-qdList"})
    public String qdList(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        Map param = this.getConditionParam(request);
        param.put("wlUserId", myInfo.getUserId());
        List list=senderServiceImpl.grabLogisticsList(param);
        ArrayList list0 = ConvertUtil.groupList(list, "userId");
        int totalCount =senderServiceImpl.grabLogisticsListCount(param);
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "2");
        model.addAttribute("userName", myInfo.getUserName());
        model.addAttribute("list", list0);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        return "views/front/sender/order/qdList.jsp";
    }

    @RequestMapping({"/front/sender/FrontSenderOrderCtrl-sqResult"})
    public String sqResult(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "views/front/sender/order/qdResult.jsp";
    }

    @RequestMapping({"/front/sender/FrontSenderOrderCtrl-qdDetail"})
    public String qdDetail(HttpServletRequest request, HttpServletResponse response, Model model) {
        MyInfo myInfo = this.getMyInfo(request);
        Map param = this.getConditionParam(request);
        Map info = this.vehicleGrabMapper.getQdInfo(param);
        ConvertUtil.convertDataBaseMapToJavaMap(info);
        ArrayList infoList = this.vehicleGrabInfoMapper.getGrabInfoList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(infoList);

        try {
            info.put("fromRegionName", this.getRegionDetailName(info.get("fromRegionId").toString()));
        } catch (Exception var10) {
            info.put("fromRegionName", "");
        }

        try {
            info.put("toRegionName", this.getRegionDetailName(info.get("toRegionId").toString()));
        } catch (Exception var9) {
            info.put("toRegionName", "");
        }

        model.addAttribute("info", info);
        model.addAttribute("infoList", infoList);
        model.addAttribute("wlUserId", myInfo.getUserId());
        return "views/front/sender/order/qdDetail.jsp";
    }

    @RequestMapping({"/front/sender/FrontSenderOrderCtrl-updateQd"})
    public void updateQd(HttpServletRequest request, HttpServletResponse response, Model model) {
        JSONObject res = new JSONObject();

        try {
            Map e = this.getBoClass(request, "wa_vehicle_grab");
            this.commonMapper.updateSingleBO(e);
            res.put("state", "1");
            res.put("content", ConstantUtil.MSG_SUCCESS);
        } catch (Exception var6) {
            var6.printStackTrace();
            res.put("state", "2");
            res.put("content", ConstantUtil.MSG_FAILS);
        }

        this.echo(response, res);
    }

    @RequestMapping(
            value = {"front/sender/FrontSenderOrderCtrl-jiesuan"},
            method = {RequestMethod.POST}
    )
    public void jiesuan(HttpServletRequest request, HttpServletResponse response, Model model) {
        String ret = "-1";
        JSONObject obj = new JSONObject();
        obj.put("content", ConstantUtil.MSG_PARAM_ERR);
        String vehicleTrans = CommonUtil.GetRequestParam(request, "vehicleTrans", "");
        MyInfo myInfo = this.getMyInfo(request);
        String sql = "";
        sql = " CALL wlJiesuanProcess(\'" + vehicleTrans + "\')";
        HashMap queryParam = new HashMap();
        queryParam.put("strsql", sql);

        try {
            if(CommonUtil.getIntFromString(vehicleTrans) > 0) {
                Map e = this.getMyOneSingBO("wa_user_vehicle", "vehicle_trans", vehicleTrans);
                if(e != null && e.get("vehicleState") != null && e.get("vehicleState").toString().equals("4") && e.get("wlUserId") != null && e.get("wlUserId").toString().equals(myInfo.getUserId())) {
                    this.commonMapper.simpleSelectReturnList(queryParam);
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

    @RequestMapping({"/front/sender/order/sender_detail"})
    public String sender_detail(HttpServletRequest request, HttpServletResponse response) {
        return "views/front/sender/order/sender_detail.jsp";
    }

    @RequestMapping({"/front/sender/order/sender"})
    public String sender(HttpServletRequest request, HttpServletResponse response) {
        return "views/front/sender/order/sender.jsp";
    }
}
