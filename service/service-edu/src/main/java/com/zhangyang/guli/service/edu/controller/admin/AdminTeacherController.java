package com.zhangyang.guli.service.edu.controller.admin;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangyang.guli.service.base.result.R;
import com.zhangyang.guli.service.edu.entity.Teacher;
import com.zhangyang.guli.service.edu.fromBean.TeacherQuery;
import com.zhangyang.guli.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apiguardian.api.API;
import org.joda.time.ReadWritableDateTime;
import org.junit.jupiter.api.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.Date;
import java.util.List;

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
    @ApiOperation(value = "查询所有的教师")
    @GetMapping("/list")
    public R getTeacherList()
    {
        System.out.println("111qq1dadsd11");
        List<Teacher> list = teacherService.list();
        if(list==null||list.size()==0)
        {
            return  R.error().message("查询失败");
        }
        return R.ok().data("items",list);

    }

    @ApiOperation(value = "查询删除指定教师")
    @DeleteMapping("/delete/{id}")
    public R delete(@ApiParam(value = "用户的id",required = true) @PathVariable Integer id)
    {
        boolean b = teacherService.removeById(id);
        if(b)
        {
            return R.ok().data("item",b);
        }
        return R.error().message("id不存在");
    }
    @ApiModelProperty(value = "分页查询数据")
    @GetMapping("/page/{pageNum}/{pageSize}")
    public  R page(@ApiParam(value = "当前页码",required = true)@PathVariable Integer pageNum,@ApiParam(value = "每页的数据数量",required = true)@PathVariable Integer  pageSize)
    {
        Page<Teacher> teacherPage = new Page<>(pageNum,pageSize);

        Page<Teacher> page = teacherService.page(teacherPage);
        if(page==null||page.getSize()==0)
        {
            return  R.error().message("查询数据失败");
        }
        return R.ok().data("item",page);

    }
    /**
     * 带有查询条件的分页
     */
     @ApiModelProperty(value = "带有查询条件的分页")
     @PostMapping("/pageContration/{pageNum}/{pageSize}")
     public R fingPage(@ApiParam(value = "当前页码",required = true)@PathVariable Integer pageNum, @ApiParam(value = "每页的数据数量",required = true)@PathVariable Integer  pageSize, TeacherQuery teacherQuery)
     {
         Page<Teacher> page =  teacherService.pageByCondiation(pageNum,pageSize,teacherQuery);
         if(page==null||page.getSize()==0)
         {
             R.error().message("查询失败");
         }
      return    R.ok().data("item",page);
     }
   /**
    * 保存记录
    */
    @ApiModelProperty(value = "保存记录")
    @PostMapping("/save")
    public  R save(@RequestBody Teacher teacher)
   {
       teacher.setJoinDate(new Date());
       teacher.setAvatar("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2672105432,110298061&fm=26&gp=0.jpg");
       boolean save = teacherService.save(teacher);
       if(save)
       {
         return   R.ok().data("boolean",save);
       }
       return   R.error().message("添加失败");
   }
   @ApiModelProperty(value = "查询指定的teacher")
    @GetMapping("/get/{id}")
    public  R getTeacher(@ApiParam(value = "指定id",required = true) @PathVariable Integer id)
   {
       Teacher byId = teacherService.getById(id);
       System.out.println(byId);
       if(byId!=null)
       {
          return R.ok().data("item",byId);
       }
       return  R.error().message("查询失败");
   }
    @ApiModelProperty(value = "更新用户信息")
    @PostMapping("/update")
    public  R update(@RequestBody Teacher teacher)
   {
       boolean b = teacherService.updateById(teacher);
       if(b)
       {
         return   R.ok().data("item",b);
       }
       return   R.error().message("跟新失败");
   }

}

