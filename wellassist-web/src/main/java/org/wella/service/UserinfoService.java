package org.wella.service;

import org.wella.entity.Prod;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/21.
 */
public interface UserinfoService {

    /**
     * 公司信息保存
     * @param params 公司基本信息
     */
    void updateCompanyInfo(Map params);

    /**
     * 校验密码
     * @param userId userId
     * @param oldPass 旧密码
     * @param type 0：登录密码，1：操作密码
     * @return 是否通过校验
     */
    boolean checkOrgPass(long userId, String oldPass, int type);

    /**
     *  更新支付密码
     * @param userId userId
     * @param payNewpass 新密码
     */
    void updatePayNewPass(long userId, String payNewpass);

    /**
     * 更新登录密码
     * @param userId userId
     * @param loginNewPass 新密码
     */
    void updateLoginNewPass(long userId, String loginNewPass);

    /**
     *  添加地址
     * @param map 地址信息
     * @return 数据库更新条数
     */
    Integer addAddress(Map  map);

    /**
     * 选择地址
     * @param userId userId
     * @return 地址详细
     */
    List<Map<String,Object>> selectAddress(Long userId);

    /**
     * 修改默认
     * @param id 地址id
     * @return 数据库更新条数
     */
    Integer updateDefault(Long id);

    /**
     * 编辑司机
     * @param userType 用户类型
     * @param map 司机信息
     * @return 数据库更新条数
     */
    Integer operationDriver(Byte userType,Map map);

    /**
     * 删除司机
     * @param userType 用户类型
     * @param id wa_vehicle_grab_info 主键
     * @return 数据库更新条数
     */
    Integer deleteDriver(Byte userType,Long id);

    /**
     *  产品详情
     * @param prodId 产品id
     * @return wa_prod pojo
     */
    Prod selectProduct(Long prodId);

    /**
     * 用户的注册地址
     * @param userId wa_user表主键
     * @return 用户的注册地址
     */
    String getZcDetailAddress(long userId);


}
