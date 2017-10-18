package org.wella.dao;

import org.wella.entity.UserSubAccount;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/4.
 */
public interface UserSubAccountDao {

    void create(UserSubAccount userSubAccount);

    /**
     * 可通过一下方式查询单条记录
     * 1.userId
     * 2.subAccNo
     * 3.subAccNo+subAccNm
     * @param param
     * @return
     */
    UserSubAccount singleQuery(Map<String,Object> param);

}
