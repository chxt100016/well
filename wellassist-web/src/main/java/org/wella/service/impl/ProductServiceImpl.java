package org.wella.service.impl;

import io.wellassist.utils.HttpContextUtils;
import io.wellassist.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.ProdDao;
import org.wella.dao.RegionDao;
import org.wella.entity.Prod;
import org.wella.entity.User;
import org.wella.service.ProductService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/16.
 */
@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProdDao prodDao;

    @Autowired
    private RegionDao regionDao;

    @Override
    public void publishProduct(Map prodMap) {
            prodMap.put("createDate",new Date());
            HttpSession session = HttpContextUtils.getHttpServletRequest().getSession();
            User user = (User) session.getAttribute("user");
            prodMap.put("createUserId",user.getUserId());
            prodMap.put("prodState",2);
            prodDao.createProd(prodMap);
    }

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

    public int updateProductById(Map map) {
        return prodDao.updateProdByPrimaryKey(map);
    }
}
