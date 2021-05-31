package com.player.toutiao.entity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class ChannelEntity {
    @ApiModelProperty(value = "主键")
    private int id;

    @ApiModelProperty(value = "频道id")
    private String channelId;

    @ApiModelProperty(value = "频道名称")
    private String channelName;

    @ApiModelProperty(value = "链接地址")
    private String href;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "最近更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "是否禁用")
    private int disabled;

    @ApiModelProperty(value = "状态，公开:0,推荐:1,默认:2")
    private int status;

    @ApiModelProperty(value = "排序")
    private int sequence;
}
