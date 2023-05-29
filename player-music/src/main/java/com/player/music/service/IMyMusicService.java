package com.player.music.service;

import com.player.common.entity.ResultEntity;

public interface IMyMusicService {
    ResultEntity getKeywordMusic(String redisKey);


    ResultEntity getMusicClassify(String redisKey);

    ResultEntity getMusicByClassifyName(String redisKey,String classifyName,int pageNum,int pageSize);
}
