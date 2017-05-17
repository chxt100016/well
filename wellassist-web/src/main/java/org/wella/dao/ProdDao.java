package org.wella.dao;

import org.apache.ibatis.annotations.Param;
import org.wella.entity.Prod;

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

    Map<String,Object> findProdById(Map param);
}
