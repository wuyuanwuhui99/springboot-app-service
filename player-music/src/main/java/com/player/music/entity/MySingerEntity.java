package com.player.music.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class MySingerEntity {
    @ApiModelProperty(value = "主键")
    private int id;//主键

    @ApiModelProperty(value = "歌手id")
    private String authorId;

    @ApiModelProperty(value = "歌手名称")
    private String authorName;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "歌手头像")
    private String avatar;

    @ApiModelProperty(value = "总数")
    private int total;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
