package org.wella.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wella.entity.BillAddress;

/**
 * Created by ailing on 2017/6/8.
 */
@Repository
public interface BillAddressDao {

    void save(BillAddress billAddress);

    BillAddress queryByuserId(@Param("userId") long userId);

    void updateByuserId(BillAddress billAddress);

}
