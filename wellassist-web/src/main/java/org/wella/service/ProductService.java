package org.wella.service;

import org.wella.entity.Prod;

import java.util.Map;

/**
 * Created by liuwen on 2017/5/16.
 */
public interface ProductService {
    public void publishProduct(Map prod);

    public Map viewProductInfo(long prodId);

    public int updateProductById(Map map);
}
