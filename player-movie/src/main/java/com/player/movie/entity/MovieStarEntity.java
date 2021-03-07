package com.player.movie.entity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStarName() {
        return starName;
    }

    public void setStarName(String starName) {
        this.starName = starName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLocalImg() {
        return localImg;
    }

    public void setLocalImg(String localImg) {
        this.localImg = localImg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getWorks() {
        return works;
    }

    public void setWorks(String works) {
        this.works = works;
    }
}
