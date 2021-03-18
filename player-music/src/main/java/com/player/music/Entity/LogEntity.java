package com.player.music.Entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "log")
public class LogEntity {

    @ApiModelProperty(value = "主键")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ApiModelProperty(value = "请求方法")
    @Column(name = "method")
    private String method;

    @ApiModelProperty(value = "请求url")
    @Column(name = "url")
    private String url;

    @ApiModelProperty(value = "请求头")
    @Column(name = "headers")
    private String headers;

    @ApiModelProperty(value = "ip地址")
    @Column(name = "ip")
    private String ip;

    @ApiModelProperty(value = "请求参数")
    @Column(name = "params")
    private String params;

    @ApiModelProperty(value = "url上面的参数")
    @Column(name = "query_string")
    private String queryString;

    @ApiModelProperty(value = "请求结果")
    @Column(name = "result")
    private String result;

    @ApiModelProperty(value = "请求开始时间")
    @Column(name = "start_time")
    private Date startTime;

    @ApiModelProperty(value = "耗时")
    @Column(name = "run_time")
    private Long runTime;

    @ApiModelProperty(value = "接口描述")
    @Column(name = "description")
    private String description;

    @ApiModelProperty(value = "请求结束时间")
    @Column(name = "end_time")
    private Date endTime;

    @ApiModelProperty(value = "操作类型")
    @Column(name = "oparation")
    private String oparation;

    @ApiModelProperty(value = "请求类型，get,post,put")
    @Column(name = "type")
    private String type;

    @ApiModelProperty(value = "用户id")
    @Column(name = "user_id")
    private String userId;

    @ApiModelProperty(value = "应用id")
    @Column(name = "app_id")
    private String appId;

    @ApiModelProperty(value = "应用名称")
    @Column(name = "app_name")
    private String appName;
}
