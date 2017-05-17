package org.wella.service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by liuwen on 2017/5/15.
 */
public interface RegisterService {

    /**
     * 用户注册时的相关业务逻辑
     * @param request
     * @return
     */
    HashMap register(HttpServletRequest request);

    boolean activeUser(String code);

    void resetPassword(String account,String password,String checkCode);

    void sentValidCode(String eamil);

    boolean checkAccount(String email,String phone);

}
