package org.wella.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wella.dao.OrderDao;
import org.wella.dao.WaUserDao;

/**
 * Created by liuwen on 2017/5/18.
 */
@Service("orderServiceImpl")
public class OrderServiceImpl {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private WaUserDao waUserDao;

}
