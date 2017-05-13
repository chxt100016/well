package org.wella.dao;

import org.wella.entity.LogisticsInfo;

/**
 * Created by liuwen on 2017/5/12.
 */
public interface LogisticsInfoDao {
    /**
     * 生成物流订单，当卖家确认，并且买家选择配送时的逻辑处理
     * @param logisticsInfo
     */
    void createLogisticsInfo(LogisticsInfo logisticsInfo);
}
