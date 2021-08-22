package com.player.toutiao.service;

import com.player.common.entity.ResultEntity;
import com.player.toutiao.entity.CommentEntity;

import java.util.List;

public interface IToutiaoService {
    ResultEntity getArticleList(int pageNum, int pageSize,String type, String channelId, String userId, String keyword, String path);

    ResultEntity getArticleDetail(int id,String token);

    ResultEntity getFavoriteChannels(String token);

    ResultEntity getUserData(String token);

    ResultEntity getAllChannels(List<Integer> status);

    ResultEntity getRecordList(String token);

    ResultEntity isFavorite(String token,int articleId);

    ResultEntity getFavoriteList(String token,int pageNum,int pageSize);

    ResultEntity insertFavorite(String token,int articleId);

    ResultEntity deleteFavorite(String token,int articleId);

    ResultEntity isLike(String token, int articleId);

    ResultEntity insertLike(String token,int articleId);

    ResultEntity deleteLike(String token,int articleId);

    ResultEntity isFocus(String token,String authorId);

    ResultEntity insertFocus(String token,String authorId);

    ResultEntity deleteFocus(String token,String authorId);

    ResultEntity getCommentCount(int articleId);

    ResultEntity getTopCommentList(int articleId,int pageNum, int pageSize);

    ResultEntity insertComment(String token,CommentEntity commentEntity);

    ResultEntity deleteComment(int id,String userId);

    ResultEntity getReplyCommentList(int topId,int pageNum,int pageSize);

    ResultEntity getCommentItem(int id);
}
