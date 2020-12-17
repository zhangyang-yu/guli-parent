package com.zhangyang.guli.service.base.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.zhangyang.guli.service.*.mapper")//放在这里，以后只要继承这个模块的微服都不要在写这个扫描了
public class MyBatisPlusConfig {
    /**
     * 创建乐观锁拦截器
     * 拦截器的本质就是在执行sql之前把sql语句拦截下来，他会在sql语句后面拼接条件
     *
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 配置分页拦截器
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


}
