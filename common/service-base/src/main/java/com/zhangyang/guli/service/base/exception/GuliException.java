package com.zhangyang.guli.service.base.exception;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zhangyang.guli.service.base.result.ResultCodeEnum;
import lombok.Data;

import javax.management.relation.RelationSupport;

/**
 * 自定义一个异常
 */
@Data
public class GuliException extends RuntimeException {
     //存储异常的信息
    private Boolean success;//
    private Integer code;//状态码
    private String message;//异常信息
    //提供多个构造器，这样异常信息具体怎么传有自己决定
    public  GuliException(ResultCodeEnum resultCodeEnum)
    {
      super(resultCodeEnum.getMessage());//把异常信息传给runtimeException，这样以后可以使用e.getMessage()打印出现在设置的异常信息
        this.success= resultCodeEnum.getSuccess();
        this.code=resultCodeEnum.getCode();
        this.message=resultCodeEnum.getMessage();
    }
    public  GuliException(Boolean success,Integer code,String message)
    {
        this.success= success;
        this.code=code;
        this.message=message;
    }
}
