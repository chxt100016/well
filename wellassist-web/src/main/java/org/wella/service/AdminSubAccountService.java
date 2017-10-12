package org.wella.service;

import org.wella.entity.AdminSubAccount;
import org.wella.entity.Bill;

import java.util.List;
import java.util.Map;

/**
 * Created by ailing on 2017/7/25.
 */
public interface AdminSubAccountService {

    /**
     * 从wa_admin_sub_account 表中查找订单交易中转账户
     * @return 订单交易中转账户
     */
    AdminSubAccount findOrderTransferAccount();

    /**
     * 从wa_admin_sub_account 表中查找贷款还款中转账户
     * @return 贷款还款中转账户
     */
    AdminSubAccount findLoanTransferAccount();
}
