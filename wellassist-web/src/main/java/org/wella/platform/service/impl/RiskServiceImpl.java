package org.wella.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wella.dao.*;
import org.wella.entity.*;
import org.wella.platform.service.RiskService;

/**
 * Created by liuwen on 2017/5/18.
 */
@Service("riskServiceImpl")
public class RiskServiceImpl implements RiskService{

    @Autowired
    private CompanyBaseinfoDao companyBaseinfoDao;
    @Autowired
    private CompanyManagementinfoDao companyManagementinfoDao;
    @Autowired
    private CompanyValuationDao companyValuationDao;
    @Autowired
    private CompanyPropertyDao companyPropertyDao;
    @Autowired
    private ManagerInfoDao managerInfoDao;

    @Override
    public void saveCompanyBaseinfo(CompanyBaseinfo companyBaseinfo) {
        companyBaseinfoDao.save(companyBaseinfo);
    }

    @Override
    public void saveManagerInfo(ManagerInfo managerInfo) {
        managerInfoDao.save(managerInfo);
    }

    @Override
    public void saveCompanyManagementinfo(CompanyManagementinfo companyManagementinfo) {
        companyManagementinfoDao.save(companyManagementinfo);
    }

    @Override
    public void saveCompanyProperty(CompanyProperty companyProperty) {
        companyPropertyDao.save(companyProperty);
    }

    @Override
    public void saveCompanyValuation(CompanyValuation companyValuation) {
        companyValuationDao.save(companyValuation);
    }

    @Override
    public CompanyBaseinfo queryCompanyBaseinfo(long id) {
        return companyBaseinfoDao.query(id);
    }

    @Override
    public ManagerInfo queryManagerInfo(long id) {
        return managerInfoDao.query(id);
    }

    @Override
    public CompanyManagementinfo queryCompanyManagementinfo(long id) {
        return companyManagementinfoDao.query(id);
    }

    @Override
    public CompanyProperty queryCompanyProperty(long id) {
        return companyPropertyDao.query(id);
    }

    @Override
    public CompanyValuation queryCompanyValuation(long id) {
        return companyValuationDao.query(id);
    }

    @Override
    public void editCompanyBaseinfo(CompanyBaseinfo companyBaseinfo) {
        companyBaseinfo.update(companyBaseinfo);
    }

    @Override
    public void editManagerInfo(ManagerInfo managerInfo) {
        managerInfoDao.update(managerInfo);
    }

    @Override
    public void editCompanyManagementinfo(CompanyManagementinfo companyManagementinfo) {
        companyManagementinfoDao.update(companyManagementinfo);
    }

    @Override
    public void editCompanyProperty(CompanyProperty companyProperty) {
        companyPropertyDao.update(companyProperty);
    }

    @Override
    public void editCompanyValuation(CompanyValuation companyValuation) {
        companyValuationDao.update(companyValuation);
    }
}
