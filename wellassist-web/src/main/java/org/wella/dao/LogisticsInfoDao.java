package org.wella.dao;

import org.apache.ibatis.annotations.Param;
import org.wella.entity.LogisticsInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/12.
 */
public interface LogisticsInfoDao {
    /**
     * 生成物流订单，当卖家确认，并且买家选择配送时的逻辑处理
     * @param logisticsInfo
     */
    void createLogisticsInfoDao(LogisticsInfo logisticsInfo);

    /*物流方没有操作过（可进行抢单）的物流订单*/
    List<Map<String,Object>> undoLogisticsInfosByConditions(Map param);

    List<Map<String,Object>> undoLogisticsInfosAttachProdByConditions(Map param);

    List<Map<String,Object>> listLogisticsInfoByConditions(Map param);

    int CountLogitticsInfoByConditions(Map param);

    Map<String,Object> singleLogisticsInfoByPrimaryKey(@Param("logisticsId") long logisticsId);

    void createLogisticsInfo(LogisticsInfo logisticsInfo);

    int updateByPrimaryKey(Map param);

    int updateByConditions(Map param);

    Map<String,Object> singleLIattachVGByConditions(Map queryLI);

    List<Map<String, Object>> customerLogisticsInfoListInfo(Map param);

    int customerLogisticsInfoListInfoCount(Map param);

    List<Map<String,Object>> senderLogisticsOrderListInfo(Map param);

    int senderLogisticsOrderListInfoCount(Map param);

    Map<String,Object> singleLogisticsInfoViewByPrimaryKey(@Param("logisticsInfoId") long logisticsInfoId);


    Map<String,Object> selectUserInfo(@Param("logisticsInfoId") long logisticsInfoId);


    List<Map<Integer,Object>> profit(Map<String,Object> map);

    List<Map<String,Object>> listLogisticsOrderInfoViewByConditions(Map params);
}
