package org.wella.houtai.login.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface LoginMapper
{
  public abstract ArrayList<Map<String, Object>> getMenuList(Map<String, Object> paramMap);
  
  public abstract Map<String, Object> getMenuByUser(Map<String, Object> paramMap);
}
