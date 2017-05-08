package org.wella.houtai.jygl.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface HoutaiJyglTixianMapper
{
  public abstract ArrayList<Map<String, Object>> getTxList(Map<String, Object> paramMap);
  
  public abstract int getTxListCount(Map<String, Object> paramMap);
}



/* Location:           C:\Users\Administrator\Desktop\wella\WEB-INF\classes\

 * Qualified Name:     org.HoutaiJyglTixianMapper

 * JD-Core Version:    0.7.0.1

 */