package org.wella.dao;

import io.wellassist.utils.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/28.
 */
public interface LogisticsTransDao {

    Map<String,Object> singlePoByConditions(Map param);

    void update(Map map);

    List listLogisticsTransAttachOrderinfoviewByConditions(Map query);

    int listLogisticsTransAttachOrderinfoviewByConditionsCount(Map query);

    Map<String,Object> singleLogisticsTransAttachLogisticsOrderInfoViewByPrimaryKey(@Param("logisticsTransId") long logisticsTransId);

    Map<String,Object> singlePoByPrimaryKey(@Param("logisticsTransId") Long logisticsTransId);
}
