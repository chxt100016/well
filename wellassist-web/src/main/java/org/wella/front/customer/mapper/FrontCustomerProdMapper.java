package org.wella.front.customer.mapper;

import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface FrontCustomerProdMapper
{
  public abstract Map<String, Object> getProdInfo(Map<String, Object> paramMap);
}
