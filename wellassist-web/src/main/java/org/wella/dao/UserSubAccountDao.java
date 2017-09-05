package org.wella.dao;

import org.wella.entity.UserSubAccount;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/4.
 */
public interface UserSubAccountDao {

    void create(UserSubAccount userSubAccount);

    UserSubAccount singleQuery(Map<String,Object> param);

}
