package org.wella.service;

import io.wellassist.utils.Query;
import org.wella.entity.CreditorAuthenticInfo;

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




}
