package org.wella.front.customer.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface CustomerBackOrderMapper
{
  public abstract ArrayList<Map<String, Object>> getWaOrderList(Map<String, Object> paramMap);
  
  public abstract int getWaOrderListCount(Map<String, Object> paramMap);
  
  public abstract ArrayList<Map<String, Object>> getWaUserVehicleList(Map<String, Object> paramMap);
  
  public abstract int getWaUserVehicleListCount(Map<String, Object> paramMap);
  
  public abstract ArrayList<Map<String, Object>> getEstiList(Map<String, Object> paramMap);
  
  public abstract int getEstiListCount(Map<String, Object> paramMap);
}

