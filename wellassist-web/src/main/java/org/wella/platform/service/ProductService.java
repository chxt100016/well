package org.wella.platform.service;

import org.wella.entity.Prod;

import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/18.
 * 平台
 */
public interface ProductService {

    /**
     *
     * @param map 分页查询参数
     *            Map对象中startNumber
     *            count一页的行数，注意最后一页行数的逻辑判断
     * @return
     */
    List<Prod> findProdList(Map map);

    /**
     * 获取模糊查询中满足商品的条数
     * @param map
     * @return
     */
    int productCount(Map map);

    /**
     * 获取指定产品id的产品
     * @param prodId
     * * @return
     */
    Prod findProductById(long prodId);

    /**
     * 更新指定的产品
     * @param prod
     */
    int updateProductById(Prod prod);

    /**
     * 根据产品id删除该产品
     * 根据现有的业务逻辑，同时需要删除wa_prod_user表中的信息
     * @param prodId
     * @return 返回删除的条目
     */
    int deleteProductById(long prodId);

    /**
     * 生成产品的业务逻辑
     * @param prod
     * @return
     */
    int createProduct(Prod prod);


}
