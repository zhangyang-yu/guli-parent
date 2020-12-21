package com.zhangyang.guli.service.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.zhangyang.guli.service.base.exception.GuliException;
import com.zhangyang.guli.service.base.result.R;
import com.zhangyang.guli.service.base.result.ResultCodeEnum;
import com.zhangyang.guli.service.oss.config.OssProperties;
import com.zhangyang.guli.service.oss.service.FileControllerService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
@Service
public class FileControllerServiceImpl implements FileControllerService {
    @Autowired
    private OssProperties ossProperties;
    private   String scheme;
    private   String endpoint ;
    private   String accessKeyId ;
    private   String accessKeySecret ;
    private   String bucketName;
    @PostConstruct//初始化参数的注解，让这个方法在构造函数执行结束后就执行
    /**
     * 数据初始化
     */
    public  void  init()
    {
     scheme=ossProperties.getScheme();
     endpoint=ossProperties.getEndpoint();
     accessKeyId=ossProperties.getAccessKeyId();
     accessKeySecret=ossProperties.getAccessKeySecret();
     bucketName=ossProperties.getBucketName();
    }

    /**
     * 文件上传
     * @param file
     * @param module
     * @return
     */
    @Override
    public String onLoadFile(MultipartFile file, String module) {
        OSS ossClient = new OSSClientBuilder().build(scheme+endpoint, accessKeyId, accessKeySecret);
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            String dateTime = new DateTime().toString("/yyyy/MM/dd/");
            String filePath= module+dateTime+ UUID.randomUUID().toString().replace("-","")+ file.getOriginalFilename();
            ossClient.putObject(bucketName, filePath, inputStream);
            ossClient.shutdown();
            return  scheme+bucketName+"."+endpoint+"/"+filePath;

        } catch (Exception e) {
            //无论发送什么异常，都要告诉前端发生线程的原因
           throw new GuliException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }


    }

    /**
     * 文件删除
     * @param uploadFilePath
     * @param module
     * @return
     */
    @Override
    public boolean deleteFile(String uploadFilePath, String module) {
        String filePath=uploadFilePath.substring(uploadFilePath.indexOf("/"+module)+1);
        OSS ossClient = new OSSClientBuilder().build(scheme+endpoint, accessKeyId, accessKeySecret);
        ossClient.deleteObject(bucketName, filePath);
        ossClient.shutdown();
        return true;
    }
}
