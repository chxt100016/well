package org.wella.houtai.jygl.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface HoutaiJyglWljyMapper
{
  public abstract ArrayList<Map<String, Object>> getJyglWljyList(Map<String, Object> paramMap);
  
  public abstract int getJyglWljyListCount(Map<String, Object> paramMap);
}



/* Location:           C:\Users\Administrator\Desktop\wella\WEB-INF\classes\

 * Qualified Name:     org.HoutaiJyglWljyMapper

 * JD-Core Version:    0.7.0.1

 */