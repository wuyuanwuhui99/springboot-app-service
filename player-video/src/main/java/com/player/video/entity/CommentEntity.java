package com.player.video.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommentEntity {
    @ApiModelProperty(value = "主键")
    private Long id;//主键

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "父节点id")
    private Long parentId;

    @ApiModelProperty(value = "顶级节点id")
    private Long topId;

    @ApiModelProperty(value = "视频id")
    private Long videoId;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "更新时间")
    private String updateTime;

    @ApiModelProperty(value = "回复数量")
    private int replyCount;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户头像")
    private String avater;

    @ApiModelProperty(value = "被回复者id")
    private String replyUserId;

    @ApiModelProperty(value = "被回复者名称")
    private String replyUserName;
}
