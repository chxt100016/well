package org.wella.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liuwen on 2017/5/17.
 */
@Controller
@RequestMapping("/platform/")
public class PlateformController {





    /**
     * 模糊查询所买方
     * @param request
     * @param response
     */
    @RequestMapping("customerList")
    public void customerList(HttpServletRequest request, HttpServletResponse response){

    }
}
