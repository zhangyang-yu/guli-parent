package com.zhangyang.guli.service.edu.controller.api;


import com.zhangyang.guli.service.edu.entity.Teacher;
import com.zhangyang.guli.service.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author zhangyang
 * @since 2020-12-15
 */
@RestController
@RequestMapping("/api/edu/teacher")
public class ApiTeacherController {

    @Autowired
    private TeacherService teacherService;
    @GetMapping("/list")
    public List<Teacher> getTeacherList()
    {
        return teacherService.list();
    }

}

