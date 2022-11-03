package com.player.comment.service;

import com.player.common.entity.ResultEntity;
import com.player.common.entity.CommentEntity;

public interface ICommentService {

    ResultEntity getCommentCount(int relaitionId,String type);

    ResultEntity getTopCommentList(int movieId, String type, int pageNum, int pageSize);

    ResultEntity insertComment(String token, CommentEntity commentEntity);

    ResultEntity deleteComment(int id, String userId);

    ResultEntity getReplyCommentList(int topId, int pageNum, int pageSize);
}
