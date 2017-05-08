package org.wella.front.sender.mapper;

import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface SenderLoginMapper
{
  public abstract Map<String, Object> get_user_count(Map<String, Object> paramMap);
  
  public abstract int insertWaUser(Map<String, Object> paramMap);
  
  public abstract int insertWaUserInfo(Map<String, Object> paramMap);
}
