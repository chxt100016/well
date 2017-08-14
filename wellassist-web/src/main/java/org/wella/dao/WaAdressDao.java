package org.wella.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/8/14.
 */
public interface WaAdressDao {



    Integer addAddress(Map map);

    List<Map<String,Object>> selectAddress(Long userId);


    Integer updateDefault(Long id);
}
