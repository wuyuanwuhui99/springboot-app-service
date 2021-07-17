package com.player.video.entity;
import com.player.common.entity.AuthorEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class VideoEntity {
    @ApiModelProperty(value = "主键")
    private Long id;//主键

    @ApiModelProperty(value = "视频id")
    private String albumId;

    @ApiModelProperty(value = "频道id")
    private String channelId;

    @ApiModelProperty(value = "视频名称")
    private String title;

    @ApiModelProperty(value = "导演")
    private String director;

    @ApiModelProperty(value = "主演")
    private String star;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "国家/语言")
    private String countryLanguage;

    @ApiModelProperty(value = "上映时间")
    private String publishTime;

    @ApiModelProperty(value = "剧情")
    private String plot;

    @ApiModelProperty(value = "是否推荐，0:不推荐，1:推荐")
    private String isRecommend;

    @ApiModelProperty(value = "大分类")
    private String classify;

    @ApiModelProperty(value = "来源名称")
    private String sourceName;

    @ApiModelProperty(value = "来源地址")
    private String sourceUrl;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "源地址")
    private String originalHref;

    @ApiModelProperty(value = "简单描述")
    private String description;

    @ApiModelProperty(value = "链接地址")
    private String targetHref;

    @ApiModelProperty(value = "0代表未使用，1表示正在使用，")
    private String status;

    @ApiModelProperty(value = "评分")
    private String score;

    @ApiModelProperty(value = "类目")
    private String category;

    @ApiModelProperty(value = "排名")
    private String ranks;

    @ApiModelProperty(value = "用户名，这这个表不需要，为了跟记录叫和收藏表的结构一致")
    private String userId;

    @ApiModelProperty(value = "时长")
    private String duration;

    @ApiModelProperty(value = "图片地址")
    private String img;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "用户信息")
    private AuthorEntity userInfo;
}
