package com.player.circle.mapper;

import com.player.common.entity.CommentEntity;

import java.util.List;

public interface CommentMapper {

    Long getCommentCount(int relationId,String type);

    List<CommentEntity> getTopCommentList(int relationId, String type, int start, int pageSize);

    Long insertComment(CommentEntity commentEntity);

    List<CommentEntity> getReplyCommentList(int topId, int start, int pageSize);

    Long deleteComment(int id, String userId);

    CommentEntity getCommentItem(Long id);
}
