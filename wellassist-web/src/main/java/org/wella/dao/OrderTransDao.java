package org.wella.dao;

import io.wellassist.utils.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/28.
 */
public interface OrderTransDao {
    List<Map<String,Object>> listOrderTransAttachOrderinfoviewByConditions(Map param);

    int listOrderTransAttachOrderinfoviewByConditionsCount(Map param);

    Map<String,Object> singleOrderTransAttachOrderinfoviewByPrimaryKey(@Param("orderTransId")long orderTransId);
}
