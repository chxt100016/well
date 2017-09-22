package org.wella.controller;


import io.wellassist.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.wella.entity.Bill;
import org.wella.service.BillService;
import java.util.List;
import java.util.Map;

/**
 * Created by ailing on 2017/7/25.
 */

@Controller
@RequestMapping(value = {"/bill/"})
public class BillController {

    Logger logger = LoggerFactory.getLogger(BillController.class);

    @Autowired
    private BillService billServiceImpl;

    /**
     * 开具发票的商品订单信息
     * @param billId wa_bill主键
     * @return 开具发票的商品订单信息
     */
    @RequestMapping(value = "billOrders",method = RequestMethod.GET)
    @ResponseBody
    public R billOrders(@RequestParam("billId") long billId){
        List<Map<String,Object>> orders=billServiceImpl.billOrders(billId);
        return R.ok().put("items",orders);
    }

    /**
     * 开具发票物流订单信息
     * @param billId wa_bill主键
     * @return 开具发票物流订单信息
     */
    @RequestMapping(value = "billLogisticss",method = RequestMethod.GET)
    @ResponseBody
    public R billLogisticss(@RequestParam("billId")long billId){
        List<Map<String,Object>> list=billServiceImpl.billLogisticss(billId);
        return R.ok().put("items",list);
    }

    /**
     * 发票基本信息
     * @param billId
     * @return
     */
    @RequestMapping(value = "bill",method =RequestMethod.GET)
    @ResponseBody
    public R bill(@RequestParam("billId") long billId){
        Bill bill=billServiceImpl.bill(billId);
        return R.ok().put("bill",bill);
    }
}
