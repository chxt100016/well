package org.wella.houtai.notice.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface HoutaiNoticeMapper
{
  public abstract ArrayList<Map<String, Object>> getNoticeList(Map<String, Object> paramMap);
  
  public abstract int getNoticeListCount(Map<String, Object> paramMap);
}
