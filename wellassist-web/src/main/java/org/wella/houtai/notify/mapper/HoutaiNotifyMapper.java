package org.wella.houtai.notify.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface HoutaiNotifyMapper
{
  public abstract ArrayList<Map<String, Object>> getNotifyList(Map<String, Object> paramMap);
  
  public abstract int getNotifyListCount(Map<String, Object> paramMap);
}
