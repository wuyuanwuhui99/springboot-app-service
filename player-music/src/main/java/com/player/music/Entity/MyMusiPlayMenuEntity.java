package com.player.music.Entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class MyMusiPlayMenuEntity {
    @ApiModelProperty(value = "主键")
    private int id;//主键

    @ApiModelProperty(value = "歌单名称")
    private String name;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "歌单里面的歌曲总数")
    private int total;

    @ApiModelProperty(value = "封面")
    private String cover;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
