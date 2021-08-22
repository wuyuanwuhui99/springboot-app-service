package com.player.movie.service;

import com.player.common.entity.PasswordEntity;
import com.player.common.entity.ResultEntity;
import com.player.common.entity.UserEntity;
import com.player.movie.entity.MovieEntity;

import java.util.Map;

public interface IMovieService {

    ResultEntity findClassify(String path);

    ResultEntity getKeyWord(String classify,String path);

    ResultEntity getUserData(String token);

    ResultEntity getUserMsg(String token);

    ResultEntity getAllCategoryByClassify(String classsify,String path);

    ResultEntity getAllCategoryListByPageName(String pageName,String path);

    ResultEntity getCategoryList(String classify, String category,String path);

    ResultEntity search(String classify, String category, String label,String star,String director,String keyword,int pageNum,int pageSize,String path);

    ResultEntity login(UserEntity userEntity);

    ResultEntity register(UserEntity userEntity);

    ResultEntity getUserById(String userId);

    ResultEntity getStar(Long movieId,String path);

    ResultEntity getMovieUrl(Long movieId,String path);

    ResultEntity getPlayRecord(String token);

    ResultEntity savePlayRecord(MovieEntity movieEntity,String token);

    ResultEntity getFavoriteList(String token,int pageNum,int pageSize);

    ResultEntity saveFavorite(Long movieId,String token);

    ResultEntity deleteFavorite(Long movieId,String token);

    ResultEntity isFavorite(Long movieId, String token);

    ResultEntity saveLike(Long movieId,String token);

    ResultEntity deleteLike(Long movieId,String token);

    ResultEntity isLike(Long movieId, String token);

    ResultEntity getYourLikes(String labels,String path);

    ResultEntity getRecommend(String classify,String path);

    ResultEntity updateUser(UserEntity userEntity,String token);

    ResultEntity updatePassword(PasswordEntity passwordEntity, String token);
}
