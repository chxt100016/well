package org.wella.service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by liuwen on 2017/5/13.
 */
public interface MailService {
//    HashMap register(HttpServletRequest request);

    boolean activeUser(String code);
}
