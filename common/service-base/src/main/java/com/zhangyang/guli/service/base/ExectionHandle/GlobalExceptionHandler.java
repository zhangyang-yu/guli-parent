package com.zhangyang.guli.service.base.ExectionHandle;

import com.zhangyang.guli.service.base.exception.GuliException;
import com.zhangyang.guli.service.base.result.R;
import com.zhangyang.guli.service.base.result.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 本质是基于aop面向切面编程
 * 只要发生异常就拦截处理
 */
@RestControllerAdvice
@Slf4j   //获取书写日志的类
public class GlobalExceptionHandler {
    @ExceptionHandler(GuliException.class)
    public R returnExection(GuliException e)
    {
        log.error(e.getMessage());//记录日志,把发生的错误打印到日志上
        return R.error().code(e.getCode()).message(e.getMessage()).success(e.getSuccess());
    }
    @ExceptionHandler(Exception.class)
    public R returnExection(Exception e)
    {
        log.error(e.getMessage());//记录日志,把发生的错误打印到日志上
        return R.setResult(ResultCodeEnum.UNKNOWN_REASON);
    }
}
