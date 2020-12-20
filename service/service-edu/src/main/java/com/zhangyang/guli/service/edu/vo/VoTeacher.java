package com.zhangyang.guli.service.edu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "可以修改的教师信息")
public class VoTeacher {
    @ApiModelProperty(value = "讲师id")
    private String id;
    @ApiModelProperty(value = "讲师姓名")
    private String name;
    @ApiModelProperty(value = "入驻时间")
    private Date joinDate;
    @ApiModelProperty(value = "排序")
    private Integer sort;
    @ApiModelProperty(value = "讲师简介")
    private String intro;
    @ApiModelProperty(value = "讲师资历,一句话说明讲师")
    private String career;
    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;
}
