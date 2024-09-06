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

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "平台")
    private String platform;

    @ApiModelProperty(value = "设备")
    private String device;

    @ApiModelProperty(value = "app版本")
    private String version;

    @ApiModelProperty(value = "用户id")
    private int musicId;
}
