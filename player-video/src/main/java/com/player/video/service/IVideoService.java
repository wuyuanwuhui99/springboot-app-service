package com.player.video.service;

import com.player.common.entity.ResultEntity;
import com.player.video.entity.ChannelEntity;

import java.util.List;

public interface IVideoService {
    ResultEntity getUserData(String token);

    ResultEntity getVideoList(int pageNum,int pageSize,String star,String category,String type,String label,String userId,String keyword,String path);

    ResultEntity insertFavoriteChannels(List<ChannelEntity> channelEntities);

    ResultEntity getFavoriteChannels(String token);

    ResultEntity isFavorite(String token,int id);

    ResultEntity insertFavorite(String token, int videoId);

    ResultEntity deleteFavorite(String token, int videoId);

    ResultEntity getFavoriteList(String token, int pageNum, int pageSize);
}