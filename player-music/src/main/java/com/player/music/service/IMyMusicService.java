package com.player.music.service;

import com.player.common.entity.ResultEntity;
import com.player.music.entity.MyMusicEntity;
import com.player.music.entity.MyMusicFavoriteDirectoryEntity;
import com.player.music.entity.MyMusicFavoriteEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IMyMusicService {
    ResultEntity getKeywordMusic(String redisKey);

    ResultEntity getMusicClassify(String redisKey);

    ResultEntity getMusicListByClassifyId(String redisKey, int classifyId, int pageNum, int pageSize, boolean isRedis, String token);

    ResultEntity getMusicAuthorList(String redisKey, int categoryId, int pageNum, int pageSize);

    ResultEntity getMyLikeMusicAuthor(String redisKey,String token,int pageNum, int pageSize);

    ResultEntity getMusicRecord(String token, int pageNum, int pageSize);

    @Transactional
    ResultEntity insertMusicRecord(String token,MyMusicEntity myMusicEntity);

    @Transactional
    ResultEntity insertMusicLike(String token,int musicId);

    @Transactional
    ResultEntity deleteMusicLike(String token,int id);

    ResultEntity queryMusicLike(String token, int pageNum, int pageSize);

    ResultEntity searchMusic(String token,String keyword, int pageNum, int pageSize);

    ResultEntity getMusicAuthorCategory(String redisKey);

    ResultEntity getFavoriteDirectory(String token,Long musicId);

    ResultEntity getMusicListByFavoriteId(String token,Long favoriteId,int pageNum,int pageSize);

    @Transactional
    ResultEntity insertFavoriteDirectory(String token, MyMusicFavoriteDirectoryEntity favoriteDirectoryEntity);

    @Transactional
    ResultEntity deleteFavoriteDirectory(String token, Long favoriteId);

    @Transactional
    ResultEntity updateFavoriteDirectory(String token, Long favoriteId,String name);

    @Transactional
    ResultEntity insertMusicFavorite(String token,Long musicId, List<MyMusicFavoriteEntity> myMusicFavoriteEntityList);

    ResultEntity isMusicFavorite(String token, Long musicId);

}
