package org.wella.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wella.dao.AdminSubAccountDao;
import org.wella.entity.AdminSubAccount;
import org.wella.service.AdminSubAccountService;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by ailing on 2017/7/25.
 */
@Service("adminSubAccountServiceImpl")
public class AdminSubAccountServiceImpl implements AdminSubAccountService   {

    @Autowired
    private AdminSubAccountDao adminSubAccountDao;

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

}
