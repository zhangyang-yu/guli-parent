package com.zhangyang.guli.service.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangyang.guli.service.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangyang.guli.service.edu.fromBean.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author zhangyang
 * @since 2020-12-15
 */
public interface TeacherService extends IService<Teacher> {

     Page<Teacher> pageByCondiation(Integer pageNum, Integer pageSize, TeacherQuery teacherQuery);
}
