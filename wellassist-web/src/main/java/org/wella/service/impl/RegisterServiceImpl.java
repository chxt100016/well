package org.wella.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.wella.common.utils.CommonUtil;
import org.wella.dao.UserinfoDao;
import org.wella.dao.WaUserDao;
import org.wella.service.RegisterService;
import org.wella.utils.CodeUtil;
import org.wella.utils.MailUtil;
import org.wella.utils.ResetUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by liuwen on 2017/5/15.
 */
@Service("registerServiceImpl")
public class RegisterServiceImpl implements RegisterService{

    @Autowired
    private WaUserDao waUserDao;

    @Autowired
    private UserinfoDao userinfoDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     *
     * @param request
     * @return
     * 处理的业务逻辑
     * 1.根据注册填写的信息，注册账号信息，公司相关信息，写入表wa_user与表wa_userinfo中
     * 2.项邮箱中发送相关信息
     */
    public HashMap register(HttpServletRequest request) {

        // 这里可以验证各字段是否为空
        HashMap result = new HashMap();
        //利用正则表达式（可改进）验证邮箱是否符合邮箱的格式

        HashMap paramlist = new HashMap();
        String companyname = CommonUtil.GetRequestParam(request, "companyname", "");
        paramlist.put("company_name", companyname);
        //企业同名的判断
        HashMap ret = this.waUserDao.getCountByName(paramlist);
        if (CommonUtil.getIntFromString(ret.get("cn").toString()) > 0) {
            ret.put("state", Integer.valueOf(-1));
            ret.put("content", "该企业已经存在！");
            return ret;

        } else {
            HttpSession session = request.getSession();
            Map userinfo = (Map) session.getAttribute("userinfo");
            HashMap wa_user = new HashMap();
            String user_name = CommonUtil.GetRequestParam(request, "companyname", "");
            String user_account=CommonUtil.GetRequestParam(request, "user_name", "");
            String user_seat_phone=CommonUtil.GetRequestParam(request, "contactseat", "");
            String legal_id_card=CommonUtil.GetRequestParam(request, "legalIdCard", "");
            String user_reg_no = CommonUtil.GetRequestParam(request, "companyaccount", "");
            String contactcustomer = CommonUtil.GetRequestParam(request, "contactcustomer", "");
            String user_phone = CommonUtil.GetRequestParam(request, "contactphone", "");
            String user_email = CommonUtil.GetRequestParam(request, "contactemail", "");
            String user_pass = CommonUtil.GetRequestParam(request, "pass1", "");
            String user_type = CommonUtil.GetRequestParam(request, "user_type", "0");
            String supply_id=CommonUtil.GetRequestParam(request, "supply_id", "0");
            wa_user.put("user_type", user_type);
            wa_user.put("user_pass", CommonUtil.MD5(user_pass));
            wa_user.put("user_email", user_email);
            wa_user.put("user_phone", user_phone);
            wa_user.put("user_reg_no", user_reg_no);
            wa_user.put("user_name", user_name);
            wa_user.put("user_account",user_account);
            wa_user.put("user_seat_phone",user_seat_phone);
            wa_user.put("legal_id_card",legal_id_card);
            wa_user.put("supply_id",supply_id);
            //生成激活码
            String code = CodeUtil.generateUniqueCode();
            wa_user.put("activity_code", code);

            //插入新的用户并将新的id号返回
            this.waUserDao.createUser(wa_user);
            String userId = wa_user.get("newId").toString();

            String waUserInfoId = "";
            if (CommonUtil.getIntFromString(userId) > 0) {
                HashMap wa_userinfo = new HashMap();
                String company_type = CommonUtil.GetRequestParam(request, "compkind", "");
                //String sp_type = CommonUtil.GetRequestParam(request, "prodkind", "");
                String company_img = CommonUtil.GetRequestParam(request, "yingye_img4", "");
                String company_name = CommonUtil.GetRequestParam(request, "companyname", "");
                String company_yy_zz_img = CommonUtil.GetRequestParam(request, "yingye_img1", "");
                String company_xkz_img = CommonUtil.GetRequestParam(request, "yingye_img2", "");
                String company_txkz_img = CommonUtil.GetRequestParam(request, "yingye_img3", "");
                String company_yy_zz = CommonUtil.GetRequestParam(request, "company_yy_zz", "");
                String company_kh_xkz = CommonUtil.GetRequestParam(request, "company_xkz", "");
                String company_txkz = CommonUtil.GetRequestParam(request, "company_txkz", "");
                String zc_region_id = CommonUtil.GetRequestParam(request, "zc_region_id", "");
                String zc_xx_address = CommonUtil.GetRequestParam(request, "address", "");
                String company_lxr = CommonUtil.GetRequestParam(request, "contact", "");
                String company_lxr_phone = CommonUtil.GetRequestParam(request, "contactphone", "");
                wa_userinfo.put("company_type", company_type);
                wa_userinfo.put("info_userId", userId);
                //wa_userinfo.put("sp_type", sp_type);
                wa_userinfo.put("company_name", company_name);
                wa_userinfo.put("company_img", company_img);
                wa_userinfo.put("company_yy_zz_img", company_yy_zz_img);
                wa_userinfo.put("company_xkz_img", company_xkz_img);
                wa_userinfo.put("company_txkz_img", company_txkz_img);
                wa_userinfo.put("company_yy_zz", company_yy_zz);
                wa_userinfo.put("company_kh_xkz", company_kh_xkz);
                wa_userinfo.put("company_txkz", company_txkz);
                wa_userinfo.put("zc_region_id", zc_region_id);
                wa_userinfo.put("zc_xx_address", zc_xx_address);
                //注册时的初始化密码为123456
                wa_userinfo.put("cz_pass", CommonUtil.MD5("123456"));
                wa_userinfo.put("company_lxr", company_lxr);
                wa_userinfo.put("company_lxr_phone", company_lxr_phone);
                try {
                    this.userinfoDao.createWaUserInfo(wa_userinfo);
                    HashMap e = new HashMap();
                    HashMap mapClass = new HashMap();
                    mapClass.put("user_id", userId);
                    mapClass.put("gys_id", contactcustomer);
                    mapClass.put("rel_state", "0");
                    mapClass.put("rel_date", new Date());
//                    e.put("mapClass", mapClass);
//                    e.put("tableName", "wa_user_relation");
//                    this.commonMapper.insertSingleBO(e);
                    ret.put("state", Integer.valueOf(1));
                    ret.put("content", "注册成功！");
                    //注册成功后的邮件发送，根据邮箱及激活码进行操作
                    String content = "<html><head></head><body><h1>这是一封激活邮件,激活请点击以下链接</h1><h3><a href='http://localhost:8080/wellassist/mail/active" +"?code=" + code + "' target = '_blank'>http://localhost:8080/wellassist/mail/active?code=" + code + "</href></h3></body></html>";
                    new Thread(new MailUtil(user_email, content)).start();
                } catch (Exception e) {
                    ret.put("state", Integer.valueOf(-1));
                    ret.put("content", "注册失败！");
                }
            } else {
                ret.put("state", Integer.valueOf(-1));
                ret.put("content", "注册失败！");
            }
        }
        //生成激活码
        String code = CodeUtil.generateUniqueCode();
        return ret;
    }

