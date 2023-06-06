package com.player.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LikeEntity {
    @ApiModelProperty(value = "主键")
    private Long id;//主键

    @ApiModelProperty(value = "类型，movie，aiqiyi，article")
    private String type;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "关联的影片id")
    private Long relationId;

    @ApiModelProperty(value = "创建时间")
    private String createTime;
}
