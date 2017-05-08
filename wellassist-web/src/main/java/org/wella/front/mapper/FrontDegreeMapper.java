package org.wella.front.mapper;

import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface FrontDegreeMapper
{
  public abstract Map<String, Object> getDegreeInfo(Map<String, Object> paramMap);
}
