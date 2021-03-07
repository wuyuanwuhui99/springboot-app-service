package com.player.learn.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "chapter")
public class ChapterEntity {
    @ApiModelProperty(value = "主键")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    @Column(name = "c_id")
    private Long id;

    @ApiModelProperty(value = "章节名称")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "访问人数")
    @Column(name = "visitors")
    private String visitors;

    @ApiModelProperty(value = "目录")
    @Column(name = "catalog")
    private String catalog;

    @ApiModelProperty(value = "播放地址")
    @Column(name = "url")
    private String url;

    @ApiModelProperty(value = "课程名称")
    @Column(name = "course_name")
    private String courseName;

    @ApiModelProperty(value = "课程id")
    @Column(name = "course_id")
    private String course_id;

    @ApiModelProperty(value = "章节链接")
    @Column(name = "href")
    private String href;

    @ApiModelProperty(value = "是否需要登录，0：立即观看，1：登录观看，2：支付观看")
    @Column(name = "need_pay")
    private String needPay;

    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_time")
    private Date create_time;

    @ApiModelProperty(value = "更新时间")
    @Column(name = "update_time")
    private Date update_time;
}
