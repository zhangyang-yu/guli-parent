package com.zhangyang.guli.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangyang.guli.service.edu.entity.Teacher;
import com.zhangyang.guli.service.edu.fromBean.TeacherQuery;
import com.zhangyang.guli.service.edu.mapper.TeacherMapper;
import com.zhangyang.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author zhangyang
 * @since 2020-12-15
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param teacherQuery
     * @return
     */
    @Override
    public Page<Teacher> pageByCondiation(Integer pageNum, Integer pageSize, TeacherQuery teacherQuery) {
        Page<Teacher> teacherPage = new Page<>(pageNum,pageSize);
         if(teacherQuery==null)
         {
             return  baseMapper.selectPage(teacherPage,null);
         }
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String joinDateBegin = teacherQuery.getJoinDateBegin();
        String joinDateEnd = teacherQuery.getJoinDateEnd();
        if(!StringUtils.isEmpty(name))
        {
          teacherQueryWrapper.likeRight("name",name);
        }
        if(level!=null){
            teacherQueryWrapper.eq("level",level);
        }if(!StringUtils.isEmpty(joinDateBegin))
        {
            teacherQueryWrapper.ge("join_date",joinDateBegin);
        }
        if(!StringUtils.isEmpty(joinDateEnd))
        {
            teacherQueryWrapper.le("join_date",joinDateEnd);
        }
        return  baseMapper.selectPage(teacherPage,teacherQueryWrapper);

    }

    /**
     * 批量删除
     * @param teacherIdArray
     * @return
     */

}
