package org.wella.front.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface NewsMapper
{
  public abstract ArrayList<Map<String, Object>> getNewsList(Map<String, Object> paramMap);
  
  public abstract int getNewsListCount(Map<String, Object> paramMap);
}
