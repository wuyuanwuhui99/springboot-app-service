package com.player.social.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
public class CircleEntity {

    @ApiModelProperty(value = "主键")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ApiModelProperty(value = "关联音乐audio_id或者电影movie_id")
    private int relationId;

    @ApiModelProperty(value = "朋友圈内容")
    private String content;

    @ApiModelProperty(value = "朋友圈图片")
    private String imgs;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "用户的昵称")
    private String username;

    @ApiModelProperty(value = "用户头像")
    private String useravater;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "歌曲名称")
    private String musicSongName;

    @ApiModelProperty(value = "歌曲id")
    private String musicAudioId;

    @ApiModelProperty(value = "歌曲作者")
    private String musicAuthorName;

    @ApiModelProperty(value = "专辑名称")
    private String musicAlbumName;

    @ApiModelProperty(value = "音乐图片")
    private String musicCover;

    @ApiModelProperty(value = "音乐播放地址")
    private String musicPlayUrl;

    @ApiModelProperty(value = "音乐本地播放地址")
    private String musicLocalPlayUrl;

    @ApiModelProperty(value = "歌词")
    private String musicLyrics;

    @ApiModelProperty(value = "电影id")
    private String movieId;

    @ApiModelProperty(value = "电影名称")
    private String movieName;

    @ApiModelProperty(value = "电影导演")
    private String movieDirector;

    @ApiModelProperty(value = "电影主演")
    private String movieStar;

    @ApiModelProperty(value = "电影类型")
    private String movieType;

    @ApiModelProperty(value = "电影上映国家")
    private String movieCountryLanguage;

    @ApiModelProperty(value = "电影状态")
    private String movieViewingState;

    @ApiModelProperty(value = "上映时间")
    private String movieReleaseTime;

    @ApiModelProperty(value = "电影海报")
    private String movieImg;

    @ApiModelProperty(value = "电影分类")
    private String movieClassify;

    @ApiModelProperty(value = "电影本地图片")
    private String movieLocalImg;

    @ApiModelProperty(value = "电影得分")
    private String movieScore;

    @ApiModelProperty(value = "喜欢列表")
    private List<CircleLikeEntity> circleLikes;
}
