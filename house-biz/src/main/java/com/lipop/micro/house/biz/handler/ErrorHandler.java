package com.lipop.micro.house.biz.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述:
 * ${DESCRIPTION}
 *
 * @author zhonglunsheng
 * @create 2018-08-23 13:21
 */
@ControllerAdvice
public class ErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public String err500(HttpServletRequest request, Exception e){
        log.error(request.getRequestURL().toString());
        log.error(e.getMessage(),e);
        return "error/500";
    }
}
