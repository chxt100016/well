package org.wella.dao;

import org.apache.ibatis.annotations.Param;
import org.wella.entity.Userinfo;

/**
 * Created by Administrator on 2017/5/10.
 */
public interface UserinfoDao {
    /**
     * 买家生成订单过程中需要的公司信息
     * @param userId
     * @return
     */
    Userinfo getOrderUserinfoByUserid(@Param("userId")long userId);
}
