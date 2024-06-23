package com.player.music.Entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class MyMusicFavoriteEntity {

    @ApiModelProperty(value = "主键")
    private Long id;//主键

    @ApiModelProperty(value = "收藏夹id")
    private Long favoriteId;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "音乐")
    private Long musicId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
