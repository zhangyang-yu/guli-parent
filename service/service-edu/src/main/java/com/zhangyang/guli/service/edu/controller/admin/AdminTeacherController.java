package com.zhangyang.guli.service.edu.controller.admin;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangyang.guli.service.base.result.R;
import com.zhangyang.guli.service.edu.entity.Teacher;
import com.zhangyang.guli.service.edu.feign.OssopenFeignClient;
import com.zhangyang.guli.service.edu.fromBean.TeacherQuery;
import com.zhangyang.guli.service.edu.service.TeacherService;
import com.zhangyang.guli.service.edu.vo.VoTeacher;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apiguardian.api.API;
import org.joda.time.ReadWritableDateTime;
import org.junit.jupiter.api.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author zhangyang
 * @since 2020-12-15
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/edu/teacher")
@Api(value = "教师操作的登录层")
public class AdminTeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private OssopenFeignClient ossopenFeignClient;
    @ApiOperation(value = "查询所有的教师")
    @GetMapping("/list")
    public R getTeacherList()
    {

        List<Teacher> list = teacherService.list();
        if(list==null||list.size()==0)
        {
            return  R.error().message("查询失败");
        }
        return R.ok().data("items",list);

    }

    @ApiOperation(value = "查询删除指定教师")
    @DeleteMapping("/delete/{id}")
    public R delete(@ApiParam(value = "用户的id",required = true) @PathVariable String id)
    {
        //在删除记录前把oss服务器上的用户对应的头像资源删除
        Teacher byId = teacherService.getById(id);
        ossopenFeignClient.deleteFile(byId.getAvatar(),"avatar");
        boolean b = teacherService.removeById(id);
        if(b)
        {
            return R.ok().data("item",b);
        }
        return R.error().message("id不存在");
    }
    /**
     * 带有查询条件的分页
     */
     @ApiModelProperty(value = "带有查询条件的分页")
     @GetMapping("/pageContration/{pageNum}/{pageSize}")
     public R fingPage(@ApiParam(value = "当前页码",required = true)@PathVariable Integer pageNum, @ApiParam(value = "每页的数据数量",required = true)@PathVariable Integer  pageSize, TeacherQuery teacherQuery)
     {
         System.out.println(teacherQuery);
         Page<Teacher> page =  teacherService.pageByCondiation(pageNum,pageSize,teacherQuery);
         if(page==null||page.getSize()==0)
         {
             R.error().message("查询失败");
         }
      return    R.ok().data("items",page);
     }
   /**
    * 保存记录
    */
    @ApiModelProperty(value = "保存记录")
    @PostMapping("/save")
    public  R save(@RequestBody Teacher teacher)
   {
       if(StringUtils.isEmpty(teacher.getAvatar())){
           teacher.setAvatar("http://www.atguigu.com/teacher/new/liyuting.jpg");//设置默认头像地址
       }
       boolean save = teacherService.save(teacher);
       if(save)
       {
         return   R.ok().data("boolean",save);
       }
       return   R.error().message("添加失败");
   }
   @ApiModelProperty(value = "查询指定的teacher")
    @GetMapping("/get/{id}")
    public  R getTeacherById(@ApiParam(value = "指定id",required = true) @PathVariable String id)
   {
       Teacher byId = teacherService.getById(id);
       if(byId!=null)
       {

           VoTeacher voTeacher=new VoTeacher(byId.getId(),byId.getName(),byId.getJoinDate(),byId.getSort(),byId.getIntro(),byId.getCareer(),byId.getLevel(),byId.getAvatar());
          return R.ok().data("item",voTeacher);
       }
       return  R.error().message("查询失败");
   }
    @ApiModelProperty(value = "更新用户信息")
    @PostMapping("/update")
    public  R update(@ApiParam(value = "修改后的教师信息")@RequestBody Teacher teacher,@ApiParam(value = "修改前教师的图片资源") @RequestParam String filePath)
   {
       String defaultHead="http://www.atguigu.com/teacher/new/liyuting.jpg";
       if(filePath!=null&&!filePath.equals(teacher.getAvatar())&&!filePath.equals(defaultHead))
       {
           ossopenFeignClient.deleteFile(filePath,"avatar");
       }
       boolean b = teacherService.updateById(teacher);
       if(b)
       {
         return   R.ok().data("item",b);
       }
       return   R.error().message("跟新失败");
   }

    @ApiModelProperty(value = "批量删除数记录")
    @DeleteMapping("/batchdelete")
    public  R batchDelete(@ApiParam(value = "批量删除的教师id") @RequestParam List<String> multipleSelection)
    {
        boolean b = teacherService.removeByIds(multipleSelection);
        if(b)
        {
            return  R.ok();
        }
        return   R.error().message("跟新失败");
    }

    @PostMapping("/test")
    public  R test(@ApiParam(value = "测试参数") @RequestBody List<Teacher> multipleSelection )
    {

        for (int i = 0; i < multipleSelection.size(); i++) {
            System.out.println(multipleSelection.get(i));
        }
        return   R.ok().message("haode");
    }
    @PostMapping("/test1")
    public  R test1(@ApiParam(value = "测试参数") @RequestBody String[] multipleSelection )
    {

        for (int i = 0; i < multipleSelection.length; i++) {
            System.out.println(multipleSelection[i]);
        }
        return   R.ok().message("haode");
    }

    @GetMapping("/test2")
    public  R Feigntest( )
    {
        R r = ossopenFeignClient.test1("这个是一个路径");
        return  r;
    }
}

