package com.player.music.service;

import com.player.common.entity.ResultEntity;

public interface IMyMusicService {
    ResultEntity getKeywordMusic(String redisKey);

    ResultEntity getRecommendMusic(String redisKey,int pageNum,int pageSize);
}
