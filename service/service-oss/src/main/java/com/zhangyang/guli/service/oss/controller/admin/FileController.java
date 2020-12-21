package com.zhangyang.guli.service.oss.controller.admin;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.zhangyang.guli.service.base.result.R;
import com.zhangyang.guli.service.base.result.ResultCodeEnum;
import com.zhangyang.guli.service.oss.config.OssProperties;
import com.zhangyang.guli.service.oss.service.FileControllerService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.UUID;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/admin/oss/file")
@Api(tags = "文件管理模块")
public class FileController {
   @Autowired
   private FileControllerService fileControllerService;
   @Value("${server.port}")
   Integer port;
    /**
     * 上传文件
     */
    @ApiOperation(value = "处理文件上传")
    @PostMapping("upload")
    public R upLoadFile(MultipartFile file, @ApiParam(value = "文件路径",required = true) @RequestParam(value = "module") String module)
    {
        String path= fileControllerService.onLoadFile(file, module);
        if(path==null){
            return  R.setResult(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }
        return R.ok().data("path",path);

    }
    /**
     * 删除文件
     * @ApiParam(value = "要删除文件的路径",required = true)
     *      *
     *
     */

    @ApiOperation(value = "把文件从服务器上删除")
    @DeleteMapping("delete")
    public  R deleteFile( @ApiParam(value = "要删除文件的路径",required = true) @RequestParam(value = "filepath",required = true) String filepath, @ApiParam(value = "要删除文件的目录",required = true) @RequestParam(value = "module",required = true) String module)
    {
    boolean b= fileControllerService.deleteFile(filepath,module);
    if(b){
        return  R.ok().message("删除成功");
    }
    return  R.error().message("删除失败");
    }
    /**
     * 远程调用的测试方法
     */
    @GetMapping("/test")
    public  R test1( String path)
    {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("port:"+port);
        return  R.ok().message("这个是远程调用");
    }

}
