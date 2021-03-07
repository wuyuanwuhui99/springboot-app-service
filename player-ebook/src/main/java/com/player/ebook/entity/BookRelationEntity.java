package com.player.ebook.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class BookRelationEntity {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "图片地址")
    private String img;

    @ApiModelProperty(value = "书名")
    private String name;

    @ApiModelProperty(value = "本地图片地址")
    private String localImg;

    @ApiModelProperty(value = "链接地址")
    private String url;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "关联的书籍名称")
    private String bookName;

    @ApiModelProperty(value = "关联的书籍的id")
    private Integer bookId;
}
