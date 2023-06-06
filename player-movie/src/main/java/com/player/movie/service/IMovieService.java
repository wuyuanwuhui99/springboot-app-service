package com.player.movie.service;

import com.player.common.entity.CommentEntity;
import com.player.common.entity.PasswordEntity;
import com.player.common.entity.ResultEntity;
import com.player.common.entity.UserEntity;
import com.player.movie.entity.MovieEntity;

import java.util.Map;

public interface IMovieService {

    ResultEntity findClassify(String redisKey);

    ResultEntity getKeyWord(String classify,String redisKey);

    ResultEntity getUserData(String token);

    ResultEntity getUserMsg(String token);

    ResultEntity getAllCategoryByClassify(String classsify,String redisKey);

    ResultEntity getAllCategoryListByPageName(String pageName,String redisKey);

    ResultEntity getCategoryList(String classify, String category,String redisKey);

    ResultEntity getTopMovieList(String classify, String category,String redisKey);

    ResultEntity search(String classify, String category, String label,String star,String director,String keyword,int pageNum,int pageSize,String redisKey);

    ResultEntity login(UserEntity userEntity);

    ResultEntity register(UserEntity userEntity);

    ResultEntity getUserById(String userId);

    ResultEntity getStar(Long movieId,String redisKey);

    ResultEntity getMovieUrl(Long movieId,String redisKey);

    ResultEntity getPlayRecord(String token);

    ResultEntity savePlayRecord(MovieEntity movieEntity,String token);

    ResultEntity saveViewRecord(MovieEntity movieEntity,String token);

    ResultEntity getFavoriteList(String token,int pageNum,int pageSize);

    ResultEntity saveFavorite(Long movieId,String token);

    ResultEntity deleteFavorite(Long movieId,String token);

    ResultEntity isFavorite(Long movieId, String token);

    ResultEntity getYourLikes(String labels,String classify,String redisKey);

    ResultEntity getRecommend(String classify,String redisKey);

    ResultEntity updateUser(UserEntity userEntity,String token);

    ResultEntity updatePassword(PasswordEntity passwordEntity, String token);

    ResultEntity getCommentCount(int relationId, String type);

    ResultEntity getTopCommentList(int relationId, String type,int pageNum, int pageSize);

    ResultEntity insertComment(String token, CommentEntity commentEntity);

    ResultEntity deleteComment(int id,String token);

    ResultEntity getReplyCommentList(int topId,int pageNum,int pageSize);

    ResultEntity getRecordList(String token);

    ResultEntity getMovieDetail(int movieId);

    ResultEntity getMovieListByType(String types,String classify,String redisKey);

    ResultEntity updateAvater(String token, Map imgMap);
}
