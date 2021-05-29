package com.player.toutiao.mapper;

import com.player.common.entity.LogEntity;
import com.player.toutiao.entity.ArticleEntity;
import com.player.toutiao.entity.ChannelEntity;

import java.util.List;
import java.util.Map;

public interface ToutiaoMapper {
    /**
     * @author: wuwenqiang
     * @description: 获取文章列表
     * @date: 2020-12-25 22:29
     */
    List<ArticleEntity> findArticleList(int start,int pageSize,String type, String keyword);

    /**
     * @author: wuwenqiang
     * @description: 获取文章详情
     * @date: 2020-5-29 19:22
     */
    ArticleEntity findArticleDetail(int id);

    Long log(LogEntity logEntity);

    /**
     * @author: wuwenqiang
     * @description: 查询用户收藏的频道
     * @date: 2020-5-29 19:22
     */
    List<ChannelEntity> findFavoriteChannels(String userId);

    /**
     * @author: wuwenqiang
     * @description: 查询所有频道
     * @date: 2020-5-29 19:22
     */
    List<ChannelEntity>  findAllChannels();

    /**
     * @author: wuwenqiang
     * @description: 按照用户id查询用户的文章
     * @date: 2020-5-29 19:22
     */
    List<ArticleEntity> findArticleByUserId(String userId,int start,int pageSize,String keyword);
}
