package org.wella.service;
import io.wellassist.utils.Query;

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
     * @param param 分页参数
     * @return 抢单大厅页面信息
     */
    List<Map<String,Object>> grabHallInfos(Map param);

    /**
     * 抢单大厅获取物流订单总数（分页）
     * @param param 分页参数
     * @return 可抢物流订单总记录数
     */
    int grabHallInfosCount(Map param);

    /**
     * 抢单报价页面信息
     * @param logisticsId wa_logistics_info id
     * @return wa_logistics_info pojo
     */
    Map<String,Object> grabLogisticsPageInfo(long logisticsId);

    /**
     * 抢单列表记录
     * @param param 分页参数
     * @return 抢单列表记录
     */
    List<Map<String,Object>> grabLogisticsList(Map param);

    /**
     * 抢单列表总条目
     * @param param 分页参数
     * @return 抢单列表总条目
     */
    int grabLogisticsListCount(Map param);

    List<Map<String,Object>> listLogisticsInfoByConditions(Map queryLogistics);

    List<Map<String,Object>> homePageLogisicsInfos(Map queryLogistics);

    /**
     * 物流抢单处理
     * @param param 抢单报价，司机信息
     * @return 数据库更新条目数
     */
    int grabLogistics(Map param);

    /**
     * 物流订单列表
     * @param param 分页参数
     * @return 物流订单列表
     */
    List<Map<String,Object>> logisticsOrderListInfo(Map param);

    /**
     * 物流订单列表总条目数
     * @param param 分页参数
     * @return 物流订单列表总条目
     */
    int logisticsOrderListInfoCount(Map param);

    /**
     * 物流方取消抢单处理
     * @param request request
     * @param vehicleGrabId wa_vehicle_grab pk
     * @return true：操作成功，false：操作失败
     */
    boolean calcelGrab(HttpServletRequest request, long vehicleGrabId);

    /**
     * 物流方再抢单
     * @param logisticsId wa_logistics_info pk
     * @return 状态码
     */
    int reGrabLogistics(long logisticsId);

    List<Map<String,Object>> selectDriver(Long  logisticsId);

    /**
     * 利润报表
     * @param map echart请求参数
     * @return 利润报表
     */
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

    /**
     * 已处理发票列表
     * @param query 分页参数
     * @return 已处理发票列表
     */
    List handledBillsList(Query query);

    /**
     * 已处理发票列表总记录数
     * @param query 分页参数
     * @return 已处理发票列表
     */
    int handledBillsListCount(Query query);
}
