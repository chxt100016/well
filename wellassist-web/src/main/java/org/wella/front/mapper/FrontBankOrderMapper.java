package org.wella.front.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface FrontBankOrderMapper
{
  public abstract Map<String, Object> getCzMoneyInfo(Map<String, Object> paramMap);
  
  public abstract ArrayList<Map<String, Object>> getCzList(Map<String, Object> paramMap);
  
  public abstract int getCzListCount(Map<String, Object> paramMap);
}

