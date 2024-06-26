package com.player.music.service;

import com.player.common.entity.ResultEntity;
import com.player.music.Entity.MyMusicEntity;
import com.player.music.Entity.MyMusicFavoriteDirectoryEntity;
import com.player.music.Entity.MyMusicFavoriteEntity;

public interface IMyMusicService {
    ResultEntity getKeywordMusic(String redisKey);

    ResultEntity getMusicClassify(String redisKey);

    ResultEntity getMusicListByClassifyId(String redisKey, int classifyId, int pageNum, int pageSize, boolean isRedis, String token);

    ResultEntity getSingerList(String redisKey, String category, int pageNum, int pageSize);

    ResultEntity getMusiPlayMenu(String redisKey,String token);

    ResultEntity getMySinger(String redisKey,String token,int pageNum, int pageSize);

    ResultEntity getMusicRecord(String token, int pageNum, int pageSize);

    ResultEntity insertMusicRecord(String token,MyMusicEntity myMusicEntity);

    ResultEntity insertMusicLike(String token,int musicId);

    ResultEntity deleteMusicLike(String token,int id);

    ResultEntity queryMusicLike(String token, int pageNum, int pageSize);

    ResultEntity searchMusic(String token,String keyword, int pageNum, int pageSize);

    ResultEntity getSingerCategory(String redisKey);

    ResultEntity getFavoriteDirectory(String token,Long musicId);

    ResultEntity getMusicListByFavoriteId(String token,Long favoriteId,int pageNum,int pageSize);

    ResultEntity insertFavoriteDirectory(String token, MyMusicFavoriteDirectoryEntity favoriteDirectoryEntity);

    ResultEntity deleteFavoriteDirectory(String token, Long favoriteId);

    ResultEntity updateFavoriteDirectory(String token, Long favoriteId,String name);

    ResultEntity insertMusicFavorite(String token, MyMusicFavoriteEntity myMusicFavoriteEntity);

    ResultEntity updateMusicFavorite(String token, MyMusicFavoriteEntity myMusicFavoriteEntity);

    ResultEntity deleteMusicFavorite(String token, MyMusicFavoriteEntity myMusicFavoriteEntity);

    ResultEntity isMusicFavorite(String token, Long musicId);
}
