package org.wella.service;

import org.wella.entity.RiskFactor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/21.
 */
public interface RiskFactorService {

    /**
     *
     * @param creditCode  企业社会信用代码
     * @return
     */
    Map<String,Object> getFactorAndValue(String creditCode);

    HashMap<String,HashMap<String,Double>> getFactorScoreMap();

    List<RiskFactor> getRiskFactor(HashMap<String, Object> map);
}
