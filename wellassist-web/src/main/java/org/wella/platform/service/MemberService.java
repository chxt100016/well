package org.wella.platform.service;

import io.wellassist.utils.Query;
import org.wella.entity.Prod;
import org.wella.entity.User;
import org.wella.entity.UserAccount;

import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/18.
 * 后台成员管理服务接口
 */
public interface MemberService {

    /////////////////////用户公共接口方法\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    /**
     * 查询企业详细信息使用，包括wa_user,wa_userInfo两张表中的内容
     * @param id
     * @return
     */
    Map<String,Object> findUserInfoById(long id);

    /**
     * 业务逻辑处理
     * 从map对象中提取UserInfo与User信息，并插入两种表中
     * 插入现对邮箱与手机号进行重复性检查，如果邮箱与手机号已经存在则创建失败
     * 该操作时一个事务操作
     * @param map
     * @return 返回是否成功，成功为1，失败为2
     */
    int createUser(Map map);

    /**
     *更新用户的性格信息，
     * @param map 新的user对象，根据user的id
     * @return 1或2表示更新成功，0表示更新失败
     */
    int updateUserInfo(Map map);

    /**
     * 根据现有的业务逻辑，同时也要删除userinfo表中的对应userid的相关信息
     * 暂不考虑删除sys_oss表中的信息
     * @param userId
     * @return
     */
    int deleteUser(long userId);

    int total(Map map);

    /**
     * 注册申请通过时的业务处理
     * 通过时：更新用户状态，并项用户邮箱中发送相关信息
     * @param map
     */
    void approve(Map map);

    /**
     *注册申请通过时的业务处理
     * 通过时：更新用户状态，并项用户邮箱中发送相关信息,并发送审核失败原因
     * @param map
     */
    void notAprove(Map map);

    /**
     * 重置用户密码，用户的登录密码与操作密码将会变为123456
     * @param userId
     */
    void resetPassword(long userId);

    //////////////////////////卖家管理相关接口方法\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    /**
     * 根据指定条件进行卖家家模糊查询（考虑查询字段为公司名称）
     * 设计级联查询，查询两张表wa_user,wa_userInfo
     * 查询字段，暂且自定为usr_id,company_name,user_money,user_credit_money,company_img,所属买家名称（子查询）
     * @param map，查询条件(String):userId,companyName,userMoney,userCreditMoney,orderBy,start,size
     *            Map对象中start表示开始的行数
     *            size一页的行数，注意最后一页行数的逻辑判断
     * @return
     */
    List<Map<String,Object>> findSellerInfo(Map map);

    /**
     * 获取卖家的产品列表
     * @param
     * @return
     */
    List<Map<String,Object>> findProductsByUserId(Map params);

    int findProductsByUserIdCount(Map params);
    /**
     * 获取卖家的买家列表
     * @param userId
     * @return
     */
    List<User> findCustomersByUserId(long userId);

    /**
     * 平台方在买方产品管理界面进行产品发布
     * @param map 包含
     * @return 发布成功返回1,，失败返回0
     */
    int publishProduct(Map map);

    /**
     * 平台管理方在卖家界面编辑该产品
     * @param prod
     * @return
     */
    int editProduct(Map map);

    /**
     * 删除id对应的产品
     * @param id
     * @return
     */
    int deleteProduct(long id);

    /**
     * 模糊查询所有的产品
     * @param map
     * @return
     */
    List<Map<String,Object>> findProducts(Map map);

    //////////////////////////买家管理相关接口方法\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    /**
     * 根据指定条件进行买家模糊查询（考虑查询字段为公司名称），考虑分页查询
     * 设计级联查询，查询两张表wa_user,wa_userInfo
     * 查询字段，暂且自定为usr_id,company_name,user_money,user_credit_money,company_img,所属买家名称（子查询）
     * @param map，查询条件
     *            Map对象中startNumber表示开始的行数
     *            count一页的行数，注意最后一页行数的逻辑判断
     * @return
     */
    List<Map<String,Object>> findCustomersInfo(Map map);

    /**
     * 对应买家列表页面的公司总数，map包含usr_id,company_name,user_money,user_credit_money查询参数
     * @param map
     * @return
     */
    int findCustomersInfoCount(Map map);

    /**
     * 获取买方账号交易信息，关联表wa_user_money,wa_user,关联实体类UserAccount，使用分页查询
     * @param map
     * @return 返回的结果为交易时间（jy_date），交易金额（支出为负，充值为正jy_sj_money），账户余额（user_money)，交易名称（jy_mc）
     */
    List<Map<String,Object>> findAccountList(Map map);

    /**
     * 买方授信情况 暂且不考虑
     * @param map
     * @return
     */
    List<Map<String,Object>> findCreditList(Map map);

    //////////////////////////物流方管理相关接口方法\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    /**
     * 获取物流方相关信息，考虑分页查询，关联表wa_user
     * 查询字段
     * @param map
     * @return
     */
    List<Map<String,Object>> findSendsList(Map map);

    /**
     * 暂且不考虑
     * 获取指定物流方的物流信息,
     * @param map
     * @return
     */
    List<Map<String,Object>> findLogisticRecord(Map map);

    /**
     * 暂且不考虑
     * 抢单记录
     * @param map
     * @return
     */
    List<Map<String,Object>> findGrapOrderRecord(Map map);


    //////////////////////////放款方管理相关接口方法\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    List<Map<String,Object>> findCreditorList(Map map);


    int findCreditorCount(Map<String, Object> map);
}
