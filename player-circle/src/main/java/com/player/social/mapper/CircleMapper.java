package com.player.social.mapper;

import com.player.social.entity.CircleEntity;
import com.player.social.entity.HotCommentMovieEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CircleMapper {

    List<CircleEntity> getCircleList(int start, int pageSize,String type);

    Map<String,Integer> getCircleArticleCount(int id);

    Long getCircleCount(String type);

    List<HotCommentMovieEntity>getHotCommentMovie();

    List<HotCommentMovieEntity>getLastModifyMovie();

    Integer saveSay(CircleEntity circleEntity);
}
