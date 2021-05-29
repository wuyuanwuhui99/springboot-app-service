package com.player.toutiao.entity;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class ChannelEntity {
    @ApiModelProperty(value = "主键")
    private int id;

    @ApiModelProperty(value = "频道id")
    private String channelId;

    @ApiModelProperty(value = "频道名称")
    private String channelName;

    @ApiModelProperty(value = "链接地址")
    private String href;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "最近更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "用户id")
    private Date userId;

    @ApiModelProperty(value = "是否禁用")
    private Date disabled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
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

    public Date getUserId() {
        return userId;
    }

    public void setUserId(Date userId) {
        this.userId = userId;
    }

    public Date getDisabled() {
        return disabled;
    }

    public void setDisabled(Date disabled) {
        this.disabled = disabled;
    }

    @Override
    public String toString() {
        return "ChannelEntity{" +
                "id=" + id +
                ", channelId='" + channelId + '\'' +
                ", channelName='" + channelName + '\'' +
                ", href='" + href + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", userId=" + userId +
                ", disabled=" + disabled +
                '}';
    }
}
