package org.wella.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.OrderLogDao;
import org.wella.dao.ZorderDao;
import org.wella.service.OrderService;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/5.
 */
@Service("orderServiceImpl")
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderLogDao orderLogDao;

    @Autowired
    private ZorderDao zorderDao;

    @Override
    public Map<String, Object> findNewestOrderLog(Long orderId) {
        Map<String, Object> res=orderLogDao.findNewestOrderLog(orderId);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        return res;
    }

    @Override
    public Map<String, Object> findDeliverProdCount(Long orderId) {
        return null;
    }

    @Override
    public Map<String, Object> findReceiveProdCount(long orderId) {
        return null;
    }
}
