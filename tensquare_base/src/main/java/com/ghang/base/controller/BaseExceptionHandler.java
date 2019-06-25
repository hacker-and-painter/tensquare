package com.ghang.base.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/10 16:48
 */
@RestControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Result exception(Exception e) {
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
