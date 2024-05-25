package com.player.movie.service;

import com.player.common.entity.ResultEntity;
import com.player.movie.entity.MovieEntity;

public interface IMovieService {

    ResultEntity findClassify(String redisKey);

    ResultEntity getKeyWord(String classify,String redisKey);

    ResultEntity getUserMsg(String token);

    ResultEntity getAllCategoryByClassify(String classsify,String redisKey);

    ResultEntity getAllCategoryListByPageName(String pageName,String redisKey);

    ResultEntity getCategoryList(String classify, String category,String redisKey);

    ResultEntity getTopMovieList(String classify, String category,String redisKey);

    ResultEntity search(String classify, String category, String label,String star,String director,String keyword,int pageNum,int pageSize,String redisKey);

    ResultEntity getStar(Long movieId,String redisKey);

    ResultEntity getMovieUrl(Long movieId,String redisKey);

    ResultEntity getPlayRecord(String token,int pageNum,int pageSize);

    ResultEntity savePlayRecord(MovieEntity movieEntity,String token);

    ResultEntity getViewRecord(String token,int pageNum,int pageSize);

    ResultEntity saveViewRecord(MovieEntity movieEntity,String token);

    ResultEntity getFavoriteList(String token,int pageNum,int pageSize);

    ResultEntity saveFavorite(int movieId,String token);

    ResultEntity deleteFavorite(int movieId,String token);

    ResultEntity isFavorite(Long movieId, String token);

    ResultEntity getYourLikes(String labels,String classify,String redisKey);

    ResultEntity getRecommend(String classify,String redisKey);

    ResultEntity getMovieDetail(int movieId);

    ResultEntity getMovieListByType(String types,String classify,String redisKey);
}
