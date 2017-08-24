package org.wella.service.impl;

import io.wellassist.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.*;
import org.wella.entity.CreditorAuthenticInfo;
import org.wella.entity.RiskFactor;
import org.wella.service.*;
import org.wella.utils.DateUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator on 2017/7/21.
 */

@Service("riskScoreServiceImpl")
public class RiskScoreServiceImpl implements RiskScoreService {

    @Autowired
    private RiskFactorService riskFactorServiceImpl;

    @Override
    public List<Map<String, Object>> getRiskFactorScoreVO(String creditCode) {
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        //从数据库中获取各项风险因子及其值
        Map<String,Object> factorValueMap = riskFactorServiceImpl.getFactorAndValue(creditCode);

        //将风险因子表转换为Map对象
        HashMap<String,HashMap<String,Double>> factorScoreMap = this.riskFactorServiceImpl.getFactorScoreMap();

        //获取所有参与计算的风险因子
        HashMap<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("isCalculate",true);
        List<RiskFactor> riskFactor = riskFactorServiceImpl.getRiskFactor(paramMap);

        for(int i=0;i< riskFactor.size();i++){
//            RiskFactorScoreVO tempRiskFactorScoreVO = new RiskFactorScoreVO();
            String factorName = riskFactor.get(i).getFactorName();
            String factorCode = riskFactor.get(i).getFactorCode();
            String factorItem = factorValueMap.get(factorName).toString();
            int factorType = riskFactor.get(i).getFactorType();
            double score = getScore(factorScoreMap,factorCode,factorItem,factorType);
            Map<String,Object> tempForMap = new HashMap<String, Object>();
            tempForMap.put("id",i);
            //tempForMap.put("score",score);
            tempForMap.put("score",String.format("%.2f",score));
            tempForMap.put("resourceTable",riskFactor.get(i).getResourceTable());
            tempForMap.put("factorDesc",riskFactor.get(i).getFactorDesc());
            tempForMap.put("factorItem",factorItem);
            result.add(tempForMap);
        }
        return  result;
    }

    /**
     *
     * @param factorScoreMap
     * @param factorCode
     * @param factorItem
     * @param type 0表示风险因子值类型为数值类型，1表示风险因子为字符串类型。
     * @return
     */
    private double getScore(HashMap<String,HashMap<String,Double>> factorScoreMap,String factorCode,String factorItem,int type){
        double score =0;
        if(type==0){
            score = getNTypeScore(factorScoreMap,factorCode,factorItem);
        }else {
            score = getSTypeScore(factorScoreMap,factorCode,factorItem);
        }

        return  score;
    }

    /**
     *功能说明：风险因子为数值类型时的风险评分模型，如果小于最小值取最小值对应的评分，如果大于最大值去最大值对应的评分
     * 如果在中间，去前后相邻的项进行查分计算。
     * @param factorScoreMap 风险因子表中的（factor_name,factor_item,factor_score)三项封装成以factor_name为key值，value为后两项组成的map对象
     * @param factorCode 风险因子名称
     * @param factorItem 风险因子的值
     * @return 该风险因子对应的分值
     */
    private double getNTypeScore(HashMap<String,HashMap<String,Double>> factorScoreMap,String factorCode,String factorItem){
        double score =0;
        //获取该风险因子对应的Map对象（key为风险值,在此种类型下为数值类型，value为风险值对应的分数)
        HashMap<String,Double> subItems = factorScoreMap.get(factorCode);
        //构造一个与subItem一样的Map对象
        HashMap<Double,Double> copySubItems = new HashMap<Double, Double>();

        //将所有的key都加入到一个List中，并使用Collections工具排序
        Set<String> keySet =subItems.keySet();
        Iterator<String> iterator = keySet.iterator();
        List<Double> keyList = new ArrayList<Double>();
        while (iterator.hasNext()){
            String tempString = iterator.next();
            Double tempValue = Double.valueOf(tempString);
            keyList.add(tempValue);
            copySubItems.put(tempValue,subItems.get(tempString));
        }
        //将该风险因子的值加入list中并进行排序，并找出该值对应的index
        keyList.add(Double.valueOf(factorItem));
        Collections.sort(keyList);
        int index = keyList.indexOf(Double.valueOf(factorItem));
        try {
            //根据index的值进行相关的计算
            if (index == 0) {
                score = copySubItems.get(keyList.get(1));
            } else if (index == keyList.size() - 1) {
                score = copySubItems.get(keyList.get(keyList.size() - 2));
            } else {
                //该风险因子的值在中间采用内插的方法完成相关的计算
                double before = copySubItems.get(keyList.get(index - 1));
                double after = copySubItems.get(keyList.get(index + 1));
                score =before + (keyList.get(index) - keyList.get(index - 1)) * (after - before) / (keyList.get(index + 1) - keyList.get(index - 1));
            }
        }catch (Exception e){
            /*logger.debug(factorCode+"----"+factorItem+":风险评分失败");*/
            e.printStackTrace();
        }
        return  score;
    }

    /**
     *
     *功能说明：风险因子为字符串类型时的风险评分模型，分值为该值对应的分数
     * @param factorScoreMap 风险因子表中的（factor_name,factor_item,factor_score)三项封装成以factor_name为key值，value为后两项组成的map对象
     * @param factorCode 风险因子名称
     * @param factorItem 风险因子的值
     * @return 该风险因子对应的分值
     */
    private double getSTypeScore(HashMap<String,HashMap<String,Double>> factorScoreMap,String factorCode,String factorItem){
        double score =0;
        try {
            HashMap<String,Double> subItems = factorScoreMap.get(factorCode);
            System.out.println(factorCode);
            score = subItems.get(factorItem);
        }catch (Exception e){
            /*logger.debug(factorCode+"----"+factorItem+":风险评分失败");*/
            e.printStackTrace();
        }
        return  score;
    }
}
