package com.player.video.service;

import com.player.common.entity.ResultEntity;

public interface IVideoService {
    ResultEntity getUserData(String token);

    ResultEntity getVideoList(int pageNum,int pageSize,String star,String category,String type,String label,String userId,String keyword,String path);

}