package com.zhangyang.guli.service.edu.service.impl;

import com.zhangyang.guli.service.edu.entity.Comment;
import com.zhangyang.guli.service.edu.mapper.CommentMapper;
import com.zhangyang.guli.service.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author zhangyang
 * @since 2020-12-15
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
