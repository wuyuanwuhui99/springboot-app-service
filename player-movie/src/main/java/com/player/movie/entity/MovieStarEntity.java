package com.player.movie.entity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class MovieStarEntity {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "演员名称")
    private String starName;

    @ApiModelProperty(value = "演员图片地址")
    private String img;

    @ApiModelProperty(value = "演员本地本地图片")
    private String localImg;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建时间")
    private Date updateTime;

    @ApiModelProperty(value = "电影的id")
    private String movieId;

    @ApiModelProperty(value = "电影的名称")
    private String movieName;

    @ApiModelProperty(value = "角色")
    private String role;

    @ApiModelProperty(value = "演员的豆瓣链接地址")
    private String href;

    @ApiModelProperty(value = "代表作")
    private String works;
}
