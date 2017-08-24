package org.wella.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wella.dao.RiskFactorDao;
import org.wella.dao.RiskScoreDao;
import org.wella.entity.RiskFactor;
import org.wella.service.RiskFactorService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/21.
 */

@Service("riskFactorServiceImpl")
public class RiskFactorServiceImpl implements RiskFactorService {

    @Autowired
    private RiskFactorDao riskFactorDao;
    @Autowired
    private RiskScoreDao riskScoreDao;

    public Map<String, Object> getFactorAndValue(String creditCode) {
        HashMap<String,Object> result = new HashMap<String, Object>();
        List<HashMap<String,String>> fieldAndTable = this.riskFactorDao.getGroupByResourceTable();
        int size = fieldAndTable.size();
        for(int i=0;i< size;i++){
            String fieldNames = fieldAndTable.get(i).get("factorNames");
            String resourceTable = fieldAndTable.get(i).get("resourceTable");
            if(fieldNames!=null&&resourceTable!=null) {
                HashMap<String, Object> fieldsAndTable = new HashMap<String, Object>();
                fieldsAndTable.put("field",fieldNames);
                fieldsAndTable.put("tableName",resourceTable);
                fieldsAndTable.put("creditCode",creditCode);
                HashMap<String, Object> factorAndValue = this.riskFactorDao.getFactValue(fieldsAndTable);
                result.putAll(factorAndValue);
            }
        }
        return  result;
    }

    /**
     * 现判断redis中是否有相应的对象，如果有则从redis中获取该Map对象，如果没有则从数据库中获取
     * 根据风险因子表中的（factor_name,factor_item,score)三项封装成以factor_name为key值，value为后两项组成的map对象
     *
     * select factor_name as factirNane,group_concat(factor_item) as factorItem,group_concat(factor_score) from factor_score
     * @return 根据风险因子表中的（factor_name,factor_item,factor_score)三项封装成以factor_name为key值，value为后两项组成的map对象
     *
     */
    public HashMap<String, HashMap<String, Double>> getFactorScoreMap() {
        List<HashMap<String,String>> list =riskScoreDao.getFactorScoreMap();
        int size = list.size();
        HashMap result = null;
        if (size > 0) {
            result = new HashMap<String,HashMap<String,Double>>();
            for (int i = 0; i < size; i++) {
                HashMap<String,String> tempMap  = (HashMap<String, String>) list.get(i);
                String factorNmae = tempMap.get("factorCode");
                String factorItem =tempMap.get("factorItem");
                String factorScore = tempMap.get("factorScore");
                tempMap = null;
                String [] factorItems = factorItem.split(",");
                String [] factorScores = factorScore.split(",");
                int itemSize = factorItems.length;
                HashMap<String ,Double> factorMap = new HashMap<String, Double>();
                for(int j =0;j<itemSize;j++){
                    factorMap.put(factorItems[j],Double.valueOf(factorScores[j]));
                }
                result.put(factorNmae,factorMap);
            }
        }
        return result;
    }

    @Override
    public List<RiskFactor> getRiskFactor(HashMap<String, Object> map) {
        return riskFactorDao.getRiskFactor(map);
    }
}
