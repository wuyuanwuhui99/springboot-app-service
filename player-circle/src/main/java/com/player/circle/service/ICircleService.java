package com.player.circle.service;

import com.player.common.entity.ResultEntity;

public interface ICircleService {

    ResultEntity getCircleList(int pageSize,int pageNum);
}
