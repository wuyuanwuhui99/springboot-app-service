package com.player.music.service;

import com.player.common.entity.ResultEntity;
import com.player.music.Entity.MyMusicEntity;

public interface IMyMusicService {
    ResultEntity getKeywordMusic(String redisKey);

    ResultEntity getMusicClassify(String redisKey);

    ResultEntity getMusicListByClassifyId(String redisKey, int classifyId, int pageNum, int pageSize, boolean isRedis, String token);

    ResultEntity getSingerList(String redisKey, int pageNum, int pageSize);

    ResultEntity getMusiPlayMenu(String redisKey,String token);

    ResultEntity getMySinger(String redisKey,String token,int pageNum, int pageSize);

    ResultEntity getMusicRecord(String path, String token, int pageNum, int pageSize);

    ResultEntity insertLog(String token,MyMusicEntity myMusicEntity);
}
