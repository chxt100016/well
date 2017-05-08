package org.wella.houtai.login.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface LoginUserMainMapper
{
  public abstract ArrayList<Map<String, Object>> getSupplierShList(Map<String, Object> paramMap);
  
  public abstract int getSupplierShListCount(Map<String, Object> paramMap);
  
  public abstract int getUserCountByName(Map<String, Object> paramMap);
  
  public abstract String getLastCode(Map<String, Object> paramMap);
  
  public abstract Map<String, Object> getUserMainInfo(Map<String, Object> paramMap);
}
