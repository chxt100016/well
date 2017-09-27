package org.wella.dao;

import io.wellassist.utils.Query;
import org.apache.ibatis.annotations.Param;
import org.wella.entity.Userinfo;

import java.util.List;
import java.util.Map;

/**
 * Created by ailing on 2017/5/10.
 */
public interface UserinfoDao {

    /**
     * 买家生成订单过程中需要的公司信息
     * @param userId
     * @return
     */
    Userinfo getOrderUserinfoByUserid(@Param("userId")long userId);

    Userinfo findUserinfoByUserid(@Param("userId")long userId);

    Integer createWaUserInfo(Map map);

    Map<String,Object> findUserInfoById(@Param("userId")long id);

    /**
     * update
     * @param map update param
     * @return react lines count
     */
    int updateUserinfoByUserId(Map map);

    int deleteByUserId(@Param("userId")long userId);

    Map<String,Object> singleUserinfoByPrimaryKey(@Param("userId")long userId);

    List<Map<String,Object>> SelectCompanyList(Query query);


    Integer SelectCompanyCount(Query query);

    Integer insertWaUserInfo(Map  map);

}
