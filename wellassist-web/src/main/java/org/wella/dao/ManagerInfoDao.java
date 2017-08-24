package org.wella.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wella.entity.Bankcard;
import org.wella.entity.ManagerInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by liuwen on 2017/6/8.
 */
@Repository
public interface ManagerInfoDao {

    List<ManagerInfo> list(Map param);

    int listCount(Map param);

    ManagerInfo getLegalmanByCreditcode(@Param("creditCode") String creditCode);

    List<ManagerInfo> getManagersExceptLegalmanByCreditcode(@Param("creditCode")String creditCode);
}
