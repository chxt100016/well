package org.wella.service;

import java.util.Map;

/**
 * Created by liuwen on 2017/6/5.
 * 财务业务处理类
 */
public interface FinanceService {

    /**
     * 充值相关业务处理
     * 两种业务处理，线下支付与银行支付
     * @param map
     * @return map返回充值处理结果，包含两个key值（state，content）
     */
    int recharge(Map<String,Object> map);


    /**
     * 提现相关业务处理
     * @param map
     * @return
     */
    int withdraw(Map<String,Object> map);


}
