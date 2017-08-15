package org.wella.service.impl;

import io.wellassist.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wella.common.utils.CommonUtil;
import org.wella.common.utils.ConvertUtil;
import org.wella.dao.*;
import org.wella.entity.CreditorAuthenticInfo;
import org.wella.entity.VehicleGrabInfo;
import org.wella.entity.VehicleInfo;
import org.wella.service.CreditorService;
import org.wella.service.UserinfoService;
import org.wella.service.WaOrderService;
import org.wella.utils.DateUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/21.
 */

@Service("userinfoServiceImpl")
public class UserinfoServiceImpl implements UserinfoService{

    @Autowired
    private WaUserDao waUserDao;

    @Autowired
    private UserinfoDao userinfoDao;

    @Autowired
    private WaAdressDao  waAdressDao;

    @Autowired
    private VehicleGrabDao vehicleGrabDao;

    @Autowired
    private VehicleGrabInfoDao  vehicleGrabInfoDao;

    @Autowired
    private VehicleInfoDao  vehicleInfoDao;


    @Override
    @Transactional
    public void updateCompanyInfo(Map params) {
        Map<String,Object> update=new HashMap<>();
        long userId=(long)params.get("userId");
        update.put("userId",userId);
        update.put("userEmail",params.get("userEmail"));
        update.put("userSeatPhone",params.get("userSeatPhone"));
        update.put("legalIdCard",params.get("legalIdCard"));
        waUserDao.updateUserByUserId(update);

        update.clear();
        update.put("userId",userId);
        update.put("companyImg",params.get("companyImg"));
        update.put("companyYyZzImg",params.get("companyYyZzImg"));
        update.put("companyXkzImg",params.get("companyXkzImg"));
        update.put("companyTxkzImg",params.get("companyTxkzImg"));
        update.put("zcRegionId",params.get("zcRegionId"));
        update.put("zcXxAddress",params.get("zcXxAddress"));
        update.put("companyLxr",params.get("companyLxr"));
        update.put("companyLxrPhone",params.get("companyLxrPhone"));
        update.put("companyLxrEmail",params.get("companyLxrEmail"));
        userinfoDao.updateUserinfoByUserId(update);
    }

    @Override
    public boolean checkOrgPass(long userId, String oldPass, int type) {
        Map<String,Object> user=waUserDao.singleUserByPrimaryKey(userId);
        String userPass=(String)user.get("user_pass");
        String czPass=(String)user.get("cz_pass");
        if (type==0){
            if(CommonUtil.MD5(oldPass).toString().equals(userPass)){
                return true;
            }
        }else if (type==1){
            if(CommonUtil.MD5(oldPass).toString().equals(czPass)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void updatePayNewPass(long userId, String payNewpass) {
        Map<String,Object> update=new HashMap<>();
        update.put("userId",userId);
        update.put("czPass",CommonUtil.MD5(payNewpass));
        waUserDao.updateUserByUserId(update);
    }

    @Override
    public void updateLoginNewPass(long userId, String loginNewPass) {
        Map<String,Object> update=new HashMap<>();
        update.put("userId",userId);
        update.put("userPass",CommonUtil.MD5(loginNewPass));
        waUserDao.updateUserByUserId(update);
    }


    @Override
    public Integer addAddress(Map map) {
        return waAdressDao.addAddress(map);
    }



   /* String pParam = userinfo.get("zcRegionId").toString().substring(0, 2) + "0000";
    String cParam = userinfo.get("zcRegionId").toString().substring(0, 4) + "00";
    //省列表
      model.addAttribute("provinceList", this.getChildRegionList(0));
      model.addAttribute("provinceId", pParam);
    //市列表
      model.addAttribute("cityList", this.getChildRegionList(CommonUtil.getIntFromString(pParam)));
      model.addAttribute("cityId", cParam);
    //区列表
      model.addAttribute("countyList", this.getChildRegionList(CommonUtil.getIntFromString(cParam)));
      model.addAttribute("userName", user.get("userName"));*/



    @Override
    public List<Map<String, Object>> selectAddress(Long userId) {
        List<Map<String,Object>> list=waAdressDao.selectAddress(userId);
        for(Map map:list){


        }
        return null;

    }


    @Override
    public Integer updateDefault(Long id) {
        return waAdressDao.updateDefault(id);
    }


    @Override
    public Integer  operationDriver(Byte userType,Map map) {
        Integer result;
        switch (userType) {
            case 3:
                Map param = vehicleGrabDao.selectVgdId(map);
                VehicleGrabInfo vehicleGrabInfo = new VehicleGrabInfo();
                vehicleGrabInfo.setVehicleGrabId((Long) param.get("vehicleGrabId"));
                vehicleGrabInfo.setDriverName((String) map.get("driverName"));
                vehicleGrabInfo.setDriverPhone((String) map.get("driverPhone"));
                vehicleGrabInfo.setVehicleHangingNo((String) map.get("vehicleHangingNo"));
                vehicleGrabInfo.setVehicleNo((String) map.get("vehicleNo"));
                vehicleGrabInfo.setVehicleSize((Double) map.get("vehicleSize"));
                result = vehicleGrabInfoDao.createVehicleGrabInfo(vehicleGrabInfo);
                return result;
            case 0:
                VehicleInfo vehicleInfo = new VehicleInfo();
                vehicleInfo.setOrderId((Long) map.get("orderId"));
                vehicleInfo.setDriverName((String) map.get("driverName"));
                vehicleInfo.setDriverPhone((String) map.get("driverPhone"));
                vehicleInfo.setVehicleHangingNo((String) map.get("vehicleHangingNo"));
                vehicleInfo.setVehicleNo((String) map.get("vehicleNo"));
                vehicleInfo.setVehicleSize((Double) map.get("vehicleSize"));
                result = vehicleInfoDao.createVehicleInfo(vehicleInfo);
                return result;
            default:
                return 0;
        }

    }










    @Override
    public Integer deleteDriver(Byte userType, Long id) {
        Integer result;
        if(userType==3){
             result=vehicleGrabInfoDao.deleteDriver(id);
        }else{
             result=vehicleInfoDao.deleteDriver(id);
        }
        return result;
    }
}
