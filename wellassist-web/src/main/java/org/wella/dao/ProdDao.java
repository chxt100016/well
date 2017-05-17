package org.wella.dao;

import org.apache.ibatis.annotations.Param;
import org.wella.entity.Prod;

<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> 6a71c2f098ca5b59d32ecf37c6434c00fb48b163
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

<<<<<<< HEAD
    List<Prod> findProd(Map map);

    List<Map<String,Object>> findUserProdList(Map map);
=======
    Map<String,Object> findProdById(Map param);
>>>>>>> 6a71c2f098ca5b59d32ecf37c6434c00fb48b163
}
