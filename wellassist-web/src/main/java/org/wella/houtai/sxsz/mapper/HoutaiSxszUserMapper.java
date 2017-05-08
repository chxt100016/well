package org.wella.houtai.sxsz.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface HoutaiSxszUserMapper
{
  public abstract ArrayList<Map<String, Object>> getYhDengjiList(Map<String, Object> paramMap);
  
  public abstract int getYhDengjiListCount(Map<String, Object> paramMap);
}

