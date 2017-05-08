package org.wella.houtai.order.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface HoutaiOrderOrderTransMapper
{
  public abstract ArrayList<Map<String, Object>> getZfQrList(Map<String, Object> paramMap);
  
  public abstract int getZfQrListCount(Map<String, Object> paramMap);
}
