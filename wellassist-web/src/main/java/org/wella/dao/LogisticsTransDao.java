package org.wella.dao;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/28.
 */
public interface LogisticsTransDao {

    Map<String,Object> singlePoByConditions(Map param);

    void update(Map map);

}
