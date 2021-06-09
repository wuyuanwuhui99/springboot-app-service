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
     * @date: 2020-12-25 22:29
     */
    List<ArticleEntity> getArticleList(int start,int pageSize,String type, String channelId,String userId, String keyword,String isTop);

    /**
     * @author: wuwenqiang
     * @description: 获取文章详情
     * @date: 2020-5-29 19:22
     */
    ArticleEntity getArticleDetail(int id);

    /**
     * @author: wuwenqiang
     * @description: 插入日志
     * @date: 2020-5-29 19:22
     */
    Long log(LogEntity logEntity);

    /**
     * @author: wuwenqiang
     * @description: 查询用户收藏的频道
     * @date: 2020-5-29 19:22
     */
    List<ChannelEntity> getFavoriteChannels(String userId);

    /**
     * @author: wuwenqiang
     * @description: 查询所有频道
     * @date: 2020-5-29 19:22
     */
    List<ChannelEntity>  getAllChannels(@Param("list") List<Integer> status);


    int insertFavoriteChannels(List<ChannelEntity> favoriteChannels);
}
