package org.wella.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.OrderTransDao;
import org.wella.dao.TradeDAO;
import org.wella.platform.service.TradeService;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/29.
 */
@Service("tradeServiceImpl")
public class TradeServiceImpl implements TradeService{

    @Autowired
    private OrderTransDao orderTransDao;

    @Autowired
    private TradeDAO tradeDao;

    @Override
    public Map<String, Object> findOfflinePayInfo(long orderTransId) {
        Map<String,Object> res=orderTransDao.singleOrderTransAttachOrderinfoviewByPrimaryKey(orderTransId);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        return res;
    }

    @Override
    public int offlinePayCheckSubmit(Map<String, Object> param) {
        return tradeDao.prodOfflinePayCheckProcess(param);
    }
}
