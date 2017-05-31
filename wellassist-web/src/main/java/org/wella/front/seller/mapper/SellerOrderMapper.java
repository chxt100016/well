package org.wella.front.seller.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface SellerOrderMapper
{
  public abstract ArrayList<Map<String, Object>> getWaProdList(Map<String, Object> paramMap);
  
  public abstract int getWaProdListCount(Map<String, Object> paramMap);
  
  public abstract ArrayList<Map<String, Object>> getWaOrderList(Map<String, Object> paramMap);
  
  public abstract int getWaOrderListCount(Map<String, Object> paramMap);
  
  public abstract ArrayList<Map<String, Object>> getEstiList(Map<String, Object> paramMap);
  
  public abstract int getEstiListCount(Map<String, Object> paramMap);
  
  public abstract ArrayList<Map<String, Object>> getUserOrderList(Map<String, Object> paramMap);
  
  public abstract int getUserOrderListCount(Map<String, Object> paramMap);

}
