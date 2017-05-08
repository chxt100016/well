package org.wella.front.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface UserMapper
{
  public abstract ArrayList<Map<String, Object>> getUserInfo(Map<String, Object> paramMap);
}
