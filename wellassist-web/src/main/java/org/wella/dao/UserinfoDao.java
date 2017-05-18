package org.wella.dao;

import org.apache.ibatis.annotations.Param;
import org.wella.entity.Userinfo;

import java.util.Map;

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

    Userinfo findUserinfoByUserid(@Param("userId")long userId);

    void createWaUserInfo(Map map);
}
