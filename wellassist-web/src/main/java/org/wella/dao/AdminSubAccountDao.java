package org.wella.dao;

import org.apache.ibatis.annotations.Param;
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

    /**
     * 通过一下方式查询单条记录：
     * 1.type+status
     * 2.subAccNo+subAccNm
     * 3.id
     * @param query
     * @return
     */
    AdminSubAccount singlePoByConditions(Map<String, Object> query);

    /**
     * insert 单挑记录
     * @param adminSubAccount
     * @return 数据库更新条数
     */
    int create(AdminSubAccount adminSubAccount);

    /**
     * list query 可通过一下方式查询：
     * 1.type
     * @param query
     * @return
     */
    List<AdminSubAccount> queryList(Map<String, Object> query);

    /**
     * 可通过一下方式update
     * 1.id
     * 2.type
     * @param param
     * @return
     */
    int update(Map<String, Object> param);

    AdminSubAccount singlePoByPk(@Param("id") long id);
}
