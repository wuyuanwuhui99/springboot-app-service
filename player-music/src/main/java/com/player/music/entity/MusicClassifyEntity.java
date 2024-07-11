package com.player.music.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class MusicClassifyEntity {

    @ApiModelProperty(value = "主键")
    private int id;

    @ApiModelProperty(value = "分类名称")
    private String classifyName;

    @ApiModelProperty(value = "权限")
    private int permission;

    @ApiModelProperty(value = "分类排名")
    private int classifyRank;

    @ApiModelProperty(value = "是否禁用")
    private int disabled;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
