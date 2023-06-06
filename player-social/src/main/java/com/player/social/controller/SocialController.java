package com.player.social.controller;

import com.player.common.entity.*;
import com.player.common.myInterface.OperLog;
import com.player.common.utils.JwtToken;
import com.player.common.utils.OperationType;
import com.player.social.service.ISocialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/service")
@Api(value = "评论", description = "查询评论，删除评论")
@RestController
public class SocialController {
    @Autowired
    private ISocialService socialService;

    @ApiOperation("获取总评论数量")
    @GetMapping("/social/getCommentCount")
    public ResultEntity getCommentCount(
            @RequestParam("relationId") int relationId,
            @RequestParam("type") String type
    ) {
        return socialService.getCommentCount(relationId,type);
    }

    @ApiOperation("获取一级评论列表")
    @GetMapping("/social/getTopCommentList")
    public ResultEntity getTopCommentList(
            @RequestParam("relationId") int relationId,
            @RequestParam("type") String type,
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize")int pageSize
    ) {
        return socialService.getTopCommentList(relationId,type,pageNum,pageSize);
    }

    @ApiOperation("新增评论")
    @PostMapping("/social-getway/insertComment")
    public ResultEntity insertComment(
            @RequestHeader("Authorization") String token,
            @RequestBody CommentEntity commentEntity
    ) {
        return socialService.insertComment(token,commentEntity);
    }

    @ApiOperation("删除评论")
    @DeleteMapping("/social-getway/deleteComment/{id}")
    public ResultEntity deleteComment(
            @RequestHeader("Authorization") String token,
            @PathVariable("id") int id
    ) {
        return socialService.deleteComment(id,token);
    }

    @ApiOperation("获取回复列表")
    @GetMapping("/social/getReplyCommentList")
    public ResultEntity getReplyCommentList(
            @RequestParam("topId") int topId,
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize")int pageSize
    ) {
        return socialService.getReplyCommentList(topId,pageNum,pageSize);
    }

    @OperLog(message = "保存收藏记录", operation = OperationType.ADD)
    @ApiOperation("保存收藏记录,请求地地址：/service/movie-getway/saveFavorite")
    @PostMapping("/social-getway/saveLike")
    public ResultEntity saveLike(
            @RequestBody LikeEntity likeEntity,
            @RequestHeader("Authorization") String token
    ) {
        return socialService.saveLike(likeEntity,token);
    }

    @OperLog(message = "删除收藏", operation = OperationType.DELETE)
    @ApiOperation("删除收藏,请求地地址：/service/movie-getway/deleteFavorite")
    @DeleteMapping("/social-getway/deleteLike")
    public ResultEntity deleteLike(
            @RequestParam("movieId") Long relationId,
            @RequestParam("type") String type,
            @RequestHeader("Authorization") String token
    ) {
        return socialService.deleteLike(relationId,type,token);
    }

    @OperLog(message = "查询是否已经收藏", operation = OperationType.QUERY)
    @ApiOperation("查询是否已经收藏,请求地地址：/service/movie-getway/isFavorite")
    @GetMapping("/social-getway/isLike")
    public ResultEntity isLike(
            @RequestParam("relationId") Long relationId,
            @RequestParam("type") String type,
            @RequestHeader("Authorization") String token
    ) {
        return socialService.isLike(relationId,type,token);
    }
}
