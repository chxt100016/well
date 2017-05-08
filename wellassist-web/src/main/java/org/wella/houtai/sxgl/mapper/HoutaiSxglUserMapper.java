package org.wella.houtai.sxgl.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface HoutaiSxglUserMapper
{
  public abstract ArrayList<Map<String, Object>> getFkfList(Map<String, Object> paramMap);
  
  public abstract int getFkfListCount(Map<String, Object> paramMap);
}