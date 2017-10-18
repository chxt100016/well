package org.wella.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wella.dao.AdminSubAccountDao;
import org.wella.dao.UserSubAccountDao;
import org.wella.entity.AdminSubAccount;
import org.wella.entity.UserSubAccount;
import org.wella.service.AdminSubAccountService;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by ailing on 2017/7/25.
 */
@Service("adminSubAccountServiceImpl")
public class AdminSubAccountServiceImpl implements AdminSubAccountService   {

    @Autowired
    private AdminSubAccountDao adminSubAccountDao;

    @Autowired
    private UserSubAccountDao userSubAccountDao;

    @Override
    public AdminSubAccount findOrderTransferAccount() {
        Map<String,Object> query=new HashMap();
        query.put("type",1);
        query.put("status",1);
        AdminSubAccount account=adminSubAccountDao.singlePoByConditions(query);
        return account;
    }

    @Override
    public AdminSubAccount findLoanTransferAccount() {
        Map<String,Object> query=new HashMap();
        query.put("type",2);
        query.put("status",1);
        AdminSubAccount account=adminSubAccountDao.singlePoByConditions(query);
        return account;
    }

    @Override
    public int validateAdminSubAccount(String subAccNo, String subAccNm) {
        Map<String,Object> query=new HashMap<>();
        query.put("subAccNo",subAccNo);
        query.put("subAccNm",subAccNm);
        UserSubAccount userSubAccount=userSubAccountDao.singleQuery(query);
        if (null==userSubAccount){
            return -1;
        }else {
            AdminSubAccount adminSubAccount=adminSubAccountDao.singlePoByConditions(query);
            if (null != adminSubAccount){
                return -2;
            }else {
                return 0;
            }
        }
    }

    @Override
    public int create(AdminSubAccount adminSubAccount) {
        int res=adminSubAccountDao.create(adminSubAccount);
        return res;
    }

    @Override
    public List<AdminSubAccount> findAdminSubAccounts(int type) {
        Map<String,Object> query=new HashMap<>();
        query.put("type",type);
        List<AdminSubAccount> list=adminSubAccountDao.queryList(query);
        return list;
    }

    @Override
    @Transactional
    public int updateOrderTransferAccount(long id) {
        Map<String,Object> query=new HashMap<>();
        query.put("id",id);
        AdminSubAccount adminSubAccount=adminSubAccountDao.singlePoByConditions(query);
        if (null==adminSubAccount || (int)(adminSubAccount.getType())!=1){
            return -1;
        }
        query.clear();
        query.put("type",1);
        query.put("status",2);
        adminSubAccountDao.update(query);
        query.clear();
        query.put("id",id);
        query.put("status",1);
        adminSubAccountDao.update(query);
        return 0;
    }

    @Override
    public int updateLoanTransferAccount(long id) {
        Map<String,Object> query=new HashMap<>();
        query.put("id",id);
        AdminSubAccount adminSubAccount=adminSubAccountDao.singlePoByConditions(query);
        if (null==adminSubAccount || (int)(adminSubAccount.getType())!=2){
            return -1;
        }
        query.clear();
        query.put("type",2);
        query.put("status",2);
        adminSubAccountDao.update(query);
        query.clear();
        query.put("id",id);
        query.put("status",1);
        adminSubAccountDao.update(query);
        return 0;
    }
}
