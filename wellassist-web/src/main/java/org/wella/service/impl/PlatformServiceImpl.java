package org.wella.service.impl;

import org.apache.tools.ant.util.LinkedHashtable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wella.common.utils.CommonUtil;
import org.wella.dao.*;
import org.wella.entity.Order;
import org.wella.entity.Prod;
import org.wella.service.PlatformService;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by liuwen on 2017/5/10.
 */

@Service("platformServiceImpl")
public class PlatformServiceImpl implements PlatformService {


    @Autowired
    private WaUserDao waUserDao;


    @Autowired
    private UserinfoDao userinfoDao;


    @Autowired
    private OrderDao orderDao;


    @Autowired
    private LoanDao loanDao;

    @Autowired
    private UserMoneyDao userMoneyDao;




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


    @Override
    public List<BigDecimal> tradingVolume(Map map) {
        List <Map<Integer,Object>> list=orderDao.tradingVolume(map);
        List<BigDecimal> list1=this.Transformation(list);
        return list1;
    }


    @Override
    public List<BigDecimal> loanAmount(Map map) {
        List<Map<Integer,Object>> list=loanDao.loanAmount(map);;
        return this.Transformation(list);
    }


    @Override
    public List<BigDecimal> turnover(Map map) {
        List<Map<Integer,Object>> list=userMoneyDao.turnover(map);
        return this.Transformation(list);
    }


    @Override
    public List<BigDecimal> interest(Map map) {
        List<Map<Integer,Object>> list=loanDao.interest(map);
        return this.Transformation(list);
    }

    @Override
    public Map<String,List> fundFlow(Map map) {
        List<Map<Integer,Object>> outlist=userMoneyDao.fundFlowOut(map);
        List<Map<Integer,Object>> inlist=userMoneyDao.fundFlowIn(map);
        Map<String,List> maplist = new HashMap<String,List>();
        List<BigDecimal> list=this.Transformation(outlist);
        List<BigDecimal> fuList=new ArrayList<BigDecimal>();
        for(BigDecimal num:list) {
            BigDecimal number=num.multiply(new BigDecimal(-1));
            fuList.add(number);
        }
        maplist.put("out",fuList);
        maplist.put("in",this.Transformation(inlist));
        return maplist;
    }



 public static List<BigDecimal> Transformation(List<Map<Integer,Object>> list){
        List<BigDecimal> list1=new ArrayList<BigDecimal>();
        for(int i=1;i<=12;i++){
            for(int j=0;j<list.size();j++){
                if((int)list.get(j).get("time")==i){
                    list1.add((BigDecimal) list.get(j).get("number"));
                    break;
                }else{
                    if(j==list.size()-1){
                        list1.add(BigDecimal.ZERO);
                    }else{
                        continue;
                    }
                }
            }
        }
        return list1;
    }
}