    /**
     *
     * @param code 激活码
     * @return
     * 业务逻辑，根据激活码与数据库中的激活码进行对比，如果二者完全一致则执行激活操作
     */
    public boolean activeUser(String code) {
        int count = this.waUserDao.updateUserByCode(code);
        if(count>0){
            return true;
        }
        return  false;
    }

    /**
     * 忘记密码后对密码进行重置时项邮箱发送验证码业务逻辑
     * @param email
     * 项邮箱发送重置验证码，将该验证码写入redis数据库
     * 后面调整使用redis操作
     */
    public void sentValidCode(String email){
        //生成重置验证码
        String code = CodeUtil.resetCode();
        //将产生的验证码发送到相应的邮箱
        new Thread(new ResetUtil(email,code)).start();
        //将验证码写入到数据库
        HashMap hashMap = new HashMap();
        hashMap.put("useEmail",email);
        hashMap.put("resetCode",code);
        ValueOperations<String ,String > valueOperations = stringRedisTemplate.opsForValue();
        String key = "checkCode" + email;
        valueOperations.append(key,code);
        //验证码码设置60秒有效期
        valueOperations.set(key,code,120L, TimeUnit.SECONDS);
        //预留验证码写入数据库
        //this.waUserDao.updateUserByEmail(hashMap);
    }

    /**
     *
     * @param email
     * 重置用户密码,将用户密码重置到数据库
     */
    public void resetPassword(String email,String password,String checkCode){
        HashMap map = new HashMap();
        map.put("userEmail",email);
        //使用MD5加密
        String tempPassword = CommonUtil.MD5(password);
        map.put("userPassword",tempPassword);
        //与redis数据库中的验证码对比
        ValueOperations<String ,String > valueOperations = stringRedisTemplate.opsForValue();
        String checkCodeValue = valueOperations.get("checkCode"+email);
        if(checkCode.equalsIgnoreCase(checkCodeValue)) {
            this.waUserDao.updateUserByEmail(map);
        }
    }

    /**
     * 判断用户phone与email是否一致
     * @param email
     * @param phone
     */
    public boolean checkAccount(String email,String phone){
        int count = waUserDao.checkAccount(email,phone);
        if(count==1){
            return true;
        }else {
            return false;
        }
    }

}
