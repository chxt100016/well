package org.wella.front.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface FrontCreditMapper
{
  public abstract ArrayList<Map<String, Object>> getCreditList(Map<String, Object> paramMap);
  
  public abstract int getCreditListCount(Map<String, Object> paramMap);
  
  public abstract Map<String, Object> getCreditInfo(Map<String, Object> paramMap);
}
