package org.wella.service;

import org.wella.entity.Prod;

import java.util.Map;

/**
 * Created by liuwen on 2017/5/16.
 */
public interface ProductService {

    /**
     * 发布产品
     * @param prod wa_prod pojo
     */
    public void publishProduct(Map prod);

    /**
     * single query
     * @param prodId prodId
     * @return wa_prod pojo
     */
    public Map viewProductInfo(long prodId);

    /**
     * 编辑产品
     * @param map wa_prod pojo
     * @return
     */
    public int updateProductById(Map map);
}
