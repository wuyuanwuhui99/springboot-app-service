package com.player.circle.mapper;

import com.player.circle.entity.CircleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CircleMapper {

    List<CircleEntity> getCircleArticleList(int start, int pageSize);
}
