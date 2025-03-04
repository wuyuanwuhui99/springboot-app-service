package com.player.music.mapper;

import com.player.music.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyMusicMapper {
    MyMusicEntity getKeywordMusic();

    List<MyMusicEntity> getMusicClassify();

    List<MyMusicEntity> getMusicListByClassifyId(int classifyId,int start,int pageSize,String userId);

    Long getMusicTotalByClassifyId(int classifyId);

    List<MyMusicAuthorEntity> getMusicAuthorListByCategoryId(String userId,int categoryId, int start,int pageSize);

    Long getMusicAuthorTotal(int categoryId);

    List<MyMusicEntity> getMusicListByAuthorId(String userId,int authorId, int start, int pageSize);

    Long getMusicListByAuthorIdTotal(int authorId);

    List<MyMusicAuthorEntity> getFavoriteAuthor(String userId, int start, int pageSize);

    Long getFavoriteAuthorCount(String userId);

    Long insertFavoriteAuthor(String userId,int authorId);

    Long deleteFavoriteAuthor(String userId,int authorId);

    List<MyMusicEntity> getMusicRecord(String userId, int start, int pageSize);

    Long getMusicRecordCount(String userId);

    Long insertMusicRecord(MyMusicRecordEntity myMusicRecordEntity);

    Long insertMusicLike(String userId,int musicId);

    Long deleteMusicLike(String userId,int musicId);

    List<MyMusicEntity> getMusicLike(String userId, int start, int pageSize);

    Long getMusicLikeCount(String userId);

    List<MyMusicEntity> searchMusic(String userId,String keyword, int start, int pageSize);

    Long searchMusicCount(String keyword);

    List<MyMusicAuthorCategoryEntity> getMusicAuthorCategory();

    List<MyMusicFavoriteDirectoryEntity> getFavoriteDirectory(String userId,Long musicId);

    List<MyMusicEntity> getMusicListByFavoriteId(String userId,Long favoriteId,int start,int pageSize);

    Long getMusicCountByFavoriteId(Long favoriteId);

    Long deleteFavoriteDirectory(String userId, Long favoriteId);

    Long updateFavoriteDirectory(String userId, Long favoriteId, String name);

    Long insertFavoriteDirectory(MyMusicFavoriteDirectoryEntity favoriteDirectoryEntity);

    MyMusicFavoriteDirectoryEntity getFavoriteDirectoryById(Long id);

    Long insertMusicFavorite(List<MyMusicFavoriteEntity> myMusicFavoriteEntityList);

    Long deleteMusicFavorite(String userId,Long musicId);

    Long deleteMusicFavoriteByFavoriteId(String userId,Long favoriteId);

    Long isMusicFavorite(String userId,Long musicId);

    Long saveLog(LogEntity logEntity);
}
