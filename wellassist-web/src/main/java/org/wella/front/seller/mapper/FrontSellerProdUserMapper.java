package org.wella.front.seller.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface FrontSellerProdUserMapper
{
  public abstract ArrayList<Map<String, Object>> getUserProdList(Map<String, Object> paramMap);
}

