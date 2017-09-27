package org.wella.dao;

import org.wella.entity.OrderLogisticsInfo;

import java.util.Map;

/**
 * Created by ailing on 2017/5/11.
 */
public interface OrderLogisticsInfoDao {

    void createOrderLogisticsInfoDao(OrderLogisticsInfo orderLogisticsInfo);

    /**
     * single query
     * @param queryOLI query param
     * @return single pojo
     */
    Map<String,Object> singleOLIByConditions(Map queryOLI);
}
