package org.wella.houtai.sys.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface MenuMapper
{
  public abstract ArrayList<Map<String, Object>> getMenuList(Map<String, Object> paramMap);
  
  public abstract int getMenuListCount(Map<String, Object> paramMap);
  
  public abstract void addNewMenu(Map<String, Object> paramMap);
  
  public abstract int deleteMenu(Map<String, Object> paramMap);
  
  public abstract Map<String, Object> getMenuInfo(Map<String, Object> paramMap);
  
  public abstract void updateMenuInfo(Map<String, Object> paramMap);
  
  public abstract ArrayList<Map<String, Object>> getRoleList(Map<String, Object> paramMap);
  
  public abstract ArrayList<Map<String, Object>> getTotalMenuList();
  
  public abstract Map<String, Object> getRoleInfo(Map<String, Object> paramMap);
  
  public abstract Map<String, Object> getRoleCount(Map<String, Object> paramMap);
}

