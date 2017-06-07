package org.wella.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/25.
 */
public interface RegionService {

    List<Map<String,Object>> getChildRegionList(Map<String,Object> param);

    String getDetailAddress(long regionId,String address);
}
