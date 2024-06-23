package com.player.music.Entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class MyMusicFavoriteDirectoryEntity {

    @ApiModelProperty(value = "主键")
    private Long id;//主键

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "收藏夹名称")
    private String name;

    @ApiModelProperty(value = "收藏夹总歌曲数据")
    private int total;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
