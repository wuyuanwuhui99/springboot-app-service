package com.player.music.entity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class MyMusicSingerCategoryEntity {
    @ApiModelProperty(value = "主键")
    private Long id;//主键

    @ApiModelProperty(value = "分类名称")
    private String categoryName;

    @ApiModelProperty(value = "排名")
    private int rank;

    @ApiModelProperty(value = "是否禁用")
    private int disabled;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
