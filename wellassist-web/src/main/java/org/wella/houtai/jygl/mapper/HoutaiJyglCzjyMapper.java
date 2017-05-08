package org.wella.houtai.jygl.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface HoutaiJyglCzjyMapper
{
  public abstract ArrayList<Map<String, Object>> getJyglCzjyList(Map<String, Object> paramMap);
  
  public abstract int getJyglCzjyListCount(Map<String, Object> paramMap);
}



/* Location:           C:\Users\Administrator\Desktop\wella\WEB-INF\classes\

 * Qualified Name:     org.HoutaiJyglCzjyMapper

 * JD-Core Version:    0.7.0.1

 */