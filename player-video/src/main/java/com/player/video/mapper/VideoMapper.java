package com.player.video.mapper;

import com.player.common.entity.LogEntity;
import com.player.video.entity.ChannelEntity;
import com.player.video.entity.VideoEntity;

import java.util.List;

public interface VideoMapper {
    /**
     * @author: wuwenqiang
     * @description: 插入日志
     * @date: 2020-5-29 19:22
     */
    Long log(LogEntity logEntity);

    /**
     * @author: wuwenqiang
     * @description: 查询日志列表
     * @date: 2020-5-29 19:22
     */
    List<VideoEntity> getVideoList(int start,int pageSize,String star,String category,String type,String label,String authorId,String keyword);

    List<ChannelEntity> getFavoriteChannels(String userId);

    List<ChannelEntity> getPublicChannels();

    Long insertFavoriteChannels(List<ChannelEntity> channelEntities);

    List<VideoEntity>isFavorite(String userId, int id);

    Long insertFavorite(String useId,int videoId);

    Long deleteFavorite(String userId, int videoId);

    List<VideoEntity> getFavoriteList(String userId,int start,int pageSize);

    List<VideoEntity> getVideoRecordList(String userId);
}
