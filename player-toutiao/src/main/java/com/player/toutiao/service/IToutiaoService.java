package com.player.toutiao.service;

import com.player.common.entity.ResultEntity;

public interface IToutiaoService {
    ResultEntity findArticleList(int pageSize, int pageNum,String type, String keyword);

    ResultEntity findArticleDetail(int id,String path);

    ResultEntity findFavoriteChannels(String token);

    ResultEntity getUserData(String token);

    ResultEntity findAllChannels();

    ResultEntity findArticleByUserId(String userId,int pageNum,int pageSize,String keyword);
}
