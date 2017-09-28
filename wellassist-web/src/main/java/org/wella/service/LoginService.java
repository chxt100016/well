package org.wella.service;

import java.util.Map;

/**
 * Created by liuwen on 2017/5/16.
 */
public interface LoginService {

    /**
     * 登录时的业务逻辑判断，判断其激活状态，其审核状态，并返回相应的逻辑处理代码
     * @param account 账号
     * @param password 密码
     * @return 逻辑处理代码
     */
    Map login(String account, String password);
}
