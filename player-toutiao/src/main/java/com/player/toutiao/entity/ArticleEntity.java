package com.player.toutiao.entity;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class ArticleEntity {
    @ApiModelProperty(value = "主键")
    private Long id;//主键

    @ApiModelProperty(value = "频道id")
    private String channelId;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "视频播放时长")
    private String duration;

    @ApiModelProperty(value = "链接地址")
    private String href;

    @ApiModelProperty(value = "视频图片地址")
    private String img;

    @ApiModelProperty(value = "图片长度")
    private String imgNum;

    @ApiModelProperty(value = "类型，视频：video, 文章: article，博客：blog")
    private String type;

    @ApiModelProperty(value = "是否置顶，0表示否，1表示是")
    private String isTop;

    @ApiModelProperty(value = "主标题")
    private String title;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "来源")
    private String source;

    @ApiModelProperty(value = "评论id")
    private String commentId;

    @ApiModelProperty(value = "标签")
    private String labels;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "头像")
    private String userAvatar;

    @ApiModelProperty(value = "用户主页")
    private String userHref;

    @ApiModelProperty(value = "排名")
    private String ranks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImgNum() {
        return imgNum;
    }

    public void setImgNum(String imgNum) {
        this.imgNum = imgNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserHref() {
        return userHref;
    }

    public void setUserHref(String userHref) {
        this.userHref = userHref;
    }

    public String getRanks() {
        return ranks;
    }

    public void setRanks(String ranks) {
        this.ranks = ranks;
    }

    @Override
    public String toString() {
        return "ArticleEntity{" +
                "id=" + id +
                ", channelId='" + channelId + '\'' +
                ", content='" + content + '\'' +
                ", duration='" + duration + '\'' +
                ", href='" + href + '\'' +
                ", img='" + img + '\'' +
                ", imgNum='" + imgNum + '\'' +
                ", type='" + type + '\'' +
                ", isTop='" + isTop + '\'' +
                ", title='" + title + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", source='" + source + '\'' +
                ", commentId='" + commentId + '\'' +
                ", labels='" + labels + '\'' +
                ", userId='" + userId + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                ", userHref='" + userHref + '\'' +
                ", ranks='" + ranks + '\'' +
                '}';
    }
}
