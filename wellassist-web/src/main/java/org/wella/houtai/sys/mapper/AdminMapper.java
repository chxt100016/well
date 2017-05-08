package org.wella.houtai.sys.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface AdminMapper
{
  public abstract ArrayList<Map<String, Object>> getAdminList(Map<String, Object> paramMap);
  
  public abstract int getAdminListCount(Map<String, Object> paramMap);
}
