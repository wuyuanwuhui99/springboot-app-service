package com.player.music.service;


import com.player.common.entity.ResultEntity;

public interface IDouyinService {
    /**
     * @author: wuwenqiang
     * @methodsName: getDouyinList
     * @description: 查询抖音播放列表
     * @return: ResultEntity
     * @date: 2020-07-25 8:26
     */
    public ResultEntity getDouyinList(String path);
}
