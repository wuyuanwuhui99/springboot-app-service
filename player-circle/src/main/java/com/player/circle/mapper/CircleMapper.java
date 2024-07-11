package com.player.circle.mapper;

import com.player.circle.entity.CircleEntity;
import com.player.circle.entity.HotCommentMovieEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CircleMapper {

    List<CircleEntity> getCircleListByType(int start, int pageSize,String type);

    Map<String,Integer> getCircleArticleCount(int id);

    Long getCircleCount(String type);

    List<HotCommentMovieEntity>getHotCommentMovie();

    List<HotCommentMovieEntity>getLastModifyMovie();

    Integer saveCircle(CircleEntity circleEntity);
}
