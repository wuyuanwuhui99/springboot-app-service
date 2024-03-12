package com.player.social.service;

import com.player.social.entity.SayEntity;
import com.player.common.entity.ResultEntity;

public interface ICircleService {

    ResultEntity getCircleArticleList(int pageNum, int pageSize, String type);

    ResultEntity getCircleArticleCount(int id);

    ResultEntity getHotCommentMovie(String path);

    ResultEntity getLastModifyMovie(String path);

    ResultEntity saveSay(SayEntity sayEntity, String token);
}
