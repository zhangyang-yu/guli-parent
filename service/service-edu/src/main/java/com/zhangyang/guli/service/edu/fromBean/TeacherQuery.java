package com.zhangyang.guli.service.edu.fromBean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Data
@ToString
@ApiModel(value="Teacher查询条件对象", description="讲师的查询条件对象")
public class TeacherQuery {
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "讲师姓名")
    private String name;
    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;
    @ApiModelProperty(value = "查修入驻的开始时间")
    private String joinDateBegin;
    @ApiModelProperty(value = "查修入驻的结束时间")
    private String joinDateEnd;
}
