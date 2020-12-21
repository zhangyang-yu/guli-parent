package com.zhangyang.guli.service.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileControllerService {
    String onLoadFile(MultipartFile file,String module);

    boolean deleteFile(String filepath, String module);
}
