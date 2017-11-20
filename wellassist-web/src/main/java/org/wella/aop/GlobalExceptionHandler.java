package org.wella.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * Created by Administrator on 2017/11/16.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(NullPointerException.class)
    public String nullPointer(NullPointerException e){
        return "views/500.jsp";
    }

}
