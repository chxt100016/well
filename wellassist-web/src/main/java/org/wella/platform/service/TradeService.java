package org.wella.platform.service;

import io.wellassist.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/29.
 */
public interface TradeService {
    Map<String,Object> findOfflinePayInfo(long orderTransId);

    int offlinePayCheckSubmit(Map<String, Object> param);

    Map<String,Object> getOrderDetailPageInfo(long orderId);

    void updateOrder(Map<String, Object> params);

    List orderList(Query query);

    int orderListCount(Query query);

    Map<String,Object> findWlOfflinePayInfo(long logisticsTransId);

    int wlOfflinePayCheckSubmit(Map<String, Object> param);

    void withdrawRefuse(long withdrawId,long mgrId,String mgrIp);

    void withdrawReCheck(long withdrawId,long mgrId,String mgrIp);

    void withdrawApprove(long withdrawId,long mgrId,String mgrIp) throws Exception;

    void withdrawComplete(long withdrawId,long mgrId,String mgrIp);
}
