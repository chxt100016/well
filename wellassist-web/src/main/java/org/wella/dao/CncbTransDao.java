package org.wella.dao;

import org.springframework.stereotype.Repository;
import org.wella.entity.Bankcard;
import org.wella.entity.CncbTrans;

import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/6/8.
 */
@Repository
public interface CncbTransDao {

    int create(CncbTrans cncbTrans);

    List<CncbTrans> queryList(Map<String,Object> conditions);

    void update(Map update);
}
