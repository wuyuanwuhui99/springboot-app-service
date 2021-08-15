package com.player.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AuthorEntity {
    @ApiModelProperty(value = "主键")
    private Long id;//主键

    @ApiModelProperty(value = "用户id")
    private String authorId;

    @ApiModelProperty(value = "用户名称")
    private String name;

    @ApiModelProperty(value = "用户签名")
    private String authorDesc;

    @ApiModelProperty(value = "头像地址")
    private String avatarUrl;

    @ApiModelProperty(value = "用户描述")
    private String description;

    @ApiModelProperty(value = "粉丝数量")
    private String followersCount;

    @ApiModelProperty(value = "核实内容")
    private String verifiedContent;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "更新时间")
    private String updateTime;

    @ApiModelProperty(value = "用户连接地址")
    private String authorHref;
}
