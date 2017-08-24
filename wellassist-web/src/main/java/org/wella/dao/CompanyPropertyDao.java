package org.wella.dao;

import org.springframework.stereotype.Repository;
import org.wella.entity.CompanyBaseinfo;
import org.wella.entity.CompanyProperty;

import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/6/8.
 */
@Repository
public interface CompanyPropertyDao {

    List<CompanyProperty> list(Map params);

    int listCount(Map params);

}
