package org.wella.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wella.dao.WaUserDao;

/**
 * Created by liuwen on 2017/5/18.
 */
@Service("menberServiceImpl")
public class MenberServiceImpl {

    @Autowired
    private WaUserDao waUserDao;



}
