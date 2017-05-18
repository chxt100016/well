package org.wella.dao;

import org.wella.entity.Prod;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/18.
 */
public interface ProdUserDao {

    List<Prod> getUserProdList(Map param);
}
