package org.wella.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wella.dao.ProdDao;

/**
 * Created by liuwen on 2017/5/18.
 */
@Service("productManageServiceImpl")
public class ProductManageServiceImpl {
    @Autowired
    private ProdDao prodDao;


}
