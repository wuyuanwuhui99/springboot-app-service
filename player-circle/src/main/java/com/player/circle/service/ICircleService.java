package com.player.circle.service;

import com.player.common.entity.ResultEntity;

public interface ICircleService {

    ResultEntity getCircleArticleList(int pageNum, int pageSize, String type);

    ResultEntity getUserData(String token);

    ResultEntity getCircleArticleCount(int id);
}
