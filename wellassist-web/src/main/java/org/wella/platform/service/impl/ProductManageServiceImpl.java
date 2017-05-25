package org.wella.platform.service.impl;

import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.ProdDao;
import org.wella.dao.RegionDao;
import org.wella.entity.Region;
import org.wella.platform.service.ProductManageService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/18.
 */
@Service("productManageServiceImpl")
public class ProductManageServiceImpl implements ProductManageService{
    @Autowired
    private ProdDao prodDao;
    @Autowired
    private RegionDao regionDao;


    @Override
    public List<Map<String, Object>> prodList(Map map) {
        List<Map<String,Object>> prods=prodDao.listProdAttachUser(map);
        ConvertUtil.convertDataBaseMapToJavaMap(prods);
        for(Map pmap : prods){
            long regionId=(long)pmap.get("prodRegionId");
            String address=regionDao.getRegionDetailName(regionId)+" "+pmap.get("prodRegionAddr");
            pmap.put("address",address);
        }
        return prods;
    }

    @Override
    public int productCount(Map map) {
        int count=prodDao.listProdAttachUserCount(map);
        return count;
    }

    @Override
    public Map viewProductInfo(long prodId) {
        Map<String,Object> prodMap=prodDao.singleProdByPrimaryKey(prodId);
        ConvertUtil.convertDataBaseMapToJavaMap(prodMap);
        long regionId=(long)prodMap.get("prodRegionId");
        String address=regionDao.getRegionDetailName(regionId)+" "+prodMap.get("prodRegionAddr");
        prodMap.put("address",address);
        return prodMap;
    }

    @Override
    public int updateProductById(Map map) {
        return prodDao.updateProdByPrimaryKey(map);
    }

    @Override
    public int remove(long prodId) {
        return prodDao.deleteProdByPrimaryKey(prodId);
    }

    @Override
    public void publish(Map prodMap) {
        prodDao.createProd(prodMap);
    }
}
