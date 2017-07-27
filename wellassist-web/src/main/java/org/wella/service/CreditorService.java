package org.wella.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/21.
 */
public interface CreditorService {
    /**
     * 先忽略放款方资质审核测试，以后改这个接口
     * @return
     */
    List<Map<String,Object>> findCreditorList();

    /**
     *放款方接受放款指派
     * test:暂定还款期为30天，免息期为7天，放款方利率=平台利率*0.9
     * @param paymentDays 还款期限
     * @return 0 失败 1成功
     */
    int acceptLoan(long loanId,long creditorUserId,int paymentDays,String operateIp);

}
