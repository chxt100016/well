package org.wella.front.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface FrontCreditInfoMapper
{
  public abstract ArrayList<Map<String, Object>> getCreditInfoList(Map<String, Object> paramMap);
  
  public abstract int getCreditInfoListCount(Map<String, Object> paramMap);
}

