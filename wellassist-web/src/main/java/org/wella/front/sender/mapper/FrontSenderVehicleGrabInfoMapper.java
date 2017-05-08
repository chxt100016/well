package org.wella.front.sender.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface FrontSenderVehicleGrabInfoMapper
{
  public abstract ArrayList<Map<String, Object>> getGrabInfoList(Map<String, Object> paramMap);
}
