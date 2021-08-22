package com.player.movie.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class MovieEntity {
    @ApiModelProperty(value = "主键")
    private Long id;//主键

    @ApiModelProperty(value = "电影id")
    private Long movieId;

    @ApiModelProperty(value = "导演")
    private String director;//导演

    @ApiModelProperty(value = "主演")
    private String star;//主演

    @ApiModelProperty(value = "类型")
    private String type;//类型

    @ApiModelProperty(value = "国家/语言")
    private String countryLanguage;//国家/语言

    @ApiModelProperty(value = "观看状态")
    private String viewingState;//观看状态

    @ApiModelProperty(value = "上映时间")
    private String releaseTime;//上映时间

    @ApiModelProperty(value = "剧情")
    private String plot;//剧情

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;//更新时间

    @ApiModelProperty(value = "电影名称")
    private String movieName;//电影名称

    @ApiModelProperty(value = "是否推荐，0:不推荐，1:推荐")
    private String isRecommend;//是否推荐，0:不推荐，1:推荐

    @ApiModelProperty(value = "电影海报")
    private String img;//电影海报

    @ApiModelProperty(value = "分类 电影,电视剧,动漫,综艺,新片库,福利,午夜,恐怖,其他")
    private String classify;//分类 电影,电视剧,动漫,综艺,新片库,福利,午夜,恐怖,其他

    @ApiModelProperty(value = "来源名称，本地，骑士影院，爱奇艺")
    private String sourceName;//来源名称，本地，骑士影院，爱奇艺

    @ApiModelProperty(value = "来源地址")
    private String sourceUrl;//来源地址

    @ApiModelProperty(value = "创建时间")
    private Date createTime;//创建时间

    @ApiModelProperty(value = "本地图片")
    private String localImg;//本地图片

    @ApiModelProperty(value = "播放集数")
    private String label;//播放集数

    @ApiModelProperty(value = "源地址")
    private String originalHref;//源地址

    @ApiModelProperty(value = "简单描述")
    private String description;//简单描述

    @ApiModelProperty(value = "链接地址")
    private String targetHref;//链接地址

    @ApiModelProperty(value = "0代表未使用，1表示正在使用，是banner和carousel图的才有")
    private String useStatus;//0代表未使用，1表示正在使用，是banner和carousel图的才有

    @ApiModelProperty(value = "评分")
    private String score;//评分

    @ApiModelProperty(value = "类目，值为banner首屏，carousel：滚动轮播")
    private String category;//类目，值为banner首屏，carousel：滚动轮播

    @ApiModelProperty(value = "排名")
    private String ranks;//排名

    @ApiModelProperty(value = "用户名，这这个表不需要，为了跟记录叫和收藏表的结构一致',")
    private String userId;//用户名，这这个表不需要，为了跟记录叫和收藏表的结构一致',

    @ApiModelProperty(value = "豆瓣网的url',")
    private String doubanUrl;
}
