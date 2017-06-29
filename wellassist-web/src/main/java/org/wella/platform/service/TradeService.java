package org.wella.platform.service;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/29.
 */
public interface TradeService {
    Map<String,Object> findOfflinePayInfo(long orderTransId);

    int offlinePayCheckSubmit(Map<String, Object> param);
}
