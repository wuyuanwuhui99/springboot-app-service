package com.player.movie.mapper;

import com.player.common.entity.LogEntity;
import com.player.movie.entity.MovieEntity;
import com.player.movie.entity.MovieStarEntity;
import com.player.movie.entity.MovieUrlEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface MovieMapper {
    /**
     * @author: wuwenqiang
     * @description: 查询电影分类
     * @date: 2020-12-21 22:40
     */
    List<Map<String, String>> findClassify();

    /**
     * @author: wuwenqiang
     * @description: 查询推荐电影
     * @date: 2020-12-24 23:50
     */
    MovieEntity getKeyWord(String classify);

    /**
     * @author: wuwenqiang
     * @description: 查询用户访问记录
     * @date: 2020-12-24 23:50
     */
    Map<String, Object> getUserMsg(String userId);

    /**
     * @author: wuwenqiang
     * @description: 按classify大类查询所有catory小类
     * @date: 2020-12-25 22:25
     */
    List<Map<String, String>> getAllCategoryByClassify(String classsify);

    /**
     * @author: wuwenqiang
     * @description: 按页面获取要展示的category小类
     * @date: 2020-12-25 22:25
     */
    List<Map<String, String>> getAllCategoryListByPageName(String pageName);

    /**
     * @author: wuwenqiang
     * @description: 获取大类中的小类
     * @date: 2020-12-25 22:25
     */
    List<MovieEntity> getCategoryList(String classify, String category);

    /**
     * @author: wuwenqiang
     * @description: 根据分类获取前20条数据
     * @date: 2021-12-21 23:36
     */
    List<MovieEntity> getTopMovieList(String classify, String category);

    List<MovieEntity> search(String classify, String category, String label,String star,String director,String keyword,int start,int pageSize);

    Long searchTotal(String classify, String category, String label,String star,String director,String keyword);

    Long log(LogEntity logEntity);

    List<MovieStarEntity> getStar(Long movieId);

    List<MovieUrlEntity> getMovieUrl(Long movieId);

    List<MovieEntity> getPlayRecord(String userId);

    Long savePlayRecord(MovieEntity movieEntity);

    Long saveViewRecord(MovieEntity movieEntity);

    List<MovieEntity> getFavoriteList(String userId,int start,int pageSize);

    Long saveFavorite(Long movieId, String userId);

    Long deleteFavorite(Long movieId,String userId);

    Long isFavorite(Long movieId, String userId);

    Long saveLike(Long movieId, String userId);

    Long deleteLike(Long movieId,String userId);

    Long isLike(Long movieId, String userId);

    List<MovieEntity> getYourLikes(@Param("labels") String[] labels,String classify);

    List<MovieEntity> getRecommend(String classify);

    List<MovieEntity> getRecordList(String userId);

    MovieEntity getMovieDetail(int movieId);

    List<MovieEntity> getMovieListByType(@Param("types") String[] types,String classify);
}
