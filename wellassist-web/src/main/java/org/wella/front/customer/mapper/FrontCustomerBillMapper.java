package org.wella.front.customer.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface FrontCustomerBillMapper
{
  public abstract ArrayList<Map<String, Object>> getFapiaoList(Map<String, Object> paramMap);
  
  public abstract int getFapiaoListCount(Map<String, Object> paramMap);
}

