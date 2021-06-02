package com.player.toutiao.service;

import com.player.common.entity.ResultEntity;

import java.util.List;

public interface IToutiaoService {
    ResultEntity findArticleList(int pageSize, int pageNum,String type, String keyword);

    ResultEntity findArticleDetail(int id,String path);

    ResultEntity findFavoriteChannels(String token);

    ResultEntity getUserData(String token);

    ResultEntity findAllChannels(List<Integer> status);

    ResultEntity findArticleByUserId(String userId,int pageNum,int pageSize,String keyword);
}
