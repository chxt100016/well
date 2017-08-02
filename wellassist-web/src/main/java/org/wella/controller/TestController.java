package org.wella.controller;

import io.wellassist.utils.PageUtils;
import io.wellassist.utils.Query;
import io.wellassist.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.LoanDao;
import org.wella.platform.service.impl.MemberServiceImpl;
import org.wella.dao.OrderDao;
import org.wella.service.SellerService;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/2.
 */
@Controller
@RequestMapping(value = "/test/")
public class TestController {
    @Autowired
    private SellerService sellerServiceImpl;
    @Autowired
    private MemberServiceImpl memberServiceImpl;
    @Autowired
    private LoanDao loanDao;
    @Autowired
    private OrderDao orderDao;

    @RequestMapping("test1")
    @ResponseBody
    public Object test1(String orderId){
        return sellerServiceImpl.getOrderLogisticsInfo(Long.parseLong(orderId));
    }


    @RequestMapping("testPage")
    @ResponseBody
    public R test1Page(@RequestParam Map<String,Object> params){
        Query query=new Query(params);
        List list=orderDao.listOrderAttachProd(query);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        int totalCount=orderDao.listOrderCount(query);
        PageUtils pageUtils=new PageUtils(list,totalCount,query.getLimit(),query.getPage());
        return R.ok().put("page",pageUtils);
    }
}
