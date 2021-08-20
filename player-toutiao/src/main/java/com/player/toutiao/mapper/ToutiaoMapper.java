package com.player.toutiao.mapper;

import com.player.common.entity.LogEntity;
import com.player.toutiao.entity.ArticleEntity;
import com.player.toutiao.entity.ChannelEntity;
import feign.Param;

import java.util.List;

public interface ToutiaoMapper {
    /**
     * @author: wuwenqiang
     * @description: 获取文章列表
     * @date: 2021-12-25 22:29
     */
    List<ArticleEntity> getArticleList(int start,int pageSize,String type, String channelId,String authorId, String keyword);

    /**
     * @author: wuwenqiang
     * @description: 获取文章详情
     * @date: 2021-5-29 19:22
     */
    ArticleEntity getArticleDetail(int id);

    /**
     * @author: wuwenqiang
     * @description: 保存浏览记录
     * @date: 2021-5-29 19:22
     */
    Long saveArticleRecord(String userId,Long articleId);

    /**
     * @author: wuwenqiang
     * @description: 只保留前20条浏览记录
     * @date: 2021-8-14 12:16
     */
    Long deleteArticleRecord(String userId);

    /**
     * @author: wuwenqiang
     * @description: 插入日志
     * @date: 2021-5-29 19:22
     */
    Long log(LogEntity logEntity);

    /**
     * @author: wuwenqiang
     * @description: 查询用户收藏的频道
     * @date: 2021-5-29 19:22
     */
    List<ChannelEntity> getFavoriteChannels(String userId);

    /**
     * @author: wuwenqiang
     * @description: 查询所有频道
     * @date: 2021-5-29 19:22
     */
    List<ChannelEntity>  getAllChannels(@Param("list") List<Integer> status);

    int insertFavoriteChannels(List<ChannelEntity> favoriteChannels);

    List<ArticleEntity> getArticleRecordList(String userId);

    Long isFavorite(String userId,int id);

    List<ArticleEntity>getFavoriteList(String userId,int start,int pageSize);

    Long insertFavorite(String userId,int articleId);

    Long deleteFavorite(String userId,int articleId);

    Long isLike(String userId, int id);

    Long insertLike(String userId,int articleId);

    Long deleteLike(String userId,int articleId);

    Long isFocus(String userId, String authorId);

    Long insertFocus(String userId,String authorId);

    Long deleteFocus(String userId,String authorId);
}
