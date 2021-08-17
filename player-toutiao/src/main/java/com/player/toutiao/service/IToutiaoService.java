package com.player.toutiao.service;

import com.player.common.entity.ResultEntity;

import java.util.List;

public interface IToutiaoService {
    ResultEntity getArticleList(int pageNum, int pageSize,String type, String channelId, String userId, String keyword, String path);

    ResultEntity getArticleDetail(int id,String token);

    ResultEntity getFavoriteChannels(String token);

    ResultEntity getUserData(String token);

    ResultEntity getAllChannels(List<Integer> status);

    ResultEntity getVideoCategory(String token);

    ResultEntity getVideoList(int pageSize,int pageNum,String star,String category,String type,String label,String userId,String keyword,String token);

    ResultEntity getMovieList(int pageSize,int pageNum,String star,String classify,String category,String type,String label,String keyword,String token);

    ResultEntity getRecordList(String token,String type);

    ResultEntity isFavorite(String token,String type,int id);

    ResultEntity getFavoriteList(String token,String type,int pageNum,int pageSize);

    ResultEntity insertFavorite(String token,String type,int id);

    ResultEntity deleteFavorite(String token,String type,int id);
}
