package org.wella.service.impl;

import io.wellassist.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.*;
import org.wella.entity.CreditorAuthenticInfo;
import org.wella.service.CreditorService;
import org.wella.service.UserinfoService;
import org.wella.service.WaOrderService;
import org.wella.utils.DateUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/21.
 */

@Service("userinfoServiceImpl")
public class UserinfoServiceImpl implements UserinfoService{

    @Autowired
    private WaUserDao waUserDao;

    @Autowired
    private UserinfoDao userinfoDao;

    @Override
    @Transactional
    public void updateCompanyInfo(Map params) {
        Map<String,Object> update=new HashMap<>();
        long userId=(long)params.get("userId");
        update.put("userId",userId);
        update.put("userEmail",params.get("userEmail"));
        update.put("userSeatPhone",params.get("userSeatPhone"));
        update.put("legalIdCard",params.get("legalIdCard"));
        waUserDao.updateUserByUserId(update);

        update.clear();
        update.put("userId",userId);
        update.put("companyImg",params.get("companyImg"));
        update.put("companyYyZzImg",params.get("companyYyZzImg"));
        update.put("companyXkzImg",params.get("companyXkzImg"));
        update.put("companyTxkzImg",params.get("companyTxkzImg"));
        update.put("zcRegionId",params.get("zcRegionId"));
        update.put("zcXxAddress",params.get("zcXxAddress"));
        update.put("companyLxr",params.get("companyLxr"));
        update.put("companyLxrPhone",params.get("companyLxrPhone"));
        update.put("companyLxrEmail",params.get("companyLxrEmail"));
        userinfoDao.updateUserinfoByUserId(update);
    }

    @Override
    public boolean checkOrgPass(long userId, String oldPass, int type) {
        Map<String,Object> user=waUserDao.singleUserByPrimaryKey(userId);
        String userPass=(String)user.get("user_pass");
        String czPass=(String)user.get("cz_pass");
        if (type==0){
            if(CommonUtil.MD5(oldPass).toString().equals(userPass)){
                return true;
            }
        }else if (type==1){
            if(CommonUtil.MD5(oldPass).toString().equals(czPass)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void updatePayNewPass(long userId, String payNewpass) {
        Map<String,Object> update=new HashMap<>();
        update.put("userId",userId);
        update.put("czPass",CommonUtil.MD5(payNewpass));
        waUserDao.updateUserByUserId(update);
    }

    @Override
    public void updateLoginNewPass(long userId, String loginNewPass) {
        Map<String,Object> update=new HashMap<>();
        update.put("userId",userId);
        update.put("userPass",CommonUtil.MD5(loginNewPass));
        waUserDao.updateUserByUserId(update);
    }
}
