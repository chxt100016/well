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
     * 公司风控评分项及其值
     * @param creditCode  企业社会信用代码
     * @return 公司风控评分项及其值组成map
     */
    Map<String,Object> getFactorAndValue(String creditCode);

    /**
     * 现判断redis中是否有相应的对象，如果有则从redis中获取该Map对象，如果没有则从数据库中获取
     * 根据风险因子表中的（factor_name,factor_item,score)三项封装成以factor_name为key值，value为后两项组成的map对象
     * select factor_name as factirNane,group_concat(factor_item) as factorItem,group_concat(factor_score) from factor_score
     * @return 根据风险因子表中的（factor_name,factor_item,factor_score)三项封装成以factor_name为key值，value为后两项组成的map对象
     */
    HashMap<String,HashMap<String,Double>> getFactorScoreMap();

    /**
     * 从wa_risk_factor中查询参与计算的评分项
     * @param map 查询参数
     * @return 风险因子
     */
    List<RiskFactor> getRiskFactor(HashMap<String, Object> map);
}
