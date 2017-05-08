package org.wella.houtai.sxsz.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface HoutaiSxszDegreeMapper
{
  public abstract ArrayList<Map<String, Object>> getUserDengjiList(Map<String, Object> paramMap);
  
  public abstract int getUserDengjiListCount(Map<String, Object> paramMap);
}

