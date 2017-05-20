package org.wella.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.ProdDao;
import org.wella.dao.ProdUserDao;
import org.wella.dao.UserinfoDao;
import org.wella.dao.WaUserDao;
import org.wella.entity.Prod;
import org.wella.entity.User;
import org.wella.platform.service.MemberService;

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


    @Override
    public Map<String, Object> findUserInfoById(long id) {
        Map res=userinfoDao.findUserInfoById(id);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
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

    }

    @Override
    public void notAprove(Map map) {

    }

    @Override
    public List<Map<String, Object>> findSellerInfo(Map map) {
        map.put("userType",0);
        return waUserDao.findPlatformUserInfo(map);
    }

    @Override
    public List<Prod> findProductsByUserId(long userId) {
        HashMap param=new HashMap();
        param.put("userId",userId);
        return prodDao.findProdByConditions(param);
    }

    @Override
    public List<User> findCustomersByUserId(long userId) {
        return prodUserDao.findCustomersBySupplierId(userId);
    }

    @Override
    public int publishProduct(Prod prod) {
        return prodDao.createProd(prod);
    }

    @Override
    public int editProduct(Prod prod) {
        return prodDao.updateProd(prod);
    }

    @Override
    public int deleteProduct(long id) {
        return prodDao.deleteProd(id);
    }

    @Override
    public List<Prod> findProducts(Map map) {
        return prodDao.findProdByConditions(map);
    }

    @Override
    public List<Map<String, Object>> findCustomersInfo(Map map) {
        map.put("userType",1);
        return waUserDao.findPlatformCustomerUsers(map);
    }

    @Override
    public long findCustomersInfoCount(Map map) {
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
