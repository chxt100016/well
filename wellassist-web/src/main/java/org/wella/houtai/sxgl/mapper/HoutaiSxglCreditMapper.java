package org.wella.houtai.sxgl.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface HoutaiSxglCreditMapper
{
  public abstract ArrayList<Map<String, Object>> getSxList(Map<String, Object> paramMap);
  
  public abstract int getSxListCount(Map<String, Object> paramMap);
  
  public abstract Map<String, Object> getSxInfo(Map<String, Object> paramMap);
}
