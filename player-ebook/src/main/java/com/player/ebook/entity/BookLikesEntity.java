package com.player.ebook.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class BookLikesEntity {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "书籍名称")
    private String name;

    @ApiModelProperty(value = "书籍的url")
    private Integer url;

    @ApiModelProperty(value = "关联的书籍的名称")
    private String bookName;

    @ApiModelProperty(value = "关联的书籍的id")
    private String bookId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
