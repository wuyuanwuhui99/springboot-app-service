package com.player.ebook.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class BookChapterEntity {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "书名")
    private String bookName;

    @ApiModelProperty(value = "书籍的id")
    private Integer bookId;

    @ApiModelProperty(value = "章节的名称")
    private String chapterName;

    @ApiModelProperty(value = "章节的链接地址")
    private String chapterUrl;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
