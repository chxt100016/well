package org.wella.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wella.dao.RegionDao;
import org.wella.entity.Region;
import org.wella.service.RegionService;

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
        return regionDao.getChildRegionList(param);
    }
}
