package org.wella.dao;

import org.apache.ibatis.annotations.Param;
import org.wella.entity.Zorder;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/2.
 */
public interface ZorderDao {

    void createZorder(Zorder zorder);

    int updateByPrimaryKey(Map<String,Object> zorder);

    Map<String,Object> findProdCountByConditions(Map query);

    List<Map<String,Object>> listZordersByConditions(Map query);

    Map<String,Object> singleZorderByPrimaryKey(@Param("zorderId") long zorderId);

    Map<String,Object> singleZorderByConditions(Map params);
}
