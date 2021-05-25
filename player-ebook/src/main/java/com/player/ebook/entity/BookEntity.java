package com.player.ebook.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class BookEntity {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "书名")
    private String name;

    @ApiModelProperty(value = "图片地址")
    private String img;

    @ApiModelProperty(value = "'评分'")
    private String score;

    @ApiModelProperty(value = "语言")
    private String language;

    @ApiModelProperty(value = "章节数量")
    private Integer chaptersNum;

    @ApiModelProperty(value = "阅读人数")
    private Integer frequency;

    @ApiModelProperty(value = "收藏次数")
    private Integer collection;

    @ApiModelProperty(value = "来源")
    private String sourceName;

    @ApiModelProperty(value = "来源的url")
    private String sourceUrl;

    @ApiModelProperty(value = "分享人")
    private String sharer;

    @ApiModelProperty(value = "分享人地址")
    private String shareUrl;

    @ApiModelProperty(value = "描述")
    private String introduction;

    @ApiModelProperty(value = "阅读地址")
    private String url;

    @ApiModelProperty(value = "创建时间")
    private Date createtime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "本地图片地址")
    private String localImg;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "大分类")
    private String classify;

    @ApiModelProperty(value = "排名")
    private String ranks;

    @ApiModelProperty(value = "小分类")
    private String categoryName;

    @ApiModelProperty(value = "分类url地址")
    private String categoryUrl;

    @ApiModelProperty(value = "分类网络图片地址")
    private String categoryImg;

    @ApiModelProperty(value = "分类本地图片地址")
    private String categoryImgLocal;

    @ApiModelProperty(value = "分类本地图片地址")
    private String categoryDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getChaptersNum() {
        return chaptersNum;
    }

    public void setChaptersNum(Integer chaptersNum) {
        this.chaptersNum = chaptersNum;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getCollection() {
        return collection;
    }

    public void setCollection(Integer collection) {
        this.collection = collection;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getSharer() {
        return sharer;
    }

    public void setSharer(String sharer) {
        this.sharer = sharer;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getLocalImg() {
        return localImg;
    }

    public void setLocalImg(String localImg) {
        this.localImg = localImg;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getRanks() {
        return ranks;
    }

    public void setRanks(String ranks) {
        this.ranks = ranks;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryUrl() {
        return categoryUrl;
    }

    public void setCategoryUrl(String categoryUrl) {
        this.categoryUrl = categoryUrl;
    }

    public String getCategoryImg() {
        return categoryImg;
    }

    public void setCategoryImg(String categoryImg) {
        this.categoryImg = categoryImg;
    }

    public String getCategoryImgLocal() {
        return categoryImgLocal;
    }

    public void setCategoryImgLocal(String categoryImgLocal) {
        this.categoryImgLocal = categoryImgLocal;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }
}
