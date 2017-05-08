package org.wella.houtai.jygl.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface HoutaiJyglUserMoneyMapper
{
  public abstract ArrayList<Map<String, Object>> getJyList(Map<String, Object> paramMap);
  
  public abstract int getJyListCount(Map<String, Object> paramMap);
  
  public abstract Map<String, Object> getJyDetailInfo(Map<String, Object> paramMap);
}



/* Location:           C:\Users\Administrator\Desktop\wella\WEB-INF\classes\

 * Qualified Name:     org.HoutaiJyglUserMoneyMapper

 * JD-Core Version:    0.7.0.1

 */