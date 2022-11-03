package com.player.comment.service.imp;

import com.player.comment.mapper.CommentMapper;
import com.player.comment.service.ICommentService;
import com.player.common.entity.*;
import com.player.common.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CommentService implements ICommentService {
    
    @Autowired
    private CommentMapper commentMapper;
    
    /**
     * @author: wuwenqiang
     * @description: 获取总评论数量
     * @date: 2021-08-21 23:15
     */
    @Override
    public ResultEntity getCommentCount(int relationId,String type){
        return ResultUtil.success(commentMapper.getCommentCount(relationId,type));
    }

    /**
     * @author: wuwenqiang
     * @description: 获取一级评论列表
     * @date: 2021-08-21 23:15
     */
    @Override
    public ResultEntity getTopCommentList(int relationId, String type,int pageNum, int pageSize){
        if(pageSize > 100)pageSize = 100;
        int start = (pageNum - 1)*pageSize;
        return ResultUtil.success(commentMapper.getTopCommentList(relationId,type,start,pageSize));
    }

    /**
     * @author: wuwenqiang
     * @description: 新增评论
     * @date: 2021-08-21 23:15
     */
    @Override
    public ResultEntity insertComment(String token, CommentEntity commentEntity){
        commentEntity.setUserId(JwtToken.getUserId(token));
        commentMapper.insertComment(commentEntity);
        commentMapper.getCommentItem(commentEntity.getId());
        return ResultUtil.success(commentMapper.getCommentItem(commentEntity.getId()));
    }

    /**
     * @author: wuwenqiang
     * @description: 删除评论
     * @date: 2021-08-21 23:15
     */
    @Override
    public ResultEntity deleteComment(int id,String token){
        return ResultUtil.success(commentMapper.deleteComment(id,JwtToken.getUserId(token)));
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
        return ResultUtil.success(commentMapper.getReplyCommentList(topId,start,pageSize));
    }
}
