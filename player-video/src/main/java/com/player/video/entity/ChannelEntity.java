package com.player.video.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ChannelEntity {
    @ApiModelProperty(value = "主键")
    private Long id;//主键

    @ApiModelProperty(value = "频道名称")
    private String channelName;

    @ApiModelProperty(value = "频道id")
    private String channelId;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "更新时间")
    private String updateTime;

    @ApiModelProperty(value = "是否禁用，0表示不禁用，1表示禁用")
    private String status;

    @ApiModelProperty(value = "排序规则")
    private String sequence;

    @ApiModelProperty(value = "用户id")
    private String userId;
}
