package org.wella.platform.service.impl;

import io.wellassist.utils.ShiroUtils;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.ProdDao;
import org.wella.dao.RegionDao;
import org.wella.entity.Region;
import org.wella.platform.service.ProductManageService;

import java.util.Date;
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
            String address=regionDao.getRegionDetailNameByRegionId(regionId)+" "+pmap.get("prodRegionAddr");
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
        String regionIdStr=String.valueOf(regionId);
        prodMap.put("proviceId",regionIdStr.substring(0,2)+"0000");
        prodMap.put("cityId",regionIdStr.substring(0,4)+"00");
        prodMap.put("regionId",regionIdStr);
        String address=regionDao.getRegionDetailNameByRegionId(regionId)+" "+prodMap.get("prodRegionAddr");
        String [] adds = address.split(" ");
        if(adds.length==4){
            prodMap.put("region",adds[2]);
        }else{
            prodMap.put("region","");
        }
        prodMap.put("province",adds[0]);
        prodMap.put("city",adds[1]);
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
        prodMap.put("createDate",new Date());
        Long userId = ShiroUtils.getUserId();
        prodMap.put("createUserId",userId);
        prodDao.createProd(prodMap);
    }

    public int totalCount(Map parms){
        return prodDao.totalCount(parms);
    }

}
