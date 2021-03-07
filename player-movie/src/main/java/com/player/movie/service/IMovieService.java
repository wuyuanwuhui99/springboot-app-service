package com.player.movie.service;

import com.player.common.entity.ResultEntity;
import com.player.common.entity.UserEntity;
import com.player.movie.entity.MovieEntity;

public interface IMovieService {

    ResultEntity findClassify(String path);

    ResultEntity getKeyWord(String classify,String path);

    ResultEntity getUserData(String token);

    ResultEntity getUserMsg(String token);

    ResultEntity getAllCategoryByClassify(String classsify,String path);

    ResultEntity getAllCategoryListByPageName(String pageName,String path);

    ResultEntity getCategoryList(String classify, String category,String path);

    ResultEntity search(String keyword, int pageNum, int pageSize,String path);

    ResultEntity login(String userId, String passsword);

    ResultEntity register(UserEntity userEntity);

    ResultEntity getUserById(String userId);

    ResultEntity getStar(String movieId,String path);

    ResultEntity getMovieUrl(String movieId,String path);

    ResultEntity getViewRecord(String token);

    ResultEntity saveViewRecord(MovieEntity movieEntity,String token);

    ResultEntity getPlayRecord(String token);

    ResultEntity savePlayRecord(MovieEntity movieEntity,String token);

    ResultEntity getFavoriteList(String token);

    ResultEntity saveFavorite(MovieEntity movieEntity,String token);

    ResultEntity deleteFavorite(String movieId,String token);

    ResultEntity isFavorite(String movieId, String token);
}
