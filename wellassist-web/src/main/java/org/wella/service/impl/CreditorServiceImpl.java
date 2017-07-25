package org.wella.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.WaUserDao;
import org.wella.service.CreditorService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/21.
 */

@Service("creditorServiceImpl")
public class CreditorServiceImpl implements CreditorService{

    @Autowired
    private WaUserDao waUserDao;

    /**
     * 先忽略放款方资质审核，以后改这个接口
     * @return
     */
    @Override
    public List<Map<String, Object>> findCreditorList() {
        Map<String,Object> param=new HashMap();
        param.put("userType",2);
        param.put("userState",1);
        List<Map<String,Object>> creditors=waUserDao.listUserAttachUserinfoByConditions(param);
        ConvertUtil.convertDataBaseMapToJavaMap(creditors);
        return creditors;
    }
}
