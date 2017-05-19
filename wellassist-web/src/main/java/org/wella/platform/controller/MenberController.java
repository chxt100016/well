package org.wella.platform.controller;

import com.alibaba.fastjson.JSONObject;
import io.wellassist.controller.AbstractController;
import io.wellassist.entity.SysUserEntity;
import io.wellassist.utils.PageUtils;
import io.wellassist.utils.Query;
import io.wellassist.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wella.common.ctrl.BaseController;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.WaUserDao;
import org.wella.entity.User;
import org.wella.platform.service.impl.MemberServiceImpl;
import org.wella.platform.service.impl.MemberServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/18.
 */
@RestController
@RequestMapping("/platform/user/")
public class MenberController extends AbstractController{

    @Autowired
    private MemberServiceImpl menberServiceImpl;

    @Autowired
    private WaUserDao waUserDao;

    /**
     * 进入买方管理界面的请求处理
     */
    @RequestMapping("customersList")
    public R customersList(@RequestParam Map<String, Object> params){
        params.put("userType", "3");
        List<User> sellerList = this.waUserDao.findUser(params);
        int totalCount = sellerList.size();

        //查询列表数据
        Query query = new Query(params);
//        List<SysUserEntity> userList = sysUserService.queryList(query);
//        int total = sysUserService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(sellerList, totalCount, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 进入卖方管理界面的请求处理
     */
    @RequestMapping("sellerList")
    public R sellerList(@RequestParam Map<String, Object> params){
        params.put("userType", "3");
        List<User> sellerList = this.waUserDao.findUser(params);
        int totalCount = sellerList.size();
        //查询列表数据
        Query query = new Query(params);

        PageUtils pageUtil = new PageUtils(sellerList, totalCount, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);

    }

    /**
     * 获取卖家详情
     * @param params
     * @return
     */
    @RequestMapping("sellerinfo")
    public R sellerInfo(@RequestParam Map<String,Object> params){

        params.put("userType", "3");
//        List<User> sellerList = this.menberServiceImpl.findSellerInfo(params);
//        int totalCount = sellerList.size();
//
//        //查询列表数据
//        Query query = new Query(params);
//        PageUtils pageUtil = new PageUtils(sellerList, totalCount, query.getLimit(), query.getPage());

//        return R.ok().put("page", pageUtil);
        return  R.ok();
    }

    /**
     * 获取卖家产品列表
     * @param params
     * @return
     */
    @RequestMapping("productList")
    public R productList(@RequestParam Map<String,Object> params){

        params.put("userType", "3");
        List<User> sellerList = this.waUserDao.findUser(params);
        int totalCount = sellerList.size();

        //查询列表数据
        Query query = new Query(params);
//        List<SysUserEntity> userList = sysUserService.queryList(query);
//        int total = sysUserService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(sellerList, totalCount, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 获取卖家的买家列表
     * @param params
     * @return
     */
    @RequestMapping("customerList")
    public R customerList(@RequestParam Map<String,Object> params){

        params.put("userType", "3");
        List<User> sellerList = this.waUserDao.findUser(params);
        int totalCount = sellerList.size();
        //查询列表数据
        Query query = new Query(params);

        PageUtils pageUtil = new PageUtils(sellerList, totalCount, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


}
