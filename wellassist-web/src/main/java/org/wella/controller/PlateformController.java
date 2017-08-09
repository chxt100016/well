package org.wella.controller;

import io.wellassist.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wella.service.impl.PlatformServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by liuwen on 2017/5/17.
 */
@Controller
@RequestMapping("/platform/")
public class PlateformController {


    @Autowired
    private PlatformServiceImpl platformService;




    /**
     * 模糊查询所买方
     * @param request
     * @param response
     */
    @RequestMapping("customerList")
    public void customerList(HttpServletRequest request, HttpServletResponse response){

    }



    @ResponseBody
    @RequestMapping("insertCustomer")
    public R insertCustomer(@RequestParam Map<String,Object> map){
        platformService.insertCustomer(map);
        return new R();

    }





}
