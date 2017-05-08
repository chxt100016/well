package org.wella.houtai.sys.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface RoleMapper
{
  public abstract ArrayList<Map<String, Object>> getRoleList(Map<String, Object> paramMap);
  
  public abstract int getRoleListCount(Map<String, Object> paramMap);
}

