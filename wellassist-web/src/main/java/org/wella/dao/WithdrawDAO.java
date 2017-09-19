package org.wella.dao;

import org.apache.ibatis.annotations.Param;
import org.wella.entity.Withdraw;

import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/6/2.
 */
public interface WithdrawDAO {
    /**
     *根据用户id获取提现记录，支持分页查询
     * @param params
     * @return
     */
    List<Withdraw> getWithdrawListByUserId(Map<String,Object> params);

    /**
     * 分页查询时的总条目
     * @param params
     * @return
     */
    int total(Map<String,Object> params);

    /**
     * @param parms
     * @return
     */
    Map<String,Object> getMoneyInfo(Map<String,Object> parms);

    /**
     * 存储过程withdrawProcess的调用
     * @param params
     * @return
     */
    int  withdrawApply(Map<String,Object> params);


    Map<String,Object> singlePoByPrimaryKey(@Param("withdrawId") long withdrawId);

    void update(Map<String, Object> update);
}
