package org.wella.front.sender.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface FrontSenderVehicleGrabMapper
{
  public abstract ArrayList<Map<String, Object>> getQdList(Map<String, Object> paramMap);
  
  public abstract int getQdListCount(Map<String, Object> paramMap);
  
  public abstract Map<String, Object> getQdInfo(Map<String, Object> paramMap);
}
