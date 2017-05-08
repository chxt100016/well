package org.wella.houtai.sxgl.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface HoutaiSxglLoanInfoMapper
{
  public abstract ArrayList<Map<String, Object>> getLoanHisList(Map<String, Object> paramMap);
}