package org.wella.service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by liuwen on 2017/5/13.
 */
public interface MailService {

    /**
     * 用户激活
     * @param code 激活吗
     * @return true：激活成功，false：激活失败
     */
    boolean activeUser(String code);
}
