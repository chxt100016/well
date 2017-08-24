package org.wella.dao;

import org.springframework.stereotype.Repository;
import org.wella.entity.CompanyBaseinfo;
import org.wella.entity.CompanyValuation;

import java.util.List;
import java.util.Map;

@Repository
public interface CompanyValuationDao {

    List<CompanyValuation> list(Map params);

    int listCount(Map params);

}
