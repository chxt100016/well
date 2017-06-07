package org.wella.dao;

import org.wella.entity.OrderLogisticsInfo;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/11.
 */
public interface OrderLogisticsInfoDao {

    void createOrderLogisticsInfoDao(OrderLogisticsInfo orderLogisticsInfo);

    Map<String,Object> singleOLIByConditions(Map queryOLI);
}
