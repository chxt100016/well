package org.wella.platform.service;

import java.util.Map;

/**
 * Created by liuwen on 2017/5/18.
 * 后台授信管理服务接口
 */
public interface CreditService {
    Map<String,Object> findCreditApplyDetailInfo(long creditId);

    void checkCreditApply(Map<String, Object> params);
}
