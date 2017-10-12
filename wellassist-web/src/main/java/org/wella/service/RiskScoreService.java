package org.wella.service;

import io.wellassist.utils.Query;
import org.wella.entity.CreditorAuthenticInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/21.
 */
public interface RiskScoreService {
    /**
     * 功能描述，根据给定的企业社会信用代码计算风险评分
     * @param creditCode 企业社会信用代码
     * @return 各风险因子风险评分集合
     */
    List<Map<String,Object>> getRiskFactorScoreVO(String creditCode);
}
