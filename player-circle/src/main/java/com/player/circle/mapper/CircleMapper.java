package com.player.circle.mapper;

import com.player.circle.entity.CircleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CircleMapper {

    List<CircleEntity> getCircleList(int start, int pageSize);
}
