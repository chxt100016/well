package org.wella.platform.service;

import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/18.
 * 平台
 */
public interface ProductManageService {

    /**
     *
     * @param map 分页查询参数
     * @return
     */
    List<Map<String, Object>> prodList(Map map);

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
    Map viewProductInfo(long prodId);

    /**
     * 更新指定id的产品
     * @param map
     */
    int updateProductById(Map map);

    /**
     * 根据产品id删除该产品
     * 根据现有的业务逻辑，同时需要删除wa_prod_user表中的信息
     * @param prodId
     * @return 返回删除的条目
     */
    int remove(long prodId);

    /**
     * 发布产品的业务逻辑
     * @param prodMap
     * @return
     */
    void publish(Map prodMap);


}
