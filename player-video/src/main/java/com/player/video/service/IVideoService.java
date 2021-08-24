package com.player.video.service;

import com.player.common.entity.ResultEntity;
import com.player.video.entity.ChannelEntity;
import com.player.video.entity.CommentEntity;

import java.util.List;

public interface IVideoService {
    ResultEntity getUserData(String token);

    ResultEntity getVideoList(int pageNum,int pageSize,String star,String category,String type,String label,String userId,String keyword,String path);

    ResultEntity insertFavoriteChannels(List<ChannelEntity> channelEntities);

    ResultEntity getFavoriteChannels(String token);

    ResultEntity isFavorite(String token,int videoId);

    ResultEntity insertFavorite(String token, int videoId);

    ResultEntity deleteFavorite(String token, int videoId);

    ResultEntity isLike(String token,int videoId);

    ResultEntity insertLike(String token, int videoId);

    ResultEntity deleteLike(String token, int videoId);

    ResultEntity isFocus(String token,String authorId);

    ResultEntity insertFocus(String token,String authorId);

    ResultEntity deleteFocus(String token,String authorId);

    ResultEntity getFavoriteList(String token, int pageNum, int pageSize);

    ResultEntity getVideoRecordList(String token);

    ResultEntity getCommentCount(int videoId);

    ResultEntity getTopCommentList(int videoId,int pageNum, int pageSize);

    ResultEntity insertComment(String token, CommentEntity commentEntity);

    ResultEntity deleteComment(int id,String userId);

    ResultEntity getReplyCommentList(int topId,int pageNum,int pageSize);

    ResultEntity getCommentItem(int id);

    ResultEntity getRecordList(String token);
}