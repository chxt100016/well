package org.wella.platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.qiniu.util.Json;
import com.wellapay.cncb.model.input.Register;
import com.wellapay.cncb.model.input.RegisterList;
import com.wellapay.cncb.model.input.VilcstDataList;
import com.wellapay.cncb.model.output.RegisterOutput;
import com.wellapay.cncb.service.CNCBPayConnectService;
import io.wellassist.utils.Query;
import io.wellassist.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConstantUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.common.utils.MapUtil;
import org.wella.dao.*;
import org.wella.entity.CreditorAuthenticInfo;
import org.wella.entity.User;
import org.wella.entity.UserSubAccount;
import org.wella.platform.service.MemberService;
import org.wella.service.CreditorService;
import org.wella.service.MessageService;
import org.wella.service.RegionService;
import org.wella.utils.MailUtil;

import java.util.*;

/**
 * Created by liuwen on 2017/5/18.
 */
@Service("menberServiceImpl")
public class MemberServiceImpl implements MemberService{

    @Autowired
    private WaUserDao waUserDao;
    @Autowired
    private ProdDao prodDao;
    @Autowired
    private ProdUserDao prodUserDao;
    @Autowired
    private RegionDao regionDao;
    @Autowired
    private RegionService regionServiceImpl;
    @Autowired
    private CreditorService creditorServiceImpl;
    @Autowired
    private CreditorAuthenticInfoDao creditorAuthenticInfoDao;
    @Autowired
    private MessageService messageServicesk;
    @Autowired
    private CNCBPayConnectService cncbPayConnectServiceImpl;
    @Autowired
    private UserinfoDao userinfoDao;
    @Autowired
    private UserSubAccountDao userSubAccountDao;


    @Override
    public Map<String, Object> findUserInfoById(long id) {
        Map res=userinfoDao.findUserInfoById(id);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        if(res.get("zcRegionId")!=null&&res.get("zcXxAddress")!=null){
            String zcRegionId=(String)res.get("zcRegionId");
            String zcXxAddress=(String)res.get("zcXxAddress");
            HashMap query=new HashMap();
            query.put("regionId",zcRegionId);
            String address=regionDao.getRegionDetailName(query)+" "+zcXxAddress;
            res.put("address",address);
        }else{
            String zcXxAddress=(String)res.get("zcXxAddress");
            res.put("address",zcXxAddress);
        }
        return res;
    }

    @Override
    public int createUser(Map map) {
        return 0;
    }


    @Override
    public int updateUserInfo(Map map) {
        int i=waUserDao.updateUserByUserId(map);
        int j=userinfoDao.updateUserinfoByUserId(map);
        messageServicesk.handleAdminUpdateUserinfoMessage(Long.parseLong(map.get("userId").toString()));
        return i+j;
    }

    @Override
    public int deleteUser(long userId) {
        int i=waUserDao.deleteUser(userId);
        int j=userinfoDao.deleteByUserId(userId);
        return i+j;
    }

    @Override
    public int total(Map map) {
        return waUserDao.findUserTotal(map);
    }

