package org.wella.dao;

import org.apache.ibatis.annotations.Param;
import org.wella.entity.Region;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/11.
 */
public interface RegionDao {

    Region getByRegionId(@Param("regionId")long regionId);

    String getRegionDetailName(Map<String, Object> paramMap);

    List<Map<String,Object>> getChildRegionList(Map<String,Object> param);

    List<Map<String,Object>> getRegionList(Map<String,Object> param);

}
