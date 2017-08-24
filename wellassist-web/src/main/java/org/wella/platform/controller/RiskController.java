package org.wella.platform.controller;

import io.wellassist.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wella.dao.*;
import org.wella.entity.*;
import org.wella.platform.service.RiskService;
import org.wella.service.RiskScoreService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/platform/risk/")
@Controller
public class RiskController {

    @Autowired
    private CompanyBaseinfoDao companyBaseinfoDao;
    @Autowired
    private ManagerInfoDao managerInfoDao;
    @Autowired
    private CompanyManagementinfoDao companyManagementinfoDao;
    @Autowired
    private CompanyPropertyDao companyPropertyDao;
    @Autowired
    private CompanyValuationDao companyValuationDao;
    @Autowired
    private RiskScoreService riskScoreServiceImpl;
    @Autowired
    private RiskService riskServiceImpl;

    @RequestMapping("companyList")
    @ResponseBody
    public R companyList(@RequestParam Map<String,Object> param){
        Query query=new Query(param);
        List list=companyBaseinfoDao.list(query);
        int totalCount=companyBaseinfoDao.listCount(query);
        PageUtils pageUtils = new PageUtils(list,totalCount,query.getLimit(),query.getPage());
        return R.ok().put("page",pageUtils);
    }

    @RequestMapping("managerList")
    @ResponseBody
    public R managerList(@RequestParam Map<String,Object> param){
        Query query=new Query(param);
        List list=managerInfoDao.list(query);
        int totalCount=managerInfoDao.listCount(query);
        PageUtils pageUtils = new PageUtils(list,totalCount,query.getLimit(),query.getPage());
        return R.ok().put("page",pageUtils);
    }

    @RequestMapping("managementinfoList")
    @ResponseBody
    public R managementinfoList(@RequestParam Map<String,Object> param){
        Query query=new Query(param);
        List list=companyManagementinfoDao.list(query);
        int totalCount=companyManagementinfoDao.listCount(query);
        PageUtils pageUtils = new PageUtils(list,totalCount,query.getLimit(),query.getPage());
        return R.ok().put("page",pageUtils);
    }

    @RequestMapping("propertyList")
    @ResponseBody
    public R propertyList(@RequestParam Map<String,Object> param){
        Query query=new Query(param);
        List list=companyPropertyDao.list(query);
        int totalCount=companyPropertyDao.listCount(query);
        PageUtils pageUtils = new PageUtils(list,totalCount,query.getLimit(),query.getPage());
        return R.ok().put("page",pageUtils);
    }

    @RequestMapping("valuationList")
    @ResponseBody
    public R valuationList(@RequestParam Map<String,Object> param){
        Query query=new Query(param);
        List list=companyValuationDao.list(query);
        int totalCount=companyValuationDao.listCount(query);
        PageUtils pageUtils = new PageUtils(list,totalCount,query.getLimit(),query.getPage());
        return R.ok().put("page",pageUtils);
    }

    @RequestMapping("risk")
    public String risk(@RequestParam(value = "creditCode",required = true)String creditCode,Model model){
        model.addAttribute("creditCode",creditCode);
        return "views/platform/risk/risk.html";
    }

    @RequestMapping("getCompanyInfo")
    @ResponseBody
    public Map getCompanyInfo(@RequestParam("creditCode")String creditCode){
        Map result=new HashMap();
        CompanyBaseinfo companyBaseinfo = companyBaseinfoDao.getByCreditCode(creditCode);
        ManagerInfo legalman = managerInfoDao.getLegalmanByCreditcode(creditCode);
        List<ManagerInfo> otherManagers = managerInfoDao.getManagersExceptLegalmanByCreditcode(creditCode);
        result.put("code",0);
        result.put("companyBaseinfo", companyBaseinfo);
        result.put("legalman", legalman);
        result.put("otherManagers", otherManagers);
        return result;
    }

    @RequestMapping("getScoreDetail")
    @ResponseBody
    public Map getScoreDetail(@RequestParam("creditCode")String creditCode){
        Map result=new HashMap();
        List<Map<String, Object>> list = this.riskScoreServiceImpl.getRiskFactorScoreVO(creditCode);
        double totalScore = getTotalScore(list);
        result.put("code",0);
        result.put("factorsNum", list.size());
        result.put("totalScore", String.format("%.2f", totalScore));
        List<List<Map<String, Object>>> scoreMaps = partByTable(list);
        List<Map<String, Object>> companyManagementinfoList = scoreMaps.get(0);
        List<Map<String, Object>> companyPropertyList = scoreMaps.get(1);
        List<Map<String, Object>> companyValuationList = scoreMaps.get(2);
        List<Map<String, Object>> managerInfoList = scoreMaps.get(3);
        result.put("companyManagementinfo",getScoreDetailPartByTable(companyManagementinfoList));
        result.put("companyProperty",getScoreDetailPartByTable(companyPropertyList));
        result.put("companyValuation",getScoreDetailPartByTable(companyValuationList));
        result.put("managerInfo",getScoreDetailPartByTable(managerInfoList));
        return result;
    }

