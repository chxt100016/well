package org.wella.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wella.entity.Bankcard;
import org.wella.entity.CompanyBaseinfo;

import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/6/8.
 */
@Repository
public interface CompanyBaseinfoDao {

    List<CompanyBaseinfo> list(Map params);

    int listCount(Map params);

    CompanyBaseinfo getByCreditCode(@Param("creditCode") String creditCode);
}
