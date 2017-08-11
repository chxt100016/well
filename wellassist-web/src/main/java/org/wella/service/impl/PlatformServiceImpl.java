package org.wella.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wella.common.utils.CommonUtil;
import org.wella.dao.UserinfoDao;
import org.wella.dao.WaUserDao;
import org.wella.entity.Order;
import org.wella.entity.Prod;
import org.wella.service.PlatformService;

import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/10.
 */

@Service("platformServiceImpl")
public class PlatformServiceImpl implements PlatformService {


    @Autowired
    private WaUserDao waUserDao;


    @Autowired
    private UserinfoDao userinfoDao;




    @Override
    public List<Order> findOrderList() {
        return null;
    }

    @Override
    public List<Order> findOrderList(Map map) {
        return null;
    }

    @Override
    public List<Prod> findProdList() {
        return null;
    }

    @Override
    public List<Prod> findProdList(Map map) {
        return null;
    }

    @Override
    public void updateOrder(Order order) {

    }

    @Override
    public void updateProduct(Prod prod) {

    }

    @Override
    public void deleteOrder(Order order) {

    }

    @Override
    public void deleteProduct(Prod prod) {

    }

    @Override
    public void confirmDeal(int orderId) {

    }

    @Override
    public void preprocessLoanApply(int orderId) {

    }

    @Override
    public void processloanApply(int orderId) {

    }


    @Override
    public boolean insertCustomer(Map map) {
        String zcXxAddress=(String)map.get("address");
        String czPass=(String)map.get("czPass");
        map.put("caPass", CommonUtil.MD5(czPass));
        map.put("zcXxAddress",zcXxAddress);
        Integer result=waUserDao.insertUser(map);
        if(result>0){
           Integer result1=userinfoDao.insertWaUserInfo(map);
           if(result1>0){
               return true;
           }
        }
        return false;
    }
}
