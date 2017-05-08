package org.wella.houtai.order.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface OrderMapper
{
  public abstract ArrayList<Map<String, Object>> getOrderList(Map<String, Object> paramMap);
  
  public abstract int getOrderListCount(Map<String, Object> paramMap);
  
  public abstract ArrayList<Map<String, Object>> getOrderInfoList(Map<String, Object> paramMap);
}
