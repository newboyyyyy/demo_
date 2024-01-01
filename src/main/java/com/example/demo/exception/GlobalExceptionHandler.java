package com.example.demo.exception;

import com.example.demo.constant.ReturnCode;
import com.example.demo.controller.bean.ResultDTO;
import com.example.demo.controller.bean.base.RestRequest;
import com.example.demo.controller.bean.base.RestResponse;
import com.example.demo.utils.ResponseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = "com.example.demo.controller")
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RestResponse error(HttpServletRequest request , Exception e){
        log.error("異常:" , e);
        return ResponseFactory.createErrorResponse(ReturnCode.FAIL.getCode(), ReturnCode.FAIL.getValue());
    }

    @ExceptionHandler(BooknowException.class)
    @ResponseBody
    public RestResponse booknowError(HttpServletRequest request ,BooknowException e){
        return ResponseFactory.createErrorResponse(ReturnCode.FAIL.getValue(),e.toString());
    }



}