    private Map<String,Object> getScoreDetailPartByTable(List<Map<String, Object>> list){
        Map<String,Object> res=new HashMap();
        res.put("list",list);
        res.put("factorsNum",list.size());
        res.put("totalScore",String.format("%.2f", getTotalScore(list)));
        return res;
    }


    public double getTotalScore(List<Map<String,Object>> list){
        if (list.size() == 0){
            return 0;
        }
        double score=0;
        for (Map fm:list){
            score+=Double.valueOf((String) fm.get("score"));
        }
        return score;
    }

    public List<List<Map<String,Object>>> partByTable(List<Map<String,Object>> list){
        List<List<Map<String,Object>>> result=new ArrayList<List<Map<String, Object>>>();
        List<Map<String,Object>> companyManagementinfoList=new ArrayList<Map<String, Object>>();
        List<Map<String,Object>> companyPropertyList=new ArrayList<Map<String, Object>>();
        List<Map<String,Object>> companyValuationList=new ArrayList<Map<String, Object>>();
        List<Map<String,Object>> managerInfoList=new ArrayList<Map<String, Object>>();
        if (list.size()==0){
            return null;
        }
        for (Map<String,Object> fm:list){
            if (((String) fm.get("resourceTable")).equals("wa_company_managementinfo")){
                companyManagementinfoList.add(fm);
            } else if (((String) fm.get("resourceTable")).equals("wa_company_property")){
                companyPropertyList.add(fm);
            } else if (((String) fm.get("resourceTable")).equals("wa_company_valuation")){
                companyValuationList.add(fm);
            } else if (((String) fm.get("resourceTable")).equals("wa_manager_info")) {
                managerInfoList.add(fm);
            }
        }
        result.add(0,companyManagementinfoList);
        result.add(1,companyPropertyList);
        result.add(2,companyValuationList);
        result.add(3,managerInfoList);
        return result;
    }

    @RequestMapping("saveBasicinfoPage")
    public String saveBasicinfo(@RequestParam(value = "isSaveAll",required = false,defaultValue = "0") int isSaveAll,Model model){
        model.addAttribute("isSaveAll",isSaveAll);
        return "views/platform/risk/save/saveBasicinfo.html";
    }

    @RequestMapping("saveManagementinfoPage")
    public String saveManagementinfo(@RequestParam(value = "isSaveAll",required = false,defaultValue = "0") int isSaveAll,Model model){
        model.addAttribute("isSaveAll",isSaveAll);
        return "views/platform/risk/save/saveManagementinfo.html";
    }

    @RequestMapping("saveManagerinfoPage")
    public String saveManagerinfo(@RequestParam(value = "isSaveAll",required = false,defaultValue = "0") int isSaveAll,Model model){
        model.addAttribute("isSaveAll",isSaveAll);
        return "views/platform/risk/save/saveManagerinfo.html";
    }

    @RequestMapping("savePropertyPage")
    public String saveProperty(@RequestParam(value = "isSaveAll",required = false,defaultValue = "0") int isSaveAll,Model model){
        model.addAttribute("isSaveAll",isSaveAll);
        return "views/platform/risk/save/saveProperty.html";
    }

    @RequestMapping("saveValuationPage")
    public String savePage(@RequestParam(value = "isSaveAll",required = false,defaultValue = "0") int isSaveAll,Model model){
        model.addAttribute("isSaveAll",isSaveAll);
        return "views/platform/risk/save/saveValuation.html";
    }

    @RequestMapping("saveBasicinfo")
    @ResponseBody
    public R saveBasicinfo(@RequestBody CompanyBaseinfo companyBaseinfo){
        riskServiceImpl.saveCompanyBaseinfo(companyBaseinfo);
        return R.ok();
    }

    @RequestMapping("saveManagementinfo")
    @ResponseBody
    public R saveManagementinfo(@RequestBody CompanyManagementinfo companyManagementinfo){
        riskServiceImpl.saveCompanyManagementinfo(companyManagementinfo);
        return R.ok();
    }

    @RequestMapping("saveManagerinfo")
    @ResponseBody
    public R saveManagerinfo(@RequestBody ManagerInfo managerInfo){
        riskServiceImpl.saveManagerInfo(managerInfo);
        return R.ok();
    }

    @RequestMapping("saveProperty")
    @ResponseBody
    public R saveProperty(@RequestBody CompanyProperty companyProperty){
        riskServiceImpl.saveCompanyProperty(companyProperty);
        return R.ok();
    }

    @RequestMapping("saveValuation")
    @ResponseBody
    public R saveValuation(@RequestBody CompanyValuation companyValuation){
        riskServiceImpl.saveCompanyValuation(companyValuation);
        return R.ok();
    }
}
