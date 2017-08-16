package org.wella.service;

import org.wella.entity.LogisticsInfo;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 抢单报价页面信息
     * @param logisticsId
     * @return
     */
    public Map<String,Object> grabLogisticsPageInfo(long logisticsId);

    public List<Map<String,Object>> grabLogisticsList(Map param);

    public int grabLogisticsListCount(Map param);

    List<Map<String,Object>> listLogisticsInfoByConditions(Map queryLogistics);

    List<Map<String,Object>> homePageLogisicsInfos(Map queryLogistics);

    int grabLogistics(Map param);

    List<Map<String,Object>> logisticsOrderListInfo(Map param);

    int logisticsOrderListInfoCount(Map param);

    boolean calcelGrab(HttpServletRequest request, long vehicleGrabId);

    int reGrabLogistics(long logisticsId);


    List<Map<String,Object>> selectDriver(String logisticsId);
}
