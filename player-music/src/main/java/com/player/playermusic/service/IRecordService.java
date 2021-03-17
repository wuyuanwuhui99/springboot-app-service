package com.player.playermusic.service;

import com.player.common.entity.ResultEntity;
import com.player.playermusic.Entity.RecordEntity;

public interface IRecordService {
    /**
     * @author: wuwenqiang
     * @methodsName: record
     * @description: 播放记录
     * @param: recordEntity 歌曲的实体对象
     * @return: ResultEntity
     * @date: 2020-07-25 8:26
     */
    ResultEntity record(RecordEntity recordEntity);
}
