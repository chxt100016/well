package org.wella.front.sender.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface FrontSenderOrderMapper
{
  public abstract ArrayList<Map<String, Object>> getWaUserVehicleList(Map<String, Object> paramMap);
  
  public abstract int getWaUserVehicleListCount(Map<String, Object> paramMap);
}
