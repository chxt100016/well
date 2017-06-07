package org.wella.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.RegionDao;
import org.wella.entity.Region;
import org.wella.service.RegionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/25.
 */
@Service("regionServiceImpl")
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionDao regionDao;

    @Override
    public List<Map<String, Object>> getChildRegionList(Map<String, Object> param) {
        List<Map<String, Object>> regions=regionDao.getChildRegionList(param);
        ConvertUtil.convertDataBaseMapToJavaMap(regions);
        return regions;
    }

    @Override
    public String getDetailAddress(long regionId, String address) {
        Map query=new HashMap();
        query.put("regionId",regionId);
        String res=regionDao.getRegionDetailName(query)+" "+address;
        return res;
    }
}
