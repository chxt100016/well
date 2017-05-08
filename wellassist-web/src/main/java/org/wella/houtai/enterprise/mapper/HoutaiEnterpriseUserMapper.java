package org.wella.houtai.enterprise.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface HoutaiEnterpriseUserMapper
{
  public abstract ArrayList<Map<String, Object>> getUserList(Map<String, Object> paramMap);
  
  public abstract ArrayList<Map<String, Object>> getShenherenList();
  
  public abstract ArrayList<Map<String, Object>> getGonghuofangList();
  
  public abstract Map<String, Object> getDuplicateCount(Map<String, Object> paramMap);
  
  public abstract Map<String, Object> getUserCount(Map<String, Object> paramMap);
  
  public abstract Map<String, Object> getUserInfo(Map<String, Object> paramMap);
  
  public abstract void setLockState(Map<String, Object> paramMap);
  
  public abstract void setState(Map<String, Object> paramMap);
  
  public abstract void setGonghuofang(Map<String, Object> paramMap);
  
  public abstract void saveUserInfo(Map<String, Object> paramMap);
  
  public abstract void saveUserPass(Map<String, Object> paramMap);
  
  public abstract void deleteData(Map<String, Object> paramMap);
}
