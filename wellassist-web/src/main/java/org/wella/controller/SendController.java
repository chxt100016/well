package org.wella.controller;

import com.alibaba.fastjson.JSONObject;
import io.wellassist.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.vo.MyInfo;
import org.wella.entity.LogisticsInfo;
import org.wella.entity.User;
import org.wella.service.CustomerService;
import org.wella.service.SellerService;
import org.wella.service.SenderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

    @Autowired
    private SellerService sellerServiceImpl;

    @Autowired
    private CustomerService customerServiceImpl;

    /**
     * 跳转抢单大厅
     * @param request 可传入参数size，page
     * @param response
     * @param model
     * @return
     */
    /*@RequestMapping("test1")
    public void qdList(HttpServletRequest request, HttpServletResponse response, Model model){
        JSONObject res = new JSONObject();
        Map param = this.getConditionParam(request);
        param.put("state",0);
        List<LogisticsInfo> logisticsInfos=senderServiceImpl.findLogisticsInfos(param);
        model.addAttribute("logisticsInfos",logisticsInfos);
        int totalCount=senderServiceImpl.findLogisticsInfosCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        res.put("logisticsInfos",logisticsInfos);
        echo(response,res);
    }*/

    /**
     * 跳转报价界面
     * @param request 需要传入的参数logisticsId
     * @return
     */
    /*@RequestMapping("test2")
    public void offerLogistics(HttpServletRequest request, HttpServletResponse response, Model model){
        JSONObject res = new JSONObject();
        Map param=new HashMap();
        param.put("logisticsId",request.getParameter("logisticsInfoId"));
        Map<String,Object> logisticsInfo=senderServiceImpl.(param);
        model.addAttribute("logisticsInfo",logisticsInfo);
        res.put("logisticsInfo",logisticsInfo);
        echo(response,res);
    }*/

    /**
     * 处理物流方抢单
     * [{"sjmc":"丁建文1","sjdh":"13145678923","cph":"scdsgv"},{"sjmc":"丁建文2","sjdh":"13245678965","cph":"vfdbg"}]
     * @param request 传入参数logisticsId物流订单id，wlUserId物流用户id，grabMoney报价，sjLxr车队联系人,sjLxPhone联系人电话，orderData车队信息，
     * @param response
     */
    /*@RequestMapping("test3")
    public void doOfferLogistics(HttpServletRequest request, HttpServletResponse response){
        MyInfo myInfo = this.getMyInfo(request);
        JSONObject res = new JSONObject();
        Map map = new HashMap();
        try {
            map.put("logisticsId",request.getParameter("logisticsId"));
            map.put("wlUserId",*//*myInfo.getUserId()*//*request.getParameter("wlUserId"));
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
    }*/
    @RequestMapping({"grabLogistics"})
    public String qdPage(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        long userId=user.getUserId();
        String logisticsId = CommonUtil.GetRequestParam(request, "logisticsId", "0");
        Map<String,Object> info=senderServiceImpl.grabLogisticsPageInfo(Long.parseLong(logisticsId));
        model.addAttribute("info", info);
        model.addAttribute("senderUserId", userId);
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "3");
        return "views/front/sender/order/quote.jsp";
    }

    @RequestMapping("orderDetail")
    public String orderDetail(@RequestParam("orderId")String orderId,HttpServletRequest request, Model model){
        User user=(User)request.getSession().getAttribute("user");
        Map<String,Object> orderDetail=customerServiceImpl.getOrderDetailInfo(Long.parseLong(orderId));
        model.addAttribute("info",orderDetail);
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "1");
        return "views/front/sender/order/orderDetail_new.jsp";
    }

    @RequestMapping("vehicleGrabHall")
    public String vehicleGrabHall(HttpServletRequest request,Model model){
        Map param = this.getConditionParam(request);
        HttpSession session=request.getSession();
        User u=(User)session.getAttribute("user");
        param.put("senderUserId",u.getUserId());
        List<Map<String,Object>> logisticsInfos=senderServiceImpl.grabHallInfos(param);
        int totalCount=senderServiceImpl.grabHallInfosCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("info",logisticsInfos);
        model.addAttribute("userName", u.getUserName());
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "3");
        return "views/front/sender/order/vehicleGrabHall.jsp";
    }

    @RequestMapping("grabLogisticsSubmit")
    @ResponseBody
    public R grabLogisticsSubmit(@RequestParam Map param){
        try {
            int res=senderServiceImpl.grabLogistics(param);
            if(res>0){
                return R.ok();
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
        return R.error();
    }


    @RequestMapping({"/logisticsGrabResult"})
    public String sqResult(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "views/front/sender/order/qdResult.jsp";
    }
    /**
     * 查看抢单记录
     * @param request 传入参数：orderNo,grabState,page
     * @param response
     * @param model
     * @return
     */
    @RequestMapping({"/logisticsGrabList"})
    public String qdList(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        Map param = this.getConditionParam(request);
        param.put("wlUserId", user.getUserId());
        List list=senderServiceImpl.grabLogisticsList(param);
        ArrayList list0 = ConvertUtil.groupList(list, "userId");
        int totalCount =senderServiceImpl.grabLogisticsListCount(param);
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "2");
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("list", list0);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        return "views/front/sender/order/qdList.jsp";
    }
    @RequestMapping("logisticsOrderList")
    public String logisticsOrderListPage(HttpServletRequest request,Model model){
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        Map param = this.getConditionParam(request);
        param.put("senderUserId", user.getUserId());
        List<Map<String,Object>> info=senderServiceImpl.logisticsOrderListInfo(param);
        int totalCount=senderServiceImpl.logisticsOrderListInfoCount(param);
        this.setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
        model.addAttribute("info",info);
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("parentMenuNo", "1");
        model.addAttribute("childMenuNo", "1");
        return "views/front/sender/order/orderList.jsp";
    }

}
