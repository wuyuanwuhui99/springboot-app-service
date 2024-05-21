package com.player.social.service.imp;

import com.player.social.mapper.SocialMapper;
import com.player.social.service.ISocialService;
import com.player.common.entity.*;
import com.player.common.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SocialService implements ISocialService {
    
    @Autowired
    private SocialMapper socialMapper;

    /**
     * @author: wuwenqiang
     * @description: 获取一级评论列表
     * @date: 2021-08-21 23:15
     */
    @Override
    public ResultEntity getTopCommentList(int relationId, String type,int pageNum, int pageSize){
        if(pageSize > 100)pageSize = 100;
        int start = (pageNum - 1) * pageSize;
        ResultEntity resultEntity = ResultUtil.success(socialMapper.getTopCommentList(relationId,type,start,pageSize));
        resultEntity.setTotal(socialMapper.getCommentCount(relationId,type));
        return resultEntity;
    }

    /**
     * @author: wuwenqiang
     * @description: 新增评论
     * @date: 2021-08-21 23:15
     */
    @Override
    public ResultEntity insertComment(String token, CommentEntity commentEntity){
        commentEntity.setUserId(JwtToken.getUserId(token));
        socialMapper.insertComment(commentEntity);
        return ResultUtil.success(socialMapper.getCommentItem(commentEntity.getId(),commentEntity.getType()));
    }

    /**
     * @author: wuwenqiang
     * @description: 删除评论
     * @date: 2021-08-21 23:15
     */
    @Override
    public ResultEntity deleteComment(int id,String token){
        return ResultUtil.success(socialMapper.deleteComment(id,JwtToken.getUserId(token)));
    }

    /**
     * @author: wuwenqiang
     * @description: 获取回复列表
     * @date: 2021-08-21 23:15
     */
    @Override
    public ResultEntity getReplyCommentList(int topId,int pageNum,int pageSize){
        if(pageSize > 100)pageSize = 100;
        int start = (pageNum - 1)*pageSize;
        return ResultUtil.success(socialMapper.getReplyCommentList(topId,start,pageSize));
    }

    /**
     * @author: wuwenqiang
     * @description: 添加收藏
     * @date: 2020-12-25 22:29
     */
    @Override
    public ResultEntity saveLike(LikeEntity likeEntity, String token) {
        likeEntity.setUserId(JwtToken.getUserId(token));
        socialMapper.saveLike(likeEntity);
        LikeEntity likeById = socialMapper.getLikeById(likeEntity.getId());
        return ResultUtil.success(likeById);
    }

    /**
     * @author: wuwenqiang
     * @description: 删除收藏
     * @date: 2021-03-07 16:10
     */
    @Override
    public ResultEntity deleteLike(Long relationId,String type,String token) {
        UserEntity userEntity = JwtToken.parserToken(token, UserEntity.class);
        return ResultUtil.success(socialMapper.deleteLike(relationId,type,userEntity.getUserId()));
    }

    @Override
    public ResultEntity isLike(Long relationId,String type,String token) {
        UserEntity userEntity = JwtToken.parserToken(token, UserEntity.class);
        return ResultUtil.success(socialMapper.isLike(relationId,type,userEntity.getUserId()));
    }

    /**
     * @author: wuwenqiang
     * @description: 获取评论总数
     * @date: 2021-03-07 16:10
     */
    @Override
    public ResultEntity getCommentCount(int relationId, String type) {
        return ResultUtil.success(socialMapper.getCommentCount(relationId,type));
    }
}
