package com.player.circle.controller;

import com.player.circle.service.ICommentService;
import com.player.common.entity.ResultEntity;
import com.player.common.entity.CommentEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/service")
@Api(value = "评论", description = "查询评论，删除评论")
@RestController
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @ApiOperation("获取总评论数量")
    @GetMapping("/comment/getCommentCount")
    public ResultEntity getCommentCount(
            @RequestParam("relationId") int relationId,
            @RequestParam("type") String type
    ) {
        return commentService.getCommentCount(relationId,type);
    }

    @ApiOperation("获取一级评论列表")
    @GetMapping("/comment/getTopCommentList")
    public ResultEntity getTopCommentList(
            @RequestParam("relationId") int relationId,
            @RequestParam("type") String type,
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize")int pageSize
    ) {
        return commentService.getTopCommentList(relationId,type,pageNum,pageSize);
    }

    @ApiOperation("新增评论")
    @PostMapping("/comment-getway/insertComment")
    public ResultEntity insertComment(
            @RequestHeader("Authorization") String token,
            @RequestBody CommentEntity commentEntity
    ) {
        return commentService.insertComment(token,commentEntity);
    }

    @ApiOperation("删除评论")
    @DeleteMapping("/comment-getway/deleteComment/{id}")
    public ResultEntity deleteComment(
            @RequestHeader("Authorization") String token,
            @PathVariable("id") int id
    ) {
        return commentService.deleteComment(id,token);
    }

    @ApiOperation("获取回复列表")
    @GetMapping("/comment/getReplyCommentList")
    public ResultEntity getReplyCommentList(
            @RequestParam("topId") int topId,
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize")int pageSize
    ) {
        return commentService.getReplyCommentList(topId,pageNum,pageSize);
    }
}
