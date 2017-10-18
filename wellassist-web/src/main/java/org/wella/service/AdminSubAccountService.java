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

    /**
     * 从本地数据库wa_user_sub_account校验此账户是否存在，
     * 然后校验此账户wa_admin_sub_account是否重复添加（待完成）
     * @param subAccNo subAccNo
     * @param subAccNm subAccNm
     * @return 校验结果：0：校验通过，-1:无此账户，-2重复添加
     */
    int validateAdminSubAccount(String subAccNo,String subAccNm);

    /**
     * 添加单条记录
     * @param adminSubAccount
     * @return 数据库更新条数
     */
    int create(AdminSubAccount adminSubAccount);

    /**
     * 通过类型查询所有可选的中转账户
     * @param type 1-付款中转户，2-还款中转户
     * @return list
     */
    List<AdminSubAccount> findAdminSubAccounts(int type);

    /**
     * 修改订单中转账户
     * @param id 选择的中转户id
     * @return 0：成功；-1失败
     */
    int updateOrderTransferAccount(long id);

    /**
     * 修改还款中转户
     * @param id 选择的中转户id
     * @return 0：成功；-1：失败
     */
    int updateLoanTransferAccount(long id);
}
