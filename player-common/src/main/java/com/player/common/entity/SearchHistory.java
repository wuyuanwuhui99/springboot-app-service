package com.player.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class SearchHistory {
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "类型")
    private String type;//类型

    @ApiModelProperty(value = "用户id")
    private String userId;//用户id

    @ApiModelProperty(value = "搜索内容")
    private String content;//搜索内容

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建时间")
    private Date updateTime;
}
