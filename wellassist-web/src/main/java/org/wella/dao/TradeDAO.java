package org.wella.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/6/5.
 */
@Repository
public interface TradeDAO {
    //交易列表中的内容
    List<Map<String,Object>> getTradeList(Map map);

    int tradeCount(Map<String,Object> map);

    Map<String,Object> tradeDetail(Map<String,Object> map);

    List<Map<String,Object>> tradeDetailList(Map<String,Object> map);

    //提现列表中的相关操作
    List<Map<String,Object>> withdrawList(Map<String,Object> map);

    int withdrawCount(Map<String,Object> map);

    List<Map<String,Object>> getWithdrawRecordList(Map<String,Object> param);

    Map<String,Object> getWithdrawMoneyTotal(Map<String,Object> param);

    int getWithdrawRecordCount(Map<String,Object> map);
    //充值列表中的相关操作
    List<Map<String,Object>> rechargeList(Map<String,Object> map);

    int rechargeCount(Map<String,Object> map);

    /**
     * 提现申请存储过程的调用
     * @param map
     * @return
     */
    int withdrawApply(Map<String,Object> map);

    /**
     * 提现处理处理存储过程的调用
     * @param map
     * @return
     */
    int withdrawHandle(Map<String,Object> map);

    /**
     * 充值申请存储过程的调用
     * @param map
     * @return
     */
    int rechargeApply(Map<String,Object> map);

    /**
     * 充值处理存储过程的调用
     * @param map
     * @return
     */
    int rechargeHandle(Map<String,Object> map);



}
