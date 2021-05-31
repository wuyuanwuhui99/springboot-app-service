package com.player.toutiao.entity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class ArticleEntity {
    @ApiModelProperty(value = "主键")
    private Long id;//主键

    @ApiModelProperty(value = "频道id")
    private String channelId;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "视频播放时长")
    private String duration;

    @ApiModelProperty(value = "链接地址")
    private String href;

    @ApiModelProperty(value = "视频图片地址")
    private String img;

    @ApiModelProperty(value = "图片长度")
    private String imgNum;

    @ApiModelProperty(value = "类型，视频：video, 文章: article，博客：blog")
    private String type;

    @ApiModelProperty(value = "是否置顶，0表示否，1表示是")
    private String isTop;

    @ApiModelProperty(value = "主标题")
    private String title;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "来源")
    private String source;

    @ApiModelProperty(value = "评论id")
    private String commentId;

    @ApiModelProperty(value = "标签")
    private String labels;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "头像")
    private String userAvatar;

    @ApiModelProperty(value = "用户主页")
    private String userHref;

    @ApiModelProperty(value = "排名")
    private String ranks;
}
