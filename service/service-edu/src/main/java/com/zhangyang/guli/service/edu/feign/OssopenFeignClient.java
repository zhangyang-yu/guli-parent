package com.zhangyang.guli.service.edu.feign;

import com.zhangyang.guli.service.base.result.R;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-oss")
public interface OssopenFeignClient {
    @DeleteMapping("/admin/oss/file/delete")
    public R deleteFile(@ApiParam(value = "要删除文件的路径",required = true) @RequestParam(value = "filepath",required = true) String filepath, @ApiParam(value = "要删除文件的目录",required = true) @RequestParam(value = "module",required = true) String module);
    @GetMapping("/admin/oss/file/test")
    public  R test1(@RequestParam String path);

}
