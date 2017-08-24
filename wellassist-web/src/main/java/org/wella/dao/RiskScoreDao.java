package org.wella.dao;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by liuwen on 2017/6/8.
 */
@Repository
public interface RiskScoreDao {

    List<HashMap<String,String>> getFactorScoreMap();
}
