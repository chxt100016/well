package org.wella.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/25.
 */
public interface RegionService {

    /**
     * 通过父区域id查询子区域list
     * @param param 父区域id
     * @return 子区域list
     */
    List<Map<String,Object>> getChildRegionList(Map<String,Object> param);

    /**
     * 通过区域id和具体地址拼接出详细地址
     * @param regionId 区域id
     * @param address 具体地址
     * @return 详细地址
     */
    String getDetailAddress(long regionId,String address);
}
