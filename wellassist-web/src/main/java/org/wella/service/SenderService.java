package org.wella.service;

import org.wella.entity.LogisticsInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/10.
 */
public interface SenderService {
    /**
     * 抢单大厅页面域对象
     * @param param
     * @return
     */
    public List<Map<String,Object>> grabHallInfos(Map param);
    /**
     * 抢单大厅获取物流订单总数（分页）
     * @param param
     * @return
     */
    public int grabHallInfosCount(Map param);


    public Map<String,Object> findLogisticsInfo(Map param);

    public void grabLogisticsOrder(Map param);

    public List<Map<String,Object>> grabLogisticsList(Map param);

    public int grabLogisticsListCount(Map param);

    List<Map<String,Object>> listLogisticsInfoByConditions(Map queryLogistics);

    List<Map<String,Object>> homePageLogisicsInfos(Map queryLogistics);
}
