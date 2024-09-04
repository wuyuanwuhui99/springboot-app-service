package com.player.music.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class MyMusicRecordEntity {
    @ApiModelProperty(value = "主键")
    private int id;//主键

    @ApiModelProperty(value = "歌单名称")
    private String userId;

    @ApiModelProperty(value = "用户id")
    private String platform;

    @ApiModelProperty(value = "用户id")
    private int musicId;
}
