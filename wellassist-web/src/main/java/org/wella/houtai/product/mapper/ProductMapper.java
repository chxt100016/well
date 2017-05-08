package org.wella.houtai.product.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface ProductMapper
{
  public abstract ArrayList<Map<String, Object>> getProductList(Map<String, Object> paramMap);
  
  public abstract int getProductListCount(Map<String, Object> paramMap);
}
