package org.wella.dao;

import org.apache.ibatis.annotations.Param;
import org.wella.entity.Region;

/**
 * Created by Administrator on 2017/5/11.
 */
public interface RegionDao {

    Region getByRegionId(@Param("regionId")long regionId);
}
