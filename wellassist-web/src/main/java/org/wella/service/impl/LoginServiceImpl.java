package org.wella.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wella.common.utils.CommonUtil;
import org.wella.dao.ProdDao;
import org.wella.dao.UserinfoDao;
import org.wella.dao.WaUserDao;
import org.wella.entity.Prod;
import org.wella.entity.User;
import org.wella.service.LoginService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/16.
 */
@Service("loginServiceImpl")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private WaUserDao waUserDao;

    @Autowired
    private UserinfoDao userinfoDao;

    @Autowired
    private ProdDao prodDao;

    /**
     *
     * @param account
     * @param password
     * @return通过验证后的提示内容
     * 如果提示内容为空字符串，通过验证，可以进行后续的逻辑操作，否则退出当前操作。
     * 三种情况的判断
     */
    public Map login(String account, String password) {
        String content = "";
        List<User> userList = waUserDao.findUserByAccount(account);
        if(userList!=null&&userList.size()!=0){
            User user = userList.get(0);
            String md5Password = CommonUtil.MD5(password);

            if(!md5Password.equals(user.getUserPass())){
                content = "密码错误,";
            }
            if(user.getActivityState()==0){
                content= content + "账号未激活,";
            }
           switch (user.getUserState())
           {
               case -3:
                   content = content + "账号已被删除";
                   break;
               case -2:
                   content = content + "账号已被冻结";
                   break;
               case  -1:
                   content = content + "审核不通过";
                   break;
               case  0:
                   content = content + "待审核";
                   break;
           }
        }else {
            content = "账号不存在";
        }

        if(content.contains("账号不存在")){
            content = "账号不存在";
        }else if(content.contains("账号未激活")){
            content = "账号未激活";
        }else {
        }
        HashMap result = new HashMap();
        result.put("content",content);
        if(content.equals("")){
            //成功登录后将user与userInfo对象保存到map对象并返回
            User user = userList.get(0);
            result.put("user",user);
            result.put("userInfo",userinfoDao.getOrderUserinfoByUserid(user.getUserId()));
            result.putAll(initViewData((userList.get(0).getUserType()),user.getUserId()));
        }
        return result;
    }

    //根据用户类型的不同项session中保存不同的数据
    //0-供货商, 1-客户方, 2-放款方， 3-物流方
    private Map initViewData(int type,long userId){
        HashMap map = new HashMap();
        switch (type){
            case 0:
            {
                HashMap queryMap = new HashMap();
                queryMap.put("userId",userId);
                List<Prod> prodList = prodDao.findProd(queryMap);
                map.put("initInfo",prodList);
            }
                break;
            case 1:
            {

            }
                break;
            case 2:
            {

            }
                break;
            case 3:
            {

            }
                break;
        }
        return map;
    }
}
