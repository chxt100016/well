package org.wella.front.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface FrontUserMoneyMapper
{
  public abstract ArrayList<Map<String, Object>> getJyList(Map<String, Object> paramMap);
  
  public abstract int getJyListCount(Map<String, Object> paramMap);
}
