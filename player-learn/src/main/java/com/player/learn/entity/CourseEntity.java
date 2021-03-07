package com.player.learn.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "course")
public class CourseEntity {
    @ApiModelProperty(value = "主键")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    @Column(name = "t_id")
    private Long id;

    @ApiModelProperty(value = "课程名称")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "课程简介")
    @Column(name = "summary")
    private String summary;

    @ApiModelProperty(value = "教程程度")
    @Column(name = "level")
    private String level;

    @ApiModelProperty(value = "软件版本")
    @Column(name = "software_version")
    private String softwareVersion;

    @ApiModelProperty(value = "所需基础")
    @Column(name = "required_base")
    private String requiredBase;

    @ApiModelProperty(value = "适合人群")
    @Column(name = "suitable_for_people")
    private String suitableForPeople;

    @ApiModelProperty(value = "章节数量")
    @Column(name = "total_chapter")
    private String totalChapter;

    @ApiModelProperty(value = "学过的人")
    @Column(name = "learner")
    private String learner;

    @ApiModelProperty(value = "推出时间")
    @Column(name = "create_time")
    private String createTime;

    @ApiModelProperty(value = "讲师")
    @Column(name = "teacher")
    private String teacher;

    @ApiModelProperty(value = "讲师资质")
    @Column(name = "qualification")
    private String qualification;

    @ApiModelProperty(value = "课程描述")
    @Column(name = "course_describe")
    private String courseDescribe;

    @ApiModelProperty(value = "类别")
    @Column(name = "category")
    private String category;

    @ApiModelProperty(value = "大类")
    @Column(name = "classify")
    private String classify;

    @ApiModelProperty(value = "更新时间")
    @Column(name = "update_time")
    private Date updateTime;

    @ApiModelProperty(value = "源码")
    @Column(name = "code")
    private String code;

    @ApiModelProperty(value = "素材")
    @Column(name = "material")
    private String material;

    @ApiModelProperty(value = "板书")
    @Column(name = "blackboard")
    private String blackboard;

    @ApiModelProperty(value = "课程连接")
    @Column(name = "href")
    private String href;

    @ApiModelProperty(value = "课程图片地址")
    @Column(name = "img")
    private String img;

    @ApiModelProperty(value = "来源")
    @Column(name = "source")
    private String source;

    @ApiModelProperty(value = "本地图片地址")
    @Column(name = "local_img")
    private String localImg;
}
