package com.zhangyang.guli.service.edu.config;

import com.netflix.loadbalancer.RandomRule;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class openFeignConfg {
    /**
     * 配置负载均衡的
     */
//    @Bean
//    public RandomRule randomRule()
//    {
//        return new   RandomRule();
//    }
    @Bean
    public Retryer retryer()
    {
        return new  Retryer.Default();
    }

}
