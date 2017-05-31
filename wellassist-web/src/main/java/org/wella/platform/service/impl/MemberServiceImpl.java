package org.wella.platform.service.impl;

import io.wellassist.utils.Constant;
import io.wellassist.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.*;
import org.wella.entity.LoanInfo;
import org.wella.entity.Prod;
import org.wella.entity.User;
import org.wella.platform.service.MemberService;
import org.wella.utils.MailUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/18.
 */
@Service("menberServiceImpl")
public class MemberServiceImpl implements MemberService{

    @Autowired
    private WaUserDao waUserDao;
    @Autowired
    private UserinfoDao userinfoDao;
    @Autowired
    private ProdDao prodDao;
    @Autowired
    private ProdUserDao prodUserDao;
    @Autowired
    private RegionDao regionDao;


    @Override
    public Map<String, Object> findUserInfoById(long id) {
        Map res=userinfoDao.findUserInfoById(id);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        String zcRegionId=(String)res.get("zcRegionId");
        String zcXxAddress=(String)res.get("zcXxAddress");
        HashMap query=new HashMap();
        query.put("regionId",zcRegionId);
        String address=regionDao.getRegionDetailName(query)+" "+zcXxAddress;
        res.put("address",address);
        return res;
    }

    @Override
    public int createUser(Map map) {
        return 0;
    }


    @Override
    public int updateUserInfo(Map map) {
        int i=waUserDao.updateUserByUserId(map);
        int j=userinfoDao.updateUserinfoByUserId(map);
        return i+j;
    }

    @Override
    public int deleteUser(long userId) {
        int i=waUserDao.deleteUser(userId);
        int j=userinfoDao.deleteByUserId(userId);
        return i+j;
    }

    @Override
    public int total(Map map) {
        return waUserDao.findUserTotal(map);
    }

    @Override
    public void approve(Map map) {
        String comment=(String)map.get("comment");
        String email=(String)map.get("userEmail");
        Map updateMap=new HashMap();
        updateMap.put("userId",map.get("userId"));
        updateMap.put("comment",comment);
        updateMap.put("userState",1);
        waUserDao.updateUserByUserId(updateMap);
        String content="<html><head></head><body><h1>您的维助供应链平台账户已通过审核</h1><h1>点击进入<a href='"+ ConstantUtil.SERVER_HOST+"'  target = '_blank'>维助供应链</a></h1></body></html>";
        new Thread(new MailUtil(email, content)).start();
    }

    @Override
    public void notAprove(Map map) {
        String comment=(String)map.get("comment");
        String email=(String)map.get("userEmail");
        Map updateMap=new HashMap();
        updateMap.put("userId",map.get("userId"));
        updateMap.put("comment",comment);
        updateMap.put("userState",-1);
        waUserDao.updateUserByUserId(updateMap);
        String content="<html><head></head><body><h1>对不起，您的维助供应链平台账户未通过审核</h1><h1>审核意见："+comment+"</h1><h1>点击进入<a href='"+ConstantUtil.SERVER_HOST+"'  target = '_blank'>维助供应链</a></h1></body></html>";
        new Thread(new MailUtil(email, content)).start();
    }

    /**
     * 将用户登录密码与操作密码重置为123456
     * @param userId
     */
    public void resetPassword(long userId){
        String password = CommonUtil.MD5("123456");
        Map map = new HashMap();
        map.put("czPass",password);
        map.put("userPass",password);
        map.put("userId",userId);
        waUserDao.resetPassword(map);
    }
    @Override
    public List<Map<String, Object>> findSellerInfo(Map map) {
        map.put("userType",0);
        List<Map<String,Object>> list = waUserDao.findPlatformUserInfo(map);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        return list;
    }

    @Override
    public List<Map<String,Object>> findProductsByUserId(Map params) {
        List<Map<String,Object>> prods= prodDao.findProdByConditions(params);
        ConvertUtil.convertDataBaseMapToJavaMap(prods);
        for(Map pmap : prods){
            long regionId=(long)pmap.get("prodRegionId");
            String address=regionDao.getRegionDetailNameByRegionId(regionId)+" "+pmap.get("prodRegionAddr");
            pmap.put("address",address);
        }
        return prods;
    }

    public int findProductsByUserIdCount(Map params){
        return prodDao.findProdByConditionsCount(params);
    }


    @Override
    public List<User> findCustomersByUserId(long userId) {
        return prodUserDao.findCustomersBySupplierId(userId);
    }

    @Override
    public int publishProduct(Map map) {
        return prodDao.createProd(map);
    }

    @Override
    public int editProduct(Map map) {
        return prodDao.updateProdByPrimaryKey(map);
    }

    @Override
    public int deleteProduct(long id) {
        return prodDao.deleteProdByPrimaryKey(id);
    }

    @Override
    public List<Map<String,Object>> findProducts(Map map) {
        return prodDao.findProdByConditions(map);
    }

    @Override
    public List<Map<String, Object>> findCustomersInfo(Map map) {
        map.put("userType",1);
        List<Map<String, Object>> customersList=waUserDao.findPlatformCustomerUsers(map);
        ConvertUtil.convertDataBaseMapToJavaMap(customersList);
        return customersList;
    }

    @Override
    public int findCustomersInfoCount(Map map) {
        map.put("userType",1);
        return waUserDao.findPlatformCustomerUsersCount(map);
    }

    @Override
    public List<Map<String, Object>> findAccountList(Map map) {
        return null;
    }

    @Override
    public List<Map<String, Object>> findCreditList(Map map) {
        return null;
    }

    @Override
    public List<Map<String, Object>> findSendsList(Map map) {
        map.put("userType",3);
        List<Map<String,Object>> list = waUserDao.findPlatformUserInfo(map);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        return waUserDao.findPlatformUserInfo(map);
    }

    @Override
    public List<Map<String, Object>> findLogisticRecord(Map map) {
        return null;
    }

    @Override
    public List<Map<String, Object>> findGrapOrderRecord(Map map) {
        return null;
    }
}
