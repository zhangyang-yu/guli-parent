package com.zhangyang.guli.service.oss.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ToString
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssProperties {
    private String scheme;
    private String endpoint ;
    private  String accessKeyId ;
    private String accessKeySecret ;
    private String bucketName;
}
