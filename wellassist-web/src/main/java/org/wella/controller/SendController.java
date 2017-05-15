package org.wella.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.vo.MyInfo;
import org.wella.entity.LogisticsInfo;
import org.wella.service.SenderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by djw on 2017/5/12.
 */
@Controller
@RequestMapping(value = "/sender/")
public class SendController extends BaseController{
    @Autowired
    private SenderService senderServiceImpl;
    /**
     * 跳转抢单大厅
     * @param request 可传入参数size，page
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("test1")
    public void qdList(HttpServletRequest request, HttpServletResponse response, Model model){
        JSONObject res = new JSONObject();
        Map param = this.getConditionParam(request);
        List<LogisticsInfo> logisticsInfos=senderServiceImpl.findLogisticsInfos(param);
        model.addAttribute("logisticsInfos",logisticsInfos);
        int totalCount=senderServiceImpl.findLogisticsInfosCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        res.put("logisticsInfos",logisticsInfos);
        echo(response,res);
    }

    /**
     * 跳转报价界面
     * @param request 需要传入的参数logisticsInfoId
     * @return
     */
    @RequestMapping("test2")
    public void offerLogistics(HttpServletRequest request, HttpServletResponse response, Model model){
        JSONObject res = new JSONObject();
        Map param=new HashMap();
        param.put("logisticsId",request.getParameter("logisticsInfoId"));
        LogisticsInfo logisticsInfo=senderServiceImpl.findLogisticsInfo(param);
        model.addAttribute("logisticsInfo",logisticsInfo);
        res.put("logisticsInfo",logisticsInfo);
        echo(response,res);
    }

    /**
     * 处理物流方抢单
     * [{"sjmc":"丁建文1","sjdh":"13145678923","cph":"scdsgv"},{"sjmc":"丁建文2","sjdh":"13245678965","cph":"vfdbg"}]
     * @param request 传入参数logisticsId物流订单id，wlUserId物流用户id，grabMoney报价，sjLxr车队联系人,sjLxPhone联系人电话，orderData车队信息，
     * @param response
     */
    @RequestMapping("test3")
    public void doOfferLogistics(HttpServletRequest request, HttpServletResponse response){
        MyInfo myInfo = this.getMyInfo(request);
        JSONObject res = new JSONObject();
        Map map = new HashMap();
        try {
            map.put("logisticsId",request.getParameter("logisticsId"));
            map.put("wlUserId",/*myInfo.getUserId()*/request.getParameter("wlUserId"));
            map.put("grabMoney",request.getParameter("grabMoney"));
            map.put("sjLxr",request.getParameter("sjLxr"));
            map.put("sjLxPhone",request.getParameter("sjLxPhone"));
            map.put("orderData",request.getParameter("orderData"));
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
}
