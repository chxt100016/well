package org.wella.service;

import org.wella.entity.LogisticsInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/10.
 */
public interface SenderService {

    public List<LogisticsInfo> findLogisticsInfos(Map param);

    public int findLogisticsInfosCount(Map param);

    public LogisticsInfo findLogisticsInfo(Map param);

    public void grabLogisticsOrder(Map param);

    public List<Map<String,Object>> grabLogisticsList(Map param);

    public int grabLogisticsListCount(Map param);
}
