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
     * @param params
     */
    void updateCompanyInfo(Map params);

    /**
     * 校验密码
     * @param userId
     * @param oldPass
     * @param type 0：登录密码，1：操作密码
     * @return
     */
    boolean checkOrgPass(long userId, String oldPass, int type);

    void updatePayNewPass(long userId, String payNewpass);

    void updateLoginNewPass(long userId, String loginNewPass);

    Integer addAddress(Map  map);

    List<Map<String,Object>> selectAddress(Long userId);

    Integer updateDefault(Long id);


    Integer operationDriver(Byte userType,Map map);


    Integer deleteDriver(Byte userType,Long id);


    Prod selectProduct(Long prodId);

    /**
     * 用户的注册地址
     * @param userId wa_user表主键
     * @return 用户的注册地址
     */
    String getZcDetailAddress(long userId);


}