    @Override
    @Transactional
    public void approve(Map map) throws Exception {
        String comment=(String)map.get("comment");
        String email=(String)map.get("userEmail");
        Map updateMap=new HashMap();
        long userId=Long.parseLong(map.get("userId").toString());
        updateMap.put("userId",userId);
        updateMap.put("comment",comment);
        updateMap.put("userState",1);

        //在注册成功后给每个用户注册虚拟附属账户
        Map<String,Object> query=new HashMap<>();
        query.put("userId",userId);
        UserSubAccount usa=userSubAccountDao.singleQuery(query);
        if (null==usa){
            Map<String,Object> user=waUserDao.singleUserByPrimaryKey(userId);
            Map<String,Object> userinfo=userinfoDao.singleUserinfoByPrimaryKey(userId);
            List<VilcstDataList> list=new ArrayList<>();
            RegisterList registerList=new RegisterList();
            registerList.setName("VilcstDataList");
            VilcstDataList vilcstDataList=new VilcstDataList(userinfo.get("company_lxr").toString(),userinfo.get("company_lxr_phone").toString(),MapUtil.getStringfromMap(userinfo,"company_lxr_email"));
            list.add(vilcstDataList);
            registerList.setList(list);
            Register register=new Register(user.get("user_name").toString(),user.get("user_name").toString(), MapUtil.getStringfromMap(userinfo,"company_lp_name"),"0",user.get("legal_id_card").toString(),regionServiceImpl.getDetailAddress(Long.parseLong(userinfo.get("zc_region_id").toString()),userinfo.get("zc_xx_address").toString()),registerList);
            /*RegisterOutput registerOutput=null;
            try {
                registerOutput=cncbPayConnectServiceImpl.register(register);
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            String params= JSON.toJSONString(register);
            String result=CommonUtil.connectCNCBLocalServer(ConstantUtil.CNCB_SERVER_BASEURL+"register",params,"application/json");
            R r=JSON.parseObject(result,R.class);
            RegisterOutput registerOutput= JSON.parseObject(r.get("registerOutput").toString(),RegisterOutput.class);
            UserSubAccount userSubAccount=new UserSubAccount();
            userSubAccount.setSubAccNo(registerOutput.getSubAccNo());
            userSubAccount.setSubAccNm(registerOutput.getSubAccNm());
            userSubAccount.setCreateTime(new Date());
            userSubAccount.setUserId(userId);
            userSubAccountDao.create(userSubAccount);
        }

        waUserDao.updateUserByUserId(updateMap);
        messageServicesk.handleRegisterReviewMessage(email,comment,true);
    }

    @Override
    public void notAprove(Map map) {
        String comment=(String)map.get("comment");
        String email=(String)map.get("userEmail");
        Map updateMap=new HashMap();
        updateMap.put("userId",map.get("userId"));
        updateMap.put("comment",comment);
        updateMap.put("userState",-1);
        waUserDao.updateUserByUserId(updateMap);
        messageServicesk.handleRegisterReviewMessage(email,comment,false);
    }

    /**
     * 将用户登录密码与操作密码重置为123456
     * @param userId
     */
    public void resetPassword(long userId){
        String password = CommonUtil.MD5("123456");
        Map map = new HashMap();
        map.put("czPass",password);
        map.put("userPass",password);
        map.put("userId",userId);
        waUserDao.resetPassword(map);
        messageServicesk.handleResetPasswordMessage(userId);
    }
    @Override
    public List<Map<String, Object>> findSellerInfo(Map map) {
        map.put("userType",0);
        List<Map<String,Object>> list = waUserDao.findPlatformUserInfo(map);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        return list;
    }

    @Override
    public List<Map<String,Object>> findProductsByUserId(Map params) {
        List<Map<String,Object>> prods= prodDao.findProdByConditions(params);
        ConvertUtil.convertDataBaseMapToJavaMap(prods);
        for(Map pmap : prods){
            long regionId=(long)pmap.get("prodRegionId");
            String address=regionDao.getRegionDetailNameByRegionId(regionId)+" "+pmap.get("prodRegionAddr");
            pmap.put("address",address);
        }
        return prods;
    }

    public int findProductsByUserIdCount(Map params){
        return prodDao.findProdByConditionsCount(params);
    }


    @Override
    public List<User> findCustomersByUserId(long userId) {
        return prodUserDao.findCustomersBySupplierId(userId);
    }

    @Override
    public int publishProduct(Map map) {
        return prodDao.createProd(map);
    }

    @Override
    public int editProduct(Map map) {
        return prodDao.updateProdByPrimaryKey(map);
    }

    @Override
    public int deleteProduct(long id) {
        return prodDao.deleteProdByPrimaryKey(id);
    }

    @Override
    public List<Map<String,Object>> findProducts(Map map) {
        return prodDao.findProdByConditions(map);
    }

    @Override
    public List<Map<String, Object>> findCustomersInfo(Map map) {
        map.put("userType",1);
        List<Map<String, Object>> customersList=waUserDao.findPlatformCustomerUsers(map);
        ConvertUtil.convertDataBaseMapToJavaMap(customersList);
        return customersList;
    }

    @Override
    public int findCustomersInfoCount(Map map) {
        map.put("userType",1);
        return waUserDao.findPlatformCustomerUsersCount(map);
    }

    @Override
    public List<Map<String, Object>> findAccountList(Map map) {
        return null;
    }

    @Override
    public List<Map<String, Object>> findCreditList(Map map) {
        return null;
    }

    @Override
    public List<Map<String, Object>> findSenderList(Map map) {
        map.put("userType",3);
        List<Map<String,Object>> list = waUserDao.findPlatformUserInfo(map);
        ConvertUtil.convertDataBaseMapToJavaMap(list);
        return list;
    }

    @Override
    public List<Map<String, Object>> findLogisticRecord(Map map) {
        return null;
    }

    @Override
    public List<Map<String, Object>> findGrapOrderRecord(Map map) {
        return null;
    }

    @Override
    public List<Map<String, Object>> findCreditorList(Map query) {
        query.put("userType",2);
        List<Map<String,Object>> res=waUserDao.listUserByConditions(query);
        ConvertUtil.convertDataBaseMapToJavaMap(res);
        return res;
    }

    @Override
    public int findCreditorCount(Map<String, Object> map) {
        int res=waUserDao.listUserByConditionsCount(map);
        return 0;
    }

    @Override
    @Transactional
    public void authCreditor(long userId, String comment, int isAuthed) {
        Date now=new Date();
        Map<String,Object> updateuser=new HashMap<>();
        updateuser.put("userId",userId);

        CreditorAuthenticInfo creditorAuthenticInfo=creditorServiceImpl.getAuthenticingInfo(userId);
        Map<String,Object> updatecreditorauthInfo=new HashMap<>();
        updatecreditorauthInfo.put("creditorAuthenticInfoId",creditorAuthenticInfo.getCreditorAuthenticInfoId());
        updatecreditorauthInfo.put("mgrDate",now);
        updatecreditorauthInfo.put("comment",comment);

        if (isAuthed==1){
            updateuser.put("creditorState",2);
            updatecreditorauthInfo.put("state",2);
        }else if (isAuthed==0){
            updateuser.put("creditorState",-1);
            updatecreditorauthInfo.put("state",-1);
        }
        waUserDao.updateUserByUserId(updateuser);
        creditorAuthenticInfoDao.update(updatecreditorauthInfo);
    }

    @Override
    public Map<String, Object> authCreditorPageInfo(long userId) {
        Map<String,Object> res=new HashMap<>();
        Map<String,Object> userinfo=userinfoDao.singleUserinfoByPrimaryKey(userId);
        res.put("companyName",userinfo.get("company_name"));
        res.put("companyLxr",userinfo.get("company_lxr"));
        res.put("companyLxrPhone",userinfo.get("company_lxr_phone"));
        CreditorAuthenticInfo creditorAuthenticInfo=creditorServiceImpl.getAuthenticingInfo(userId);
        res.put("authCreditorPageInfo",creditorAuthenticInfo);
        return res;
    }


}
