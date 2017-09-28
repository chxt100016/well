package org.wella.service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by ailing on 2017/5/15.
 */
public interface RegisterService {

    /**
     * 用户注册时的相关业务逻辑
     * @param request request
     * @return 相应逻辑码
     */
    HashMap register(HttpServletRequest request);

    /**
     * 用户激活
     * @param code 激活码
     * @return 是否激活成功
     */
    boolean activeUser(String code);

    /**
     * 用户修改密码
     * @param account 账户
     * @param password 新密码
     * @param checkCode 验证码
     */
    void resetPassword(String account,String password,String checkCode);

    /**
     * 向用户邮箱发送激活码
     * @param eamil email
     */
    void sentValidCode(String eamil);

    /**
     * 校验账户邮箱和电话
     * @param email email
     * @param phone phone
     * @return 是否校验通过
     */
    boolean checkAccount(String email,String phone);

}
