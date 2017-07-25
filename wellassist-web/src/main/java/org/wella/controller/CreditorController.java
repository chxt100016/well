package org.wella.controller;

import io.wellassist.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wella.service.CreditorService;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/21.
 */

@Controller
@RequestMapping(value = "/creditor/")
public class CreditorController {

    @Autowired
    private CreditorService creditorServiceImpl;

    @RequestMapping("creditors")
    @ResponseBody
    public R creditors(){
        List<Map<String,Object>> creditors=creditorServiceImpl.findCreditorList();
        return R.ok().put("creditors",creditors);
    }
}
