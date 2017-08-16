package org.wella.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wella.common.ctrl.BaseController;
import org.wella.dao.WaUserDao;
import org.wella.platform.service.impl.MemberServiceImpl;
import org.wella.platform.service.impl.ProductManageServiceImpl;
import org.wella.service.RegionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/18.
 * 平台页面跳转控制
 */
@Controller
@RequestMapping("/platform/")
public class PageController extends BaseController {
    @Autowired
    private WaUserDao waUserDao;

    @Autowired
    private ProductManageServiceImpl productManageService;

    @Autowired
    private MemberServiceImpl memberService;

    @Autowired
    private RegionService regionServiceImpl;
    /**
     * 控制用户管理界面跳转（卖家，买家，物流方，放款方）
     * @param url
     * @return
     */
    @RequestMapping("user/{url}.html")
    public String userPage(@PathVariable("url") String url){
        return "views/platform/user/" + url + ".html";
    }

    /**
     * 控制用户管理界面跳转（卖家，买家，物流方，放款方）
     * @param url
     * @return
     */
    @RequestMapping("trade/{url}.html")
    public String tradePage(@PathVariable("url") String url){
        return "views/platform/trade/" + url + ".html";
    }
    /**
     * 买卖家管理相关的页面跳转
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping("user/customer/customerInfo/{userId}")
    public String customerInfoPage(@PathVariable("userId")String userId,Model model){
        model.addAttribute("userId",userId);
        return "views/platform/user/customer/customerInfo.html";
    }
    @RequestMapping("user/seller/sellerInfo/{userId}")
    public String sellerInfoPage(@PathVariable("userId")String userId,Model model){
        model.addAttribute("userId",userId);
        return "views/platform/user/seller/sellerInfo.html";
    }
    @RequestMapping("user/sender/senderInfo/{userId}")
    public String senderInfoPage(@PathVariable("userId")String userId,Model model){
        model.addAttribute("userId",userId);
        return "views/platform/user/sender/senderInfo.html";
    }
    @RequestMapping("user/creditor/creditorInfo/{userId}")
    public String creditorInfoPage(@PathVariable("userId")String userId,Model model){
        model.addAttribute("userId",userId);
        return "views/platform/user/creditor/creditorInfo.html";
    }

    @RequestMapping("user/creditor/creditorAuthCheck")
    public String creditorAuthCheck(@RequestParam("userId")String userId,Model model){
        model.addAttribute("userId",userId);
        return "views/platform/user/creditor/creditorAuthCheck.html";
    }

    @RequestMapping("user/customer/accountDetail/{userId}")
    public String accountDetailPage(@PathVariable("userId")String userId,Model model){
        model.addAttribute("userId",userId);
        return "views/platform/user/customer/accountDetail.html";
    }
    @RequestMapping("user/seller/productList/{userId}")
    public String productListPage(@PathVariable("userId")String userId,Model model){
        model.addAttribute("userId",userId);
        return "views/platform/user/seller/productList.html";
    }

    @RequestMapping("user/customer/credit/{userId}")
    public String creditPage(@PathVariable("userId")String userId,Model model){
        model.addAttribute("userId",userId);
        return "views/platform/user/customer/credit.html";
    }

    @RequestMapping("user/customer/customerBill/{userId}")
    public String billPage(@PathVariable("userId")String userId,Model model) {
        model.addAttribute("userId", userId);
        return "views/platform/user/customer/bill.html";
    }
    @RequestMapping("user/seller/sellerBill/{userId}")
    public String sellerBillPage(@PathVariable("userId")String userId,Model model) {
        model.addAttribute("userId", userId);
        return "views/platform/user/seller/bill.html";
    }

    @RequestMapping("user/seller/publish.html")
    public String publishProdPage(Model model,@RequestParam("userId")String userId){
        model.addAttribute("userId",userId);
        Map queryRegion=new HashMap();
        queryRegion.put("regionId",0);
        model.addAttribute("provinceList",regionServiceImpl.getChildRegionList(queryRegion));
        return "views/platform/user/seller/publish.html";
    }

    @RequestMapping("user/seller/editProduct")
    public String sellerEditProdPage(Model model,@RequestParam("prodId")String prodId){
        Long prodIdd = Long.parseLong(prodId.toString());
        Map prod = productManageService.viewProductInfo(prodIdd);
        model.addAttribute("prod",prod);
        Map queryRegion=new HashMap();
        queryRegion.put("regionId",0);
        model.addAttribute("provinceList",regionServiceImpl.getChildRegionList(queryRegion));
        return "views/platform/user/seller/edit.html";
    }
    /**
     * 产品管理相关的页面跳转
     * @param model
     * @return
     */
    @RequestMapping("product/publish.html")
    public String productPage(Model model){
        Map map = new HashMap();
        map.put("userType",0);
        List arrayList =  waUserDao.findUser(map);
        model.addAttribute("sellerList", arrayList);
        model.addAttribute("provinceList", this.getChildRegionList(0));
        return "views/platform/product/publish.html" ;
    }


    @RequestMapping("product/list.html")
    public String listPage(Model model){
        return "views/platform/product/list.html" ;
    }
    @RequestMapping("user/customerInfo")
    public String customerInfo(@RequestParam Map<String,Object> requestMap, Model model){
        String id = requestMap.get("id").toString();
        model.addAttribute("testname","wellassist");
        return "views/platform/user/customer/customerInfo.html";
    }
    @RequestMapping("product/edit")
    public String editProduct(@RequestParam Map<String,Object> requestMap, Model model){
        Long prodId = Long.parseLong(requestMap.get("prodId").toString());
        Map prod = productManageService.viewProductInfo(prodId);
        Map map = new HashMap();
        map.put("userType",0);
        List sellerList = waUserDao.findUser(map);
        model.addAttribute("prod",prod);
        model.addAttribute("sellerList",sellerList);
        model.addAttribute("provinceList", this.getChildRegionList(0));
        return "views/platform/product/edit.html";
    }



    @RequestMapping("user/customerInsert")
    public String customerInsert(Model model){
        return "views/platform/user/customer/customerInsert.html";
    }



    @RequestMapping("user/sellerInsert")
    public String sellerInsert(Model model){
        return  "views/platform/user/seller/sellerInsert.html";
    }


    @RequestMapping("user/creditorInsert")
    public String creditorInsert(Model model){
        return  "views/platform/user/creditor/creditorInsert.html";
    }


    @RequestMapping("user/customer")
    public String returnCustomer(Model model){
        return "views/platform/user/customer.html";
    }



    @RequestMapping("user/seller")
    public String returnSeller(Model model){
        return "views/platform/user/seller.html";
    }



    @RequestMapping("user/creditor")
    public String returnCreditor(Model model){
        return "views/platform/user/creditor.html";
    }





}
