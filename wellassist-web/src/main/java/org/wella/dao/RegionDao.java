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

    /**
     * 通过regionId获取完整的地区名
     * @param paramMap 传入参数(String)regionId
     * @return 区域id对应的区域地址
     */
    String getRegionDetailName(Map<String, Object> paramMap);

    /**
     * 通过父区域id查询子区域list
     * @param param 父区域id
     * @return 子区域list
     */
    List<Map<String,Object>> getChildRegionList(Map<String,Object> param);

    List<Map<String,Object>> getRegionList(Map<String,Object> param);

    /**
     *
     * @param regionId
     * @return
     */
    String getRegionDetailNameByRegionId(@Param("regionId") long regionId);
}
