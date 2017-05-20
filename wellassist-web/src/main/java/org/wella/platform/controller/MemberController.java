package org.wella.platform.controller;


import io.wellassist.controller.AbstractController;
import io.wellassist.utils.PageUtils;
import io.wellassist.utils.Query;
import io.wellassist.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wella.dao.WaUserDao;
import org.wella.entity.Prod;
import org.wella.entity.User;
import org.wella.platform.service.impl.MemberServiceImpl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/18.
 */
@RestController
@RequestMapping("/platform/user/")
public class MemberController extends AbstractController{

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
     * 用户信息查看
     * @param
     * @return
     */
    @RequestMapping("userinfo/{userId}")
    public R userInfo(@PathVariable("userId") Long userId){
        Map<String,Object> params = new HashMap();
        params.put("userId",userId);
        Map user = menberServiceImpl.findUserInfoById(userId);
        return  R.ok().put("user",user);
    }

    /**
     * 用户信息查看
     * @param
     * @return
     */
    @RequestMapping("reviewInfo/{userId}")
    public R reviewInfo(@PathVariable("userId") Long userId){
        Map<String,Object> params = new HashMap();
        params.put("userId",userId);
        Map user = menberServiceImpl.findUserInfoById(userId);
        return  R.ok().put("user",user);
    }
    /**
     * 获取卖家产品列表
     *
     * @return
     */
    @RequestMapping("productList/{userId}")
    public R productList(@RequestParam Map<String,Object> params,@PathVariable("userId") Long userId){
        List<Prod> prodList = this.menberServiceImpl.findProductsByUserId(userId);

//        List<User> sellerList = this.waUserDao.findUser(params);
//        int totalCount = sellerList.size();

        //查询列表数据
        Query query = new Query(params);
        PageUtils pageUtil = new PageUtils(prodList, prodList.size(), query.getPage(), query.getLimit());
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

    @RequestMapping("reviewlist")
    public R rewiewList(@RequestParam Map<String,Object> params) {

        params.put("activityState", 1);
        List<User> sellerList = this.waUserDao.findUser(params);
        int totalCount = waUserDao.findUserTotal(params);
        //查询列表数据
        Query query = new Query(params);
        PageUtils pageUtil = new PageUtils(sellerList, totalCount, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 通过注册申请
     * @param params
     * @return
     */
    @RequestMapping("approve")
    public R approve (@RequestParam Map<String,Object> params) {
        menberServiceImpl.approve(params);
        return R.ok();
    }

    /**
     * 未通过通过注册申请
     * @param params
     * @return
     */
    @RequestMapping("notApprove")
    public R notApprove (@RequestParam Map<String,Object> params) {
        menberServiceImpl.notAprove(params);
        return R.ok();
    }
}
