package com.zhangyang.guli.service.base.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class MetaHandle implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue("gmtCreate",new Date());
        metaObject.setValue("gmtModified",new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("gmtModified",new Date());
    }
}
