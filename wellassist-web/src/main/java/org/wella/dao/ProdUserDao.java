package org.wella.dao;

import org.apache.ibatis.annotations.Param;
import org.wella.entity.Prod;
import org.wella.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/18.
 */
public interface ProdUserDao {

    List<Prod> getUserProdList(Map param);

    List<User> findCustomersBySupplierId(@Param("userId")long userId);
}
