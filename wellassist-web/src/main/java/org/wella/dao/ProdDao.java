package org.wella.dao;

import org.apache.ibatis.annotations.Param;
import org.wella.entity.Prod;

import java.util.List;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/10.
 */
public interface ProdDao {
    /**
     * 买家生成订单过程中需要的商品信息
     * @param prodId
     * @return
     */
    Prod getOrderProdByProdid(@Param("prodId")long prodId);

    List<Map<String,Object>> findUserProdList(Map map);

    Map<String,Object> findProdById(Map param);

    /**
     *
     * @param map
     * @return
     */
    List<Prod> findProdByConditions(Map map);

    int createProd(Prod prod);

    int updateProdByPrimaryKey(Prod prod);

    int deleteProdByPrimaryKey(@Param("prodId")long prodId);

    List<Prod> findProdByUserId(Map map);

}
