package org.wella.front.customer.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface FrontCustomerProdUserMapper
{
  public abstract ArrayList<Map<String, Object>> getUserProdList(Map<String, Object> paramMap);
}
