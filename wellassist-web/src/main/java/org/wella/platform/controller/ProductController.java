package org.wella.platform.controller;

import io.wellassist.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;
import org.wella.common.ctrl.BaseController;
import org.wella.entity.Prod;
import org.wella.entity.User;
import org.wella.platform.service.impl.ProductManageServiceImpl;
import org.wella.service.impl.ProductServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/18.
 * 后台产品管理controller
 */
@RestController
@RequestMapping("/platform/product/")
public class ProductController extends BaseController{
    @Autowired
    private ProductManageServiceImpl productManageServiceImpl;

    @RequestMapping(value = "productlist",method = RequestMethod.GET)
    public R productList(@RequestParam Map<String,Object> params) {
        Query query = new Query(params);
        List<Map<String,Object>> list =productManageServiceImpl.prodList(params);
        int totalCount = productManageServiceImpl.totalCount(params);
        PageUtils pageUtils = new PageUtils(list,totalCount,query.getLimit(),query.getPage());
        return R.ok().put("page",pageUtils);
    }

    /**
     * 产品发布时的请求处理
     * @param params
     * @return
     */
    @RequestMapping("publishproduct")
    public R publish(@RequestParam Map<String,Object> params) {
        productManageServiceImpl.publish(params);
        return R.ok();
    }

    /**
     * 修改产品
     * @param params
     * @return
     */
    @RequestMapping("updateproduct")
    public R update(@RequestParam Map<String,Object> params){
        productManageServiceImpl.updateProductById(params);
        return R.ok();
    }

    @RequestMapping("delete/{prodId}")
    public R deleteProduct(@PathVariable("prodId")long prodId){
        productManageServiceImpl.remove(prodId);
        return R.ok();
    }
}
