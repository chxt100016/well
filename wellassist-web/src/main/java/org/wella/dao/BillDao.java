package org.wella.dao;

import com.sun.javafx.collections.MappingChange;
import io.wellassist.utils.Query;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wella.entity.Bankcard;
import org.wella.entity.Bill;

import java.util.List;
import java.util.Map;

/**
 * Created by ailing on 2017/6/8.
 */
@Repository
public interface BillDao {

    /**
     * insert wa_bill 表
     * @param bill wa_bill pojo对象
     * @return 更新条数
     */
    int save(Bill bill);

    Bill query(@Param("billId") long billId);

    /**
     * 发票列表视图
     * @param params 查询参数
     * @return 发票视图列表
     */
    List<Map<String,Object>> listBilllistviewByConditions(Map params);

    /**
     * 发票列表视图总记录数
     * @param params 查询参数
     * @return 总记录数
     */
    int listBilllistviewByConditionsCount(Map params);


    int update(Bill bill);
}
