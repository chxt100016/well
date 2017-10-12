package org.wella.dao;

import org.springframework.stereotype.Repository;
import org.wella.entity.AdminSubAccount;
import org.wella.entity.Bankcard;

import java.util.List;
import java.util.Map;

/**
 * Created by ailing on 2017/6/8.
 */
@Repository
public interface AdminSubAccountDao {

    AdminSubAccount singlePoByConditions(Map<String, Object> query);
}
