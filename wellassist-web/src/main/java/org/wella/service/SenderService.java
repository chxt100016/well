package org.wella.service;


import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by ailing on 2017/5/10.
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

    List<Map<String,Object>> selectDriver(Long  logisticsId);

    List<BigDecimal> profit(Map<String,Object> map);

    /**
     * 物流方收到申请发票列表
     * @param query 分页参数
     * @return 发票列表
     */
    List requestBillsList(Map query);

    /**
     * 物流方收到申请发票列表总记录数
     * @param query 分页参数
     * @return 总记录数
     */
    int requestBillsListCount(Map query);

    /**
     * 物流方发送发票
     * @param billId 发票id
     * @param kpType 开票类型
     * @param eBill 电子发票url
     * @param kdNo 快递单号
     * @param kdName 快递名
     * @return 数据库表更新条数
     */
    int sendBill(long billId, String billNo, int kpType, String eBill, String kdNo, String kdName);
}
