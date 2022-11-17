package com.player.circle.service;

import com.player.common.entity.ResultEntity;

public interface ICircleService {

    ResultEntity getCircleArticleList(int pageSize,int pageNum);

    ResultEntity getUserData(String token);
}
