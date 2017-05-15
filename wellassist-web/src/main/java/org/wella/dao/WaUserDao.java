package org.wella.dao;

import org.apache.ibatis.annotations.Param;
import org.wella.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/10.
 */
public interface WaUserDao {

    List<User> findUser(Map map);
}
