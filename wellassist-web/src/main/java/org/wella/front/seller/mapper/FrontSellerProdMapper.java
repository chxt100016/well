package org.wella.front.seller.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface FrontSellerProdMapper
{
  public abstract ArrayList<Map<String, Object>> getAddUserProdList(Map<String, Object> paramMap);
  
  public abstract int getAddUserProdListCount(Map<String, Object> paramMap);
}

