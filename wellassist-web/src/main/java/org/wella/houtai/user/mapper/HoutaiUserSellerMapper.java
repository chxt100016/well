package org.wella.houtai.user.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface HoutaiUserSellerMapper
{
  public abstract ArrayList<Map<String, Object>> getUserList(Map<String, Object> paramMap);
  
  public abstract int getUserListCount(Map<String, Object> paramMap);
  
  public abstract ArrayList<Map<String, Object>> getRegionList(Map<String, Object> paramMap);
}

