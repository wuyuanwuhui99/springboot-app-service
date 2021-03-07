package com.player.movie.entity;

import io.swagger.annotations.ApiModelProperty;

public class MovieUrlEntity {
    @ApiModelProperty(value = "主键")
    private String id;//主键

    @ApiModelProperty(value = "电影名称")
    private String movieName;

    @ApiModelProperty(value = "对应的电影的id")
    private String movieId;

    @ApiModelProperty(value = "源地址")
    private String href;

    @ApiModelProperty(value = "集数")
    private String label;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "播放地址")
    private String updateTime;

    @ApiModelProperty(value = "播放地址")
    private String url;

    @ApiModelProperty(value = "播放分组，1, 2")
    private int playGroup;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPlayGroup() {
        return playGroup;
    }

    public void setPlayGroup(int playGroup) {
        this.playGroup = playGroup;
    }
}
