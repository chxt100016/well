package org.wella.platform.controller;

import io.wellassist.utils.PageUtils;
import io.wellassist.utils.Query;
import io.wellassist.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wella.entity.Prod;
import org.wella.entity.User;
import org.wella.platform.service.impl.ProductManageServiceImpl;
import org.wella.service.impl.ProductServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/18.
 * 后台产品管理controller
 */
@Controller
@RequestMapping("/platform/product/")
public class ProductController {
    @Autowired
    private ProductManageServiceImpl productManageServiceImpl;

    @RequestMapping("productlist")
    public R productList(@RequestParam Map<String,Object> params) {
        Query query = new Query(params);
//        List<Prod> list =
//        query.put("u")
//        List<User> sellerList = this.waUserDao.findUser(query);
//        int totalCount = waUserDao.findUserTotal(query);
        //查询列表数据
//        PageUtils pageUtil = new PageUtils(sellerList, totalCount, query.getLimit(), query.getPage());
        return R.ok();
    }

    /**
     * 产品发布时的请求处理
     * @param params
     * @return
     */
    @RequestMapping("publish")
    public R publish(@RequestParam Map<String,Object> params) {
        Query query = new Query(params);
//        query.put("u")
//        List<User> sellerList = this.waUserDao.findUser(query);
//        int totalCount = waUserDao.findUserTotal(query);
        //查询列表数据
//        PageUtils pageUtil = new PageUtils(sellerList, totalCount, query.getLimit(), query.getPage());
        return R.ok();
    }


}
