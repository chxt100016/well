package org.wella.dao;

import org.wella.entity.Zorder;

/**
 * Created by Administrator on 2017/6/2.
 */
public interface ZorderDao {

    void createZorder(Zorder zorder);

    void updateByPrimaryKey(Zorder zorder);

}
