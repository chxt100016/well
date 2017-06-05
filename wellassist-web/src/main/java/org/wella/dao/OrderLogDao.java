package org.wella.dao;

import org.apache.ibatis.annotations.Param;
import org.wella.entity.OrderLog;

import java.util.Map;

/**
 * Created by liuwen on 2017/5/12.
 */
public interface OrderLogDao {
    void createOrderLog(OrderLog orderLog);

    Map<String,Object> findNewestOrderLog(@Param("orderId") Long orderId);
}
