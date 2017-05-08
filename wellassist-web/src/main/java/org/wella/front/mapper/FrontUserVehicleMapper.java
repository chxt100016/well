package org.wella.front.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface FrontUserVehicleMapper
{
  public abstract ArrayList<Map<String, Object>> getRecentQdList();
  
  public abstract Map<String, Object> getVehicleInfo(Map<String, Object> paramMap);
}