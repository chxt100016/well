package org.wella.dao;

import io.wellassist.utils.Query;
import org.apache.ibatis.annotations.Param;
import org.wella.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/10.
 */
public interface WaUserDao {

    List<User> findUser(Map map);

    //返回插入的条数
    Integer  createUser(Map map);

    HashMap getCountByName(Map map);


    int updateUserByCode(String code);

    //找回密码时，后台生成验证码，并将验证码发到用户邮箱，并将该验证码写入到数据库，提交时根据此验证
    void updateUserByEmail(Map map);

    //登录时根据账号查找相应的成员
    List<User> findUserByAccount(@Param("account") String account);

    int checkAccount(@Param("email") String eamil,@Param("userName") String userName);

    int updateUserByUserId(Map map);

    int deleteUser(@Param("userId")long userId);

    List<Map<String,Object>> findPlatformUserInfo(Map map);

    List<Map<String,Object>> findPlatformCustomerUsers(Map map);

    int findPlatformCustomerUsersCount(Map map);

    /**
     * 获取满足条件的总的数目
     * @param map
     * @return
     */
    int findUserTotal(Map map);

    /**
     * 重置密码
     * @param map 加密后的新密码
     */
    void resetPassword(Map map);

    Map<String,Object> singleUserByPrimaryKey(@Param("userId") long userId);

    List<Map<String,Object>> listUserAttachUserinfoByConditions(Map<String, Object> param);

    List<Map<String,Object>> listUserByConditions(Map query);

    int listUserByConditionsCount(Map<String, Object> map);



    Integer insertUser(Map map);
}
