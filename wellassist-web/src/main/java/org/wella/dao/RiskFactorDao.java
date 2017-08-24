package org.wella.dao;

import org.springframework.stereotype.Repository;
import org.wella.entity.RiskFactor;

import java.util.HashMap;
import java.util.List;

/**
 * Created by liuwen on 2017/6/8.
 */
@Repository
public interface RiskFactorDao {

    List<HashMap<String,String>> getGroupByResourceTable();

    HashMap<String,Object> getFactValue(HashMap<String, Object> fieldsAndTable);

    List<RiskFactor> getRiskFactor(HashMap<String, Object> map);
}
