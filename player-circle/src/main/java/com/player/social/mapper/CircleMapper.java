package com.player.social.mapper;

import com.player.social.entity.CircleEntity;
import com.player.social.entity.CircleLikeEntity;
import com.player.social.entity.HotCommentMovieEntity;
import com.player.social.entity.LogCircleEntity;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

@Repository
public interface CircleMapper {

    List<CircleEntity> getCircleList(int start, int pageSize,String type,int permission);

    Long getCircleCountByType(String type);

    Map<String,Integer> getCircleArticleCount(int id);

    Integer insertLog(List<LogCircleEntity> logCircleEntities);

    Long getCircleCount(String type,String keyword);

    List<HotCommentMovieEntity>getHotCommentMovie();

    List<HotCommentMovieEntity>getLastModifyMovie();

    Integer saveSay(CircleEntity circleEntity);

    List<CircleLikeEntity> getCircleLikesByCircleIds(List circleIds);
}
