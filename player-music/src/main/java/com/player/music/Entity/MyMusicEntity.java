package com.player.music.Entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class MyMusicEntity {

    @ApiModelProperty(value = "主键")
    private Long id;//主键

    @ApiModelProperty(value = "专辑id")
    private Long albumId;

    @ApiModelProperty(value = "歌曲名称")
    private String songName;

    @ApiModelProperty(value = "歌手名称")
    private String authorName;

    @ApiModelProperty(value = "歌手id")
    private Long authorId;

    @ApiModelProperty(value = "专辑")
    private String albumName;

    @ApiModelProperty(value = "版本")
    private String version;

    @ApiModelProperty(value = "语言")
    private String language;

    @ApiModelProperty(value = "发布时间")
    private Date publishDate;

    @ApiModelProperty(value = "未使用字段")
    private Long wideAudioId;

    @ApiModelProperty(value = "是否发布")
    private Long isPublish;

    @ApiModelProperty(value = "未使用字段")
    private Long bigPackId;

    @ApiModelProperty(value = "未使用字段")
    private Long finalId;

    @ApiModelProperty(value = "音频id")
    private Long audioId;

    @ApiModelProperty(value = "未使用字段")
    private Long similarAudioId;

    @ApiModelProperty(value = "是否热门")
    private int isHot;

    @ApiModelProperty(value = "音频专辑id")
    private Long albumAudioId;

    @ApiModelProperty(value = "歌曲组id")
    private Long audioGroupId;

    @ApiModelProperty(value = "歌曲图片")
    private String cover;

    @ApiModelProperty(value = "网络播放地址")
    private String playUrl;

    @ApiModelProperty(value = "本地播放地址")
    private String localPlayUrl;

    @ApiModelProperty(value = "歌曲来源")
    private String sourceName;

    @ApiModelProperty(value = "来源地址")
    private String sourceUrl;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "歌词")
    private String lyrics;

    @ApiModelProperty(value = "播放权限")
    private int permission;
}
