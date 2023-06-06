package com.player.music.service;

import com.player.common.entity.ResultEntity;

public interface IMyMusicService {
    ResultEntity getKeywordMusic(String redisKey);

    ResultEntity getMusicClassify(String redisKey);

    ResultEntity getMusicListByClassifyId(String redisKey, int classifyId, int pageNum, int pageSize, boolean isRedis, String token);

    ResultEntity getSingerList(String redisKey, int pageNum, int pageSize);
}
