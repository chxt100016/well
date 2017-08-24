package org.wella.platform.service;

import org.wella.entity.*;

/**
 * Created by liuwen on 2017/5/18.
 * 后台通知管理服务接口
 */
public interface RiskService {

    void saveCompanyBaseinfo(CompanyBaseinfo companyBaseinfo);

    void saveManagerInfo(ManagerInfo managerInfo);

    void saveCompanyManagementinfo(CompanyManagementinfo companyManagementinfo);

    void saveCompanyProperty(CompanyProperty companyProperty);

    void saveCompanyValuation(CompanyValuation companyValuation);

    CompanyBaseinfo queryCompanyBaseinfo(long id);

    ManagerInfo queryManagerInfo(long id);

    CompanyManagementinfo queryCompanyManagementinfo(long id);

    CompanyProperty queryCompanyProperty(long id);

    CompanyValuation queryCompanyValuation(long id);

    void editCompanyBaseinfo(CompanyBaseinfo companyBaseinfo);

    void editManagerInfo(ManagerInfo managerInfo);

    void editCompanyManagementinfo(CompanyManagementinfo companyManagementinfo);

    void editCompanyProperty(CompanyProperty companyProperty);

    void editCompanyValuation(CompanyValuation companyValuation);

}
