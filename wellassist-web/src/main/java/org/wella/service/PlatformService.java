package org.wella.service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/10.
 */
public interface PlatformService {

    /**
     * 添加用户
     * @param map 用户信息
     * @return 是否操作成功
     */
    boolean insertCustomer(Map map);

    /**
     * 交易量
     * @param map echart请求参数
     * @return 报表信息
     */
    List<BigDecimal> tradingVolume(Map map);

    /**
     * 贷款
     * @param map echart请求参数
     * @return 报表信息
     */
    List<BigDecimal> loanAmount(Map map);

    /**
     * 成交额
     * @param map echart请求参数
     * @return 报表信息
     */
    List<BigDecimal> turnover(Map map);

    /**
     * 利息
     * @param map echart请求参数
     * @return 报表信息
     */
    List<BigDecimal> interest(Map map);

    /**
     * 利息
     * @param map echart请求参数
     * @return 报表信息
     */
    Map<String,List> fundFlow(Map map);

}
