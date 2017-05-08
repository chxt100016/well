package org.wella.common.mapper;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface CommonMapper
{
  public abstract int deleteSingleBO(Map<String, Object> paramMap)
    throws Exception;
  
  public abstract int insertSingleBO(Map<String, Object> paramMap)
    throws Exception;
  
  public abstract ArrayList<Map<String, Object>> getTableAllFieldNames(Map<String, Object> paramMap)
    throws Exception;
  
  public abstract Map<String, Object> selectOneSingleBO(Map<String, Object> paramMap)
    throws Exception;
  
  public abstract ArrayList<Map<String, Object>> selectSingleBO(Map<String, Object> paramMap)
    throws Exception;
  
  public abstract ArrayList<Map<String, Object>> simpleSelectReturnList(Map<String, Object> paramMap);
  
  public abstract Map<String, Object> simpleSelectReturnObject(Map<String, Object> paramMap);
  
  public abstract int simpleSelectReturnInt(Map<String, Object> paramMap);
  
  public abstract String simpleSelectReturnString(Map<String, Object> paramMap);
  
  public abstract int updateSingleBO(Map<String, Object> paramMap)
    throws Exception;
  
  public abstract String getRegionDetailName(Map<String, Object> paramMap);
  
  public abstract ArrayList<Map<String, Object>> getChildRegionList(Map<String, Object> paramMap);
  
  public abstract Map<String, Object> getUserInfo(Map<String, Object> paramMap);
  
  public abstract int simpleUpdate(Map<String, Object> paramMap)
    throws Exception;
  
  public abstract void simpleInsertReturnVoid(Map<String, Object> paramMap)
    throws Exception;
}
