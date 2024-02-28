package com.player.music.Entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class MusicAuthorEntity {

    @ApiModelProperty(value = "主键")
    private int id;

    @ApiModelProperty(value = "歌手id")
    private int authorId;

    @ApiModelProperty(value = "歌手名称")
    private String authorName;

    @ApiModelProperty(value = "语言")
    private String language;

    @ApiModelProperty(value = "是否发布")
    private int isPublish;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "类型")
    private int type;

    @ApiModelProperty(value = "国家")
    private String country;

    @ApiModelProperty(value = "生日")
    private String birthday;

    @ApiModelProperty(value = "身份")
    private int identity;

    @ApiModelProperty(value = "排名")
    private int rank;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "总数")
    private int total;
}
