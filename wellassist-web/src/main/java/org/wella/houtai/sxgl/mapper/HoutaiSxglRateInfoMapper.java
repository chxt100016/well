package org.wella.houtai.sxgl.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface HoutaiSxglRateInfoMapper
{
  public abstract ArrayList<Map<String, Object>> getLixiHisList(Map<String, Object> paramMap);
}
