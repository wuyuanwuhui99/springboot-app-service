package com.player.circle.service;

import com.player.circle.entity.CircleEntity;
import com.player.common.entity.ResultEntity;

public interface ICircleService {

    ResultEntity getCircleArticleList(int pageNum, int pageSize, String type, String keyword,String token);

    ResultEntity getUserData(String token);

    ResultEntity getCircleArticleCount(int id);
}
