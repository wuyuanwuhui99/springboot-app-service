package com.player.social.mapper;

import com.player.common.entity.CommentEntity;
import com.player.common.entity.LikeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocialMapper {

    Long getCommentCount(int relationId,String type);

    List<CommentEntity> getTopCommentList(int relationId, String type, int start, int pageSize);

    Long insertComment(CommentEntity commentEntity);

    List<CommentEntity> getReplyCommentList(int topId, int start, int pageSize);

    Long deleteComment(int id, String userId);

    CommentEntity getCommentItem(Long id,String type);

    void saveLike(LikeEntity likeEntity);

    LikeEntity getLikeById(int id);

    Long deleteLike(Long relationId,String type,String userId);

    Long isLike(Long relationId,String type, String userId);
}
