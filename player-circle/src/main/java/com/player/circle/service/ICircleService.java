package com.player.circle.service;

import com.player.circle.entity.CircleEntity;
import com.player.common.entity.ResultEntity;

public interface ICircleService {

    ResultEntity getCircleListByType(int pageNum, int pageSize, String type);

    ResultEntity getCircleArticleCount(int id);

    ResultEntity getHotCommentMovie(String path);

    ResultEntity getLastModifyMovie(String path);

    ResultEntity saveCircle(CircleEntity circleEntity, String token);
}
