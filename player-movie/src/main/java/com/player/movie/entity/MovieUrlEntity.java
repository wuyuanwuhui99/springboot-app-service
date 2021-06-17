package com.player.movie.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MovieUrlEntity {
    @ApiModelProperty(value = "主键")
    private String id;//主键

    @ApiModelProperty(value = "电影名称")
    private String movieName;

    @ApiModelProperty(value = "对应的电影的id")
    private String movieId;

    @ApiModelProperty(value = "源地址")
    private String href;

    @ApiModelProperty(value = "集数")
    private String label;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "播放地址")
    private String updateTime;

    @ApiModelProperty(value = "播放地址")
    private String url;

    @ApiModelProperty(value = "播放分组，1, 2")
    private int playGroup;

}
