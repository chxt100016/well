package org.wella.front.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface FrontTixianMapper
{
  public abstract Map<String, Object> getTixianMoneyInfo(Map<String, Object> paramMap);
  
  public abstract ArrayList<Map<String, Object>> getTxList(Map<String, Object> paramMap);
  
  public abstract int getTxListCount(Map<String, Object> paramMap);
}
